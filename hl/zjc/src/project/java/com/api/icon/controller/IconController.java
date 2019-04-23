/**
 * 
 */
package com.api.icon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.asset.WebCxt;
import aos.framework.web.router.HttpModel;

import com.api.icon.ZjcIconService;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class IconController {
	@Autowired
	private ZjcIconService ZjcIconService;
	/**
	 * @api {get} notokenapi/app/v1/getIcon.jhtml 获取图片列表
	 * @apiName 获取图片列表
	 * @apiGroup icon
	 *
	 */
	//获取图片列表
	@RequestMapping(value = "/app/v1/getIcon")
	public void getIcon(HttpServletRequest request, HttpServletResponse response){
			HttpModel httpModel = new HttpModel(request, response);
			String outMsg =ZjcIconService.getIcon(httpModel);
			WebCxt.write(response, outMsg);
		}
}
