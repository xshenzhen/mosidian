package com.mosidian.web.controller.sys;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mosidian.web.model.sys.ContactUs;
import com.mosidian.web.service.sys.ContactUsService;
import com.mosidian.web.utils.Const;
import com.mosidian.web.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2020-05-22
 */
@Controller
@RequestMapping("/contactUs")
public class ContactUsController extends BaseController {

    @Autowired
    private ContactUsService contactUsService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(name = "name",required = false) String name,
                             @RequestParam(name = "phone",required = false) String phone){
        ModelAndView mv =new ModelAndView();
        Page<ContactUs> page=new Page<>(this.pageIndex(),this.pageSize());
        IPage<ContactUs> iPage = contactUsService.listByPage(page, name,phone);
        PageData pd=this.getPageData();
        pd.put("name",name);
        pd.put("phone",phone);
        mv.setViewName("sys/contactUs/list");
        mv.addObject("contactUsList",iPage);
        mv.addObject("pd",pd);
        return mv;
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(name = "id",required = false) int id){
        ModelAndView mv=new ModelAndView();
        ContactUs contactUs=new ContactUs();
        if (id>0){
            contactUs=contactUsService.getById(id);
        }
        mv.addObject("contactUs", contactUs);
        mv.setViewName("sys/contactUs/edit");
        return mv;
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestParam(name = "name",required = false) String name,
                         @RequestParam(name = "email",required = false) String email,
                         @RequestParam(name = "phone",required = false) String phone,
                         @RequestParam(name = "feedbackMessage",required = false) String feedbackMessage){
        ContactUs contactUs=new ContactUs();
        if (StrUtil.isEmpty(name) | StrUtil.isEmpty(email)| StrUtil.isEmpty(phone)| StrUtil.isEmpty(feedbackMessage)){
            return this.error("信息有误，请重新填写");
        }
        contactUs.setCreatedate(new Date());
        contactUs.setName(name);
        contactUs.setEmail(email);
        contactUs.setPhone(phone);
        contactUs.setFeedbackMessage(feedbackMessage);
        boolean save = contactUsService.save(contactUs);
        return this.getMessage(Const.ADDTYPE,save);
    }
}

