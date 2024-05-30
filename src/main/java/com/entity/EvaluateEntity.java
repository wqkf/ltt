package com.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
* 类说明 : 
*/
@TableName("evaluate")
@Data
public class EvaluateEntity implements Serializable{
private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	/**
	 * key
	 */
	private String context;
	
	/**
	 * value
	 */
	private Integer uid;

	/**
	 *
	 */
	private Integer bid;
	
}
