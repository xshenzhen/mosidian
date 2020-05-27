package com.mosidian.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mosidian.web.common.redis.RedisService;
import com.mosidian.web.model.sys.User;
import com.mosidian.web.service.sys.UserService;
import com.mosidian.web.utils.PageData;
import com.mosidian.web.utils.SuperUtils;
import com.mosidian.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class HomeController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @GetMapping("/")
    public ModelAndView login(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index-test");
        return mv;
    }

    @GetMapping("/sys/megaData/outlet")
    public ModelAndView outlet(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("sys/megaData/outlet");
        return mv;
    }

    @GetMapping("/sys/user/card")
    public ModelAndView meCard(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("sys/user/card");
        return mv;
    }



    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("register");
        return mv;
    }

    @GetMapping("/sys/kh/register")
    public ModelAndView kh(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("sys/kehu/register");
        return mv;
    }

    @GetMapping("/bcLogin")
    public ModelAndView bcLogin(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("sys/login/bcLogin");
        return mv;
    }

    @GetMapping("/meLogin")
    public ModelAndView meLogin(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("sys/login/meLogin");
        return mv;
    }


    @RequestMapping(value = "/index-member",method = RequestMethod.GET)
    public ModelAndView indexMember(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index-member");
        return mv;
    }

    @RequestMapping(value = "/index-admin",method = RequestMethod.GET)
    public ModelAndView indexAdmin(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index-admin");
        return mv;
    }

    @RequestMapping(value = "/index-user",method = RequestMethod.GET)
    public ModelAndView indexUser(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index-user");
        return mv;
    }

    @RequestMapping(value = "/index-express",method = RequestMethod.GET)
    public ModelAndView indexExpress(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index-express");
        return mv;
    }

    @RequestMapping(value = "/index-check",method = RequestMethod.GET)
    public ModelAndView indexCheck(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index-check");
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam(name = "username") String username,
                              @RequestParam(name = "password") String password, HttpServletResponse response,HttpSession session) {
        String token = userService.login(username, password);
        if (token == null) {
            return this.error("用户名或密码错误");
        }
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        User user=userService.getOne(wrapper);
        if (Objects.isNull(user)){
            return this.error("用户名或密码错误");
        }
        SuperUtils.addCookie(response,"Authorization","Bearer"+token,3600);
        PageData pd=this.getPageData();
        String roleId=user.getRoleid().toString();
        pd.put("roleId",roleId);
        pd.put("isLogin",user.getIsLogin());
        session.setAttribute("username",user.getUsername());
        return this.success(pd,"","");
    }

    @RequestMapping("/loginOut")
    public String loginOut() {
        return "/login";
    }

}
