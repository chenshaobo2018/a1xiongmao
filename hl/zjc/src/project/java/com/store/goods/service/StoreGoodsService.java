/**
 * 
 */
package com.store.goods.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.api.common.util.ParameterUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.zjc.comment.dao.po.ZjcCommentPO;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.ZjcSpecGoodsPriceDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.goods.dao.po.ZjcSpecGoodsPricePO;
import com.zjc.goodsImage.dao.ZjcGoodsImagesDao;
import com.zjc.goodsImage.dao.po.ZjcGoodsImagesPO;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.system.dao.po.ZjcMemberOtherPO;

/**
 * @author Administrator
 *
 */
@Service(value="storeGoodsService")
public class StoreGoodsService {
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcGoodsImagesDao zjcGoodsImagesDao;
	@Autowired
	private ZjcMemberOtherDao zjcMemberOtherDao;
	@Autowired
	private ZjcSpecGoodsPriceDao zjcSpecGoodsPriceDao;
	
	/**
	 * 查找待审核商品列表
	 * @param httpModel
	 */
	public PageVO serachPendingGoods(HttpModel httpModel){
		//获取查询参数
		Dto dto=httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		if(AOSUtils.isEmpty(httpModel.getRequest().getParameter("page"))){
			dto.put("page", 1);
		}
		if(AOSUtils.isEmpty(dto.getString("goods_state_1"))){
			dto.put("goods_state_1", "");
		}
		if(AOSUtils.isEmpty(dto.getString("goods_state_2"))){
			dto.put("goods_state_2", "");
		}
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		dto.put("start", (dto.getInteger("page")-1)*ConstantUtil.pageSize);
		List<Dto> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.serachPendingGoodsPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(goodslist);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	/**
	 * 回收站
	 * @param httpModel
	 */
	public PageVO serachRece(HttpModel httpModel){
		//获取查询参数
		Dto dto=httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		if(AOSUtils.isEmpty(httpModel.getRequest().getParameter("page"))){
			dto.put("page", 1);
		}
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		dto.put("start", (dto.getInteger("page")-1)*ConstantUtil.pageSize);
		List<Dto> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.store_re_goods_listPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(goodslist);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	/**
	 * 下架
	 * @param httpModel
	 */
	public PageVO serachUnsale(HttpModel httpModel){
		//获取查询参数
		Dto dto=httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		if(AOSUtils.isEmpty(httpModel.getRequest().getParameter("page"))){
			dto.put("page", 1);
		}
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		dto.put("start", (dto.getInteger("page")-1)*ConstantUtil.pageSize);
		List<Dto> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.store_unsale_goods_listPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(goodslist);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}

	public MessageVO del(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		Dto dto = httpModel.getInDto();
		int i = sqlDao.delete("com.zjc.goods.dao.ZjcGoodsDao.del", dto);
		ZjcGoodsPO g = new ZjcGoodsPO();
		g.setGoods_id(dto.getInteger("goods_id"));
		zjcGoodsImagesDao.deleteByGoodsId(g);
		if(i == 1){
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
		}else{
			msgVO.setCode(Apiconstant.Do_Fails.getIndex());
			msgVO.setMsg(Apiconstant.Do_Fails.getName());
		}
		return msgVO;
	}
	
	public int dels(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		int i = sqlDao.delete("com.zjc.goods.dao.ZjcGoodsDao.dels", dto);
		return i;
	}
	
	public MessageVO del_to_unsale(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		Dto dto = httpModel.getInDto();
		int i = sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.del_to_unsale", dto);
		if(i == 1){
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
		}else{
			msgVO.setCode(Apiconstant.Do_Fails.getIndex());
			msgVO.setMsg(Apiconstant.Do_Fails.getName());
		}
		return msgVO;
	}
	
	public String del_to_unsales(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		Dto dto = httpModel.getInDto();
		sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.del_to_unsales", dto);
		return AOSJson.toJson(msgVO);
	}
	
	public MessageVO revoke(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		Dto dto = httpModel.getInDto();
		int i = sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.revoke", dto);
		if(i == 1){
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
		}else{
			msgVO.setCode(Apiconstant.Do_Fails.getIndex());
			msgVO.setMsg(Apiconstant.Do_Fails.getName());
		}
		return msgVO;
	}
	
	public String del_to_gbs(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		Dto dto = httpModel.getInDto();
		sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.del_to_gbs", dto);
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * 查看选中的商品详情
	 * @param httpModel
	 */
	public ZjcGoodsPO goodsDetial(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		ZjcGoodsPO goodsPO = (ZjcGoodsPO) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsDao.getGoodsDetialById", dto);
		if(goodsPO.getGoods_state_1() == 2){
			String[] aduit_infoArr = goodsPO.getAduit_info().split(";");
			String str = aduit_infoArr[5];
			String info = aduit_infoArr[3];
			goodsPO.setAduit_info(info.substring(info.indexOf("\"")+1, info.indexOf("\"",info.indexOf("\"")+1 )));
			String aduit_user_name = str.substring(str.indexOf("\"")+1, str.indexOf("\"",str.indexOf("\"")+1 ));
			goodsPO.setAduit_user_name(aduit_user_name);
		}else if(goodsPO.getGoods_state_2() == 2){
			String[] aduit_infoArr = goodsPO.getAdmin_aduit_info().split(";");
			String str = aduit_infoArr[5];
			String info = aduit_infoArr[3];
			goodsPO.setAdmin_aduit_info(info.substring(info.indexOf("\"")+1, info.indexOf("\"",info.indexOf("\"")+1 )));
			String aduit_user_name = str.substring(str.indexOf("\"")+1, str.indexOf("\"",str.indexOf("\"")+1 ));
			goodsPO.setAduit_user_name(aduit_user_name);
		}	
		return goodsPO;
	}
	
	/**
	 * 获取商家收到的所有评论
	 * @param httpModel
	 */
	public PageVO serachComments(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		if(AOSUtils.isEmpty(httpModel.getRequest().getParameter("page"))){
			dto.put("page", 1);
		}	
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		dto.put("start", (dto.getInteger("page")-1)*ConstantUtil.pageSize);
		List<Dto> commentlist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.store_comment_listPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(commentlist);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
	
	/**
	 * 获取评论详情
	 * @param httpModel
	 */
	public String store_comment_detial(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("store_id")) || AOSUtils.isEmpty(dto.getString("comment_id"))){
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		}else{
			ZjcCommentPO commentPO = (ZjcCommentPO) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsDao.store_comment_detial", dto);
			if(AOSUtils.isEmpty(commentPO)){
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
			}else{
				msgVO.setData(AOSJson.toJson(commentPO));
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
			}
		}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * 回收站商品
	 * @param httpModel
	 */
	public PageVO gbs(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		if(AOSUtils.isEmpty(httpModel.getAttribute("page"))){
			dto.put("page", 1);
		}else {
			dto.put("page", httpModel.getAttribute("page"));
		}
				
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		
		List<Dto> gbs = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.gbsPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(gbs);
		return pageVO;
	}
	
	/**
	 * 查找商品分类
	 * @param httpModel
	 * @return
	 */
	public List<Dto> getAllCategorys(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		List<Dto> catagorys = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.store_goods_category", dto);
		return catagorys;
	}
	
	/**
	 * 查找用户店铺里有的品牌
	 * @param httpModel
	 * @return
	 */
	public List<Dto> getAllBrands(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		List<Dto> brands = sqlDao.list("com.zjc.goods.dao.ZjcBrandDao.list", dto);
		return brands;
	}
	
	/**
	 * 查找商品列表
	 * @param httpModel
	 */
	public PageVO serachgoods(HttpModel httpModel){
		//获取查询参数
		Dto dto=httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		if(AOSUtils.isEmpty(httpModel.getRequest().getParameter("page"))){
			dto.put("page", 1);
		}
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("store_id", sellerInfoPO.getStore_id());
		dto.put("start", (dto.getInteger("page")-1)*ConstantUtil.pageSize);
		List<ZjcGoodsPO> goodslist;
		PageVO pageVO;
		try {
			goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.serachGoodsPage", dto);
			pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
			pageVO.setList(goodslist);
			pageVO.setTotalSize(dto.getInteger("total"));
		} catch (Exception e) {//发生异常返回空的集合
			goodslist = new ArrayList<>();
			pageVO = ParameterUtil.getPageVO(0, 0);
			pageVO.setList(goodslist);
			pageVO.setTotalSize(0);
		}
		
		return pageVO;
	}
	
	/**
	 * 更新商品信息
	 * @param httpModel
	 * @throws Exception 
	 */
	public MessageVO updateGoods(HttpModel httpModel) throws Exception{
		MessageVO msg = new MessageVO();
		//获取查询参数
		Dto inDto=httpModel.getInDto();
		inDto.put("goods_content", inDto.getString("editorValue"));
		ZjcGoodsPO zjcGoodsPO = new ZjcGoodsPO();//新的商品实体
		zjcGoodsPO.copyProperties(inDto);
		if(inDto.getInteger("is_free_shipping")==1){//商品包邮，邮费为0
			zjcGoodsPO.setPostage(0);
		}
		zjcGoodsPO.setShop_price(zjcGoodsPO.getShop_price().setScale(2));
		ZjcGoodsPO oldGoodsPO = zjcGoodsDao.selectByKey(inDto.getInteger("goods_id"));
		Multimap<String, String> map = compare(zjcGoodsPO, oldGoodsPO);
		map.remove("ineq", "goods_type");
		Set<String> set = new HashSet<>();
		set.addAll(map.get("ineq"));
		Set<String> ineqSet = Sets.intersection(set, inDto.keySet());
		//表示修改商品后是否需要重新审核商品,0商品未作任何修改，1商品修改需要重新审核，2商品修改但不需要重新审核,3只修改商品数量
		int flag = 0;
		int row = 0;
		
		ZjcSpecGoodsPricePO  specGoodsPricePO = new ZjcSpecGoodsPricePO();
		specGoodsPricePO.setGoods_id(zjcGoodsPO.getGoods_id());
		specGoodsPricePO.setKey(inDto.getString("guige_key"));
		specGoodsPricePO.setKey_name(inDto.getString("guige_name"));
		List<ZjcSpecGoodsPricePO> oldSpecGoodsPricePO = sqlDao.list("com.zjc.goods.dao.ZjcSpecGoodsPriceDao.selectByGoodsId", inDto);
		try{
			/*if(!oldSpecGoodsPricePO.get(0).getKey().equals(specGoodsPricePO.getKey())//比较商品规格是否发生变化
					|| !oldSpecGoodsPricePO.get(0).getKey_name().equals(specGoodsPricePO.getKey_name())){
				flag = 2;
			}else */if(ineqSet.isEmpty()){//如果商品主数据均未修改直接结束
				flag = 0;
			}else if(ineqSet.size() == 1 && ineqSet.toArray()[0].equals("store_count")){//只修改了商品数量
				flag = 3;
			}else{
				flag = 1;
			}
		}catch (Exception e){
			flag = 1;
		}
		//根据情况执行修改操作
		if(flag == 0){
			row = 1;
		}else if(flag == 1){//修改商品主数据，不修改商品规格，商品需要重新审核
			zjcGoodsPO.setGoods_state_1(1);
			zjcGoodsPO.setGoods_state_2(1);
			zjcGoodsPO.setIs_on_sale(1);
			zjcGoodsPO.setLast_update(new Date());
			//row = zjcGoodsDao.updateByKey(zjcGoodsPO);
			row = zjcGoodsDao.updateStoreGoods(zjcGoodsPO);
			//对商品图片数据处理
			String goodsImages = inDto.getString("goodsImages");
			List<ZjcGoodsImagesPO> images = zjcGoodsImagesDao.list(Dtos.newDto("goods_id", zjcGoodsPO.getGoods_id()));
			String oldImages = "";
			if(images != null && images.size()>0){
				for(ZjcGoodsImagesPO oldimg :images){
					oldImages += (oldimg.getImage_url() + ";");
				}
			}
			if(goodsImages.equals(oldImages)){
				//商品图片未改变，不用更新
			}else{
				//先删除原有图片，在新增新图片
				for(ZjcGoodsImagesPO oldimg :images){
					zjcGoodsImagesDao.deleteByKey(Integer.valueOf(oldimg.getImg_id()));
				}
				String[] imgs = goodsImages.split(";");
				if(imgs.length > 0 ){
					for(String imgurl : imgs){
						if(imgurl != "" && imgurl != null){
							ZjcGoodsImagesPO zjcGoodsImagesPO = new ZjcGoodsImagesPO();
							zjcGoodsImagesPO.setImage_url(imgurl);
							zjcGoodsImagesPO.setGoods_id(zjcGoodsPO.getGoods_id());
							zjcGoodsImagesDao.insert(zjcGoodsImagesPO);
						}
					}
				}
			}
		}else if(flag == 2){//修改商品主数据，修改商品规格，商品需要重新审核
			zjcGoodsPO.setGoods_state_1(1);
			zjcGoodsPO.setGoods_state_2(1);
			zjcGoodsPO.setIs_on_sale(1);
			zjcGoodsPO.setLast_update(new Date());
			//row = zjcGoodsDao.updateByKey(zjcGoodsPO);
			row = zjcGoodsDao.updateStoreGoods(zjcGoodsPO);
			//对商品图片数据处理
			String goodsImages = inDto.getString("goodsImages");
			List<ZjcGoodsImagesPO> images = zjcGoodsImagesDao.list(Dtos.newDto("goods_id", zjcGoodsPO.getGoods_id()));
			String oldImages = "";
			if(images != null && images.size()>0){
				for(ZjcGoodsImagesPO oldimg :images){
					oldImages += (oldimg.getImage_url() + ";");
				}
			}
			if(goodsImages.equals(oldImages)){
				//商品图片未改变，不用更新
			}else{
				//先删除原有图片，在新增新图片
				for(ZjcGoodsImagesPO oldimg :images){
					zjcGoodsImagesDao.deleteByKey(Integer.valueOf(oldimg.getImg_id()));
				}
				String[] imgs = goodsImages.split(";");
				if(imgs.length > 0 ){
					for(String imgurl : imgs){
						if(imgurl != "" && imgurl != null){
							ZjcGoodsImagesPO zjcGoodsImagesPO = new ZjcGoodsImagesPO();
							zjcGoodsImagesPO.setImage_url(imgurl);
							zjcGoodsImagesPO.setGoods_id(zjcGoodsPO.getGoods_id());
							zjcGoodsImagesDao.insert(zjcGoodsImagesPO);
						}
					}
				}
			}
			sqlDao.update("com.zjc.goods.dao.ZjcSpecGoodsPriceDao.updateByGoodsId", specGoodsPricePO);
		}else{//只修改商品数量
			row = sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.updateGoodsCount", inDto);
		}
		if(row == 1){
			msg.setCode(Apiconstant.Do_Success.getIndex());
			msg.setMsg(Apiconstant.Do_Success.getName());
		}else{
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			msg.setMsg(Apiconstant.Do_Fails.getName());
		}
		return msg;
	}
	
	/**
	 * 新增商品
	 * @param httpModel
	 * @return
	 */
	@Transactional(rollbackFor={Exception.class})
	public MessageVO saveGoods(HttpModel httpModel){
		Dto dto=httpModel.getInDto();
		MessageVO msg = new MessageVO();
		String editorValue = dto.getString("editorValue");
		ZjcGoodsPO zjcGoodsPO = new ZjcGoodsPO();
		zjcGoodsPO.copyProperties(dto);
		zjcGoodsPO.setIs_on_sale(1);
		zjcGoodsPO.setGoods_state_1(1);
		zjcGoodsPO.setGoods_state_2(1);
		zjcGoodsPO.setOn_time(new Date());
		zjcGoodsPO.setLast_update(new Date());
		if(AOSUtils.isEmpty(zjcGoodsPO.getShop_price())){
			zjcGoodsPO.setShop_price(new BigDecimal("0.00"));
		}else{
			zjcGoodsPO.setShop_price(zjcGoodsPO.getShop_price().setScale(2));
		}
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		zjcGoodsPO.setGoods_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
		zjcGoodsPO.setStore_id(sellerInfoPO.getStore_id());
		zjcGoodsPO.setGoods_content(editorValue);
		if(zjcGoodsPO.getGoods_sn().isEmpty()){
			zjcGoodsPO.setGoods_sn("TP00" + zjcGoodsPO.getGoods_id());
		}
		ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
		Integer sum = (Integer) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsDao.countGooodsNum", sellerInfoPO.getStore_id());
		if((sum + 1) > Integer.valueOf(zjcMemberOtherPO.getMerchants_quantity_limit())){
			msg.setMsg("商品数量超出限制，最多"+zjcMemberOtherPO.getMerchants_quantity_limit());
		}else{
			zjcGoodsDao.insert(zjcGoodsPO);
			//商品规格添加
			ZjcSpecGoodsPricePO  specGoodsPricePO = new ZjcSpecGoodsPricePO();
			specGoodsPricePO.setGoods_id(zjcGoodsPO.getGoods_id());
			specGoodsPricePO.setKey(dto.getString("guige_key"));
			specGoodsPricePO.setKey_name(dto.getString("guige_name"));
			sqlDao.insert("com.zjc.goods.dao.ZjcSpecGoodsPriceDao.insertSub", specGoodsPricePO);
			try {		
				//图片添加
				String goodsImages = dto.getString("goodsImages");
				String[] imgs = goodsImages.split(";");
				if(imgs.length > 0 ){
					for(String imgurl : imgs){
						if(imgurl != "" && imgurl != null){
							ZjcGoodsImagesPO zjcGoodsImagesPO = new ZjcGoodsImagesPO();
							zjcGoodsImagesPO.setImage_url(imgurl);
							zjcGoodsImagesPO.setGoods_id(zjcGoodsPO.getGoods_id());
							zjcGoodsImagesDao.insert(zjcGoodsImagesPO);
						}
					}
				}
				msg.setCode(Apiconstant.Do_Success.getIndex());
				msg.setMsg(Apiconstant.Do_Success.getName());
			} catch (Exception e) {
				msg.setCode(Apiconstant.Do_Fails.getIndex());
				msg.setMsg(Apiconstant.Do_Fails.getName());
			}		
		}
		return msg;
	}
	
	public MessageVO grounding(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		MessageVO msg = new MessageVO();
		ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		Integer sum = (Integer) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsDao.countGooodsNum", sellerInfoPO.getStore_id());
		if((sum + 1) > Integer.valueOf(zjcMemberOtherPO.getMerchants_quantity_limit())){
			msg.setMsg("商品数量超出限制，最多"+zjcMemberOtherPO.getMerchants_quantity_limit());
		}else{
			try {
				int i = sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.grounding", dto);
				if(i >= 1){
					msg.setCode(Apiconstant.Do_Success.getIndex());
					msg.setMsg(Apiconstant.Do_Success.getName());
				}else{
					msg.setCode(Apiconstant.Do_Fails.getIndex());
					msg.setMsg(Apiconstant.Do_Fails.getName());
				}
			} catch (Exception e) {
				msg.setCode(Apiconstant.Do_Fails.getIndex());
				msg.setMsg(Apiconstant.Do_Fails.getName());
			}
		}
		return msg;
	}
	
	/**
	 * 判断两个实体是否只有指定属性值不一样
	 * @param obj1
	 * @param obj2
	 * @param fieldName
	 * @return true 满足， false不满足
	 * @throws Exception
	 */
	public Multimap<String, String> compare(Object obj1, Object obj2) throws Exception{		
		Field[] fields1 = obj1.getClass().getDeclaredFields();
		Multimap<String, String> map = ArrayListMultimap.create();
		for (int i = 0; i < fields1.length; i++) {
			if(fields1[i].getType().toString().equals("class java.lang.String")){
				Method m = obj2.getClass().getDeclaredMethod("get"+getMethodName(fields1[i].getName()));
				String val1 = (String) m.invoke(obj1);
				String val2 = (String) m.invoke(obj2);
				if(val1 == null && val2 == null){
					map.put("eq", fields1[i].getName());
				}else if((val1 == null && val2 != null) || (val1 != null && val2 == null)){
					map.put("ineq", fields1[i].getName());
				}else{
					if (!val1.equals(val2)) {
						map.put("ineq", fields1[i].getName());
					} else {
						map.put("eq", fields1[i].getName());	
					}
				}
			}
			if(fields1[i].getType().toString().equals("class java.lang.Integer")){
				Method m = obj2.getClass().getDeclaredMethod("get"+getMethodName(fields1[i].getName()));
				Integer val1 = (Integer) m.invoke(obj1);
				Integer val2 = (Integer) m.invoke(obj2);
				if(val1 == null && val2 == null){
					map.put("eq", fields1[i].getName());
				}else if((val1 == null && val2 != null) || (val1 != null && val2 == null)){
					map.put("ineq", fields1[i].getName());
				}else{
					if (!val1.equals(val2)) {
						map.put("ineq", fields1[i].getName());
					} else {
						map.put("eq", fields1[i].getName());	
					}
				}
			}
			if(fields1[i].getType().toString().equals("class java.math.BigDecimal")){
				Method m = obj2.getClass().getDeclaredMethod("get"+getMethodName(fields1[i].getName()));
				BigDecimal val1 = (BigDecimal) m.invoke(obj1);
				BigDecimal val2 = (BigDecimal) m.invoke(obj2);
				if(val1 == null && val2 == null){
					map.put("eq", fields1[i].getName());
				}else if((val1 == null && val2 != null) || (val1 != null && val2 == null)){
					map.put("ineq", fields1[i].getName());
				}else{
					if (!val1.equals(val2)) {
						map.put("ineq", fields1[i].getName());
					} else {
						map.put("eq", fields1[i].getName());	
					}
				}
			}
			if(fields1[i].getType().toString().equals("class java.util.Date")){
				Method m = obj2.getClass().getDeclaredMethod("get"+getMethodName(fields1[i].getName()));
				Date val1 = (Date) m.invoke(obj1);
				Date val2 = (Date) m.invoke(obj2);
				if(val1 == null && val2 == null){
					map.put("eq", fields1[i].getName());
				}else if((val1 == null && val2 != null) || (val1 != null && val2 == null)){
					map.put("ineq", fields1[i].getName());
				}else{
					if (!val1.equals(val2)) {
						map.put("ineq", fields1[i].getName());
					} else {
						map.put("eq", fields1[i].getName());	
					}
				}
			}
		}
		return map;
	}
	
	private String getMethodName(String fildeName) throws Exception{  
        byte[] items = fildeName.getBytes();  
        items[0] = (byte) ((char) items[0] - 'a' + 'A');  
        return new String(items);  
    } 
}
