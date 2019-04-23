package com.api.hl.service;

import aos.system.common.id.IdService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSUtils;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.hl.dao.TGoodsDao;
import com.api.hl.dao.TOrderDao;
import com.api.hl.dao.TOrderDetailedDao;
import com.api.hl.dao.TOrderReceiveDao;
import com.api.hl.dao.TSpecificationsDao;
import com.api.hl.dao.TTypeDao;
import com.api.hl.dao.TUserhlDao;
import com.api.hl.dao.po.TGoodsPO;
import com.api.hl.dao.po.TOrderDetailedPO;
import com.api.hl.dao.po.TOrderPO;
import com.api.hl.dao.po.TOrderReceivePO;
import com.api.hl.dao.po.TSpecificationsPO;
import com.api.hl.dao.po.TTypePO;
import com.api.hl.dao.po.TUserhlPO;
import com.api.order.dao.po.Json;
import com.gexin.fastjson.JSON;



@Service(value="LoginService")
@Transactional
public class LoginService {
	private static final String Map = null;
	@Autowired
	private TUserhlDao T_userhlMapper;
	@Autowired
	private TGoodsDao T_goodsMapper;
	
	@Autowired
	private TTypeDao T_typeMapper;
	@Autowired
	private TSpecificationsDao T_specificationsMapper;
	
	@Autowired
	private TOrderReceiveDao tOrderReceiveMapper;
	@Autowired
	private IdService IdService;
	@Autowired
	private TOrderDao tOrderMapper;
	@Autowired
	private TOrderDetailedDao tOrderDetailedMapper;
	@Autowired
	private SqlDao sqlDao;
	
	
		public TUserhlPO storeLogin(String phone){
			Dto t=Dtos.newDto();
			t.put("phone", phone);
			//t.put("password", password);
			TUserhlPO user=T_userhlMapper.selectOne(t);
			return user;
		}
		
		public List<TGoodsPO> getgoods(){
			List<TGoodsPO> list=T_goodsMapper.list(null);
			return list;
		}
		
		public List<TGoodsPO> getgoods(String type){
			List<TGoodsPO> list=T_goodsMapper.list(Dtos.newDto("type_id", type));
			return list;
		}
		
		public List<TTypePO> getgoodsType(){
			List<TTypePO> list=T_typeMapper.list(null);
			return list;
		}
		
		

		public List<TSpecificationsPO> getgoodsSpecifications(String id){
			List<TSpecificationsPO> list=T_specificationsMapper.list(Dtos.newDto("goods_id", id));
			return list;
		}
		
		/**
		 * 根据类型查询不重复产品集合
		 * @param type
		 * @return
		 */
		public List<TGoodsPO> getNoRepeatGoods(String type){
			List<TGoodsPO> list=T_goodsMapper.list(Dtos.newDto("type_id", type));
			return list;
		}
		
