
package com.service.impl;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.EvaluateDao;
import com.entity.EvaluateEntity;
import com.service.EvaluateService;
import com.utils.PageUtils;
import com.utils.Query;


/**
 * 系统用户
 */
@Service("configService")
public class EvaluateServiceImpl extends ServiceImpl<EvaluateDao, EvaluateEntity> implements EvaluateService {
	@Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<EvaluateEntity> wrapper) {
		Page<EvaluateEntity> page = this.selectPage(
                new Query<EvaluateEntity>(params).getPage(),
                wrapper
        );
        return new PageUtils(page);
	}
}
