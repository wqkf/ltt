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


import com.dao.YingjiwuziDao;
import com.entity.BookBooklbEntity;
import com.service.BookBooklbService;
import com.entity.vo.YingjiwuziVO;
import com.entity.view.BookBooklbView;

@Service("yingjiwuziService")
public class BookBooklbServiceImpl extends ServiceImpl<YingjiwuziDao, BookBooklbEntity> implements BookBooklbService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BookBooklbEntity> page = this.selectPage(
                new Query<BookBooklbEntity>(params).getPage(),
                new EntityWrapper<BookBooklbEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BookBooklbEntity> wrapper) {
		  Page<BookBooklbView> page =new Query<BookBooklbView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<YingjiwuziVO> selectListVO(Wrapper<BookBooklbEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YingjiwuziVO selectVO(Wrapper<BookBooklbEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BookBooklbView> selectListView(Wrapper<BookBooklbEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BookBooklbView selectView(Wrapper<BookBooklbEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
