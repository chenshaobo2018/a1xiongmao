/**
 * 
 */
package com.zjc.system.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;
import aos.system.dao.AosSequenceDao;
import aos.system.dao.po.AosSequencePO;

import com.zjc.system.dao.ZjcMemberParameterDao;
import com.zjc.system.dao.po.ZjcMemberParameterPO;

/**
 * @author Administrator
 *会员配置页面
 */
@Service(value="ZjcMemberParameter")
public class ZjcMemberParameterService {
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcMemberParameterDao ZjcMemberParameterDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private AosSequenceDao aosSequenceDao;
	
	/**
	 * 会员配置页面跳转
	 * @param httpModel
	 */
	public void initUserList(HttpModel httpModel){
			httpModel.setViewPath("project/zjc/system/zjcMemberOarameter.jsp");
		}

	
	/**
	 * 
	 * 新增会员配置
	 * @param httpModel
	 */
	public void saveAdposition(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcMemberParameterPO ZjcMemberParameterPO = new ZjcMemberParameterPO();
		Date date=new Date();
		ZjcMemberParameterPO.copyProperties(inDto);
		ZjcMemberParameterPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		ZjcMemberParameterPO.setData_time(date);
		ZjcMemberParameterDao.insert(ZjcMemberParameterPO);
		AosSequencePO secquence  =  (AosSequencePO)sqlDao.selectOne("aos.system.dao.AosSequenceDao.selectOne", Dtos.newDto("name", "seq_user"));
		secquence.setIncrement(inDto.getInteger("menber_id"));
		aosSequenceDao.updateByKey(secquence);//修改会员跳段
		outDto.setAppMsg("会员配置新增成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 会员配置页面显示查询
	 * 
	 * @param httpModel
	 */
	public void getMemberParameter(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcMemberParameterPO> paramDtos = sqlDao.list("com.zjc.system.dao.ZjcMemberParameterDao.selectByMaxKey",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 会员配置页面显示查询
	 * 
	 * @param httpModel
	 */
	public void getNewMemberParameter(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		ZjcMemberParameterPO paramDtos = (ZjcMemberParameterPO) sqlDao.selectOne("com.zjc.system.dao.ZjcMemberParameterDao.selectByMaxKey",qDto);
		httpModel.setOutMsg(AOSJson.toJson(paramDtos, "YYYY-MM-dd HH:mm:ss"));
	}
}
