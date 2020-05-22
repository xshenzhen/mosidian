package com.example.demo.dao.sys;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.model.sys.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.sys.PermissionList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shenzhen
 * @since 2020-05-19
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<PermissionList> getPermissionList(@Param(Constants.WRAPPER) Wrapper<PermissionList> wrapper);

}
