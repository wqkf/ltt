package com.dao;

import com.entity.StockEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WuzirukuVO;
import com.entity.view.StockView;


/**
 * 物资入库
 * 
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface WuzirukuDao extends BaseMapper<StockEntity> {
	
	List<WuzirukuVO> selectListVO(@Param("ew") Wrapper<StockEntity> wrapper);
	
	WuzirukuVO selectVO(@Param("ew") Wrapper<StockEntity> wrapper);
	
	List<StockView> selectListView(@Param("ew") Wrapper<StockEntity> wrapper);

	List<StockView> selectListView(Pagination page, @Param("ew") Wrapper<StockEntity> wrapper);
	
	StockView selectView(@Param("ew") Wrapper<StockEntity> wrapper);
	

}
