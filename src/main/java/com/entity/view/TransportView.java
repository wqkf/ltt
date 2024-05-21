package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.StockEntity;
import com.entity.TransportEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 物资入库
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@EqualsAndHashCode(callSuper = true)
@TableName("stock")
@Data
public class TransportView extends TransportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public TransportView(){
	}

 	public TransportView(StockEntity wuzirukuEntity){
 	try {
			BeanUtils.copyProperties(this, wuzirukuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}

	/**
	 * 页码
	 */
	private Integer page=1;
}
