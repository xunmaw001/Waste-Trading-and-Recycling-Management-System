
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
 * 废品
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/feipin")
public class FeipinController {
    private static final Logger logger = LoggerFactory.getLogger(FeipinController.class);

    private static final String TABLE_NAME = "feipin";

    @Autowired
    private FeipinService feipinService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ChatService chatService;//反馈
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private FeipinOrderService feipinOrderService;//废品订单
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
        params.put("feipinDeleteStart",1);params.put("feipinDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = feipinService.queryPage(params);

        //字典表数据转换
        List<FeipinView> list =(List<FeipinView>)page.getList();
        for(FeipinView c:list){
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
        FeipinEntity feipin = feipinService.selectById(id);
        if(feipin !=null){
            //entity转view
            FeipinView view = new FeipinView();
            BeanUtils.copyProperties( feipin , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(feipin.getYonghuId());
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
    public R save(@RequestBody FeipinEntity feipin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,feipin:{}",this.getClass().getName(),feipin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            feipin.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<FeipinEntity> queryWrapper = new EntityWrapper<FeipinEntity>()
            .eq("yonghu_id", feipin.getYonghuId())
            .eq("feipin_name", feipin.getFeipinName())
            .eq("feipin_address", feipin.getFeipinAddress())
            .eq("feipin_types", feipin.getFeipinTypes())
            .eq("feipin_delete", feipin.getFeipinDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FeipinEntity feipinEntity = feipinService.selectOne(queryWrapper);
        if(feipinEntity==null){
            feipin.setFeipinDelete(1);
            feipin.setInsertTime(new Date());
            feipin.setCreateTime(new Date());
            feipinService.insert(feipin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FeipinEntity feipin, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,feipin:{}",this.getClass().getName(),feipin.toString());
        FeipinEntity oldFeipinEntity = feipinService.selectById(feipin.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            feipin.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(feipin.getFeipinPhoto()) || "null".equals(feipin.getFeipinPhoto())){
                feipin.setFeipinPhoto(null);
        }

            feipinService.updateById(feipin);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<FeipinEntity> oldFeipinList =feipinService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<FeipinEntity> list = new ArrayList<>();
        for(Integer id:ids){
            FeipinEntity feipinEntity = new FeipinEntity();
            feipinEntity.setId(id);
            feipinEntity.setFeipinDelete(2);
            list.add(feipinEntity);
        }
        if(list != null && list.size() >0){
            feipinService.updateBatchById(list);
        }

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
            List<FeipinEntity> feipinList = new ArrayList<>();//上传的东西
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
                            FeipinEntity feipinEntity = new FeipinEntity();
//                            feipinEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            feipinEntity.setFeipinName(data.get(0));                    //废品名称 要改的
//                            feipinEntity.setFeipinUuidNumber(data.get(0));                    //废品编号 要改的
//                            feipinEntity.setFeipinPhoto("");//详情和图片
//                            feipinEntity.setFeipinAddress(data.get(0));                    //废品地点 要改的
//                            feipinEntity.setFeipinTypes(Integer.valueOf(data.get(0)));   //废品类型 要改的
//                            feipinEntity.setFeipinContent("");//详情和图片
//                            feipinEntity.setFeipinDelete(1);//逻辑删除字段
//                            feipinEntity.setInsertTime(date);//时间
//                            feipinEntity.setCreateTime(date);//时间
                            feipinList.add(feipinEntity);


                            //把要查询是否重复的字段放入map中
                                //废品编号
                                if(seachFields.containsKey("feipinUuidNumber")){
                                    List<String> feipinUuidNumber = seachFields.get("feipinUuidNumber");
                                    feipinUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> feipinUuidNumber = new ArrayList<>();
                                    feipinUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("feipinUuidNumber",feipinUuidNumber);
                                }
                        }

                        //查询是否重复
                         //废品编号
                        List<FeipinEntity> feipinEntities_feipinUuidNumber = feipinService.selectList(new EntityWrapper<FeipinEntity>().in("feipin_uuid_number", seachFields.get("feipinUuidNumber")).eq("feipin_delete", 1));
                        if(feipinEntities_feipinUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FeipinEntity s:feipinEntities_feipinUuidNumber){
                                repeatFields.add(s.getFeipinUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [废品编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        feipinService.insertBatch(feipinList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<FeipinView> returnFeipinViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = feipinOrderService.queryPage(params1);
        List<FeipinOrderView> orderViewsList =(List<FeipinOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(FeipinOrderView orderView:orderViewsList){
            Integer feipinTypes = orderView.getFeipinTypes();
            if(typeMap.containsKey(feipinTypes)){
                typeMap.put(feipinTypes,typeMap.get(feipinTypes)+1);
            }else{
                typeMap.put(feipinTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("feipinTypes",type);
            PageUtils pageUtils1 = feipinService.queryPage(params2);
            List<FeipinView> feipinViewList =(List<FeipinView>)pageUtils1.getList();
            returnFeipinViewList.addAll(feipinViewList);
            if(returnFeipinViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = feipinService.queryPage(params);
        if(returnFeipinViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnFeipinViewList.size();//要添加的数量
            List<FeipinView> feipinViewList =(List<FeipinView>)page.getList();
            for(FeipinView feipinView:feipinViewList){
                Boolean addFlag = true;
                for(FeipinView returnFeipinView:returnFeipinViewList){
                    if(returnFeipinView.getId().intValue() ==feipinView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnFeipinViewList.add(feipinView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnFeipinViewList = returnFeipinViewList.subList(0, limit);
        }

        for(FeipinView c:returnFeipinViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnFeipinViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = feipinService.queryPage(params);

        //字典表数据转换
        List<FeipinView> list =(List<FeipinView>)page.getList();
        for(FeipinView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FeipinEntity feipin = feipinService.selectById(id);
            if(feipin !=null){


                //entity转view
                FeipinView view = new FeipinView();
                BeanUtils.copyProperties( feipin , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(feipin.getYonghuId());
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
    public R add(@RequestBody FeipinEntity feipin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,feipin:{}",this.getClass().getName(),feipin.toString());
        Wrapper<FeipinEntity> queryWrapper = new EntityWrapper<FeipinEntity>()
            .eq("yonghu_id", feipin.getYonghuId())
            .eq("feipin_name", feipin.getFeipinName())
            .eq("feipin_uuid_number", feipin.getFeipinUuidNumber())
            .eq("feipin_address", feipin.getFeipinAddress())
            .eq("feipin_types", feipin.getFeipinTypes())
            .eq("feipin_delete", feipin.getFeipinDelete())
//            .notIn("feipin_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FeipinEntity feipinEntity = feipinService.selectOne(queryWrapper);
        if(feipinEntity==null){
            feipin.setFeipinDelete(1);
            feipin.setInsertTime(new Date());
            feipin.setCreateTime(new Date());
        feipinService.insert(feipin);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

