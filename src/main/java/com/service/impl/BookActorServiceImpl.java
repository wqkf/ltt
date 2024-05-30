package com.service.impl;

import com.entity.BookActorEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.BookActorDao;
import com.entity.RiskRoadEntity;
import com.service.BookActorService;
import com.entity.vo.WuzijuanzengVO;
import com.entity.view.EvaluateView;

@Service("wuzijuanzengService")
public class BookActorServiceImpl extends ServiceImpl<BookActorDao, BookActorEntity> implements BookActorService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BookActorEntity> page = this.selectPage(
                new Query<BookActorEntity>(params).getPage(),
                new EntityWrapper<>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<RiskRoadEntity> wrapper) {
		  Page<EvaluateView> page =new Query<EvaluateView>(params).getPage();
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
	public List<EvaluateView> selectListView(Wrapper<RiskRoadEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public EvaluateView selectView(Wrapper<RiskRoadEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
