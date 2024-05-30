package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BookBooklbEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YingjiwuziVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BookBooklbView;


/**
 * 应急物资
 *
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface BookBooklbService extends IService<BookBooklbEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YingjiwuziVO> selectListVO(Wrapper<BookBooklbEntity> wrapper);
   	
   	YingjiwuziVO selectVO(@Param("ew") Wrapper<BookBooklbEntity> wrapper);
   	
   	List<BookBooklbView> selectListView(Wrapper<BookBooklbEntity> wrapper);
   	
   	BookBooklbView selectView(@Param("ew") Wrapper<BookBooklbEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BookBooklbEntity> wrapper);
   	

}

