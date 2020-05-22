package com.example.demo.service.sys;

import com.example.demo.model.sys.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.sys.PermissionList;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shenzhen
 * @since 2020-05-19
 */
public interface PermissionService extends IService<Permission> {

    List<PermissionList> getPermissionList(String userId);
}
