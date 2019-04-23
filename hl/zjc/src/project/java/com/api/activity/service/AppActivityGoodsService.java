/**
 * 
 */
package com.api.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.zjc.goods.dao.po.ZjcGoodsPO;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Service(value="AppActivityGoodsService")
public class AppActivityGoodsService {
		@Autowired
		private SqlDao sqlDao;
	/**
	 * 查询分页商品列表
	 * 
	 * @param httpModel
	 * @return
	 */
	public MessageVO getAppGoods(HttpModel httpModel) {
		MessageVO MessageVO = new MessageVO();
		Dto qDto = httpModel.getInDto();
		// 设置limit每页条数
		qDto.put("limit", ConstantUtil.pageSize);
		// 设置start开始条数
		if(AOSUtils.isEmpty(qDto.getInteger("page")) || qDto.getInteger("page")<1){//当前页数不能为空
			MessageVO.setCode(Apiconstant.Page_Is_Null.getIndex());
			MessageVO.setMsg(Apiconstant.Page_Is_Null.getName());
		} 
		int page = qDto.getInteger("page");
		qDto.put("start", (page - 1) * ConstantUtil.pageSize);
		List<ZjcGoodsPO> goodslist = sqlDao.list(
				"com.zjc.goods.dao.ZjcGoodsDao.goodsWxStorePage", qDto);
		PageVO pageVO = new PageVO();
		if (goodslist.size() == 0) {
			MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
			MessageVO.setMsg(Apiconstant.NO_DATA.getName());
		} else {
			// 生成返回分页参数实体
			pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), 1);
			pageVO.setList(goodslist);
			pageVO.setNowPage(page);
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
			MessageVO.setData(pageVO);
		}
		return MessageVO;
	}
	
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
}
