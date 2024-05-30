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


import com.dao.ActorDao;
import com.entity.ActorEntity;
import com.service.ActorService;
import com.entity.vo.WuzixuqiuVO;
import com.entity.view.ActorView;

@Service("wuzixuqiuService")
public class ActorServiceImpl extends ServiceImpl<ActorDao, ActorEntity> implements ActorService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ActorEntity> page = this.selectPage(
                new Query<ActorEntity>(params).getPage(),
                new EntityWrapper<ActorEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ActorEntity> wrapper) {
		  Page<ActorView> page =new Query<ActorView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WuzixuqiuVO> selectListVO(Wrapper<ActorEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WuzixuqiuVO selectVO(Wrapper<ActorEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ActorView> selectListView(Wrapper<ActorEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ActorView selectView(Wrapper<ActorEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
