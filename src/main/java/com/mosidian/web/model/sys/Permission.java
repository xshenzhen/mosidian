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
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String permissionUuid;

    /**
     * 名称
     */
    private String permissionName;

    private Boolean recordstatus;

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
     * 说明
     */
    private String permissionDesc;

    private String permissionUrl;

    private String parentPermissionUuid;

    /**
     * 序号
     */
    private Integer sortno;

    /**
     * 是否显示
     */
    private Boolean isVisible;

    /**
     * 标识
     */
    private String permissionKey;

    /**
     * 图标
     */
    private String avatar;

    private Integer level;


}
