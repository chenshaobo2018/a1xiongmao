/**
 * 
 */
package com.zjc.goods.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.common.util.ParameterUtil;
import com.api.goods.dao.XpttsgoodsidDao;
import com.api.goods.dao.ZjcPromptGoodsDao;
import com.api.goods.dao.po.XpttsgoodsidPO;
import com.api.goods.dao.po.ZjcPromptGoodsPO;
import com.wxstore.controller.WxStoreController;
import com.zjc.common.po.ExportExcelInfo;
import com.zjc.common.util.ExportExcle;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.goodsImage.dao.ZjcGoodsImagesDao;

/**
 * 商品管理
 * 
 * @author Administrator
 *
 */
@Service(value="zjcGoodsService")
public class ZjcGoodsService {
	
	public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ZjcGoodsImagesDao zjcGoodsImagesDao;
	
	@Autowired
	private XpttsgoodsidDao xpttsgoodsidDao;
	
	@Autowired
	private ZjcPromptGoodsDao zjcPromptGoodsDao;
	/**
	 * 初始化自营商品页面
	 * @param httpModel
	 */
	public void initZjcGoods(HttpModel httpModel){
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/goodsManaer/zjcMyGoods.jsp");
	}
	
	/**
	 * 自营商品管理
	 * 
	 * @param httpModel
	 */
	public void listZjcMyGoods(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> myGoodsDtos = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.listMyGoodsPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(myGoodsDtos, qDto.getPageTotal()));
	}
	
	public void initGoodsQuery(HttpModel httpModel){
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/goodsManaer/zjcGoodsQuery.jsp");
	}
	
	/**
	 * 初始化非自营商品管理
	 * @param httpModel
	 */
	public void initGoods(HttpModel httpModel){
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/goodsManaer/zjcGoods.jsp");
	}
	

	/**
	 * 非自营商品管理列表查询
	 * @param httpModel
	 */
	public void listGoods(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> goodsDtos = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.listGoodsPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(goodsDtos, qDto.getPageTotal()));
	}
	
	
	
	/**
	 * 初始化审批员审批商品
	 * @param httpModel
	 */
	public void initApprovalOfficerGoods(HttpModel httpModel){
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/goodsManaer/zjcApprovalOfficerGoods.jsp");
	}
	
	/**
	 * 审批员商品审核列表查询
	 * @param httpModel
	 */
	public void listApprovalOfficerGoods(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> approvalOfficerGoodsDtos = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.listApprovalOfficerPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(approvalOfficerGoodsDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 审批员商品审核
	 * @param httpModel
	 */
	public void updateApprovalOfficerGoods(HttpModel httpModel){	
		try {
			Dto inDto = httpModel.getInDto();
			ZjcGoodsPO zjcGoodsPO = new ZjcGoodsPO();
			zjcGoodsPO.copyProperties(inDto);	
			String goods_state_1_mark = inDto.getString("goods_state_1_mark").replaceAll("\n", "");
			zjcGoodsPO.setAduit_info("a:4:{s:4:\"time\";s:19:\""+FORMAT.format(new Date())+"\";s:4:\"mark\";s:"+goods_state_1_mark.length()+":\""+goods_state_1_mark+
					"\";s:10:\"admin_name\";s:6:\""+httpModel.getUserModel().getName()+"\";s:8:\"admin_id\";i:"+httpModel.getUserModel().getId()+";}");
			zjcGoodsPO.setApproval_id(httpModel.getUserModel().getId());
			//zjcGoodsPO.setLast_update(new Date());
			zjcGoodsDao.updateByKey(zjcGoodsPO);
			httpModel.setOutMsg("审核完成。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/**
	 * 初始化商品审批
	 * @param httpModel
	 */
	public void initApprovalGoods(HttpModel httpModel){
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/goodsManaer/zjcApprovalGoods.jsp");
	}
	
	/**
	 * 商品审核列表查询
	 * @param httpModel
	 */
	public void listApprovalGoods(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<Dto> approvalGoodsDtos = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.listApprovalPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(approvalGoodsDtos, qDto.getPageTotal()));
	}
	
	/**
	 * 商品审核
	 * @param httpModel
	 * @throws ParseException 
	 */
	public void updateApprovalGoods(HttpModel httpModel) throws ParseException{
		Dto inDto = httpModel.getInDto();
		Dto outDto = Dtos.newDto();
		ZjcGoodsPO oldGoods = zjcGoodsDao.selectByKey(inDto.getInteger("goods_id"));
		if(AOSUtils.isNotEmpty(oldGoods) && oldGoods.getGoods_state_1() != 3){
			outDto.setAppCode("-1");
			outDto.setAppMsg("该商品初审失败，请重新初审");
			httpModel.setOutMsg(AOSJson.toJson(outDto));
			return;
		}
		if(AOSUtils.isNotEmpty(inDto.getInteger("xpt")) ){
			if(inDto.getInteger("xpt")==1){
				XpttsgoodsidPO XpttsgoodsidPO=new XpttsgoodsidPO();
				XpttsgoodsidPO.setGoods_id(inDto.getString("goods_id"));
				XpttsgoodsidPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				xpttsgoodsidDao.insert(XpttsgoodsidPO);
			}
		}
		if(inDto.getInteger("prompt_goods")==1 && inDto.getInteger("goods_state_2")==3){//判断是否是限时抢购
			ZjcPromptGoodsPO promptgoods = zjcPromptGoodsDao.selectOne(Dtos.newDto("goods_id", inDto.getInteger("goods_id")));
			String activity_date = inDto.getString("activity_date");
			String activity_date_part = inDto.getString("activity_date_part");
			String activity_date_part_start = activity_date_part.split("-")[0];
			String activity_date_part_end = activity_date_part.split("-")[1];
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StringBuffer sb = new StringBuffer(); 
			Date start_time = sdf.parse(sb.append(activity_date).append(" ").append(activity_date_part_start).append(":00").toString());
			sb = new StringBuffer();
			Date end_time = sdf.parse(sb.append(activity_date).append(" ").append(activity_date_part_end).append(":00").toString());
			if(activity_date_part_end.contains("00:00")){//如果结束时间段包含0点整，活动结束时间加一天
				 Calendar c = Calendar.getInstance();  
			     c.setTime(end_time);
			     c.add(Calendar.DAY_OF_YEAR, 1);// 日期加1天  
			     end_time = sdf.parse(sdf.format(c.getTime()));
			}
			if(AOSUtils.isEmpty(promptgoods)){//该商品不是限时抢购的，新增限时抢购属性
				ZjcPromptGoodsPO promptgood = new ZjcPromptGoodsPO();
				promptgood.copyProperties(inDto);
				promptgood.setStart_time(start_time);
				promptgood.setEnd_time(end_time);
				zjcPromptGoodsDao.insert(promptgood);
			} else {//该商品本来就是限时抢购的，修改限时抢购属性
				promptgoods.copyProperties(inDto);
				promptgoods.setStart_time(start_time);
				promptgoods.setEnd_time(end_time);
				zjcPromptGoodsDao.updateByKey(promptgoods);
			}
		}
		ZjcGoodsPO zjcGoodsPO = new ZjcGoodsPO();
		zjcGoodsPO.copyProperties(inDto);
		zjcGoodsPO.setIs_on_sale(1);//设置商品为上架状态
		String goods_state_2_mark = inDto.getString("goods_state_2_mark").replaceAll("\n", "");
		zjcGoodsPO.setAdmin_aduit_info("a:4:{s:4:\"time\";s:19:\""+FORMAT.format(new Date())+"\";s:4:\"mark\";s:"+goods_state_2_mark.length()+":\""+goods_state_2_mark+
				"\";s:10:\"admin_name\";s:6:\""+httpModel.getUserModel().getName()+"\";s:8:\"admin_id\";i:"+httpModel.getUserModel().getId()+";}");
		//zjcGoodsPO.setLast_update(new Date());
		zjcGoodsDao.updateByKey(zjcGoodsPO);
		httpModel.setOutMsg("审核完成。");
	}
	
	/**
	 * 查询商品信息
	 * 
	 * @param httpModel
	 */
	public void getGoodsInfo(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		ZjcGoodsPO zjcGoodsPO = zjcGoodsDao.selectByKey(inDto.getInteger("goods_id"));
		if(zjcGoodsPO.getPrompt_goods() == 1){//是限时购
			ZjcPromptGoodsPO promptgoods = zjcPromptGoodsDao.selectOne(Dtos.newDto("goods_id", zjcGoodsPO.getGoods_id()));
			if(AOSUtils.isNotEmpty(promptgoods)){//限时购商品属性不为空
				zjcGoodsPO.setLimit_cost_price(promptgoods.getLimit_cost_price());
				zjcGoodsPO.setLimit_market_price(promptgoods.getLimit_market_price());
				//格式化获取活动日期
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
				String activityDate = sdf.format(promptgoods.getStart_time());
				zjcGoodsPO.setActivity_date(activityDate);
				//格式化获取活动时间段
				sdf = new SimpleDateFormat("HH:mm");
				StringBuilder activityPart = new StringBuilder(); 
				activityPart.append(sdf.format(promptgoods.getStart_time())).append("-").append(sdf.format(promptgoods.getEnd_time()));
				zjcGoodsPO.setActivity_date_part(activityPart.toString());
			}
		}
		httpModel.setOutMsg(AOSJson.toJson(zjcGoodsPO));
	}
	
	/**
	 * 终审获取商品信息
	 * 
	 * @param httpModel
	 */
	public void getZSGoodsInfo(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		ZjcGoodsPO zjcGoodsPO = zjcGoodsDao.selectByKey(inDto.getInteger("goods_id"));
		if(zjcGoodsPO.getPrompt_goods() == 1){//是限时购
			ZjcPromptGoodsPO promptgoods = zjcPromptGoodsDao.selectOne(Dtos.newDto("goods_id", zjcGoodsPO.getGoods_id()));
			if(AOSUtils.isNotEmpty(promptgoods)){//限时购商品属性不为空
				zjcGoodsPO.setLimit_cost_price(promptgoods.getLimit_cost_price());
				zjcGoodsPO.setLimit_market_price(promptgoods.getLimit_market_price());
				//格式化获取活动日期
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
				String activityDate = sdf.format(promptgoods.getStart_time());
				zjcGoodsPO.setActivity_date(activityDate);
				//格式化获取活动时间段
				sdf = new SimpleDateFormat("HH:mm");
				StringBuilder activityPart = new StringBuilder(); 
				activityPart.append(sdf.format(promptgoods.getStart_time())).append("-").append(sdf.format(promptgoods.getEnd_time()));
				zjcGoodsPO.setActivity_date_part(activityPart.toString());
			}
		}
		httpModel.setOutMsg(AOSJson.toJson(zjcGoodsPO));
	}
	
	/**
	 * 新增商品
	 * @param httpModel
	 */
	public void saveGoods(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		if (zjcGoodsDao.rows(Dtos.newDto("goods_name", inDto.getString("goods_name"))) > 0) {
			outDto.setAppCode(AOSCons.ERROR);
			outDto.setAppMsg(AOSUtils.merge("操作失败，该商品[{0}]已经存在。", inDto.getString("goods_name")));
		}else {
			ZjcGoodsPO zGoodsPO = new ZjcGoodsPO();
			zGoodsPO.copyProperties(inDto);
			zGoodsPO.setGoods_id(idService.nextValue(SystemCons.SEQ.SEQ_GOODS_ID).intValue());
			zGoodsPO.setLast_update(new Date());
			if(zGoodsPO.getGoods_sn().isEmpty()){
				zGoodsPO.setGoods_sn("TP00" + zGoodsPO.getGoods_id());
			}
			zjcGoodsDao.insert(zGoodsPO);
			outDto.setAppMsg("商品新增成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 
	 * 商品修改
	 * @param httpModel
	 * @throws ParseException 
	 */
	public void updateGoods(HttpModel httpModel) throws ParseException{
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcGoodsPO zjcOldGoodsPO = zjcGoodsDao.selectByKey(inDto.getInteger("goods_id"));
		if (!StringUtils.equals(zjcOldGoodsPO.getGoods_name(), inDto.getString("goods_name"))) {
			if (zjcGoodsDao.rows(Dtos.newDto("goods_name", inDto.getString("goods_name"))) > 0) {
				outDto.setAppCode(AOSCons.ERROR);
				outDto.setAppMsg(AOSUtils.merge("操作失败，商品[{0}]已经存在。", inDto.getString("goods_name")));
			}
		}
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcGoodsPO zjcGoodsPO = new ZjcGoodsPO();
			zjcGoodsPO.copyProperties(inDto);
			//zjcGoodsPO.setLast_update(new Date());
			zjcGoodsDao.updateByKey(zjcGoodsPO);
			if(AOSUtils.isNotEmpty(inDto.getInteger("prompt_goods")) && inDto.getInteger("prompt_goods")==1){//判断是否是限时抢购
				ZjcPromptGoodsPO promptgoods = zjcPromptGoodsDao.selectOne(Dtos.newDto("goods_id", inDto.getInteger("goods_id")));
				String activity_date = inDto.getString("activity_date");
				String activity_date_part = inDto.getString("activity_date_part");
				String activity_date_part_start = activity_date_part.split("-")[0];
				String activity_date_part_end = activity_date_part.split("-")[1];
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				StringBuffer sb = new StringBuffer(); 
				Date start_time = sdf.parse(sb.append(activity_date).append(" ").append(activity_date_part_start).append(":00").toString());
				sb = new StringBuffer();
				Date end_time = sdf.parse(sb.append(activity_date).append(" ").append(activity_date_part_end).append(":00").toString());
				if(activity_date_part_end.contains("00:00")){//如果结束时间段包含0点整，活动结束时间加一天
					 Calendar c = Calendar.getInstance();  
				     c.setTime(end_time);
				     c.add(Calendar.DAY_OF_YEAR, 1);// 日期加1天  
				     end_time = sdf.parse(sdf.format(c.getTime()));
				}
				if(AOSUtils.isEmpty(promptgoods)){//该商品不是限时抢购的，新增限时抢购属性
					ZjcPromptGoodsPO promptgood = new ZjcPromptGoodsPO();
					promptgood.copyProperties(inDto);
					promptgood.setStart_time(start_time);
					promptgood.setEnd_time(end_time);
					zjcPromptGoodsDao.insert(promptgood);
				} else {//该商品本来就是限时抢购的，修改限时抢购属性
					promptgoods.copyProperties(inDto);
					promptgoods.setStart_time(start_time);
					promptgoods.setEnd_time(end_time);
					zjcPromptGoodsDao.updateByKey(promptgoods);
				}
			}
			outDto.setAppMsg("商品修改成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	
	/**
	 * 商品删除
	 * 
	 * @param httpModel
	 */
	public void deleteGoods(HttpModel httpModel){
		String[] selectionIds = httpModel.getInDto().getRows();
		int rows = 0;
		for (String goods_id : selectionIds) {
			ZjcGoodsPO zjcGoodsPO = zjcGoodsDao.selectByKey(Integer.valueOf(goods_id));
			zjcGoodsDao.deleteByKey(Integer.valueOf(goods_id));
			zjcGoodsImagesDao.deleteByGoodsId(zjcGoodsPO);
//			cacheMasterDataService.delParamOption(aos_paramsPO.getParams_key());
			rows++;
		}
		httpModel.setOutMsg(AOSUtils.merge("操作成功，成功删除[{0}]条商品。", rows));
	}
	
	/**
	 * 排序上下移动
	 * @param httpModel
	 */
	public void upOrDownGoodsSort(HttpModel httpModel) {
		String goodsId = httpModel.getInDto().getString("goods_id");
		String upOrDown = httpModel.getInDto().getString("upOrDown");
		ZjcGoodsPO goodsPO = zjcGoodsDao.selectByKey(Integer.valueOf(goodsId));
		if(!upOrDown.isEmpty()){
			if(Integer.valueOf(upOrDown) == 1){
				//上移操作
				if(goodsPO.getSort() > 1){
					goodsPO.setSort(goodsPO.getSort() - 1);
					zjcGoodsDao.updateByKey(goodsPO);
					httpModel.setOutMsg(AOSUtils.merge("移动排序成功", goodsPO));
				}else{
					httpModel.setOutMsg(AOSUtils.merge("已为最小值，不能上移", goodsPO));
				}
			}else{
				//下移操作
				goodsPO.setSort(goodsPO.getSort() + 1);
				zjcGoodsDao.updateByKey(goodsPO);
				httpModel.setOutMsg(AOSUtils.merge("移动排序成功", goodsPO));
			}
		}
	}
	
	/**
	 * 缓存商品详情内容
	 * 
	 * @param httpModel
	 */
	public void cacheGoodsDetail(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Jedis jedis = JedisUtil.getJedisClient();
		try{
			if(!AOSUtils.isEmpty(jedis.hvals("goodsDetail"))){//删除原有redis数据
				jedis.del("goodsDetail");
			}
			HashMap<String,String> map = new HashMap<String,String>();  
			List<ZjcGoodsPO> goodslist = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.getGoodsContent", null);
			for(ZjcGoodsPO goods:goodslist){//
				map.put(goods.getGoods_id().toString(), ParameterUtil.createHtmlBody(goods.getGoods_name(), goods.getGoods_content()));
			}
			jedis.hmset("goodsDetail", map);//追加数据到redis
			//System.out.println("good202:" + jedis.hmget("goodsDetail","161").toString());
			outDto.setAppMsg("商品详情缓存成功");
		} catch(Exception e){
			outDto.setAppMsg("商品详情缓存失败");
			e.printStackTrace();
		} finally {
			JedisUtil.close(jedis);
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 导出商品
	 * 
	 * @param httpModel
	 */
	public void exportgoodsInfo(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		try {
			if(AOSUtils.isNotEmpty(inDto.getString("goods_name"))){
					inDto.put("goods_name", URLDecoder.decode(inDto.getString("goods_name"), "UTF-8"));
			}
			if(AOSUtils.isNotEmpty(inDto.getString("store_name"))){
				inDto.put("store_name", URLDecoder.decode(inDto.getString("store_name"), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpServletRequest req = httpModel.getRequest();
		try {
			req.setCharacterEncoding("utf-8");
			HttpServletResponse resp = httpModel.getResponse();
	        String title = "商品信息";
	        String[] strHeader =
	            {"商家ID","店铺名称", "商品名称", "分类", "在线支付","易支付",  "库存", "销量", "上架", "优选", "新品", "特殊商品", "更新时间"};
	        String[] strField =
	            {"user_id", "store_name","goods_name","category_name","shop_price", "market_price", "store_count", "sales_sum", "is_on_sale", "is_recommend", "is_new",
	        		"is_car", "last_update"};
	        //根据条件查询待导出订单数据
	        //List list = zjcOrderDao.findExportData(inDto);
	        List<Dto> list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.listExportGoods", inDto);
	        ExportExcelInfo paramExcelInfo = new ExportExcelInfo();
	        paramExcelInfo.setObjList(list);
	        paramExcelInfo.setTitle(title);
	        paramExcelInfo.setStrHeader(strHeader);
	        paramExcelInfo.setStrField(strField);
	        paramExcelInfo.setReq(req);
	        paramExcelInfo.setResp(resp);
	        //导出excel
	        boolean b =ExportExcle.exportExcel(paramExcelInfo);
	        if(b){
	        	httpModel.setOutMsg("商品信息导出成功");
	        } else {
	        	httpModel.setOutMsg("商品信息导出失败");
	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化非自营商品管理(部分商品属性可修改)
	 * @param httpModel
	 */
	public void initSecondGoods(HttpModel httpModel){
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/goodsManaer/zjcSecondGoods.jsp");
	}
}
