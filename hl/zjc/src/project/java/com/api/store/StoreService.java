package com.api.store;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.api.find.dao.ZjcCpOrderDao;
import com.api.find.dao.po.ZjcCpOrderPO;
import com.api.store.dao.ZjcStoreCollectDao;
import com.api.store.dao.po.ZjcStoreCollectPO;
import com.zjc.goods.dao.po.ZjcGoodsCategoryPO;
import com.zjc.region.dao.po.ZjcRegionPO;
import com.zjc.store.dao.ZjcCompanyPublicDao;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcCompanyPublicPO;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * app接口- 店铺管理
 * 
 * @author wgm
 */
@Service(value="storeService")
public class StoreService {
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	
	@Autowired
	private ZjcStoreCollectDao zjcStoreCollectDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcCompanyPublicDao companyPublicDao;
	
	@Autowired
	private ZjcCpOrderDao cpOrderDao;
	
	@Autowired
	private ApiPublicService apiPublicService;
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	/**
	 * app接口-查询店铺列表
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String getStores(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getInteger("page"))||dto.getInteger("page")<1){//当前页数不能为空
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		} else {//当前页数不为空
			//设置limit每页条数（此处特殊处理）
			dto.put("limit", 20);
			//设置start开始条数
			int page = dto.getInteger("page");
			dto.put("start", (page-1)*ConstantUtil.pageSize);
			//通过坐标点以及经纬度计算该坐标点范围内的最大经纬度
			if(!AOSUtils.isEmpty(dto.getString("lng")) && !AOSUtils.isEmpty(dto.getString("lat"))){//经纬度不为空
				Map<String, double[]> map = ParameterUtil.returnLLSquarePoint(dto.getBigDecimal("lng").doubleValue(), dto.getBigDecimal("lat").doubleValue(), ConstantUtil.DISTANCE);
				//取右上的坐标点(得到最大的坐标点范围)
				double[] rightTopPoint = map.get("rightTopPoint");
				double latMax = rightTopPoint[0];
				double lngMax = rightTopPoint[1];
				//取左下的坐标点(得到最小的坐标点范围)
				double[] leftBottomPoint = map.get("leftBottomPoint");
				double latMin = leftBottomPoint[0];
				double lngMin = leftBottomPoint[1];
				dto.put("lngMax", lngMax);
				dto.put("latMax", latMax);
				dto.put("lngMin", lngMin);
				dto.put("latMin", latMin);
			}
			//查询店铺列表
			List<ZjcStorePO> storeList = sqlDao.list("com.zjc.store.dao.ZjcStoreDao.likeDistancePage", dto);
			if(AOSUtils.isEmpty(storeList)){//暂无店铺数据
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			} else {//验证通过，分页查询
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				pageVO.setList(storeList);
				msgVO.setData(pageVO);
			}
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * app接口-通过store_id获取店铺信息
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String getStoreInfoByStoreId(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		//获取store_id
		int store_id;
		if(AOSUtils.isEmpty(dto.getString("store_id"))){
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		}else{
			if(!ApiPublicService.isInteger(dto.getString("store_id"))){//检查是否整数
				msgVO.setCode(Apiconstant.ILLEGAL_PARAMETER.getIndex());
				msgVO.setMsg("商家ID不符合规范，请保证为纯数字");
				return AOSJson.toJson(msgVO);
			}
			store_id = dto.getInteger("store_id");
		}
		if(AOSUtils.isEmpty(store_id)){//店铺ID不能为空
			msgVO.setMsg(Apiconstant.Condition_Is_Null.getName());
			msgVO.setCode(Apiconstant.Condition_Is_Null.getIndex());
		} else {
			//通过store_id获取店铺信息
			ZjcStorePO zjcStorePO = zjcStoreDao.selectByKey(store_id);
			if(AOSUtils.isEmpty(zjcStorePO)){//店铺不存在
				msgVO.setMsg(Apiconstant.Store_Not_Exist.getName());
				msgVO.setCode(Apiconstant.Store_Not_Exist.getIndex());
			} else {
				if(!AOSUtils.isEmpty(dto.getString("token"))){//如果token不为空
					dto.put("store_id", null);
					//查询用户信息
					ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectOne(dto);
					if(!AOSUtils.isEmpty(userInfo)){//如果用户信息不为空
						dto.put("user_id", userInfo.getUser_id());
						dto.put("store_id", store_id);
						ZjcStoreCollectPO collectPO = zjcStoreCollectDao.selectOne(dto);
						if(!AOSUtils.isEmpty(collectPO)){//如果该店铺收藏信息不为空
							zjcStorePO.setHasCollect(true);//该用户收藏了该店铺
						}
					}
				}
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				msgVO.setData(zjcStorePO);
			}
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * app接口-通过店铺ID收藏店铺
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String collect_store(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		int store_id = qDto.getInteger("store_id");
		if(AOSUtils.isEmpty(store_id)){//参数为空
			msgVO.setCode(Apiconstant.Condition_Is_Null.getIndex());
		} else {//条件符合要求
			ZjcStorePO zPO = zjcStoreDao.selectOne(qDto);
			if(AOSUtils.isEmpty(zPO)){//店铺信息为空
				msgVO.setCode(Apiconstant.Store_Not_Exist.getIndex());
				msgVO.setMsg(Apiconstant.Store_Not_Exist.getName());
			} else {//店铺信息不为空，收藏店铺
				qDto.put("user_id", httpModel.getAttribute("user_id"));
				ZjcStoreCollectPO oldStoreCollectPO = zjcStoreCollectDao.selectOne(qDto);
				if(!AOSUtils.isEmpty(oldStoreCollectPO)){//判断该店铺自己是否已收藏
					msgVO.setCode(Apiconstant.Store_Was_Collected.getIndex());
					msgVO.setMsg(Apiconstant.Store_Was_Collected.getName());
				} else {//该店铺未收藏，收藏店铺
					ZjcStoreCollectPO zjcStoreCollectPO = new ZjcStoreCollectPO();
					zjcStoreCollectPO.setUser_id(qDto.getBigInteger("user_id"));
					zjcStoreCollectPO.setStore_id(store_id);
					zjcStoreCollectPO.setAdd_time(new Date());
					try {
						int successNum = zjcStoreCollectDao.insert(zjcStoreCollectPO);
						if(successNum == 0){//保存失败
							msgVO.setCode(Apiconstant.Save_fails.getIndex());
							msgVO.setMsg(Apiconstant.Save_fails.getName());
						} else {//收藏成功
							msgVO.setCode(Apiconstant.Do_Success.getIndex());
							msgVO.setMsg(Apiconstant.Do_Success.getName());
						}
					} catch (Exception e) {
						msgVO.setCode(Apiconstant.Do_Fails.getIndex());
						msgVO.setMsg(Apiconstant.Do_Fails.getName());
					}
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * app接口-通过店铺ID取消收藏店铺
	 * 
	 * @param httpModel
	 * @return
	 */
	public String cancle_collect_store(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		int store_id = qDto.getInteger("store_id");
		//转化user_id类型
		qDto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
		if(AOSUtils.isEmpty(store_id)){//参数为空
			msgVO.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Condition_Is_Null.getName());
		} else {//条件符合要求
			ZjcStoreCollectPO zjcStoreCollectPO = zjcStoreCollectDao.selectOne(qDto);
			if(AOSUtils.isEmpty(zjcStoreCollectPO)){//店铺收藏信息为空
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
			} else {//店铺收藏信息不为空，取消收藏店铺
				try {
					int successNum = zjcStoreCollectDao.deleteByKey(zjcStoreCollectPO.getCollect_id());
					if(successNum == 0){//保存失败
						msgVO.setCode(Apiconstant.Save_fails.getIndex());
						msgVO.setMsg(Apiconstant.Save_fails.getName());
					} else {//取消收藏成功
						msgVO.setCode(Apiconstant.Do_Success.getIndex());
						msgVO.setMsg(Apiconstant.Do_Success.getName());
					}
				} catch (Exception e) {
					msgVO.setCode(Apiconstant.Do_Fails.getIndex());
					msgVO.setMsg(Apiconstant.Do_Fails.getName());
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * app接口-查询我的收藏店铺
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String getMyCollectStore(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		qDto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
		if(AOSUtils.isEmpty(qDto.getInteger("page"))||qDto.getInteger("page")<1){//当前页数不能为空
			msgVO.setCode(Apiconstant.Page_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Page_Is_Null.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = qDto.getInteger("page");
			qDto.put("start", (page-1)*ConstantUtil.pageSize);
			List<ZjcStoreCollectPO> storeCollectList = sqlDao.list("com.api.store.dao.ZjcStoreCollectDao.listMyCollectStorePage", qDto);
			if(AOSUtils.isEmpty(storeCollectList)){//查询无数据
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
			} else {//有数据
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				pageVO.setList(storeCollectList);
				msgVO.setData(pageVO);
			}
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 *企业宣传列表
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String companyPublicList(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getInteger("page"))||qDto.getInteger("page")<1||AOSUtils.isEmpty(qDto.getInteger("condition"))){//当前页数不能为空
			msgVO.setCode(Apiconstant.Page_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Page_Is_Null.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = qDto.getInteger("page");
			qDto.put("start", (page-1)*ConstantUtil.pageSize);
			List<Dto> companyPublicPOs =companyPublicDao.listConditionPage(qDto);
			if(AOSUtils.isEmpty(companyPublicPOs)){//查询无数据
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			} else {//有数据
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				pageVO.setList(companyPublicPOs);
				msgVO.setData(pageVO);
			}
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 *企业宣传详情
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String companyPublicDetail(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getInteger("cp_id"))){
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			msgVO.setMsg(Apiconstant.NO_DATA.getName());
		}else {
			ZjcCompanyPublicPO companyPublicPO=companyPublicDao.selectByKey(qDto.getInteger("cp_id"));
			if(AOSUtils.isEmpty(companyPublicPO)){
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
			} else {
				if(!AOSUtils.isEmpty(qDto.getString("token"))){//如果token不为空
					//查询用户信息
					ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectOne(qDto);
					if(!AOSUtils.isEmpty(userInfo)){//如果用户信息不为空
						qDto.put("user_id", userInfo.getUser_id());
						ZjcCpOrderPO cpOrderPO = cpOrderDao.selectOne(qDto);
						if(!AOSUtils.isEmpty(cpOrderPO)){//如果该用户已购买了企业宣传
							companyPublicPO.setHasBuy(true);
						}
					}
				}
				companyPublicPO.setContent(ParameterUtil.createHtmlBody(companyPublicPO.getTitle(), companyPublicPO.getContent()));
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				msgVO.setData(companyPublicPO);
			}
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 *	 判断用户是否购买企业宣传
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  checkCPIsBuy(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO = new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		//获取查询条件
		Dto dto = httpModel.getInDto();
		dto.put("user_id", request.getAttribute("user_id"));
		List<ZjcCpOrderPO> cpOrderPOs=cpOrderDao.selectByUserId(dto);
		if(cpOrderPOs.size() >= 1){//成功
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			msgVO.setData(true);
		}else{
			msgVO.setCode(Apiconstant.No_Buy_Cp.getIndex());
			msgVO.setMsg(Apiconstant.No_Buy_Cp.getName());
			msgVO.setData(false);
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * app接口-商家申请开店
	 * 
	 * @param httpModel
	 * @return
	 */
	public String applyStore(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO();
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		qDto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
		if(AOSUtils.isEmpty("store_name")){//判断企业名字不能为空
			msgVo.setMsg(Apiconstant.Store_Name_Is_Null.getName());
			msgVo.setCode(Apiconstant.Store_Name_Is_Null.getIndex());
		} else if(AOSUtils.isEmpty(qDto.getString("province_id")) || AOSUtils.isEmpty(qDto.getString("city_id"))
				|| AOSUtils.isEmpty(qDto.getString("area_id"))){//省市区选择不完整
			msgVo.setMsg(Apiconstant.ProviceCityDistrict_Is_Wrong.getName());
			msgVo.setCode(Apiconstant.ProviceCityDistrict_Is_Wrong.getIndex());
		} else if(AOSUtils.isEmpty(qDto.getString("address_detail"))){//判断详细地址不能为空
			msgVo.setMsg(Apiconstant.Address_Is_Null.getName());
			msgVo.setCode(Apiconstant.Address_Is_Null.getIndex());
		} else if(AOSUtils.isEmpty(qDto.getString("office_phone"))){//办公电话不能为空
			msgVo.setMsg(Apiconstant.Office_Phone_Is_Null.getName());
			msgVo.setCode(Apiconstant.Office_Phone_Is_Null.getIndex());
		} else if(AOSUtils.isEmpty(qDto.getString("contacts_name"))){//联系人不能为空
			msgVo.setMsg(Apiconstant.Contacts_Name_Is_Null.getName());
			msgVo.setCode(Apiconstant.Contacts_Name_Is_Null.getIndex());
		} else if(AOSUtils.isEmpty(qDto.getString("contacts_phone"))){//联系电话不能为空
			msgVo.setMsg(Apiconstant.Contacts_Phone_Is_Null.getName());
			msgVo.setCode(Apiconstant.Contacts_Phone_Is_Null.getIndex());
		} else if(AOSUtils.isEmpty(qDto.getString("business_licence_number_elc")) || AOSUtils.isEmpty(qDto.getString("food_hygiene_img"))){//营业执照和身份证照必须不能为空
			msgVo.setMsg(Apiconstant.Bussiness_Or_Foods_Not_All_Null.getName());
			msgVo.setCode(Apiconstant.Bussiness_Or_Foods_Not_All_Null.getIndex());
		} else if(AOSUtils.isEmpty(qDto.getString("cat_id"))){//请选择商品分类
			msgVo.setMsg(Apiconstant.Cat_Id_Is_Wrong.getName());
			msgVo.setCode(Apiconstant.Cat_Id_Is_Wrong.getIndex());
		} else {//查询当前企业名称是否存在
			ZjcStorePO store = new ZjcStorePO();
			store.setStore_name(qDto.getString("store_name"));
			ZjcUsersInfoPO userinfo = zjcUsersInfoDao.selectByKey(qDto.getBigInteger("user_id"));
			//判断商家账号是否存在
			ZjcSellerInfoPO oldsellerInfo = zjcSellerInfoDao.selectOne(Dtos.newDto("mobile", userinfo.getMobile()));
			if(AOSUtils.isNotEmpty(oldsellerInfo)){
				ZjcStorePO oldStore = zjcStoreDao.selectByKey(oldsellerInfo.getStore_id());
				if(oldStore.getStore_state() != 4){
					store = new ZjcStorePO();
					store.copyProperties(qDto);
					store.setStore_id(oldsellerInfo.getStore_id());
					//store.setAdd_time(new Date());
					store.setLast_login_time(new Date());
					store.setLast_login_ip(ParameterUtil.getIpAddr(httpModel.getRequest()));
					store.setUser_id(oldsellerInfo.getUser_id());//关联商家用户ID
					store.setStore_state(1);//店铺待审核
					//获取省名字
					String province_name = apiPublicService.getAddressName(qDto.getInteger("province_id"));
					//获取市名字
					String city_name = apiPublicService.getAddressName(qDto.getInteger("city_id"));
					//获取区名字
					String district_name = apiPublicService.getAddressName(qDto.getInteger("area_id"));
					store.setArea_info(province_name + city_name + district_name);
					zjcStoreDao.updateByKey(store);
					msgVo.setCode(Apiconstant.Do_Success.getIndex());
					msgVo.setMsg(Apiconstant.Do_Success.getName());
				} else {//商家账号被冻结或店铺已关闭，请联系客服
					msgVo.setCode(Apiconstant.Store_Is_Lock_Or_Close.getIndex());
					msgVo.setMsg(Apiconstant.Store_Is_Lock_Or_Close.getName());
				}
			} else {
				List<ZjcStorePO> storeList = zjcStoreDao.selectStoreByName(store);
				if(!AOSUtils.isEmpty(storeList)){//当前企业名称已存在
					msgVo.setCode(Apiconstant.Store_Was_Existed.getIndex());
					msgVo.setMsg(Apiconstant.Store_Was_Existed.getName());
				} else {//新增店铺记录，新增商家记录
					ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectByKey(qDto.getBigInteger("user_id"));
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("mobile", userInfo.getMobile());
					//map.put("recommended_code", userInfo.getUser_id());
					//map.put("is_lock", 1);
					List<ZjcSellerInfoPO> isSellerList = zjcSellerInfoDao.list(Dtos.newDto(map));
					if(isSellerList.size()>0){//你已经开过店铺，不能开通多个店铺
						msgVo.setCode(Apiconstant.User_Has_A_Store.getIndex());
						msgVo.setMsg(Apiconstant.User_Has_A_Store.getName());
					} else {
						if(!AOSUtils.isEmpty(userInfo)){//审核通过，新增商家记录
							ZjcSellerInfoPO sellerInfo = new ZjcSellerInfoPO();
							sellerInfo.copyProperties(userInfo);
							BigInteger seller_id =  idService.nextValue(SystemCons.SEQ.SEQ_USER);
							sellerInfo.setUser_id(seller_id);//设置商家ID
							sellerInfo.setRecommended_code(userInfo.getUser_id().toString());//设置商家的会员推荐码为该商家的会员ID
							sellerInfo.setReg_time(new Date());
							sellerInfo.setStore_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
							sellerInfo.setIs_lock(1);//设置商家状态为锁定
							System.out.println("sellerinfo:" + AOSJson.toJson(sellerInfo));
							try {
								zjcSellerInfoDao.insert(sellerInfo);//新增商家记录
								//新增商家记录成功,则新增商家账户记录
								ZjcUsersAccountInfoPO accountInfo = zjcUsersAccountInfoDao.selectByKey(qDto.getBigInteger("user_id"));
								ZjcUsersAccountInfoPO newAccount = new ZjcUsersAccountInfoPO();
								newAccount.copyProperties(accountInfo);
								//新增的商家用户可转可可用易物劵置零
								newAccount.setPay_points(0);
								newAccount.setMake_over_integral(0);
								newAccount.setPay_points(0);
								newAccount.setFrozen_money(new BigDecimal(0));
								newAccount.setDistribut_money(new BigDecimal(0));
								newAccount.setTotal_amount("0");
								newAccount.setWallet_quota("0");
								newAccount.setSum(0);
								newAccount.setUser_money(new BigDecimal(0));
								newAccount.setReg_giving_points("0");
								newAccount.setReturn_points("0");
								newAccount.setDue_tc_points("0");
								newAccount.setPractical_tc_points("0");
								newAccount.setCount_wallet_quota(0);
								newAccount.setPending_transfer(new BigDecimal(0));
								newAccount.setTransferred(new BigDecimal(0));
								newAccount.setUser_id(seller_id);//设置账户user_id为商家ID
								try{
									zjcUsersAccountInfoDao.insert(newAccount);//新增商家账户记录
									store = new ZjcStorePO();
									store.copyProperties(qDto);
									store.setStore_id(sellerInfo.getStore_id());
									store.setAdd_time(new Date());
									store.setLast_login_time(new Date());
									store.setLast_login_ip(ParameterUtil.getIpAddr(httpModel.getRequest()));
									store.setUser_id(seller_id);//关联商家用户ID
									store.setStore_state(1);//店铺待审核
									//获取省名字
									String province_name = apiPublicService.getAddressName(qDto.getInteger("province_id"));
									//获取市名字
									String city_name = apiPublicService.getAddressName(qDto.getInteger("city_id"));
									//获取区名字
									String district_name = apiPublicService.getAddressName(qDto.getInteger("area_id"));
									store.setArea_info(province_name + city_name + district_name);
									try {
										sqlDao.insert("com.zjc.store.dao.ZjcStoreDao.insert", store);
										msgVo.setCode(Apiconstant.Do_Success.getIndex());
										msgVo.setMsg(Apiconstant.Do_Success.getName());
									} catch(Exception e){//新增店铺信息失败
										zjcSellerInfoDao.deleteByKey(seller_id);
										zjcUsersAccountInfoDao.deleteByKey(seller_id);
										msgVo.setCode(Apiconstant.Save_fails.getIndex());
										msgVo.setMsg(Apiconstant.Save_fails.getName());
										e.printStackTrace();
									}
								} catch(Exception e){//新增商家账户记录失败
									zjcSellerInfoDao.deleteByKey(seller_id);
									msgVo.setCode(Apiconstant.Seller_Account_Save_Fails.getIndex());
									msgVo.setMsg(Apiconstant.Seller_Account_Save_Fails.getName());
									e.printStackTrace();
								}
							}catch(Exception e){
								msgVo.setCode(Apiconstant.Seller_Info_Save_Fails.getIndex());
								msgVo.setMsg(Apiconstant.Seller_Info_Save_Fails.getName());
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 *	 检查商户
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  checkShop(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO = new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("sellerId"))){
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		}else if(!ApiPublicService.isInteger(dto.getString("sellerId"))){//检查是否整数
			msgVO.setCode(Apiconstant.ILLEGAL_PARAMETER.getIndex());
			msgVO.setMsg("商家ID不符合规范，请保证为纯数字");
		}else{
			dto.put("user_id", dto.getBigInteger("sellerId"));
			dto.put("token", null);
			ZjcSellerInfoPO sellerInfoPO = zjcSellerInfoDao.selectOne(dto);
			if(AOSUtils.isEmpty(sellerInfoPO)){
				msgVO.setCode(Apiconstant.No_Seller.getIndex());
				msgVO.setMsg(Apiconstant.No_Seller.getName());
			}else{
				dto.put("user_id", sellerInfoPO.getUser_id().toString());
				ZjcUsersAccountInfoPO usersAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(sellerInfoPO.getUser_id());
				if(AOSUtils.isEmpty(usersAccountInfoPO)){
					msgVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
					msgVO.setMsg(Apiconstant.Username_Not_Exist.getName());
				}else{
					ZjcStorePO storePO = zjcStoreDao.selectByUserId(dto.getInteger("user_id"));
					if(AOSUtils.isEmpty(storePO)){
						msgVO.setCode(Apiconstant.Store_Not_Exist.getIndex());
						msgVO.setMsg(Apiconstant.Store_Not_Exist.getName());
					}else{
						msgVO.setCode(Apiconstant.Do_Success.getIndex());
						msgVO.setMsg(Apiconstant.Do_Success.getName());
						Map<String, Object> out = new HashMap<>();
						out.put("store_id", sellerInfoPO.getUser_id());
						if(storePO.getStore_logo() == null){
							out.put("store_logo", "");
						}else{
							out.put("store_logo", storePO.getStore_logo());
						}
						out.put("hasTerminal", usersAccountInfoPO.getHas_terminal());
						out.put("store_name", storePO.getStore_name());
						msgVO.setData(out);
					}
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * 判断是否有开通店铺
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getStoreDetail(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		}
		qDto.put("user_id", httpModel.getAttribute("user_id"));
		ZjcUsersInfoPO userinfo = zjcUsersInfoDao.selectByKey(qDto.getBigInteger("user_id"));
		Dto outDto = sqlDao.selectDto("com.zjc.store.dao.ZjcSellerInfoDao.isExitStore", Dtos.newDto("mobile", userinfo.getMobile()));
		if(AOSUtils.isEmpty(outDto)){//没有开通店铺
			msgVO.setCode(Apiconstant.Store_Not_Opened.getIndex());
			msgVO.setMsg(Apiconstant.Store_Not_Opened.getName());
			return AOSJson.toJson(msgVO);
		}
		ZjcRegionPO region = (ZjcRegionPO) sqlDao.selectOne("com.zjc.region.dao.ZjcRegionDao.selectOne", Dtos.newDto("id", outDto.getInteger("province_id")));
		outDto.put("province_name", region.getName());//获取省名字
		region = (ZjcRegionPO) sqlDao.selectOne("com.zjc.region.dao.ZjcRegionDao.selectOne", Dtos.newDto("id", outDto.getInteger("city_id")));
		outDto.put("city_name", region.getName());//获取市名字
		region = (ZjcRegionPO) sqlDao.selectOne("com.zjc.region.dao.ZjcRegionDao.selectOne", Dtos.newDto("id", outDto.getInteger("area_id")));
		outDto.put("area_name", region.getName());//获取区名字
		ZjcGoodsCategoryPO categoryPo = (ZjcGoodsCategoryPO) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsCategoryDao.selectOne", Dtos.newDto("id", outDto.getInteger("cat_id")));
		if(AOSUtils.isEmpty(categoryPo)){
			outDto.put("cat_name", "暂无分类");//获取店铺分类名词 
		} else {
			outDto.put("cat_name", categoryPo.getName());//获取店铺分类名词 
		}
		msgVO.setCode(Apiconstant.Do_Success.getIndex());
		msgVO.setMsg(Apiconstant.Do_Success.getName());
		msgVO.setData(outDto);
		return AOSJson.toJson(msgVO);
	}
}
