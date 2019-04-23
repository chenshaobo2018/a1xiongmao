/**
 * 
 */
package com.zjc.order.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.xml.sax.InputSource;

import com.api.ApiPublic.Apiconstant;
import com.api.cfca.controller.MerchantX509Cert;
import com.api.common.po.MessageVO;
import com.api.common.util.ZjcTurnIntoUtil;
import com.zjc.common.po.ExportExcelInfo;
import com.zjc.common.util.ExportExcle;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.ZjcDeliveryDocDao;
import com.zjc.order.dao.ZjcOrderActionDao;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcOrderGoodsDao;
import com.zjc.order.dao.po.ZjcDeliveryDocPO;
import com.zjc.order.dao.po.ZjcOrderActionPO;
import com.zjc.order.dao.po.ZjcOrderExportVO;
import com.zjc.order.dao.po.ZjcOrderGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.system.dao.po.ZjcMemberOtherPO;
import com.zjc.users.dao.ZjcQueueDao;
import com.zjc.users.dao.ZjcUserLogDao;
import com.zjc.users.dao.ZjcUserOperateLogDao;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.po.ZjcQueuePO;
import com.zjc.users.dao.po.ZjcUserLogPO;
import com.zjc.users.dao.po.ZjcUserOperateLogPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;
/**
 * @author wugaoming 
 *
 */
@Service(value="zjcOrderService")
public class ZjcOrderService {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	@Autowired
	private ZjcGoodsDao ZjcGoodsDao;
	@Autowired
	private ZjcOrderGoodsDao zjcOrderGoodsDao;
	@Autowired
	private ZjcStoreDao ZjcStoreDao;
	
	@Autowired
	private ZjcQueueDao ZjcQueueDao;
	@Autowired
	private ZjcOrderActionDao zjcOrderActionDao;
	
	@Autowired
	private ZjcDeliveryDocDao zjcDeliveryDocDao;
	
	
	@Autowired
	private ZjcUserOperateLogDao zjcUserOperateLogDao;
	
	@Autowired
	private ZjcMemberOtherDao zjcMemberOtherDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	
	@Autowired
	private ZjcUserLogDao userLogDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ZjcTurnIntoUtil zjcTurnIntoUtil;
	private static final Logger logger = LoggerFactory
			.getLogger(ZjcOrderService.class);
	
