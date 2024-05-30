package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.BookEntity;
import com.entity.BookUserEntity;

import java.util.List;
import java.util.Map;

public interface BookUserService  extends IService<BookUserEntity> {
	List<String> getOption(Map<String, Object> params);
	
	Map<String, Object> getFollowByOption(Map<String, Object> params);
	
	void sh(Map<String, Object> params);
	
	int remindCount(Map<String, Object> params);
	
//	Integer selectCount(Wrapper<BookUserEntity> params);
	
	List<Map<String, Object>> selectGroup(Map<String, Object> params);
	
	List<Map<String, Object>> selectValue(Map<String, Object> params);

	List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params);
}
