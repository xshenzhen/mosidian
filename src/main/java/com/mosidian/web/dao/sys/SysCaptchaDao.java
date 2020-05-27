package com.mosidian.web.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mosidian.web.model.sys.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
