package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BooklbEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiquVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BooklbView;


/**
 * 地区
 *
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface BooklbService extends IService<BooklbEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiquVO> selectListVO(Wrapper<BooklbEntity> wrapper);
   	
   	DiquVO selectVO(@Param("ew") Wrapper<BooklbEntity> wrapper);
   	
   	List<BooklbView> selectListView(Wrapper<BooklbEntity> wrapper);
   	
   	BooklbView selectView(@Param("ew") Wrapper<BooklbEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BooklbEntity> wrapper);
   	

}

