/**
 * 
 */
package com.zjc.users.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/notokenapi")
public class ZjcComplaintsContrller {
	@Autowired
	private IdService idService; 
	@Autowired
	private ZjcAfterSalesRecordService ZjcAfterSalesRecordService; 
	
	
	@RequestMapping(value = "/addOrUpdateAfterSales")
	public void addOrUpdateAfterSales(HttpServletRequest request, HttpServletResponse response){
		    HttpModel httpModel = new HttpModel(request, response);
		    ZjcAfterSalesRecordService.addOrUpdateAfterSales(httpModel);
	}
}
