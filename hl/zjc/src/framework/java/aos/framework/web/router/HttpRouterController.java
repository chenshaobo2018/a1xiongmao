package aos.framework.web.router;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import aos.framework.core.asset.AOSBeanLoader;
import aos.framework.core.asset.WebCxt;
import aos.framework.core.exception.AOSException;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSPropertiesHandler;
import aos.framework.core.utils.AOSUtils;
import aos.framework.core.utils.FileUtils;

import com.zjc.common.util.QiniuUtil;

/**
 * HTTP路由转发器&服务输出包装类
 * 
 * @author xiongchun
 *
 */
@Controller
@RequestMapping(value = "http")
@SuppressWarnings("all")
public class HttpRouterController {

	private static final Logger logger = LoggerFactory.getLogger(HttpRouterController.class);

	/**
	 * HTTP路由转发器<br>
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "do")
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		String router = request.getHeader("router");
		
		router = AOSUtils.isEmpty(router) ? httpModel.getInDto().getString("router") : router;
		try {
			if (AOSUtils.isEmpty(router) || StringUtils.lastIndexOf(router, ".") == StringUtils.INDEX_NOT_FOUND) {
				throw new AOSException("请求路由规则参数缺失或路由规则不合法，请检查。" );
			}
			String[] routerArr = StringUtils.split(router, ".");
			if (routerArr.length != 2) {
				throw new AOSException("请求路由规则参数缺失或路由规则不合法，请检查。");
			}
			Object object = AOSBeanLoader.getSpringBean(routerArr[0]);
			Class c = object.getClass();
			Method method = c.getMethod(routerArr[1], httpModel.getClass());
			Object resultObj = method.invoke(object, httpModel);
		} catch (InvocationTargetException e) {
			throw new AOSException("系统服务出错，请稍后再试。", e);
		} catch (NoSuchMethodException e) {
			throw new AOSException(AOSUtils.merge("没有找到[{0}]对应的方法，请确认路由信息是否正确。", router), e);
		} catch (Exception e) {
			throw new AOSException("系统繁忙，请稍后再试。", e);
		} finally {
			/*logger.info(AOSCons.CONSOLE_FLAG3 + "ClientIP：{}，URI：{}，ROUTER：{}，HttpModel：{} ", WebCxt.getClientIpAddr(request),
					request.getRequestURI(), router, httpModel.toJson());*/
		}
		if (AOSUtils.isNotEmpty(httpModel.getOutMsg())) {
			WebCxt.write(response, httpModel.getOutMsg());
		}
		return httpModel.getViewPath();
	}

	
	
	
	/**
	 * 对from 提交的input file 进行上传（未截图）
	 * @param fileUpload
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "fileUpload")
	public void uploadFile(@RequestParam MultipartFile fileUpload,HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpModel httpModel = new HttpModel(request, response);
		String directory="";
		String savePath = httpModel.getRequest().getServletContext().getRealPath(directory) 
				+ File.separator + AOSPropertiesHandler.getProperty("fileUploadPath")
				+ File.separator + "file" + File.separator + AOSUtils.getDateStr();
		File serverFile = FileUtils.fileUpload(savePath, fileUpload);
        response.getWriter().print(AOSJson.toJson(serverFile));  
	}
	
	/**
	 * 对from 提交的input file 进行上传（未截图）并上传至七牛云
	 * @param fileUpload
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "uploadImage")
	public void uploadImage(@RequestParam MultipartFile imgUpload,HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpModel httpModel = new HttpModel(request, response);
		String directory="";
		String savePath = httpModel.getRequest().getServletContext().getRealPath(directory) 
				+ File.separator + AOSPropertiesHandler.getProperty("fileUploadPath")
				+ File.separator + "img" + File.separator + AOSUtils.getDateStr();
		File serverFile = FileUtils.fileUpload(savePath, imgUpload);
		String FilePath = serverFile.getPath();
		System.out.println("第一个路径:"+FilePath);
		String key = FilePath.substring(FilePath.lastIndexOf("\\")+1);
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		FilePath=File.separator+"uploadfile"+File.separator+"img"+File.separator+sdf.format(d)+File.separator +key;
		FilePath = FilePath.replaceAll("\\\\", "/");
		String returnUrl = request.getScheme() + "://" +"47.111.10.202:8080"+request.getServletContext().getContextPath()+FilePath;
        Map<String, String> map = new HashMap<String, String>();
        map.put("path", returnUrl);
        response.getWriter().print(AOSJson.toJson(map));
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
        String outrl = QiniuUtil.upload(FilePath, key);
        Map<String, String> map = new HashMap<String, String>();
        map.put("path", outrl);
        response.getWriter().print(AOSJson.toJson(map));
	}
	
}
