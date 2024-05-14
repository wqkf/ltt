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


import com.dao.WuzijuanzengDao;
import com.entity.RiskRoadEntity;
import com.service.WuzijuanzengService;
import com.entity.vo.WuzijuanzengVO;
import com.entity.view.RiskRoadView;

@Service("wuzijuanzengService")
public class WuzijuanzengServiceImpl extends ServiceImpl<WuzijuanzengDao, RiskRoadEntity> implements WuzijuanzengService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RiskRoadEntity> page = this.selectPage(
                new Query<RiskRoadEntity>(params).getPage(),
                new EntityWrapper<RiskRoadEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<RiskRoadEntity> wrapper) {
		  Page<RiskRoadView> page =new Query<RiskRoadView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WuzijuanzengVO> selectListVO(Wrapper<RiskRoadEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WuzijuanzengVO selectVO(Wrapper<RiskRoadEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<RiskRoadView> selectListView(Wrapper<RiskRoadEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public RiskRoadView selectView(Wrapper<RiskRoadEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
