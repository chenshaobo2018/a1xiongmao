/**
 * 
 */
package com.zjc.users.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.ApiLogService;
import com.api.order.dao.po.ZjcXxOrderPO;
import com.zjc.order.dao.ZjcBzOrderDao;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.po.ZjcBzOrderPO;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.system.dao.po.ZjcMemberOtherPO;
import com.zjc.users.dao.ZjcQueueDao;
import com.zjc.users.dao.ZjcUserLogDao;
import com.zjc.users.dao.po.ZjcQueuePO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;

/**
 * 投诉管理接口
 * @author Administrator
 *
 */
@Service(value="ZjcQueueService")
public class ZjcQueueService {
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcQueueDao ZjcQueueDao;
	
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	
	@Autowired
	private ZjcUserLogDao zjcUserLogDao;
	
	@Autowired
	private ApiLogService apiLogService;
	
	@Autowired
	private com.zjc.users.dao.ZjcUsersAccountInfoDao ZjcUsersAccountInfoDao;
	
	@Autowired
	private ZjcMemberOtherDao zjcMemberOtherDao;
	
	@Autowired
	private ZjcBzOrderDao zjcBzOrderDao;
	
	/**
	 * 投诉管理页面跳转
	 * @param httpModel
	 */
	public void initUserList(HttpModel httpModel){
			httpModel.setViewPath("project/zjc/users/ZjcQueue.jsp");
		}
	
	
	/**
	 *反分页面跳转
	 * @param httpModel
	 */
	public void fanfenlist(HttpModel httpModel){
			httpModel.setViewPath("project/zjc/points/points.jsp");
		}
	
	/**
	 *待处理返分列表页面跳转
	 * @param httpModel
	 */
	public void pointslist(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/points/pointslist.jsp");
	}
	
	/**
	 *反分页面跳转
	 * @param httpModel
	 */
	public void pointslistsuccess(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/points/pointslistsuccess.jsp");
	}
	
	/**
	 * 已取消返分列表页面
	 * 
	 * @param httpModel
	 */
	public void pointsCancleListPage(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/points/pointsCanclelist.jsp");
	}
	
	/**
	 *券返还列表查询页面跳转
	 * @param httpModel
	 */
	public void initUserListQuery(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/users/ZjcQueueQuery.jsp");
	}
	
	/**
	 * 已取消返分查询列表页面
	 * 
	 * @param httpModel
	 */
	public void pointsCancleListPageQuery(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/points/pointsCanclelistQuery.jsp");
	}
	
