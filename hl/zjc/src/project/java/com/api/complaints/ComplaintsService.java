package com.api.complaints;

import java.util.ArrayList;
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
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.users.dao.ZjcComplaintsDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcComplaintsPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 需要Tiken的app接口- 投诉管理
 * 
 * @author wgm
 */
@Service(value="complaintsService")
public class ComplaintsService {
	@Autowired
	private ZjcComplaintsDao zjcComplaintsDao;
	
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	/**
	 * app接口-获取我的投诉
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String getMyComplaints(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		
		if(AOSUtils.isEmpty(dto.getInteger("page"))||dto.getInteger("page")<1){
			msgVO.setCode(Apiconstant.Page_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Page_Is_Null.getName());
		} else {
			if(AOSUtils.isEmpty(dto.getInteger("complaints_type"))){//投诉类型为空则默认查询被投诉记录
				dto.put("to_user_id", httpModel.getRequest().getAttribute("user_id"));
			} else {
				if(dto.getInteger("complaints_type")==0){//被投诉记录
					dto.put("to_user_id", httpModel.getRequest().getAttribute("user_id"));
				} else {//投诉记录
					dto.put("from_user_id", httpModel.getRequest().getAttribute("user_id"));
				}
			}
			//设置limit每页条数
			dto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = dto.getInteger("page");
			dto.put("start", (page-1)*ConstantUtil.pageSize);
			List<ZjcComplaintsPO> complaintsList=new ArrayList<ZjcComplaintsPO>();
			if(AOSUtils.isEmpty(dto.get("to_user_id")) && AOSUtils.isEmpty(dto.get("from_user_id"))){
				msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			}else {
			    complaintsList = sqlDao.list("com.zjc.users.dao.ZjcComplaintsDao.listPage", dto);
			}
			if(AOSUtils.isEmpty(complaintsList)){//没有投诉数据
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
			} else {//成功返回数据
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				pageVO.setList(complaintsList);
				msgVO.setData(pageVO);
			} 
		}
		
		return AOSJson.toJson(msgVO);
	}

	/**
	 * 新增投诉记录
	 * 
	 * @param httpModel
	 * @return
	 */
	public String addComplaints(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		//获取user_id
		String form_user_id = httpModel.getRequest().getAttribute("user_id").toString();
		//被投诉人电话
		String to_user_id = dto.getString("to_user_id");
		//投诉理由
		String info = dto.getString("info");
		//投诉人联系电话
		String phone = dto.getString("phone");
		if(AOSUtils.isEmpty(to_user_id)){//被投诉人为空
			msgVO.setMsg(Apiconstant.To_User_Id_Null.getName());
			msgVO.setCode(Apiconstant.To_User_Id_Null.getIndex());
		} else if(AOSUtils.isEmpty(info)){//投诉原因不能为空
			msgVO.setMsg(Apiconstant.Info_Is_Null.getName());
			msgVO.setCode(Apiconstant.Info_Is_Null.getIndex());
		} else if(AOSUtils.isEmpty(phone)){//手机号码不能为空
			msgVO.setMsg(Apiconstant.Phone_Is_Null.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Null.getIndex());
		} /*else if(!ValidateUtil.isRightPhone(phone)){//手机号码格式不正确
			msgVO.setMsg(Apiconstant.Phone_Is_Wrong.getName());
		}*/else {//条件成功
			ZjcSellerInfoPO sellerInfo = zjcSellerInfoDao.selectByKey(dto.getBigInteger("to_user_id"));
			ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectByKey(dto.getBigInteger("to_user_id"));
			if(AOSUtils.isEmpty(sellerInfo) && AOSUtils.isEmpty(userInfo)){//被投诉用户不存在
				msgVO.setMsg(Apiconstant.Username_Not_Exist.getName());
				msgVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
			} else {
				ZjcComplaintsPO zjcComplaintsPO =  new ZjcComplaintsPO();
				zjcComplaintsPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				zjcComplaintsPO.setFrom_user_id(form_user_id);
				zjcComplaintsPO.setTo_user_id(to_user_id);
				zjcComplaintsPO.setInfo(ParameterUtil.filterEmoji(info));
				zjcComplaintsPO.setPhone(phone);
				zjcComplaintsPO.setAdd_time(new Date());
				int successNum = zjcComplaintsDao.insert(zjcComplaintsPO);
				if(successNum==0){//存储失败
					msgVO.setMsg(Apiconstant.Save_fails.getName());
					msgVO.setCode(Apiconstant.Save_fails.getIndex());
				} else {
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * app接口-订单投诉
	 * 
	 * @param httpModel
	 * @return
	 */
	public String orderComplaint(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		//获取user_id
		String form_user_id = httpModel.getAttribute("user_id").toString();
		//投诉理由
		String info = dto.getString("info");
		//投诉人联系电话
		String phone = dto.getString("mobile");
		if(AOSUtils.isEmpty(info)){//投诉原因不能为空
			msgVO.setMsg(Apiconstant.Info_Is_Null.getName());
			msgVO.setCode(Apiconstant.Info_Is_Null.getIndex());
		} else if(AOSUtils.isEmpty(phone)){//手机号码不能为空
			msgVO.setMsg(Apiconstant.Phone_Is_Null.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Null.getIndex());
		} /*else if(!ValidateUtil.isRightPhone(phone)){//手机号码格式不正确
			msgVO.setMsg(Apiconstant.Phone_Is_Wrong.getName());
			msgVO.setCode(Apiconstant.Phone_Is_Wrong.getIndex());
		}*/else {//条件成功
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("order_id", dto.getInteger("order_id"));
			map.put("store_id", dto.getInteger("store_id"));
			map.put("user_id", form_user_id);
			ZjcOrderPO orderPO = zjcOrderDao.selectOne(Dtos.newDto(map));
			if(AOSUtils.isEmpty(orderPO)){//投诉订单不存在
				msgVO.setCode(Apiconstant.Order_NO_Exist.getIndex());
				msgVO.setMsg(Apiconstant.Order_NO_Exist.getName());
			} else {
				ZjcStorePO storePO = zjcStoreDao.selectByKey(dto.getInteger("store_id"));
				if(AOSUtils.isEmpty(storePO)){//投诉订单店铺不存在
					msgVO.setCode(Apiconstant.Store_Not_Exist.getIndex());
					msgVO.setMsg(Apiconstant.Store_Not_Exist.getName());
				} else {
					ZjcSellerInfoPO sellerInfo = zjcSellerInfoDao.selectByKey(storePO.getUser_id());
					if(AOSUtils.isEmpty(sellerInfo)){//投诉商户不存在
						msgVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
						msgVO.setMsg(Apiconstant.Username_Not_Exist.getName());
					} else {
						ZjcComplaintsPO zjcComplaintsPO =  new ZjcComplaintsPO();
						zjcComplaintsPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
						zjcComplaintsPO.setFrom_user_id(form_user_id);
						zjcComplaintsPO.setTo_user_id(sellerInfo.getUser_id().toString());
						zjcComplaintsPO.setInfo(ParameterUtil.filterEmoji(info));
						zjcComplaintsPO.setPhone(phone);
						zjcComplaintsPO.setOrder_id(dto.getInteger("order_id"));
						zjcComplaintsPO.setAdd_time(new Date());
						zjcComplaintsPO.setImg(dto.getString("img"));
						int successNum = zjcComplaintsDao.insert(zjcComplaintsPO);
						if(successNum==0){//存储失败
							msgVO.setCode(Apiconstant.Save_fails.getIndex());
							msgVO.setMsg(Apiconstant.Save_fails.getName());
						} else {
							msgVO.setCode(Apiconstant.Do_Success.getIndex());
							msgVO.setMsg(Apiconstant.Do_Success.getName());
						}
					}
					
				}
				
			}
		}
		return AOSJson.toJson(msgVO);
	}
	
	
}
