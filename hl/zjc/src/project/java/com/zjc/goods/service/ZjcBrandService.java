/**
 * 
 */
package com.zjc.goods.service;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSCxt;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.zjc.goods.dao.ZjcBrandDao;
import com.zjc.goods.dao.po.ZjcBrandPO;

/**
 * 品牌列表
 * 
 * @author Administrator
 *
 */
@Service(value="zjcBrandService")
public class ZjcBrandService {
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcBrandDao zjcBrandDao;
	@Autowired
	private IdService idService;
	
	/**
	 * 初始化品牌页面
	 * 
	 * @param httpModel
	 */
	public void initBrand(HttpModel httpModel){
		httpModel.setAttribute("juid", httpModel.getUserModel().getJuid());
		httpModel.setViewPath("project/zjc/goodsManaer/brand.jsp");
	}
	
	

	/**
	 * 通用商品图片上传
	 * 
	 * @param httpModel
	 * @return
	 */
	public void logoUploadPage(HttpModel httpModel) {
		String paramkey = httpModel.getInDto().getString("paramkey");
		String windowsId = httpModel.getInDto().getString("windowsId");
		String inputName = httpModel.getInDto().getString("inputName");
		String updateFlag = httpModel.getInDto().getString("updateFlag");
		if(StringUtils.isNotEmpty(paramkey)){
			String [] stringArr = AOSCxt.getParam(paramkey).split(":");
			Double prop = Double.parseDouble(stringArr[0]) / Double.parseDouble(stringArr[1]);
			DecimalFormat df = new DecimalFormat("#.00");   
			httpModel.setAttribute("prop",df.format(prop));
		}
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setAttribute("windowsId", windowsId);
		httpModel.setAttribute("inputName", inputName);
		httpModel.setAttribute("updateFlag", updateFlag);
		httpModel.setViewPath("project/zjc/common/logoPicUpload.jsp");
	}
	
	/**
	 * 条件查询品牌列表
	 * @param httpModel
	 */
	public void listBrand(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> brandDtos = sqlDao.list("com.zjc.goods.dao.ZjcBrandDao.likePage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(brandDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 添加品牌信息
	 * @param httpModel
	 */
	public void saveBrand(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		if (zjcBrandDao.rows(Dtos.newDto("name", inDto.getString("name"))) > 0) {
			outDto.setAppCode(AOSCons.ERROR);
			outDto.setAppMsg(AOSUtils.merge("操作失败，该品牌信息[{0}]已经存在。", inDto.getString("name")));
		}else {
			ZjcBrandPO zjcBrandPO = new ZjcBrandPO();
			zjcBrandPO.copyProperties(inDto);
			zjcBrandPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
			zjcBrandDao.insert(zjcBrandPO);
//			cacheMasterDataService.cacheParamOption(aos_paramsPO.getParams_key(), aos_paramsPO.getValue());
			outDto.setAppMsg("品牌信息新增成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 修改品牌信息
	 * @param httpModel
	 */
	public void updateBrand(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcBrandPO zjcBrandOldPO = zjcBrandDao.selectByKey(inDto.getInteger("id"));
		if (!StringUtils.equals(zjcBrandOldPO.getName(), inDto.getString("name"))) {
			if (zjcBrandDao.rows(Dtos.newDto("name", inDto.getString("name"))) > 0) {
				outDto.setAppCode(AOSCons.ERROR);
				outDto.setAppMsg(AOSUtils.merge("操作失败，品牌信息[{0}]已经存在。", inDto.getString("name")));
			}
		}
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcBrandPO zjcBrandPO = new ZjcBrandPO();
			zjcBrandPO.copyProperties(inDto);
			zjcBrandDao.updateByKey(zjcBrandPO);
			outDto.setAppMsg("品牌信息修改成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 删除品牌信息
	 * @param httpModel
	 */
	public void deleteBrand(HttpModel httpModel){
		String[] selectionIds = httpModel.getInDto().getRows();
		int rows = 0;
		for (String id : selectionIds) {
			//ZjcBrandPO zjcBrandPO = zjcBrandDao.selectByKey(Integer.valueOf(id));
			zjcBrandDao.deleteByKey(Integer.valueOf(id));
//			cacheMasterDataService.delParamOption(aos_paramsPO.getParams_key());
			rows++;
		}
		httpModel.setOutMsg(AOSUtils.merge("操作成功，成功删除[{0}]条品牌信息。", rows));
	}
	
	/**
	 * 下拉
	 * @param httpModel
	 */
	public void listBrandComboBoxData(HttpModel httpModel){
		List<Dto> list = sqlDao.list("com.zjc.goods.dao.ZjcBrandDao.listBrandComboBoxData", null);
		httpModel.setOutMsg(AOSJson.toJson(list));
	}
	
}
