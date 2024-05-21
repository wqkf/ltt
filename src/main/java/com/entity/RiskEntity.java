package com.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/** 
 * token表
 */
@TableName("risk")
@Data
public class RiskEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	/**
	 * 储存数量
	 */
	private Long num;
	
	/**
	 * 检测时间
	 */
	private String monitorTime;
	
	/**
	 * 温度
	 */
	private Integer heat;
	
	/**
	 * 湿度
	 */
	private Integer humidity;
	
	/**
	 * 入库时间
	 */
	private Integer stockTime;

	/**
	 * 道路状态
	 */
	private String  road;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 角色
	 */
	private Integer role;

	/**
	 * 关联id
	 */
	private Integer relationId;
}
