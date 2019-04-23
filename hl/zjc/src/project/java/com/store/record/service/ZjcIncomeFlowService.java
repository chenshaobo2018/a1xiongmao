/**
 * 
 */
package com.store.record.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.store.record.dao.po.ZjcIncomeFlowPO;
import com.zjc.store.dao.po.ZjcSellerInfoPO;

/**
 * @author Administrator
 *
 */
@Service("zjcIncomeFlowService")
public class ZjcIncomeFlowService {
	
	
	@Autowired
	private SqlDao sqlDao;
	
	/**
	 * 根据条件搜索用户的收支流水
	 * @return
	 */
	public PageVO serachRecord(HttpModel httpModel){
		//获取查询参数
		Dto dto=httpModel.getInDto();
		//设置limit每页条数
		dto.put("limit", ConstantUtil.pageSize);
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		dto.put("user_id", sellerInfoPO.getUser_id());
		if(AOSUtils.isEmpty(dto.getInteger("page"))){
			dto.put("page", 1);
		}
		dto.put("start", (dto.getInteger("page")-1)*ConstantUtil.pageSize);
		List<ZjcIncomeFlowPO> records = sqlDao.list("com.store.record.dao.ZjcIncomeFlowDao.getFlowPage", dto);
		PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
		pageVO.setList(records);
		pageVO.setTotalSize(dto.getInteger("total"));
		return pageVO;
	}
}
