package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 废品
 *
 * @author 
 * @email
 */
@TableName("feipin")
public class FeipinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FeipinEntity() {

	}

	public FeipinEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 废品名称
     */
    @ColumnInfo(comment="废品名称",type="varchar(200)")
    @TableField(value = "feipin_name")

    private String feipinName;


    /**
     * 废品编号
     */
    @ColumnInfo(comment="废品编号",type="varchar(200)")
    @TableField(value = "feipin_uuid_number")

    private String feipinUuidNumber;


    /**
     * 废品照片
     */
    @ColumnInfo(comment="废品照片",type="varchar(200)")
    @TableField(value = "feipin_photo")

    private String feipinPhoto;


    /**
     * 废品地点
     */
    @ColumnInfo(comment="废品地点",type="varchar(200)")
    @TableField(value = "feipin_address")

    private String feipinAddress;


    /**
     * 废品类型
     */
    @ColumnInfo(comment="废品类型",type="int(11)")
    @TableField(value = "feipin_types")

    private Integer feipinTypes;


    /**
     * 废品介绍
     */
    @ColumnInfo(comment="废品介绍",type="text")
    @TableField(value = "feipin_content")

    private String feipinContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "feipin_delete")

    private Integer feipinDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：废品名称
	 */
    public String getFeipinName() {
        return feipinName;
    }
    /**
	 * 设置：废品名称
	 */

    public void setFeipinName(String feipinName) {
        this.feipinName = feipinName;
    }
    /**
	 * 获取：废品编号
	 */
    public String getFeipinUuidNumber() {
        return feipinUuidNumber;
    }
    /**
	 * 设置：废品编号
	 */

    public void setFeipinUuidNumber(String feipinUuidNumber) {
        this.feipinUuidNumber = feipinUuidNumber;
    }
    /**
	 * 获取：废品照片
	 */
    public String getFeipinPhoto() {
        return feipinPhoto;
    }
    /**
	 * 设置：废品照片
	 */

    public void setFeipinPhoto(String feipinPhoto) {
        this.feipinPhoto = feipinPhoto;
    }
    /**
	 * 获取：废品地点
	 */
    public String getFeipinAddress() {
        return feipinAddress;
    }
    /**
	 * 设置：废品地点
	 */

    public void setFeipinAddress(String feipinAddress) {
        this.feipinAddress = feipinAddress;
    }
    /**
	 * 获取：废品类型
	 */
    public Integer getFeipinTypes() {
        return feipinTypes;
    }
    /**
	 * 设置：废品类型
	 */

    public void setFeipinTypes(Integer feipinTypes) {
        this.feipinTypes = feipinTypes;
    }
    /**
	 * 获取：废品介绍
	 */
    public String getFeipinContent() {
        return feipinContent;
    }
    /**
	 * 设置：废品介绍
	 */

    public void setFeipinContent(String feipinContent) {
        this.feipinContent = feipinContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getFeipinDelete() {
        return feipinDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setFeipinDelete(Integer feipinDelete) {
        this.feipinDelete = feipinDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Feipin{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", feipinName=" + feipinName +
            ", feipinUuidNumber=" + feipinUuidNumber +
            ", feipinPhoto=" + feipinPhoto +
            ", feipinAddress=" + feipinAddress +
            ", feipinTypes=" + feipinTypes +
            ", feipinContent=" + feipinContent +
            ", feipinDelete=" + feipinDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
