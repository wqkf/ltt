package com.dao;

import com.entity.WuzixuqiuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WuzixuqiuVO;
import com.entity.view.WuzixuqiuView;


/**
 * 物资需求
 * 
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface WuzixuqiuDao extends BaseMapper<WuzixuqiuEntity> {
	
	List<WuzixuqiuVO> selectListVO(@Param("ew") Wrapper<WuzixuqiuEntity> wrapper);
	
	WuzixuqiuVO selectVO(@Param("ew") Wrapper<WuzixuqiuEntity> wrapper);
	
	List<WuzixuqiuView> selectListView(@Param("ew") Wrapper<WuzixuqiuEntity> wrapper);

	List<WuzixuqiuView> selectListView(Pagination page,@Param("ew") Wrapper<WuzixuqiuEntity> wrapper);
	
	WuzixuqiuView selectView(@Param("ew") Wrapper<WuzixuqiuEntity> wrapper);
	

}
