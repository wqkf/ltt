package com.entity.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 物资捐赠
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public class WuzijuanzengModel  implements Serializable {
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
	 * 捐赠数量
	 */
	
	private String juanzengshuliang;
		
	/**
	 * 地区
	 */
	
	private String diqu;
		
	/**
	 * 物资类型
	 */
	
	private String wuzileixing;
		
	/**
	 * 具体地址
	 */
	
	private String jutidizhi;
		
	/**
	 * 用户名
	 */
	
	private String yonghuming;
		
	/**
	 * 姓名
	 */
	
	private String xingming;
		
	/**
	 * 手机号
	 */
	
	private String shoujihao;
		
	/**
	 * 捐赠时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date juanzengshijian;
				
	
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
	 * 设置：捐赠数量
	 */
	 
	public void setJuanzengshuliang(String juanzengshuliang) {
		this.juanzengshuliang = juanzengshuliang;
	}
	
	/**
	 * 获取：捐赠数量
	 */
	public String getJuanzengshuliang() {
		return juanzengshuliang;
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
	 * 设置：用户名
	 */
	 
	public void setYonghuming(String yonghuming) {
		this.yonghuming = yonghuming;
	}
	
	/**
	 * 获取：用户名
	 */
	public String getYonghuming() {
		return yonghuming;
	}
				
	
	/**
	 * 设置：姓名
	 */
	 
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
	}
				
	
	/**
	 * 设置：手机号
	 */
	 
	public void setShoujihao(String shoujihao) {
		this.shoujihao = shoujihao;
	}
	
	/**
	 * 获取：手机号
	 */
	public String getShoujihao() {
		return shoujihao;
	}
				
	
	/**
	 * 设置：捐赠时间
	 */
	 
	public void setJuanzengshijian(Date juanzengshijian) {
		this.juanzengshijian = juanzengshijian;
	}
	
	/**
	 * 获取：捐赠时间
	 */
	public Date getJuanzengshijian() {
		return juanzengshijian;
	}
			
}
