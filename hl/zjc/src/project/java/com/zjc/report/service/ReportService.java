/**
 * 
 */
package com.zjc.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.demo.dao.DemoAccountDao;
import aos.demo.dao.po.DemoAccountPO;
import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

import com.zjc.report.util.AOSPrint;
import com.zjc.report.util.AOSReport;
import com.zjc.report.util.AOSReportModel;
import com.zjc.users.dao.po.ZjcUserLogPO;

/**
 * @author Administrator
 *
 */
@Service(value="reportService")
public class ReportService {
	
	@Autowired
	private DemoAccountDao demoAccountDao;
	@Autowired
	private SqlDao sqlDao;
	
	/**
	 * 基本报表页面初始化
	 * 
	 * @param httpModel
	 */
	public void initReport1(HttpModel httpModel) {
		httpModel.setAttribute("juid", httpModel.getUserModel().getJuid());
		httpModel.setViewPath("project/zjc/report/report1.jsp");
	}
	
	/**
	 * 查询信用卡账户列表
	 * @param httpModel
	 */
	public void listAccounts(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<DemoAccountPO> list = demoAccountDao.listPage(inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list, inDto.getPageTotal()));
	}
	
	
	/**
	 * 填充报表
	 * @param httpModel
	 */
	public void fillReport(HttpModel httpModel) {
		//组装报表数据模型
		AOSReportModel reportModel = new AOSReportModel();
		Dto parameters = Dtos.newDto();
		parameters.put("create_user", httpModel.getUserModel().getName());
		//设置报表参数型
		reportModel.setParametersDto(parameters);
		Dto inDto = Dtos.newDto();
		inDto.setPageStart(0);
		inDto.setPageLimit(25);
		//只获取25条作为报表的演示数据
		List<DemoAccountPO> list = demoAccountDao.listPage(inDto);
		for (DemoAccountPO demoAccountPO : list) {
			demoAccountPO.setCard_type(WebCxt.getDicCodeDesc("card_type", demoAccountPO.getCard_type()));
		}
		//设置报表集合
		reportModel.setFieldsList(list);
		//设置报表模版的编译文件
		reportModel.setJasperFile(httpModel.getRequest().getSession().getServletContext().getRealPath("/WEB-INF/template/report/AccountDetails.jasper"));
		//填充报表
		AOSPrint aosPrint = AOSReport.fillReport(reportModel);
		//这个设置主要是下载报表文件时候使用的缺省文件名
		aosPrint.setFileName("信用卡账户信息报表");
		//设置缺省的AOSPrint对象到会话中。
		httpModel.getRequest().getSession().setAttribute(AOSReport.DEFAULT_AOSPRINT_KEY, aosPrint);
		WebCxt.write(httpModel.getResponse(), AOSJson.toJson(Dtos.newOutDto()));
	}
	
	/**
	 * 基本报表页面初始化
	 * 
	 * @param httpModel
	 */
	public void initReportDemo(HttpModel httpModel) {
		httpModel.setAttribute("juid", httpModel.getUserModel().getJuid());
		httpModel.setViewPath("project/zjc/report/reportDemo.jsp");
	}
	
	/**
	 * 查询信用卡账户列表
	 * @param httpModel
	 */
	public void listLogs(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<ZjcUserLogPO> list = sqlDao.list("com.zjc.users.dao.ZjcUserLogDao.demo", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(list, inDto.getPageTotal()));
	} 
	
	/**
	 * 填充报表
	 * @param httpModel
	 */
	public void fillReportDemo(HttpModel httpModel) {
		//组装报表数据模型
		AOSReportModel reportModel = new AOSReportModel();
		Dto parameters = Dtos.newDto();
		parameters.put("create_user", httpModel.getUserModel().getName());
		//设置报表参数型
		reportModel.setParametersDto(parameters);
		List<Dto> list = sqlDao.list("com.zjc.users.dao.ZjcUserLogDao.demo", null);
		//设置报表集合
		reportModel.setFieldsList(list);
		//设置报表模版的编译文件
		reportModel.setJasperFile(httpModel.getRequest().getSession().getServletContext().getRealPath("/WEB-INF/template/report/demo1.jasper"));
		//填充报表
		AOSPrint aosPrint = AOSReport.fillReport(reportModel);
		//这个设置主要是下载报表文件时候使用的缺省文件名
		aosPrint.setFileName("用户操作日志报表");
		//设置缺省的AOSPrint对象到会话中。
		httpModel.getRequest().getSession().setAttribute(AOSReport.DEFAULT_AOSPRINT_KEY, aosPrint);
		WebCxt.write(httpModel.getResponse(), AOSJson.toJson(Dtos.newOutDto()));
	}
}
