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


import com.dao.YingjiwuziDao;
import com.entity.YingjiwuziEntity;
import com.service.YingjiwuziService;
import com.entity.vo.YingjiwuziVO;
import com.entity.view.YingjiwuziView;

@Service("yingjiwuziService")
public class YingjiwuziServiceImpl extends ServiceImpl<YingjiwuziDao, YingjiwuziEntity> implements YingjiwuziService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YingjiwuziEntity> page = this.selectPage(
                new Query<YingjiwuziEntity>(params).getPage(),
                new EntityWrapper<YingjiwuziEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YingjiwuziEntity> wrapper) {
		  Page<YingjiwuziView> page =new Query<YingjiwuziView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<YingjiwuziVO> selectListVO(Wrapper<YingjiwuziEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YingjiwuziVO selectVO(Wrapper<YingjiwuziEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YingjiwuziView> selectListView(Wrapper<YingjiwuziEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YingjiwuziView selectView(Wrapper<YingjiwuziEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
