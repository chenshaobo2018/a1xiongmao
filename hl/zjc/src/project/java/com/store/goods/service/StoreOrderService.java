/**
 * 
 */
package com.store.goods.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.gexin.fastjson.JSON;
import com.store.goods.OrderExportVO;
import com.store.record.dao.ZjcIncomeFlowDao;
import com.store.record.dao.po.ZjcIncomeFlowPO;
import com.zjc.common.util.ExportExcle;
import com.zjc.order.dao.ZjcDeliveryDocDao;
import com.zjc.order.dao.ZjcOrderActionDao;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcOrderGoodsDao;
import com.zjc.order.dao.ZjcTransferOrderTimeDao;
import com.zjc.order.dao.po.ZjcDeliveryDocPO;
import com.zjc.order.dao.po.ZjcOrderActionPO;
import com.zjc.order.dao.po.ZjcOrderGoodsPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.order.dao.po.ZjcOrderVO2;
import com.zjc.order.dao.po.ZjcTDOrderExportVO;
import com.zjc.order.dao.po.ZjcTransferOrderTimePO;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.users.dao.ZjcUserLogDao;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.po.ZjcQueuePO;
import com.zjc.users.dao.po.ZjcUserLogPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;

@Service(value="storeOrderService")
public class StoreOrderService {
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ZjcDeliveryDocDao zjcDeliveryDocDao;
	
	@Autowired
	private ZjcOrderGoodsDao zjcOrderGoodsDao;
	
	@Autowired
	private ZjcOrderActionDao zjcOrderActionDao;
	
	@Autowired
	private ZjcTransferOrderTimeDao ZjcTransferOrderTimeDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private ZjcUserLogDao zjcUserLogDao;
	
	@Autowired
	private ZjcIncomeFlowDao zjcIncomeFlowDao;
	
