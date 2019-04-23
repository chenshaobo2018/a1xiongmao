package com.corp.project.webIDE.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;

import com.corp.project.webIDE.asset.DicCons;
import com.corp.project.webIDE.dao.mapper.Aos_sys_dicMapper;
import com.corp.project.webIDE.dao.mapper.Aos_sys_dic_indexMapper;
import com.corp.project.webIDE.dao.mapper.Aos_sys_paramMapper;
import com.corp.project.webIDE.dao.po.Aos_sys_dicPO;
import com.corp.project.webIDE.dao.po.Aos_sys_dic_indexPO;
import com.corp.project.webIDE.dao.po.Aos_sys_paramPO;
import com.google.common.collect.Lists;

/**
 * 系统资源缓存服务
 * 
 * @author XChun
 *
 */
@Service
public class ResourceCacheService {
	
	@Autowired
	private Aos_sys_paramMapper aos_sys_paramMapper;
	
	@Autowired
	private Aos_sys_dic_indexMapper aos_sys_dic_indexMapper;
	
	@Autowired
	private Aos_sys_dicMapper aos_sys_dicMapper;
	
	/**
	 * 根据参数键获取参数对象
	 * 
	 * @param key_
	 *            参数键
	 */
	public Aos_sys_paramPO getParamPOByParamKey(String key_) {
		Aos_sys_paramPO aos_sys_paramPO = null;
		aos_sys_paramPO = getParamPOByParamKeyFromDB(key_);
		return aos_sys_paramPO;
	}
	
	/**
	 * 从数据库中根据参数键获取参数对象
	 * 
	 * @param key_
	 *            参数键
	 */
	private Aos_sys_paramPO getParamPOByParamKeyFromDB(String key_) {
		Aos_sys_paramPO aos_sys_paramPO = aos_sys_paramMapper.selectOne(Dtos.newDto("key_", key_));
		return aos_sys_paramPO;
	}
	
	
	/**
	 * 根据数据字典标识键获取字典对照集合
	 * 
	 * @param dickey
	 *            数据字典标识键
	 * @return
	 */
	public List<Aos_sys_dicPO> getDicList(String dickey) {
		List<Aos_sys_dicPO> dicList = Lists.newArrayList();
		dicList = getDicListFromDB(dickey);
		return dicList;
	}
	
	/**
	 * 根据数据字典标识键从数据库中获取字典对照集合
	 * 
	 * @param dickey
	 *            数据字典标识键
	 * @return
	 */
	private List<Aos_sys_dicPO> getDicListFromDB(String dickey) {
		List<Aos_sys_dicPO> dicList = Lists.newArrayList();
		Aos_sys_dic_indexPO aos_sys_dic_indexPO = aos_sys_dic_indexMapper.selectOne(Dtos.newDto("key_", dickey));
		if (aos_sys_dic_indexPO != null) {
			Dto inDto = Dtos.newDto();
			inDto.put("status_", DicCons.ENABLED_YES);
			inDto.put("dic_index_id_", aos_sys_dic_indexPO.getId_());
			inDto.setOrder("code_, id_");
			dicList = aos_sys_dicMapper.list(inDto);
		}
		return dicList;
	}
}
