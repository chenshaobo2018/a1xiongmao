/**
 * 
 */
package com.api.order;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.order.dao.ZjcCartDao;
import com.api.order.dao.po.CatVO;
import com.api.order.dao.po.Goods;
import com.api.order.dao.po.ZjcCartPO;
import com.google.common.reflect.TypeToken;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.store.dao.po.ZjcStorePO;

/**
 * @author Administrator
 *
 */
@Service(value = "CartService")
public class CartService {
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private ZjcCartDao ZjcCartDao;
	@Autowired
	private IdService idService;
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	
	/**
	 * 添加商品到购物车
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("serial")
	public String addCart(HttpServletRequest request, HttpServletResponse response){
			MessageVO MessageVO=new MessageVO();
			HttpModel httpModel=new HttpModel(request, response);
			Dto inDto = httpModel.getInDto();
			ZjcCartPO ZjcCartPO=new ZjcCartPO();
			if(AOSUtils.isEmpty(inDto.get("Goods")) || AOSUtils.isEmpty(inDto.getInteger("type")) ){
				MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
				return AOSJson.toJson(MessageVO);
			}
			List<Goods> order=AOSJson.fromJson(httpModel.getRequest().getParameter("Goods").toString(), new TypeToken<List<Goods>>(){}.getType());
			ZjcCartPO.setUser_id(new BigInteger(request.getAttribute("user_id").toString()));
			try{
				for (Goods goods2 : order) {
					ZjcCartPO.setGoods_id(Integer.parseInt(goods2.getGoods_id()));
					List<ZjcCartPO> paramDtos = sqlDao.list("com.api.order.dao.ZjcCartDao.queryCartgoods", ZjcCartPO);
					if(paramDtos.size()>0){
						for (int i = 0; i < paramDtos.size(); i++) {
							ZjcCartPO.setId(paramDtos.get(i).getId());
							
							if(request.getParameter("type").equals("1")){
								ZjcCartPO.setGoods_num(Integer.parseInt(goods2.getGoods_num()));
							}else {
								int num=Integer.parseInt(goods2.getGoods_num())+paramDtos.get(0).getGoods_num();
								ZjcCartPO.setGoods_num(num);
							}
							ZjcCartDao.updateByKey(ZjcCartPO);
						}
					}else{
						ZjcGoodsPO zjcGoodsPO = zjcGoodsDao.selectByKey(Integer.parseInt(goods2.getGoods_id()));
						ZjcCartPO.setId(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
						ZjcCartPO.setAdd_time(new Date());
						ZjcCartPO.setGoods_num(Integer.parseInt(goods2.getGoods_num()));;
						ZjcCartPO.setGoods_sn(zjcGoodsPO.getGoods_sn());
						ZjcCartPO.setGoods_name(zjcGoodsPO.getGoods_name());
						ZjcCartPO.setMarket_price(zjcGoodsPO.getMarket_price());
						ZjcCartPO.setShop_price(zjcGoodsPO.getShop_price());
						ZjcCartPO.setCost_price(zjcGoodsPO.getCost_price());
						ZjcCartPO.setProm_type(zjcGoodsPO.getProm_type());
						ZjcCartPO.setProm_id(zjcGoodsPO.getProm_id());
						ZjcCartPO.setSku(zjcGoodsPO.getSku());
						ZjcCartPO.setStore_id(zjcGoodsPO.getStore_id());
						ZjcCartPO.setImg(zjcGoodsPO.getOriginal_img());
						ZjcCartDao.insert(ZjcCartPO);
					}
				}
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			} catch(Exception e){
				e.printStackTrace();	
				MessageVO.setMsg(Apiconstant.Do_Fails.getName());
				MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
			}
			return AOSJson.toJson(MessageVO);
		}
	
	/**
	 * 拉取购物车列表
	 * @param request
	 * @param response
	 * @return
	 */
	public String cartList(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		ZjcCartPO ZjcCartPO1=new ZjcCartPO();
		ZjcCartPO1.setUser_id(new BigInteger(request.getAttribute("user_id").toString()));
		
		if(request.getAttribute("user_id")==null){
			MessageVO.setCode(Apiconstant.Srore_No_Login.getIndex());
			MessageVO.setMsg(Apiconstant.Srore_No_Login.getName());
		}else{
			try{
				List<ZjcCartPO> paramDtos = sqlDao.list("com.api.order.dao.ZjcCartDao.queryCartList", ZjcCartPO1);
				ZjcCartPO ZjcCartPO=new ZjcCartPO();
				List<CatVO> list=new ArrayList<CatVO>();
				ZjcStorePO ZjcStorePO=new ZjcStorePO();
				for (int i = 0; i < paramDtos.size(); i++) {
					if(i>=1){
						//判断是否已有该店铺对应的购物车集合
						boolean b = false;
						for(CatVO catv :list){
							if(catv.getStore_id() == paramDtos.get(i).getStore_id()){
								b =true;
							}
						}
						if(!b){
							CatVO catvo=new CatVO();
							ZjcStorePO.setStore_id(paramDtos.get(i).getStore_id());
							List<ZjcStorePO> Stores=sqlDao.list("com.zjc.store.dao.ZjcStoreDao.queryStore",ZjcStorePO);
							ZjcCartPO.setStore_id(paramDtos.get(i).getStore_id());
							ZjcCartPO.setUser_id(paramDtos.get(i).getUser_id());
							List<ZjcCartPO> cat = sqlDao.list("com.api.order.dao.ZjcCartDao.Carstore_id", ZjcCartPO);
							catvo.setStore_id(ZjcCartPO.getStore_id());
							for (int j = 0; j < cat.size(); j++) {
								cat.get(j).setStore_name(Stores.get(0).getStore_name());
								ZjcGoodsPO good = zjcGoodsDao.selectByKey(cat.get(j).getGoods_id());
								cat.get(j).setIs_mixed(good.getIs_mixed()+"");
								cat.get(j).setIs_voucher(good.getIs_voucher()+"");
								cat.get(j).setGoods_name(good.getGoods_name());
								cat.get(j).setGoods_sn(good.getGoods_sn());
								cat.get(j).setMarket_price(good.getMarket_price());
								cat.get(j).setShop_price(good.getShop_price());
								cat.get(j).setCost_price(good.getCost_price());
							}
							catvo.setZjcCartPO(cat);
							catvo.setStore_name(Stores.get(0).getStore_name());
							list.add(catvo);
						}
						b = false;
					} else{
						CatVO catvo=new CatVO();
						ZjcStorePO.setStore_id(paramDtos.get(i).getStore_id());
						List<ZjcStorePO> Stores=sqlDao.list("com.zjc.store.dao.ZjcStoreDao.queryStore",ZjcStorePO);
						ZjcCartPO.setStore_id(paramDtos.get(i).getStore_id());
						ZjcCartPO.setUser_id(paramDtos.get(i).getUser_id());
						List<ZjcCartPO> cat = sqlDao.list("com.api.order.dao.ZjcCartDao.Carstore_id", ZjcCartPO);
						for (int j = 0; j < cat.size(); j++) {
							cat.get(j).setStore_name(Stores.get(0).getStore_name());
							ZjcGoodsPO good = zjcGoodsDao.selectByKey(cat.get(j).getGoods_id());
							cat.get(j).setIs_mixed(good.getIs_mixed()+"");
							cat.get(j).setIs_voucher(good.getIs_voucher()+"");
							cat.get(j).setGoods_name(good.getGoods_name());
							cat.get(j).setGoods_sn(good.getGoods_sn());
							cat.get(j).setMarket_price(good.getMarket_price());
							cat.get(j).setShop_price(good.getShop_price());
							cat.get(j).setCost_price(good.getCost_price());
						}
						catvo.setStore_id(ZjcCartPO.getStore_id());
						catvo.setZjcCartPO(cat);
						catvo.setStore_name(Stores.get(0).getStore_name());
						list.add(catvo);
					}
					
					
				}
				MessageVO.setData(list);
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}catch(Exception e){
				e.printStackTrace();
				MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Fails.getName());
			}			
		}
		return AOSJson.toJson(MessageVO);
	}
     
	/**
	 * 根据id删除购物车里的商品
	 * @param request
	 * @param response
	 * @return
	 */
	public String delCart(HttpServletRequest request, HttpServletResponse response){
		MessageVO MessageVO=new MessageVO();
		if(AOSUtils.isEmpty(request.getParameter("id"))){
			MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
			MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
			return AOSJson.toJson(MessageVO);
		}
		 String [] id=request.getParameter("id").split(",");
		 for (int j = 0; j < id.length; j++) {
			 String ids=id[j];
			 ZjcCartPO ZjcCartPO=new ZjcCartPO();
			 ZjcCartPO.setId(Integer.parseInt(ids));
			 sqlDao.list("com.api.order.dao.ZjcCartDao.deleteByKey", ZjcCartPO);
		 }
		 MessageVO.setMsg(Apiconstant.Do_Success.getName());
		 MessageVO.setCode(Apiconstant.Do_Success.getIndex());
	     return AOSJson.toJson(MessageVO);
	}
}
