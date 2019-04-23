package com.api.goods;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.redis.JedisUtil;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.ApiPublicService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.api.goods.dao.ZjcGoodsCollectDao;
import com.api.goods.dao.po.ZjcActivitiyPO;
import com.api.goods.dao.po.ZjcGoodsCategoryVO;
import com.api.goods.dao.po.ZjcGoodsCollectPO;
import com.api.goods.dao.po.ZjcPromptGoodsPO;
import com.zjc.comment.dao.po.ZjcCommentPO;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.ZjcSpecGoodsPriceDao;
import com.zjc.goods.dao.po.ZjcGoodsCategoryPO;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.goods.dao.po.ZjcSpecItemPO;
import com.zjc.goods.dao.po.ZjcSpecPO;
import com.zjc.goodsImage.dao.po.ZjcGoodsImagesPO;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcStorePO;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 不需要Tiken的app接口- 商品管理
 * 
 * @author wgm
 */
@Service(value="goodsService")
public class GoodsService {
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	
	@Autowired
	private ZjcGoodsCollectDao zjcGoodsCollectDao;
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private ZjcSpecGoodsPriceDao zjcSpecGoodsPriceDao;
	
	@Autowired
	private SqlDao sqlDao;
	
	
	/**
	 * app接口-获取店铺的商品
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String getStoreGoods(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		if(AOSUtils.isEmpty(dto.getString("store_id")) || AOSUtils.isEmpty(dto.getInteger("page"))||dto.getInteger("page")<1){//店铺ID不能为空
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(msgVO);
		} 
		//获取store_id
		int store_id = dto.getInteger("store_id");
		ZjcStorePO zjcStorePO = zjcStoreDao.selectByKey(store_id);
		if(AOSUtils.isEmpty(zjcStorePO)){//店铺不存在
			msgVO.setMsg(Apiconstant.Store_Not_Exist.getName());
			msgVO.setCode(Apiconstant.Store_Not_Exist.getIndex());
		} else if(zjcStorePO.getStore_state() == 4){//店铺已被关闭
			msgVO.setMsg(Apiconstant.Store_Has_Closed.getName());
			msgVO.setCode(Apiconstant.Store_Has_Closed.getIndex());
		} else {//验证通过，分页查询
				//设置limit每页条数
				dto.put("limit", ConstantUtil.pageSize);
				//设置start开始条数
				int page = dto.getInteger("page");
				dto.put("start", (page-1)*ConstantUtil.pageSize);
				dto.put("is_on_sale", 1);
				if(AOSUtils.isNotEmpty(dto.getString("goods_name")))
					dto.put("goods_name", ApiPublicService.trim(dto.getString("goods_name")));
				if(AOSUtils.isNotEmpty(dto.getString("keywords")))
					dto.put("keywords", ApiPublicService.trim(dto.getString("keywords")));
				List<Dto> goodsList = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.goodslistPage", dto);
				if(goodsList.size()==0){//所属店铺没有上架商品
					msgVO.setMsg(Apiconstant.Store_No_Goods.getName());
					msgVO.setCode(Apiconstant.Store_No_Goods.getIndex());
				} else {//查询成功
					//生成返回分页参数实体
					PageVO pageVO = ParameterUtil.getPageVO(dto.getInteger("total"), dto.getInteger("page"));
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
					pageVO.setList(goodsList);
					msgVO.setData(pageVO);
				}
			}
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * app接口-获取新商品，默认按上架时间倒叙排列
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String getNewGoods(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto qDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getInteger("page")) || qDto.getInteger("page")<1 
				|| AOSUtils.isEmpty(qDto.getString("desc")) || AOSUtils.isEmpty(qDto.getString("type"))){//当前页数不能为空
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		} 
		//设置limit每页条数
		qDto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		int page = qDto.getInteger("page");
		qDto.put("start", (page-1)*ConstantUtil.pageSize);
		String strsql = "";
		if("all".equals(qDto.getString("type"))){
			strsql = "order by on_time desc";
		} else if("time".equals(qDto.getString("type"))){
			if(qDto.getBoolean("desc")==true){
				strsql = "order by on_time desc";
			} else {
				strsql = "order by on_time";
			}
		} else if("price".equals(qDto.getString("type"))){
			if(qDto.getBoolean("desc")==true){
				strsql = "order by market_price desc";
			} else {
				strsql = "order by market_price";
			}
		}
		qDto.put("strsql", strsql);
		List<ZjcGoodsPO> zjcGoodsPOs=zjcGoodsDao.getNewGoodsPage(qDto);
		if(AOSUtils.isEmpty(zjcGoodsPOs)){//无数据
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			msgVO.setMsg(Apiconstant.NO_DATA.getName());
		} else {
			//生成返回分页参数实体
			PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			pageVO.setList(zjcGoodsPOs);
			msgVO.setData(pageVO);
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * app接口-通过商品ID查询商品详情
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String getGoodsDetailByGoodsId(HttpModel httpModel) {
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		MessageVO msgVO = new MessageVO();
		//根据商品ID查询商品详情信息
		if(AOSUtils.isEmpty(qDto.getString("goods_id"))){
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		}
		//查询限时购商品详情
		//ZjcPromptGoodsPO ZjcPromptGoodsPO = (ZjcPromptGoodsPO) sqlDao.selectOne("com.api.goods.dao.ZjcPromptGoodsDao.selectOne", Dtos.newDto("goods_id", qDto.getString("goods_id")));
		
		
		List<ZjcGoodsPO> goodDetailsList = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.GoodsDetailed", qDto);
		if(goodDetailsList.size() == 0){//暂无数据
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
			msgVO.setMsg(Apiconstant.NO_DATA.getName());
		} else {
			goodDetailsList.get(0).setZjcGoodsImagesPO(null);
			ZjcGoodsImagesPO imgPO = new ZjcGoodsImagesPO();
			imgPO.setImage_url(goodDetailsList.get(0).getOriginal_img());
			List<ZjcGoodsImagesPO> imgsList = new ArrayList<ZjcGoodsImagesPO>();
			imgsList.add(imgPO);
			goodDetailsList.get(0).setZjcGoodsImagesPO(imgsList);
			String aduit_info =  goodDetailsList.get(0).getAduit_info();
			if(AOSUtils.isEmpty(aduit_info)){//该商品还未审核或者审核不通过
				msgVO.setCode(Apiconstant.GOODS_NOT_AUDIT.getIndex());
				msgVO.setMsg(Apiconstant.GOODS_NOT_AUDIT.getName());
			} else {//审核通过
				String[] aduit_infoArr = aduit_info.split(";");
				String str = aduit_infoArr[5];
				String aduit_user_name = str.substring(str.indexOf("\"")+1, str.indexOf("\"",str.indexOf("\"")+1 ));
				goodDetailsList.get(0).setAduit_user_name(aduit_user_name);
				if(!AOSUtils.isEmpty(qDto.getString("token"))){//如果token不为空
					//查询用户信息
					ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectOne(qDto);
					if(!AOSUtils.isEmpty(userInfo)){//如果用户信息不为空
						qDto.put("user_id", userInfo.getUser_id());
						ZjcGoodsCollectPO collectPO = zjcGoodsCollectDao.selectOne(qDto);
						if(!AOSUtils.isEmpty(collectPO)){//如果该用户收藏了该商品
							goodDetailsList.get(0).setHasCollect(true);
						}
					}
				}
				//通过goods_id查询规格项字符串
				String specStr = zjcSpecGoodsPriceDao.getSpecStr(qDto);
				if(!AOSUtils.isEmpty(specStr)){
					specStr = specStr.replace("_", ",");
					String strSql = " and id in ("+ specStr + ")";
					qDto.put("strSql", strSql);
					//查询该商品的符合条件的规格项数据
					List<ZjcSpecItemPO> itemList = sqlDao.list("com.zjc.goods.dao.ZjcSpecItemDao.getSpecItem", qDto);
					//查询商品规格
					String sqlStr = " and t1.id in ("+ specStr + ")";
					qDto.put("sqlStr", sqlStr);
					//查询该商品的规格数据
					List<ZjcSpecPO> specList = sqlDao.list("com.zjc.goods.dao.ZjcSpecDao.getSpecs", qDto);
					if(!AOSUtils.isEmpty(itemList) || !AOSUtils.isEmpty(specList)){
						for(ZjcSpecPO specPO :specList){//规格项分组
							List<ZjcSpecItemPO> newItemList = new ArrayList<ZjcSpecItemPO>();
							for(ZjcSpecItemPO specItemPO :itemList){
								if(specPO.getId()==specItemPO.getSpec_id()){//如果规格数据ZjcSpecPO的ID等于规格项数据ZjcSpecItemPO的spec_id
									newItemList.add(specItemPO);//追加到List,分组
								}
							}
							specPO.setZjcSpecItemPOList(newItemList);
						}
						goodDetailsList.get(0).setZjcSpecPOList(specList);
					} 
				}
				/*Jedis jedis = JedisUtil.getJedisClient();
				goodDetailsList.get(0).setGoods_content(jedis.hmget("goodsDetail",goodDetailsList.get(0).getGoods_id().toString()).get(0).toString());
				JedisUtil.close(jedis);*/
				/*if(!(goodDetailsList.get(0).getShop_price().compareTo(BigDecimal.ZERO)==0)){//现金不为0http://omhpz0ifi.bkt.clouddn.com/goodsimg/1523612254177094324.jpg
					String s1 ="<p><img src='http://omhpz0ifi.bkt.clouddn.com/goodsimg/1528092436968075781.jpg'/></p>"; 
					String s2 ="<p><img src='http://omhpz0ifi.bkt.clouddn.com/goodsimg/1512449359869079254.jpg'/></p>"; 
					goodDetailsList.get(0).setGoods_content(goodDetailsList.get(0).getGoods_content()+s1+s2);
				}*/
				goodDetailsList.get(0).setGoods_content(ParameterUtil.createHtmlBody(goodDetailsList.get(0).getGoods_name(), goodDetailsList.get(0).getGoods_content()));
				ZjcPromptGoodsPO ZjcPromptGoodsPO = (ZjcPromptGoodsPO) sqlDao.selectOne("com.api.goods.dao.ZjcPromptGoodsDao.selectOne", Dtos.newDto("goods_id", qDto.getString("goods_id")));
				if(AOSUtils.isNotEmpty(ZjcPromptGoodsPO)){//判断是否是限时抢购商品，是的话，返回显示抢购商品属性
					SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					goodDetailsList.get(0).setEnd_time(dft.format(ZjcPromptGoodsPO.getEnd_time()));
					goodDetailsList.get(0).setStart_time(dft.format(ZjcPromptGoodsPO.getStart_time()));
					goodDetailsList.get(0).setLimit_cost_price(ZjcPromptGoodsPO.getLimit_cost_price());
					goodDetailsList.get(0).setLimit_market_price(ZjcPromptGoodsPO.getLimit_market_price());
					Jedis jedis = JedisUtil.getJedisClient();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try{
						//获取该活动商品销量
						String goods_sale_num = jedis.get(qDto.getString("goods_id")+":"+sdf.format(new Date()));
						if(AOSUtils.isEmpty(goods_sale_num)){//还未购买，销量为0
							goodDetailsList.get(0).setLimit_goods_sale(0);
						} else {
							goodDetailsList.get(0).setLimit_goods_sale(Integer.parseInt(goods_sale_num));
						}
						//获取限时抢购购商品围观人数
						String userStr = jedis.get(qDto.getString("goods_id"));
						if(AOSUtils.isEmpty(userStr)){//围观人数为空，设置默认围观人数
							jedis.set(qDto.getString("goods_id"),"1000");
							goodDetailsList.get(0).setOnlookers(1000);
						}else{//围观人数不为空，在原围观人数上+1
							jedis.del(qDto.getString("goods_id"));
							jedis.set(qDto.getString("goods_id"),Integer.parseInt(userStr)+1+"");
							goodDetailsList.get(0).setOnlookers(Integer.parseInt(userStr));
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						jedis.close();
					}
				}
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				msgVO.setData(goodDetailsList.get(0));
			}
		}
		
		return AOSJson.toJson(msgVO);
	}

	/**
	 * app接口-收藏商品
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String collect_goods(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		int isTwo = 0;
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		qDto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
		int goods_id = qDto.getInteger("goods_id");
		if(AOSUtils.isEmpty(goods_id)){//参数为空
			msgVO.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Condition_Is_Null.getName());
			return AOSJson.toJson(msgVO);
		} 
		ZjcGoodsPO goods = zjcGoodsDao.selectByKey(goods_id);
		if(AOSUtils.isEmpty(goods)){//商品不存在
			msgVO.setCode(Apiconstant.Goods_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Goods_Is_Null.getName());
		} else {//收藏商品
			qDto.put("user_id", httpModel.getAttribute("user_id"));
			ZjcGoodsCollectPO oldGoodsCollectPO = zjcGoodsCollectDao.selectOne(qDto);
			if(!AOSUtils.isEmpty(oldGoodsCollectPO)){//判断该商品自己是否已收藏
				msgVO.setCode(Apiconstant.Goods_Was_Collected.getIndex());
				msgVO.setMsg(Apiconstant.Goods_Was_Collected.getName());
			} else {//该商品未收藏，收藏商品
				ZjcGoodsCollectPO zjcGoodsCollectPO = new ZjcGoodsCollectPO();
				//zjcGoodsCollectPO.setCollect_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				zjcGoodsCollectPO.setUser_id(qDto.getBigInteger("user_id"));
				zjcGoodsCollectPO.setGoods_id(goods_id);
				zjcGoodsCollectPO.setAdd_time(new Date());
				try {
					int successNum = zjcGoodsCollectDao.insert(zjcGoodsCollectPO);
					isTwo++;
					if(successNum == 0 && isTwo ==0){//保存失败
						msgVO.setCode(Apiconstant.Save_fails.getIndex());
						msgVO.setMsg(Apiconstant.Save_fails.getName());
					} else {//收藏成功
						msgVO.setCode(Apiconstant.Do_Success.getIndex());
						msgVO.setMsg(Apiconstant.Do_Success.getName());
					}
				} catch (Exception e) {
					msgVO.setCode(Apiconstant.Do_Fails.getIndex());
					msgVO.setMsg(Apiconstant.Do_Fails.getName());
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * app接口-取消收藏商品
	 * 
	 * @param httpModel
	 * @return
	 */
	public String cancle_collect_goods(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		qDto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
		int goods_id = qDto.getInteger("goods_id");
		if(AOSUtils.isEmpty(goods_id)){//参数为空
			msgVO.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVO.setData(Apiconstant.Condition_Is_Null.getName());
		} else {//条件符合要求
			ZjcGoodsCollectPO zjcGoodsCollectPO = zjcGoodsCollectDao.selectOne(qDto);
			if(AOSUtils.isEmpty(zjcGoodsCollectPO)){//收藏的商品不存在
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
			} else {//取消收藏商品
				try {
					zjcGoodsCollectDao.deleteByKey(zjcGoodsCollectPO.getCollect_id());
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
				} catch (Exception e) {
					e.printStackTrace();
					msgVO.setCode(Apiconstant.Do_Fails.getIndex());
					msgVO.setMsg(Apiconstant.Do_Fails.getName());
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}

	/**
	 * app接口-查看我的收藏商品
	 * 
	 * @param httpModel
	 * @return
	 */
	public String getMyCollectGoods(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		qDto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
		if(AOSUtils.isEmpty(qDto.getInteger("page"))||qDto.getInteger("page")<1){//当前页数不能为空
			msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			return AOSJson.toJson(msgVO);
		}
		//设置limit每页条数
		qDto.put("limit", ConstantUtil.pageSize);
		//设置start开始条数
		int page = qDto.getInteger("page");
		qDto.put("start", (page-1)*ConstantUtil.pageSize);
		//通过user_id查询收藏的商品列表
		List<ZjcGoodsCollectPO> goodsCollectList = sqlDao.list("com.api.goods.dao.ZjcGoodsCollectDao.listMyCollectGoodsPage", qDto);
		if(AOSUtils.isEmpty(goodsCollectList)){//查询无数据
			msgVO.setMsg(Apiconstant.NO_DATA.getName());
			msgVO.setCode(Apiconstant.NO_DATA.getIndex());
		} else {//有数据
			//生成返回分页参数实体
			PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
			msgVO.setCode(Apiconstant.Do_Success.getIndex());
			msgVO.setMsg(Apiconstant.Do_Success.getName());
			pageVO.setList(goodsCollectList);
			msgVO.setData(pageVO);
		}
		return AOSJson.toJson(msgVO);
	}
	
	
	/**
	 * 查询分类查询
	 * 
	 * @param httpModel
	 */
	public String getGoodsCategorys(HttpModel httpModel){
		MessageVO msgVO = new MessageVO();
		Dto qdto = httpModel.getInDto();
		List<ZjcGoodsCategoryPO> list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsCategoryDao.queryCategoryList", qdto);
		msgVO.setData(list);
		return AOSJson.toJson(msgVO);
	}
	
	/**
	 * app接口-商品分类
	 * 
	 * @param httpModel
	 * @return
	 */
	public String get_goods_category_tree(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		String str = "";
//		String str = JedisUtil.getJedisClient().get("categoryVOs");
//		if(AOSUtils.isEmpty(JedisUtil.getJedisClient().get("categoryVOs"))){
			List<ZjcGoodsCategoryVO> categoryVOs=sqlDao.list("com.zjc.goods.dao.ZjcGoodsCategoryDao.get_goods_category", qDto);
			if(AOSUtils.isEmpty(categoryVOs)){
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
				str = AOSJson.toJson(msgVO);
			}else{
				msgVO.setCode(Apiconstant.Do_Success.getIndex());
				msgVO.setMsg(Apiconstant.Do_Success.getName());
				msgVO.setData(categoryVOs);
				//JedisUtil.getJedisClient().set("categoryVOs", AOSJson.toJson(msgVO));
				//str = JedisUtil.getJedisClient().get("categoryVOs");
				str = AOSJson.toJson(msgVO);
			}
//		}
		return str;
	}
	
	/**
	 * 获取商品评论数据
	 * @param request
	 * @param response
	 * @return
	 */
	public String GoodsComments(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getInteger("page"))||qDto.getInteger("page")<1 
				|| AOSUtils.isEmpty(qDto.getInteger("goods_id"))){//当前页数不能为空
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = qDto.getInteger("page");
			qDto.put("start", (page-1)*ConstantUtil.pageSize);
			List<ZjcCommentPO> goodsid = sqlDao.list("com.zjc.comment.dao.ZjcCommentDao.goodsidPage", qDto);
			if(goodsid.size()==0){
				MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
				MessageVO.setMsg(Apiconstant.NO_DATA.getName());
			}else{
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
				pageVO.setList(goodsid);
				MessageVO.setData(pageVO);
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}
		}
		return AOSJson.toJson(MessageVO);
	}
	
	/**
	 * 获取商品评论数据
	 * @param request
	 * @param response
	 * @return
	 */
	public String queryLimitGoods(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		
		if(AOSUtils.isEmpty(qDto.getInteger("start_time"))||AOSUtils.isEmpty(qDto.getInteger("end_time"))){
			MessageVO.setCode(Apiconstant.parameter_is_not_null.getIndex());
			MessageVO.setMsg(Apiconstant.parameter_is_not_null.getName());
			return AOSJson.toJson(MessageVO);
		}
		if(AOSUtils.isEmpty(qDto.getInteger("page"))||qDto.getInteger("page")<1){//当前页数不能为空
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
		} else {//当前页数不为空
			//设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = qDto.getInteger("page");
			qDto.put("start", (page-1)*ConstantUtil.pageSize);
			List<ZjcGoodsPO> goods = sqlDao.list("com.zjc.goods.dao.ZjcGoodsDao.queryLimitGoodsPage", qDto);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < goods.size(); i++) {
				Jedis jedis = JedisUtil.getJedisClient();
				try{
					//获取该活动商品销量
					String goods_sale_num = jedis.get(goods.get(i).getGoods_id().toString()+":"+sdf.format(new Date()));
					if(AOSUtils.isEmpty(goods_sale_num)){//还未购买，销量为0
						goods.get(i).setLimit_goods_sale(0);
					} else {
						goods.get(i).setLimit_goods_sale(Integer.parseInt(goods_sale_num));
					}
					String userStr = jedis.get(goods.get(i).getGoods_id().toString());
					if(AOSUtils.isEmpty(userStr)){
						jedis.set(goods.get(i).getGoods_id().toString(),"1000");
						goods.get(i).setOnlookers(1000);
					}else{
						jedis.del(goods.get(i).getGoods_id().toString());
						jedis.set(goods.get(i).getGoods_id().toString(),Integer.parseInt(userStr)+1+"");
						goods.get(i).setOnlookers(Integer.parseInt(userStr));
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					jedis.close();
				}
			}
			if(goods.size()==0){
				MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
				MessageVO.setMsg(Apiconstant.NO_DATA.getName());
			}else{
				//生成返回分页参数实体
				PageVO pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), qDto.getInteger("page"));
				pageVO.setList(goods);
				MessageVO.setData(pageVO);
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
				
				
			}
		}
		return AOSJson.toJson(MessageVO);
	}
	
	public String newDate(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		MessageVO.setData(new Date());
		MessageVO.setCode(Apiconstant.Do_Success.getIndex());
		MessageVO.setMsg(Apiconstant.Do_Success.getName());
		return AOSJson.toJson(MessageVO);
	}
	
	
	/**
	 * 获取活动
	 * @param request
	 * @param response
	 * @return
	 */
	public String queryZjcActivitiy(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		HttpModel httpModel=new HttpModel(request, response);
		Dto qDto=httpModel.getInDto();
		qDto.put("time", new Date());
		List<ZjcActivitiyPO> ZjcActivitiyPO = sqlDao.list("com.api.goods.dao.ZjcActivitiyDao.listActivities", qDto);
		if(ZjcActivitiyPO.size() == 0){
			MessageVO.setCode(Apiconstant.NO_ACTIVITY.getIndex());
			MessageVO.setMsg(Apiconstant.NO_ACTIVITY.getName());
		}else {
			MessageVO.setData(ZjcActivitiyPO);
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
		}
		return AOSJson.toJson(MessageVO);
	}
	
}
