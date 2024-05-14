package com.entity.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 物资筹措
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
public class WuzichoucuoModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 物资名称
	 */
	
	private String wuzimingcheng;
		
	/**
	 * 物资类型
	 */
	
	private String wuzileixing;
		
	/**
	 * 物资数量
	 */
	
	private Integer wuzishuliang;
		
	/**
	 * 供应商名称
	 */
	
	private String gongyingshangmingcheng;
		
	/**
	 * 供应商地址
	 */
	
	private String gongyingshangdizhi;
		
	/**
	 * 地区
	 */
	
	private String diqu;
		
	/**
	 * 合同
	 */
	
	private String hetong;
		
	/**
	 * 签订时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date qiandingshijian;
				
	
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
	 * 设置：物资数量
	 */
	 
	public void setWuzishuliang(Integer wuzishuliang) {
		this.wuzishuliang = wuzishuliang;
	}
	
	/**
	 * 获取：物资数量
	 */
	public Integer getWuzishuliang() {
		return wuzishuliang;
	}
				
	
	/**
	 * 设置：供应商名称
	 */
	 
	public void setGongyingshangmingcheng(String gongyingshangmingcheng) {
		this.gongyingshangmingcheng = gongyingshangmingcheng;
	}
	
	/**
	 * 获取：供应商名称
	 */
	public String getGongyingshangmingcheng() {
		return gongyingshangmingcheng;
	}
				
	
	/**
	 * 设置：供应商地址
	 */
	 
	public void setGongyingshangdizhi(String gongyingshangdizhi) {
		this.gongyingshangdizhi = gongyingshangdizhi;
	}
	
	/**
	 * 获取：供应商地址
	 */
	public String getGongyingshangdizhi() {
		return gongyingshangdizhi;
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
	 * 设置：合同
	 */
	 
	public void setHetong(String hetong) {
		this.hetong = hetong;
	}
	
	/**
	 * 获取：合同
	 */
	public String getHetong() {
		return hetong;
	}
				
	
	/**
	 * 设置：签订时间
	 */
	 
	public void setQiandingshijian(Date qiandingshijian) {
		this.qiandingshijian = qiandingshijian;
	}
	
	/**
	 * 获取：签订时间
	 */
	public Date getQiandingshijian() {
		return qiandingshijian;
	}
			
}
