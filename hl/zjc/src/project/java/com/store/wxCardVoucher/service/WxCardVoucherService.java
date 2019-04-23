/**
 * 
 */
package com.store.wxCardVoucher.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.store.wxCardVoucher.dao.WxCardVoucherDao;
import com.store.wxCardVoucher.dao.po.WxCardVoucherPO;

/**
 * @author Administrator
 *
 */
@Service(value="wxCardVoucherService")
public class WxCardVoucherService {
	@Autowired
	private WxCardVoucherDao wxCardVoucherDao;
	
	/**
	 * 添加卡券
	 * 
	 * @param httpModel
	 */
	public String addCardInfo(Dto dto) {
		WxCardVoucherPO cardVoucher = new WxCardVoucherPO();
		cardVoucher.copyProperties(dto);
		cardVoucher.setAdd_time(new Date());
		int i = wxCardVoucherDao.insert(cardVoucher);
		MessageVO msgvo = new MessageVO();
		if(i==0){
			msgvo.setCode(Apiconstant.Do_Fails.getIndex());
			msgvo.setMsg("新增卡券失败，请确认输入是否正确！");
		} else {
			msgvo.setCode(Apiconstant.Do_Success.getIndex());
			msgvo.setMsg("新增卡券成功");
		}
		return AOSJson.toJson(msgvo);
	}
	
	/**
	 * 生成领取卡券二维码
	 * 
	 * @param httpModel
	 */
	public String createCode(Dto dto) {
		MessageVO msgvo = new MessageVO();
		WxCardVoucherPO cardVoucher = wxCardVoucherDao.selectOne(Dtos.newDto("card_id", dto.getString("card_id")));
		if(AOSUtils.isEmpty(cardVoucher)){
			msgvo.setCode(Apiconstant.Do_Fails.getIndex());
			msgvo.setMsg("卡券不存在，生成二维码失败");
			return AOSJson.toJson(msgvo);
		}
		cardVoucher.setShow_qrcode_url(dto.getString("show_qrcode_url"));
		int i = wxCardVoucherDao.updateByKey(cardVoucher);
		if(i==0){
			msgvo.setCode(Apiconstant.Do_Fails.getIndex());
			msgvo.setMsg("卡券二维码生成失败，请稍后重试！");
		} else {
			msgvo.setCode(Apiconstant.Do_Success.getIndex());
			msgvo.setMsg("卡券二维码生成成功");
		}
		return AOSJson.toJson(msgvo);
	}
	
	/**
	 * 封装新增卡券参数
	 * 
	 * @param dto
	 * @return
	 */
	public Map<String,Object> getCardConditionMap(Dto dto){
		Map<String,Object> condimap = new HashMap<String, Object>();
		Map<String,Object> map = new HashMap<String, Object>();
		
		condimap.put("card_type", dto.getString("card_type"));
		Map<String,Object> baseMap = new HashMap<String, Object>();
		baseMap.put("logo_url", dto.getString("logo_url"));
		baseMap.put("code_type", "CODE_TYPE_TEXT");
		baseMap.put("brand_name", dto.getString("brand_name"));
		baseMap.put("title", dto.getString("card_title"));
		baseMap.put("color", dto.getString("color"));
		baseMap.put("notice", dto.getString("notice"));
		baseMap.put("description", dto.getString("description"));
		Map<String,Object> skuMap = new HashMap<String, Object>();
		skuMap.put("quantity", dto.getInteger("quantity"));
		baseMap.put("sku", skuMap);
		Map<String,Object> dateInfoMap = new HashMap<String, Object>();
		if("1".equals(dto.getString("type"))){
			dateInfoMap.put("type", "DATE_TYPE_FIX_TIME_RANGE");
			dateInfoMap.put("begin_timestamp", getDateValue(dto.getDate("start_time")));
			dateInfoMap.put("end_timestamp", getDateValue(dto.getDate("end_time")));
		} else {
			dateInfoMap.put("type", "DATE_TYPE_FIX_TERM");
			dateInfoMap.put("fixed_term", dto.getInteger("fixed_term"));
			dateInfoMap.put("fixed_begin_term", dto.getInteger("fixed_begin"));
			dateInfoMap.put("end_timestamp", getDateValue(dto.getDate("end_timestamp")));
		}
		baseMap.put("date_info", dateInfoMap);
		map.put("base_info", baseMap);
		map.put("discount", 100-dto.getInteger("discount"));
		condimap.put("discount", map);
		return condimap;
		
	}

	/**
	 * 发送post请求上传图片到微信服务器
	 * 
	 * @param path
	 * @param s
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String connectHttpsByPost(String path,String s,File file) throws IOException{
	    // Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(path);
        // 打开连接
        HttpURLConnection con = (HttpURLConnection) postUrl.openConnection();
		String result =null;  
		con.setDoInput(true);  
		con.setDoOutput(true);  
		con.setUseCaches(false); // post方式不能使用缓存  
		// 设置请求头信息  
		con.setRequestProperty("Connection", "Keep-Alive");  
		con.setRequestProperty("Charset", "UTF-8");  
		// 设置边界  
		String BOUNDARY = "----------" + System.currentTimeMillis();  
		con.setRequestProperty("Content-Type",  
		        "multipart/form-data; boundary="  
		        + BOUNDARY);  
		// 请求正文信息  
		// 第一部分：  
		StringBuilder sb = new StringBuilder();  
		sb.append("--"); // 必须多两道线  
		sb.append(BOUNDARY);  
		sb.append("\r\n");  
		sb.append("Content-Disposition: form-data;name=\"media\";filelength=\""+file.length()+"\";filename=\""  
		  
		        + file.getName() + "\"\r\n");  
		sb.append("Content-Type:application/octet-stream\r\n\r\n");  
		byte[] head = sb.toString().getBytes("utf-8");  
		// 获得输出流  
		OutputStream out = new DataOutputStream(con.getOutputStream());  
		// 输出表头  
		out.write(head);  
		// 文件正文部分  
		// 把文件已流文件的方式 推入到url中  
		DataInputStream in = new DataInputStream(new FileInputStream(file));  
		int bytes = 0;  
		byte[] bufferOut = new byte[1024];  
		while ((bytes = in.read(bufferOut)) != -1) {  
		    out.write(bufferOut, 0, bytes);  
		}  
		in.close();  
		// 结尾部分  
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
		out.write(foot);  
		out.flush();  
		out.close();  
		StringBuffer buffer = new StringBuffer();  
		BufferedReader reader = null;  
		try {  
		    // 定义BufferedReader输入流来读取URL的响应  
		    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
		    String line = null;  
		    while ((line = reader.readLine()) != null) {  
		        buffer.append(line);  
		    }  
		    if (result == null) {  
		        result = buffer.toString();  
		    }  
		} catch (IOException e) {  
		    System.out.println("发送POST请求出现异常！" + e);  
		    e.printStackTrace();  
		} finally {  
		    if (reader != null) {  
		        reader.close();  
		    }  
		}  
		return result;  
	}
	
	public int getDateValue(Date date){
	   //获取当前时间的时间戳
	   long datelong = date.getTime()/1000;
	   //转换long型时间为int
	   int dateInt = new Long(datelong).intValue();
	   return dateInt;
	}
	
}
