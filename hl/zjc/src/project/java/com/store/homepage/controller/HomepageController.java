/**
 * 
 */
package com.store.homepage.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.homepage.HomePageVO;
import com.store.homepage.service.HomePageService;
import com.store.goods.service.StoreGoodsService;
import com.zjc.common.util.QiniuUtil;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStorePO;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSPropertiesHandler;
import aos.framework.core.utils.AOSUtils;
import aos.framework.core.utils.FileUtils;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "/store")
public class HomepageController {
	@Autowired
	private HomePageService homePageService;
	@Autowired
	private MinImg MinImg;
	
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	@RequestMapping(value = "/homepage")
	public String brand_list(HttpServletRequest request, HttpServletResponse response) throws  ParseException{
		HttpModel httpModel = new HttpModel(request, response);
		HomePageVO homepage =homePageService.getStore(httpModel);
		request.setAttribute("homepage", homepage);
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		//查询店铺信息
		ZjcStorePO zjcStorePO = zjcStoreDao.selectByKey(sellerInfoPO.getStore_id());
		request.setAttribute("zjcStorePO", zjcStorePO);
		return "/project/store/index.jsp";
	}
	/**
	 * 商家转账数据
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/app/v1/transfer")
	public String transfer(HttpServletRequest request, HttpServletResponse response) throws  ParseException{
		HttpModel httpModel = new HttpModel(request, response);
		HomePageVO homepage =homePageService.transfer(httpModel);
		request.setAttribute("homepage", homepage);
		return "/project/store/index.jsp";
	}
	
	@RequestMapping(value = "/app/v1/userid")
	public String userid(HttpServletRequest request, HttpServletResponse response) throws  ParseException{
		HttpModel httpModel = new HttpModel(request, response);
		String mag =homePageService.userid(httpModel);
		WebCxt.write(response, mag);
		return httpModel.getViewPath();
	}

	
	@RequestMapping(value = "/app/v1/getrealname")
	public String getrealname(HttpServletRequest request, HttpServletResponse response) throws  ParseException{
		HttpModel httpModel = new HttpModel(request, response);
		String mag =homePageService.getrealname(httpModel);
		WebCxt.write(response, mag);
		return httpModel.getViewPath();
	}
	
	@RequestMapping(value = "/app/v1/getmobile")
	public String getmobile(HttpServletRequest request, HttpServletResponse response) throws  ParseException{
		HttpModel httpModel = new HttpModel(request, response);
		String mag =homePageService.getmobile(httpModel);
		WebCxt.write(response, mag);
		return httpModel.getViewPath();
	}
	
	/**
	 * 使用img src 提交上传截取之后的图片
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "uploadBase64")
	public void uploadBase64(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpModel httpModel = new HttpModel(request, response);
		String directory="";
		String savePath = httpModel.getRequest().getServletContext().getRealPath(directory)
				+ File.separator + AOSPropertiesHandler.getProperty("fileUploadPath")
				+ File.separator + "img" + File.separator + AOSUtils.getDateStr();
		String base64 = request.getParameter("img");
		File serverFile = FileUtils.convertBase64DataToImage(base64,savePath);
        String FilePath = serverFile.getPath();
        String key = FilePath.substring(FilePath.lastIndexOf("\\")+1);
        String min_FilePath=MinImg.thumbnailImage(FilePath, 100, 150,"min_",false);
        String min_key =min_FilePath.substring(min_FilePath.lastIndexOf("\\")+1);
        String outrl = QiniuUtil.upload(FilePath, key);
        String min_outrl = QiniuUtil.upload(min_FilePath, min_key);
        Map<String, String> map = new HashMap<String, String>();
        map.put("path", outrl);
        response.getWriter().print(AOSJson.toJson(map));
	}

}
