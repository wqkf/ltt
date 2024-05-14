package com.entity.view;

import com.entity.GongyingshangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.RiskEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@EqualsAndHashCode(callSuper = true)
@TableName("risk")
@Data
public class RiskView  extends RiskEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 頁碼
	 */
	private Integer page =1;
}