		/**
		 * 15.收货
		 */
		public MessageVO add_order_receive(HttpServletRequest request, HttpServletResponse response){
			MessageVO msg = new MessageVO();
			try {
				//0.必填参数不能为空
				String user_id=request.getParameter("user_id");
				String order_detailed_id=request.getParameter("order_detailed_id");
				String receive_number=request.getParameter("receive_number");
				if(AOSUtils.isEmpty(user_id) || AOSUtils.isEmpty(order_detailed_id) || AOSUtils.isEmpty(receive_number)){
					msg.setCode(Apiconstant.Condition_Is_Null.getIndex());
					msg.setMsg(Apiconstant.Condition_Is_Null.getName());
					return msg;
				}
				
				//1.在商品表中加库存数量
				//根据订单明细id查到订单明细
				TOrderDetailedPO tOrderDetailedPO = tOrderDetailedMapper.selectByKey(order_detailed_id);
				if(tOrderDetailedPO==null){
					msg.setMsg("操作失败，订单明细编号"+order_detailed_id+"不存在");
					return msg;
				}
				//根据商品规格id找到商品
				String specifications_id = tOrderDetailedPO.getSpecifications_id();
				TSpecificationsPO tSpecificationsPO = T_specificationsMapper.selectByKey(specifications_id);
				if(tSpecificationsPO==null){
					msg.setMsg("操作失败，商品规格"+specifications_id+"不存在");
					return msg;
				}
				tSpecificationsPO.setRepertory_number(tSpecificationsPO.getRepertory_number() + Integer.valueOf(receive_number));
				//修改库存数量
				T_specificationsMapper.updateByKey(tSpecificationsPO);
				
				//2.插入一条收货数据，相当于日志表
				TOrderReceivePO tOrderReceivePO = new TOrderReceivePO();
				tOrderReceivePO.setId(IdService.uuid());
				tOrderReceivePO.setOrder_detailed_id(order_detailed_id);
				tOrderReceivePO.setUser_id(user_id);
				tOrderReceivePO.setReceive_number(Integer.valueOf(receive_number));
				tOrderReceivePO.setReceive_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				tOrderReceiveMapper.insert(tOrderReceivePO);
				
				//3.返回数据
				msg.setCode(Apiconstant.Do_Success.getIndex());
				msg.setMsg(Apiconstant.Do_Success.getName());
			} catch (Exception e) {
				e.printStackTrace();
				msg.setCode(Apiconstant.Do_Fails.getIndex());
		    	msg.setMsg(Apiconstant.Do_Fails.getName());
				msg.setData("");
				//手动回滚事务
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			return msg;
		}

		/**
		 * 16.出库
		 */
		public MessageVO reduce_repertory(HttpServletRequest request, HttpServletResponse response){
			MessageVO msg = new MessageVO();
			try {
				//0.接收参数
				String reduce_list=request.getParameter("reduce_list");
				
				//1.转为List
				List<Object> list = JSON.parseArray(reduce_list);
				//装在List<Map>中
				List< Map<String,Object>> listw = new ArrayList<Map<String,Object>>();
				for (Object object : list){
			        Map<String,Object> ageMap = new HashMap<String,Object>();
			        Map <String,Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
			        listw.add(ret);
			    }
				//2.遍历listw，进行相关逻辑
				for (int i = 0; i < listw.size(); i++) {
					//2.1参数准备
					String goods_specifications_id = listw.get(i).get("goods_specifications_id").toString();
					String reduce_number = listw.get(i).get("reduce_number").toString();
					
					//2.2参数不能为空
					if(AOSUtils.isEmpty(goods_specifications_id) || AOSUtils.isEmpty(reduce_number)){
						msg.setCode(Apiconstant.Condition_Is_Null.getIndex());
						msg.setMsg(Apiconstant.Condition_Is_Null.getName());
						return msg;
					}
					
					//2.3业务逻辑，在商品-规格表中加库存数量
					//根据商品id找到商品
					TSpecificationsPO tSpecificationsPO = T_specificationsMapper.selectByKey(goods_specifications_id);
					if(tSpecificationsPO==null){
						msg.setMsg("操作失败，商品规格"+goods_specifications_id+"不存在");
						return msg;
					}
					tSpecificationsPO.setRepertory_number(tSpecificationsPO.getRepertory_number() - Integer.valueOf(reduce_number));
					//修改库存数量
					T_specificationsMapper.updateByKey(tSpecificationsPO);
				}
				
				//3.返回数据
				msg.setCode(Apiconstant.Do_Success.getIndex());
				msg.setMsg(Apiconstant.Do_Success.getName());
			} catch (Exception e) {
				e.printStackTrace();
				msg.setCode(Apiconstant.Do_Fails.getIndex());
		    	msg.setMsg(Apiconstant.Do_Fails.getName());
				msg.setData("");
				//手动回滚事务
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			return msg;
		}
		
		/**
		 * 17.出库（页面初始化）
		 */
		public MessageVO reduce_repertory_init(HttpServletRequest request, HttpServletResponse response){
			MessageVO msg = new MessageVO();
			try {
				//1.根据商品id查询出该商品
				String id = request.getParameter("id");
				TGoodsPO goods = T_goodsMapper.selectByKey(id);
				
				//2.根据商品id查询出该商品的所有规格
				List<TSpecificationsPO> goods_specifications_list =sqlDao.list("com.api.hl.dao.TSpecificationsDao.listbrand", Dtos.newDto("goods_id",id));
				
				//3.封装数据
				if(AOSUtils.isNotEmpty(goods_specifications_list)){
					Map<String, Object> map=new HashMap<>();
				    map.put("goods", goods);//商品信息
				    map.put("goods_specifications_list", goods_specifications_list);//商品规格
				    msg.setCode(Apiconstant.Do_Success.getIndex());
			    	msg.setMsg(Apiconstant.Do_Success.getName());
			    	msg.setData(map);
			    }else {
			    	msg.setCode(Apiconstant.NO_DATA.getIndex());
			    	msg.setMsg(Apiconstant.NO_DATA.getName());
					msg.setData("");
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg.setCode(Apiconstant.Do_Fails.getIndex());
		    	msg.setMsg(Apiconstant.Do_Fails.getName());
				msg.setData("");
				//手动回滚事务
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			return msg;
		}
		
}



















