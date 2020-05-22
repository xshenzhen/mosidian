package com.example.demo.service.impl.mms;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.model.mms.Member;
import com.example.demo.dao.mms.MemberMapper;
import com.example.demo.service.mms.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
                .eq("recordstatus",true);
        return  memberMapper.memberList(page,wrapper);
    }
}
