package com.mosidian.web.model.oms;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@TableName("oms_express")
public class Express implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 快递LOGO
     */
    private String logo;

    /**
     * 快递公司名称
     */
    private String name;

    /**
     * 快递认证码
     */
    private String code;

    /**
     * 编号
     */
    private String no;

    /**
     * 状态
     */
    private Boolean state;

    /**
     * 说明
     */
    private String memo;

    private String uuid;


}
