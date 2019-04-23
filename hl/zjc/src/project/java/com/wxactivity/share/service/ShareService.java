/**
 * 
 */
package com.wxactivity.share.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.wxactivity.share.dao.WxShareActivityDao;
import com.wxactivity.share.dao.po.WxShareActivityPO;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;

/**
 * @author Administrator
 *
 */
@Service(value="ShareService")
public class ShareService {
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private WxShareActivityDao WxShareActivityDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	/**
	 * 商品分享进度
	 * @param httpModel
	 * @return
	 */
	public String goodsShare(HttpModel httpModel) {
		MessageVO MessageVO=new MessageVO();
		Dto qDto=httpModel.getInDto();
			//设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			Object b = httpModel.getAttribute("page");
			String a=String.valueOf(b);
			int page=Integer.parseInt((String)a);
			qDto.put("start", (page-1)*ConstantUtil.pageSize);
			if(AOSUtils.isEmpty(qDto.getString("open_id"))){
				MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
				return AOSJson.toJson(MessageVO);
			}
			List<WxShareActivityPO> shareGoods = sqlDao.list("com.wxactivity.share.dao.WxShareActivityDao.shareGoodsPage", qDto);
			PageVO pageVO=new PageVO();
			if(shareGoods.size()==0){
				if("undefined".equals(qDto.getString("goods_id"))){
					qDto.put("goods_id", null);
				}
				ZjcGoodsPO zjcGoodsPO = zjcGoodsDao.selectByKey(qDto.getInteger("goods_id"));
				if(AOSUtils.isEmpty(zjcGoodsPO)){
					MessageVO.setCode(Apiconstant.Goods_Is_Null.getIndex());
					MessageVO.setMsg(Apiconstant.Goods_Is_Null.getName());
					return AOSJson.toJson(MessageVO);
				}
				List<WxShareActivityPO> funList = new ArrayList<WxShareActivityPO>();
				WxShareActivityPO activityPO  = new WxShareActivityPO();
				activityPO.setGoods_id(zjcGoodsPO.getGoods_id().toString());
				activityPO.setMarket_price(zjcGoodsPO.getMarket_price().toString());
				activityPO.setShare_integral("0");
				funList.add(activityPO);
				pageVO.setList(funList);
				pageVO.setNowPage(page);
				MessageVO.setData(pageVO);
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}else{
				//生成返回分页参数实体
				pageVO.setList(shareGoods);
				pageVO.setNowPage(page);
				MessageVO.setData(pageVO);
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}
			return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 商品分享进度
	 * @param httpModel
	 * @return
	 */
	public boolean isGoodsShare(String openid,String goods_id) {
		Map<String, Object> inMap = new HashMap<String, Object>();
		if(AOSUtils.isEmpty(openid)){
			return false;
		}
		if(AOSUtils.isEmpty(goods_id)){
			return false;
		}
		inMap.put("open_id", openid );
		inMap.put("goods_id", goods_id );
		List<WxShareActivityPO> shareGoods = sqlDao.list("com.wxactivity.share.dao.WxShareActivityDao.shareGoods", Dtos.newDto(inMap));
		Integer typeSum = is_receive(openid,goods_id);
		if(AOSUtils.isNotEmpty(shareGoods) && shareGoods.size() == 1){
			WxShareActivityPO schedule = shareGoods.get(0);
			BigDecimal a = new BigDecimal(schedule.getMarket_price());//商品原价
			BigDecimal b = new BigDecimal(schedule.getShare_integral());//助力获得
			BigDecimal c =  a.subtract(b);
			int result = c.compareTo(BigDecimal.ZERO);
			if(result == -1 || result == 0){
				if(AOSUtils.isEmpty(typeSum) || typeSum == 0){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			//生成返回分页参数实体
			return false;
		}
	}
	
	/**
	 * 根据分享人open_id 和 商品id 查询是否已经兑换
	 */
	public Integer is_receive(String share_open_id,String goods_id){
		Map<String,Object> qMap = new HashMap<String, Object>();
		qMap.put("share_open_id", share_open_id);
		qMap.put("goods_id", goods_id);
		Dto sumResult = sqlDao.selectDto("com.wxactivity.share.dao.WxShareActivityDao.selectTypeSum", Dtos.newDto(qMap));
		Integer typeSum = sumResult.getInteger("typeSum");
		if(AOSUtils.isNotEmpty(typeSum)){
			return typeSum;
		}else{
			return null;
		}
	}
	
	/**
	 * 查询商品详情
	 * @param httpModel
	 * @return
	 */
	public String shareGoodsDetailed(HttpModel httpModel) {
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		MessageVO msgVO = new MessageVO();
		//根据商品ID查询商品详情信息
		if(AOSUtils.isEmpty(qDto.getString("goods_id"))){
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		}
		ZjcGoodsPO good = (ZjcGoodsPO) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsDao.selectOne", qDto);
		if(AOSUtils.isEmpty(good)){//暂无数据
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			msgVO.setMsg(Apiconstant.NO_DATA.getName());
		} else {
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			msgVO.setData(good);
	     }
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 *  查询商品助力好友
	 * @param httpModel
	 * @return
	 */
	public String shareFriends(HttpModel httpModel) {
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		MessageVO MessageVO = new MessageVO();
			//设置limit每页条数
//			qDto.put("limit", ConstantUtil.pageSize);
//			//设置start开始条数
//			//Object b = qDto.getString("goods_id");
//			int page=qDto.getInteger("page");
//			qDto.put("start", (page-1)*ConstantUtil.pageSize);
			if(AOSUtils.isEmpty(qDto.getString("share_open_id"))){
				MessageVO.setCode(Apiconstant.open_id_Is_Null.getIndex());
				MessageVO.setMsg(Apiconstant.open_id_Is_Null.getName());
				return AOSJson.toJson(MessageVO);
			}
			/*if(AOSUtils.isEmpty(qDto.getString("goods_id"))){
				MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
				return AOSJson.toJson(MessageVO);
			}*/
			List<WxShareActivityPO> shareFriends = sqlDao.list("com.wxactivity.share.dao.WxShareActivityDao.list2", qDto);
			MessageVO.setData(shareFriends);
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
//			PageVO pageVO=new PageVO();
//			if(shareFriends.size()==0){
//				MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
//				MessageVO.setMsg(Apiconstant.NO_DATA.getName());
//			}else{
//				//生成返回分页参数实体
//				pageVO = ParameterUtil.getPageVO(shareFriends.size(), page);
//				pageVO.setList(shareFriends);
//				MessageVO.setData(pageVO);
//				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
//				MessageVO.setMsg(Apiconstant.Do_Success.getName());
//			}
			return AOSJson.toJson(MessageVO);
	  }
	/**
	 * 添加助力信息
	 * @param httpModel
	 * @return
	 */
	  public String AddShare(HttpModel httpModel) {
			//获取查询参数
			Dto qDto=httpModel.getInDto();
			MessageVO MessageVO = new MessageVO();
			if(AOSUtils.isEmpty(qDto.getString("share_open_id"))){
				MessageVO.setCode(Apiconstant.open_id_Is_Null.getIndex());
				MessageVO.setMsg(Apiconstant.open_id_Is_Null.getName());
				return AOSJson.toJson(MessageVO);
			}
			if(AOSUtils.isEmpty(qDto.getString("open_id"))){
				MessageVO.setCode(Apiconstant.open_id_Is_Null.getIndex());
				MessageVO.setMsg(Apiconstant.open_id_Is_Null.getName());
				return AOSJson.toJson(MessageVO);
			}
			if(AOSUtils.isEmpty(qDto.getString("goods_id"))){
				MessageVO.setCode(Apiconstant.goods_id_not_null.getIndex());
				MessageVO.setMsg(Apiconstant.goods_id_not_null.getName());
				return AOSJson.toJson(MessageVO);
			}
			WxShareActivityPO WxShareActivityPO=new WxShareActivityPO();
			WxShareActivityPO.copyProperties(qDto);
			WxShareActivityPO.setAddtime(new Date());
			WxShareActivityPO.setShare_id(idService.nextValue(SystemCons.SEQ.SEQ_USER).toString());
			int row=WxShareActivityDao.insert(WxShareActivityPO);
			if(row==1){
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}else {
				MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Fails.getName());
			}
			return AOSJson.toJson(MessageVO);
	  }
	  
	  /**
	   * 添加分享助力信息
	   * @param httpModel
	   * @return
	   */
	  public String addShareActivity(HttpModel httpModel) {
			//获取查询参数
			Dto qDto=httpModel.getInDto();
			MessageVO MessageVO = new MessageVO();
			if(AOSUtils.isEmpty(qDto.getString("share_open_id"))){
				MessageVO.setCode(Apiconstant.open_id_Is_Null.getIndex());
				MessageVO.setMsg(Apiconstant.open_id_Is_Null.getName());
				return AOSJson.toJson(MessageVO);
			}
			if(AOSUtils.isEmpty(qDto.getString("goods_id"))){
				MessageVO.setCode(Apiconstant.goods_id_not_null.getIndex());
				MessageVO.setMsg(Apiconstant.goods_id_not_null.getName());
				return AOSJson.toJson(MessageVO);
			}
			Map<String,Object> qMap = new HashMap<String,Object>();
			qMap.put("share_open_id", qDto.getString("share_open_id"));
			qMap.put("goods_id", qDto.getString("goods_id"));
			qMap.put("share_integral", "0");
			WxShareActivityPO selectPo = WxShareActivityDao.selectOne(Dtos.newDto(qMap));
			if(AOSUtils.isEmpty(selectPo)){
				WxShareActivityPO WxShareActivityPO=new WxShareActivityPO();
				WxShareActivityPO.copyProperties(qDto);
				WxShareActivityPO.setAddtime(new Date());
				WxShareActivityPO.setShare_integral("0");
				WxShareActivityPO.setShare_id(idService.nextValue(SystemCons.SEQ.SEQ_USER).toString());
				int row=WxShareActivityDao.insert(WxShareActivityPO);
				if(row==1){
					MessageVO.setCode(Apiconstant.Do_Success.getIndex());
					MessageVO.setMsg(Apiconstant.Do_Success.getName());
				}else {
					MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
					MessageVO.setMsg(Apiconstant.Do_Fails.getName());
				}
			}else{
				MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
				MessageVO.setMsg("该商品已经分享过");
			}
			return AOSJson.toJson(MessageVO);
	  }
		  
	  
	  /**
		 * 查询助力好友是否可以助力
		 * @param httpModel
		 * @return
		 */
		public String Is_share(HttpModel httpModel) {
			//获取查询参数
			Dto qDto=httpModel.getInDto();
			MessageVO MessageVO = new MessageVO();
			if(AOSUtils.isEmpty(qDto.getString("share_open_id"))){
				MessageVO.setCode(Apiconstant.open_id_Is_Null.getIndex());
				MessageVO.setMsg(Apiconstant.open_id_Is_Null.getName());
				return AOSJson.toJson(MessageVO);
			}
			if(AOSUtils.isEmpty(qDto.getString("open_id"))){
				MessageVO.setCode(Apiconstant.open_id_Is_Null.getIndex());
				MessageVO.setMsg(Apiconstant.open_id_Is_Null.getName());
				return AOSJson.toJson(MessageVO);
			}
			if(AOSUtils.isEmpty(qDto.getString("goods_id"))){
				MessageVO.setCode(Apiconstant.goods_id_not_null.getIndex());
				MessageVO.setMsg(Apiconstant.goods_id_not_null.getName());
				return AOSJson.toJson(MessageVO);
			}
			List<WxShareActivityPO> shareFriends = sqlDao.list("com.wxactivity.share.dao.WxShareActivityDao.list", qDto);
				if(AOSUtils.isEmpty(shareFriends)){
					MessageVO.setCode(Apiconstant.Do_Success.getIndex());
					MessageVO.setMsg(Apiconstant.Do_Success.getName());
				}else {
					MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
					MessageVO.setMsg(Apiconstant.Do_Fails.getName());
				}
				return AOSJson.toJson(MessageVO);
		  }
	
}