	/**
	 * 积分管理查询
	 * 
	 * @param httpModel
	 */
	public void getUserList(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		List<ZjcQueuePO> list = new ArrayList<>();
		if(AOSUtils.isNotEmpty(dto.getString("order_sn"))){//判断是否是商城购物订单
			ZjcOrderPO orderPO = (ZjcOrderPO)sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.selectByOrderSn", dto);
			if(AOSUtils.isNotEmpty(orderPO)){
				dto.put("relation_id", orderPO.getOrder_id());
				String sqlStr = " and t.type in (4,6) ";
				dto.put("type1", sqlStr);
			} else {
				ZjcXxOrderPO xxOrderPO = (ZjcXxOrderPO)sqlDao.selectOne("com.api.order.dao.ZjcXxOrderDao.selectByOrderSn", dto);
				if(AOSUtils.isNotEmpty(xxOrderPO)){//判断是否是线下购物订单
					dto.put("relation_id", xxOrderPO.getXx_id());
					dto.put("type0", 8);
				} else {
					ZjcBzOrderPO bzOrderPO = (ZjcBzOrderPO)sqlDao.selectOne("com.zjc.order.dao.ZjcBzOrderDao.selectByOrderSn", Dtos.newDto("bz_sn", dto.getString("order_sn")));
					if(AOSUtils.isNotEmpty(bzOrderPO)){//判断是否是倍增结算订单
						dto.put("relation_id", bzOrderPO.getBz_id());
						dto.put("type0", 1);
					} else {
						dto.put("relation_id", dto.getString("order_sn"));
					}
				}
			}
		}
		list = sqlDao.list("com.zjc.users.dao.ZjcQueueDao.listQueuePage", dto);
		httpModel.setOutMsg(AOSJson.toGridJson(list, dto.getPageTotal()));
	}
	/**
	 * 未反分数据
	 * @param httpModel
	 */
	public void getfanfenList(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcQueuePO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcQueueDao.listuserPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(paramDtos, qDto.getPageTotal()));
	}
	/**
	 * 返分异常列表
	 * @param httpModel
	 */
	public void getfanfen(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcMemberOtherPO> otherList = sqlDao.list("com.zjc.system.dao.ZjcMemberOtherDao.list", null);
		if(!AOSUtils.isEmpty(otherList)){
			qDto.put("return_time", otherList.get(1).getReturn_time());
		}
		List<ZjcQueuePO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcQueueDao.queueList1Page", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(paramDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 查询已取消返分记录
	 * 
	 * @param httpModel
	 */
	public void pointsCancleList(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		List<ZjcQueuePO> list = new ArrayList<>();
		String type = dto.getString("type");
		if(AOSUtils.isEmpty(type)){
			if(AOSUtils.isEmpty(dto.getString("order_sn"))){
				list = sqlDao.list("com.zjc.users.dao.ZjcQueueDao.listCanclePage", dto);
				httpModel.setOutMsg(AOSJson.toGridJson(list, dto.getPageTotal()));
			} else {
				httpModel.setOutMsg(AOSJson.toGridJson(list, dto.getPageTotal()));
			}
		}else {
			if("1".equals(type)){//结算
				if(AOSUtils.isNotEmpty(dto.getString("order_sn"))){
					ZjcBzOrderPO bzOrderPO = (ZjcBzOrderPO)sqlDao.selectOne("com.zjc.order.dao.ZjcBzOrderDao.selectByOrderSn", Dtos.newDto("bz_sn", dto.getString("order_sn")));
					dto.put("relation_id", bzOrderPO.getBz_id());
				}
			}else if("4".equals(type) || "6".equals(type)){//商城购物
				if(AOSUtils.isNotEmpty(dto.getString("order_sn"))){
					ZjcOrderPO orderPO = (ZjcOrderPO)sqlDao.selectOne("com.zjc.order.dao.ZjcOrderDao.selectByOrderSn", dto);
					dto.put("relation_id", orderPO.getOrder_id());
				}
			}else {//线下购物
				if(AOSUtils.isNotEmpty(dto.getString("order_sn"))){
					ZjcXxOrderPO orderPO = (ZjcXxOrderPO)sqlDao.selectOne("com.api.order.dao.ZjcXxOrderDao.selectByOrderSn", dto);
					dto.put("relation_id", orderPO.getXx_id());
				}
			}
			list = sqlDao.list("com.zjc.users.dao.ZjcQueueDao.listCanclePage", dto);
			httpModel.setOutMsg(AOSJson.toGridJson(list, dto.getPageTotal()));
		}
	}
	
	/**
	 * 反分成功列表
	 * @param httpModel
	 */
	public void listsuccessPage(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcQueuePO> paramDtos = sqlDao.list("com.zjc.users.dao.ZjcQueueDao.listsuccessPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(paramDtos, qDto.getPageTotal()));
	}
	
	
	/**
	 * 将数据添加到反分列表
	 * @param httpModel
	 */
	public void fanfenlistList(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		String id=dto.get("id").toString();
		String [] queid=id.split(",");
		for (int i = 0; i < queid.length; i++) {
			 ZjcQueuePO ZjcQueuePO =new ZjcQueuePO();
			 ZjcQueuePO.setId(Integer.parseInt(queid[i]));
			 List<ZjcQueuePO> queue=sqlDao.list("com.zjc.users.dao.ZjcQueueDao.selectByKeys",ZjcQueuePO);
			 //将数据添加到反分列表中
			 queue.get(0).setIs_send(2);
			 ZjcQueueDao.updateByKey(queue.get(0));
		}
		httpModel.setOutMsg("添加返分列表成功");
	}
	
	/**
	 * 反分接口
	 * @param request
	 * @param response
	 * @return
	 */
	public void fanfen(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		Dto outDto = Dtos.newOutDto();
		String id=dto.get("id").toString();
		String [] queid=id.split(",");
		try{
			for (int i = 0; i < queid.length; i++) {
				 ZjcQueuePO ZjcQueuePO =new ZjcQueuePO();
				 ZjcQueuePO.setId(Integer.parseInt(queid[i]));
				 ZjcQueuePO queue=(com.zjc.users.dao.po.ZjcQueuePO) sqlDao.selectOne("com.zjc.users.dao.ZjcQueueDao.selectByKeys",ZjcQueuePO);
				 ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO=new ZjcUsersAccountInfoPO();
				 ZjcUsersAccountInfoPO.setUser_id(queue.getUser_id());
				 if("6".equals(queue.getType())){//会员商城购物，商家返利
					 queue.setSend_time(new Date());
					 ZjcQueueDao.updateByKey(queue);
				 } else {//会员返分
					 ZjcMemberOtherPO paramDtos = (ZjcMemberOtherPO) sqlDao.selectOne("com.zjc.system.dao.ZjcMemberOtherDao.selectByMaxKey",null);
					 queue.setAdd_time(paramDtos.getReturn_time());
					 ZjcQueueDao.updateByKey(queue);
				}
				/*queue.setIs_send(1);
				queue.setSuccess_time(new Date());
				ZjcQueueDao.updateByKey(queue);*/
			}
			outDto.setAppMsg("数据已进入下一次反分数据中!");
		} catch(Exception e){
			e.printStackTrace();
			outDto.setAppMsg("修改失败!");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	 
	
	/**
	 * 
	 * 积分信息修改
	 * 
	 * @param httpModel
	 */
	public void updateStoreInfo(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcQueuePO ZjcQueuePO = new ZjcQueuePO();
			ZjcQueuePO.copyProperties(inDto);
			ZjcQueueDao.updateByKey(ZjcQueuePO);
			outDto.setAppMsg("积分信息修改成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 将已取消返分的记录重新发起返分
	 * 
	 * @param httpModel
	 */
	public void confirmReq(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		try{
			ZjcQueuePO queue = ZjcQueueDao.selectByKey(inDto.getInteger("id"));
			queue.setIs_cancle(0);
			ZjcQueueDao.updateByKey(queue);
			outDto.setAppMsg("处理成功");
		} catch (Exception e){
			outDto.setAppMsg("处理失败");
			e.printStackTrace();
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 批量将已取消返分的记录重新发起返分
	 * 
	 * @param httpModel
	 */
	public void confirmAllReq(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		//获取选中的订单id集合
		String[] selectionIds = inDto.getRows();
		try{
			int rows = 0;
			for(String id : selectionIds){
				ZjcQueuePO queue = ZjcQueueDao.selectByKey(Integer.parseInt(id));
				queue.setIs_cancle(0);
				ZjcQueueDao.updateByKey(queue);
				rows++;
			}
			outDto.setAppMsg(AOSUtils.merge("操作成功，成功处理[{0}]条数据。", rows));
		} catch (Exception e){
			outDto.setAppMsg("处理失败");
			e.printStackTrace();
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	public void cancleQueue(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		Dto outDto = Dtos.newDto();
		int row = sqlDao.update("com.zjc.users.dao.ZjcQueueDao.cancleQueue", dto);
		if(row >= 1){
			outDto.setAppMsg("操作成功！");
		}else{
			outDto.setAppMsg("操作失败！");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
}
