package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entity.RiskEntity;
import com.entity.ThresholdEntity;
import com.entity.view.RiskView;
import com.service.RiskService;
import com.service.ThresholdService;
import com.utils.MPUtil;
import com.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 供应商
 * 后端接口
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@RestController
@RequestMapping("/risk")
public class RiskController {
    @Autowired
    private RiskService riskService;

    @Autowired
    private ThresholdService thresholdService;


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestBody RiskView riskView,
                  HttpServletRequest request){
        RiskEntity stock = new RiskEntity();
        BeanUtils.copyProperties(riskView, stock);
        EntityWrapper<RiskEntity> ew = new EntityWrapper<>();
        ew.allEq(MPUtil.allEQMapPre( stock, "risk"));
        Page<RiskEntity> page = new Page<>(riskView.getPage(),10);
        Page<RiskView> page1 = new Page<>(riskView.getPage(),10);
        Page<RiskEntity> result = riskService.selectPage(page, ew);
        List<RiskEntity> records = result.getRecords();
        List<RiskView> riskViews = new ArrayList<>();
        for (RiskEntity riskEntity : records) {
            RiskView view = new RiskView();
            BeanUtils.copyProperties(riskEntity, view);
            String count = "无";
            EntityWrapper<ThresholdEntity> ew1 = new EntityWrapper<>();
            ew1.eq("relation_id", riskEntity.getRelationId());
            ThresholdEntity thresholdEntity = thresholdService.selectOne(ew1);
            if(thresholdEntity != null) {
                if (riskEntity.getHeat() > thresholdEntity.getHeat() || riskEntity.getHumidity() > thresholdEntity.getHumidity() ||
                        riskEntity.getNum() > thresholdEntity.getNum() || riskEntity.getStockTime() > thresholdEntity.getStockTime()) {
                    int i = riskEntity.getHeat() - thresholdEntity.getHeat();
                    int j = riskEntity.getHumidity() - thresholdEntity.getHumidity();
                    long z = riskEntity.getNum() - thresholdEntity.getNum();
                    int m = riskEntity.getStockTime() - thresholdEntity.getStockTime();
                    if ((i > 10 && i < 20) || (j > 10 && j < 20) || (z > 10 && z < 20) || (m > 10 && m < 20)) {
                        count = "蓝";
                    } else if ((i > 20 && i < 30) || (j > 20 && j < 30) || (z > 20 && z < 30) || (m > 20 && m < 30)) {
                        count = "黄";
                    } else if ((i > 30 && i < 40) || (j > 30 && j < 40) || (z > 30 && z < 40) || (m > 30 && m < 40)) {
                        count = "橙";
                    } else if (i > 40 || j > 40 || z > 40 || m > 40) {
                        count = "红";
                    }
                }
            }
            if(StringUtils.equals(riskEntity.getRoad(),"无路")){
                count = "红";
            }

            view.setLevel(count);
            riskViews.add(view);
        }
        page1.setRecords(riskViews);
        return R.ok().put("data", page1);
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody RiskEntity risk){
        riskService.deleteById(risk.getId());
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind")
	public R remindCount(HttpServletRequest request) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        EntityWrapper<RiskEntity> ew = new EntityWrapper<>();
        ew.eq("monitor_time", format);
        List<RiskEntity> riskEntities = riskService.selectList(ew);
        int count = 0;
        for(RiskEntity riskEntity : riskEntities){
            EntityWrapper<ThresholdEntity> ew1 = new EntityWrapper<>();
            ew1.eq("relation_id", riskEntity.getRelationId());
            ThresholdEntity thresholdEntity = thresholdService.selectOne(ew1);

            if(riskEntity.getHeat() > thresholdEntity.getHeat() || riskEntity.getHumidity() > thresholdEntity.getHumidity() ||
                    riskEntity.getNum() > thresholdEntity.getNum() || riskEntity.getStockTime()>thresholdEntity.getStockTime()) {
                int i = riskEntity.getHeat() - thresholdEntity.getHeat();
                int j = riskEntity.getHumidity() - thresholdEntity.getHumidity();
                long z = riskEntity.getNum() - thresholdEntity.getNum();
                int m = riskEntity.getStockTime() - thresholdEntity.getStockTime();
                if((i>10 && i<20) || (j>10 && j<20) || (z>10 && z<20) || (m>10 && m<20)) {
                    count =1;
                    break;
                }else if((i>20 && i<30) || (j>20 && j<30) || (z>20 && z<30) || (m>20 && m<30)) {
                    count =2;
                    break;
                }else if ((i>30 && i<40) || (j>30 && j<40) || (z>30 && z<40)|| (m>30 && m<40)) {
                    count =3;
                    break;
                } else if (i>40 || j>40 || z>40|| m>40 ) {
                    count =4;
                    break;
                }
            }
            if(StringUtils.equals(riskEntity.getRoad(),"无路")){
                count =4;
                break;
            }
        }

		return R.ok().put("count", count);
	}
	






}
