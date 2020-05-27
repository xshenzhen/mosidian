package com.mosidian.web.dao.mms;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mosidian.web.model.mms.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shenzhen
 * @since 2020-05-18
 */
public interface MemberMapper extends BaseMapper<Member> {

    IPage<Member> memberList(Page<Member> page,@Param(Constants.WRAPPER) Wrapper<Member> wrapper);
}
