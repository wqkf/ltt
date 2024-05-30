package com.dao;

import com.entity.ActorEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WuzixuqiuVO;
import com.entity.view.ActorView;


/**
 * 物资需求
 * 
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface ActorDao extends BaseMapper<ActorEntity> {
	
	List<WuzixuqiuVO> selectListVO(@Param("ew") Wrapper<ActorEntity> wrapper);
	
	WuzixuqiuVO selectVO(@Param("ew") Wrapper<ActorEntity> wrapper);
	
	List<ActorView> selectListView(@Param("ew") Wrapper<ActorEntity> wrapper);

	List<ActorView> selectListView(Pagination page, @Param("ew") Wrapper<ActorEntity> wrapper);
	
	ActorView selectView(@Param("ew") Wrapper<ActorEntity> wrapper);
	

}
