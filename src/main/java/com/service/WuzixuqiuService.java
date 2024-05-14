package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.WuzixuqiuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WuzixuqiuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.WuzixuqiuView;


/**
 * 物资需求
 *
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface WuzixuqiuService extends IService<WuzixuqiuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WuzixuqiuVO> selectListVO(Wrapper<WuzixuqiuEntity> wrapper);
   	
   	WuzixuqiuVO selectVO(@Param("ew") Wrapper<WuzixuqiuEntity> wrapper);
   	
   	List<WuzixuqiuView> selectListView(Wrapper<WuzixuqiuEntity> wrapper);
   	
   	WuzixuqiuView selectView(@Param("ew") Wrapper<WuzixuqiuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WuzixuqiuEntity> wrapper);
   	

}

