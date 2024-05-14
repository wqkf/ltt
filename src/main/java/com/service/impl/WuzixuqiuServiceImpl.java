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


import com.dao.WuzixuqiuDao;
import com.entity.WuzixuqiuEntity;
import com.service.WuzixuqiuService;
import com.entity.vo.WuzixuqiuVO;
import com.entity.view.WuzixuqiuView;

@Service("wuzixuqiuService")
public class WuzixuqiuServiceImpl extends ServiceImpl<WuzixuqiuDao, WuzixuqiuEntity> implements WuzixuqiuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WuzixuqiuEntity> page = this.selectPage(
                new Query<WuzixuqiuEntity>(params).getPage(),
                new EntityWrapper<WuzixuqiuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<WuzixuqiuEntity> wrapper) {
		  Page<WuzixuqiuView> page =new Query<WuzixuqiuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<WuzixuqiuVO> selectListVO(Wrapper<WuzixuqiuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public WuzixuqiuVO selectVO(Wrapper<WuzixuqiuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<WuzixuqiuView> selectListView(Wrapper<WuzixuqiuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public WuzixuqiuView selectView(Wrapper<WuzixuqiuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
