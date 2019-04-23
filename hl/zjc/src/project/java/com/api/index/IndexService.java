package com.api.index;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ConstantUtil;
import com.api.order.OrderService;
import com.zjc.ad.dao.ZjcAdDao;
import com.zjc.advertising.dao.po.ZjcAppversonPO;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.order.dao.ZjcOrderActionDao;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcOrderGoodsDao;
import com.zjc.order.dao.po.ZjcOrderActionPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.region.dao.po.ZjcRegionPO;
import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.system.dao.po.ZjcMemberOtherPO;
import com.zjc.users.dao.ZjcQueueDao;
import com.zjc.users.dao.po.ZjcQueuePO;

/**
 * app接口- 通用信息查询
 * 
 * @author wgm
 */
@Service(value="indexService")
public class IndexService {
	@Autowired
	private ZjcMemberOtherDao zjcMemberOtherDao;
	
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	
	@Autowired
	private ZjcOrderActionDao zjcOrderActionDao;
	
	@Autowired
	private ZjcOrderGoodsDao zjcOrderGoodsDao;
	
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	
	@Autowired
	private ZjcQueueDao zjcQueueDao;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ZjcAdDao zjcAdDao;
	
	@Autowired
	private ZjcQueueDao ZjcQueueDao;
	
	
	/**
	 * app接口-根据积分计算手续费
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getPoundage(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		int fee = 0;
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getInteger("points"))){//查询条件不能为空
			msgVO.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Condition_Is_Null.getName());
		} else if(dto.getInteger("points")<1){//积分必须不小于1
			msgVO.setCode(Apiconstant.Points_Was_Wrong.getIndex());
			msgVO.setMsg(Apiconstant.Points_Was_Wrong.getName());
		} else {
			ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
			int points = dto.getInteger("points");
			if(AOSUtils.isEmpty(zjcMemberOtherPO)){//请先配置系统参数
				msgVO.setCode(Apiconstant.Parameter_Is_Null.getIndex());
				msgVO.setMsg(Apiconstant.Parameter_Is_Null.getName());
			} else {//转账
				fee = (int)Math.ceil(points*Double.valueOf(zjcMemberOtherPO.getBarter_voucher_transfer_fees()));
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				msgVO.setData(fee);
			}
		}
		return AOSJson.toJson(msgVO);
	}


	/**
	 * app接口-获取转账范围
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getExchangRang(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
		if(AOSUtils.isEmpty(zjcMemberOtherPO)){//请先配置系统参数
			msgVO.setCode(Apiconstant.Parameter_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Parameter_Is_Null.getName());
		} else {
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("min_point", zjcMemberOtherPO.getTransfer_minimum());
			map.put("max_point", zjcMemberOtherPO.getMaximum_transfer());
			msgVO.setData(map);
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * app接口-级联查询省市区街道名字列表
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getAddr(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		//查询省信息列表
		List<ZjcRegionPO> provicePOList = sqlDao.list("com.zjc.region.dao.ZjcRegionDao.getAddr", dto);
		if(AOSUtils.isEmpty(provicePOList)){//地址信息为空
			msgVo.setMsg(Apiconstant.NO_DATA.getName());
			msgVo.setCode(Apiconstant.NO_DATA.getIndex());
		} else {//地址信息不为空
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			msgVo.setData(provicePOList);
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * app接口-获取最新版本
	 * 
	 * @param httpModel
	 * @return
	 */
	public String checkVersion(HttpModel httpModel,int type) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getInteger("terminalType"))){
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
		}else{
			dto.put("type", type);
			//倒序查询app版本
			List<ZjcAppversonPO> versionList =  sqlDao.list("com.zjc.advertising.dao.ZjcAppversonDao.listVersionDesc", dto);
			if(AOSUtils.isEmpty(versionList)){//版本数据为空
				msgVo.setMsg(Apiconstant.NO_DATA.getName());
				msgVo.setCode(Apiconstant.NO_DATA.getIndex());
			} else {
				//获取最新app版本
				ZjcAppversonPO appversion = versionList.get(0);
				msgVo.setCode(Apiconstant.Do_Success.getIndex());
				msgVo.setMsg(Apiconstant.Do_Success.getName());
				msgVo.setData(appversion);
			}
		}		
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * 定时任务-确认收货
	 * 
	 * @param httpModel
	 * @return
	 */
	public String confrimOrder(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getInteger("order_id"))){//查询条件order_id为空
			msgVO.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Condition_Is_Null.getName());
		} else {
			ZjcOrderPO zjcOrderPO = zjcOrderDao.selectByKey(dto.getInteger("order_id"));
			if(AOSUtils.isEmpty(zjcOrderPO)){//订单数据不存在
				msgVO.setCode(Apiconstant.Order_NO_Exist.getIndex());
				msgVO.setMsg(Apiconstant.Order_NO_Exist.getName());
			} else if(zjcOrderPO.getOrder_status() != 1){//该订单不能收货确认
				msgVO.setCode(Apiconstant.Order_NO_Confirm.getIndex());
				msgVO.setMsg(Apiconstant.Order_NO_Confirm.getName());
			} else {//确认收货
				zjcOrderPO.setOrder_status(2);//已收货
				zjcOrderPO.setConfirm_time(new Date());
				try{
					zjcOrderDao.updateByKey(zjcOrderPO);
					Dto pDto = Dtos.newDto();
					pDto.put("type", 6);
					pDto.put("relation_id", zjcOrderPO.getOrder_id());
					ZjcQueuePO queuePO = zjcQueueDao.selectOne(pDto);
					if("rate".equals(zjcOrderPO.getPay_code()) && AOSUtils.isEmpty(queuePO)){//礼品兑换时，同时该订单没有生成商家返分,确认收货，商家返利
						orderService.points(dto.getInteger("order_id"));
					}
					
					//上级会员提成
					//orderService.higher_commission(zjcOrderPO);
					//商家返分
				/*	boolean is_car = true;
					List<ZjcOrderGoodsPO> ordergoods = zjcOrderGoodsDao.list(Dtos.newDto("order_id", zjcOrderPO.getOrder_id()));
					for(ZjcOrderGoodsPO orderGood :ordergoods){
						ZjcGoodsPO goods = zjcGoodsDao.selectByKey(orderGood.getGoods_id());
						if(goods.getIs_car()==1){
							is_car = false;
						}
					}
					Dto pDto = Dtos.newDto();
					pDto.put("type", 6);
					pDto.put("relation_id", zjcOrderPO.getOrder_id());
					ZjcQueuePO queuePO = zjcQueueDao.selectOne(pDto);
					if(is_car && AOSUtils.isEmpty(queuePO)){//是普通商品，同时该商品没有生成商家返分，进行商家返分，否则不返分
						orderService.points(dto.getInteger("order_id"));
					}*/
					//降序查询订单状态列表数据
					List<ZjcOrderActionPO> orderActionList =  sqlDao.list("com.zjc.order.dao.ZjcOrderActionDao.listDataDesc", dto);
					ZjcOrderActionPO orderAction = orderActionList.get(0);
					orderAction.setAction_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
					orderAction.setAction_user(new BigInteger("1"));//操作人（管理员）
					orderAction.setAction_user_type(0);//用户
					orderAction.setLog_time(new Date());
					orderAction.setAction_note(ConstantUtil.CONFIRM_ORDER_NOTE);
					orderAction.setStatus_desc(ConstantUtil.CONFIRM_ORDER_DESC);
					//新增订单状态
					zjcOrderActionDao.insert(orderAction);
					
					int total=0;
					if(zjcOrderPO.getPay_code().equals("mixed_pay_drops")){
						 total=new BigDecimal("50").divide(new BigDecimal("100")).multiply(zjcOrderPO.getBarter_coupons()).intValue();
					}else if(zjcOrderPO.getPay_code().equals("cash")||zjcOrderPO.getPay_code().equals("wxpay")||zjcOrderPO.getPay_code().equals("alipay")){
						 total=zjcOrderPO.getCash().intValue();
					}else {
						total=new BigDecimal("5").divide(new BigDecimal("100")).multiply(zjcOrderPO.getBarter_coupons()).intValue();
					}
					 ZjcQueuePO queue=new ZjcQueuePO();
					 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 Date date= df.parse("2033-5-18 11:33:19");
					 //设置周期后自动返分
				     Date newdate=zjcOrderPO.getAdd_time();  
				     Calendar calendar = Calendar.getInstance();  
				     calendar.setTime(newdate);  
				     calendar.add(Calendar.DAY_OF_MONTH, +0);  
				     date = calendar.getTime();  
					 queue.setAdd_time(df.parse(df.format(date)));
					 queue.setSend_time(new Date());
					 queue.setNote("消费后，将易货券返回会员");
					 queue.setType(4);
					 queue.setUser_id(zjcOrderPO.getUser_id());
					 queue.setRelation_id(zjcOrderPO.getOrder_id());
					 queue.setXf_points(total);
					 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
					 ZjcQueueDao.insert(queue);
					 //给商家自动转分
					 if(zjcOrderPO.getPay_code().equals("mixed_pay_drops")){
						 total=zjcOrderPO.getBarter_coupons().intValue();
					}else if(zjcOrderPO.getPay_code().equals("cash")||zjcOrderPO.getPay_code().equals("wxpay")||zjcOrderPO.getPay_code().equals("alipay")){
						 total=zjcOrderPO.getCash().intValue();
					}
				     Calendar calendars = Calendar.getInstance();  
				     calendars.setTime(newdate);  
				     calendars.add(Calendar.DAY_OF_MONTH, +7);  
				     date = calendars.getTime();
				     queue.setAdd_time(new Date());
					 queue.setSend_time(df.parse(df.format(date)));
					 queue.setNote("消费后，将易货券返回商家");
					 queue.setType(4);
					 queue.setUser_id(zjcOrderPO.getUser_id());
					 queue.setRelation_id(zjcOrderPO.getOrder_id());
					 queue.setXf_points(total);
					 queue.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
					 ZjcQueueDao.insert(queue);
					
					
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
				} catch(Exception e) {
					msgVO.setCode(Apiconstant.Save_fails.getIndex());
					msgVO.setMsg(Apiconstant.Save_fails.getName());
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}


	/**
	 * 获取会员app启动图形
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getLancher(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		//查询app启动banner图
		List<Dto> adPoList =  sqlDao.list("com.zjc.ad.dao.ZjcAdDao.listStartDiag", null);
		if(adPoList.size() == 0){//版本数据为空
			msgVo.setMsg(Apiconstant.NO_DATA.getName());
			msgVo.setCode(Apiconstant.NO_DATA.getIndex());
		} else {
			//获取最新app版本
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			msgVo.setData(adPoList.get(0));
		}
		return AOSJson.toJson(msgVo);
	}
}
