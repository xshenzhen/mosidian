package com.mosidian.web.utils;


import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
* @Description:
* @Param:
* @return:
* @Author: shenzhen
* @Date: 2020/2/14
*/
@Component
public class Current {
   /* @Autowired
    private RedisService redisService;

    public  Employee get() {
        try {
            Object token = this.getRequest().getSession().getAttribute(Const.SESSION_USER_TOKEN_KEY);
            if (token != null) {
                Object empJson = redisService.get(Const.RedisCache.TOKEN_USER + token.toString());
                if (empJson != null) {
                    String jsonStr = JSONUtil.toJsonStr(empJson.toString());
                    return JSONUtil.toBean(jsonStr,Employee.class);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }




    public HttpServletRequest getRequest() throws Exception {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }*/
}
