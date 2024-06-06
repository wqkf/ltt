package com.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/** 
 * 用户
 */
@TableName("obj_user")
@Data
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	/**
	 * 用户账号
	 */
	private String nickname;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 用户类型
	 */
	private Integer role = 0;

	/**
	 * 会员截止日期
	 */
	private Date deadline;

	/**
	 * 头像
	 */
	private String pic = "https://img01.yzcdn.cn/vant/cat.jpeg";


	/**
	 * 是否会员
	 */
	private Integer ifvip = 0;

	/**
	 * 年齡
	 */
	private Integer age;
}
