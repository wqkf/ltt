package com.dao;

import com.entity.BooklbEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiquVO;
import com.entity.view.BooklbView;


/**
 * 地区
 * 
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface DiquDao extends BaseMapper<BooklbEntity> {
	
	List<DiquVO> selectListVO(@Param("ew") Wrapper<BooklbEntity> wrapper);
	
	DiquVO selectVO(@Param("ew") Wrapper<BooklbEntity> wrapper);
	
	List<BooklbView> selectListView(@Param("ew") Wrapper<BooklbEntity> wrapper);

	List<BooklbView> selectListView(Pagination page, @Param("ew") Wrapper<BooklbEntity> wrapper);
	
	BooklbView selectView(@Param("ew") Wrapper<BooklbEntity> wrapper);
	

}
