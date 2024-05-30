package com.dao;

import com.entity.BookBooklbEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YingjiwuziVO;
import com.entity.view.BookBooklbView;


/**
 * 应急物资
 * 
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface YingjiwuziDao extends BaseMapper<BookBooklbEntity> {
	
	List<YingjiwuziVO> selectListVO(@Param("ew") Wrapper<BookBooklbEntity> wrapper);
	
	YingjiwuziVO selectVO(@Param("ew") Wrapper<BookBooklbEntity> wrapper);
	
	List<BookBooklbView> selectListView(@Param("ew") Wrapper<BookBooklbEntity> wrapper);

	List<BookBooklbView> selectListView(Pagination page, @Param("ew") Wrapper<BookBooklbEntity> wrapper);
	
	BookBooklbView selectView(@Param("ew") Wrapper<BookBooklbEntity> wrapper);
	

}
