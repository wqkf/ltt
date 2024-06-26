package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 物资筹措
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@TableName("threshold")
@Data
public class ThresholdEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ThresholdEntity() {
		
	}
	
	public ThresholdEntity(T t) {
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
	 * 入庫時間
	 */
					
	private Integer stockTime;
	
	/**
	 * 溫度
	 */
					
	private Integer heat;
	
	/**
	 * 濕度
	 */
					
	private Integer humidity;
	
	/**
	 * 儲存数量
	 */
					
	private Integer num;

	/**
	 * 道路状态
	 */
	private String road;

	/**
	 * 地址信息
	 */
	private String address;

	/**
	 * 角色
	 */
	private Integer role;

	/**
	 * 运输人员
	 */
	private Long relationId;
}
