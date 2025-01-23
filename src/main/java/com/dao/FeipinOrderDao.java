package com.dao;

import com.entity.FeipinOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.FeipinOrderView;

/**
 * 废品订单 Dao 接口
 *
 * @author 
 */
public interface FeipinOrderDao extends BaseMapper<FeipinOrderEntity> {

   List<FeipinOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
