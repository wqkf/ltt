package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BookEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WuzichukuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BookView;


/**
 * 物资出库
 *
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public interface BookService extends IService<BookEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WuzichukuVO> selectListVO(Wrapper<BookEntity> wrapper);
   	
   	WuzichukuVO selectVO(@Param("ew") Wrapper<BookEntity> wrapper);
   	
   	List<BookView> selectListView(Wrapper<BookEntity> wrapper);
   	
   	BookView selectView(@Param("ew") Wrapper<BookEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BookEntity> wrapper);
   	

}

