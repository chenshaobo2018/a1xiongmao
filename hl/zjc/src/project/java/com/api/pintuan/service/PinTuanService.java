/**
 * 
 */
package com.api.pintuan.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.api.pintuan.dao.po.SpellGroupRelationalPO;
import com.zjc.goods.dao.po.ZjcGoodsPO;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

/**
 * @author Administrator
 *
 */
@Service(value="PinTuanService")
public class PinTuanService {
	@Autowired
	private SqlDao sqlDao;
	
	/**
	 * 获取商品列表
	 * @param request
	 * @param response
	 * @return
	 */
	public String  queryPinTuan(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getInteger("page")) || qDto.getInteger("page")<1){//当前页数不能为空
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = qDto.getInteger("page");
			qDto.put("start", (page-1)*ConstantUtil.pageSize);
			List<ZjcGoodsPO> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.PinTuanListPage", qDto);
			if(goodslist.size()==0){
				MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
				MessageVO.setMsg(Apiconstant.NO_DATA.getName());
			}else{
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
				pageVO.setList(goodslist);
				MessageVO.setData(pageVO);
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}
		}
		return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 获取商品列表
	 * @param request
	 * @param response
	 * @return
	 */
	public String  queryPinTuanInvitation(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getString("goods_id"))){//当前页数不能为空
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		} else {//当前页数不为空
			List<Dto> spellList = sqlDao.list("com.api.pintuan.dao.SpellGroupRelationalDao.listPituan", qDto);
			if(spellList.size()==0){
				MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
				MessageVO.setMsg(Apiconstant.NO_DATA.getName());
			}else{
				for (int i = 0; i < spellList.size(); i++) {
	                  List<SpellGroupRelationalPO> spell = sqlDao.list("com.api.pintuan.dao.SpellGroupRelationalDao.list", Dtos.newDto("pin_order_id", spellList.get(i).getString("pin_order_id")));
	                  spellList.get(i).put("sum", spell.size());
	                  spellList.get(i).put("pituan_date", 3);
				}
				//生成返回分页参数实体
				MessageVO.setData(spellList);
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}
		}
		return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 查询分页商品列表
	 * 
	 * @param httpModel
	 * @return
	 */
	public MessageVO getPinTuanWxGoods(HttpModel httpModel) {
		MessageVO MessageVO = new MessageVO();
		Dto qDto = httpModel.getInDto();
		// 设置limit每页条数
		qDto.put("limit", ConstantUtil.pageSize);
		// 设置start开始条数
		Object b = httpModel.getAttribute("page");
		String a = String.valueOf(b);
		int page = Integer.parseInt((String) a);
		qDto.put("start", (page - 1) * ConstantUtil.pageSize);
		List<ZjcGoodsPO> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.getPinTuanWxGoodsPage", qDto);
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

}
