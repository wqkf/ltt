package com.dao;

import com.entity.YingjiwuziEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YingjiwuziVO;
import com.entity.view.YingjiwuziView;


/**
 * 应急物资
 * 
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface YingjiwuziDao extends BaseMapper<YingjiwuziEntity> {
	
	List<YingjiwuziVO> selectListVO(@Param("ew") Wrapper<YingjiwuziEntity> wrapper);
	
	YingjiwuziVO selectVO(@Param("ew") Wrapper<YingjiwuziEntity> wrapper);
	
	List<YingjiwuziView> selectListView(@Param("ew") Wrapper<YingjiwuziEntity> wrapper);

	List<YingjiwuziView> selectListView(Pagination page,@Param("ew") Wrapper<YingjiwuziEntity> wrapper);
	
	YingjiwuziView selectView(@Param("ew") Wrapper<YingjiwuziEntity> wrapper);
	

}
