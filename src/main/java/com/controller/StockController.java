package com.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.plugins.Page;
import com.entity.ThresholdEntity;
import com.service.ThresholdService;
import org.springframework.beans.BeanUtils;
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

import com.entity.StockEntity;
import com.entity.view.StockView;

import com.service.StockService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;

/**
 * 物资入库
 * 后端接口
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private ThresholdService thresholdService;
    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, StockEntity wuziruku,
                  HttpServletRequest request){
        EntityWrapper<StockEntity> ew = new EntityWrapper<StockEntity>();
		PageUtils page = stockService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wuziruku), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, StockEntity wuziruku,
                  HttpServletRequest request){
        EntityWrapper<StockEntity> ew = new EntityWrapper<StockEntity>();
		PageUtils page = stockService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wuziruku), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( StockEntity wuziruku){
       	EntityWrapper<StockEntity> ew = new EntityWrapper<StockEntity>();
      	ew.allEq(MPUtil.allEQMapPre( wuziruku, "wuziruku")); 
        return R.ok().put("data", stockService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(@RequestBody StockView stockView){
        StockEntity stock = new StockEntity();
        BeanUtils.copyProperties(stockView, stock);
        EntityWrapper<StockEntity> ew = new EntityWrapper<StockEntity>();
        ew.allEq(MPUtil.allEQMapPre( stock, "stock"));
        Page<StockEntity> page = new Page<>(stockView.getPage(),10);
        Page<StockEntity> result = stockService.selectPage(page, ew);
        return R.ok("查询物资入库成功").put("data", result);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        StockEntity wuziruku = stockService.selectById(id);
        return R.ok().put("data", wuziruku);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        StockEntity wuziruku = stockService.selectById(id);
        return R.ok().put("data", wuziruku);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody StockEntity wuziruku, HttpServletRequest request){
        stockService.insert(wuziruku);
        ThresholdEntity thresholdEntity = new ThresholdEntity();
        thresholdEntity.setAddress(wuziruku.getAddress());
        thresholdEntity.setRole(1);
        thresholdEntity.setRelationId(wuziruku.getId());
        thresholdService.insert(thresholdEntity);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody StockEntity wuziruku, HttpServletRequest request){
    	wuziruku.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(wuziruku);
        stockService.insert(wuziruku);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody StockEntity wuziruku, HttpServletRequest request){
        //ValidatorUtils.validateEntity(wuziruku);
        stockService.updateById(wuziruku);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody StockView view){
        stockService.deleteById(view.getId());
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
		
		Wrapper<StockEntity> wrapper = new EntityWrapper<StockEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = stockService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}
