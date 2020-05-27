package com.mosidian.web.model.mms;

import java.math.BigDecimal;
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
@TableName("mms_member")
public class  Member implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会员姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 当前余额
     */
    private BigDecimal balance;

    /**
     * 积分
     */
    private Integer credits;

    /**
     * 是否会员
     */
    private String state;

    /**
     * 是否禁用
     */
    private String enable;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 标签
     */
    private String label;

    /**
     * 会员编号
     */
    private String no;

    /**
     * userid
     */
    private Integer userid;

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

    private String mid;

    private String base1;

    private String base2;

    private String base3;

    private String base4;

    private String base5;


}
