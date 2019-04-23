/**
 * 
 */
package com.api.goods;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;

/**
 * @author Administrator
 *
 */
@Service(value="ZjcGoodsHomePageService")
public class ZjcGoodsHomePageService {
	@Autowired
	private SqlDao sqlDao;
	
	/**
	 * app接口-获取店铺的商品
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String  getHomePage(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getInteger("page")) || qDto.getInteger("page")<1){//当前页数不能为空
			MessageVO.setCode(Apiconstant.Page_Is_Null.getIndex());
			MessageVO.setMsg(Apiconstant.Page_Is_Null.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = qDto.getInteger("page");
			qDto.put("start", (page-1)*ConstantUtil.pageSize);
			List<Dto> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.homePage", qDto);
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
}
