package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;


/**
 * 物资入库
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@TableName("stock")
@Data
public class StockEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public StockEntity() {
		
	}
	
	public StockEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 地址
	 */
					
	private String address;
	
	/**
	 * 种类
	 */
					
	private String category;
	
	/**
	 * 入库时间
	 */
					
	private String stockTime;
	
	/**
	 * 储存数量
	 */
					
	private Integer num;

}
