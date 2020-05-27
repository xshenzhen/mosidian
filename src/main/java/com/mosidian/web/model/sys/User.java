package com.mosidian.web.model.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author shenzhen
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 角色表
     */
    private Integer roleid;


    /**
     * 备注
     */
    private String memo;

    /**
     * 创建日期
     */
    private Date createdate;

    /**
     * 创建人
     */
    private String creater;

    /**
     * 编辑日期
     */
    private Date editdate;

    /**
     * 编辑人
     */
    private String editor;

    /**
     * 记录状态
     */
    private Boolean recordstatus;

    private String uuid;

    private Integer shopid;

     /**
     * 是否是审核通过首次登录
     */
    private Integer isLogin;

     /**
     * 审核状态
     */
    private Integer status;
   /**
     * 审核回复
     */
    private String message;


}
