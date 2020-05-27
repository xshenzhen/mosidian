package com.mosidian.web.service.impl.sys;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mosidian.web.model.sys.ContactUs;
import com.mosidian.web.dao.sys.ContactUsMapper;
import com.mosidian.web.service.sys.ContactUsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shenzhen
 * @since 2020-05-22
 */
@Service
public class ContactUsServiceImpl extends ServiceImpl<ContactUsMapper, ContactUs> implements ContactUsService {

    @Autowired
    private ContactUsMapper contactUsMapper;

    @Override
    public IPage<ContactUs> listByPage(Page<ContactUs> page, String name, String phone) {
        QueryWrapper<ContactUs> wrapper=new QueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(name),"name",name)
                .like(StrUtil.isNotEmpty(phone),"phone",phone)
                .eq("recordstatus",true);
       return contactUsMapper.contsctUsList(page,wrapper);
    }
}
