package com.example.demo.controller.sys;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.model.mms.Member;
import com.example.demo.model.sys.User;
import com.example.demo.service.mms.MemberService;
import com.example.demo.service.sys.UserService;
import com.example.demo.utils.Const;
import com.example.demo.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.controller.BaseController;
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

    @PostMapping("/register")
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
        } else {
            return this.error("密码错误");
        }

    }

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
}

