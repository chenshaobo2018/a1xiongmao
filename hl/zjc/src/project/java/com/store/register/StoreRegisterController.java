/**
 * 
 */
package com.store.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/store")
public class StoreRegisterController {
	
	@RequestMapping(value = "/initRegister")
	public String initStoreLogin(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		return "project/store/mct_shop_register.jsp";
	}
}
