package com.mosidian.web.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mosidian.web.model.sys.Permission;
import com.mosidian.web.dao.sys.PermissionMapper;
import com.mosidian.web.model.sys.PermissionList;
import com.mosidian.web.service.sys.PermissionService;
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
 * @since 2020-05-19
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionList> getPermissionList(String userId) {
        QueryWrapper<PermissionList> wrapper=new QueryWrapper<>();
        wrapper.isNotNull("e.permission_uuid")
                .eq("a.id",userId);
        return permissionMapper.getPermissionList(wrapper);
    }
}