	/**
	 * 查询商家的全部的订单
	 * @param httpModel
	 * @return
	 */
	public PageVO serachOrder(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isEmpty(dto.getString("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		dto.put("goods_name", dto.getString("goods_name").trim());
		dto.put("consignee", dto.getString("consignee").trim());
		dto.put("order_sn", dto.getString("order_sn").trim());
		//查询该店铺下的全部订单
		List<Dto> orders = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.serachOrderPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(orders);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	/**
	 * 查询订单状态
	 * @param httpModel
	 * @return
	 */
	public List<Dto> getAllOrderStatus(HttpModel httpModel){
		List<Dto> order_status  = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getOrderStatus", httpModel.getInDto());
		return order_status;
	}
	/**
	 * 查询订单状态
	 * @param httpModel
	 * @return
	 */
	public List<Dto> getAllPayType(HttpModel httpModel){
		List<Dto> pay_type  = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getAllPayType", httpModel.getInDto());
		return pay_type;
	}
	/**
	 * 查询订单状态
	 * @param httpModel
	 * @return
	 */
	public List<Dto> getAllPayStatus(HttpModel httpModel){
		List<Dto> pay_status  = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getAllPayStatus", httpModel.getInDto());
		return pay_status;
	}
	
	/**
	 * 获取全部的物流公司信息
	 * @param httpModel
	 * @return
	 */
	public List<Dto> getAllLogistics(HttpModel httpModel){
		List<Dto> Logistics  = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getAllLogistics", httpModel.getInDto());
		return Logistics;
	}
	
	/**
	 * 获取全部的货品状态
	 * @param httpModel
	 * @return
	 */
	public List<Dto> getAllShipping(HttpModel httpModel){
		List<Dto> Logistics  = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getAllShipping", httpModel.getInDto());
		return Logistics;
	}
	
	/**
	 * 订单详情
	 * @param httpModel
	 * @return
	 */
	public ZjcOrderVO2 order_detial(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		String order_id =  httpModel.getRequest().getParameter("order_id");
		dto.put("order_id", order_id);
		ZjcOrderVO2 order = (ZjcOrderVO2) sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.order_detial", dto);
		return order;
	}
	
	/**
	 * 打印送货单
	 * @param httpModel
	 * @return
	 */
	public Dto order_invo(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		String order_id =  httpModel.getRequest().getParameter("order_id");
		dto.put("order_id", order_id);
		Dto order = (Dto) sqlDao.selectDto("com.zjc.order.dao.ZjcOrderDao.order_invo", dto);
		return order;
	}
	
	/**
	 * 确认订单
	 * @param httpModel
	 * @return
	 */
	public MessageVO invo_comfirm(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		MessageVO msg = new MessageVO();
		//通过订单ID查询订单信息数据
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(dto.getInteger("order_id"));
		if(AOSUtils.isEmpty(zjcOrderPO)){//订单状态
			msg.setData(Apiconstant.System_busy.getName());
			msg.setCode(Apiconstant.System_busy.getIndex());
			return msg;
		}
		//如果订单状态不为0，则不进行数据库操作
		if(zjcOrderPO.getOrder_status() != 0){//订单状态
			msg.setData(Apiconstant.Do_Success.getName());
			msg.setCode(Apiconstant.Do_Success.getIndex());
			return msg;
		}
		if(zjcOrderPO.getSingle_back() == 3){//订单退单成功，不能发货
			msg.setMsg("该订单已退单，不能确认");
			msg.setCode(0);
			return msg;
		}
		//修改订单状态
		zjcOrderPO.setOrder_status(1);//已确认
		try {
			zjcOrderDao.updateByKey(zjcOrderPO);
			//查询订单状态信息表
			List<ZjcOrderActionPO> zjcOrderActionPOList = zjcOrderActionDao.list(dto);
			if(zjcOrderActionPOList.size()>0){
				ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
				zjcOrderActionPONew.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				zjcOrderActionPONew.setAction_note(dto.getString("note"));
				ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
				zjcOrderActionPONew.setAction_user(sellerInfoPO.getUser_id());
				zjcOrderActionPONew.setAction_user_type(3);
				zjcOrderActionPONew.setAction_user_name(sellerInfoPO.getNickname());
				zjcOrderActionPONew.setLog_time(new Date());
				zjcOrderActionPONew.setOrder_id(dto.getInteger("order_id"));
				zjcOrderActionPONew.setOrder_status(1);//已确认
				zjcOrderActionPONew.setPay_status(1);//已支付
				zjcOrderActionPONew.setShipping_status(0);//已发货
				zjcOrderActionPONew.setStatus_desc("确认订单");				
				zjcOrderActionDao.insert(zjcOrderActionPONew);
			}
			msg.setData(Apiconstant.Do_Success.getName());
			msg.setCode(Apiconstant.Do_Success.getIndex());
		} catch(Exception e) {
			msg.setData(Apiconstant.Do_Fails.getName());
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 订单发货
	 * @param httpModel
	 * @return
	 */
	public MessageVO delivery(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		MessageVO msg = new MessageVO();
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(dto.getInteger("order_id"));
		//如果发货状态为1，则不进行数据库操作，不发货
		if(zjcOrderPO.getShipping_status() == 1){//订单状态
			msg.setMsg("该订单已发货，不能再次发货");
			msg.setCode(Apiconstant.Do_Success.getIndex());
			return msg;
		}
		
		if(zjcOrderPO.getOrder_status() == 4){//订单异常，已被系统售后梳理
			msg.setMsg("该订单已完成，不能再次发货");
			msg.setCode(Apiconstant.Do_Success.getIndex());
			return msg;
		}
		if(zjcOrderPO.getSingle_back() == 2){//订单退单成功，不能发货
			msg.setMsg("该订单已退单，不能发货");
			msg.setCode(0);
			return msg;
		}
		//修改订单状态
		zjcOrderPO.setShipping_status(1);//状态改为已发货
		zjcOrderPO.setShipping_code(dto.getString("shipping_code"));
		zjcOrderPO.setTracking_no(dto.getString("invoice_no"));
		zjcOrderPO.setShipping_time(new Date());
		try {
			zjcOrderDao.updateByKey(zjcOrderPO);
			//新增发货单记录
			ZjcDeliveryDocPO zjcDeliveryDocPO = new ZjcDeliveryDocPO();
			zjcDeliveryDocPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			zjcDeliveryDocPO.copyProperties(zjcOrderPO);
			zjcDeliveryDocPO.setNote(dto.getString("note"));
			zjcDeliveryDocPO.setInvoice_no(dto.getString("invoice_no"));
			zjcDeliveryDocPO.setShipping_code(dto.getString("shipping_code"));
			zjcDeliveryDocPO.setCreate_time(new Date());
			zjcDeliveryDocPO.setIs_del(0);
			ZjcDeliveryDocPO  oldDeleveyPO =zjcDeliveryDocDao.getZjcDeliveryByOrderId(dto.getInteger("order_id"));
			if(AOSUtils.isNotEmpty(oldDeleveyPO)){
				zjcDeliveryDocPO.setId(oldDeleveyPO.getId());
				zjcDeliveryDocDao.updateByKey(zjcDeliveryDocPO);
			}else{
				zjcDeliveryDocDao.insert(zjcDeliveryDocPO);
			}
			
			//查询订单状态信息表
			List<ZjcOrderActionPO> zjcOrderActionPOList = zjcOrderActionDao.list(dto);
			//生成订单状态记录
			if(zjcOrderActionPOList.size()>0){
				ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
				zjcOrderActionPONew.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				zjcOrderActionPONew.setAction_note(dto.getString("note"));
				ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
				zjcOrderActionPONew.setAction_user(sellerInfoPO.getUser_id());
				zjcOrderActionPONew.setAction_user_type(3);
				zjcOrderActionPONew.setAction_user_name(sellerInfoPO.getNickname());
				zjcOrderActionPONew.setLog_time(new Date());
				zjcOrderActionPONew.setOrder_id(dto.getInteger("order_id"));
				zjcOrderActionPONew.setOrder_status(1);//已确认
				zjcOrderActionPONew.setPay_status(1);//已支付
				zjcOrderActionPONew.setShipping_status(1);//已发货
				zjcOrderActionPONew.setStatus_desc("确认发货");
				zjcOrderActionDao.insert(zjcOrderActionPONew);
			}
			
			//修改订单商品发货状态
			List<ZjcOrderGoodsPO> zjcOrderGoodsPOList = zjcOrderGoodsDao.getOrderGoodsByOrderId(dto.getInteger("order_id"));
			if(null != zjcOrderGoodsPOList){
				for(ZjcOrderGoodsPO zjcOrderGoodsPO : zjcOrderGoodsPOList){
					zjcOrderGoodsPO.setDelivery_id(zjcDeliveryDocPO.getId());
					zjcOrderGoodsPO.setIs_send(1);
					zjcOrderGoodsDao.updateByKey(zjcOrderGoodsPO);
				}
			}
			msg.setMsg(Apiconstant.Do_Success.getName());
			msg.setCode(Apiconstant.Do_Success.getIndex());
		} catch(Exception e) {
			msg.setMsg(Apiconstant.Do_Fails.getName());
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 搜索发货单
	 * @param httpModel
	 * @return
	 */
	public PageVO serachInvo(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		//查询该店铺下的全部订单
		List<Dto> invos = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.serachInvoPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(invos);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	public ResponseEntity<byte[]> exportOrder(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		try {
			if(AOSUtils.isNotEmpty(dto.getString("goods_name"))){
				dto.put("goods_name", URLDecoder.decode(dto.getString("goods_name"), "UTF-8"));
			}
			if(AOSUtils.isNotEmpty(dto.getString("consignee"))){
				dto.put("consignee", URLDecoder.decode(dto.getString("consignee"), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		List<OrderExportVO> list = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.findExportOrder", dto);
		String title = "订单信息";
        String[] headers =
            {"订单编号","用户ID","收货人", "商品名称", "现金",  "易物券", "商品数量", "收货人地址", "收货人电话", "支付状态", "发货状态", "下单时间", "发货时间", "收货时间"};
        String[] fields =
            {"order_sn", "user_id","consignee","goods_name", "cash", "barter_coupons", "goods_num", "address", "mobile", "pay_status", 
            		"shipping_status", "add_time", "shipping_time", "confirm_time"};
        return ExportExcle.exportExcel2(title, headers, fields, list, httpModel.getResponse());
	}
	
	/**
	 * 查询账单明细
	 * @param httpModel
	 * @return
	 */
	public PageVO getTransferOrderPage(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isNotEmpty(dto.getString("transfer_type"))){
			if("0".equals(dto.getString("transfer_type"))){
				dto.put("transfer", "0");
				dto.put("transfer_type", "");
			}
		}
		//查询该店铺下的全部订单
		List<Dto> invos = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getTransferOrderPage", dto);
		List<Dto> getTypeNull = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getTypeNull", dto);
		List<Dto> getTypeNotNull = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getTypeNotNull", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(invos);
		pageVO.setTotalSize(dto.getInteger("total"));
		pageVO.setSum(getTypeNull.get(0).getInteger("countType"));
		pageVO.setNotsum(getTypeNotNull.get(0).getInteger("countTypeNot"));
		return pageVO;
	}
	
	/**
	 * 查询账单明细
	 * @param httpModel
	 * @return
	 */
	public PageVO updataTransferOrderPage(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		String checked_array=dto.getString("checked_array");
		if(AOSUtils.isNotEmpty(checked_array)){
			 List<String> list =  JSON.parseArray(checked_array, String.class);
			    for (int i = 0; i < list.size(); i++) {
					String order_id = list.get(i);
					dto.put("order_id", order_id);
					ZjcTransferOrderTimePO ZjcTransferOrder = (ZjcTransferOrderTimePO) sqlDao.selectOne("com.zjc.order.dao.ZjcTransferOrderTimeDao.selectOne", dto);
					if(AOSUtils.isEmpty(ZjcTransferOrder)){
						ZjcTransferOrderTimePO ZjcTransferOrderTimePO=new ZjcTransferOrderTimePO();
						ZjcTransferOrderTimePO.setOrder_id(Integer.valueOf(order_id));
						ZjcTransferOrderTimePO.setTransfer_time(new Date());
						ZjcTransferOrderTimePO.setTransfer_type(1);
						ZjcTransferOrderTimeDao.insert(ZjcTransferOrderTimePO);
					}
				}
		}
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isNotEmpty(dto.getString("transfer_type"))){
			if("0".equals(dto.getString("transfer_type"))){
				dto.put("transfer", "0");
				dto.put("transfer_type", "");
			}
		}
		//查询该店铺下的全部订单
		List<Dto> invos = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getTransferOrderPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(invos);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	
	public ResponseEntity<byte[]> TransferExportOrder(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		try {
			if(AOSUtils.isNotEmpty(dto.getString("goods_name"))){
				dto.put("goods_name", URLDecoder.decode(dto.getString("goods_name"), "UTF-8"));
			}
			if(AOSUtils.isNotEmpty(dto.getString("real_name"))){
				dto.put("real_name", URLDecoder.decode(dto.getString("real_name"), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		List<OrderExportVO> list = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getTransferOrder", dto);
		String title = "台账信息";
        String[] headers =
            {"订单编号","用户ID","用户名", "商品名称", "订单金额",  "成单时间", "转账时间", "转账状态"};
        String[] fields =
            {"order_sn", "user_id","real_name","goods_name", "money", "add_time", "transfer_time", "transfer_type"};
        return ExportExcle.exportExcel3(title, headers, fields, list, httpModel.getResponse());
	}
	
	
	
	/**
	 * 查询催单退货
	 * @param httpModel
	 * @return
	 */
	public PageVO ReminderToReturn(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		String checked_array=dto.getString("checked_array");
		if(AOSUtils.isNotEmpty(checked_array)){
			 List<String> list =  JSON.parseArray(checked_array, String.class);
			    for (int i = 0; i < list.size(); i++) {
					String order_id = list.get(i);
					dto.put("order_id", order_id);
					ZjcTransferOrderTimePO ZjcTransferOrder = (ZjcTransferOrderTimePO) sqlDao.selectOne("com.zjc.order.dao.ZjcTransferOrderTimeDao.selectOne", dto);
					if(AOSUtils.isEmpty(ZjcTransferOrder)){
						ZjcTransferOrderTimePO ZjcTransferOrderTimePO=new ZjcTransferOrderTimePO();
						ZjcTransferOrderTimePO.setOrder_id(Integer.valueOf(order_id));
						ZjcTransferOrderTimePO.setTransfer_time(new Date());
						ZjcTransferOrderTimePO.setTransfer_type(1);
						ZjcTransferOrderTimeDao.insert(ZjcTransferOrderTimePO);
					}
				}
		}
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isNotEmpty(dto.getString("transfer_type"))){
			if("0".equals(dto.getString("transfer_type"))){
				dto.put("transfer", "0");
				dto.put("transfer_type", "");
			}
		}
		//查询该店铺下的全部订单
		List<Dto> invos = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.getTransferOrderPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(invos);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	
	/**
	 * 查询商家的全部催单退单数据的订单
	 * @param httpModel
	 * @return
	 */
	public PageVO serachCDTHOrder(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		if(AOSUtils.isEmpty(dto.getString("page"))){
			dto.put("page", 1);
		}
		int page = dto.getInteger("page");
		dto.put("start", (page-1)*ConstantUtil.pageSize);
		dto.put("goods_name", dto.getString("goods_name").trim());
		dto.put("consignee", dto.getString("consignee").trim());
		dto.put("order_sn", dto.getString("order_sn").trim());
		//查询该店铺下的全部订单
		List<Dto> orders = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.serachCDTHOrderPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(orders);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	/**
	 * 拒绝退单
	 * 
	 * @param httpModel
	 * @return
	 */
	public MessageVO refuseBackOrder(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		MessageVO msg = new MessageVO();
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(dto.getInteger("order_id"));
		if(zjcOrderPO.getPay_status() == 0){//订单未支付
			msg.setMsg("该订单还未支付完成，不能退单");
			msg.setCode(0);
			return msg;
		}
		if(zjcOrderPO.getSingle_back() == 0){//该订单未申请退单，不能执行退单操作
			msg.setMsg("该订单未申请退单，不能执行退单操作");
			msg.setCode(0);
			return msg;
		}
		
		//修改订单状态
		zjcOrderPO.setSingle_back(3);//退单失败（拒绝退单）
		zjcOrderPO.setConfirm_single_back_time(new Date());//确认退单时间
		try {
			zjcOrderDao.updateByKey(zjcOrderPO);
			
			//查询订单状态信息表
			List<ZjcOrderActionPO> zjcOrderActionPOList = zjcOrderActionDao.list(dto);
			//生成订单状态记录
			if(zjcOrderActionPOList.size()>0){
				ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
				zjcOrderActionPONew.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				zjcOrderActionPONew.setAction_note(dto.getString("note"));
				ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
				zjcOrderActionPONew.setAction_user(sellerInfoPO.getUser_id());
				zjcOrderActionPONew.setAction_user_type(3);
				zjcOrderActionPONew.setAction_user_name(sellerInfoPO.getNickname());
				zjcOrderActionPONew.setLog_time(new Date());
				zjcOrderActionPONew.setOrder_id(dto.getInteger("order_id"));
				zjcOrderActionPONew.setOrder_status(1);//已确认
				zjcOrderActionPONew.setPay_status(1);//已支付
				zjcOrderActionPONew.setShipping_status(0);//未发货
				zjcOrderActionPONew.setStatus_desc("拒绝退单");
				zjcOrderActionDao.insert(zjcOrderActionPONew);
			}
			
			msg.setMsg(Apiconstant.Do_Success.getName());
			msg.setCode(Apiconstant.Do_Success.getIndex());
		} catch(Exception e) {
			msg.setMsg(Apiconstant.Do_Fails.getName());
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			e.printStackTrace();
		}
		return msg;
	}
	
	
	/**
	 * 确认退单
	 * 
	 * @param httpModel
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public MessageVO confirmBackOrder(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		MessageVO msg = new MessageVO();
		ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(dto.getInteger("order_id"));
		if(zjcOrderPO.getPay_status() == 0){//订单未支付
			msg.setMsg("该订单还未支付完成，不能退单");
			msg.setCode(0);
			return msg;
		}
		if("1".equals(zjcOrderPO.getShipping_code())){//订单已发货
			msg.setMsg("该订单已发货，不能退单");
			msg.setCode(0);
			return msg;
		}
		if(zjcOrderPO.getSingle_back() != 1){//该订单暂未退单，不能执行退单操作
			msg.setMsg("该订单已退单或不能退单，请勿继续操作");
			msg.setCode(0);
			return msg;
		}
		//修改订单状态
		zjcOrderPO.setSingle_back(2);//退单成功
		zjcOrderPO.setConfirm_single_back_time(new Date());//确认退单时间
		try {
			//查询商家账户的可用券额度
			ZjcUsersAccountInfoPO storeaccountinfo = (ZjcUsersAccountInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersAccountInfoDao.selectStoreAccountInfo", Dtos.newDto("store_id",zjcOrderPO.getStore_id()));
			if(AOSUtils.isEmpty(storeaccountinfo)){//商家账户不存在
				msg.setMsg("您的账户不存在，请重试");
				msg.setCode(0);
				return msg;
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("relation_id", dto.getInteger("order_id"));
			map.put("type", 6);//商家返分记录
			map.put("is_send", 1);//已返分
			//统计商家所得券
			ZjcQueuePO queue  = (ZjcQueuePO) sqlDao.selectOne("com.zjc.users.dao.ZjcQueueDao.selectOne", Dtos.newDto(map));
			int sellerbackRec = 0;
			if(AOSUtils.isNotEmpty(queue)){//存在已返分记录
				sellerbackRec = queue.getKz_points() + queue.getXf_points();
			}
			if(storeaccountinfo.getMake_over_integral() < sellerbackRec){//商家账户可用券不足，不能退单
				msg.setMsg("您的可用券不足，无法退款");
				msg.setCode(0);
				return msg;
			}
			//查询会员账户信息
			ZjcUsersAccountInfoPO useraccountinfo = (ZjcUsersAccountInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersAccountInfoDao.selectOne", Dtos.newDto("user_id",zjcOrderPO.getUser_id()));
			if(AOSUtils.isEmpty(useraccountinfo)){//会员账户信息不存在
				msg.setMsg("会员账户信息不存在，无法退款");
				msg.setCode(0);
				return msg;
			}
			map = new HashMap<String,Object>();
			map.put("user_id", zjcOrderPO.getUser_id());
			map.put("relation_id", dto.getInteger("order_id"));
			map.put("type", 4);//商城购物类型
			map.put("is_send", 1);//已返分
			//统计会员所得券
			List<ZjcQueuePO> queueList  = sqlDao.list("com.zjc.users.dao.ZjcQueueDao.list", Dtos.newDto(map));
			int userbackRec = 0;//会员获得的赠送券
			if(queueList.size()>0){
				for(ZjcQueuePO userqueue: queueList){
					userbackRec += userqueue.getKz_points() + userqueue.getXf_points();
				}
			}
			if(useraccountinfo.getPay_points() + zjcOrderPO.getBarter_coupons().intValue() < userbackRec){//会员账户可用券不足，不能退单
				msg.setMsg("会员可用券不足，无法退款");
				msg.setCode(0);
				return msg;
			}
			zjcOrderDao.updateByKey(zjcOrderPO);
			
			//查询订单状态信息表
			List<ZjcOrderActionPO> zjcOrderActionPOList = zjcOrderActionDao.list(dto);
			//生成订单状态记录
			if(zjcOrderActionPOList.size()>0){
				ZjcOrderActionPO zjcOrderActionPONew = new ZjcOrderActionPO();
				zjcOrderActionPONew.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				zjcOrderActionPONew.setAction_note(dto.getString("note"));
				ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
				zjcOrderActionPONew.setAction_user(sellerInfoPO.getUser_id());
				zjcOrderActionPONew.setAction_user_type(3);
				zjcOrderActionPONew.setAction_user_name(sellerInfoPO.getNickname());
				zjcOrderActionPONew.setLog_time(new Date());
				zjcOrderActionPONew.setOrder_id(dto.getInteger("order_id"));
				zjcOrderActionPONew.setOrder_status(1);//已确认
				zjcOrderActionPONew.setPay_status(1);//已支付
				zjcOrderActionPONew.setShipping_status(0);//未发货
				zjcOrderActionPONew.setStatus_desc("确认退单");
				zjcOrderActionDao.insert(zjcOrderActionPONew);
			}
			//扣除商家所得券
			storeaccountinfo.setMake_over_integral(storeaccountinfo.getMake_over_integral()-sellerbackRec);
			zjcUsersAccountInfoDao.updateByKey(storeaccountinfo);
			//生成日志
			ZjcUserLogPO sellerLog = new ZjcUserLogPO(); 
			sellerLog.setUser_id(storeaccountinfo.getUser_id());
			sellerLog.setShow_type(0);
			sellerLog.setTime(new Date());
			sellerLog.setType("退单申请");
			String sellerdescs = zjcOrderPO.getUser_id() + "会员申请的退单已处理，订单号为："+zjcOrderPO.getOrder_sn()
					+"，当前可转券：" + storeaccountinfo.getMake_over_integral();
			sellerLog.setDescs(sellerdescs);
			zjcUserLogDao.insert(sellerLog);
			//生成操作流水
			ZjcIncomeFlowPO zjcIncomeFlowPO = new ZjcIncomeFlowPO();
			zjcIncomeFlowPO.setBalance(storeaccountinfo.getMake_over_integral());
			zjcIncomeFlowPO.setExchange_time(new Date());
			zjcIncomeFlowPO.setIn_user_id(zjcOrderPO.getUser_id().intValue());
			zjcIncomeFlowPO.setOut_user_id(storeaccountinfo.getUser_id().intValue());
			zjcIncomeFlowPO.setExpend(sellerbackRec);
			zjcIncomeFlowPO.setType("1");//商家退单支出卷
			zjcIncomeFlowPO.setOrder_sn(zjcOrderPO.getOrder_sn());
			zjcIncomeFlowDao.insert(zjcIncomeFlowPO);
			//退回会员的券并扣除赠送的券
			useraccountinfo.setPay_points(useraccountinfo.getPay_points()+zjcOrderPO.getBarter_coupons().intValue()-userbackRec);
			zjcUsersAccountInfoDao.updateByKey(useraccountinfo);
			ZjcUserLogPO userLog = new ZjcUserLogPO(); 
			userLog.setUser_id(useraccountinfo.getUser_id());
			userLog.setShow_type(1);
			userLog.setTime(new Date());
			userLog.setType("退单申请");
			int bduserpoint = 0;
			if((zjcOrderPO.getBarter_coupons().intValue()-userbackRec)>0){
				bduserpoint = zjcOrderPO.getBarter_coupons().intValue()-userbackRec;
			} else {
				bduserpoint = userbackRec - zjcOrderPO.getBarter_coupons().intValue();
			}
			String userdescs = "亲，恭喜您申请的退单已处理，订单号为："+zjcOrderPO.getOrder_sn()+"，并获得商城赠送券"
			               +bduserpoint+"，当前可用券："
			               +useraccountinfo.getPay_points();
			userLog.setDescs(userdescs);
			zjcUserLogDao.insert(userLog);
			msg.setMsg(Apiconstant.Do_Success.getName());
			msg.setCode(Apiconstant.Do_Success.getIndex());
		} catch(Exception e) {
			e.printStackTrace();
			msg.setMsg(Apiconstant.Do_Fails.getName());
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return msg;
		}
		return msg;
	}

	/**
	 * 导出退单催单数据
	 * 
	 * @param httpModel
	 * @return
	 */
	public ResponseEntity<byte[]> exportTDOrder(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		try {
			if(AOSUtils.isNotEmpty(dto.getString("consignee"))){
				dto.put("consignee", URLDecoder.decode(dto.getString("consignee"), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		List<ZjcTDOrderExportVO> list = sqlDao.list("com.zjc.order.dao.ZjcOrderDao.findExportTDOrder", dto);
		String title = "退单催单订单信息";
        String[] headers =
            {"订单编号","用户ID","收货人", "商品名称", "现金",  "易物券", "下单时间", "催单时间", "催单次数", "发起退单时间", "确认退单时间", "退单状态"};
        String[] fields =
            {"order_sn", "user_id","consignee","goods_name", "cash","barter_coupons", "add_time", "reminderTime", "reminder", "single_back_time", "confirm_single_back_time","single_back"};
        return ExportExcle.exportTDExcel(title, headers, fields, list, httpModel.getResponse());
	}
	
}
