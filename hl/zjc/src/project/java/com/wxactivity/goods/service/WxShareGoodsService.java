/**
 * 
 */
package com.wxactivity.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.wxactivity.share.dao.po.WxShareActivityPO;
import com.zjc.goods.dao.po.ZjcGoodsPO;

/**
 * @author Administrator
 *
 */
@Service(value = "WxShareGoodsService")
public class WxShareGoodsService {
	@Autowired
	private SqlDao sqlDao;
	/**
	 * 查询首页商品
	 * 
	 * @param httpModel
	 * @return
	 */
	public MessageVO shareHomePageGoods(HttpModel httpModel) {
		MessageVO MessageVO = new MessageVO();
		Dto qDto = httpModel.getInDto();
		List<ZjcGoodsPO> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.shareHomePageGoods", qDto);
		if(AOSUtils.isNotEmpty(qDto.getString("goods_id"))){
			ZjcGoodsPO ZjcGoodsPO=new ZjcGoodsPO();
			ZjcGoodsPO.setGoods_id(Integer.parseInt(qDto.getString("goods_id")));
			ZjcGoodsPO goods = (ZjcGoodsPO) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsDao.getgoodsuserid", ZjcGoodsPO);
			goodslist.add(goods);
		}
		if (goodslist.size() == 0) {
			MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
			MessageVO.setMsg(Apiconstant.NO_DATA.getName());
		}else {
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
			MessageVO.setData(goodslist);
		} 
		return MessageVO;
	}
	
	/**
	 * 查询用户商品分享数据
	 * @param httpModel
	 * @return
	 */
	public MessageVO shareGoods(HttpModel httpModel) {
		MessageVO MessageVO = new MessageVO();
		Dto qDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getString("open_id"))){
			MessageVO.setCode(Apiconstant.parameter_is_not_null.getIndex());
			MessageVO.setMsg(Apiconstant.parameter_is_not_null.getName());
			return MessageVO;
		}
		List<WxShareActivityPO> sharelist = sqlDao.list("com.wxactivity.share.dao.WxShareActivityDao.sharelist", qDto);
		WxShareActivityPO shareintegral = (WxShareActivityPO) sqlDao.selectOne("com.wxactivity.share.dao.WxShareActivityDao.shareintegral", qDto);
		if(AOSUtils.isNotEmpty(shareintegral) && AOSUtils.isNotEmpty(sharelist)){
			//设置分享商品数量
			shareintegral.setMarket_price(String.valueOf(sharelist.size()));
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
			MessageVO.setData(shareintegral);
		}else {
			MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Fails.getName());
			MessageVO.setData(new WxShareActivityPO());
		}
		return MessageVO;
	}
	
	
	/**
	 * 查询积分详细
	 * 
	 * @param httpModel
	 * @return
	 */
	public String shareIntegralDetailed(HttpModel httpModel) {
		MessageVO MessageVO = new MessageVO();
		Dto qDto = httpModel.getInDto();
		List<ZjcGoodsPO> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.shareHomePageGoods", qDto);
		ZjcGoodsPO ZjcGoodsPO=new ZjcGoodsPO();
		if(AOSUtils.isNotEmpty(qDto.getString("goods_id"))){
			ZjcGoodsPO.setGoods_id(Integer.parseInt(qDto.getString("goods_id")));
			ZjcGoodsPO goods = (ZjcGoodsPO) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsDao.getgoodsuserid", ZjcGoodsPO);
			goodslist.add(goods);
		}
		if (goodslist.size() == 0) {
			MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
			MessageVO.setMsg(Apiconstant.NO_DATA.getName());
		}else {
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
			MessageVO.setData(goodslist);
		} 
		return AOSJson.toJson(MessageVO);
	}
}
