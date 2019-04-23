/**
 * 
 */
package com.zjc.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.common.po.PageVO;
import com.api.order.OrderService;
import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;
import com.wxstore.service.WxStoreService;
import com.zjc.users.dao.po.ZjcMessagePO;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.dao.asset.DBDialectUtils;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSCxt;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.dao.AosUserDao;
import aos.framework.dao.po.AosUserPO;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.model.UserModel;
import aos.system.common.utils.ErrorCode;
import aos.system.common.utils.SystemCons;
import aos.system.common.utils.SystemUtils;
import aos.system.modules.cache.CacheUserDataService;


/**
 * @author Administrator
 *
 */
@Service(value="zjcHomeService")
public class ZjcHomeService {

	
	@Autowired
	private IdService idService;
	@Autowired
	private AosUserDao aosUserDao;
	@Autowired
	private CacheUserDataService cacheUserDataService;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private WxStoreService WxStoreService;
	@Autowired
	private OrderService OrderService;
	
	
	/**
	 * 登录页面初始化
	 * 
	 * @param httpModel
	 */
	public void initZjcLogin(HttpModel httpModel) {
		httpModel.setAttribute("vercode_characters", AOSCxt.getParam("vercode_characters"));
		httpModel.setAttribute("vercode_length", AOSCxt.getParam("vercode_length"));
		httpModel.setAttribute("is_show_vercode", AOSCxt.getParam("is_show_vercode"));
		// 用来标识登录页面校验验证码用的
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setViewPath("project/zjc/login/login.jsp");
	}
	
