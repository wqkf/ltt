
package com.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.TokenDao;
import com.entity.RiskEntity;
import com.service.RiskService;
import com.utils.CommonUtil;
import com.utils.PageUtils;
import com.utils.Query;


/**
 * token
 */
@Service("tokenService")
public class RiskServiceImpl extends ServiceImpl<TokenDao, RiskEntity> implements RiskService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<RiskEntity> page = this.selectPage(
                new Query<RiskEntity>(params).getPage(),
                new EntityWrapper<RiskEntity>()
        );
        return new PageUtils(page);
	}

	@Override
	public List<RiskEntity> selectListView(Wrapper<RiskEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params,
			Wrapper<RiskEntity> wrapper) {
		 Page<RiskEntity> page =new Query<RiskEntity>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
	}

	@Override
	public String generateToken(Long userid,String username, String tableName, String role) {
		RiskEntity riskEntity = this.selectOne(new EntityWrapper<RiskEntity>().eq("userid", userid).eq("role", role));
		String token = CommonUtil.getRandomString(32);
		Calendar cal = Calendar.getInstance();   
    	cal.setTime(new Date());   
    	cal.add(Calendar.HOUR_OF_DAY, 1);
//		if(riskEntity !=null) {
//			riskEntity.setToken(token);
//			riskEntity.setExpiratedtime(cal.getTime());
//			this.updateById(riskEntity);
//		} else {
//			this.insert(new RiskEntity(userid,username, tableName, role, token, cal.getTime()));
//		}
		return token;
	}

	@Override
	public RiskEntity getTokenEntity(String token) {
		RiskEntity riskEntity = this.selectOne(new EntityWrapper<RiskEntity>().eq("token", token));
//		if(riskEntity == null || riskEntity.getExpiratedtime().getTime()<new Date().getTime()) {
//			return null;
//		}
		return riskEntity;
	}
}
