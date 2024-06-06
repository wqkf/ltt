package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;


/**
 * 地区
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@TableName("booklb")
@Data
public class BooklbEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public BooklbEntity() {
		
	}
	
	public BooklbEntity(T t) {
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
	 * 用户名
	 */

	private String name;



}
