package com.service.impl;

import com.dao.TransportDao;
import com.entity.TransportEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.entity.BookActorEntity;
import com.service.TransportService;
import com.entity.vo.WuzileixingVO;
import com.entity.view.BookActorView;

@Service("wuzileixingService")
public class TransportServiceImpl extends ServiceImpl<TransportDao, TransportEntity> implements TransportService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        return null;
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BookActorEntity> wrapper) {
		  Page<BookActorView> page =new Query<BookActorView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WuzileixingVO> selectListVO(Wrapper<BookActorEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WuzileixingVO selectVO(Wrapper<BookActorEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BookActorView> selectListView(Wrapper<BookActorEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BookActorView selectView(Wrapper<BookActorEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
