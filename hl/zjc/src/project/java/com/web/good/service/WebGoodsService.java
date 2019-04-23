/**
 * 
 */
package com.web.good.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.taobao.api.ApiException;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.users.dao.po.ZjcUserAddressPO;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Service(value="WebGoodsService")
public class WebGoodsService {
	@Autowired
	private SqlDao sqlDao;
	/**
	 * 通过商品号查询商品
	 * 
	 * @param httpModel
	 * @return
	 * @throws ApiException
	 */
	public MessageVO makeOrder(HttpModel httpModel) throws ApiException{
		MessageVO msgVo = new MessageVO(); 
		Dto inDto = httpModel.getInDto();
		ZjcGoodsPO Goods = (ZjcGoodsPO) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsDao.goodsnum",inDto);
		if(AOSUtils.isNotEmpty(Goods)){
			Goods.setGoods_num(Integer.valueOf(inDto.get("goods_num").toString()));
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setData(Goods);
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}else {
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setData("");
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
		}
		return msgVo;
	}
	
	/**
	 * 通过user_id查询收货地址
	 * 
	 * @param httpModel
	 * @return
	 * @throws ApiException
	 */
	public MessageVO shippingAddress(HttpModel httpModel) throws ApiException{
		MessageVO msgVo = new MessageVO(); 
		Dto inDto = httpModel.getInDto();
		String user_id=(String) httpModel.getRequest().getSession().getAttribute("user_id");
		inDto.put("user_id", user_id);
		ZjcUserAddressPO UserAddress =  sqlDao.list("com.zjc.users.dao.ZjcUserAddressDao.list",inDto);
		if(AOSUtils.isNotEmpty(UserAddress)){
			msgVo.setCode(Apiconstant.Do_Success.getIndex());
			msgVo.setData(UserAddress);
			msgVo.setMsg(Apiconstant.Do_Success.getName());
		}else {
			msgVo.setCode(Apiconstant.Do_Fails.getIndex());
			msgVo.setData("");
			msgVo.setMsg(Apiconstant.Do_Fails.getName());
		}
		return msgVo;
	}
}
