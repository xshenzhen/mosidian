package com.mosidian.web.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.mosidian.web.model.mms.Member;
import com.mosidian.web.model.sys.ContactUs;
import com.mosidian.web.model.sys.User;
import com.mosidian.web.service.mms.MemberService;
import com.mosidian.web.service.sys.ContactUsService;
import com.mosidian.web.service.sys.SysCaptchaService;
import com.mosidian.web.service.sys.UserService;
import com.mosidian.web.utils.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

/**
 * @Author ZSY
 * @createTime 2020/5/26 10:25
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController extends com.mosidian.web.controller.BaseController {

    @Resource
    private ContactUsService contactUsService;

    @Resource
    private MemberService memberService;

    @Resource
    private UserService userService;

    @Resource
    private SysCaptchaService sysCaptchaService;

    @PostMapping(value = "/contactUs/update")
    @ResponseBody
    public Object contactUpdate(@RequestParam(name = "ticket") String ticket,
                                @RequestParam(name = "randstr") String randstr,
                                @RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "email", required = false) String email,
                                @RequestParam(name = "phone", required = false) String phone,
                                @RequestParam(name = "feedbackMessage", required = false) String feedbackMessage) {

        if (ticket != null && randstr != null) {
            ContactUs contactUs = new ContactUs();
            contactUs.setCreatedate(new Date());
            contactUs.setName(name);
            contactUs.setEmail(email);
            contactUs.setPhone(phone);
            contactUs.setFeedbackMessage(feedbackMessage);
            boolean save = contactUsService.save(contactUs);
            return this.getMessage(Const.ADDTYPE, save);
        } else {
            return this.success("提交失败！");
        }
    }

    @PostMapping(value = "/member/update")
    @ResponseBody
    public Object memberUpdate(@RequestParam("code") String code,
                               @RequestParam("uuid") String uuid,
                               @RequestParam(name = "name", required = false) String name,
                               @RequestParam(name = "email", required = false) String email,
                               @RequestParam(name = "id", required = false) String ids,
                               @RequestParam(name = "address", required = false) String address,
                               @RequestParam(name = "phone", required = false) String phone,
                               @RequestParam(name = "tell", required = false) String tell) {

        boolean validate = sysCaptchaService.validate(uuid, code);
        if (!validate) {
            return this.error("验证码错误！");
        } else {
            Member member = new Member();
            boolean flag;
            if (StrUtil.isNotEmpty(ids)) {
                int id = Convert.toInt(ids);
                member.setName(name);
                member.setBase1(email);
                member.setRecordstatus(true);
                member.setId(id);
                member.setPhone(phone);
                member.setEditdate(new Date());
                flag = memberService.updateById(member);
            } else {
                String maxMemberNo = memberService.getMaxMemberNo();
                member.setCreatedate(new Date());
                member.setName(name);
                member.setBase1(address);
                member.setBase2(tell);
                member.setRecordstatus(true);
                member.setNo(maxMemberNo);
                member.setPhone(phone);
                flag = memberService.save(member);
            }
            if (flag) {
                return this.success("操作成功");
            } else {
                return this.error("操作失败");
            }
        }


    }

    @PostMapping(value = "/user/update")
    @ResponseBody
    public Object userUpdate(@RequestParam(name = "username", required = false) String username,
                             @RequestParam(name = "phone", required = false) String phone,
                             @RequestParam(name = "id", required = false) String ids) {
        User user = new User();
        user.setUsername(username);
        user.setRecordstatus(true);
        user.setPhone(phone);
        boolean status;
        if (StrUtil.isNotEmpty(ids)) {
            int id = Convert.toInt(ids);
            user.setId(id);
            user.setEditdate(new Date());
            status = userService.updateById(user);
        } else {
            user.setCreatedate(new Date());
            status = userService.save(user);
        }
        if (status) {
            return this.success("操作成功");
        } else {
            return this.error("操作失败");
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public Object userRegister(@RequestParam("userNo") String userNo,
                               @RequestParam("password") String password,
                               @RequestParam("memo") String memo,
                               @RequestParam("code") String code,
                               @RequestParam("uuid") String uuid) {

        boolean validate = sysCaptchaService.validate(uuid, code);
        if (validate) {
            int register = userService.register(userNo, password, memo);
            if (register == 2) {
                return this.error("该会员不存在！");
            } else if (register == 1) {
                return this.success("申请成功，待审核！");
            } else if (register == 0) {
                return this.error("申请失败，请稍后再试！");
            } else {
                return this.error("该企业已入住");
            }
        } else {
            return this.error("验证码错误，请重新输入！");
        }

    }


    /**
     * 验证码
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }
}
