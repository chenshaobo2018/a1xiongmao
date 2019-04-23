/**
 * 
 *//*
package com.store.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.goods.dao.po.ZjcGoodsCollectPO;
import com.taobao.api.ApiException;
import com.zjc.comment.dao.po.ZjcCommentPO;

*//**
 * 商家后台登录
 * 
 * @author zc
 *
 *//*
@Controller
@SuppressWarnings("all")
@RequestMapping("/store")
public class StoreLoginController {
	
	@Autowired
	private StoreLoginService storeLoginService;
	
	@Autowired
	private IdService idService;
	
	@RequestMapping(value = "/initStoreLogin")
	public String initStoreLogin(@ModelAttribute("outMsg") String outMsg,
			@ModelAttribute("account") String account, @ModelAttribute("password") String password,
			HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);		
//		JedisUtil.setString(idService.uuid(), verificationCode, 5 * 60);
		httpModel.setAttribute("outMsg", outMsg);
		httpModel.setAttribute("account", account);
		httpModel.setAttribute("password", password);
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		return "project/store/mct_login.jsp";
	}
	
	@RequestMapping(value = "/storeLogin")
	public void storeLogin(HttpServletRequest request, HttpServletResponse response,RedirectAttributes model){
		HttpModel httpModel = new HttpModel(request, response);
		Dto result = storeLoginService.login(httpModel);
		MessageVO msg = new MessageVO();
		if(result.getAppCode().equals(AOSCons.SUCCESS)){
			msg.setCode(Apiconstant.Do_Success.getIndex());
			
			return "redirect:homepage.jhtml";
		}else{
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			ZjcCommentPO commentPO = new ZjcCommentPO();
			commentPO.setUsername(httpModel.getRequest().getParameter("account"));
			commentPO.setIp_address(httpModel.getRequest().getParameter("password"));
			commentPO.setAgain_content(result.getAppMsg());
			msg.setData(commentPO);
			model.addFlashAttribute("outMsg", result.getAppMsg()); 
			model.addFlashAttribute("account",httpModel.getRequest().getParameter("account"));
			model.addFlashAttribute("password",httpModel.getRequest().getParameter("password"));
			M
			return "redirect:initStoreLogin.jhtml";
		}
		WebCxt.write(response, AOSJson.toJson(msg));
	}
	
	@RequestMapping(value = "/loginOut")
    public void loginOut(HttpServletRequest request, HttpServletResponse response) {  
        request.getSession().invalidate();  
        MessageVO msg = new MessageVO();
        msg.setCode(Apiconstant.Do_Success.getIndex());
        WebCxt.write(response, AOSJson.toJson(msg));
        //return "redirect:initStoreLogin.jhtml";
    }  
	
	//获取短信验证
	@RequestMapping(value = "/getVerificationCode")
    public void  getVerificationCode(HttpServletRequest request, HttpServletResponse response) throws ApiException {  
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		WebCxt.write(response, storeLoginService.getVerificationCode(httpModel));
    } 
	
	//验证短信验证码
	@RequestMapping(value = "/validate_code")
	public void validate_code(HttpServletRequest request, HttpServletResponse response) throws ApiException {  
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		WebCxt.write(response, storeLoginService.validate_code(httpModel));
	} 
	
	//确认重置密码
	@RequestMapping(value = "/confirm_reset")
	public void confirm_reset(HttpServletRequest request, HttpServletResponse response) throws ApiException {  
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		WebCxt.write(response, AOSJson.toJson(storeLoginService.confirm_reset(httpModel)));
	} 
}
*/