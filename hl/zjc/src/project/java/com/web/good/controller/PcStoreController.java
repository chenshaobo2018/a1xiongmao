/**
 * 
 */
package com.web.good.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.web.router.HttpModel;

import com.api.goods.dao.po.ZjcGoodsCategoryVO;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.users.dao.ZjcMessageDao;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/notokenapi")
public class PcStoreController {
	
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcMessageDao messageDao;

	@RequestMapping(value = "/pcstore/v1/initPcIndex")
	public String initPcIndex(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		Dto inDto = httpModel.getInDto();
		/*查询商品列表*/
		//购商品
		Dto qDto = Dtos.newDto();
		qDto.put("shop_price", 1);
		qDto.put("start", 1);
		qDto.put("limit", 6);
		List<ZjcGoodsPO> shopGoods = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.homePage", qDto);
		httpModel.setAttribute("shopGoods", shopGoods);
		qDto.clear();
		
		//购实惠
		qDto.put("is_mixed", 1);
		qDto.put("start", 1);
		qDto.put("limit", 6);
		List<ZjcGoodsPO> substantialGoods = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.homePage", qDto);
		httpModel.setAttribute("substantialGoods", substantialGoods);
		qDto.clear();
		//易生活
		qDto.put("market_price", 1);
		qDto.put("start", 1);
		qDto.put("limit", 6);
		List<ZjcGoodsPO> changeSelfGoods = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.homePage", qDto);
		httpModel.setAttribute("changeSelfGoods", changeSelfGoods);
		qDto.clear();
		//平台产品
		qDto.put("cat_id", 1);
		qDto.put("start", 1);
		qDto.put("limit", 6);
		List<ZjcGoodsPO> myGoods = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodslistPage", qDto);
		httpModel.setAttribute("myGoods", myGoods);
		qDto.clear();
		//酒水、食品
		qDto.put("cat_id", 2);
		qDto.put("start", 1);
		qDto.put("limit", 6);
		List<ZjcGoodsPO> moreTypeGoods = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodslistPage", qDto);
		httpModel.setAttribute("moreTypeGoods", moreTypeGoods);
		qDto.clear();
		//查询热销
		qDto.put("is_hot", 1);
		qDto.put("start", 1);
		qDto.put("limit", 4);
		List<ZjcGoodsPO> hotGoodsList = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.homePage", qDto);
		httpModel.setAttribute("hotGoodsList", hotGoodsList);
		qDto.clear();
		//查询特价
		qDto.put("special_offer", 1);
		qDto.put("start", 1);
		qDto.put("limit", 4);
		List<ZjcGoodsPO> specialGoodsList = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.homePage", qDto);
		httpModel.setAttribute("specialGoodsList", specialGoodsList);
		qDto.clear();
		//查询新商品
		qDto.put("strsql", "order by on_time desc");
		qDto.put("start", 1);
		qDto.put("limit", 4);
		List<ZjcGoodsPO> newGoodsList = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.getNewGoodsPage", qDto);
		httpModel.setAttribute("newGoodsList", newGoodsList);
		qDto.clear();
		//查询最热销
		qDto.put("is_hot", 1);
		qDto.put("start", 1);
		qDto.put("limit", 1);
		List<ZjcGoodsPO> highHotGoodsList = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.maxHotPage", qDto);
		httpModel.setAttribute("highHotGoodsList", highHotGoodsList);
		qDto.clear();
		//商品分类
		List<ZjcGoodsCategoryVO> categoryList = sqlDao.list("com.zjc.goods.dao.ZjcGoodsCategoryDao.get_goods_category", qDto);
		httpModel.setAttribute("categoryList", categoryList);
		//公告
		qDto.put("start", 1);
		qDto.put("limit", 3);
		List<Dto> messagePOs = messageDao.listAllPage(qDto);
		httpModel.setAttribute("messagePOs", messagePOs);
		qDto.clear();
		//首页图片
		List<Dto> goodsAdlist = sqlDao.list("com.zjc.ad.dao.ZjcAdDao.list1", qDto);
		httpModel.setAttribute("goodsAdlist", goodsAdlist);
		return "project/pcstore/pcindex.jsp";
	}
	
}
