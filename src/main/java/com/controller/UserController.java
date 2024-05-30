
package com.controller;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.entity.BookUserEntity;
import com.entity.YonghuEntity;
import com.entity.view.UserView;
import com.service.BookUserService;
import com.service.YonghuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.UserEntity;
import com.service.UserService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 登录相关
 */
@RequestMapping("users")
@RestController
public class UserController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private YonghuService yonghuService;

	@Autowired
	private BookUserService bookUserService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@PostMapping(value = "/login")
	public R login(@RequestBody UserEntity user1, HttpServletRequest request) {
		UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("nickname", user1.getNickname()));
		if(user==null || !user.getPassword().equals(user1.getPassword())) {
			return R.error("账号或密码不正确");
		}
		request.getSession().setAttribute("userId", user.getId());
		return R.ok().put("data",user);
	}
	
	/**
	 * 注册
	 */
	@IgnoreAuth
	@PostMapping(value = "/register")
	public R register(@RequestBody UserEntity user){
    	if(userService.selectOne(new EntityWrapper<UserEntity>().eq("nickname", user.getNickname())) !=null) {
    		return R.error("用户已存在");
    	}
        userService.insert(user);
        return R.ok();
    }

	/**
	 * 退出
	 */
	@GetMapping(value = "adminload")
	public R logout(@RequestParam String nickname) {
		Date date = new Date();
		UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("nickname", nickname));
		UserView userView = new UserView();
		Integer age = user.getAge();
		Integer limit = 0;
		if(age >= 18){
			limit = 3;
		} else {
			limit = 5;
		}
		EntityWrapper<BookUserEntity> ew = new EntityWrapper<>();
		ew.eq("userid", user.getId());
		// 当前日期
		LocalDate currentDate = LocalDate.now();
		// 当月第一天
		LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
		// 当月最后一天
		LocalDate lastDayOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
		ew.between("borrow_date", firstDayOfMonth, lastDayOfMonth);
		int num = bookUserService.selectCount(ew);
		if (user.getIfvip() == 1) {
			Date deadline = user.getDeadline();
			if (date.after(deadline)) {
				user.setIfvip(0);
				userService.updateById(user);
				BeanUtils.copyProperties(user, userView);
				if (limit-num < 0){
					userView.setSurplus(0);
				}else {
					userView.setSurplus(limit - num);
				}
			} else{
				BeanUtils.copyProperties(user, userView);
			}

		} else {
			BeanUtils.copyProperties(user, userView);
			if (limit-num < 0){
				userView.setSurplus(0);
			}else {
				userView.setSurplus(limit - num);
			}
		}

		return R.ok().put("data", userView);
	}
	
	/**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", username));
    	if(user==null) {
    		return R.error("账号不存在");
    	}
    	user.setPassword("123456");
        userService.update(user,null);
        return R.ok("密码已重置为：123456");
    }
	
	/**
     * 列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,UserEntity user){
        EntityWrapper<UserEntity> ew = new EntityWrapper<UserEntity>();
    	PageUtils page = userService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, user), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/list")
    public R list( UserEntity user){
       	EntityWrapper<UserEntity> ew = new EntityWrapper<UserEntity>();
      	ew.allEq(MPUtil.allEQMapPre( user, "user")); 
        return R.ok().put("data", userService.selectListView(ew));
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        UserEntity user = userService.selectById(id);
        return R.ok().put("data", user);
    }
    
    /**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        UserEntity user = userService.selectById(id);
        return R.ok().put("data", user);
    }

//    /**
//     * 保存
//     */
//    @PostMapping("/save")
//    public R save(@RequestBody UserEntity user){
////    	ValidatorUtils.validateEntity(user);
//    	if(userService.selectOne(new EntityWrapper<UserEntity>().eq("username", user.getUsername())) !=null) {
//    		return R.error("用户已存在");
//    	}
//        userService.insert(user);
//        return R.ok();
//    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestParam String nickname){
    	UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("nickname", nickname));
		LocalDate today = LocalDate.now(); // 获取今天的日期
		LocalDate sixMonthsLater = today.plusMonths(6); // 在今天的日期上加6个月
    	user.setDeadline(Date.from(sixMonthsLater.atTime(LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant()));
		user.setIfvip(1);
        userService.updateById(user);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        userService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
