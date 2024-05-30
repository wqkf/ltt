package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.UserEntity;
import com.entity.YonghuEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 用户
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@EqualsAndHashCode(callSuper = true)
@TableName("user")
@Data
public class UserView extends UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public UserView(){
	}


	/**
	 * 剩余借阅次数
	 */
	private Integer surplus;
}
