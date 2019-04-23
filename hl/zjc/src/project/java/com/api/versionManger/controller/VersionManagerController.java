package com.api.versionManger.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.versionManger.VersionManagerService;


/**
 * app接口管理控制器
 * 
 * @author wgm
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class VersionManagerController {
	private static final Logger logger = LoggerFactory.getLogger(VersionManagerController.class);

	@Autowired
	private VersionManagerService versionManagerService;
	
	/**
	 * app接口-获取接口列表数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/app/v1/interfaceList")
	public void getSystemInterfaceList(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		//获取返回数据
	    String outMsg = versionManagerService.getSystemInterfaceList(request);
	    WebCxt.write(response, outMsg);
	}
}