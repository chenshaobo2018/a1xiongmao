package aos.framework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import aos.framework.core.asset.AOSBeanLoader;
import aos.framework.core.asset.WebCxt;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStorePO;

/**
 * <b>API接口拦截器</b>
 * <p>
 * 拦截/api/*后台接口请求。无实现，可自行扩展。
 * 
 * @author xiongchun
 */
@SuppressWarnings("sellerapi")
public class HttpSellerApiFilter implements Filter {

	/**
	 * 初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * 过滤
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		response.setContentType("application/json; charset=UTF-8");
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpModel httpModel = new HttpModel(httpRequest, httpResponse);
		MessageVO msgVo = new MessageVO();
		String token = httpRequest.getParameter("token");
		String user_id = null;
		//验证是否在有效期内
		if(AOSUtils.isEmpty(token)){//判断token是否存在
			msgVo.setCode(Apiconstant.Token_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Token_Is_Null.getName());
			WebCxt.write(httpResponse,  AOSJson.toJson(msgVo).toString());
			httpModel.getViewPath();
			return;
		} else {
			user_id = token(token);
		}
		if(AOSUtils.isEmpty(user_id)){//有效期外
			msgVo.setCode(Apiconstant.Token_Is_Unused.getIndex());
			msgVo.setMsg(Apiconstant.Token_Is_Unused.getName());
			WebCxt.write(httpResponse,  AOSJson.toJson(msgVo).toString());
			httpModel.getViewPath();
			return;
		} else if(user_id.equals(Apiconstant.Token_Not_Exist.getName())){//token不存在，用户已在别处登陆
			msgVo.setCode(Apiconstant.Token_Not_Exist.getIndex());
			msgVo.setMsg(Apiconstant.Token_Not_Exist.getName());
			WebCxt.write(httpResponse,  AOSJson.toJson(msgVo).toString());
			httpModel.getViewPath();
			return;
		} else if(user_id.equals(Apiconstant.Token_Time_Is_Null.getName())){//token生效时间为空
			msgVo.setCode(Apiconstant.Token_Time_Is_Null.getIndex());
			msgVo.setMsg(Apiconstant.Token_Time_Is_Null.getName());
			WebCxt.write(httpResponse,  AOSJson.toJson(msgVo).toString());
			httpModel.getViewPath();
			return;
		} else if(user_id.equals(Apiconstant.Account_Was_Locked.getName())){//账号被冻结
			msgVo.setCode(Apiconstant.Account_Was_Locked.getIndex());
			msgVo.setMsg(Apiconstant.Account_Was_Locked.getName());
			WebCxt.write(httpResponse,  AOSJson.toJson(msgVo).toString());
			httpModel.getViewPath();
			return;
		} else {//有效期内
			ZjcSellerInfoDao zjcSellerInfoDao = (ZjcSellerInfoDao) AOSBeanLoader
					.getSpringBean("zjcSellerInfoDao");
			ZjcStoreDao zjcStoreDao = (ZjcStoreDao) AOSBeanLoader
					.getSpringBean("zjcStoreDao");
			ZjcSellerInfoPO seller = zjcSellerInfoDao.selectOne(Dtos.newDto("user_id",user_id));
			if(AOSUtils.isEmpty(seller)){
				msgVo.setCode(Apiconstant.Username_Not_Exist.getIndex());
				msgVo.setMsg(Apiconstant.Username_Not_Exist.getName());
				WebCxt.write(httpResponse,  AOSJson.toJson(msgVo).toString());
				httpModel.getViewPath();
				return;
			}
			if(seller.getIs_lock() == 1){
				msgVo.setCode(Apiconstant.Account_Was_Locked.getIndex());
				msgVo.setMsg(Apiconstant.Account_Was_Locked.getName());
				WebCxt.write(httpResponse,  AOSJson.toJson(msgVo).toString());
				httpModel.getViewPath();
				return;
			}
			ZjcStorePO store = zjcStoreDao.selectByKey(seller.getStore_id());
			if(AOSUtils.isEmpty(store)){
				msgVo.setCode(Apiconstant.Store_Not_Exist.getIndex());
				msgVo.setMsg(Apiconstant.Store_Not_Exist.getName());
				WebCxt.write(httpResponse,  AOSJson.toJson(msgVo).toString());
				httpModel.getViewPath();
				return;
			}
			if(store.getStore_state() != 3){//店铺未审核通过
				msgVo.setCode(Apiconstant.Store_Is_Audit_Or_Close.getIndex());
				msgVo.setMsg(Apiconstant.Store_Is_Audit_Or_Close.getName());
				WebCxt.write(httpResponse,  AOSJson.toJson(msgVo).toString());
				httpModel.getViewPath();
				return;
			}
			httpRequest.setAttribute("user_id", user_id);
			httpRequest.setAttribute("store_id", seller.getStore_id());
			chain.doFilter(httpRequest, httpResponse);
		}
		
		
	}
	
	/**
	 * token有效期验证
	 * 
	 * @param token
	 * @return
	 */
	public  String  token(String token){
		//取redis
		Jedis jedis = JedisUtil.getJedisClient();
		String userStr = jedis.get(token);
		String is_user_id = "";
		try{
			if(AOSUtils.isEmpty(userStr)){
				is_user_id = Apiconstant.Token_Not_Exist.getName();
			} else {
				String[] userArr = userStr.split("_");
				if("1".equals(userArr[1].toString())){//账号被冻结
					is_user_id = Apiconstant.Account_Was_Locked.getName();
				}else {
					if(AOSUtils.isEmpty(userArr[2].toString())){
						is_user_id = Apiconstant.Token_Time_Is_Null.getName();
					}
					//获取数据库的有效期
					int validate = Integer.parseInt(userArr[2].toString());
					int validateTime = new Long(ConstantUtil.TOKEN_VALIDATE_TIME).intValue();
					//获取当前时间(10位数的int型数字)
		            int nowdate= ParameterUtil.dateToInt();
					if(nowdate<=validate+validateTime){//token有效期内
						is_user_id = userArr[0].toString();
					} else {
						jedis.del(token);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return is_user_id;
	}

	/**
	 * 释放资源
	 */
	@Override
	public void destroy() {
	}

}
