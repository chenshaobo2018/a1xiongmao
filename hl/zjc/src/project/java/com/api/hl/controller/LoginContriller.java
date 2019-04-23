package com.api.hl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.AOS;
import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.system.common.id.IdService;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.hl.dao.TCartDao;
import com.api.hl.dao.TGoodsDao;
import com.api.hl.dao.TOrderDao;
import com.api.hl.dao.TOrderDetailedDao;
import com.api.hl.dao.TOrderReceiveDao;
import com.api.hl.dao.TProjectDao;
import com.api.hl.dao.TSpecificationsDao;
import com.api.hl.dao.TSupplierDao;
import com.api.hl.dao.TTypeDao;
import com.api.hl.dao.TUserProjectDao;
import com.api.hl.dao.TUserhlDao;
import com.api.hl.dao.po.TCartPO;
import com.api.hl.dao.po.TGoodsPO;
import com.api.hl.dao.po.TOrderDetailedPO;
import com.api.hl.dao.po.TOrderPO;
import com.api.hl.dao.po.TOrderReceivePO;
import com.api.hl.dao.po.TProjectPO;
import com.api.hl.dao.po.TSpecificationsPO;
import com.api.hl.dao.po.TTypePO;
import com.api.hl.dao.po.TUserProjectPO;
import com.api.hl.dao.po.TUserhlPO;
import com.api.hl.service.LoginService;
import com.api.hl.vo.T_catVO;
import com.api.order.dao.po.Goods;
import com.google.common.reflect.TypeToken;


@Controller
@SuppressWarnings("all")
@RequestMapping("/notokenapi")
public class LoginContriller {
	@Autowired
	private IdService IdService;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private IdService idService;
	@Autowired
	private LoginService LoginService;
	@Autowired
	private TGoodsDao T_goodsMapper;
	@Autowired
	private TCartDao T_cartMapper;
	@Autowired
	private TTypeDao T_typeMapper;
	
	@Autowired
	private TProjectDao T_projectMapper;
	
	@Autowired
	private TSupplierDao T_supplierMapper;
	
	@Autowired
	private TSpecificationsDao T_specificationsMapper;
	
	@Autowired
	private TUserProjectDao T_user_projectMapper;
	
	@Autowired
	private TUserhlDao T_userhlMapper;
	
	@Autowired
	private TOrderDao T_orderMapper;
	
	@Autowired
	private TOrderDetailedDao T_order_detailedMapper;
	
	@Autowired
	private TOrderReceiveDao tOrderReceiveMapper;
	
	
	/**
	 * 01.notokenapi/app/v1/add_detail.jhtml 查询类型数据
	 * 02.notokenapi/app/v1/storeLogin.jhtml 登录
	 * 03.notokenapi/app/v1/hlIndex.jhtml 获取商品列表
	 * 04.notokenapi/app/v1/hlLikeIndex.jhtml 根据商品名称获取商品列表
	 * 05.notokenapi/app/v1/query_detail.jhtml 添加产品数据
	 * 06.notokenapi/app/v1/con_order.jhtml 获取购物车中的数据数据
	 * 07.notokenapi/app/v1/add_cat.jhtml 添加购物车
	 * 08.notokenapi/app/v1/delete_cat.jhtml 删除购物车
	 * 09.notokenapi/app/v1/update_cat.jhtml 修改购物车
	 * 10.notokenapi/app/v1/add_order.jhtml 添加购物车
	 * 11.notokenapi/app/v1/order_list.jhtml 查询订单列表
	 * 12.notokenapi/app/v1/order_detailed.jhtml 订单详情
	 * 13.notokenapi/app/v1/getGoodsVOByMaterial.jhtml 根据材料名称查询产品集合
	 * 
	 * 14.notokenapi/app/v1/order_receive_list.jhtml 收货记录
	 * 15.notokenapi/app/v1/add_order_receive.jhtml 收货
	 * 16.notokenapi/app/v1/reduce_repertory.jhtml 出库
	 * 17.notokenapi/app/v1/reduce_repertory_init.jhtml 出库（页面初始化）
	 * 18.
	 * 19.
	 * 20.
	 */
	
