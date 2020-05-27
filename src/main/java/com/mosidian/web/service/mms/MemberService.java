package com.mosidian.web.service.mms;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mosidian.web.model.mms.Member;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shenzhen
 * @since 2020-05-18
 */
public interface MemberService extends IService<Member> {

    IPage<Member> listAndPage(Page<Member> page, String name);

    String getMaxMemberNo();
}
