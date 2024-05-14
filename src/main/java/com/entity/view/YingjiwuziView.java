package com.entity.view;

import com.entity.YingjiwuziEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 应急物资
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@TableName("yingjiwuzi")
public class YingjiwuziView  extends YingjiwuziEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public YingjiwuziView(){
	}
 
 	public YingjiwuziView(YingjiwuziEntity yingjiwuziEntity){
 	try {
			BeanUtils.copyProperties(this, yingjiwuziEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
