package com.dao;

import com.entity.ThresholdEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WuzichoucuoVO;
import com.entity.view.ThresholdView;


/**
 * 物资筹措
 * 
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface WuzichoucuoDao extends BaseMapper<ThresholdEntity> {
	
	List<WuzichoucuoVO> selectListVO(@Param("ew") Wrapper<ThresholdEntity> wrapper);
	
	WuzichoucuoVO selectVO(@Param("ew") Wrapper<ThresholdEntity> wrapper);
	
	List<ThresholdView> selectListView(@Param("ew") Wrapper<ThresholdEntity> wrapper);

	List<ThresholdView> selectListView(Pagination page, @Param("ew") Wrapper<ThresholdEntity> wrapper);
	
	ThresholdView selectView(@Param("ew") Wrapper<ThresholdEntity> wrapper);
	

}
