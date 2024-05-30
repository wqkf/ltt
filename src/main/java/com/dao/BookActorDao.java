package com.dao;

import com.entity.BookActorEntity;
import com.entity.RiskRoadEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WuzijuanzengVO;
import com.entity.view.EvaluateView;


/**
 * 物资捐赠
 * 
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface BookActorDao extends BaseMapper<BookActorEntity> {
	
	List<WuzijuanzengVO> selectListVO(@Param("ew") Wrapper<RiskRoadEntity> wrapper);
	
	WuzijuanzengVO selectVO(@Param("ew") Wrapper<RiskRoadEntity> wrapper);
	
	List<EvaluateView> selectListView(@Param("ew") Wrapper<RiskRoadEntity> wrapper);

	List<EvaluateView> selectListView(Pagination page, @Param("ew") Wrapper<RiskRoadEntity> wrapper);
	
	EvaluateView selectView(@Param("ew") Wrapper<RiskRoadEntity> wrapper);
	

}