	/**
	 * 初始化后台管理订单管理订单列表页面
	 * @param httpModel
	 */
	public void initOrderListPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/orderList.jsp");
	}
	/**
	 * 订单列表查询
	 * @param httpModel
	 */
	public void initOrderListPageQuery(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/orderListQuery.jsp");
	}
	
	/**
	 * 首信易订单列表查询
	 * @param httpModel
	 */
	public void initOrderSXYPageQuery(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/orderSXYQuery.jsp");
	}
	
	/**
	 * 初始化系统后台混合支付未支付订单管理列表页面
	 * 
	 * @param httpModel
	 */
	public void initMixNoPayOrderListPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/mixNoPayOrderList.jsp");
	}
	/**
	 * 初始化系统后台混合支付订单管理列表页面
	 * 
	 * @param httpModel
	 */
	public void initMixOrderListPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/mixOrderList.jsp");
	}
	
	/**
	 * 初始化系统后台现金支付未支付订单管理列表页面
	 * 
	 * @param httpModel
	 */
	public void initCashNoPayOrderListPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/cashNoPayOrderList.jsp");
	}
	
	
	/**
	 * 初始化系统后台混合支付订单管理列表页面
	 * 
	 * @param httpModel
	 */
	public void initCashOrderListPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/order/cashOrderList.jsp");
	}
	
	
	/**
	 * 查询后台管理订单管理订单列表
	 * 
	 * @param httpModel
	 */
	public void listZjcOrders(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		inDto.put("goods_name", inDto.getString("goods_name").trim());
		inDto.put("store_name", inDto.getString("store_name").trim());
		inDto.put("consignee", inDto.getString("consignee").trim());
		inDto.put("order_sn", inDto.getString("order_sn").trim());
		List<Dto> zjcOrderPOList = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.listZjcOrdersPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(zjcOrderPOList, inDto.getPageTotal()));
	}
	
	public void listZjcSXYOrders(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		String vod=inDto.getString("v_oid").trim();
		List<Dto> zjcOrderPOList=new ArrayList<Dto>();
		if(AOSUtils.isNotEmpty(vod)){
			String voidsz[]=vod.split("\\n");
			for (int i = 0; i < voidsz.length; i++) {
				String value=voidsz[i];
				String[] strs=value.split("-");
				/*for(int j=0,len=strs.length;j<len;j++){
				  System.out.println(strs[2].toString());
				}*/
				inDto.put("order_id",strs[2].toString());
				inDto.put("goods_name", inDto.getString("goods_name").trim());
				inDto.put("store_name", inDto.getString("store_name").trim());
				inDto.put("consignee", inDto.getString("consignee").trim());
				inDto.put("order_sn", inDto.getString("order_sn").trim());
				inDto.put("v_oid","");
				Dto Dto =  (aos.framework.core.typewrap.Dto) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.listZjcSXYOrdersPage", inDto);
				if(AOSUtils.isNotEmpty(Dto)){
					zjcOrderPOList.add(Dto);
				}
				
			}
		}else {
			inDto.put("goods_name", inDto.getString("goods_name").trim());
			inDto.put("store_name", inDto.getString("store_name").trim());
			inDto.put("consignee", inDto.getString("consignee").trim());
			inDto.put("order_sn", inDto.getString("order_sn").trim());
			zjcOrderPOList = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.listZjcSXYOrdersPage", inDto);
		}
		httpModel.setOutMsg(AOSJson.toGridJson(zjcOrderPOList, inDto.getPageTotal()));
	}
	
	/**
	 * 删除订单信息
	 * 
	 * @param httpModel
	 */
	public void delOrderInfo(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(inDto.getInteger("order_id"));
		zjcOrderPO.setIs_del(1);//删除状态改为已删除
		zjcOrderPO.setOrder_status(5);//订单状态改为已作废
		zjcOrderDao.updateByKey(zjcOrderPO);
		
		httpModel.setOutMsg("订单信息删除成功");
	}
	
	/**
	 * 根据条件导出订单信息
	 * 
	 * @param httpModel
	 */
	@SuppressWarnings("rawtypes")
    public void exportOrderInfo(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		try {
			if(AOSUtils.isNotEmpty(inDto.getString("goods_name"))){
					inDto.put("goods_name", URLDecoder.decode(inDto.getString("goods_name"), "UTF-8"));
			}
			if(AOSUtils.isNotEmpty(inDto.getString("store_name"))){
				inDto.put("store_name", URLDecoder.decode(inDto.getString("store_name"), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpServletRequest req = httpModel.getRequest();
		try {
			req.setCharacterEncoding("utf-8");
			HttpServletResponse resp = httpModel.getResponse();
	        String title = "订单信息";
	        String[] strHeader =
	            {"商家ID","订单编号","店铺名称", "商品名称", "商家电话", "商品数量","特殊商品","买家ID",  "收货人", "联系电话", "现金", "券", "订单状态", "支付状态", "支付方式", "发货状态","下单时间", "发货时间", "收货时间","收货地址"};
	        String[] strField =
	            {"seller_id", "order_sn","store_name","goods_name","seller_mobile","goods_num","is_car", "user_id", "consignee", "contract_mobile", "cash", "barter_coupons", "order_status",
	        		"pay_status", "pay_name", "shipping_status","add_time","shipping_time", "confirm_time","address"};
	        //根据条件查询待导出订单数据
	        List list = zjcOrderDao.findExportData(inDto);
	        ExportExcelInfo paramExcelInfo = new ExportExcelInfo();
	        paramExcelInfo.setObjList(list);
	        paramExcelInfo.setTitle(title);
	        paramExcelInfo.setStrHeader(strHeader);
	        paramExcelInfo.setStrField(strField);
	        paramExcelInfo.setReq(req);
	        paramExcelInfo.setResp(resp);
	        //导出excel
	        boolean b =ExportExcle.exportExcel(paramExcelInfo);
	        if(b){
	        	httpModel.setOutMsg("订单信息导出成功");
	        } else {
	        	httpModel.setOutMsg("订单信息导出失败");
	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
	
	
	/**
	 * 根据条件导出订单信息
	 * 
	 * @param httpModel
	 */
	@SuppressWarnings("rawtypes")
    public void exportSXYOrderInfo(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		try {
			if(AOSUtils.isNotEmpty(inDto.getString("goods_name"))){
					inDto.put("goods_name", URLDecoder.decode(inDto.getString("goods_name"), "UTF-8"));
			}
			if(AOSUtils.isNotEmpty(inDto.getString("store_name"))){
				inDto.put("store_name", URLDecoder.decode(inDto.getString("store_name"), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpServletRequest req = httpModel.getRequest();
		try {
			req.setCharacterEncoding("utf-8");
			HttpServletResponse resp = httpModel.getResponse();
	        String title = "首信易订单信息";
	        String[] strHeader =
	            {"首信易ID","商家ID","订单编号","店铺名称", "商品名称", "商家电话", "商品数量","特殊商品","买家ID",  "收货人", "联系电话", "现金", "券", "订单状态", "支付状态", "支付方式", "发货状态","下单时间", "发货时间", "收货时间"};
	        String[] strField =
	            {"v_oid","seller_id", "order_sn","store_name","goods_name","seller_mobile","goods_num","is_car", "user_id", "consignee", "contract_mobile", "cash", "barter_coupons", "order_status",
	        		"pay_status", "pay_name", "shipping_status","add_time","shipping_time", "confirm_time"};
	        //根据条件查询待导出订单数据
	        List<ZjcOrderExportVO> list=new ArrayList<ZjcOrderExportVO>();
	        String vod=inDto.getString("v_oid").trim();
	        inDto.put("goods_name", inDto.getString("goods_name").trim());
			inDto.put("store_name", inDto.getString("store_name").trim());
			inDto.put("consignee", inDto.getString("consignee").trim());
			inDto.put("order_sn", inDto.getString("order_sn").trim());
			if(AOSUtils.isNotEmpty(vod)){
				String voidsz[]=vod.split(",");
				for (int i = 0; i < voidsz.length; i++) {
					String value=voidsz[i];
					String[] strs=value.split("-");
					inDto.put("order_id",strs[2].toString());
					inDto.put("v_oid","");
					ZjcOrderExportVO ZjcOrderExportVO = (com.zjc.order.dao.po.ZjcOrderExportVO) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.findSXYExportData", inDto);
					if(AOSUtils.isNotEmpty(ZjcOrderExportVO)){
						list.add(ZjcOrderExportVO);
					}
					
				}
			}else {
				list =  sqlDao.list("com.zjc.order.dao.ZjcOrderDao.findSXYExportData", inDto);
			}
	        ExportExcelInfo paramExcelInfo = new ExportExcelInfo();
	        paramExcelInfo.setObjList(list);
	        paramExcelInfo.setTitle(title);
	        paramExcelInfo.setStrHeader(strHeader);
	        paramExcelInfo.setStrField(strField);
	        paramExcelInfo.setReq(req);
	        paramExcelInfo.setResp(resp);
	        //导出excel
	        boolean b =ExportExcle.exportExcel(paramExcelInfo);
	        if(b){
	        	httpModel.setOutMsg("订单信息导出成功");
	        } else {
	        	httpModel.setOutMsg("订单信息导出失败");
	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * 后台管理订单管理根据订单ID查询商品信息
	 * @param httpModel
	 */
	public void getGoodsByOrderId(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<ZjcOrderGoodsPO> zjcOrderGoodsPOList = zjcOrderGoodsDao.list(inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(zjcOrderGoodsPOList, inDto.getPageTotal()));
	} 
	
	/**
	 * 后台管理订单管理根据订单ID查询商品信息
	 * @param httpModel
	 */
	public void getGoodsByOrderId2(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<Dto> zjcOrderGoodsPOList = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.list2", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(zjcOrderGoodsPOList, inDto.getPageTotal()));
	} 
	
	/**
	 * 返回统计汇总对象(商品小计)
	 * @param httpModel
	 */
	public void querySummary(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		//根据inDto参数去统计相关信息
		Dto outDto = sqlDao.selectDto("com.zjc.order.dao.ZjcOrderGoodsDao.querySummary", inDto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 后台管理订单管理根据订单ID查询操作记录
	 * @param httpModel
	 */
	public void getOrderActionByOrderId(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<ZjcOrderActionPO> zjcOrderActionPOList = zjcOrderActionDao.listPage(inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(zjcOrderActionPOList, inDto.getPageTotal()));
	}
	
	/**
	 * 后台管理订单管理待确认状态订单的订单状态修改
	 * 
	 * @param httpModel
	 */
	public void updateOrderStatus(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		//通过订单ID查询订单信息数据
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(inDto.getInteger("order_id"));
		//查询订单状态信息表
		List<ZjcOrderActionPO> zjcOrderActionPOList = zjcOrderActionDao.list(inDto);
		
		if(StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)){
			zjcOrderPO.setOrder_status(inDto.getInteger("order_status"));
			zjcOrderDao.updateByKey(zjcOrderPO);
			if(zjcOrderActionPOList != null && StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)){
				ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
				zjcOrderActionPONew.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				zjcOrderActionPONew.setAction_note(inDto.getString("action_note"));
				zjcOrderActionPONew.setAction_user(BigInteger.valueOf(httpModel.getUserModel().getId()));
				zjcOrderActionPONew.setAction_user_name(httpModel.getUserModel().getName());
				zjcOrderActionPONew.setAction_user_type(Integer.getInteger(httpModel.getUserModel().getType()));
				zjcOrderActionPONew.setLog_time(new Date());
				zjcOrderActionPONew.setOrder_id(inDto.getInteger("order_id"));
				zjcOrderActionPONew.setOrder_status(inDto.getInteger("order_status"));
				zjcOrderActionPONew.setPay_status(1);
				zjcOrderActionPONew.setShipping_status(inDto.getInteger("shipping_status"));
				zjcOrderActionPONew.setStatus_desc("确认订单");
				zjcOrderActionDao.insert(zjcOrderActionPONew);
				outDto.setAppMsg("确认订单成功");
			} else {
				outDto.setAppMsg("确认订单失败");
			}
			
		} else {
			outDto.setAppMsg("确认订单失败");
		}
		
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 订单发货页面查询订单基本信息
	 * 
	 * @param httpModel
	 */
	public void getZjcOrderByOrderId(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(inDto.getInteger("order_id"));
		httpModel.setOutMsg(AOSJson.toJson(zjcOrderPO));
	}
	
	/**
	 * 配货单
	 * 
	 * @param httpModel
	 */
	public void getZjcOrderInfoByOrderId(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		Dto zjcOrderPO = zjcOrderDao.getZjcOrderInfoByOrderId(inDto.getInteger("order_id"));
		httpModel.setOutMsg(AOSJson.toJson(zjcOrderPO));
	}
	
	/**
	 * 通过order_id查询发货记录
	 * 
	 * @param httpModel
	 */
	public void getZjcDeliveryByOrderId(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		ZjcDeliveryDocPO zjcDeliveryDocPO = zjcDeliveryDocDao.getZjcDeliveryByOrderId(inDto.getInteger("order_id"));
		httpModel.setOutMsg(AOSJson.toJson(zjcDeliveryDocPO));
	}
	
	/**
	 * 订单发货
	 * 
	 * @param httpModel
	 */
	public void delivery(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		//通过订单ID查询订单信息数据
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(inDto.getInteger("order_id"));
		//通过订单ID查询订单发货信息
		ZjcDeliveryDocPO zjcDeliveryDocPO = zjcDeliveryDocDao.getZjcDeliveryByOrderId(inDto.getInteger("order_id"));
		if(StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)){
			//修改订单状态
			zjcOrderPO.setShipping_status(1);//状态改为已发货
			zjcOrderPO.setShipping_code(inDto.getString("shipping_code"));
			zjcOrderPO.setShipping_time(new Date());
			zjcOrderPO.setTracking_no(inDto.getString("invoice_no"));
			zjcOrderPO.setOrder_status(1);
			//zjcOrderPO.setPay_status(1);
			zjcOrderDao.updateByKey(zjcOrderPO);
			//新增订单状态记录
			ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
			zjcOrderActionPONew.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			zjcOrderActionPONew.setAction_note(inDto.getString("note"));
			zjcOrderActionPONew.setAction_user(BigInteger.valueOf(httpModel.getUserModel().getId()));
			zjcOrderActionPONew.setAction_user_name(httpModel.getUserModel().getName());
			zjcOrderActionPONew.setAction_user_type(Integer.getInteger(httpModel.getUserModel().getType()));
			zjcOrderActionPONew.setLog_time(new Date());
			zjcOrderActionPONew.setOrder_id(inDto.getInteger("order_id"));
			zjcOrderActionPONew.setOrder_status(1);
			zjcOrderActionPONew.setPay_status(1);
			zjcOrderActionPONew.setShipping_status(1);
			zjcOrderActionPONew.setStatus_desc("确认发货");
			zjcOrderActionDao.insert(zjcOrderActionPONew);
			//新增发货单记录
			if(zjcDeliveryDocPO==null){
				zjcDeliveryDocPO = new ZjcDeliveryDocPO();
				zjcDeliveryDocPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				zjcDeliveryDocPO.copyProperties(zjcOrderPO);
				zjcDeliveryDocPO.setNote(inDto.getString("note"));
				zjcDeliveryDocPO.setInvoice_no(inDto.getString("invoice_no"));
				zjcDeliveryDocPO.setShipping_code(inDto.getString("shipping_code"));
				zjcDeliveryDocPO.setCreate_time(new Date());
				zjcDeliveryDocPO.setIs_del(0);
				zjcDeliveryDocDao.insert(zjcDeliveryDocPO);
				//修改订单商品发货状态
				List<ZjcOrderGoodsPO> zjcOrderGoodsPOList = zjcOrderGoodsDao.getOrderGoodsByOrderId(inDto.getInteger("order_id"));
				if(null != zjcOrderGoodsPOList){
					for(ZjcOrderGoodsPO zjcOrderGoodsPO : zjcOrderGoodsPOList){
						zjcOrderGoodsPO.setDelivery_id(zjcDeliveryDocPO.getId());
						zjcOrderGoodsPO.setIs_send(1);
						zjcOrderGoodsDao.updateByKey(zjcOrderGoodsPO);
					}
				}
			} 
			outDto.setAppMsg("订单发货成功");
		} else {
			outDto.setAppMsg("订单发货失败");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 确认混合支付付款完成
	 * 
	 * @param httpModel
	 */
	@Transactional(rollbackFor=Exception.class)
	public void confirmMixedPay(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(inDto.getInteger("order_id"));
		zjcOrderPO.setPay_status(1);//现金已支付成功
		zjcOrderPO.setOrder_status(0);//订单状态改为待确认
		try{
			zjcOrderDao.updateByKey(zjcOrderPO);
			if(!"rate".equals(zjcOrderPO.getPay_code()) && !"mixed_payment".equals(zjcOrderPO.getPay_code())){//生产用户商城购买记录
				zjcTurnIntoUtil.shop_order(inDto.getInteger("order_id")); //商城购物记录发送
			}
			//生成订单状态记录
			ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
			zjcOrderActionPONew.setAction_note("管理员："+ httpModel.getUserModel().getName() + "确认了订单编号：" + zjcOrderPO.getOrder_sn()+ "已支付");
			zjcOrderActionPONew.setAction_user(BigInteger.valueOf(httpModel.getUserModel().getId()));
			zjcOrderActionPONew.setAction_user_name(httpModel.getUserModel().getName());
			zjcOrderActionPONew.setAction_user_type(Integer.getInteger(httpModel.getUserModel().getType()));
			zjcOrderActionPONew.setLog_time(new Date());
			zjcOrderActionPONew.setOrder_id(inDto.getInteger("order_id"));
			zjcOrderActionPONew.setOrder_status(0);
			zjcOrderActionPONew.setPay_status(1);
			zjcOrderActionPONew.setShipping_status(0);
			zjcOrderActionPONew.setStatus_desc("确认支付");
			zjcOrderActionDao.insert(zjcOrderActionPONew);
			
			//boolean is_car = false;
			int delay_days = 0;//返利延迟天数
	/*		List<ZjcOrderGoodsPO> ordergoods = zjcOrderGoodsDao.list(Dtos.newDto("order_id", zjcOrderPO.getOrder_id()));
			for(ZjcOrderGoodsPO orderGood :ordergoods){
				ZjcGoodsPO goods = zjcGoodsDao.selectByKey(orderGood.getGoods_id());
				if(goods.getIs_car()==1){
					is_car = true;
				}
			}
			if(is_car){//特殊商品
*/				//查询特殊商品返利延迟天数
				ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
				delay_days= Integer.parseInt(zjcMemberOtherPO.getSpecial_rebate_delay_days());
				inDto.put("delay_days", delay_days);
				sqlDao.update("com.zjc.users.dao.ZjcQueueDao.requeueByRelationId", inDto);
			    sqlDao.update("com.zjc.users.dao.ZjcQueueDao.updateUserByRelationId", inDto);//修改会员返分状态
			//}
			//生成操作日志
			ZjcUserOperateLogPO operateLog = new ZjcUserOperateLogPO();
			int user_id = httpModel.getUserModel().getId();
			if(user_id == -1){
				user_id = 1111;
			}
			operateLog.setUser_id(user_id);
			operateLog.setUser_name(httpModel.getUserModel().getName());
			operateLog.setOperate_time(new Date());
			operateLog.setDescs("管理员："+ httpModel.getUserModel().getName() + "确认了订单编号：" + zjcOrderPO.getOrder_sn()+ "已支付");
			operateLog.setOperate_type("确认支付成功");
			zjcUserOperateLogDao.insert(operateLog);
			 ZjcStorePO ZjcStorePO=ZjcStoreDao.selectByKey(zjcOrderPO.getStore_id());
			 List<ZjcOrderGoodsPO> ZjcOrderGoodsPO = zjcOrderGoodsDao.list(Dtos.newDto("order_id",zjcOrderPO.getOrder_id()));
			  if("mixed_payment".equals(zjcOrderPO.getPay_code())){//易物券支付周期不返还，则不进行返分
				  for (int i = 0; i < ZjcOrderGoodsPO.size(); i++) {
					  ZjcGoodsPO ZjcGoodsPO=ZjcGoodsDao.selectByKey(ZjcOrderGoodsPO.get(i).getGoods_id());
					  if(ZjcGoodsPO.getIs_car()==1){//混合支付并且是特殊商品
						//根据权重算出要返回的
							 ZjcUsersAccountInfoPO user=new ZjcUsersAccountInfoPO();
							 user.setUser_id(ZjcStorePO.getUser_id());
							 //商家
							 List<ZjcUsersAccountInfoPO> list1=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
							 int total=new BigDecimal(zjcMemberOtherPO.getConsumption_coupons()).divide(new BigDecimal("100")).multiply(zjcOrderPO.getBarter_coupons()).intValue();
							 ZjcQueuePO queue=new ZjcQueuePO();
							 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							 Date date= df.parse("2033-5-18 11:33:19");
							 //设置26天后自动返分
						     Date newdate=zjcOrderPO.getAdd_time();  
						     Calendar calendar = Calendar.getInstance();  
						     calendar.setTime(newdate);  
						     calendar.add(Calendar.DAY_OF_MONTH, +26);  
						     date = calendar.getTime();  
							 queue.setAdd_time(df.parse(df.format(date)));
							 queue.setSend_time(new Date());
							 queue.setNote("消费后，将滴返回商家");
							 queue.setType(4);
							 queue.setUser_id(list1.get(0).getUser_id());
							 queue.setRelation_id(zjcOrderPO.getOrder_id());
							 queue.setXf_points(total);
							 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
							 ZjcQueueDao.insert(queue);
					  }else if("mixed_payment".equals(zjcOrderPO.getPay_code())&&ZjcGoodsPO.getIs_car()==0){//混合支付但不是特殊商品
						  //根据权重算出要返回的
							 ZjcUsersAccountInfoPO user=new ZjcUsersAccountInfoPO();
							 user.setUser_id(ZjcStorePO.getUser_id());
							 //商家
							 List<ZjcUsersAccountInfoPO> list1=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
							 int total=new BigDecimal(zjcMemberOtherPO.getConsumption_coupons()).divide(new BigDecimal("100")).multiply(zjcOrderPO.getBarter_coupons()).intValue();
							 ZjcQueuePO queue=new ZjcQueuePO();
							 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							 Date date= df.parse("2033-5-18 11:33:19");
							//设置26天后自动返分
						     Date newdate=zjcOrderPO.getAdd_time();  
						     Calendar calendar = Calendar.getInstance();  
						     calendar.setTime(newdate);  
						     calendar.add(Calendar.DAY_OF_MONTH, +26);  
						     date = calendar.getTime();  
							 queue.setAdd_time(df.parse(df.format(date)));
							 queue.setSend_time(new Date());
							 queue.setNote("消费后，将滴返回商家");
							 queue.setType(4);
							 queue.setUser_id(list1.get(0).getUser_id());
							 queue.setRelation_id(zjcOrderPO.getOrder_id());
							 queue.setXf_points(total);
							 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
							 ZjcQueueDao.insert(queue);
							 /*//商家立即到账50%
							 BigDecimal totals=new BigDecimal(zjcMemberOtherPO.getShop_drop_commission_rate()).multiply(zjcOrderPO.getCash());
							 list1.get(0).setDrops(totals.intValue());
							 zjcUsersAccountInfoDao.updateByKey(list1.get(0));
							//会员立即到账50%
							 user.setUser_id(zjcOrderPO.getUser_id());
							 List<ZjcUsersAccountInfoPO> userinfo=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
							 BigDecimal t=new BigDecimal(zjcMemberOtherPO.getUser_drop_commission_rate()).multiply(zjcOrderPO.getCash());
							 userinfo.get(0).setDrops(t.intValue());
							 zjcUsersAccountInfoDao.updateByKey(userinfo.get(0));*/
					  }
				  }
				}
			httpModel.setOutMsg("确认付款成功");
		} catch (Exception e){
			httpModel.setOutMsg("确认付款失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 多条订单记录确认混合支付付款完成
	 * 
	 * @param httpModel
	 */
	public void confirmAllMixedPay(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		//获取选中的订单id集合
		String[] selectionIds = inDto.getRows();
		try{
			int rows = 0;
			int delay_days = 0;//返利延迟天数
			for(String order_id : selectionIds){
				ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(Integer.parseInt(order_id));
				zjcOrderPO.setPay_status(1);//现金已支付成功
				zjcOrderPO.setOrder_status(0);//订单状态改为待确认
				zjcOrderDao.updateByKey(zjcOrderPO);
				
				if(!"rate".equals(zjcOrderPO.getPay_code()) && !"mixed_payment".equals(zjcOrderPO.getPay_code())){//生产用户商城购买记录
					zjcTurnIntoUtil.shop_order(inDto.getInteger("order_id")); //商城购物记录发送
				}
				
				//生成订单状态记录
				ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
				zjcOrderActionPONew.setAction_note("管理员："+ httpModel.getUserModel().getName() + "确认了订单编号：" + zjcOrderPO.getOrder_sn()+ "已支付");
				zjcOrderActionPONew.setAction_user(BigInteger.valueOf(httpModel.getUserModel().getId()));
				zjcOrderActionPONew.setAction_user_name(httpModel.getUserModel().getName());
				zjcOrderActionPONew.setAction_user_type(Integer.getInteger(httpModel.getUserModel().getType()));
				zjcOrderActionPONew.setLog_time(new Date());
				zjcOrderActionPONew.setOrder_id(Integer.parseInt(order_id));
				zjcOrderActionPONew.setOrder_status(0);
				zjcOrderActionPONew.setPay_status(1);
				zjcOrderActionPONew.setShipping_status(0);
				zjcOrderActionPONew.setStatus_desc("确认支付");
				zjcOrderActionDao.insert(zjcOrderActionPONew);
			/*	boolean is_car = false;
				List<ZjcOrderGoodsPO> ordergoods = zjcOrderGoodsDao.list(Dtos.newDto("order_id", zjcOrderPO.getOrder_id()));
				for(ZjcOrderGoodsPO orderGood :ordergoods){
					ZjcGoodsPO goods = zjcGoodsDao.selectByKey(orderGood.getGoods_id());
					if(goods.getIs_car()==1){
						is_car = true;
					}
				}*/
				//查询商品返利延迟天数
				ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
				delay_days= Integer.parseInt(zjcMemberOtherPO.getSpecial_rebate_delay_days());
				Dto dto = Dtos.newDto();
				dto.put("order_id", order_id);
				dto.put("delay_days", delay_days);
				sqlDao.update("com.zjc.users.dao.ZjcQueueDao.requeueByRelationId", dto);//热销预售商品改变商家返分状态
			    sqlDao.update("com.zjc.users.dao.ZjcQueueDao.updateUserByRelationId", dto);//修改热销预售商品与优选商品的会员返分状态
				//生成操作日志
				ZjcUserOperateLogPO operateLog = new ZjcUserOperateLogPO();
				int user_id = httpModel.getUserModel().getId();
				if(user_id == -1){
					user_id = 1111;
				}
				operateLog.setUser_id(user_id);
				operateLog.setUser_name(httpModel.getUserModel().getName());
				operateLog.setOperate_time(new Date());
				operateLog.setDescs("管理员："+ httpModel.getUserModel().getName() + "确认了订单编号：" + zjcOrderPO.getOrder_sn()+ "已支付");
				operateLog.setOperate_type("确认支付成功");
				zjcUserOperateLogDao.insert(operateLog);

				ZjcStorePO ZjcStorePO=ZjcStoreDao.selectByKey(zjcOrderPO.getStore_id());
				 List<ZjcOrderGoodsPO> ZjcOrderGoodsPO = zjcOrderGoodsDao.list(Dtos.newDto("order_id",zjcOrderPO.getOrder_id()));
				  if("mixed_payment".equals(zjcOrderPO.getPay_code())){//易物券支付周期不返还，则不进行返分
					  for (int i = 0; i < ZjcOrderGoodsPO.size(); i++) {
						  ZjcGoodsPO ZjcGoodsPO=ZjcGoodsDao.selectByKey(ZjcOrderGoodsPO.get(i).getGoods_id());
						  if(ZjcGoodsPO.getIs_car()==1){//混合支付并且是特殊商品
							//根据权重算出要返回的
								 ZjcUsersAccountInfoPO user=new ZjcUsersAccountInfoPO();
								 user.setUser_id(ZjcStorePO.getUser_id());
								 //商家
								 List<ZjcUsersAccountInfoPO> list1=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
								 int total=new BigDecimal(zjcMemberOtherPO.getConsumption_coupons()).divide(new BigDecimal("100")).multiply(zjcOrderPO.getBarter_coupons()).intValue();
								 ZjcQueuePO queue=new ZjcQueuePO();
								 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								 Date date= df.parse("2033-5-18 11:33:19");
								//设置26天后自动返分
							     Date newdate=zjcOrderPO.getAdd_time();  
							     Calendar calendar = Calendar.getInstance();  
							     calendar.setTime(newdate);  
							     calendar.add(Calendar.DAY_OF_MONTH, +26);  
							     date = calendar.getTime();  
								 queue.setAdd_time(df.parse(df.format(date)));
								 queue.setSend_time(new Date());
								 queue.setNote("消费后，将滴返回商家");
								 queue.setType(4);
								 queue.setUser_id(list1.get(0).getUser_id());
								 queue.setRelation_id(zjcOrderPO.getOrder_id());
								 queue.setXf_points(total);
								 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
								 ZjcQueueDao.insert(queue);
						  }else if("mixed_payment".equals(zjcOrderPO.getPay_code())&&ZjcGoodsPO.getIs_car()==0){//混合支付但不是特殊商品
							  //根据权重算出要返回的
								 ZjcUsersAccountInfoPO user=new ZjcUsersAccountInfoPO();
								 user.setUser_id(ZjcStorePO.getUser_id());
								 //商家
								 List<ZjcUsersAccountInfoPO> list1=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
								 int total=new BigDecimal(zjcMemberOtherPO.getConsumption_coupons()).divide(new BigDecimal("100")).multiply(zjcOrderPO.getBarter_coupons()).intValue();
								 ZjcQueuePO queue=new ZjcQueuePO();
								 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								 Date date= df.parse("2033-5-18 11:33:19");
								//设置26天后自动返分
							     Date newdate=zjcOrderPO.getAdd_time();  
							     Calendar calendar = Calendar.getInstance();  
							     calendar.setTime(newdate);  
							     calendar.add(Calendar.DAY_OF_MONTH, +26);  
							     date = calendar.getTime();  
								 queue.setAdd_time(df.parse(df.format(date)));
								 queue.setSend_time(new Date());
								 queue.setNote("消费后，将滴返回商家");
								 queue.setType(4);
								 queue.setUser_id(list1.get(0).getUser_id());
								 queue.setRelation_id(zjcOrderPO.getOrder_id());
								 queue.setXf_points(total);
								 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
								 ZjcQueueDao.insert(queue);
								/* //商家立即到账50%
								 BigDecimal totals=new BigDecimal(zjcMemberOtherPO.getShop_drop_commission_rate()).multiply(zjcOrderPO.getCash());
								 list1.get(0).setDrops(totals.intValue());
								 zjcUsersAccountInfoDao.updateByKey(list1.get(0));
								//会员立即到账50%
								 user.setUser_id(zjcOrderPO.getUser_id());
								 List<ZjcUsersAccountInfoPO> userinfo=sqlDao.list("com.zjc.users.dao.ZjcUsersAccountInfoDao.users",user);
								 BigDecimal t=new BigDecimal(zjcMemberOtherPO.getUser_drop_commission_rate()).multiply(zjcOrderPO.getCash());
								 userinfo.get(0).setDrops(t.intValue());
								 zjcUsersAccountInfoDao.updateByKey(userinfo.get(0));*/
						  }
					  }
				  }
				rows++;
			}
			httpModel.setOutMsg(AOSUtils.merge("操作成功，成功确认[{0}]条订单。", rows));
		} catch (Exception e){
			httpModel.setOutMsg("确认付款失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 订单售后处理
	 * 
	 * @param httpModel
	 */
	@Transactional(rollbackFor=Exception.class)
	public void afterSale(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();//
		 boolean isInt = Pattern.compile("^[1-9]+[0-9]*$").matcher(inDto.getString("proportion")).find();
		 boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(inDto.getString("proportion")).find();

		if (!isInt && !isDouble){
			if(inDto.getInteger("proportion") < 0){
				outDto.setAppMsg("首次处理比例只能为正数");
				outDto.setAppCode("-1");
				httpModel.setOutMsg(AOSJson.toJson(outDto));
				return;
			}
			outDto.setAppMsg("首次处理比例只能为数字");
			outDto.setAppCode("-1");
			httpModel.setOutMsg(AOSJson.toJson(outDto));
			return;
		}
		ZjcOrderPO orderpo = zjcOrderDao.selectOne(Dtos.newDto("order_id", inDto.getString("order_id")));
		if(AOSUtils.isEmpty(orderpo)){
			outDto.setAppMsg("订单不存在");
			outDto.setAppCode("-1");
			httpModel.setOutMsg(AOSJson.toJson(outDto));
			return;
		}
		orderpo.setOrder_status(4);//已完成
		orderpo.setConfirm_time(new Date());
		try{
			zjcOrderDao.updateByKey(orderpo);//更改订单状态
			ZjcUsersAccountInfoPO useraccount = (ZjcUsersAccountInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersAccountInfoDao.selectOne", Dtos.newDto("user_id", inDto.getString("user_id")));
			if(AOSUtils.isEmpty(useraccount)){
				outDto.setAppMsg("账户不存在");
				outDto.setAppCode("-1");
				httpModel.setOutMsg(AOSJson.toJson(outDto));
				return;
			}
			BigDecimal total = inDto.getBigDecimal("barter_coupons").multiply(inDto.getBigDecimal("proportion"));
			BigDecimal kz = total.multiply(new BigDecimal("0.5"));
			BigDecimal ky = total.subtract(kz);
			useraccount.setPay_points(useraccount.getPay_points()+ky.intValue());
			useraccount.setMake_over_integral(useraccount.getMake_over_integral()+kz.intValue());
			zjcUsersAccountInfoDao.updateByKey(useraccount);//退回部分易物券
			//新增日志
			ZjcUserLogPO userLogPO = new ZjcUserLogPO();
			userLogPO.setDescs("亲，恭喜您获得商城赠送您的"+total.intValue()+"券已到账，请查收！订单号为：" + inDto.getString("order_sn") +"，当前可用券："+ useraccount.getPay_points() +"，当前可转券："+ useraccount.getMake_over_integral());
			userLogPO.setShow_type(1);
			userLogPO.setTime(new Date());
			userLogPO.setType("售后处理");
			userLogPO.setUser_id(inDto.getBigInteger("user_id"));
			userLogDao.insert(userLogPO);
			outDto.setAppMsg("处理成功");
			outDto.setAppCode("1");
		}catch(Exception e){
			e.printStackTrace();
			outDto.setAppMsg("处理失败");
			outDto.setAppCode("-1");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
 	}
	
	public void payOneQuery(HttpModel httpModel){
		MessageVO msgVo = new MessageVO();
		Dto inDto = httpModel.getInDto();
		String v_oid=inDto.getString("v_oid");
		if(AOSUtils.isEmpty(v_oid)){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			httpModel.setOutMsg(AOSJson.toJson(msgVo));
		}
		logger.info("v_oid:"+v_oid);
		Dto qDto=httpModel.getInDto();
		ZjcOrderPO orderpo= (ZjcOrderPO) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.selectOne", qDto);
		if(AOSUtils.isEmpty(orderpo)){
			msgVo.setCode(Apiconstant.Order_NO_Exist.getIndex());
			msgVo.setMsg(Apiconstant.Order_NO_Exist.getName());
			httpModel.setOutMsg(AOSJson.toJson(msgVo));
		}
		try {
			String v_mid="18338";
			//String pfxFile = "C:/Users/Administrator/Desktop/payeay.pfx";//商户私钥文件
			String pfxFile = "/usr/local/apache-tomcat-7.0.81/webapps/payeay.pfx";//商户私钥文件
			String pfxPassword = "zjc1518";//私钥键入密码
			String aliasPassword = "zjc1518";
			String aliasName = "{17ce4d07-c8a1-457e-a139-a9d3362d8ab4}";//别名
			String src =  v_mid+v_oid;
			String sign = MerchantX509Cert.sign(src, pfxFile, aliasName, pfxPassword, aliasPassword);
			String value="v_oid="+v_oid+"&v_mac="+sign+"&v_mid="+v_mid;
			PrintWriter out = null;  
		    BufferedReader in = null;  
		    String result = "";  
		    try {  
		    	logger.info("开始请求接口:"+value);
		            URL realUrl = new URL("https://api.yizhifubj.com/merchant/order/order_ack_oid_list.jsp");  
		            // 打开和URL之间的连接  
		            URLConnection conn = realUrl.openConnection();  
		            // 设置通用的请求属性  
		            conn.setRequestProperty("accept", "*/*");  
		            conn.setRequestProperty("connection", "Keep-Alive");  
		            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
		            // 发送POST请求必须设置如下两行  
		            conn.setDoOutput(true);  
		            conn.setDoInput(true);  
		            //1.获取URLConnection对象对应的输出流  
		            out = new PrintWriter(conn.getOutputStream());  
		            //2.中文有乱码的需要将PrintWriter改为如下  
		            //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")  
		            // 发送请求参数  
		            out.print(value);  
		            // flush输出流的缓冲  
		            out.flush();  
		            // 定义BufferedReader输入流来读取URL的响应  
		            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
		            logger.info("输入流来读取URL的响应 :"+in);
		            String line;  
		            while ((line = in.readLine()) != null) {  
		                result += line; 
		                logger.info("xml:"+result);
		               //result="<?xml version='1.0' encoding='gb2312'?><ordermessage><messagehead><status>0</status><statusdesc>Success</statusdesc><mid>18338</mid><oid>20180424-18338-909507862051</oid></messagehead><messagebody><order><orderindex>1</orderindex><oid>20180424-18338-909507862051</oid><pmode>����֧��</pmode><pstatus>1</pstatus><pstring>֧���ɹ�</pstring><amount>0.01</amount><moneytype>0</moneytype><isvirement>0</isvirement><sign>6db01715e8649b1bbf57356a3f547d637856783a7399b23aeb3b923de52bbc92f2d59ebde45195450a4c1cb5fbba94cabe2bedb7dc1348339127a66fd99e7fc7eb587745b2b85d5a3e816ef2a616a2696f7c33697cb72222728803b06fe7fe22a028da57734730c8d932bc96fae0b39114ca5e1e7f9dd704b29fbeb87350fd992866a5767208369b3f1337740ecb236a63a5dda26c7c1df047d7f0344a3e032620662472dfbb116ac608d254f6d61538050c572006d84a2385eb358b4e86b0045f8f0b44b71a96b25e943c536ac203d1bd3b5e7139b899c8ab256a5add5d085fccc9cb00809300a0f3ff4a7ca5d67dba5c1abc20fef30c73db3ad3a9e64df791</sign><tokenid>null</tokenid><cardtype></cardtype><cardmask></cardmask><bankname></bankname><phone></phone></order></messagebody></ordermessage>";
		                   try {   
		                    Map<String, Object> map= xmlElements(result);
		                    String status=map.get("status").toString();
		                    String pstatus=map.get("pstatus").toString();
		                    if("0".equals(status)&&"1".equals(pstatus)){
		                    	orderpo.setPay_status(1);
		                    	orderpo.setPay_time(new Date());
		                    	if(orderpo.getPay_code().equals("alipay")){
		                    		orderpo.setPay_code("paySYX");
			                    	orderpo.setPay_name("首易信支付");
		                    	}
		                    	zjcOrderDao.updateByKey(orderpo);
		                    	msgVo.setCode(Apiconstant.Do_Success.getIndex());
		            			msgVo.setMsg("该笔订单支付成功");
		            			logger.info("msgVo:"+msgVo);
		                    }else {
		                    	msgVo.setCode(Apiconstant.Do_Fails.getIndex());
		            			msgVo.setMsg("该笔订单暂时未支付成功");
		            			httpModel.setOutMsg(AOSJson.toJson(msgVo));
		            			logger.info("msgVo:"+msgVo);
							}
		                   } catch (Exception e) {   
		                    e.printStackTrace();   
		                  }   
		            }
		        } catch (Exception e) {  
		            System.out.println("发送 POST 请求出现异常！"+e);  
		            e.printStackTrace();  
		        }  
		        //使用finally块来关闭输出流、输入流  
		        finally{  
		            try{  
		                if(out!=null){  
		                    out.close();  
		                }  
		                if(in!=null){  
		                    in.close();  
		                }  
		            }  
		            catch(IOException ex){  
		                ex.printStackTrace();  
		            }  
		        }  
		       
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpModel.setOutMsg(AOSJson.toJson(msgVo));
	}
	
	public static Map<String, Object> xmlElements(String xmlDoc) throws JDOMException, IOException {
		// 创建一个新的字符串
		StringReader read = new StringReader(xmlDoc);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder sb = new SAXBuilder();
		// 通过输入源构造一个Document
		Document doc = (Document) sb.build(source);
		// 取的根元素
		Element root = (Element) doc.getRootElement();
		// 得到根元素所有子元素的集合
		List jiedian = root.getChildren();
		Element et = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		for (int i = 0; i < jiedian.size(); i++) {
		et = (Element) jiedian.get(i);// 循环依次得到子元素
		resultMap.put(et.getName(), et.getText());
		if (et.getName().equals("messagehead")) {
			List childrenNode = et.getChildren();
			for (int j = 0; j < childrenNode.size(); j++) {
				et = (Element) childrenNode.get(j);// 循环依次得到子元素
				if (et.getName().equals("status")){
					resultMap.put(et.getName(), et.getText());
				}
				}
		}
		if (et.getName().equals("messagebody")) {
			List childrenNode = et.getChildren();
			for (int j = 0; j < childrenNode.size(); j++) {
			et = (Element) childrenNode.get(j);// 循环依次得到子元素
			if (et.getName().equals("order")) {
				List childrenNodes = et.getChildren();
				for (int l = 0; l < childrenNodes.size(); l++){
					et = (Element) childrenNodes.get(l);
					if (et.getName().equals("pstatus")){
						resultMap.put(et.getName(), et.getText());
					}
				}
			
			}
			break;
			}
			}
		}
		return resultMap;


		}
}
