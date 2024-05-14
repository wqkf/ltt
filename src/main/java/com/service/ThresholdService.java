package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ThresholdEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WuzichoucuoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ThresholdView;


/**
 * 物资筹措
 *
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface ThresholdService extends IService<ThresholdEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WuzichoucuoVO> selectListVO(Wrapper<ThresholdEntity> wrapper);
   	
   	WuzichoucuoVO selectVO(@Param("ew") Wrapper<ThresholdEntity> wrapper);
   	
   	List<ThresholdView> selectListView(Wrapper<ThresholdEntity> wrapper);
   	
   	ThresholdView selectView(@Param("ew") Wrapper<ThresholdEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ThresholdEntity> wrapper);
   	

}

