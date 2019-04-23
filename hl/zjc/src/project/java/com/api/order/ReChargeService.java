package com.api.order;

import java.util.Date;
import java.util.List;

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

import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.zjc.order.dao.ZjcRechargeDao;
import com.zjc.order.dao.po.ZjcRechargePO;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 需要Tiken的app接口- 易物券订单
 * 
 * @author wgm
 */
@Service(value="reChargeService")
public class ReChargeService {
	
	@Autowired
	private ZjcRechargeDao zjcRechargeDao;
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ApiPublicService apiPublicService;
	
	/**
	 * app接口-我的易物券订单
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String getMyRechargeOrder(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
		if(AOSUtils.isEmpty(dto.getString("page"))||dto.getInteger("page")<1){//当前页数不能为空
			msgVO.setCode(Apiconstant.Page_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Page_Is_Null.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			dto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = dto.getInteger("page");
			dto.put("start", (page-1)*ConstantUtil.pageSize);
			List<ZjcRechargePO> rechargeList = zjcRechargeDao.listPage(dto);
			if(AOSUtils.isEmpty(rechargeList)){//没有易物券订单数据
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
			} else {
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				pageVO.setList(rechargeList);
				msgVO.setData(pageVO);
			}
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * 删除易物券订单
	 * 
	 * @param httpModel
	 * @return
	 */
	public String delRechargeOrder(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getInteger("order_id"))){//查询条件不能为空
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		} else {
			ZjcRechargePO zjcRechargePO = zjcRechargeDao.selectByKey(dto.getBigInteger("order_id"));
			if(AOSUtils.isEmpty(zjcRechargePO)){//易物券订单不存在
				msgVO.setCode(Apiconstant.Order_NO_Exist.getIndex());
				msgVO.setMsg(Apiconstant.Order_NO_Exist.getName());
			} else {//删除易物券订单
				try{
					zjcRechargeDao.deleteByKey(dto.getBigInteger("order_id"));
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
				} catch(Exception e){
					e.printStackTrace();msgVO.setCode(Apiconstant.Do_Fails.getIndex());
					msgVO.setMsg(Apiconstant.Do_Fails.getName());
					msgVO.setCode(Apiconstant.Do_Fails.getIndex());
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * 新增易物券订单
	 * 
	 * @param httpModel
	 */
	public String saveRechargeOrder(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		Dto inDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(httpModel.getAttribute("user_id"))||AOSUtils.isEmpty(inDto.getString("buy_points")) || AOSUtils.isEmpty(inDto.getString("account"))
				|| AOSUtils.isEmpty(inDto.getString("pay_code"))|| AOSUtils.isEmpty(inDto.getString("pay_name"))){
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		}
		msgVO = apiPublicService.isLockedHandle();
		if(msgVO.getCode() != 1){//限制所有会员操作
			return AOSJson.toJson(msgVO);
		}
		msgVO = apiPublicService.isLockedAccount(Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
	    if(msgVO.getCode() != 1){//会员账户被冻结
		   return AOSJson.toJson(msgVO);
	    }
		String user_id = httpModel.getAttribute("user_id").toString();
		ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoDao.selectOne(Dtos.newDto("user_id", user_id));
		ZjcRechargePO zjcRechargePO = new ZjcRechargePO();
		zjcRechargePO.setOrder_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM));
		zjcRechargePO.setUser_id(zjcUsersInfoPO.getUser_id());
		zjcRechargePO.setNickname(zjcUsersInfoPO.getNickname());
		zjcRechargePO.setOrder_sn(ParameterUtil.getOrderSn());
		zjcRechargePO.setAccount(inDto.getString("account"));
		zjcRechargePO.setCtime(new Date());
		zjcRechargePO.setPay_code(inDto.getString("pay_code"));
		zjcRechargePO.setPay_name(inDto.getString("pay_name"));
		zjcRechargePO.setPay_status(0);//新增的订单支付状态都是0 表示未支付
		zjcRechargePO.setBuy_points(inDto.getString("buy_points"));
		try{
			zjcRechargeDao.insert(zjcRechargePO); //插入条数
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			msgVO.setData(zjcRechargePO.getOrder_sn());
		} catch(Exception e){
			e.printStackTrace();
			msgVO.setCode(Apiconstant.Do_Fails.getIndex());
			msgVO.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msgVO);
	}
	
}
