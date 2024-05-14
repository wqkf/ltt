package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.RiskRoadEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WuzijuanzengVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.RiskRoadView;


/**
 * 物资捐赠
 *
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface WuzijuanzengService extends IService<RiskRoadEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WuzijuanzengVO> selectListVO(Wrapper<RiskRoadEntity> wrapper);
   	
   	WuzijuanzengVO selectVO(@Param("ew") Wrapper<RiskRoadEntity> wrapper);
   	
   	List<RiskRoadView> selectListView(Wrapper<RiskRoadEntity> wrapper);
   	
   	RiskRoadView selectView(@Param("ew") Wrapper<RiskRoadEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<RiskRoadEntity> wrapper);
   	

}

