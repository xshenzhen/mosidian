package com.mosidian.web.service.impl.mms;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mosidian.web.model.mms.Member;
import com.mosidian.web.dao.mms.MemberMapper;
import com.mosidian.web.service.mms.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mosidian.web.utils.Const;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shenzhen
 * @since 2020-05-18
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;


    @Override
    public IPage<Member> listAndPage(Page<Member> page, String name) {
        QueryWrapper<Member> wrapper=new QueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(name),"name",name)
                .eq("recordstatus",true).orderByAsc("no");
        return  memberMapper.memberList(page,wrapper);
    }

    @Override
    public String getMaxMemberNo() {
        QueryWrapper<Member> wrapper= new QueryWrapper<>();
        wrapper.orderByDesc("no").eq("recordstatus",true);
        List<Member> list = memberMapper.selectList(wrapper);
        Member member=null;
        int intNo=0;
        if (list.size()>0){
             member=list.get(0);
            String no=member.getNo();
            String substring = no.substring(Const.MEMBER_NO_PREFIX_NAME.length());
            intNo = Convert.toInt(substring)+1;
        }
        return Const.MEMBER_NO_PREFIX_NAME+intNo;
    }

}
