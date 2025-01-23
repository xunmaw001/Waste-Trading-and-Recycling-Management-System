package com.entity.model;

import com.entity.FeipinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 废品
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class FeipinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 废品名称
     */
    private String feipinName;


    /**
     * 废品编号
     */
    private String feipinUuidNumber;


    /**
     * 废品照片
     */
    private String feipinPhoto;


    /**
     * 废品地点
     */
    private String feipinAddress;


    /**
     * 废品类型
     */
    private Integer feipinTypes;


    /**
     * 废品介绍
     */
    private String feipinContent;


    /**
     * 逻辑删除
     */
    private Integer feipinDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
