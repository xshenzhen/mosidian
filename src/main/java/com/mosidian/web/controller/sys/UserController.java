package com.mosidian.web.controller.sys;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mosidian.web.model.mms.Member;
import com.mosidian.web.model.sys.User;
import com.mosidian.web.service.mms.MemberService;
import com.mosidian.web.service.sys.UserService;
import com.mosidian.web.utils.Const;
import com.mosidian.web.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/user")
@SuppressWarnings("all")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/list")
    public ModelAndView list(@RequestParam(name = "username",required = false) String username){
        ModelAndView mv =new ModelAndView();
        Page<User> page=new Page<>(this.pageIndex(),this.pageSize());
        IPage<User> iPage = userService.listAndPage(page, username);
        PageData pd=this.getPageData();
        pd.put("username",username);
        mv.setViewName("sys/user/list");
        mv.addObject("userList",iPage);
        mv.addObject("pd",pd);
        return mv;
    }


    @GetMapping("/listCheck")
    public ModelAndView listCheck(@RequestParam(name = "username",required = false) String username){
        ModelAndView mv =new ModelAndView();
        Page<User> page=new Page<>(this.pageIndex(),this.pageSize());
        IPage<User> iPage = userService.listAndPage(page, username);
        PageData pd=this.getPageData();
        pd.put("username",username);
        mv.setViewName("sys/user/listCheck");
        mv.addObject("userList",iPage);
        mv.addObject("pd",pd);
        return mv;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam(name = "id") int id){
        User user=new User();
        user.setId(id);
        user.setRecordstatus(false);
        boolean flag = userService.updateById(user);
        if (flag){
            return this.success("删除成功");
        }else {
            return this.error("删除失败");
        }
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(name = "id",required = false) int id){
        ModelAndView mv=new ModelAndView();
        User user=new User();
        if (id>0){
            user=userService.getById(id);
        }
        mv.addObject("user", user);
        mv.setViewName("sys/user/edit");
        return mv;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestParam(name = "username",required = false) String username,
                         @RequestParam(name = "phone",required = false) String phone,
                         @RequestParam(name = "id",required = false) String ids){
        User user=new User();
        user.setUsername(username);
        user.setRecordstatus(true);
        user.setPhone(phone);
        boolean status ;
        if (StrUtil.isNotEmpty(ids)){
            int id = Convert.toInt(ids);
            user.setId(id);
            user.setEditdate(new Date());
            status = userService.updateById(user);
        }else {
            user.setCreatedate(new Date());
            status = userService.save(user);
        }
        if (status){
            return this.success("操作成功");
        }else {
            return this.error("操作失败");
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(@RequestParam("userNo") String userNo,
                           @RequestParam("password") String password,
                           @RequestParam("memo") String memo) {
        int register = userService.register(userNo, password, memo);
        if (register == 2) {
            return this.error("该会员不存在！");
        } else if (register == 1){
            return this.success("申请成功，待审核！");
        } else if (register == 0){
            return this.error("申请失败，请稍后再试！");
        }else {
            return this.error("该企业已入住");
        }
    }

    @PostMapping("/checkUserStatus")
    @ResponseBody
    public Object checkUserStatus(@RequestParam(name = "id") String id,
                                  @RequestParam(name = "message") String message,
                                  @RequestParam(name = "status") String statusType){
        boolean status = userService.checkUserStatus(id,message,statusType);
        return  this.getMessage(Const.UPDATETYPE,status);
    }

    @GetMapping("/editCheck")
    @ResponseBody
    public ModelAndView editCheck(@RequestParam(name = "id") int id){
        ModelAndView mv=new ModelAndView();
        User user = userService.getById(id);
        mv.setViewName("sys/user/editCheck");
        mv.addObject("user",user);
        return mv;
    }


    @RequestMapping("/activation")
    @ResponseBody
    public Object activation(@RequestParam(name = "id") String id){
        boolean activation = userService.activation(id);
        return this.getMessage(Const.UPDATETYPE,activation);
    }
}

