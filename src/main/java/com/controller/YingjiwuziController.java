package com.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.YingjiwuziEntity;
import com.entity.view.YingjiwuziView;

import com.service.YingjiwuziService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;

/**
 * 应急物资
 * 后端接口
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@RestController
@RequestMapping("/yingjiwuzi")
public class YingjiwuziController {
    @Autowired
    private YingjiwuziService yingjiwuziService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YingjiwuziEntity yingjiwuzi,
		HttpServletRequest request){
        EntityWrapper<YingjiwuziEntity> ew = new EntityWrapper<YingjiwuziEntity>();
		PageUtils page = yingjiwuziService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yingjiwuzi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YingjiwuziEntity yingjiwuzi, 
		HttpServletRequest request){
        EntityWrapper<YingjiwuziEntity> ew = new EntityWrapper<YingjiwuziEntity>();
		PageUtils page = yingjiwuziService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yingjiwuzi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YingjiwuziEntity yingjiwuzi){
       	EntityWrapper<YingjiwuziEntity> ew = new EntityWrapper<YingjiwuziEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yingjiwuzi, "yingjiwuzi")); 
        return R.ok().put("data", yingjiwuziService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YingjiwuziEntity yingjiwuzi){
        EntityWrapper< YingjiwuziEntity> ew = new EntityWrapper< YingjiwuziEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yingjiwuzi, "yingjiwuzi")); 
		YingjiwuziView yingjiwuziView =  yingjiwuziService.selectView(ew);
		return R.ok("查询应急物资成功").put("data", yingjiwuziView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YingjiwuziEntity yingjiwuzi = yingjiwuziService.selectById(id);
        return R.ok().put("data", yingjiwuzi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YingjiwuziEntity yingjiwuzi = yingjiwuziService.selectById(id);
        return R.ok().put("data", yingjiwuzi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YingjiwuziEntity yingjiwuzi, HttpServletRequest request){
    	yingjiwuzi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yingjiwuzi);
        yingjiwuziService.insert(yingjiwuzi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YingjiwuziEntity yingjiwuzi, HttpServletRequest request){
    	yingjiwuzi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yingjiwuzi);
        yingjiwuziService.insert(yingjiwuzi);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YingjiwuziEntity yingjiwuzi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yingjiwuzi);
        yingjiwuziService.updateById(yingjiwuzi);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yingjiwuziService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<YingjiwuziEntity> wrapper = new EntityWrapper<YingjiwuziEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = yingjiwuziService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}
