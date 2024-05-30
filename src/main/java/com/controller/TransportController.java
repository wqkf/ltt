package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entity.ThresholdEntity;
import com.entity.TransportEntity;
import com.entity.BookActorEntity;
import com.entity.view.TransportView;
import com.service.ThresholdService;
import com.service.TransportService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 物资类型
 * 后端接口
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@RestController
@RequestMapping("/transport")
public class TransportController {
    @Autowired
    private TransportService transportService;


    @Autowired
    private ThresholdService thresholdService;


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, BookActorEntity wuzileixing,
                  HttpServletRequest request){
        EntityWrapper<BookActorEntity> ew = new EntityWrapper<BookActorEntity>();
		PageUtils page = transportService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wuzileixing), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, BookActorEntity wuzileixing,
                  HttpServletRequest request){
        EntityWrapper<BookActorEntity> ew = new EntityWrapper<BookActorEntity>();
		PageUtils page = transportService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, wuzileixing), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BookActorEntity wuzileixing){
       	EntityWrapper<BookActorEntity> ew = new EntityWrapper<BookActorEntity>();
      	ew.allEq(MPUtil.allEQMapPre( wuzileixing, "wuzileixing")); 
        return R.ok().put("data", transportService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(@RequestBody TransportView stockView){
        TransportEntity stock = new TransportEntity();
        BeanUtils.copyProperties(stockView, stock);
        EntityWrapper<TransportEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre( stock, "transport"));
        Page<TransportEntity> page = new Page<>(stockView.getPage(),10);
        Page<TransportEntity> result = transportService.selectPage(page, ew);
        return R.ok("查询物资入库成功").put("data", result);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TransportEntity wuziruku, HttpServletRequest request){
        transportService.insert(wuziruku);
        ThresholdEntity thresholdEntity = new ThresholdEntity();
        thresholdEntity.setAddress(wuziruku.getAddress());
        thresholdEntity.setRole(2);
        thresholdEntity.setRelationId(wuziruku.getId());
        thresholdService.insert(thresholdEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody TransportView view){
        transportService.deleteById(view.getId());
        EntityWrapper<ThresholdEntity> ew = new EntityWrapper<>();
        ew.eq("relation_id", view.getId());
        thresholdService.delete(ew);
        return R.ok();
    }



}
