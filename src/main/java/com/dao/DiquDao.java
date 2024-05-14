package com.dao;

import com.entity.ApproveEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiquVO;
import com.entity.view.ApproveView;


/**
 * 地区
 * 
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface DiquDao extends BaseMapper<ApproveEntity> {
	
	List<DiquVO> selectListVO(@Param("ew") Wrapper<ApproveEntity> wrapper);
	
	DiquVO selectVO(@Param("ew") Wrapper<ApproveEntity> wrapper);
	
	List<ApproveView> selectListView(@Param("ew") Wrapper<ApproveEntity> wrapper);

	List<ApproveView> selectListView(Pagination page, @Param("ew") Wrapper<ApproveEntity> wrapper);
	
	ApproveView selectView(@Param("ew") Wrapper<ApproveEntity> wrapper);
	

}
