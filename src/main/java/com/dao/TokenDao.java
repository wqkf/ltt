
package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.RiskEntity;

/**
 * token
 */
public interface TokenDao extends BaseMapper<RiskEntity> {
	
	List<RiskEntity> selectListView(@Param("ew") Wrapper<RiskEntity> wrapper);

	List<RiskEntity> selectListView(Pagination page, @Param("ew") Wrapper<RiskEntity> wrapper);
	
}
