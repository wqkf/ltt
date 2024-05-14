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


import com.dao.WuzileixingDao;
import com.entity.WuzileixingEntity;
import com.service.WuzileixingService;
import com.entity.vo.WuzileixingVO;
import com.entity.view.WuzileixingView;

@Service("wuzileixingService")
public class WuzileixingServiceImpl extends ServiceImpl<WuzileixingDao, WuzileixingEntity> implements WuzileixingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WuzileixingEntity> page = this.selectPage(
                new Query<WuzileixingEntity>(params).getPage(),
                new EntityWrapper<WuzileixingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<WuzileixingEntity> wrapper) {
		  Page<WuzileixingView> page =new Query<WuzileixingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WuzileixingVO> selectListVO(Wrapper<WuzileixingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WuzileixingVO selectVO(Wrapper<WuzileixingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<WuzileixingView> selectListView(Wrapper<WuzileixingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public WuzileixingView selectView(Wrapper<WuzileixingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
