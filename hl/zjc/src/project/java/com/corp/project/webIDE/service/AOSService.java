package com.corp.project.webIDE.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corp.project.webIDE.dao.po.Aos_sys_dicPO;
import com.corp.project.webIDE.dao.po.Aos_sys_paramPO;


/**
 * <b>提供一些基础服务</b>
 * 
 * @author OSWorks-XC
 * @date 2014-05-13
 */
@Service("aosService")
public class AOSService {

	@Autowired
	private ResourceCacheService resourceCacheService;

	
	/**
	 * 根据参数键获取参数值
	 * 
	 * @param key_
	 *            参数键
	 */
	public String getValueByParamKey(String key_) {
		String value_ = "";
		Aos_sys_paramPO aos_sys_paramPO = getParamPOByParamKey(key_);
		if (aos_sys_paramPO != null) {
			value_ = aos_sys_paramPO.getValue_();
		}
		return value_;
	}
	
	/**
	 * 根据参数键获取参数对象
	 * 
	 * @param key_
	 *            参数键
	 */
	public Aos_sys_paramPO getParamPOByParamKey(String key_) {
		return resourceCacheService.getParamPOByParamKey(key_);
	}
	
	/**
	 * 根据数据字典标识键获取字典对照集合
	 * 
	 * @param dickey
	 *            数据字典标识键
	 * @return
	 */
	public List<Aos_sys_dicPO> getDicList(String dickey) {
		return resourceCacheService.getDicList(dickey);
	}
	
	
}
