package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.StockEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WuzirukuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.StockView;


/**
 * 物资入库
 *
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface StockService extends IService<StockEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WuzirukuVO> selectListVO(Wrapper<StockEntity> wrapper);
   	
   	WuzirukuVO selectVO(@Param("ew") Wrapper<StockEntity> wrapper);
   	
   	List<StockView> selectListView(Wrapper<StockEntity> wrapper);
   	
   	StockView selectView(@Param("ew") Wrapper<StockEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<StockEntity> wrapper);
   	

}

