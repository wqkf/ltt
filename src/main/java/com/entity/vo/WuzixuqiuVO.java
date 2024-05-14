package com.entity.vo;

import com.entity.WuzixuqiuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 物资需求
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public class WuzixuqiuVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
