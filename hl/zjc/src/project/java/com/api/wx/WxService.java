/**
 * 
 */
package com.api.wx;


import java.math.BigInteger;

import org.springframework.stereotype.Service;

import aos.framework.core.typewrap.Dto;
import aos.framework.web.router.HttpModel;

/**
 * @author zc
 *
 */
@Service(value="wxService")
public class WxService {
	
	public String getMyQRCodePage(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		BigInteger shareId = dto.getBigInteger("shareId");
		return "project/zjc/wx/myQRCode.jsp?shareId=" + shareId;
	}
}
