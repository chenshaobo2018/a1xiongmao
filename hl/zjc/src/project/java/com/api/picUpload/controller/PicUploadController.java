/**
 * 
 */
package com.api.picUpload.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSPropertiesHandler;
import aos.framework.core.utils.AOSUtils;
import aos.framework.core.utils.FileUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.zjc.common.util.QiniuUtil;


/**
 * 文件上传接口
 * 
 * @author zc
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "notokenapi")
public class PicUploadController {
	

	/**
	 * @api {post} notokenapi/app/v1/picUpload.jhtml 文件上传
	 * @apiName 文件上传接口
	 * @apiGroup upload
	 * 
	 * @apiParam {File} fileUpload 
	 */
	/**
	 * 图片上传接口
	 * 
	 * @param fileUpload
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/app/v1/picUpload")
	public void picUpload(@RequestParam MultipartFile fileUpload,HttpServletRequest request, HttpServletResponse response) throws IOException{
		MessageVO messageVO = new MessageVO();
		HttpModel httpModel = new HttpModel(request, response);
		String directory="";
		String savePath = httpModel.getRequest().getServletContext().getRealPath(directory)
				+ File.separator + AOSPropertiesHandler.getProperty("fileUploadPath")
				+ File.separator + "img" + File.separator + AOSUtils.getDateStr();
		File serverFile = FileUtils.fileUpload(savePath, fileUpload);
		String FilePath = serverFile.getPath();
        String key = FilePath.substring(FilePath.lastIndexOf("\\")+1);
        String outrl = QiniuUtil.upload(FilePath, key);
        Map<String, String> map = new HashMap<String, String>();
        messageVO.setData(outrl);
        messageVO.setCode(Apiconstant.Do_Success.getIndex());
        messageVO.setMsg(Apiconstant.Do_Success.getName());
        String outMsg = AOSJson.toJson(messageVO);
		WebCxt.write(response, outMsg);
	}
}
