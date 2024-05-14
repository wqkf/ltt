package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ApproveEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiquVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ApproveView;


/**
 * 地区
 *
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface ApproveService extends IService<ApproveEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiquVO> selectListVO(Wrapper<ApproveEntity> wrapper);
   	
   	DiquVO selectVO(@Param("ew") Wrapper<ApproveEntity> wrapper);
   	
   	List<ApproveView> selectListView(Wrapper<ApproveEntity> wrapper);
   	
   	ApproveView selectView(@Param("ew") Wrapper<ApproveEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ApproveEntity> wrapper);
   	

}

