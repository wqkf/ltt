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

import com.entity.RiskRoadEntity;
import com.entity.view.RiskRoadView;

import com.service.WuzijuanzengService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;

/**
 * 物资捐赠
 * 后端接口
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@RestController
@RequestMapping("/wuzijuanzeng")
public class WuzijuanzengController {
    @Autowired
    private WuzijuanzengService wuzijuanzengService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, RiskRoadEntity riskRoad,
                  HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
//		if(tableName.equals("yonghu")) {
//			riskRoad.set((String)request.getSession().getAttribute("username"));
//		}
        EntityWrapper<RiskRoadEntity> ew = new EntityWrapper<RiskRoadEntity>();
		PageUtils page = wuzijuanzengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, riskRoad), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, RiskRoadEntity wuzijuanzeng,
                  HttpServletRequest request){
        EntityWrapper<RiskRoadEntity> ew = new EntityWrapper<RiskRoadEntity>();
		PageUtils page = wuzijuanzengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wuzijuanzeng), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( RiskRoadEntity wuzijuanzeng){
       	EntityWrapper<RiskRoadEntity> ew = new EntityWrapper<RiskRoadEntity>();
      	ew.allEq(MPUtil.allEQMapPre( wuzijuanzeng, "wuzijuanzeng")); 
        return R.ok().put("data", wuzijuanzengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(RiskRoadEntity wuzijuanzeng){
        EntityWrapper<RiskRoadEntity> ew = new EntityWrapper<RiskRoadEntity>();
 		ew.allEq(MPUtil.allEQMapPre( wuzijuanzeng, "wuzijuanzeng")); 
		RiskRoadView wuzijuanzengView =  wuzijuanzengService.selectView(ew);
		return R.ok("查询物资捐赠成功").put("data", wuzijuanzengView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RiskRoadEntity wuzijuanzeng = wuzijuanzengService.selectById(id);
        return R.ok().put("data", wuzijuanzeng);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RiskRoadEntity wuzijuanzeng = wuzijuanzengService.selectById(id);
        return R.ok().put("data", wuzijuanzeng);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RiskRoadEntity wuzijuanzeng, HttpServletRequest request){
    	wuzijuanzeng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(wuzijuanzeng);
        wuzijuanzengService.insert(wuzijuanzeng);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody RiskRoadEntity wuzijuanzeng, HttpServletRequest request){
    	wuzijuanzeng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(wuzijuanzeng);
        wuzijuanzengService.insert(wuzijuanzeng);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody RiskRoadEntity wuzijuanzeng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wuzijuanzeng);
        wuzijuanzengService.updateById(wuzijuanzeng);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        wuzijuanzengService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<RiskRoadEntity> wrapper = new EntityWrapper<RiskRoadEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			wrapper.eq("yonghuming", (String)request.getSession().getAttribute("username"));
		}

		int count = wuzijuanzengService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}
