package com.dao;

import com.entity.TransportEntity;
import com.entity.BookActorEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WuzileixingVO;
import com.entity.view.BookActorView;


/**
 * 物资类型
 * 
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface TransportDao extends BaseMapper<TransportEntity> {
	
	List<WuzileixingVO> selectListVO(@Param("ew") Wrapper<BookActorEntity> wrapper);
	
	WuzileixingVO selectVO(@Param("ew") Wrapper<BookActorEntity> wrapper);
	
	List<BookActorView> selectListView(@Param("ew") Wrapper<BookActorEntity> wrapper);

	List<BookActorView> selectListView(Pagination page, @Param("ew") Wrapper<BookActorEntity> wrapper);
	
	BookActorView selectView(@Param("ew") Wrapper<BookActorEntity> wrapper);
	

}
