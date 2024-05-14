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
 * 地区
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@TableName("approve")
@Data
public class ApproveEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ApproveEntity() {
		
	}
	
	public ApproveEntity(T t) {
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

	/**
	 * 车牌号
	 */

	private String carNumber;

	/**
	 * 密码
	 */

	private String password;

	/**
	 * 车型
	 */

	private String model;

	/**
	 * 年龄
	 */

	private Integer age;

	/**
	 * 身份证号
	 */

	private String idNumber;

	/**
	 * 审批状态
	 */
	private String status;

	/**
	 * 用户id
	 */
	private Long userId;

}
