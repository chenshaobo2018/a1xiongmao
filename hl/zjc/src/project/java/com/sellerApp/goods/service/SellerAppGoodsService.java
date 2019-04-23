/**
 * 
 */
package com.sellerApp.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.zjc.system.dao.ZjcMemberOtherDao;
import com.zjc.system.dao.po.ZjcMemberOtherPO;

/**
 * @author Administrator
 *
 */
@Service(value = "sellerAppGoodsService")
public class SellerAppGoodsService{
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcMemberOtherDao zjcMemberOtherDao;
	
	/**
	 * 店铺商品列表
	 * 
	 * @param inDto
	 * @return
	 */
	public String listStoreGoods(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		MessageVO msg = new MessageVO();
		if(AOSUtils.isEmpty(inDto.getString("page")) || AOSUtils.isEmpty(inDto.getString("store_id")) 
				|| AOSUtils.isEmpty(inDto.getString("type"))){
			msg.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msg.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msg); 
		}
		if(inDto.getInteger("page") < 1){
			msg.setCode(Apiconstant.Page_Is_Null.getIndex());
			msg.setMsg(Apiconstant.Page_Is_Null.getName());
			return AOSJson.toJson(msg); 
		}
		inDto.put("limit", ConstantUtil.pageSize);
		int page = inDto.getInteger("page");
		inDto.put("start", (page - 1)*ConstantUtil.pageSize);
		List<Dto> list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.serachStoreGoodsPage", inDto);
		if(list.size()==0){
			msg.setCode(Apiconstant.NO_DATA.getIndex());
			msg.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			//生成返回分页参数实体
			PageVO pageVO = ParameterUtil.getPageVO(inDto.getInteger("total"), inDto.getInteger("page"));
			pageVO.setList(list);
			msg.setData(pageVO);
			msg.setCode(Apiconstant.Do_Success.getIndex());
			msg.setMsg(Apiconstant.Do_Success.getName());
		}
		return AOSJson.toJson(msg);  
	}
	
	/**
	 * 商品下架
	 * 
	 * @param inDto
	 * @return
	 */
	public String del_to_unsale(Dto inDto) {
		MessageVO msg = new MessageVO();
		if(AOSUtils.isEmpty(inDto.getString("goods_id"))){
			msg.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msg.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msg); 
		}
		try {
			sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.del_to_unsale", inDto);
			msg.setCode(Apiconstant.Do_Success.getIndex());
			msg.setMsg(Apiconstant.Do_Success.getName());
		} catch(Exception e) {
			e.printStackTrace();
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			msg.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msg);  
	}

	/**
	 * 增加商品库存数量
	 * 
	 * @param inDto
	 * @return
	 */
	public String add_store_count(Dto inDto) {
		MessageVO msg = new MessageVO();
		if(AOSUtils.isEmpty(inDto.getString("goods_id")) || AOSUtils.isEmpty(inDto.getString("store_count"))
				|| AOSUtils.isEmpty(inDto.getString("store_id"))){//参数传递不全
			msg.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msg.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msg); 
		}
		Dto dto = Dtos.newDto();
		dto.put("store_id", inDto.getString("store_id"));
		dto.put("goods_id", inDto.getString("goods_id"));
		Dto goodDto = sqlDao.selectDto("com.zjc.goods.dao.ZjcGoodsDao.selectOne", dto);
		if(AOSUtils.isEmpty(goodDto)){//该店铺不存在该商品
			msg.setCode(Apiconstant.Store_no_goods.getIndex());
			msg.setMsg(Apiconstant.Store_no_goods.getName());
			return AOSJson.toJson(msg); 
		}
		try{
			inDto.put("store_count", goodDto.getInteger("store_count")+inDto.getInteger("store_count"));
			sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.add_store_count", inDto);
			msg.setCode(Apiconstant.Do_Success.getIndex());
			msg.setMsg(Apiconstant.Do_Success.getName());
		} catch (Exception e){
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			msg.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msg);  
	}
	
	/**
	 * 商品详情
	 * 
	 * @param inDto
	 * @return
	 */
	public String goodsDetial(Dto inDto) {
		MessageVO msg = new MessageVO();
		Dto outDto = sqlDao.selectDto("com.zjc.goods.dao.ZjcGoodsDao.sellerAppGoodsDetial", inDto);
		if(AOSUtils.isEmpty(outDto)){
			msg.setCode(Apiconstant.NO_DATA.getIndex());
			msg.setMsg(Apiconstant.NO_DATA.getName());
		}else{
			outDto.put("goods_content", ParameterUtil.createHtmlBody(outDto.getString("goods_name"),outDto.getString("goods_content")));
			msg.setCode(Apiconstant.Do_Success.getIndex());
			msg.setMsg(Apiconstant.Do_Success.getName());
			msg.setData(outDto);
		}
		return AOSJson.toJson(msg);  
	}

	/**
	 * 统计商品数量
	 * 
	 * @param httpModel
	 * @return
	 */
	public String countGoodsNum(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto qDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getString("store_id"))){//参数错误
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		}
		Dto goodsNumDto = sqlDao.selectDto("com.zjc.goods.dao.ZjcGoodsDao.countGoodsNum", qDto);
		msgVO.setCode(Apiconstant.Do_Success.getIndex());
		msgVO.setMsg(Apiconstant.Do_Success.getName());
		msgVO.setData(goodsNumDto);
		return AOSJson.toJson(msgVO);
	}

	/**
	 * 商品评论列表
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getGoodsComments(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto qDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getInteger("goods_id"))){//参数错误
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		}
		qDto.put("limit", ConstantUtil.pageSize);
		int page = qDto.getInteger("page");
		qDto.put("start", (page - 1)*ConstantUtil.pageSize);
		List<Dto> commentList = sqlDao.list("com.zjc.comment.dao.ZjcCommentDao.listCommentPage", qDto);
		if(AOSUtils.isEmpty(commentList)){//暂无数据
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			msgVO.setMsg(Apiconstant.NO_DATA.getName());
			return AOSJson.toJson(msgVO);
		}
		//生成返回分页参数实体
		PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
		pageVO.setList(commentList);
		msgVO.setData(pageVO);
		msgVO.setCode(Apiconstant.Do_Success.getIndex());
		msgVO.setMsg(Apiconstant.Do_Success.getName());
		msgVO.setData(pageVO);
		return AOSJson.toJson(msgVO);
	}

	/**
	 * 上架商品
	 * 
	 * @param httpModel
	 * @return
	 */
	public String groundGoods(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		MessageVO msg = new MessageVO();
		if(AOSUtils.isEmpty(dto.getString("goods_id")) || AOSUtils.isEmpty(httpModel.getAttribute("store_id"))){
			msg.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msg.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msg);
		}
		ZjcMemberOtherPO zjcMemberOtherPO = zjcMemberOtherDao.selectByMaxKey();
		Integer sum = (Integer) sqlDao.selectOne("com.zjc.goods.dao.ZjcGoodsDao.countGooodsNum", httpModel.getAttribute("store_id").toString());
		if((sum + 1) > Integer.valueOf(zjcMemberOtherPO.getMerchants_quantity_limit())){
			msg.setMsg("商品数量超出限制，最多"+zjcMemberOtherPO.getMerchants_quantity_limit());
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			return AOSJson.toJson(msg);
		}
		try {
			sqlDao.update("com.zjc.goods.dao.ZjcGoodsDao.grounding", dto);
			msg.setCode(Apiconstant.Do_Success.getIndex());
			msg.setMsg(Apiconstant.Do_Success.getName());
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(Apiconstant.Do_Fails.getIndex());
			msg.setMsg(Apiconstant.Do_Fails.getName());
		}
		return AOSJson.toJson(msg);
	}

}
