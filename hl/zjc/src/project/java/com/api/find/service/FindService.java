/**
 * 
 */
package com.api.find.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.api.find.dao.ZjcCpOrderDao;
import com.api.find.dao.ZjcCustomServiceDao;
import com.api.find.dao.ZjcFinanceToolDao;
import com.api.find.dao.po.ZjcCpOrderPO;
import com.api.find.dao.po.ZjcCustomServicePO;
import com.api.find.dao.po.ZjcFinanceToolPO;
import com.zjc.ad.dao.ZjcAdDao;
import com.zjc.article.dao.ZjcArticleDao;
import com.zjc.users.dao.ZjcMessageDao;
import com.zjc.users.dao.po.ZjcArticleVO;
import com.zjc.users.dao.po.ZjcMessagePO;

/**
 * @author pubing
 *
 */
@Service(value = "findService")
public class FindService {
	
	@Autowired
	private ZjcFinanceToolDao financeToolDao;
	
	@Autowired
	private ZjcArticleDao articleDao;
	
	@Autowired
	private ZjcMessageDao messageDao;
	
	@Autowired
	private ZjcCustomServiceDao customServiceDao;
	
	@Autowired
	private ZjcAdDao adDao;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcCpOrderDao cpOrderDao;
	/**
	 * 创富工具
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  getFinaceToolList(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel=new HttpModel(request, response);
		MessageVO messageVO=new MessageVO();
		Dto dto=httpModel.getInDto();
		List<ZjcFinanceToolPO> financeToolPOs = financeToolDao.list(dto);
		if(AOSUtils.isEmpty(financeToolPOs)){
			messageVO.setCode(Apiconstant.NO_DATA.getIndex());
			messageVO.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			messageVO.setCode(Apiconstant.Do_Success.getIndex());
			messageVO.setMsg(Apiconstant.Do_Success.getName());
			messageVO.setData(financeToolPOs);
		}
		return AOSJson.toJson(messageVO);
	}
	
	/**
	 * 获取新闻列表
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  getNewsList(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO = new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getInteger("page")) || dto.getInteger("page")<1){//当前页数不能为空
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			dto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = dto.getInteger("page");
			dto.put("start", (page-1)*ConstantUtil.pageSize);
			List<Dto> articlePOs = articleDao.listNewsPage(dto);
			if(AOSUtils.isEmpty(articlePOs)){
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
			}else{
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO( dto.getInteger("total"), dto.getInteger("page"));
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				pageVO.setList(articlePOs);
				msgVO.setData(pageVO);
			}
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * 平台通知列表
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  getSystemMsg(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO = new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getInteger("page"))||dto.getInteger("page")<1){//当前页数不能为空
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			dto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = dto.getInteger("page");
			dto.put("start", (page-1)*ConstantUtil.pageSize);
			List<Dto> messagePOs = messageDao.listAllPage(dto);
			if(AOSUtils.isEmpty(messagePOs)){
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
			}else{
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				pageVO.setList(messagePOs);
				msgVO.setData(pageVO);
			}
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * 系统消息详情
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  systemMsgDetail(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO = new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getInteger("message_id"))){
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		}
		List<ZjcMessagePO> messagePO = sqlDao.list("com.zjc.users.dao.ZjcMessageDao.selectOnes", dto);
		if(AOSUtils.isEmpty(messagePO)){
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			msgVO.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			messagePO.get(0).setMessage(ParameterUtil.createHtmlBody(messagePO.get(0).getTitle(), messagePO.get(0).getMessage()));
			msgVO.setData(messagePO.get(0));
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * 弹出消息
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  getAlertMsg(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO = new MessageVO();
		ZjcMessagePO messagePO=messageDao.alertMsg();
		if(AOSUtils.isEmpty(messagePO)){
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			msgVO.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			messagePO.setMessage(ParameterUtil.createHtmlBody(messagePO.getTitle(), messagePO.getMessage()));
			msgVO.setData(messagePO);
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * 文章详情 
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  articleDetail(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO = new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getInteger("article_id"))){//判断参数完整性
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		}
		ZjcArticleVO articleVO=articleDao.selectByKey1(dto.getInteger("article_id"));
		if(AOSUtils.isEmpty(articleVO)){
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			msgVO.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			articleVO.setContent(ParameterUtil.createHtmlBody(articleVO.getTitle(), articleVO.getContent()));
			msgVO.setData(articleVO);
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 *	 客服列表
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  getServiceList(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO = new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		//获取查询条件
		Dto dto = httpModel.getInDto();
		List<ZjcCustomServicePO> customServicePOs=customServiceDao.list(dto);
		if(AOSUtils.isEmpty(customServicePOs)){
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			msgVO.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			msgVO.setData(customServicePOs);
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 *	 判断用户是否购买企业宣传
	 * @param request
	 * @param response
	 * @return 
	 */
	public String  checkCPIsBuy(HttpServletRequest request, HttpServletResponse response){
		MessageVO msgVO = new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		//获取查询条件
		Dto dto = httpModel.getInDto();
		dto.put("user_id", request.getAttribute("user_id"));
		List<ZjcCpOrderPO> cpOrderPOs=cpOrderDao.selectByUserId(dto);
		if(cpOrderPOs.size() >= 1){
			msgVO.setCode(Apiconstant.Has_Buy_Cp.getIndex());
			msgVO.setMsg(Apiconstant.Has_Buy_Cp.getName());
			msgVO.setData(cpOrderPOs);
		}else{
			msgVO.setCode(Apiconstant.No_Buy_Cp.getIndex());
			msgVO.setMsg(Apiconstant.No_Buy_Cp.getName());
		}
		return AOSJson.toJson(msgVO);
	}
}
