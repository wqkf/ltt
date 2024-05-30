package com.dao;

import com.entity.BookEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WuzichukuVO;
import com.entity.view.BookView;


/**
 * 物资出库
 * 
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface WuzichukuDao extends BaseMapper<BookEntity> {
	
	List<WuzichukuVO> selectListVO(@Param("ew") Wrapper<BookEntity> wrapper);
	
	WuzichukuVO selectVO(@Param("ew") Wrapper<BookEntity> wrapper);
	
	List<BookView> selectListView(@Param("ew") Wrapper<BookEntity> wrapper);

	List<BookView> selectListView(Pagination page, @Param("ew") Wrapper<BookEntity> wrapper);
	
	BookView selectView(@Param("ew") Wrapper<BookEntity> wrapper);
	

}
