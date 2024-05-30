
package com.service.impl;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entity.BookUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookUserDao;
import com.service.BookUserService;


/**
 * 系统用户
 */
@Service("commonService")
public class BookUserServiceImpl extends ServiceImpl<BookUserDao, BookUserEntity> implements BookUserService {
	
	@Autowired
	private BookUserDao bookUserDao;

	@Override
	public List<String> getOption(Map<String, Object> params) {
		return bookUserDao.getOption(params);
	}
	
	@Override
	public Map<String, Object> getFollowByOption(Map<String, Object> params) {
		return bookUserDao.getFollowByOption(params);
	}
	
	@Override
	public void sh(Map<String, Object> params) {
		bookUserDao.sh(params);
	}

	@Override
	public int remindCount(Map<String, Object> params) {
		return bookUserDao.remindCount(params);
	}

//	@Override
//	public Integer selectCount(Wrapper<BookUserEntity> params) {
//		return bookUserDao.selectCount(params);
//	}
	
	@Override
	public List<Map<String, Object>> selectGroup(Map<String, Object> params) {
		return bookUserDao.selectGroup(params);
	}
	
	@Override
	public List<Map<String, Object>> selectValue(Map<String, Object> params) {
		return bookUserDao.selectValue(params);
	}

	@Override
	public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params) {
		return bookUserDao.selectTimeStatValue(params);
	}

}
