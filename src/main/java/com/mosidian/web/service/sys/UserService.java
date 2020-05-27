package com.mosidian.web.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mosidian.web.model.sys.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shenzhen
 * @since 2020-05-18
 */
public interface UserService extends IService<User> {

    String login(String username, String password);

    IPage<User> listAndPage(Page<User> page,String username);

    int register(String userNo,String password, String memo);

    boolean checkUserStatus(String id,String message,String status);

    boolean activation(String id);
}
