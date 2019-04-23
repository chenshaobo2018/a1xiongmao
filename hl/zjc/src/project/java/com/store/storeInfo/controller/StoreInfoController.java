package com.store.storeInfo.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.store.record.service.ZjcIncomeFlowService;
import com.store.storeInfo.service.StoreInfoService;
import com.zjc.region.dao.po.ZjcRegionPO;
import com.zjc.store.dao.po.ZjcStoreDeputePO;
import com.zjc.store.dao.po.ZjcStorePO;



/**
 * 店铺基本信息管理控制层
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/store")
public class StoreInfoController {
	private static final Logger logger = LoggerFactory.getLogger(StoreInfoController.class);
	
	@Autowired
	private StoreInfoService storeInfoService;
	
	@Autowired
	private ZjcIncomeFlowService zjcIncomeFlowService;
	/**
	 * 跳转到店铺详情界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toStoreDetailPage", method=RequestMethod.GET)
	public String toStoreDetailPage(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		Map<String, Object> map = storeInfoService.getStoreDetailByStoreId(httpModel);
		request.setAttribute("map", map);
		ZjcStorePO zjcStorePO = (ZjcStorePO)map.get("zjcStorePO");
		request.setAttribute("zjcStorePO", zjcStorePO);
		return "project/store/mct_shop.jsp";
	}
	
	/**
	 * 跳转到店铺详情界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateStoreInfo")
	public String updateStoreInfo(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVo = new MessageVO();
		HttpModel httpModel = new HttpModel(request, response);
		boolean result = storeInfoService.updateStore(httpModel);
		if(result){
			msgVo.setMsg("修改成功");
		}else{
			msgVo.setMsg("修改失败");
		}
		return "redirect:toStoreDetailPage.jhtml";
	}
	
	@RequestMapping(value = "/getProvince", method=RequestMethod.GET)
	public void getProvince(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		List<ZjcRegionPO> provinceList = storeInfoService.getProvince(httpModel);
		request.setAttribute("provinceList", provinceList);
	}
	
	@RequestMapping(value = "/findAddr")
	public String  findAddr(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		List<ZjcRegionPO> addrList = storeInfoService.findAddr(httpModel);
		String addrListStr = AOSJson.toGridJson(addrList);
		WebCxt.write(response, addrListStr);
		return httpModel.getViewPath();
	}
	
	@RequestMapping(value = "/getRegion", method=RequestMethod.GET)
	public void getRegion(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		ZjcRegionPO zjcRegionPO = storeInfoService.getRegion(httpModel);
		request.setAttribute("zjcRegionPO", zjcRegionPO);
	}
	
	@RequestMapping(value="/transfer")
	public String transfer(HttpServletRequest request, HttpServletResponse response) throws ParseException{
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg = storeInfoService.transfer(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
		return httpModel.getViewPath();
	}
	
	@RequestMapping(value="/real_name", method=RequestMethod.POST)
	public  String real_name(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg = storeInfoService.real_name(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
		return httpModel.getViewPath();
	}
	
	@RequestMapping(value="/mobile", method=RequestMethod.POST)
	public void mobile(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg = storeInfoService.mobile(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	@RequestMapping(value="/serachRecord")
	public void serachLog(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO records = zjcIncomeFlowService.serachRecord(httpModel);
		WebCxt.write(response, AOSJson.toJson(records));
	}
	
	@RequestMapping(value="/confirm_trust")
	public void confirm_trust(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg = storeInfoService.confirm_trust(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	@RequestMapping(value="/deputeDetial")	
	public void deputeDetial(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		ZjcStoreDeputePO msg = storeInfoService.deputeDetial(httpModel);
		if(!AOSUtils.isEmpty(msg)){
			WebCxt.write(response, AOSJson.toJson(msg));
		}
	}
	
	/**
	 * 获取当天未支付的订单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/wait_pay_order")	
	public void wait_pay_order(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO msg = storeInfoService.wait_pay_order(httpModel);
		if(!AOSUtils.isEmpty(msg)){
			WebCxt.write(response, AOSJson.toJson(msg));
		}
	}
	
	/**
	 * 获取当天未支付的订单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/wait_delivery_order")	
	public void wait_delivery_order(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO msg = storeInfoService.wait_delivery_order(httpModel);
		if(!AOSUtils.isEmpty(msg)){
			WebCxt.write(response, AOSJson.toJson(msg));
		}
	}
	
	/**
	 * 获取当天未支付的订单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/wait_comment_order")	
	public void wait_comment_order(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO msg = storeInfoService.wait_comment_order(httpModel);
		if(!AOSUtils.isEmpty(msg)){
			WebCxt.write(response, AOSJson.toJson(msg));
		}
	}
	
	/**
	 * 获取当天未支付的订单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/finish_order")	
	public void finish_order(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO msg = storeInfoService.finish_order(httpModel);
		if(!AOSUtils.isEmpty(msg)){
			WebCxt.write(response, AOSJson.toJson(msg));
		}
	}
	
	@RequestMapping(value="/sale_goods")	
	public void sale_goods(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO msg = storeInfoService.sale_goods(httpModel);
		if(!AOSUtils.isEmpty(msg)){
			WebCxt.write(response, AOSJson.toJson(msg));
		}
	}
	
	@RequestMapping(value="/stock_goods")	
	public void stock_goods(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO msg = storeInfoService.stock_goods(httpModel);
		if(!AOSUtils.isEmpty(msg)){
			WebCxt.write(response, AOSJson.toJson(msg));
		}
	}
	
	@RequestMapping(value="/unsale_goods")	
	public void unsale_goods(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO msg = storeInfoService.unsale_goods(httpModel);
		if(!AOSUtils.isEmpty(msg)){
			WebCxt.write(response, AOSJson.toJson(msg));
		}
	}
	
	@RequestMapping(value="/sells_hot_goods")	
	public void sells_hot_goods(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO msg = storeInfoService.sells_hot_goods(httpModel);
		if(!AOSUtils.isEmpty(msg)){
			WebCxt.write(response, AOSJson.toJson(msg));
		}
	}
	
	/**
	 * 老会员生成对应的商家用户信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/create_user_account")	
	public void create_user_account(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg = storeInfoService.create_user_account(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
	}
}
