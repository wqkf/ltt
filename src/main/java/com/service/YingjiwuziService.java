package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YingjiwuziEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YingjiwuziVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YingjiwuziView;


/**
 * 应急物资
 *
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface YingjiwuziService extends IService<YingjiwuziEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YingjiwuziVO> selectListVO(Wrapper<YingjiwuziEntity> wrapper);
   	
   	YingjiwuziVO selectVO(@Param("ew") Wrapper<YingjiwuziEntity> wrapper);
   	
   	List<YingjiwuziView> selectListView(Wrapper<YingjiwuziEntity> wrapper);
   	
   	YingjiwuziView selectView(@Param("ew") Wrapper<YingjiwuziEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YingjiwuziEntity> wrapper);
   	

}

