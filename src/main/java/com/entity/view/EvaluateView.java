package com.entity.view;

import com.entity.EvaluateEntity;
import com.entity.RiskRoadEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 物资捐赠
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@EqualsAndHashCode(callSuper = true)
@TableName("evaluate")
@Data
public class EvaluateView extends EvaluateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public EvaluateView(){
	}
 
 	private String  nickname;

	private String pic;
}
