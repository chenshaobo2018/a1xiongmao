/**
 * 
 */
package com.zjc.sms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.zjc.sms.dao.ZjcSmsLogDao;
import com.zjc.sms.dao.po.ZjcSmsLogPO;

/**
 * 短信日志
 * @author Administrator
 *
 */
@Service("smsService")
public class SmsService {

	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcSmsLogDao zjcSmsLogDao;
	
	@Autowired
	private IdService idService;
	
	/**
	 * 添加SmsLog
	 * @param httpModel
	 */
	public void saveSmsLog(Dto inDto){
		//Dto outDto = Dtos.newOutDto();
		ZjcSmsLogPO zjcSmsLogPO = new ZjcSmsLogPO();
		zjcSmsLogPO.copyProperties(inDto);
		zjcSmsLogPO.setAdd_time(new Date());
		zjcSmsLogPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		zjcSmsLogDao.insert(zjcSmsLogPO);
		//cacheMasterDataService.cacheParamOption(aos_paramsPO.getParams_key(), aos_paramsPO.getValue());
		//httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
}