	/**
	 * 首页初始化
	 * 
	 * @param httpModel
	 */
	public void initZjcIndex(HttpModel httpModel){
		UserModel userModel = httpModel.getUserModel();
		// 构造卡片菜单
		httpModel.setAttribute("cardDtos", getCardListFromCache(userModel.getId()));
		List<Dto> cardDtos = getCardListFromCache(userModel.getId());
		httpModel.setAttribute("menuDtos", getCardTree(userModel.getId(),cardDtos));
		httpModel.setAttribute("user_name", userModel.getName());
		httpModel.setAttribute("org_name", userModel.getAosOrgPO().getName());
//		httpModel.setAttribute("navDto", initNavBarStyle(userModel.getSkin()));

		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));

		httpModel.setViewPath("project/zjc/index.jsp");
	}
	
	/**
	 * 用户登录
	 * 
	 * @param httpModel
	 * @return
	 */
	public void zjcLogin(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		Dto outDto = Dtos.newDto();
		outDto.setAppCode(AOSCons.SUCCESS);
		//验证码校验
		String cached_vercode_ = JedisUtil.getString(inDto.getString("vercode_uuid"));
		if (!StringUtils.equalsIgnoreCase(cached_vercode_, inDto.getString("vercode"))) {
			outDto.setAppCode(ErrorCode.VERCODE_ERROR);
			outDto.setAppMsg("验证码不对，请重新输入。");
			httpModel.setOutMsg(AOSJson.toJson(outDto));
			return;
		}

		// 帐号存在校验
		Dto qDto = Dtos.newDto("account", inDto.getString("account"));
		qDto.put("is_del", SystemCons.IS.NO);
		AosUserPO aosUserPO = aosUserDao.selectOne(qDto);
		Boolean is_pass = true;
		if (AOSUtils.isEmpty(aosUserPO)) {
			outDto.setAppCode(ErrorCode.ACCOUNT_ERROR);
			outDto.setAppMsg("帐号输入错误，请重新输入。");
			is_pass = false;
		} else {
			// 密码校验
			String password = AOSCodec.password(inDto.getString("password"));
			if (!StringUtils.equals(password, aosUserPO.getPassword())) {
				outDto.setAppCode(ErrorCode.PASSWORD_ERROR);
				outDto.setAppMsg("密码输入错误，请重新输入。");
				is_pass = false;
			} else {
				// 状态校验
				if (!aosUserPO.getStatus().equals(SystemCons.USER_STATUS.NORMAL)) {
					outDto.setAppCode(ErrorCode.LOCKED_ERROR);
					outDto.setAppMsg("该帐号已锁定或已停用，请联系管理员。");
					is_pass = false;
				}
			}
		}

		if (!is_pass) {
			httpModel.setOutMsg(AOSJson.toJson(outDto));
			return;
		}

		// 通过检查
		outDto.setAppCode(AOSCons.SUCCESS);
		String juid = cacheUserDataService.login(aosUserPO, httpModel.getRequest());
		outDto.put("juid", juid);
		httpModel.setOutMsg(AOSJson.toJson(outDto));

	}
	
	/**
	 * 获取用户权限菜单
	 * 
	 * @param inDto
	 * @return
	 */
	private List<Dto> getCardListFromCache(Integer user_id_) {
		final String cacheKey = SystemCons.KEYS.CARDLIST + user_id_;
		List<Dto> cardList = Lists.newArrayList();
		String cardListJson = JedisUtil.getString(cacheKey);
		if (AOSUtils.isNotEmpty(cardListJson)) {
			List<Map<String, String>> cardMapList = AOSJson.fromJson(cardListJson,
					new TypeToken<List<Map<String, String>>>() {
					}.getType());
			for (Map<String, String> map : cardMapList) {
				cardList.add(Dtos.newDto(map));
			}
		} else {
			Dto qDto = Dtos.newDto();
			qDto.put("user_id", user_id_);
			qDto.put("grant_type", SystemCons.GRANT_TYPE.BIZ);
			// 取CASCADE_ID长度为5的菜单出来作为卡片
			qDto.put("length", '5');
			qDto.put("fnLength", DBDialectUtils.fnLength(sqlDao.getDatabaseId()));
			cardList = sqlDao.list("Home.selectModulesOfUser", qDto);
			if (AOSUtils.isNotEmpty(cardList)) {
				JedisUtil.setString(cacheKey, AOSJson.toJson(cardList), 20);
			}
		}
		return cardList;
	}
	
	/**
	 * 获取菜单树
	 * 
	 * @param inDto
	 * @return
	 */
	public String getCardTree(Integer user_id_,List<Dto> cardDtos) {
		StringBuffer cardTree = new StringBuffer();
		final String cacheKey = SystemCons.KEYS.CARD_TREE + user_id_ + ".menu";
		String menuDtos = JedisUtil.getString(cacheKey);
		if(AOSUtils.isEmpty(menuDtos)){
			if(cardDtos.size() > 0 ){
				for(Dto cardDto :  cardDtos){
					String cascade_id_ = cardDto.getString("cascade_id");
					List<Dto> moduleList = null;
					Dto qDto = Dtos.newDto();
					qDto.put("user_id", user_id_);
					qDto.put("grant_type", SystemCons.GRANT_TYPE.BIZ);
					qDto.put("cascade_id", cascade_id_);
					moduleList = sqlDao.list("Home.selectModulesOfUser", qDto);
					if (AOSUtils.isNotEmpty(moduleList)) {
						cardTree.append(SystemUtils.toTreeModalAllInOne(moduleList));
					}
				}
			}
			menuDtos = cardTree.toString().replace("][", ",");
			JedisUtil.setString(cacheKey, menuDtos, 20);
		}
		return menuDtos;
	}
	
	/**
	 * 生成问候信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getWelcomeMsg() {
		String welcome = "晚上好";
		Integer timeInteger = new Integer(AOSUtils.getDateTimeStr("HH"));
		if (timeInteger.intValue() >= 7 && timeInteger.intValue() <= 12) {
			welcome = "上午好";
		} else if (timeInteger.intValue() > 12 && timeInteger.intValue() < 19) {
			welcome = "下午好";
		}
		return welcome;
	}
}
