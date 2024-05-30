package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.WuzichukuDao;
import com.entity.BookEntity;
import com.service.BookService;
import com.entity.vo.WuzichukuVO;
import com.entity.view.BookView;

@Service("wuzichukuService")
public class BookServiceImpl extends ServiceImpl<WuzichukuDao, BookEntity> implements BookService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BookEntity> page = this.selectPage(
                new Query<BookEntity>(params).getPage(),
                new EntityWrapper<BookEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BookEntity> wrapper) {
		  Page<BookView> page =new Query<BookView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WuzichukuVO> selectListVO(Wrapper<BookEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WuzichukuVO selectVO(Wrapper<BookEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BookView> selectListView(Wrapper<BookEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BookView selectView(Wrapper<BookEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
