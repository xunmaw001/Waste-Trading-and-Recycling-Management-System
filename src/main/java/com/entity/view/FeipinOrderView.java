package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.FeipinOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 废品订单
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("feipin_order")
public class FeipinOrderView extends FeipinOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单类型的值
	*/
	@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
	private String feipinOrderValue;

	//级联表 废品
					 
		/**
		* 废品 的 用户
		*/
		@ColumnInfo(comment="用户",type="int(11)")
		private Integer feipinYonghuId;
		/**
		* 废品名称
		*/

		@ColumnInfo(comment="废品名称",type="varchar(200)")
		private String feipinName;
		/**
		* 废品编号
		*/

		@ColumnInfo(comment="废品编号",type="varchar(200)")
		private String feipinUuidNumber;
		/**
		* 废品照片
		*/

		@ColumnInfo(comment="废品照片",type="varchar(200)")
		private String feipinPhoto;
		/**
		* 废品地点
		*/

		@ColumnInfo(comment="废品地点",type="varchar(200)")
		private String feipinAddress;
		/**
		* 废品类型
		*/
		@ColumnInfo(comment="废品类型",type="int(11)")
		private Integer feipinTypes;
			/**
			* 废品类型的值
			*/
			@ColumnInfo(comment="废品类型的字典表值",type="varchar(200)")
			private String feipinValue;
		/**
		* 废品介绍
		*/

		@ColumnInfo(comment="废品介绍",type="text")
		private String feipinContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer feipinDelete;
	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;



	public FeipinOrderView() {

	}

	public FeipinOrderView(FeipinOrderEntity feipinOrderEntity) {
		try {
			BeanUtils.copyProperties(this, feipinOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 订单类型的值
	*/
	public String getFeipinOrderValue() {
		return feipinOrderValue;
	}
	/**
	* 设置： 订单类型的值
	*/
	public void setFeipinOrderValue(String feipinOrderValue) {
		this.feipinOrderValue = feipinOrderValue;
	}


	//级联表的get和set 废品
		/**
		* 获取：废品 的 用户
		*/
		public Integer getFeipinYonghuId() {
			return feipinYonghuId;
		}
		/**
		* 设置：废品 的 用户
		*/
		public void setFeipinYonghuId(Integer feipinYonghuId) {
			this.feipinYonghuId = feipinYonghuId;
		}

		/**
		* 获取： 废品名称
		*/
		public String getFeipinName() {
			return feipinName;
		}
		/**
		* 设置： 废品名称
		*/
		public void setFeipinName(String feipinName) {
			this.feipinName = feipinName;
		}

		/**
		* 获取： 废品编号
		*/
		public String getFeipinUuidNumber() {
			return feipinUuidNumber;
		}
		/**
		* 设置： 废品编号
		*/
		public void setFeipinUuidNumber(String feipinUuidNumber) {
			this.feipinUuidNumber = feipinUuidNumber;
		}

		/**
		* 获取： 废品照片
		*/
		public String getFeipinPhoto() {
			return feipinPhoto;
		}
		/**
		* 设置： 废品照片
		*/
		public void setFeipinPhoto(String feipinPhoto) {
			this.feipinPhoto = feipinPhoto;
		}

		/**
		* 获取： 废品地点
		*/
		public String getFeipinAddress() {
			return feipinAddress;
		}
		/**
		* 设置： 废品地点
		*/
		public void setFeipinAddress(String feipinAddress) {
			this.feipinAddress = feipinAddress;
		}
		/**
		* 获取： 废品类型
		*/
		public Integer getFeipinTypes() {
			return feipinTypes;
		}
		/**
		* 设置： 废品类型
		*/
		public void setFeipinTypes(Integer feipinTypes) {
			this.feipinTypes = feipinTypes;
		}


			/**
			* 获取： 废品类型的值
			*/
			public String getFeipinValue() {
				return feipinValue;
			}
			/**
			* 设置： 废品类型的值
			*/
			public void setFeipinValue(String feipinValue) {
				this.feipinValue = feipinValue;
			}

		/**
		* 获取： 废品介绍
		*/
		public String getFeipinContent() {
			return feipinContent;
		}
		/**
		* 设置： 废品介绍
		*/
		public void setFeipinContent(String feipinContent) {
			this.feipinContent = feipinContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getFeipinDelete() {
			return feipinDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setFeipinDelete(Integer feipinDelete) {
			this.feipinDelete = feipinDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}


	@Override
	public String toString() {
		return "FeipinOrderView{" +
			", feipinOrderValue=" + feipinOrderValue +
			", feipinName=" + feipinName +
			", feipinUuidNumber=" + feipinUuidNumber +
			", feipinPhoto=" + feipinPhoto +
			", feipinAddress=" + feipinAddress +
			", feipinContent=" + feipinContent +
			", feipinDelete=" + feipinDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
