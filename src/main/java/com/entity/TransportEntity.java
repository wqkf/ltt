package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 物资入库
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@TableName("transport")
@Data
public class TransportEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public TransportEntity() {

	}

	public TransportEntity(T t) {
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
