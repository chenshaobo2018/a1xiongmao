/**
 * 
 */
package com.store.goods.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import aos.framework.core.asset.WebCxt;
import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCxt;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.goods.controller.GoodsController;
import com.store.goods.service.StoreGoodsService;
import com.store.goods.service.StoreOrderService;
import com.zjc.goods.dao.ZjcBrandDao;
import com.zjc.goods.dao.ZjcGoodsCategoryDao;
import com.zjc.goods.dao.ZjcGoodsTypeDao;
import com.zjc.goods.dao.po.ZjcBrandPO;
import com.zjc.goods.dao.po.ZjcGoodsCategoryPO;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.goods.dao.po.ZjcGoodsSpecVO;
import com.zjc.goods.dao.po.ZjcGoodsTypePO;
import com.zjc.goodsImage.dao.ZjcGoodsImagesDao;
import com.zjc.goodsImage.dao.po.ZjcGoodsImagesPO;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.po.ZjcSellerInfoPO;
import com.zjc.store.dao.po.ZjcStorePO;

/**
 * @author Administrator
 *
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/store")
public class StoreGoodsController {
	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Autowired
	private StoreGoodsService storeGoodsService;
	@Autowired
	private StoreOrderService storeOrderService;
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcGoodsCategoryDao zjcGoodsCategoryDao;
	@Autowired
	private ZjcGoodsImagesDao zjcGoodsImagesDao;
	@Autowired
	private ZjcBrandDao zjcBrandDao;
	@Autowired
	private ZjcGoodsTypeDao zjcGoodsTypeDao;
	
	@RequestMapping(value = "/toProductPage", method=RequestMethod.GET)
	public String toProductPage(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		if(AOSUtils.isEmpty(httpModel.getRequest().getSession().getAttribute("categorys"))){
			List<Dto> categorys = storeGoodsService.getAllCategorys(httpModel);
			httpModel.getRequest().getSession().setAttribute("categorys", categorys);
		}
		if(AOSUtils.isEmpty(httpModel.getRequest().getSession().getAttribute("brinds"))){
			List<Dto> brinds = storeGoodsService.getAllBrands(httpModel);
			httpModel.getRequest().getSession().setAttribute("brinds", brinds);
		}
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO) httpModel.getRequest().getSession().getAttribute("sellerInfo");
		//查询店铺信息
		ZjcStorePO zjcStorePO = zjcStoreDao.selectByKey(sellerInfoPO.getStore_id());
		request.setAttribute("zjcStorePO", zjcStorePO);
		return "project/store/mct_product_list.jsp";
	}
	//搜索商品列表
	@RequestMapping(value = "/serachGoods")
	public void serachgoods(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		//商品数据
		PageVO goods =  storeGoodsService.serachgoods(httpModel);
		WebCxt.write(response, AOSJson.toJson(goods));
	}
	
	//搜索待审核商品列表
	@RequestMapping(value = "/serachPendingGoods")
	public void serachPendingGoods(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO pending_goods =  storeGoodsService.serachPendingGoods(httpModel);
		WebCxt.write(response, AOSJson.toJson(pending_goods));
	}	
	
	@RequestMapping(value="/serachComments")
	public void serachComments(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO comments= storeGoodsService.serachComments(httpModel);
		WebCxt.write(response, AOSJson.toJson(comments));
	}
	
	@RequestMapping(value="/serachUnsale")
	public void serachUnsale(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		PageVO rece= storeGoodsService.serachUnsale(httpModel);
		WebCxt.write(response, AOSJson.toJson(rece));
	}
	//查找商品的一级分类
	@RequestMapping(value = "/toaddgoods")
	public String toaddgoods(HttpServletRequest request, HttpServletResponse response){
		List<ZjcBrandPO> brandPOs = zjcBrandDao.list(null);
		List<ZjcGoodsTypePO> goodsTypePOs = zjcGoodsTypeDao.list(null);
		request.setAttribute("brandPOs", brandPOs);
		request.setAttribute("goodsTypePOs", goodsTypePOs);
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO)request.getSession().getAttribute("sellerInfo");
		//查询店铺信息
		ZjcStorePO zjcStorePO = zjcStoreDao.selectByKey(sellerInfoPO.getStore_id());
		request.setAttribute("zjcStorePO", zjcStorePO);
		return "project/store/mct_add.jsp";
	}	

	//商品详情
	@RequestMapping(value = "/goodsDetial")
	public String  goodsDetial(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map){
		HttpModel httpModel = new HttpModel(request, response);
		ZjcGoodsPO goods =storeGoodsService.goodsDetial(httpModel);
		List<ZjcBrandPO> brandPOs = zjcBrandDao.list(null);
		List<ZjcGoodsTypePO> goodsTypePOs = zjcGoodsTypeDao.list(null);
		request.setAttribute("brandPOs", brandPOs);
		request.setAttribute("goodsTypePOs", goodsTypePOs);
		map.put("goods", goods);
		List<ZjcGoodsImagesPO> images = zjcGoodsImagesDao.list(Dtos.newDto("goods_id", goods.getGoods_id()));
		String oldImages = "";
		if(images != null && images.size()>0){
			for(ZjcGoodsImagesPO oldimg :images){
				oldImages += (oldimg.getImage_url() + ";");
			}
		}
		ZjcSellerInfoPO sellerInfoPO = (ZjcSellerInfoPO)request.getSession().getAttribute("sellerInfo");
		//查询店铺信息
		ZjcStorePO zjcStorePO = zjcStoreDao.selectByKey(sellerInfoPO.getStore_id());
		request.setAttribute("zjcStorePO", zjcStorePO);
		map.put("goodsImages", oldImages);
		return "project/store/mct_detial.jsp";
	}
	
	//更新商品信息
	@RequestMapping(value = "/updateGoods")
	public void  updateGoods(RedirectAttributes attr,HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception{
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO row = storeGoodsService.updateGoods(httpModel);
		WebCxt.write(response, AOSJson.toJson(row));
	}
	
	@RequestMapping(value = "/saveGoods",method=RequestMethod.POST)
	public String saveGoods(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg = storeGoodsService.saveGoods(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
		return httpModel.getViewPath();
	}
	
	@RequestMapping(value = "/listGoodsCategory2")
	public String listGoodsCategory2(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		Dto qDto = httpModel.getInDto();
		Integer id = qDto.getInteger("id");
		List<Dto> list = null;
		if(id != null){
			ZjcGoodsCategoryPO zjcGoodsCategoryPO = zjcGoodsCategoryDao.selectByKey(id);
			list = sqlDao.list("com.zjc.goods.dao.ZjcGoodsCategoryDao.listGoodsCategory2", Dtos.newDto("parent_id_path", zjcGoodsCategoryPO.getParent_id_path()));
		}
		WebCxt.write(response, AOSJson.toJson(list));
		return httpModel.getViewPath();
	}
	
	//删除商品到下架
	@RequestMapping(value = "/del_to_unsale")
	public String  del_to_unsale(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg = storeGoodsService.del_to_unsale(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
		return httpModel.getViewPath();
	}
	
	//删除商品到下架
	@RequestMapping(value = "/del_to_unsales")
	public void  del_to_unsales(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map){
		HttpModel httpModel = new HttpModel(request, response);
		storeGoodsService.del_to_unsales(httpModel);
	}
	
	//待审商品撤销审核
	@RequestMapping(value = "/revoke")
	public String  revoke(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg = storeGoodsService.revoke(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
		return httpModel.getViewPath();
	}	
	
	@RequestMapping(value="/grounding")
	public String grounding(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		MessageVO msg  = storeGoodsService.grounding(httpModel);
		WebCxt.write(response, AOSJson.toJson(msg));
		return httpModel.getViewPath();	
	}
	
	//获取商品规格
	@RequestMapping(value = "/listGoodsSpec")
	public String listGoodsSpec(HttpServletRequest request, HttpServletResponse response){
		HttpModel httpModel = new HttpModel(request, response);
		response.setContentType("application/x-json;charset=UTF-8");
		Dto qDto = httpModel.getInDto();
		List<ZjcGoodsSpecVO> list = null;
		if(qDto.getString("tyep_id") != null){
			list = sqlDao.list("com.zjc.goods.dao.ZjcSpecDao.listGoodsSpec", qDto);
		}
		WebCxt.write(response, AOSJson.toJson(list));
		return httpModel.getViewPath();
	}
	
	/**
	 * 通用商品图片上传
	 * 
	 * @param httpModel
	 * @return
	 */
	@RequestMapping(value = "/newGoodsUpload")
	public String newGoodsUpload(HttpServletRequest request, HttpServletResponse response) {
		HttpModel httpModel = new HttpModel(request, response);
		String paramkey = httpModel.getInDto().getString("paramkey");
		String windowsId = httpModel.getInDto().getString("windowsId");
		String inputName = httpModel.getInDto().getString("inputName");
		String updategoods = httpModel.getInDto().getString("updategoods");
		if(StringUtils.isNotEmpty(paramkey)){
			String [] stringArr = AOSCxt.getParam(paramkey).split(":");
			Double prop = Double.parseDouble(stringArr[0]) / Double.parseDouble(stringArr[1]);
			DecimalFormat df = new DecimalFormat("#.00");   
			httpModel.setAttribute("prop",df.format(prop));
		}
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setAttribute("windowsId", windowsId);
		httpModel.setAttribute("inputName", inputName);
		httpModel.setAttribute("updategoods", updategoods);
		return "project/zjc/common/storeGoodsPicUpload.jsp";
	}
}
