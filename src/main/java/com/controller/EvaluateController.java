package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.UserEntity;
import com.entity.view.EvaluateView;
import com.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.EvaluateEntity;
import com.entity.EIException;
import com.service.EvaluateService;
import com.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * 上传文件映射表
 */
@RestController
@RequestMapping("home")
@SuppressWarnings({"unchecked","rawtypes"})
public class EvaluateController {
	@Autowired
    private EvaluateService evaluateService;

	@Autowired
	private UserService userService;
	/**
	 * 上传文件
	 */
	@RequestMapping("/plload")
	public R upload(@RequestParam("id") Integer id) throws Exception {
		List<EvaluateView> list = new ArrayList<>();
		List<EvaluateEntity> bid = evaluateService.selectList(new EntityWrapper<EvaluateEntity>().eq("bid", id));
		bid.forEach(item -> {
			EvaluateView evaluateView = new EvaluateView();
			BeanUtils.copyProperties(item,evaluateView);
			UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("id", item.getUid()));
			evaluateView.setNickname(user.getNickname());
			evaluateView.setPic(user.getPic());
			list.add(evaluateView);
		});
		return R.ok().put("data", list);
	}
	
	/**
	 * 下载文件
	 */
	@IgnoreAuth
	@RequestMapping("/pinglun")
	public R download(@RequestBody EvaluateEntity entity) {
		evaluateService.insert(entity);
		List<EvaluateView> list = new ArrayList<>();
		List<EvaluateEntity> bid = evaluateService.selectList(new EntityWrapper<EvaluateEntity>().eq("bid", entity.getBid()));
		bid.forEach(item -> {
			EvaluateView evaluateView = new EvaluateView();
			BeanUtils.copyProperties(item,evaluateView);
			UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("id", item.getUid()));
			evaluateView.setNickname(user.getNickname());
			evaluateView.setPic(user.getPic());
			list.add(evaluateView);
		});
		return R.ok().put("data", list);
	}
	
}
