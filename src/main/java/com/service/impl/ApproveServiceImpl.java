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
import com.entity.ApproveEntity;
import com.service.ApproveService;
import com.entity.vo.DiquVO;
import com.entity.view.ApproveView;

@Service("diquService")
public class ApproveServiceImpl extends ServiceImpl<DiquDao, ApproveEntity> implements ApproveService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ApproveEntity> page = this.selectPage(
                new Query<ApproveEntity>(params).getPage(),
                new EntityWrapper<ApproveEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ApproveEntity> wrapper) {
		  Page<ApproveView> page =new Query<ApproveView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DiquVO> selectListVO(Wrapper<ApproveEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiquVO selectVO(Wrapper<ApproveEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ApproveView> selectListView(Wrapper<ApproveEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ApproveView selectView(Wrapper<ApproveEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
