
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 废品订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/feipinOrder")
public class FeipinOrderController {
    private static final Logger logger = LoggerFactory.getLogger(FeipinOrderController.class);

    private static final String TABLE_NAME = "feipinOrder";

    @Autowired
    private FeipinOrderService feipinOrderService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ChatService chatService;//反馈
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private FeipinService feipinService;//废品
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private LiuyanService liuyanService;//环保咨询
    @Autowired
    private SingleSeachService singleSeachService;//回收指南
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = feipinOrderService.queryPage(params);

        //字典表数据转换
        List<FeipinOrderView> list =(List<FeipinOrderView>)page.getList();
        for(FeipinOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FeipinOrderEntity feipinOrder = feipinOrderService.selectById(id);
        if(feipinOrder !=null){
            //entity转view
            FeipinOrderView view = new FeipinOrderView();
            BeanUtils.copyProperties( feipinOrder , view );//把实体数据重构到view中
            //级联表 废品
            //级联表
            FeipinEntity feipin = feipinService.selectById(feipinOrder.getFeipinId());
            if(feipin != null){
            BeanUtils.copyProperties( feipin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setFeipinId(feipin.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(feipinOrder.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody FeipinOrderEntity feipinOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,feipinOrder:{}",this.getClass().getName(),feipinOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            feipinOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        feipinOrder.setCreateTime(new Date());
        feipinOrder.setInsertTime(new Date());
        feipinOrderService.insert(feipinOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FeipinOrderEntity feipinOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,feipinOrder:{}",this.getClass().getName(),feipinOrder.toString());
        FeipinOrderEntity oldFeipinOrderEntity = feipinOrderService.selectById(feipinOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            feipinOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            feipinOrderService.updateById(feipinOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<FeipinOrderEntity> oldFeipinOrderList =feipinOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        feipinOrderService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<FeipinOrderEntity> feipinOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            FeipinOrderEntity feipinOrderEntity = new FeipinOrderEntity();
//                            feipinOrderEntity.setFeipinId(Integer.valueOf(data.get(0)));   //废品 要改的
//                            feipinOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            feipinOrderEntity.setFeipinOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            feipinOrderEntity.setInsertTime(date);//时间
//                            feipinOrderEntity.setCreateTime(date);//时间
                            feipinOrderList.add(feipinOrderEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        feipinOrderService.insertBatch(feipinOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = feipinOrderService.queryPage(params);

        //字典表数据转换
        List<FeipinOrderView> list =(List<FeipinOrderView>)page.getList();
        for(FeipinOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FeipinOrderEntity feipinOrder = feipinOrderService.selectById(id);
            if(feipinOrder !=null){


                //entity转view
                FeipinOrderView view = new FeipinOrderView();
                BeanUtils.copyProperties( feipinOrder , view );//把实体数据重构到view中

                //级联表
                    FeipinEntity feipin = feipinService.selectById(feipinOrder.getFeipinId());
                if(feipin != null){
                    BeanUtils.copyProperties( feipin , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setFeipinId(feipin.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(feipinOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody FeipinOrderEntity feipinOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,feipinOrder:{}",this.getClass().getName(),feipinOrder.toString());
            FeipinEntity feipinEntity = feipinService.selectById(feipinOrder.getFeipinId());
            if(feipinEntity == null){
                return R.error(511,"查不到该废品");
            }
            // Double feipinNewMoney = feipinEntity.getFeipinNewMoney();

            if(false){
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");


            feipinOrder.setFeipinOrderTypes(101); //设置订单状态为已预约上门

            feipinOrder.setYonghuId(userId); //设置订单支付人id
            feipinOrder.setInsertTime(new Date());
            feipinOrder.setCreateTime(new Date());
                feipinOrderService.insert(feipinOrder);//新增订单


            yonghuService.updateById(yonghuEntity);

            return R.ok();
    }


    /**
    * 取消上门预约
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            FeipinOrderEntity feipinOrder = feipinOrderService.selectById(id);//当前表service
            Integer feipinId = feipinOrder.getFeipinId();
            if(feipinId == null)
                return R.error(511,"查不到该废品");
            FeipinEntity feipinEntity = feipinService.selectById(feipinId);
            if(feipinEntity == null)
                return R.error(511,"查不到该废品");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            Double zhekou = 1.0;


                //计算所获得积分
                Double buyJifen = 0.0;





            feipinOrder.setFeipinOrderTypes(102);//设置订单状态为已取消上门预约
            feipinOrderService.updateById(feipinOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            feipinService.updateById(feipinEntity);//更新订单中废品的信息

            return R.ok();
    }

    /**
     * 同意上门
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        FeipinOrderEntity  feipinOrderEntity = feipinOrderService.selectById(id);
        feipinOrderEntity.setFeipinOrderTypes(103);//设置订单状态为已同意上门
        feipinOrderService.updateById( feipinOrderEntity);

        return R.ok();
    }


    /**
     * 取走废品
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        FeipinOrderEntity  feipinOrderEntity = feipinOrderService.selectById(id);
        feipinOrderEntity.setFeipinOrderTypes(104);//设置订单状态为取走废品
        feipinOrderService.updateById( feipinOrderEntity);
        return R.ok();
    }

}

