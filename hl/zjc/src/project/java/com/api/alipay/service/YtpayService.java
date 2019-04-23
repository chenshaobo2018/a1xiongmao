/**
 * 
 */
package com.api.alipay.service;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCodec;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.Apiconstant;
import com.api.alipay.dao.ZjcPayBankDao;
import com.api.alipay.dao.po.ZjcPayBankPO;
import com.api.common.po.MessageVO;
import com.api.common.util.HttpClientUtil;
import com.gexin.fastjson.JSONObject;
import com.zjc.users.dao.ZjcUserLogDao;
import com.zjc.users.dao.ZjcUserOperateLogDao;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.po.ZjcUserLogPO;
import com.zjc.users.dao.po.ZjcUserOperateLogPO;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Service(value = "YtpayService")
public class YtpayService {
	
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcPayBankDao ZjcPayBankDao;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcUsersAccountInfoDao ZjcUsersAccountInfoDao;
	@Autowired
	private ZjcUserOperateLogDao ZjcUserOperateLogDao;
	@Autowired
	private ZjcUserLogDao ZjcUserLogDao;
	
	
	/**
	 * 银行卡添加绑定
	 * @param httpModel
	 * @return
	 */
	public String bankSave(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO();
		Dto inDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVo);
		}
		inDto.put("user_id", httpModel.getAttribute("user_id"));
		Map<String,Object> accountmap = new HashMap<String,Object>();
		accountmap.put("bank_card", inDto.getString("bank_card"));
		accountmap.put("is_delete", 1);
		ZjcPayBankPO oldPayBankPO = ZjcPayBankDao.selectOne(Dtos.newDto(accountmap));
		if(AOSUtils.isNotEmpty(oldPayBankPO)){//该银行卡已绑定，不能重复绑定
			msgVo.setCode(Apiconstant.Bank_Is_Bind.getIndex());
			msgVo.setMsg(Apiconstant.Bank_Is_Bind.getName());
			return AOSJson.toJson(msgVo);
		}
		if(AOSUtils.isNotEmpty(inDto.getString("pay_default"))){//绑定同时设置为默认
			if(inDto.getInteger("pay_default") == 1){//如果要绑定同时设置为默认卡号
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("pay_default", 1);
				map.put("is_delete", 1);//表示未删除
				map.put("user_id", httpModel.getAttribute("user_id"));
				ZjcPayBankPO defaultPayBankPO = ZjcPayBankDao.selectOne(Dtos.newDto(map));
				if(AOSUtils.isNotEmpty(defaultPayBankPO)){//如果该用户有默认绑定的银行卡，则取消该默认
					defaultPayBankPO.setPay_default(0);//设置为不默认
					ZjcPayBankDao.updateByKey(defaultPayBankPO);
				}
			}
		}
		ZjcPayBankPO ZjcPayBankPO =new ZjcPayBankPO();
		ZjcPayBankPO.copyProperties(inDto);
		int id=idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue();
		ZjcPayBankPO.setBank_id(String.valueOf(id));
		ZjcPayBankPO.setIs_delete(1);
		int row=ZjcPayBankDao.insert(ZjcPayBankPO);
		if(row>0){
			msgVo.setData("");
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}else {
			msgVo.setData("");
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msgVo);
	}
   
	
	

	/**
	 * 银行卡查询
	 * @param httpModel
	 * @return
	 */
	public String queryBank(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO();
		Dto inDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVo);
		}
		inDto.put("user_id", httpModel.getAttribute("user_id"));
		List<ZjcPayBankPO> ZjcPayBankPO = sqlDao.list("com.api.alipay.dao.ZjcPayBankDao.list",inDto);
		if(ZjcPayBankPO.size()>0){
			msgVo.setData(ZjcPayBankPO);
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}else {
			msgVo.setData("");
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * 修改银行卡
	 * @param httpModel
	 * @return
	 */
	public String updataBank(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO();
		Dto inDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(inDto.getString("bank_id"))||AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVo);
		}
		inDto.put("user_id", httpModel.getAttribute("user_id"));
		if(AOSUtils.isNotEmpty(inDto.getString("pay_default"))){
			if(inDto.getInteger("pay_default") == 1){//如果要修改该银行卡为默认卡号
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("pay_default", 1);
				map.put("is_delete", 1);//表示不删除
				map.put("user_id", httpModel.getAttribute("user_id"));
				ZjcPayBankPO defaultPayBankPO = ZjcPayBankDao.selectOne(Dtos.newDto(map));
				if(AOSUtils.isNotEmpty(defaultPayBankPO)){//如果该用户有默认绑定的银行卡，则取消该默认
					defaultPayBankPO.setPay_default(0);//设置为不默认
					ZjcPayBankDao.updateByKey(defaultPayBankPO);
				}
			}
		}
		ZjcPayBankPO ZjcPayBankPO =new ZjcPayBankPO();
		ZjcPayBankPO.copyProperties(inDto);
		int row=ZjcPayBankDao.updateByKey(ZjcPayBankPO);
		if(row>0){
			msgVo.setData("");
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}else {
			msgVo.setData("");
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msgVo);
	}
	
	
	/**
	 * 搜了券豆兑换
	 * @param httpModel
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public String SLConvert(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO();
		try {
			Dto inDto = httpModel.getInDto();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endTime = "2018-06-06 23:59:59";
			if(new Date().getTime() > sdf.parse(endTime).getTime()){//截止6月6号，停止转豆
				msgVo.setCode(Apiconstant.LiaoDou_Too_Many.getIndex());
				msgVo.setMsg("很抱歉，本次兑换活动已结束。");
				return AOSJson.toJson(msgVo);
			}
			if(AOSUtils.isEmpty(inDto.getString("voucher"))||AOSUtils.isEmpty(httpModel.getAttribute("user_id"))){
				msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
				msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
				return AOSJson.toJson(msgVo);
			}
			inDto.put("user_id", httpModel.getAttribute("user_id"));
			int voucher=inDto.getInteger("voucher");
			if(voucher>10000){
				msgVo.setCode(Apiconstant.LiaoDou_Too_Many.getIndex());
				msgVo.setMsg(Apiconstant.LiaoDou_Too_Many.getName());
				return AOSJson.toJson(msgVo);
			}
			Map<String,Object> operatemap = new HashMap<String,Object>();
			operatemap.put("user_id", httpModel.getAttribute("user_id"));
			operatemap.put("operate_type", "券兑换豆");
			operatemap.put("operate_time", "2018-06-06 23:59:59");
			List<ZjcUserOperateLogPO> oldoperateloglist = sqlDao.list("com.zjc.users.dao.ZjcUserOperateLogDao.listLogs", Dtos.newDto(operatemap));
			//ZjcUserOperateLogDao.list(Dtos.newDto(operatemap));
			//String a="用户3004788用券兑换了100豆";
			int number=0;
			for (int i = 0; i < oldoperateloglist.size(); i++) {
				String size=oldoperateloglist.get(i).getDescs().substring(oldoperateloglist.get(i).getDescs().indexOf("了")+1,oldoperateloglist.get(i).getDescs().indexOf("豆"));
				number=number+Integer.parseInt(size);
			}
			if(number+voucher>10000){
				msgVo.setCode(Apiconstant.Do_Fails.getIndex());
				msgVo.setMsg("您当前兑换和历史兑换超过了10000");
				return AOSJson.toJson(msgVo);
			}
			ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO = (com.zjc.users.dao.po.ZjcUsersAccountInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersAccountInfoDao.selectOne", inDto);
			if(AOSUtils.isNotEmpty(ZjcUsersAccountInfoPO)){
			ZjcUsersInfoPO userInfo = (ZjcUsersInfoPO) sqlDao.selectOne("com.zjc.users.dao.ZjcUsersInfoDao.selectOne", Dtos.newDto("user_id", httpModel.getAttribute("user_id")));
				if(ZjcUsersAccountInfoPO.getMake_over_integral()>=voucher){
					    String httpOrgCreateTest = "http://api2.xn--ykq093c.com/api/sllogin/convert"; 
			    	    HttpClientUtil httpClientUtil = new HttpClientUtil();
			    	    String key=AOSCodec.md5("1");
			    	    String sin="mobile="+userInfo.getMobile()+"&voucher="+voucher+"&key="+key;
			    	    String sinvalue=AOSCodec.md5(sin).toUpperCase();
			    	    Map<String,String> map = new HashMap<String,String>();
			        	map.put("mobile", userInfo.getMobile());
			        	map.put("voucher", "<![CDATA["+voucher+"]]>");
			        	map.put("sign", "<![CDATA["+sinvalue+"]]>");
			        	String xml=com.api.common.util.GenerateXml.callMapToXML(map);
			        	 Map<String,String> maps = new HashMap<String,String>();
			        	 maps.put("xml", xml);
			        	String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,maps,"utf-8");
			    	    JSONObject j = JSONObject.parseObject(httpOrgCreateTestRtn);
			    	    String success=j.get("success").toString();
			    	    String code=j.get("code").toString();
			    	    if("true".equals(success)&&"1".equals(code)){
			    	    	int make_over_integral=ZjcUsersAccountInfoPO.getMake_over_integral()-voucher;
						    ZjcUsersAccountInfoPO.setMake_over_integral(make_over_integral);
						    int row=ZjcUsersAccountInfoDao.updateByKey(ZjcUsersAccountInfoPO);
						    if(row>0){
						    	ZjcUserOperateLogPO ZjcUserOperateLogPO=new ZjcUserOperateLogPO();
						    	ZjcUserOperateLogPO.setUser_id(Integer.parseInt(httpModel.getAttribute("user_id").toString()));
						    	ZjcUserOperateLogPO.setOperate_time(new Date());
						    	ZjcUserOperateLogPO.setOperate_type("券兑换豆");
						    	ZjcUserOperateLogPO.setUser_name(userInfo.getReal_name());
						    	ZjcUserOperateLogPO.setDescs("用户"+httpModel.getAttribute("user_id").toString()+"用券兑换了"+voucher+"豆");
						    	ZjcUserOperateLogDao.insert(ZjcUserOperateLogPO);
						    	ZjcUserLogPO ZjcUserLogPO=new ZjcUserLogPO();
						    	ZjcUserLogPO.setUser_id(new BigInteger(httpModel.getAttribute("user_id").toString()));
						    	ZjcUserLogPO.setShow_type(1);
						    	ZjcUserLogPO.setTime(new Date());
						    	ZjcUserLogPO.setType("兑换");
						    	ZjcUserLogPO.setDescs("亲，恭喜您成功将可转券"+voucher+"兑换成了豆"+voucher+"，请查收！当前可转券："+make_over_integral);
						    	int rows=ZjcUserLogDao.insert(ZjcUserLogPO);
						    	if(rows>0){
									msgVo.setCode(Apiconstant.Do_Success.getIndex());
									msgVo.setMsg(Apiconstant.Do_Success.getName());
								}else {
									msgVo.setCode(Apiconstant.Do_Fails.getIndex());
									msgVo.setMsg(Apiconstant.Do_Fails.getName());
								}
						    }
			    	    } else {
			    	    	msgVo.setCode(Apiconstant.LiaoDou_Change_Is_Fail.getIndex());
							msgVo.setMsg(j.get("msg").toString());
			    	    }
				}else {
					msgVo.setCode(Apiconstant.Do_Fails.getIndex());
					msgVo.setMsg("很抱歉，兑换了豆所需可转券不足，当前可兑换豆数量为：" + ZjcUsersAccountInfoPO.getMake_over_integral());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return AOSJson.toJson(msgVo);
	}
}
