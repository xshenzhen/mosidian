package com.mosidian.web.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mosidian.web.model.sys.ContactUs;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shenzhen
 * @since 2020-05-22
 */
public interface ContactUsService extends IService<ContactUs> {

    IPage<ContactUs> listByPage(Page<ContactUs> page,String name ,String phone);
}
