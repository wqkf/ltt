package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;

import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;


/**
 * 物资出库
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@TableName("book")
@Data
public class BookEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public BookEntity() {

	}

	public BookEntity(T t) {
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
	@TableId
	private Long id;


	private String name;


	private String img;


	private Float rate;


	private String jianjie;


	private String keyword;

	private String title;

}