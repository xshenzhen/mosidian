package com.mosidian.web.model.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PermissionList implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;
    private String permissionUuid;
    private String permissionName;
    private String permissionUrl;
    private String permissionKey;
    private String userId;
}
