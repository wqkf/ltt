package com.entity.view;

import com.entity.ThresholdEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 物资筹措
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@TableName("wuzichoucuo")
public class ThresholdView extends ThresholdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ThresholdView(){
	}
 
 	public ThresholdView(ThresholdEntity thresholdEntity){
 	try {
			BeanUtils.copyProperties(this, thresholdEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
