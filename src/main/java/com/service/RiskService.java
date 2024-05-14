
package com.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.RiskEntity;
import com.utils.PageUtils;


/**
 * token
 */
public interface RiskService extends IService<RiskEntity> {
 	PageUtils queryPage(Map<String, Object> params);
    
   	List<RiskEntity> selectListView(Wrapper<RiskEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<RiskEntity> wrapper);
	
   	String generateToken(Long userid,String username,String tableName, String role);
   	
   	RiskEntity getTokenEntity(String token);
}
