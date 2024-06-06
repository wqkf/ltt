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


import com.dao.DiquDao;
import com.entity.BooklbEntity;
import com.service.BooklbService;
import com.entity.vo.DiquVO;
import com.entity.view.BooklbView;

@Service("diquService")
public class BooklbServiceImpl extends ServiceImpl<DiquDao, BooklbEntity> implements BooklbService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BooklbEntity> page = this.selectPage(
                new Query<BooklbEntity>(params).getPage(),
                new EntityWrapper<BooklbEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BooklbEntity> wrapper) {
		  Page<BooklbView> page =new Query<BooklbView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DiquVO> selectListVO(Wrapper<BooklbEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiquVO selectVO(Wrapper<BooklbEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BooklbView> selectListView(Wrapper<BooklbEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BooklbView selectView(Wrapper<BooklbEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
