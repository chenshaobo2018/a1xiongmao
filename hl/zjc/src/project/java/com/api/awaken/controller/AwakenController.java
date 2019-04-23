/**
 * 
 */
package com.api.awaken.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.web.router.HttpModel;

/**
 * 唤醒APP
 * 
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/awaken")
public class AwakenController {
	
	@RequestMapping(value = "/initAwaken")
	public String initStoreLogin(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);		
		return "project/zjc/webpage/awakenApp.jsp";
	}
	
}
