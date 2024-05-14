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


import com.dao.WuzichoucuoDao;
import com.entity.ThresholdEntity;
import com.service.ThresholdService;
import com.entity.vo.WuzichoucuoVO;
import com.entity.view.ThresholdView;

@Service("wuzichoucuoService")
public class ThresholdServiceImpl extends ServiceImpl<WuzichoucuoDao, ThresholdEntity> implements ThresholdService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ThresholdEntity> page = this.selectPage(
                new Query<ThresholdEntity>(params).getPage(),
                new EntityWrapper<ThresholdEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ThresholdEntity> wrapper) {
		  Page<ThresholdView> page =new Query<ThresholdView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WuzichoucuoVO> selectListVO(Wrapper<ThresholdEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WuzichoucuoVO selectVO(Wrapper<ThresholdEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ThresholdView> selectListView(Wrapper<ThresholdEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ThresholdView selectView(Wrapper<ThresholdEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
