package com.mosidian.web.controller.mms;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mosidian.web.model.mms.Member;
import com.mosidian.web.service.mms.MemberService;
import com.mosidian.web.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.mosidian.web.controller.BaseController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shenzhen
 * @since 2020-05-18
 */
@Controller
@RequestMapping("/member")
@SuppressWarnings("all")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(name = "name",required = false) String name){
        ModelAndView mv =new ModelAndView();
        Page<Member> page=new Page<>(this.pageIndex(),this.pageSize());
        IPage<Member> iPage = memberService.listAndPage(page, name);
        PageData pd=this.getPageData();
        pd.put("name",name);
        mv.setViewName("mms/member/list");
        mv.addObject("memberList",iPage);
        mv.addObject("pd",pd);
        return mv;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam(name = "id") int id){
        Member member=new Member();
        member.setId(id);
        member.setRecordstatus(false);
        boolean flag = memberService.updateById(member);
        if (flag){
            return this.success("删除成功");
        }else {
            return this.error("删除失败");
        }
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(name = "id",required = false) int id){
        ModelAndView mv=new ModelAndView();
        Member member=new Member();
        if (id>0){
            member=memberService.getById(id);
        }
        mv.addObject("member", member);
        mv.setViewName("mms/member/edit");
        return mv;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestParam(name = "name",required = false) String name,
                         @RequestParam(name = "email",required = false) String email,
                         @RequestParam(name = "id",required = false) String ids,
                         @RequestParam(name = "address",required = false) String address,
                         @RequestParam(name = "phone",required = false) String phone,
                         @RequestParam(name = "tell",required = false) String tell){
        Member member=new Member();
        boolean flag ;
        if (StrUtil.isNotEmpty(ids)){
            int id = Convert.toInt(ids);
            member.setName(name);
            member.setBase1(email);
            member.setRecordstatus(true);
            member.setId(id);
            member.setPhone(phone);
            member.setEditdate(new Date());
            flag = memberService.updateById(member);
        }else {
            String maxMemberNo = memberService.getMaxMemberNo();
            member.setCreatedate(new Date());
            if (StrUtil.isEmpty(name) | StrUtil.isEmpty(address)| StrUtil.isEmpty(tell)| StrUtil.isEmpty(phone)){
                return this.error("信息有误，请重新填写");
            }
            member.setName(name);
            member.setBase1(address);
            member.setBase2(tell);
            member.setRecordstatus(true);
            member.setNo(maxMemberNo);
            member.setPhone(phone);
            flag = memberService.save(member);
        }
        if (flag){
            return this.success("操作成功");
        }else {
            return this.error("操作失败");
        }
    }

}

