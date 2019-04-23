package com.api.hl.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;





















import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSUtils;
import aos.framework.core.utils.FileUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;

import com.api.hl.dao.TGoodsDao;
import com.api.hl.dao.TGutboundDao;
import com.api.hl.dao.TOrderDao;
import com.api.hl.dao.TOrderDetailedDao;
import com.api.hl.dao.TProjectDao;
import com.api.hl.dao.TSpecificationsDao;
import com.api.hl.dao.TSupplierDao;
import com.api.hl.dao.TTypeDao;
import com.api.hl.dao.TUserProjectDao;
import com.api.hl.dao.TUserhlDao;
import com.api.hl.dao.po.TGoodsPO;
import com.api.hl.dao.po.TGutboundPO;
import com.api.hl.dao.po.TOrderDetailedPO;
import com.api.hl.dao.po.TOrderPO;
import com.api.hl.dao.po.TProjectPO;
import com.api.hl.dao.po.TSpecificationsPO;
import com.api.hl.dao.po.TSupplierPO;
import com.api.hl.dao.po.TTypePO;
import com.api.hl.dao.po.TUserProjectPO;
import com.api.hl.dao.po.TUserhlPO;
import com.gexin.fastjson.JSONArray;
import com.gexin.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;


@Service(value="HtService")
public class HtService {
	
	@Autowired
	private IdService IdService;
	@Autowired
	private TGoodsDao t_goodsMapper;
	
	@Autowired
	private TTypeDao t_typeMapper;
	@Autowired
	private TGutboundDao TGutboundDao;
	@Autowired
	private TProjectDao t_projectMapper;
	
	@Autowired
	private TSupplierDao t_supplierMapper;
	
	@Autowired
	private TSpecificationsDao t_specificationsMapper;
	
	@Autowired
	private TUserProjectDao t_user_projectMapper;
	
	@Autowired
	private TUserhlDao t_userhlMapper;
	
	@Autowired
	private TOrderDao t_orderMapper;
	
	@Autowired
	private TOrderDetailedDao t_order_detailedMapper;
		
