/**
 * 
 */
package com.api.icon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.zjc.img.dao.po.ZjcFunctionIconPO;

/**
 * @author Administrator
 *
 */
@Service(value="ZjcIconService")
public class ZjcIconService {
	@Autowired
	private SqlDao sqlDao;
	/**
	 * 查询图片列表
	 * @param httpModel
	 * @return
	 */
	public String getIcon(HttpModel httpModel) {
		Dto qDto = httpModel.getInDto();
		MessageVO msgVO = new MessageVO();
		List<ZjcFunctionIconPO> paramDtos = sqlDao.list("com.zjc.img.dao.ZjcFunctionIconDao.list", qDto);
		msgVO.setCode(Apiconstant.Do_Success.getIndex());
		msgVO.setMsg(Apiconstant.Do_Success.getName());
		msgVO.setData(paramDtos);
		return AOSJson.toJson(msgVO);
	}
}
