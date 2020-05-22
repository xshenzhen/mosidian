package com.example.demo.controller.mms;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.model.mms.Member;
import com.example.demo.service.mms.MemberService;
import com.example.demo.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/member")
@SuppressWarnings("all")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('mms:member:read')")
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
                         @RequestParam(name = "no",required = false) String no,
                         @RequestParam(name = "id",required = false) String ids){
        Member member=new Member();
        member.setName(name);
        member.setNo(no);
        member.setRecordstatus(true);
        boolean flag ;
        if (StrUtil.isNotEmpty(ids)){
            int id = Convert.toInt(ids);
            member.setId(id);
            member.setEditdate(new Date());
             flag = memberService.updateById(member);
        }else {
            member.setCreatedate(new Date());
            flag = memberService.save(member);
        }
        if (flag){
            return this.success("操作成功");
        }else {
            return this.error("操作失败");
        }
    }

}

