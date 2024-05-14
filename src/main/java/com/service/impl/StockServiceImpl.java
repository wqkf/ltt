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


import com.dao.WuzirukuDao;
import com.entity.StockEntity;
import com.service.StockService;
import com.entity.vo.WuzirukuVO;
import com.entity.view.StockView;

@Service("wuzirukuService")
public class StockServiceImpl extends ServiceImpl<WuzirukuDao, StockEntity> implements StockService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StockEntity> page = this.selectPage(
                new Query<StockEntity>(params).getPage(),
                new EntityWrapper<StockEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<StockEntity> wrapper) {
		  Page<StockView> page =new Query<StockView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WuzirukuVO> selectListVO(Wrapper<StockEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WuzirukuVO selectVO(Wrapper<StockEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<StockView> selectListView(Wrapper<StockEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public StockView selectView(Wrapper<StockEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
