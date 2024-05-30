
package com.service;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.EvaluateEntity;
import com.utils.PageUtils;


/**
 * 系统用户
 */
public interface EvaluateService extends IService<EvaluateEntity> {
	PageUtils queryPage(Map<String, Object> params,Wrapper<EvaluateEntity> wrapper);
}
