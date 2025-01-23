package com.entity.vo;

import com.entity.FeipinEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 废品
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("feipin")
public class FeipinVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 废品名称
     */

    @TableField(value = "feipin_name")
    private String feipinName;


    /**
     * 废品编号
     */

    @TableField(value = "feipin_uuid_number")
    private String feipinUuidNumber;


    /**
     * 废品照片
     */

    @TableField(value = "feipin_photo")
    private String feipinPhoto;


    /**
     * 废品地点
     */

    @TableField(value = "feipin_address")
    private String feipinAddress;


    /**
     * 废品类型
     */

    @TableField(value = "feipin_types")
    private Integer feipinTypes;


    /**
     * 废品介绍
     */

    @TableField(value = "feipin_content")
    private String feipinContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "feipin_delete")
    private Integer feipinDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：废品名称
	 */
    public String getFeipinName() {
        return feipinName;
    }


    /**
	 * 获取：废品名称
	 */

    public void setFeipinName(String feipinName) {
        this.feipinName = feipinName;
    }
    /**
	 * 设置：废品编号
	 */
    public String getFeipinUuidNumber() {
        return feipinUuidNumber;
    }


    /**
	 * 获取：废品编号
	 */

    public void setFeipinUuidNumber(String feipinUuidNumber) {
        this.feipinUuidNumber = feipinUuidNumber;
    }
    /**
	 * 设置：废品照片
	 */
    public String getFeipinPhoto() {
        return feipinPhoto;
    }


    /**
	 * 获取：废品照片
	 */

    public void setFeipinPhoto(String feipinPhoto) {
        this.feipinPhoto = feipinPhoto;
    }
    /**
	 * 设置：废品地点
	 */
    public String getFeipinAddress() {
        return feipinAddress;
    }


    /**
	 * 获取：废品地点
	 */

    public void setFeipinAddress(String feipinAddress) {
        this.feipinAddress = feipinAddress;
    }
    /**
	 * 设置：废品类型
	 */
    public Integer getFeipinTypes() {
        return feipinTypes;
    }


    /**
	 * 获取：废品类型
	 */

    public void setFeipinTypes(Integer feipinTypes) {
        this.feipinTypes = feipinTypes;
    }
    /**
	 * 设置：废品介绍
	 */
    public String getFeipinContent() {
        return feipinContent;
    }


    /**
	 * 获取：废品介绍
	 */

    public void setFeipinContent(String feipinContent) {
        this.feipinContent = feipinContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getFeipinDelete() {
        return feipinDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setFeipinDelete(Integer feipinDelete) {
        this.feipinDelete = feipinDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
