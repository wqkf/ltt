package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ActorEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WuzixuqiuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ActorView;


/**
 * 物资需求
 *
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface ActorService extends IService<ActorEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WuzixuqiuVO> selectListVO(Wrapper<ActorEntity> wrapper);
   	
   	WuzixuqiuVO selectVO(@Param("ew") Wrapper<ActorEntity> wrapper);
   	
   	List<ActorView> selectListView(Wrapper<ActorEntity> wrapper);
   	
   	ActorView selectView(@Param("ew") Wrapper<ActorEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ActorEntity> wrapper);
   	

}

