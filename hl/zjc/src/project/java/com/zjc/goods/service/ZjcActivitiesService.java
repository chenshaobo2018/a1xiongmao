/**
 * 
 */
package com.zjc.goods.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.goods.dao.ZjcActivitiyDao;
import com.api.goods.dao.po.ZjcActivitiyPO;
import com.zjc.goods.dao.ZjcBrandDao;
import com.zjc.goods.dao.po.ZjcBrandPO;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

/**
 * @author Administrator
 *
 */
@Service(value="ZjcActivitiesService")
public class ZjcActivitiesService {

	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcActivitiyDao ZjcActivitiyDao;
	@Autowired
	private IdService idService;
	
	/**
	 * 初始化品牌页面
	 * 
	 * @param httpModel
	 */
	public void init(HttpModel httpModel){
		httpModel.setViewPath("/project/zjc/goodsManaer/activities_time.jsp");
	}
	
	/**
	 * 查询活动时间数据
	 * @param httpModel
	 */
	public void queryActivitiy(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcActivitiyPO> ZjcActivitiyPO = sqlDao.list("com.api.goods.dao.ZjcActivitiyDao.listPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(ZjcActivitiyPO, qDto.getPageTotal()));
	}
	
	/**
	 * 添加品牌信息
	 * @param httpModel
	 */
	public void saveActivitiy(HttpModel httpModel){
			Dto outDto = Dtos.newOutDto();
			Dto inDto = httpModel.getInDto();
			ZjcActivitiyPO ZjcActivitiyPO = new ZjcActivitiyPO();
			ZjcActivitiyPO.copyProperties(inDto);
			ZjcActivitiyPO.setTime_id(idService.nextValue(SystemCons.SEQ.SEQ_USER).toString());
			int row=ZjcActivitiyDao.insert(ZjcActivitiyPO);
			if(row==1){
				outDto.setAppMsg("新增成功");
			}else {
				outDto.setAppMsg("新增失败");
			}
			
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 修改品牌信息
	 * @param httpModel
	 */
	public void updateActivitiy(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcActivitiyPO ZjcActivitiyPO = new ZjcActivitiyPO();
		ZjcActivitiyPO.copyProperties(inDto);
		int row=ZjcActivitiyDao.updateByKey(ZjcActivitiyPO);
		if(row==1){
			outDto.setAppMsg("修改成功");
		}else {
			outDto.setAppMsg("xiugai失败");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
}