	//01.
	/**
	 * @api {get} notokenapi/app/v1/add_detail.jhtml 查询类型数据
	 * @apiName add_detail 查询类型数据
	 * @apiGroup hl
	 *
	 */
	@RequestMapping(value = "/app/v1/add_detail")
	public void add_detail(HttpServletRequest request, HttpServletResponse response){
		    MessageVO msg = new MessageVO();
		    List<TTypePO> type=LoginService.getgoodsType();
		    if(AOSUtils.isEmpty(type)){
		    	msg.setCode(Apiconstant.Do_Fails.getIndex());
		    	msg.setMsg(Apiconstant.Do_Fails.getName());
				msg.setData("");
		    }else {
		    	msg.setCode(Apiconstant.Do_Success.getIndex());
		    	msg.setMsg(Apiconstant.Do_Success.getName());
				msg.setData(type);
			}
		    WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	//02.
	/**
	 * @api {get} notokenapi/app/v1/storeLogin.jhtml 登录
	 * @apiName storeLogin获取商品列表
	 * @apiGroup hl
	 *
	 * @apiParam {String} phone 电话
	 */
	@RequestMapping(value = "/app/v1/storeLogin")
	public void storeLogin(HttpServletRequest request, HttpServletResponse response){
		    MessageVO msg = new MessageVO();
		    String phone=request.getParameter("phone");
		    //String password=request.getParameter("password");
		    TUserhlPO user=LoginService.storeLogin(phone);
		    if(AOSUtils.isEmpty(user)){
		    	msg.setCode(Apiconstant.Do_Fails.getIndex());
		    	msg.setMsg(Apiconstant.Do_Fails.getName());
				msg.setData("");
		    }else {
		    	msg.setCode(Apiconstant.Do_Success.getIndex());
		    	msg.setMsg(Apiconstant.Do_Success.getName());
				msg.setData(user);
			}
		   WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	//03.
	/**
	 * @api {get} notokenapi/app/v1/hlIndex.jhtml 获取商品列表
	 * @apiName hlIndex获取商品列表
	 * @apiGroup hl
	 * 
	 * @apiParam {String} type_id 类型id
	 */
	@RequestMapping(value = "/app/v1/hlIndex")
	public void hlIndex(HttpServletRequest request, HttpServletResponse response){
		 String type_id=request.getParameter("type_id");
		 MessageVO msg = new MessageVO();
		 List<TGoodsPO> goods=T_goodsMapper.noRepeatList(Dtos.newDto("type_id", type_id));
		 for (int i = 0; i < goods.size(); i++) {
			String id=goods.get(i).getId();
			List<TSpecificationsPO> TSpecificationsPO=LoginService.getgoodsSpecifications(id);
			goods.get(i).setSpecificationsPO(TSpecificationsPO);
		}
		 if(AOSUtils.isEmpty(goods)){
		    	msg.setCode(Apiconstant.Do_Fails.getIndex());
		    	msg.setMsg(Apiconstant.Do_Fails.getName());
				msg.setData("");
		    }else {
		    	 msg.setData(goods);
				 msg.setCode(Apiconstant.Do_Success.getIndex());
			     msg.setMsg(Apiconstant.Do_Success.getName());
			}
		
		 WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	//04.
	/**
	 * @api {get} notokenapi/app/v1/hlLikeIndex.jhtml 根据商品名称获取商品列表
	 * @apiName hlLikeIndex根据商品名称获取商品列表
	 * @apiGroup hl
	 * 
	 * @apiParam {String} goods_name 商品名称
	 */
	@RequestMapping(value = "/app/v1/hlLikeIndex")
	public void hlLikeIndex(HttpServletRequest request, HttpServletResponse response){
		 String goods_name=request.getParameter("goods_name");
		 MessageVO msg = new MessageVO();
		 List<TGoodsPO> goods=T_goodsMapper.like(Dtos.newDto("material_name", goods_name));
		 if(AOSUtils.isEmpty(goods)){
		    	msg.setCode(Apiconstant.NO_DATA.getIndex());
		    	msg.setMsg(Apiconstant.NO_DATA.getName());
				msg.setData("");
		    }else {
		    	 for (int i = 0; i < goods.size(); i++) {
		 			String id=goods.get(i).getId();
		 			List<TSpecificationsPO> TSpecificationsPO=LoginService.getgoodsSpecifications(id);
		 			goods.get(i).setSpecificationsPO(TSpecificationsPO);
		 		}
		    	 msg.setData(goods);
				 msg.setCode(Apiconstant.Do_Success.getIndex());
			     msg.setMsg(Apiconstant.Do_Success.getName());
			}
		 WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	//05.
	/**
	 * @api {get} notokenapi/app/v1/query_detail.jhtml 添加产品数据
	 * @apiName hlIndex获取商品列表
	 * @apiGroup hl
	 *
	 *@apiParam {String} type_name 材料类型id
	 *@apiParam {String} material_name 材料名称
	 *@apiParam {String} unit 单位
	 *@apiParam {String} brand 品牌
	 *@apiParam {String} specifications_name 规格名称多规格以,隔开
	 *@apiParam {String} feedbackcontent 备注
	 *
	 */
	@RequestMapping(value = "/app/v1/query_detail")
	public void query_detail(HttpServletRequest request, HttpServletResponse response){
		MessageVO msg = new MessageVO();
		String type_name=request.getParameter("type_name");
		String material_name=request.getParameter("material_name");
		String unit=request.getParameter("unit");
		String brand=request.getParameter("brand");
		String specifications_name=request.getParameter("specifications_name");
		String feedbackcontent=request.getParameter("feedbackcontent");
		TGoodsPO goods=new TGoodsPO();
		goods.setId(IdService.uuid());
		goods.setBrand(brand);
		goods.setMaterial_name(material_name);
		goods.setNote(feedbackcontent);
		goods.setType_id(type_name);
		goods.setUnit(unit);
		int row=T_goodsMapper.insert(goods);
		if(row==1){
			String[]  strs=specifications_name.split(",");
			for(int i=0,len=strs.length;i<len;i++){
			   String specifications =strs[i].toString();
			   TSpecificationsPO TSpecificationsPO=new TSpecificationsPO();
			   TSpecificationsPO.setId(IdService.uuid());
			   TSpecificationsPO.setGoods_id(goods.getId());
			   TSpecificationsPO.setSpecifications_name(specifications);
			   T_specificationsMapper.insertAll(TSpecificationsPO);
			   msg.setCode(Apiconstant.Do_Success.getIndex());
			   msg.setMsg(Apiconstant.Do_Success.getName());
			}
		}else {
			msg.setCode(Apiconstant.Do_Fails.getIndex());
	    	msg.setMsg(Apiconstant.Do_Fails.getName());
		}
		 
		 WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	
    //06.
	/**
	 * @api {get} notokenapi/app/v1/con_order.jhtml 获取购物车中的数据数据
	 * @apiName con_order获取购物车中的数据数据
	 * @apiGroup hl
	 *
	 *@apiParam {String} user_id 用户id
	 *
	 */
	@RequestMapping(value = "/app/v1/con_order")
	public void con_order(HttpServletRequest request, HttpServletResponse response){
		 MessageVO msg = new MessageVO();
		 String user_id=request.getParameter("user_id");
		 List<TGoodsPO> goodslist=new ArrayList<TGoodsPO>();
		 List<TCartPO> car=T_cartMapper.list(Dtos.newDto("user_id", user_id));
		 for (int i = 0; i < car.size(); i++) {
			 TGoodsPO goods=new TGoodsPO();
			 List<TSpecificationsPO> TSpecificationsPO=T_specificationsMapper.list(Dtos.newDto("id", car.get(i).getGoods_id()));
			 goods=T_goodsMapper.selectByKey(car.get(i).getGoods_id());
			 goods.setNum(car.get(i).getNum());
			 goods.setCat_id(car.get(i).getId().toString());
			 goods.setSpecificationsPO(TSpecificationsPO);
			 goodslist.add(goods);
		}
		 List<TUserProjectPO> T_user_projectPO=T_user_projectMapper.list(Dtos.newDto("user_id", user_id));
		 List<TProjectPO> list=new ArrayList<TProjectPO>();
		 for (int i = 0; i < T_user_projectPO.size(); i++) {
			 TProjectPO TProjectPO=T_projectMapper.selectOne(Dtos.newDto("id", T_user_projectPO.get(i).getProject_id()));
			 list.add(TProjectPO);
		}
		 if(AOSUtils.isNotEmpty(list)){
			   Map<String, List> map=new HashMap<>();
			   map.put("goodslist", goodslist);
			   map.put("list", list);
			   msg.setData(map);
			   msg.setCode(Apiconstant.Do_Success.getIndex());
			   msg.setMsg(Apiconstant.Do_Success.getName());
		 }else {
				msg.setCode(Apiconstant.Do_Fails.getIndex());
		    	msg.setMsg(Apiconstant.Do_Fails.getName());
			}
		 WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	//07.
	/**
	 * @api {get} notokenapi/app/v1/add_cat.jhtml 添加购物车
	 * @apiName add_cat添加购物车
	 * @apiGroup hl
	 *
	 *@apiParam {String} Goods[{goods_id:商品id,num:数量},{goods_id:商品id,num:数量}]
	 *@apiParam {String} user_id 用户id
	 */
	@RequestMapping(value = "/app/v1/add_cat")
	public void add_cat(HttpServletRequest request, HttpServletResponse response){
		MessageVO msg = new MessageVO();
		String user_id=request.getParameter("user_id");
		String goodJson=request.getParameter("Goods").toString();
		List<T_catVO> goodList=AOSJson.fromJson(goodJson, new TypeToken<List<T_catVO>>(){}.getType());
		 for (T_catVO goods2 : goodList) {
			    TCartPO cat=new TCartPO();
				cat.setGoods_id(goods2.getGoods_id());
		        cat.setNum(goods2.getNum());
		        cat.setUser_id(user_id);
				int row=T_cartMapper.insert(cat);
				 if(row==1){
					    msg.setCode(Apiconstant.Do_Success.getIndex());
				    	msg.setMsg(Apiconstant.Do_Success.getName());
				    	msg.setData("");
				    }else {
				    	msg.setCode(Apiconstant.Do_Fails.getIndex());
				    	msg.setMsg(Apiconstant.Do_Fails.getName());
						msg.setData("");
					}
		 }
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	//08.
	/**
	 * @api {get} notokenapi/app/v1/delete_cat.jhtml 删除购物车
	 * @apiName delete_cat删除购物车
	 * @apiGroup hl
	 *
	 *@apiParam {String} Cat_id 购物车id
	 */
	@RequestMapping(value = "/app/v1/delete_cat")
	public void delete_cat(HttpServletRequest request, HttpServletResponse response){
		MessageVO msg = new MessageVO();
		String Cat_id=request.getParameter("Cat_id");
		int row=T_cartMapper.deleteByKey(Integer.parseInt(Cat_id));
		 if(row==1){
			    msg.setCode(Apiconstant.Do_Success.getIndex());
		    	msg.setMsg(Apiconstant.Do_Success.getName());
		    	msg.setData("");
		    }else {
		    	msg.setCode(Apiconstant.Do_Fails.getIndex());
		    	msg.setMsg(Apiconstant.Do_Fails.getName());
				msg.setData("");
			}
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	//09.
	/**
	 * @api {get} notokenapi/app/v1/update_cat.jhtml 修改购物车
	 * @apiName update_cat修改购物车
	 * @apiGroup hl
	 *
	 *@apiParam {String} Cat_id 购物车id
	 *@apiParam {String} num 数量
	 */
	@RequestMapping(value = "/app/v1/update_cat")
	public void update_cat(HttpServletRequest request, HttpServletResponse response){
		MessageVO msg = new MessageVO();
		String Cat_id=request.getParameter("Cat_id");
		String num=request.getParameter("num");
		TCartPO cat=T_cartMapper.selectByKey(Integer.parseInt(Cat_id));
		cat.setNum(num);
		int row=T_cartMapper.updateByKey(cat);
		 if(row==1){
			    msg.setCode(Apiconstant.Do_Success.getIndex());
		    	msg.setMsg(Apiconstant.Do_Success.getName());
		    	msg.setData("");
		    }else {
		    	msg.setCode(Apiconstant.Do_Fails.getIndex());
		    	msg.setMsg(Apiconstant.Do_Fails.getName());
				msg.setData("");
			}
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	//10.添加购物车
//	/**没改动该接口
//     * 
//     * @catalog JAVA接口文档/hl/APP端
//     * @title 添加购物车（添加了参数）
//     * @description 添加购物车，即添加生成订单，需要填传参数goods_id才能访问接口
//     * @param project_name 必填 String 项目名称
//     * @param user_name 必填 String 用户名称
//     * @param user_id 必填 String 用户ID
//     * @param user_name 必填 String 用户名称
//     * @param goods_id 必填 String（新增） 商品id
//     * @param note 必填 String 备注
//     * @method get
//     * @url notokenapi/app/v1/add_order.jhtml
//     * @return {"code":1,"data":{},"msg":"操作成功"}
//     * @remark 接口由陈绍波编写
//     * @number 100
//     */
	/**
	 * @api {get} notokenapi/app/v1/add_order.jhtml 添加购物车
	 * @apiName add_order添加购物车
	 * @apiGroup hl
	 *
	 *@apiParam {String} project_name 项目名称
	 *@apiParam {String} user_name 用户名称
	 *@apiParam {String} user_id 用户id
	 *@apiParam {String} goods_id 商品id
	 *@apiParam {String} note 备注
	 */
	@RequestMapping(value = "/app/v1/add_order")
	public void add_order(HttpServletRequest request, HttpServletResponse response){
		MessageVO msg = new MessageVO();
		String user_id=request.getParameter("user_id");
		String project_name=request.getParameter("project_name");
		if(AOSUtils.isEmpty(project_name)){
			return;
		}
		String user_name=request.getParameter("user_name");
		String note=request.getParameter("note");
//		String goods_id=request.getParameter("goods_id");
//		if(AOSUtils.isEmpty(goods_id)){
//			return;
//		}
        List<TCartPO> list=T_cartMapper.list(Dtos.newDto("user_id", user_id));
        BigDecimal sum_price=new BigDecimal(0);
        TOrderPO order=new TOrderPO();
    	order.setId(IdService.uuid());
    	order.setNote(note);
    	order.setUser_id(user_id);
    	order.setProject_name(project_name);
    	order.setUser_name(user_name);
    	order.setAdd_date(new Date());
//    	order.setGoods_id(goods_id);
        for (int i = 0; i < list.size(); i++) {
        	TCartPO cat=list.get(i);
        	TSpecificationsPO sp=T_specificationsMapper.selectOne(Dtos.newDto("id",cat.getGoods_id()));
            TOrderDetailedPO order_detaoled=new TOrderDetailedPO();
            order_detaoled.setId(IdService.uuid());
            order_detaoled.setNumber(cat.getNum());
            order_detaoled.setOrder_id(order.getId());
            order_detaoled.setMaterial_name(sp.getSpecifications_name());
            TGoodsPO goods=T_goodsMapper.selectByKey(list.get(i).getGoods_id());
            order_detaoled.setSum_price(sp.getPrice());
            order_detaoled.setAdjust_price(sp.getPrice());
            order_detaoled.setSpecifications_id(sp.getId());
            //sum_price+=Integer.parseInt(list.get(i).getNum())*Integer.parseInt(goods.getPrice());
            sum_price=sum_price.add(new BigDecimal(list.get(i).getNum()).multiply(new BigDecimal(goods.getPrice())));
            T_order_detailedMapper.insert(order_detaoled);
            T_cartMapper.deleteByKey(cat.getId());
		}
    	order.setSum_price(sum_price.toString());
        int row =T_orderMapper.insert(order);
        if(row==1){
		    msg.setCode(Apiconstant.Do_Success.getIndex());
	    	msg.setMsg(Apiconstant.Do_Success.getName());
	    	msg.setData("");
	    }else {
	    	msg.setCode(Apiconstant.Do_Fails.getIndex());
	    	msg.setMsg(Apiconstant.Do_Fails.getName());
			msg.setData("");
		}
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	//11.
//	/**
//     * 
//     * @catalog JAVA接口文档/hl/APP端
//     * @title 查询订单列表
//     * @description 查看该用户所有订单
//     * @param user_id 必填 String 用户ID
//     * @method get
//     * @url notokenapi/app/v1/order_list.jhtml
//     * @return {"id":"63001f81949e4fcebe1942a464c8f426","project_name":"1","user_name":"123456","note":"2019-03-14 09:36:52","add_date":"2019-03-14 09:36:52","material_name":"你好","price":"5.6","OrderDetailedSize":"1","OrderDetailedCash":"691353.6","all_receive_number":4}
//     * @return_param all_receive_number String 实际收货
//     * @remark 接口由陈绍波编写
//     * @number 110
//     */
	/**
	 * @api {get} notokenapi/app/v1/order_list.jhtml 查询订单列表
	 * @apiName order_list查询订单列表
	 * @apiGroup hl
	 *
	 *@apiParam {String} user_id 用户id
	 */
	@RequestMapping(value = "/app/v1/order_list")
	public void order_list(HttpServletRequest request, HttpServletResponse response){
		//1.封装参数
		 MessageVO msg = new MessageVO();
		 String user_id=request.getParameter("user_id");
		 
		 //2.获得订单
		 Map<String, Object> map=new HashMap<>();
		 List<TOrderPO> list=sqlDao.list("com.api.hl.dao.TOrderDao.orderlist", Dtos.newDto("user_id",user_id));
		 List<Dto> listcash=sqlDao.list("com.api.hl.dao.TOrderDao.orderListCash",Dtos.newDto("user_id",user_id));
		 
		 //3.给订单添加其他计算出来的信息
		 List<TOrderPO> order_list=new ArrayList<TOrderPO>();
		 for (int i = 0; i < list.size(); i++) {
			 Dto dto=Dtos.newDto();
			 dto.put("user_id", user_id);//获得用户id
			 dto.put("order_id", list.get(i).getId());//获得订单ID
			 
			 List<TOrderDetailedPO> OrderDetaile=sqlDao.list("com.api.hl.dao.TOrderDetailedDao.order_detailed",dto);
			 List<Dto> cash=sqlDao.list("com.api.hl.dao.TOrderDetailedDao.orderCash",dto);
			 TOrderPO TOrderPO=list.get(i);
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String dateString=formatter.format(TOrderPO.getAdd_date());
			 TOrderPO.setNote(dateString);
			 TOrderPO.setOrderDetailedSize(String.valueOf(OrderDetaile.size()));
			 TOrderPO.setOrderDetailedCash(cash.get(0).getString("sum_price"));
			 order_list.add(TOrderPO);
		}
		 
		 //4.返回数据
		 if(AOSUtils.isNotEmpty(order_list)){
			    map.put("size", list.size());//订单个数
			    map.put("list", list);//订单列表
			    if(AOSUtils.isNotEmpty(listcash)){
			    map.put("sum_price", listcash.get(0).get("sum_price"));//计算订单总额
			    }
			    msg.setCode(Apiconstant.Do_Success.getIndex());
		    	msg.setMsg(Apiconstant.Do_Success.getName());
		    	msg.setData(map);
		    }else {
		    	msg.setCode(Apiconstant.NO_DATA.getIndex());
		    	msg.setMsg(Apiconstant.NO_DATA.getName());
				msg.setData("");
			}
			WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	//12.
	/**
     * showdoc
     * @catalog JAVA接口文档/hl/APP端
     * @title 12.订单详情，记录详情（返回实际收货数量）
     * @description 查看订单详情
     * @param user_id 必填 String 用户id
     * @param order_id 必填 String 订单id
     * @method get
     * @url notokenapi/app/v1/order_detailed.jhtml
     * @return {"code":1,"data":{"sum_price":"0","list":[{"sum_price":"0","material_name":"跳关","price":"5.6","number":"22","order_detailed_id":"2","goodsName":"你好"},{"sum_price":"0","material_name":"钢管","price":"5.6","all_receive_number":8,"number":"1111","order_detailed_id":"e652a7b35f3e4346b6315e8c4b83e713","goodsName":"你好"}],"size":2},"msg":"操作成功"}
     * @return_param order_detailed_id String（新增） 订单详情id
     * @return_param all_receive_number String（新增） 实际收货数量，若该字段不存在则用0替代
     * @return_param goodsName String 商品名称
     * @return_param material_name String 商品型号名称
     * @return_param number String 商下单数量
     * @return_param price String 单价
     * @remark 接口由陈绍波编写
     * @number 120
     */
	/**
	 * @api {get} notokenapi/app/v1/order_detailed.jhtml 订单详情
	 * @apiName order_detailed 订单详情
	 * @apiGroup hl
	 *
	 *@apiParam {String} user_id 用户id
	 *@apiParam {String} order_id 订单id
	 */
	@RequestMapping(value = "/app/v1/order_detailed")
	public void order_detailed(HttpServletRequest request, HttpServletResponse response){
		//0.获得参数
		 MessageVO msg = new MessageVO();
		 String user_id=request.getParameter("user_id");
		 String order_id=request.getParameter("order_id").trim();
		 
		 //1.查询出该订单所有订单明细
		 Map<String, Object> map=new HashMap<>();
		 Dto dto=Dtos.newDto();
		 dto.put("user_id", user_id);
		 dto.put("order_id", order_id);
		 List<Dto> list=sqlDao.list("com.api.hl.dao.TOrderDetailedDao.order_detailed",dto);//改了sql
		 List<Dto> listcash=sqlDao.list("com.api.hl.dao.TOrderDetailedDao.orderCash",dto);
		 
		 //2.返回数据
		 if(AOSUtils.isNotEmpty(list)){
			    map.put("size", list.size());
			    map.put("list", list);
			    map.put("sum_price", listcash.get(0).get("sum_price"));
			    msg.setCode(Apiconstant.Do_Success.getIndex());
		    	msg.setMsg(Apiconstant.Do_Success.getName());
		    	msg.setData(map);
		  }else {
		    	msg.setCode(Apiconstant.Do_Fails.getIndex());
		    	msg.setMsg(Apiconstant.Do_Fails.getName());
				msg.setData("");
		  }
		  WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	
	//13.
	/**
	 * @api {get} notokenapi/app/v1/getGoodsVOByMaterial.jhtml 根据材料名称查询产品集合
	 * @apiName getGoodsVOByMaterial 根据材料名称查询产品集合
	 * @apiGroup hl
	 *
	 *@apiParam {String} id 商品id
	 */
	@RequestMapping(value = "/app/v1/getGoodsVOByMaterial")
	public void getGoodsVOByMaterial(HttpServletRequest request, HttpServletResponse response){
		MessageVO msg = new MessageVO();
		String user_id=request.getParameter("user_id");
		String id = request.getParameter("id");
		TGoodsPO list = T_goodsMapper.selectByKey(id);
		List<TSpecificationsPO> listtype =sqlDao.list("com.api.hl.dao.TSpecificationsDao.listbrand", Dtos.newDto("goods_id",id));
		if(AOSUtils.isNotEmpty(listtype)){
			Map<String, Object> map=new HashMap<>();
		    map.put("list", list);
		    map.put("listtype", listtype);
		    msg.setCode(Apiconstant.Do_Success.getIndex());
	    	msg.setMsg(Apiconstant.Do_Success.getName());
	    	msg.setData(map);
	    }else {
	    	msg.setCode(Apiconstant.NO_DATA.getIndex());
	    	msg.setMsg(Apiconstant.NO_DATA.getName());
			msg.setData("");
		}
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	// 14.notokenapi/app/v1/order_receive_list.jhtml 收货记录
	/**
     * showdoc
     * @catalog JAVA接口文档/hl/APP端
     * @title 14.收货记录（传的参数有改动）
     * @description 查看该用户某订单的所有收货记录
     * @param order_detailed_id 必填 String（新参数） 订单明细id，在标题12处获得
     * @method get
     * @url http://47.111.10.202:8080/aosuite/notokenapi/app/v1/order_receive_list.jhtml?order_detailed_id=63001f81949e4fcebe1942a464c8f426
     * @return {"code":1,"data":[{"id":"d6c7fe845dfc4e59808293680aa7fdb9","order_detailed_id":"1","receive_time":"2019-04-21 14:20:15","receive_number":4,"user_id":"1"},{"id":"e7ab1b7a62e842f9871af81d368fe717","order_detailed_id":"1","receive_time":"2019-04-21 14:20:24","receive_number":4,"user_id":"1"}],"msg":"操作成功"}
     * @return_param receive_number String 收货数量
     * @return_param receive_time String 收货时间
     * @remark 接口由陈绍波编写
     * @number 140
     */
	@RequestMapping(value = "/app/v1/order_receive_list")
	public void order_receive_list(HttpServletRequest request, HttpServletResponse response){
		//1.封装参数
		MessageVO msg = new MessageVO();
		String order_detailed_id=request.getParameter("order_detailed_id");
		 
		//2.根据订单明细ID查询出所有收货记录
		List<TOrderDetailedPO> order_receive_list=sqlDao.list("com.api.hl.dao.TOrderReceiveDao.list", Dtos.newDto("order_detailed_id",order_detailed_id));
		if(AOSUtils.isEmpty(order_receive_list)){
			msg.setCode(Apiconstant.NO_DATA.getIndex());
	    	msg.setMsg(Apiconstant.NO_DATA.getName());
			msg.setData("");
		}else{
			msg.setData(order_receive_list);
			msg.setCode(Apiconstant.Do_Success.getIndex());
	    	msg.setMsg(Apiconstant.Do_Success.getName());
		}
		
		//3.返回数据
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	// 15.notokenapi/app/v1/add_order_receive.jhtml 收货
	/**
     * showdoc
     * @catalog JAVA接口文档/hl/APP端
     * @title 15.收货（传的参数有改动）
     * @description 用户点击收货
     * @param user_id 必填 String 用户ID
     * @param order_detailed_id 必填 String（新参数） 订单详情ID，在标题12处获得
     * @param receive_number 必填 String 实际收货数量，用户输入的值
     * @method get
     * @url notokenapi/app/v1/add_order_receive.jhtml
     * @return {"code":1,"data":{},"msg":"操作成功"}
     * @remark 接口由陈绍波编写
     * @number 150
     */
	@RequestMapping(value = "/app/v1/add_order_receive")
	public void add_order_receive(HttpServletRequest request, HttpServletResponse response){
		//1.返回对象
		MessageVO msg = new MessageVO();
		
		//2.收货逻辑
		msg = LoginService.add_order_receive(request,response);
		
		//3.返回数据
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	//16.出库
	/**
     * showdoc
     * @catalog JAVA接口文档/hl/APP端
     * @title 16.出库（参数有改动）
     * @description 用户点击出库
     * @param reduce_list 必填 String 格式：[{"goods_specifications_id":"1","reduce_number":"2"},{"goods_specifications_id":"1","reduce_number":"2"}]
     * @param goods_specifications_id 必填 String 商品规格ID（这个参数封装在参数reduce_list中），即标题17的中goods_specifications_list中的id
     * @param reduce_number 必填 String 出库数量（这个参数封装在参数reduce_list中），用户输入的值
     * @method get
     * @url notokenapi/app/v1/reduce_repertory.jhtml
     * @return {"code":1,"data":{},"msg":"操作成功"}
     * @remark 接口由陈绍波编写
     * @number 160
     */
	@RequestMapping(value = "/app/v1/reduce_repertory")
	public void reduce_repertory(HttpServletRequest request, HttpServletResponse response){
		//1.返回对象
		MessageVO msg = new MessageVO();
		
		//2.出库逻辑
		msg = LoginService.reduce_repertory(request,response);
		
		//3.返回数据
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	//17.出库（页面初始化）
	/**
	 * showdoc
	 * @catalog JAVA接口文档/hl/APP端
	 * @title 17.出库（页面初始化）
	 * @description 出库（页面初始化）
	 * @param id 必填 String 商品id，获得方式：和请购接口相同
	 * @method get
	 * @url http://47.111.10.202:8080/aosuite/notokenapi/app/v1/reduce_repertory_init.jhtml?id=1
	 * @return {"code":1,"data":{"goods":{"id":"1","type_id":"2","material_name":"你好","unit":"米","price":"5.6","img_src":"http://omhpz0ifi.bkt.clouddn.com/goods1489071130495zjc.jpg","note":"耐用","createTime":"2019-03-12 14:40:47"},"goods_specifications_list":[{"id":"1","goods_id":"1","specifications_name":"钢管","price":"1","brand":"好得很","repertory_number":0},{"id":"5","goods_id":"1","specifications_name":"钢管2","price":"10","brand":"很不错","repertory_number":10}]},"msg":"操作成功"}
	 * @return_param goods String 商品信息，其中【unit代表单位】
	 * @return_param goods_specifications_list String 商品规格信息，其中【repertory_number代表库存数量，specifications_name代表规格名称，id代表商品规格ID】
	 * @remark 接口由陈绍波编写
	 * @number 170
	 */
	@RequestMapping(value = "/app/v1/reduce_repertory_init")
	public void reduce_repertory_init(HttpServletRequest request, HttpServletResponse response){
		//1.返回对象
		MessageVO msg = new MessageVO();
		
		//2.出库页面
		msg = LoginService.reduce_repertory_init(request,response);
		
		//3.返回数据
		WebCxt.write(response, AOSJson.toJson(msg));
	}
}
