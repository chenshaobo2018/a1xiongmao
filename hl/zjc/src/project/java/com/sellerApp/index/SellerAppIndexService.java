package com.sellerApp.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.goods.dao.po.ZjcGoodsCategoryVO;
import com.zjc.order.dao.ZjcOrderActionDao;
import com.zjc.order.dao.ZjcOrderDao;
import com.zjc.order.dao.ZjcOrderGoodsDao;
import com.zjc.region.dao.po.ZjcRegionPO;
import com.zjc.store.dao.ZjcSellerInfoDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.system.dao.ZjcMemberOtherDao;

/**
 * app接口- 通用信息查询
 * 
 * @author wgm
 */
@Service(value="sellerAppIndexService")
public class SellerAppIndexService {
	@Autowired
	private ZjcMemberOtherDao zjcMemberOtherDao;
	
	@Autowired
	private ZjcOrderDao zjcOrderDao;
	
	@Autowired
	private ZjcOrderActionDao zjcOrderActionDao;
	
	@Autowired
	private ZjcOrderGoodsDao zjcOrderGoodsDao;
	
	@Autowired
	private ApiLogService apiLogService;
	
	@Autowired
	private ZjcSellerInfoDao zjcSellerInfoDao;
	
	@Autowired
	private ApiPublicService apiPublicService;
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	
	/**
	 * 用户找回密码
	 * 
	 * @param httpModel
	 * @return
	 */
	public String findPassword(HttpModel httpModel){

		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("mobile")) || AOSUtils.isEmpty(dto.getString("code")) 
				|| AOSUtils.isEmpty(dto.getString("new_password")) || AOSUtils.isEmpty(dto.getString("type"))){
			msgVo.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVo.setMsg(Apiconstant.Srore_Param_Error.getName());
		}else{
			//验证短信验证码
			MessageVO msg = apiPublicService.sms_code_verify(httpModel);
			if(msg.getCode() == 1){
				//检查用户是否存在
				ZjcSellerInfoPO sellerInfoPO = zjcSellerInfoDao.selectOne(dto);
				if(AOSUtils.isEmpty(sellerInfoPO)){
					msgVo.setCode(Apiconstant.Username_Not_Exist.getIndex());
					msgVo.setMsg(Apiconstant.Username_Not_Exist.getName());
				}else{
					if(sellerInfoPO.getIs_lock() == 1){
						msgVo.setCode(Apiconstant.Username_Is_Lock.getIndex());
						msgVo.setMsg(Apiconstant.Username_Is_Lock.getName());
					}else{
						sellerInfoPO.setPassword(dto.getString("new_password"));
						try {
							zjcSellerInfoDao.updateByKey(sellerInfoPO);
								//生成用户日志
								Map<String,Object> map = new HashMap<String,Object>();
								map.put("logType", "找回密码");
								map.put("user_id", sellerInfoPO.getUser_id());
								map.put("mobile", dto.getString("mobile"));
								int logSuccessNum = apiLogService.saveLog(map);
								if(logSuccessNum == 0){//用户日志生成失败
									msgVo.setCode(Apiconstant.Log_Save_Fails.getIndex());
									msgVo.setMsg(Apiconstant.Log_Save_Fails.getName());
								} else {
									msgVo.setCode(Apiconstant.Do_Success.getIndex());
									msgVo.setMsg(Apiconstant.Do_Success.getName());
								}
						} catch (Exception e) {
							msgVo.setCode(Apiconstant.Do_Fails.getIndex());
							msgVo.setMsg(Apiconstant.Do_Fails.getName());
						}
					}
				}
			}else{
				msgVo.setCode(Apiconstant.Code_Was_Wrong.getIndex());
				msgVo.setMsg(Apiconstant.Code_Was_Wrong.getName());
			}
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * 商家app接口-级联查询省市区街道名字列表
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getAddr(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		//查询省信息列表
		List<ZjcRegionPO> provicePOList = sqlDao.list("com.zjc.region.dao.ZjcRegionDao.getAddr", dto);
		if(AOSUtils.isEmpty(provicePOList)){//地址信息为空
			msgVo.setMsg(Apiconstant.NO_DATA.getName());
		} else {//地址信息不为空
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			msgVo.setData(provicePOList);
		}
		return AOSJson.toJson(msgVo);
	}
	
	/**
	 * 查询绑定的店铺分类数据
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getCategoryTreeApp(HttpModel httpModel) {
		MessageVO msgVo = new MessageVO(); 
		Dto dto = httpModel.getInDto();
		//查询省信息列表
		List<Dto> catDtoList=sqlDao.list("com.zjc.goods.dao.ZjcGoodsCategoryDao.getCategoryTreeApp", dto);
		if(AOSUtils.isEmpty(catDtoList)){//地址信息为空
			msgVo.setMsg(Apiconstant.NO_DATA.getName());
			msgVo.setCode(Apiconstant.NO_DATA.getIndex());
		} else {//地址信息不为空
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setMsg(Apiconstant.Do_Success.getName());
			msgVo.setData(catDtoList);
		}
		return AOSJson.toJson(msgVo);
	}
}
