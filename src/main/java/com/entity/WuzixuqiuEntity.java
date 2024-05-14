package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 物资需求
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@TableName("wuzixuqiu")
public class WuzixuqiuEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public WuzixuqiuEntity() {
		
	}
	
	public WuzixuqiuEntity(T t) {
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
	@TableId
	private Long id;
	/**
	 * 物资编号
	 */
					
	private String wuzibianhao;
	
	/**
	 * 标题名称
	 */
					
	private String biaotimingcheng;
	
	/**
	 * 物资名称
	 */
					
	private String wuzimingcheng;
	
	/**
	 * 需求数量
	 */
					
	private String xuqiushuliang;
	
	/**
	 * 地区
	 */
					
	private String diqu;
	
	/**
	 * 物资类型
	 */
					
	private String wuzileixing;
	
	/**
	 * 咨询热线
	 */
					
	private String zixunrexian;
	
	/**
	 * 具体地址
	 */
					
	private String jutidizhi;
	
	/**
	 * 详情
	 */
					
	private String xiangqing;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：物资编号
	 */
	public void setWuzibianhao(String wuzibianhao) {
		this.wuzibianhao = wuzibianhao;
	}
	/**
	 * 获取：物资编号
	 */
	public String getWuzibianhao() {
		return wuzibianhao;
	}
	/**
	 * 设置：标题名称
	 */
	public void setBiaotimingcheng(String biaotimingcheng) {
		this.biaotimingcheng = biaotimingcheng;
	}
	/**
	 * 获取：标题名称
	 */
	public String getBiaotimingcheng() {
		return biaotimingcheng;
	}
	/**
	 * 设置：物资名称
	 */
	public void setWuzimingcheng(String wuzimingcheng) {
		this.wuzimingcheng = wuzimingcheng;
	}
	/**
	 * 获取：物资名称
	 */
	public String getWuzimingcheng() {
		return wuzimingcheng;
	}
	/**
	 * 设置：需求数量
	 */
	public void setXuqiushuliang(String xuqiushuliang) {
		this.xuqiushuliang = xuqiushuliang;
	}
	/**
	 * 获取：需求数量
	 */
	public String getXuqiushuliang() {
		return xuqiushuliang;
	}
	/**
	 * 设置：地区
	 */
	public void setDiqu(String diqu) {
		this.diqu = diqu;
	}
	/**
	 * 获取：地区
	 */
	public String getDiqu() {
		return diqu;
	}
	/**
	 * 设置：物资类型
	 */
	public void setWuzileixing(String wuzileixing) {
		this.wuzileixing = wuzileixing;
	}
	/**
	 * 获取：物资类型
	 */
	public String getWuzileixing() {
		return wuzileixing;
	}
	/**
	 * 设置：咨询热线
	 */
	public void setZixunrexian(String zixunrexian) {
		this.zixunrexian = zixunrexian;
	}
	/**
	 * 获取：咨询热线
	 */
	public String getZixunrexian() {
		return zixunrexian;
	}
	/**
	 * 设置：具体地址
	 */
	public void setJutidizhi(String jutidizhi) {
		this.jutidizhi = jutidizhi;
	}
	/**
	 * 获取：具体地址
	 */
	public String getJutidizhi() {
		return jutidizhi;
	}
	/**
	 * 设置：详情
	 */
	public void setXiangqing(String xiangqing) {
		this.xiangqing = xiangqing;
	}
	/**
	 * 获取：详情
	 */
	public String getXiangqing() {
		return xiangqing;
	}

}
