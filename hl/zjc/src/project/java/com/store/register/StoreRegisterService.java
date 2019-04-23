/**
 * 
 */
package com.store.register;

import org.springframework.stereotype.Service;

import aos.framework.web.router.HttpModel;

/**
 * 申请开店
 * 
 * @author zc
 *
 */
@Service(value="storeRegisterService")
public class StoreRegisterService {

	/**
	 * 初始化商家注册页面
	 *
	 * @param httpModel
	 */
	public void initStoreRegister(HttpModel httpModel){
		httpModel.setViewPath("project/store/mct_shop_register.jsp");
	}
	
}
