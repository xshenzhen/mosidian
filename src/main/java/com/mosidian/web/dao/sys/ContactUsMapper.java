package com.mosidian.web.dao.sys;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mosidian.web.model.sys.ContactUs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shenzhen
 * @since 2020-05-22
 */
public interface ContactUsMapper extends BaseMapper<ContactUs> {

    IPage<ContactUs> contsctUsList(Page<ContactUs> page, @Param(Constants.WRAPPER) Wrapper<ContactUs> wrapper);
}
