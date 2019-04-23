/**
 * 
 */
package com.zjc.store.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.common.util.ParameterUtil;
import com.zjc.common.po.ExportExcelInfo;
import com.zjc.common.util.ExportExcle;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.ZjcStoreDeputeDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStoreDeputePO;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUserLogPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Service(value="zjcStoreService")
public class ZjcStoreService {

	//private static Logger logger = LoggerFactory.getLogger(ZjcStoreService.class);
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	@Autowired 
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcStoreDeputeDao zjcStoreDeputeDao;
	
	@Autowired
	private ApiLogService apiLogService;
	
	@Autowired
	private ApiPublicService apiPublicService;
	
	/**
	 * 初始化商户管理店铺信息页面
	 * 
	 * @param httpModel
	 */
	public void initStorePage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/store/storeInfo.jsp");
	}
	
	/**
	 * 初始化后台管理自营店信息页面
	 * 
	 * @param httpModel
	 */
	public void initMyShopPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/store/myShopInfo.jsp");
	}
	
	/**
	 * 初始化后台管理商家列表页面
	 * 
	 * @param httpModel
	 */
	public void initShopListPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/store/storeList.jsp");
	}
	/**
	 * 商家查询页面
	 * @param httpModel
	 */
	public void initShopList(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/store/storeListTwo.jsp");
	}
	
	/**
	 * 初始化后台管理商家审核列表页面
	 * 
	 * @param httpModel
	 */
	public void initShopAuditListPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/store/storeAuditList.jsp");
	}
	
	/**
	 * 初始化客服处理列表页面
	 * 
	 * @param httpModel
	 */
	public void initCustomerHandleListPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/store/customerHandleList.jsp");
	}
	
	/**
	 * 通过店铺id查询店铺信息
	 * 
	 * @param httpModel
	 */
	public void getStoreInfoByStoreId(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		//查询店铺信息
		ZjcStorePO zjcStorePO = zjcStoreDao.selectByKey(inDto.getInteger("store_id"));
		httpModel.setOutMsg(AOSJson.toJson(zjcStorePO, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 查询店铺信息
	 * 
	 * @param httpModel
	 */
	public void getStoreInfo(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		int user_id=0;
		if("1".equals(inDto.getString("type"))) {//商家后台
			inDto.put("mobile", httpModel.getUserModel().getAccount());
			ZjcSellerInfoPO zjcSellerInfoPO = zjcSellerInfoDao.selectOne(inDto);
			inDto.put("mobile", null);
			user_id = zjcSellerInfoPO.getUser_id().intValue();
		} else {//后台管理员
			user_id = 14196;
		}
		//查询店铺信息
		ZjcStorePO zjcStorePO = zjcStoreDao.selectByUserId(user_id);
		httpModel.setOutMsg(AOSJson.toJson(zjcStorePO, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 
	 * 店铺信息修改
	 * 
	 * @param httpModel
	 */
	public void updateStoreInfo(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		//查询原店铺信息
		//ZjcStorePO oldZjcStorePO = zjcStoreDao.selectByKey(inDto.getInteger("store_id"));
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcStorePO isExitstore = (ZjcStorePO) sqlDao.selectOne("com.zjc.store.dao.ZjcStoreDao.selectByStoreName", Dtos.newDto("store_name", inDto.getString("store_name")));
			ZjcStorePO oldstore = (ZjcStorePO) sqlDao.selectOne("com.zjc.store.dao.ZjcStoreDao.selectByStoreName", inDto);
			if(AOSUtils.isNotEmpty(isExitstore) && AOSUtils.isEmpty(oldstore)){
				outDto.setAppMsg("该店铺名称已存在");
				outDto.setAppCode("-1");
				httpModel.setOutMsg(AOSJson.toJson(outDto));
				return;
			}
			ZjcStorePO zjcStorePO = new ZjcStorePO();
			zjcStorePO.copyProperties(inDto);
			if(zjcStorePO.getCat_id()==null){
				zjcStorePO.setCat_id(0);
			}
			//获取省名字
			String province_name = apiPublicService.getAddressName(inDto.getInteger("province_id"));
			//获取市名字
			String city_name = apiPublicService.getAddressName(inDto.getInteger("city_id"));
			//获取区名字
			String district_name = apiPublicService.getAddressName(inDto.getInteger("area_id"));
			zjcStorePO.setArea_info(province_name + city_name + district_name);
			zjcStoreDao.updateByKey(zjcStorePO);
			outDto.setAppMsg("店铺信息修改成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 
	 * 审核店铺
	 * 
	 * @param httpModel
	 */
	public void auditStoreInfo(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		//查询原店铺信息
		//ZjcStorePO oldZjcStorePO = zjcStoreDao.selectByKey(inDto.getInteger("store_id"));
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcStorePO zjcStorePO = new ZjcStorePO();
			zjcStorePO.copyProperties(inDto);
			if(zjcStorePO.getCat_id()==null){
				zjcStorePO.setCat_id(0);
			}
			zjcStorePO.setStore_state(inDto.getInteger("store_state"));
			Map<String,Object> map = new HashMap<String,Object>();
			try{
				zjcStoreDao.updateByKey(zjcStorePO);
				ZjcSellerInfoPO sellerInfo = (ZjcSellerInfoPO) sqlDao.selectOne("com.zjc.store.dao.ZjcSellerInfoDao.selectByStoreId", inDto);
				if(inDto.getString("store_state").equals("3")){//审核通过
					if(AOSUtils.isNotEmpty(sellerInfo)){//商家会员信息不为空，启用该商家会员
						sellerInfo.setIs_lock(0);//改变商家状态
						zjcSellerInfoDao.updateByKey(sellerInfo);//启用商家会员
						ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectByKey(new BigInteger(sellerInfo.getRecommended_code()));
						userInfo.setIs_distribut(1);//其会员号变为分销商
						zjcUsersInfoDao.updateByKey(userInfo);
						//生产日志
						map.put("logType", "我要开店");
						map.put("logType", "开店成功");
						map.put("user_id", sellerInfo.getRecommended_code());
						apiLogService.saveLog(map);
						outDto.setAppMsg("店铺信息审核通过");
					} 
				} else {
					//生产日志
					map.put("logType", "我要开店");
					map.put("type", "开店失败");
					map.put("user_id", sellerInfo.getRecommended_code());
					map.put("show_type", 1);
					apiLogService.saveLog(map);
					outDto.setAppMsg("店铺信息审核不通过");
				}
			} catch(Exception e){
				e.printStackTrace();
				outDto.setAppMsg("店铺信息修改失败");
			}
			
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 查询商家信息列表
	 * 
	 * @param httpModel
	 */
	public void listZjcStores(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<Dto> zjcStorePOList = sqlDao.list("com.zjc.store.dao.ZjcStoreDao.getStoreListPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(zjcStorePOList, inDto.getPageTotal()));
	}
	
	/**
	 * 修改店铺状态
	 * 
	 * @param httpModel
	 */
	public void changeStoreState(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		//查询原店铺信息
		ZjcStorePO zjcStorePO = zjcStoreDao.selectByKey(inDto.getInteger("store_id"));
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			zjcStorePO.setStore_state(inDto.getInteger("store_state"));
			zjcStoreDao.updateByKey(zjcStorePO);
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 后台管理商家查询审核列表
	 * 
	 * @param httpModel
	 */
	public void listZjcAuditStores(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<ZjcUserLogPO> paramDtos = sqlDao.list("com.zjc.store.dao.ZjcStoreDao.getPendingStorePage",inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(paramDtos, inDto.getPageTotal()));
	}
	
	/**
	 * 根据user_id查询商家管理员ID
	 * 
	 * @param httpModel
	 */
	public void getUserInfo(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		ZjcSellerInfoPO zjcSellerInfoPO = zjcSellerInfoDao.selectByKey(inDto.getBigInteger("user_id"));
		httpModel.setOutMsg(AOSJson.toJson(zjcSellerInfoPO));
	}
	
	/**
	 * 初始化列表
	 */
	public void initDepute(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/store/storeDepute.jsp");
	}
	
	/**
	 * 初始化列表数据
	 */
	public void listDepute(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		List<ZjcStoreDeputePO> storeDeputePOs = zjcStoreDeputeDao.listSerachPage(inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(storeDeputePOs, inDto.getPageTotal()));
	}
	
	/**
	 * 根据user_id查询数据
	 */
	public void deputeDetial(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		ZjcStoreDeputePO storeDeputePO = (ZjcStoreDeputePO)sqlDao.selectOne("com.zjc.store.dao.ZjcStoreDeputeDao.deputeDelByCondition", inDto);
		httpModel.setOutMsg(AOSJson.toJson(storeDeputePO));
	}
	
	/**
	 * 根据user_id更新数据
	 */
	public void updateDepute(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		Dto outDto = Dtos.newOutDto();
		ZjcStoreDeputePO storeDeputePO = new ZjcStoreDeputePO();
		storeDeputePO.copyProperties(inDto);
		ZjcStoreDeputePO oldDepute = zjcStoreDeputeDao.selectByKey(inDto.getBigInteger("user_id"));
		//查询商家会员ID
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) sqlDao.selectOne("com.zjc.store.dao.ZjcSellerInfoDao.selectByStoreId", storeDeputePO);
		try {
			if(oldDepute.getState() == storeDeputePO.getState()){//未修改审核状态
				outDto.setAppMsg("请修改审核状态！");
			}else{
				int row = zjcStoreDeputeDao.updateDepute(storeDeputePO);//更新数据
				if(row >= 1){//修改成功
					String msg = "";
					Map<String, Object> map = new HashMap<>();
					if(storeDeputePO.getState().equals("3")){//审核通过
						msg = ParameterUtil.getTuisongMsg(2, "委托收款申请审核", "委托收款申请审核", sellerInfoPO.getUser_id()+"会员，恭喜您委托收款审核通过，请尽快登录商家后台。");
						map.put("user_id", sellerInfoPO.getUser_id());
						map.put("logType", "委托收款申请");
						map.put("type", "审核通过");
						apiLogService.saveLog(map);
					}else if(storeDeputePO.getState().equals("2") && oldDepute.getState().equals("3")){//审核通过后取消审核通过
						msg = ParameterUtil.getTuisongMsg(2, "委托收款申请审核", "委托收款申请审核",sellerInfoPO.getUser_id()+"会员，您的委托收款申请已取消！");
						map.put("user_id", sellerInfoPO.getUser_id());
						map.put("logType", "委托收款申请");
						map.put("type", "申请撤销");
						apiLogService.saveLog(map);
					}else{//审核失败
						msg = ParameterUtil.getTuisongMsg(2, "委托收款申请审核", "委托收款申请审核", sellerInfoPO.getUser_id()+"会员，很抱歉由于您的资料不齐，委托收款审核未通过，请重新提交。");
						map.put("user_id", sellerInfoPO.getUser_id());
						map.put("logType", "委托收款申请");
						map.put("type", "审核失败");
						apiLogService.saveLog(map);
					}
					//通过user_id查询账户信息
					//ZjcUsersInfoPO usersInfoPO = zjcUsersInfoDao.selectByKey(BigInteger.valueOf(Long.valueOf(storeDeputePO.getUser_id())));
					//推送消息到APP
					ParameterUtil.tuisongToSingle(sellerInfoPO.getClientid(), sellerInfoPO.getSrc_client(), msg);
					outDto.setAppMsg("操作成功！");
				}
			}
		} catch (Exception e) {
			outDto.setAppMsg("操作失败！");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 根据条件导出店铺信息
	 * 
	 * @param httpModel
	 */
	@SuppressWarnings("rawtypes")
    public void exportStoreInfo(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		try {
			if(AOSUtils.isNotEmpty(inDto.getString("real_name"))){
					inDto.put("real_name", URLDecoder.decode(inDto.getString("real_name"), "UTF-8"));
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
	        String title = "店铺信息";
	        String[] strHeader =
	            {"商家ID","店铺名称", "真实姓名", "联系电话1", "联系电话2","产品分类","营业执照"};
	        String[] strField =
	            {"user_id","store_name","real_name","mobile","contacts_phone","cat_name","business_licence_number_elc"};
	        //根据条件查询待导出订单数据
	        List list = sqlDao.list("com.zjc.store.dao.ZjcStoreDao.findStoreExportData", inDto);
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
}
