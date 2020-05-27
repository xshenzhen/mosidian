package com.mosidian.web.common.security;


import com.mosidian.web.model.sys.PermissionList;
import com.mosidian.web.model.sys.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
* @Description: SpringSecurity需要的用户详情
* @Author: shenzhen
* @Date: 2020/4/8
*/
public class AdminUserDetails implements UserDetails {

    private User user;
    private List<PermissionList> permissionList;
    public AdminUserDetails(User user, List<PermissionList> permissionList) {
        this.user = user;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return  permissionList.stream()
                .filter(permission -> permission.getPermissionKey() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionKey()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getRecordstatus().equals(1);
    }
}
