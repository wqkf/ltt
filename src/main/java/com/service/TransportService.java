package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.TransportEntity;
import com.utils.PageUtils;
import com.entity.WuzileixingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WuzileixingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.WuzileixingView;


/**
 * 物资类型
 *
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface TransportService extends IService<TransportEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WuzileixingVO> selectListVO(Wrapper<WuzileixingEntity> wrapper);
   	
   	WuzileixingVO selectVO(@Param("ew") Wrapper<WuzileixingEntity> wrapper);
   	
   	List<WuzileixingView> selectListView(Wrapper<WuzileixingEntity> wrapper);
   	
   	WuzileixingView selectView(@Param("ew") Wrapper<WuzileixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WuzileixingEntity> wrapper);
   	

}