	/** 新增/修改保存材料信息
	 * @param inDto
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@Transactional
	public Dto saveT_goodsPO(Dto inDto) throws IOException {
		Dto outDto = Dtos.newDto();
		//上传图片
		
		/*FileUtils fu = new FileUtils();
		String path = fu.fileUpload(savePath, file).getPath();
		int indexs = path.indexOf("\\img\\");
        String pt = path.substring(indexs);
        pt = ".." + pt.replace("\\", "/");*/
		//保存信息
		String specjsonstr = inDto.getString("specjson");
		TGoodsPO po = new TGoodsPO();
		AOSUtils.copyProperties(inDto, po);
		po.setImg_src(inDto.getString("ad_code"));
		String id = inDto.getString("id");
		if(AOSUtils.isNotEmpty(id)){
			String rspecjsonstr = inDto.getString("rspecjson");
		/*	po.setImg_src((null != file && file.getSize() > 0) ? pt : po.getImg_src());*/
			t_goodsMapper.updateByKey(po);
			if(AOSUtils.isNotEmpty(specjsonstr)){
				
				JSONArray specjson = JSONArray.parseArray(specjsonstr);
				
				for (Object specjs : specjson) {
					JSONObject jso = (JSONObject) specjs;
					String specid = jso.getString("id");
					String brand = jso.getString("brand");
					String specifications_name = jso.getString("specifications_name");
					String price = jso.getString("price");
					if(AOSUtils.isNotEmpty(brand) && AOSUtils.isNotEmpty(specifications_name) && 
							AOSUtils.isNotEmpty(price)){
						TSpecificationsPO spepo = new TSpecificationsPO();
						
						spepo.setGoods_id(id);
						spepo.setBrand(brand);
						spepo.setSpecifications_name(specifications_name);
						spepo.setPrice(price);
						if(AOSUtils.isNotEmpty(specid)){
							spepo.setId(specid);
							t_specificationsMapper.updateByKey(spepo);
						}else{
							spepo.setId(IdService.uuid());
							t_specificationsMapper.insert(spepo);
						}
						
					}
				}
			}
			if(AOSUtils.isNotEmpty(rspecjsonstr)){
				JSONArray rspecjson = JSONArray.parseArray(rspecjsonstr);
				
				for (Object rspecjs : rspecjson) {
					JSONObject jso = (JSONObject) rspecjs;
					String specid = jso.getString("id");
					
					t_specificationsMapper.deleteByKey(specid);
				}
			}
			outDto.setAppMsg("操作完成，修改成功。");
		}else{
			String uuid =IdService.uuid();
			po.setId(uuid);
			po.setCreateTime(new Date());
			/*po.setImg_src((null != file && file.getSize() > 0) ? pt : "");*/
			t_goodsMapper.insert(po);
			if(AOSUtils.isNotEmpty(specjsonstr)){
				JSONArray specjson = JSONArray.parseArray(specjsonstr);
				for (Object specjs : specjson) {
					JSONObject jso = (JSONObject) specjs;
					String brand = jso.getString("brand");
					String specifications_name = jso.getString("specifications_name");
					String price = jso.getString("price");
					if(AOSUtils.isNotEmpty(brand) && AOSUtils.isNotEmpty(specifications_name) && 
							AOSUtils.isNotEmpty(price)){
						TSpecificationsPO spepo = new TSpecificationsPO();
						spepo.setId(IdService.uuid());
						spepo.setGoods_id(uuid);
						spepo.setBrand(brand);
						spepo.setSpecifications_name(specifications_name);
						spepo.setPrice(price);
						t_specificationsMapper.insert(spepo);
					}
					
				}
			}
			
			outDto.setAppMsg("操作完成，新增成功。");
		}
		outDto.setAppCode(AOSCons.SUCCESS);
		return outDto;
	}
	
	/** 删除材料信息
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto deleteT_goodsPO(Dto qDto) {
		Dto outDto = Dtos.newDto();
		String id = null != qDto.get("id") ? qDto.get("id").toString() : null;
		t_specificationsMapper.deleteByFKey(id);
		t_goodsMapper.deleteByKey(id);
		String msg = "操作完成, ";
		msg = AOSUtils.merge(msg + "成功删除");
		outDto.setAppMsg(msg);
		return outDto;
	}
	
	/** 新增/修改保存分类信息
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto saveT_typePO(Dto inDto) {
		Dto outDto = Dtos.newDto();
		TTypePO po = new TTypePO();
		AOSUtils.copyProperties(inDto, po);
		Object id = inDto.get("id");
		if(null != id && !"".equals(id)){
			t_typeMapper.updateByKey(po);
			outDto.setAppMsg("操作完成，修改成功。");
		}else{
			po.setId(IdService.uuid());
			t_typeMapper.insert(po);
			outDto.setAppMsg("操作完成，新增成功。");
		}
		outDto.setAppCode(AOSCons.SUCCESS);
		return outDto;
	}
	
	/** 删除分类信息
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto deleteT_typePO(Dto qDto) {
		Dto outDto = Dtos.newDto();
		String selections = qDto.getString("id");
		t_typeMapper.deleteByKey(selections);
		String msg = "操作完成, ";
		msg = AOSUtils.merge(msg + "成功删除");
		outDto.setAppMsg(msg);
		return outDto;
	}
	
	/** 新增/修改保存项目信息
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto saveT_projectPO(Dto inDto) {
		Dto outDto = Dtos.newDto();
		TProjectPO po = new TProjectPO();
		AOSUtils.copyProperties(inDto, po);
		Object id = inDto.get("id");
		if(null != id && !"".equals(id)){
			t_projectMapper.updateByKey(po);
			outDto.setAppMsg("操作完成，修改成功。");
		}else{
			po.setId(IdService.uuid());
			t_projectMapper.insert(po);
			outDto.setAppMsg("操作完成，新增成功。");
		}
		outDto.setAppCode(AOSCons.SUCCESS);
		return outDto;
	}
	
	/** 删除项目信息
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto deleteT_projectPO(Dto qDto) {
		Dto outDto = Dtos.newDto();
		String selections = qDto.getString("id");
		t_projectMapper.deleteByKey(selections);
		String msg = "操作完成, ";
		msg = AOSUtils.merge(msg + "成功删除");
		outDto.setAppMsg(msg);
		return outDto;
	}
	
	/** 新增/修改保存供应商信息
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto saveT_supplierPO(Dto inDto) {
		Dto outDto = Dtos.newDto();
		TSupplierPO po = new TSupplierPO();
		AOSUtils.copyProperties(inDto, po);
		Object id = inDto.get("id");
		if(null != id && !"".equals(id)){
			t_supplierMapper.updateByKey(po);
			outDto.setAppMsg("操作完成，修改成功。");
		}else{
			po.setId(IdService.uuid());
			t_supplierMapper.insert(po);
			outDto.setAppMsg("操作完成，新增成功。");
		}
		outDto.setAppCode(AOSCons.SUCCESS);
		return outDto;
	}
	
	/** 删除供应商信息
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto deleteT_supplierPO(Dto qDto) {
		Dto outDto = Dtos.newDto();
		String[] selections = qDto.getRows();
		int del = 0;
		for (String id_ : selections) {
			t_supplierMapper.deleteByKey(id_);
			del++;
		}
		String msg = "操作完成, ";
		if (del > 0) {
			msg = AOSUtils.merge(msg + "成功删除[{0}]个。", del);
		}
		outDto.setAppMsg(msg);
		return outDto;
	}
	
	/** 新增/修改保存用户项目关系信息
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto saveT_user_projectPO(Dto inDto) {
		Dto outDto = Dtos.newDto();
		TUserProjectPO po = new TUserProjectPO();
		AOSUtils.copyProperties(inDto, po);
		Object id = inDto.get("id");
		if(null != id && !"".equals(id)){
			t_user_projectMapper.updateByKey(po);
			outDto.setAppMsg("操作完成，修改成功。");
		}else{
			po.setId(IdService.uuid());
			t_user_projectMapper.insert(po);
			outDto.setAppMsg("操作完成，新增成功。");
		}
		outDto.setAppCode(AOSCons.SUCCESS);
		return outDto;
	}
	
	
	@Transactional
	public Dto saveT_userPO(Dto inDto) {
		Dto outDto = Dtos.newDto();
		TUserhlPO po = new TUserhlPO();
		AOSUtils.copyProperties(inDto, po);
		Object id = inDto.get("id");
		if(null != id && !"".equals(id)){
			t_userhlMapper.updateByKey(po);
			outDto.setAppMsg("操作完成，修改成功。");
		}else{
			po.setId(IdService.uuid());
			t_userhlMapper.insert(po);
			outDto.setAppMsg("操作完成，新增成功。");
		}
		outDto.setAppCode(AOSCons.SUCCESS);
		return outDto;
	}
	
	
	@Transactional
	public Dto saveGutboundPO(Dto inDto) {
		Dto outDto = Dtos.newDto();
		TGutboundPO po = new TGutboundPO();
		String outboundID=inDto.getString("outboundID");
		String num=inDto.getString("num");
		String account=inDto.getString("account");
		String name=inDto.getString("name");
	    po.setId(IdService.uuid());
	    po.setGoods(outboundID);
	    po.setNum(num);
	    po.setUser_account(account);
	    po.setUser_name(name);
	    po.setAdd_time(new Date());
	    TGutboundDao.insert(po);
		outDto.setAppMsg("操作完成，新增成功。");
		outDto.setAppCode(AOSCons.SUCCESS);
		return outDto;
	}
	
	/** 删除用户项目关系信息
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto deleteT_user_projectPO(Dto qDto) {
		Dto outDto = Dtos.newDto();
		String[] selections = qDto.getRows();
		int del = 0;
		for (String id_ : selections) {
			t_user_projectMapper.deleteByKey(id_);
			del++;
		}
		String msg = "操作完成, ";
		if (del > 0) {
			msg = AOSUtils.merge(msg + "成功删除[{0}]个。", del);
		}
		outDto.setAppMsg(msg);
		return outDto;
	}
	
	/** 调整单价
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto saveChangeQgorderPrice(Dto inDto) {
		Dto outDto = Dtos.newDto();
		String detailjsonstr = inDto.getString("detailjsonstr");
		String order_id = inDto.getString("order_id");
		if(AOSUtils.isNotEmpty(detailjsonstr)){
			JSONArray detailjson = JSONArray.parseArray(detailjsonstr);
			Integer sumprice = 0;
			for (Object detailjs : detailjson) {
				JSONObject jso = (JSONObject) detailjs;
				String id = jso.getString("id");
				String adjust_price = jso.getString("adjust_price");
				String number = jso.getString("number");
				if(AOSUtils.isNotEmpty(adjust_price) && 
						AOSUtils.isNotEmpty(number)){
					TOrderDetailedPO TOrderDetailedPO = t_order_detailedMapper.selectByKey(id);
					TOrderDetailedPO.setAdjust_price(adjust_price);
					t_order_detailedMapper.updateByKey(TOrderDetailedPO);
					
					Integer nm = Integer.parseInt(number);
					Integer adjustprice = Integer.parseInt(adjust_price);
					Integer sumpc = 0;
					sumpc = nm * adjustprice;
					sumprice += sumpc;
				}
			}
			TOrderPO orderpo = t_orderMapper.selectByKey(order_id);
			orderpo.setAdjust_price(sumprice.toString());
			t_orderMapper.updateByKey(orderpo);
			outDto.setAppMsg("操作完成，修改成功。");
		}
		return outDto;
	}
	
	/** 确认订单
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto saveChangeQgorderSure(Dto inDto) {
		Dto outDto = Dtos.newDto();
		String selections = inDto.getString("aos_rows_");
		Dto qDto = Dtos.newDto();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(selections);
		String s = scanner.next() ;
		String[] nums = s.split(",") ;
		for (int i = 0; i < nums.length; i++) {
			if(AOSUtils.isNotEmpty(nums[i])){
				TOrderPO orderpo = t_orderMapper.selectByKey(nums[i]);
				orderpo.setIs_refer("已确认");
				t_orderMapper.updateByKey(orderpo);
				Dto dto = Dtos.newDto();
				dto.put("order_id", nums[i]);
				List<TOrderDetailedPO> details = t_order_detailedMapper.list(dto);
				for(TOrderDetailedPO dtpo : details){
					String speId = dtpo.getSpecifications_id();
					String adjust_price = dtpo.getAdjust_price();
					TSpecificationsPO sppo = t_specificationsMapper.selectByKey(speId);
					sppo.setPrice(adjust_price.toString());
					t_specificationsMapper.updateByKey(sppo);
				}
			}
		}
		qDto.setAppMsg("操作成功");
		return qDto;
	}
	
	/** 删除请购信息
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto saveQgorder(Dto qDto) {
		String selections = qDto.getString("aos_rows_");
		Dto outDto = Dtos.newDto();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(selections);
		String s = scanner.next() ;
		String[] nums = s.split(",") ;
		for (int i = 0; i < nums.length; i++) {
			if(AOSUtils.isNotEmpty(nums[i])){
				TOrderPO po = t_orderMapper.selectByKey(nums[i]);
				po.setStatus("0");
				t_orderMapper.updateByKey(po);
			}
		}
		outDto.setAppMsg("删除成功");
		return outDto;
	}
	
	
	
}
