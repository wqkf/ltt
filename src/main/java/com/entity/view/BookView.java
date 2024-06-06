package com.entity.view;

import com.entity.BookEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 物资出库
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@EqualsAndHashCode(callSuper = true)
@TableName("book")
@Data
public class BookView extends BookEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public BookView(){
	}
 
 	public BookView(BookEntity bookEntity){
 	try {
			BeanUtils.copyProperties(this, bookEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}

	private	String actor;

	/**
	 * 是否到期 1 到期 0 没有
	 */
	private Integer mature;

	/**
	 * 页码
	 */
	private Integer page=1;

	/**
	 * 书籍类型
	 */
	private Integer categoryId;

	private String categoryName;
}
