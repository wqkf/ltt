package com.dao;

import com.entity.WuzileixingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WuzileixingVO;
import com.entity.view.WuzileixingView;


/**
 * 物资类型
 * 
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface WuzileixingDao extends BaseMapper<WuzileixingEntity> {
	
	List<WuzileixingVO> selectListVO(@Param("ew") Wrapper<WuzileixingEntity> wrapper);
	
	WuzileixingVO selectVO(@Param("ew") Wrapper<WuzileixingEntity> wrapper);
	
	List<WuzileixingView> selectListView(@Param("ew") Wrapper<WuzileixingEntity> wrapper);

	List<WuzileixingView> selectListView(Pagination page,@Param("ew") Wrapper<WuzileixingEntity> wrapper);
	
	WuzileixingView selectView(@Param("ew") Wrapper<WuzileixingEntity> wrapper);
	

}
