package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entity.ThresholdEntity;
import com.entity.YonghuEntity;
import com.entity.view.ThresholdView;
import com.service.ThresholdService;
import com.service.YonghuService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 物资筹措
 * 后端接口
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@RestController
@RequestMapping("/threshold")
public class ThresholdController {
    @Autowired
    private ThresholdService thresholdService;


    @Autowired
    private YonghuService yonghuService;


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, ThresholdEntity wuzichoucuo,
                  HttpServletRequest request){
        EntityWrapper<ThresholdEntity> ew = new EntityWrapper<ThresholdEntity>();
		PageUtils page = thresholdService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wuzichoucuo), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(){
        EntityWrapper<ThresholdEntity> ew = new EntityWrapper<ThresholdEntity>();
        ThresholdEntity thresholdEntity = thresholdService.selectOne(ew);
        return R.ok().put("data", thresholdEntity);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( @RequestBody ThresholdEntity wuzichoucuo){
       	EntityWrapper<ThresholdEntity> ew = new EntityWrapper<ThresholdEntity>();
      	ew.allEq(MPUtil.allEQMapPre( wuzichoucuo, "wuzichoucuo")); 
        return R.ok().put("data", thresholdService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(@RequestBody ThresholdView stockView, HttpServletRequest request){
        if(stockView.getRole() == 3) {
            Long userId = (Long)request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity != null && yonghuEntity.getRole() == 4) {

            } else {
                stockView.setRelationId(userId);
            }
        }
        ThresholdEntity stock = new ThresholdEntity();
        BeanUtils.copyProperties(stockView, stock);
        EntityWrapper<ThresholdEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre( stock, "threshold"));
        Page<ThresholdEntity> page = new Page<>(stockView.getPage(),10);
        Page<ThresholdEntity> result = thresholdService.selectPage(page, ew);
        return R.ok("查询物资入库成功").put("data", result);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ThresholdEntity wuzichoucuo = thresholdService.selectById(id);
        return R.ok().put("data", wuzichoucuo);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ThresholdEntity wuzichoucuo = thresholdService.selectById(id);
        return R.ok().put("data", wuzichoucuo);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ThresholdEntity threshold, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(threshold);
        if(threshold.getId()!=null){
            thresholdService.updateById(threshold);
        }else {
            thresholdService.insert(threshold);
        }
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ThresholdEntity wuzichoucuo, HttpServletRequest request){
    	wuzichoucuo.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(wuzichoucuo);
        thresholdService.insert(wuzichoucuo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ThresholdEntity wuzichoucuo, HttpServletRequest request){
        thresholdService.updateById(wuzichoucuo);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        thresholdService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<ThresholdEntity> wrapper = new EntityWrapper<ThresholdEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = thresholdService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}
