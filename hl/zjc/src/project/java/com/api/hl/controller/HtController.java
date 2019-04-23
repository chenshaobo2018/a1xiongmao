package com.api.hl.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hl.dao.TGoodsDao;
import com.api.hl.dao.TOrderDao;
import com.api.hl.dao.TOrderDetailedDao;
import com.api.hl.dao.TProjectDao;
import com.api.hl.dao.TSpecificationsDao;
import com.api.hl.dao.TSupplierDao;
import com.api.hl.dao.TTypeDao;
import com.api.hl.dao.TUserProjectDao;
import com.api.hl.dao.TUserhlDao;
import com.api.hl.dao.po.TOrderPO;
import com.api.hl.dao.po.TProjectPO;
import com.api.hl.dao.po.TSpecificationsPO;
import com.api.hl.dao.po.TSupplierPO;
import com.api.hl.dao.po.TTypePO;
import com.api.hl.dao.po.TUserProjectPO;
import com.api.hl.dao.po.TUserhlPO;
import com.api.hl.service.HtService;
import com.api.hl.vo.T_goodsVO;
import com.api.hl.vo.T_order_detailedVO;
import com.api.hl.vo.T_orderqgtjVO;
import com.api.hl.vo.T_user_projectVO;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.model.UserModel;
import net.sf.jasperreports.engine.JRException;






/**
 * 后台管理controller
 * @author Administrator
 *
 */
@Service(value="HtController")
public class HtController {
	@Autowired
	private IdService IdService;
	@Autowired
	private TGoodsDao t_goodsMapper;
	
	@Autowired
	private TTypeDao t_typeMapper;
	
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
	
	@Autowired
	private HtService htService;
	@Autowired
	private SqlDao sqlDao;
	
	public void init(HttpModel httpModel) {
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("house/hl/t_goods_list.jsp");
	}
	
	public void chuku(HttpModel httpModel) {
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("house/hl/t_OutboundMaterial_list.jsp");
	}
	
	public void initBase(HttpModel httpModel) {
		httpModel.setViewPath("house/hl/t_base_list.jsp");
	}
	
	public void initUserProject(HttpModel httpModel) {
		httpModel.setViewPath("house/hl/t_user_project_list.jsp");
	}
	
	public void initQgorder(HttpModel httpModel) {
		httpModel.setViewPath("house/hl/t_qgorder_list.jsp");
		}
	
	public void initQgorderTj(HttpModel httpModel) {
		httpModel.setViewPath("house/hl/t_qgtjorder_list.jsp");
	}
	
	/**
	 * 根据查询条件查询材料信息集合
	 * 
	 * @param request
	 * @param response
	 */
	public void getT_goodsPOList(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<T_goodsVO> list = t_goodsMapper.likePage(inDto);
		String outString = AOSJson.toGridJson(list, inDto.getPageTotal());
		httpModel.setOutMsg(outString);
	}
	
	/**
	 * 根据查询条件查询分类信息集合
	 * 
	 * @param request
	 * @param response
	 */
	public void getT_typePOList(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<TTypePO> list = t_typeMapper.likePage(inDto);
		String outString = AOSJson.toGridJson(list, inDto.getPageTotal());
		httpModel.setOutMsg(outString);
	}
	
	
	/**
	 * 根据查询条件查询项目信息集合
	 * 
	 * @param request
	 * @param response
	 */
	public void getT_projectPOList(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<TProjectPO> list = t_projectMapper.likePage(inDto);
		String outString = AOSJson.toGridJson(list, inDto.getPageTotal());
		httpModel.setOutMsg(outString);
	}
	
	/**
	 * 根据查询条件查询供应商信息集合
	 * 
	 * @param request
	 * @param response
	 */
	public void getT_supplierPOList(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<TSupplierPO> list = t_supplierMapper.likePage(inDto);
		String outString = AOSJson.toGridJson(list, inDto.getPageTotal());
		httpModel.setOutMsg(outString);
	}
	
	/**
	 * 根据查询条件查询用户项目关系集合
	 * 
	 * @param request
	 * @param response
	 */
	public void getT_user_projectVOList(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<TUserProjectPO> list = t_user_projectMapper.likePage(inDto);
		String outString = AOSJson.toGridJson(list, inDto.getPageTotal());
		httpModel.setOutMsg(outString);
	}
	
	/**
	 * 根据查询条件查询用户信息集合
	 * 
	 * @param request
	 * @param response
	 */
	public void getT_userhlPOList(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<TUserhlPO> list = t_userhlMapper.likePage(inDto);
		String outString = AOSJson.toGridJson(list, inDto.getPageTotal());
		httpModel.setOutMsg(outString);
	}
	
	/**
	 * 根据查询条件查询用户信息集合
	 * 
	 * @param request
	 * @param response
	 */
	public void getT_userhlPO(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		TUserhlPO list = t_userhlMapper.selectOne(inDto);
		String outString = AOSJson.toJson(list);
		httpModel.setOutMsg(outString);
	}
	
	/**
	 * 根据查询条件查询请购记录
	 * 
	 * @param request
	 * @param response
	 */
	public void getQgorderList(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<TOrderPO> list = t_orderMapper.qglikePage(inDto);
		String outString = AOSJson.toGridJson(list, inDto.getPageTotal());
		httpModel.setOutMsg(outString);
	}
	
	/**
	 * 根据查询条件查询请购统计
	 * 
	 * @param request
	 * @param response
	 */
	public void getQgorderTjList(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		List<T_orderqgtjVO> list = t_order_detailedMapper.qgtjlikePage(inDto);
		String outString = AOSJson.toGridJson(list, inDto.getPageTotal());
		httpModel.setOutMsg(outString);
	}
	
	public void getQgorderTjCount(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		T_orderqgtjVO vo = t_order_detailedMapper.qgtjcount(inDto);
		String outString = AOSJson.toJson(vo);
		httpModel.setOutMsg(outString);
	}
	
	public void qgorder_detailedByOrderId(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		String order_id = inDto.getString("order_id");
		List<T_order_detailedVO> list = new ArrayList<T_order_detailedVO>();
		if(AOSUtils.isNotEmpty(order_id)){
			list = t_order_detailedMapper.qgorder_detailedByOrderId(order_id);
			httpModel.setOutMsg(AOSJson.toJson(list));
		}
	}
	
	/**
	 * 保存材料信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public void saveT_goodsPO(HttpModel httpModel) throws IOException {
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.saveT_goodsPO(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 保存分类信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	public void saveT_typePO(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.saveT_typePO(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 保存项目信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	public void saveT_projectPO(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.saveT_projectPO(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 保存供应商信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	public void saveT_supplierPO(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.saveT_supplierPO(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 保存用户项目关系信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	public void saveT_user_projectPO(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.saveT_user_projectPO(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	public void saveT_userPO(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.saveT_userPO(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	public void saveGutboundPO(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		UserModel UserModel=httpModel.getUserModel();
		dto.put("account", UserModel.getAccount());
		dto.put("name", UserModel.getName());
		Dto outDto = htService.saveGutboundPO(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}

	/**
	 * 删除材料信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	public void deleteT_goodsPO(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.deleteT_goodsPO(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 删除分类信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	public void deleteT_typePO(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.deleteT_typePO(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 删除项目信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	public void deleteT_projectPO(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.deleteT_projectPO(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 删除供应商信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	public void deleteT_supplierPO(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.deleteT_supplierPO(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 删除用户项目信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	public void deleteT_user_projectPO(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.deleteT_user_projectPO(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	
	/**
	 * 删除用户信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	public void deleteUser_projectPO(HttpModel httpModel) {
		Dto dto = httpModel.getInDto();
		String selections = dto.getString("aos_rows_");
		Dto outDto = Dtos.newDto();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(selections);
		String s = scanner.next() ;
		String[] nums = s.split(",") ;
		for (int i = 0; i < nums.length; i++) {
			if(AOSUtils.isNotEmpty(nums[i])){
				t_userhlMapper.deleteByKey(nums[i]);
				List<TUserProjectPO> list=t_user_projectMapper.list(Dtos.newDto("user_id", nums[i]));
				if(AOSUtils.isNotEmpty(list)){
					for (int j = 0; j < list.size(); j++) {
						t_user_projectMapper.deleteByKey(list.get(j).getId());
				}
				}
				
		}
		}
		outDto.setAppMsg("删除成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	public void getT_user_projectVOById(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		String id = null != dto.get("id") ? dto.get("id").toString() : null;
		T_user_projectVO vo = t_user_projectMapper.selectVOByKey(id);
		httpModel.setOutMsg(AOSJson.toJson(vo));
	}
	
	public void getT_typePOById(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		String id = null != dto.get("typeZj") ? dto.get("typeZj").toString() : null;
		TTypePO po = t_typeMapper.selectByKey(id);
		httpModel.setOutMsg(AOSJson.toJson(po));
	}
	
	public void getT_projectPOById(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		String id = null != dto.get("projectZj") ? dto.get("projectZj").toString() : null;
		TProjectPO po = t_projectMapper.selectByKey(id);
		httpModel.setOutMsg(AOSJson.toJson(po));
	}
	
	public void getT_supplierPOById(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		String id = null != dto.get("supplierZj") ? dto.get("supplierZj").toString() : null;
		TSupplierPO po = t_supplierMapper.selectByKey(id);
		httpModel.setOutMsg(AOSJson.toJson(po));
	}
	public void getT_goodsVOById(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		String id = null != dto.get("id") ? dto.get("id").toString() : null;
		T_goodsVO vo = t_goodsMapper.selectVOByKey(id);
		httpModel.setOutMsg(AOSJson.toJson(vo));
	}
	
	public void getT_specificationsPOListByGoodsId(HttpModel httpModel) {
		Dto inDto = httpModel.getInDto();
		String goods_id = inDto.getString("goods_id");
		if(AOSUtils.isNotEmpty(goods_id)){
			Dto dto = Dtos.newDto();
			dto.put("goods_id", goods_id);
			List<TSpecificationsPO> list = t_specificationsMapper.list(dto);
			if(AOSUtils.isNotEmpty(list)){
				String outString = AOSJson.toJson(list);
				httpModel.setOutMsg(outString);
			}
		}
	}
	
	/**
	 * 保存材料信息
	 * 
	 * @param session
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public void importSaveT_goodsPO(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		Dto outDto = Dtos.newDto();
		TSpecificationsPO po=new TSpecificationsPO();
		AOSUtils.copyProperties(inDto, po);
		if(AOSUtils.isEmpty(po.getId())){
			po.setId(IdService.uuid());
			t_specificationsMapper.insert(po);
			outDto.setAppMsg("添加成功");
		}else {
			t_specificationsMapper.updateByKey(po);
			outDto.setAppMsg("修改成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	public void importdeleteT_goodsPO(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		t_specificationsMapper.deleteByKey(inDto.getString("id"));
		httpModel.setOutMsg(AOSJson.toJson("删除成功"));
	}
	
	public void saveChangeQgorderPrice(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.saveChangeQgorderPrice(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 确认订单
	 * @param request
	 * @param response
	 */
	public void saveChangeQgorderSure(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.saveChangeQgorderSure(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 删除请购记录
	 */
	public void saveQgorder(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		Dto outDto = htService.saveQgorder(dto);
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 导出请购统计
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws JRException
	 */
	public void fillReportOutbound(HttpModel httpModel) throws FileNotFoundException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		OutputStream out = httpModel.getResponse().getOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("table");  //创建table工作薄
		Dto inDto = httpModel.getInDto();
		List<Dto> list = sqlDao.list("com.api.hl.dao.TGutboundDao.OutboundExport", inDto);
		HSSFRow row1= sheet.createRow(1);   ////创建第二列 标题
		HSSFCell cell0 = row1.createCell((short)0);   //--->创建一个单元格  
		cell0.setCellValue("商品名称");
		HSSFCell cell1 = row1.createCell((short)1);   //--->创建一个单元格  
		cell1.setCellValue("分类");
		HSSFCell cell2 = row1.createCell((short)2);   //--->创建一个单元格  
		cell2.setCellValue("规格");
		HSSFCell cell3 = row1.createCell((short)3);   //--->创建一个单元格  
		cell3.setCellValue("出库账号");
		HSSFCell cell4 = row1.createCell((short)4);   //--->创建一个单元格  
		cell4.setCellValue("出库人名称 ");
		HSSFCell cell5 = row1.createCell((short)5);   //--->创建一个单元格  
		cell5.setCellValue("数量 ");
		HSSFCell cell6 = row1.createCell((short)6);   //--->创建一个单元格  
		cell6.setCellValue("价格");
		HSSFCell cell7 = row1.createCell((short)7);   //--->创建一个单元格  
		cell7.setCellValue("剩余库存数量 ");
	/*	HSSFCell cell8 = row1.createCell((short)8);   //--->创建一个单元格  
		cell8.setCellValue("供应商");*/
		HSSFCell cell8 = row1.createCell((short)8);   //--->创建一个单元格  
		cell8.setCellValue("出库时间");
		for(int i = 0;i < list.size();i ++) {
			Dto vo = list.get(i);
			HSSFRow row = sheet.createRow(i+2);//创建表格行
		    HSSFCell c0 = row.createCell(0);//根据表格行创建单元格
		    c0.setCellValue(null != vo.getString("material_name") ? vo.getString("material_name") : "");
	        HSSFCell c1 = row.createCell(1);
	        c1.setCellValue(null !=vo.getString("type_name") ? vo.getString("type_name") : "");
	        HSSFCell c2 = row.createCell(2);
	        c2.setCellValue(null != vo.getString("specifications_name") ? vo.getString("specifications_name") : "");
	        HSSFCell c3 = row.createCell(3);
	        c3.setCellValue(null != vo.getString("user_account") ? vo.getString("user_account") : "");
	        HSSFCell c4 = row.createCell(4);
	        c4.setCellValue(null != vo.getString("user_name") ? vo.getString("user_name") : "");
	        HSSFCell c5 = row.createCell(5);
	        c5.setCellValue(null != vo.getString("num") ? vo.getString("num") : "");
	        HSSFCell c6 = row.createCell(6);
	        c6.setCellValue(null != vo.getString("price") ?vo.getString("price") : "");
	        HSSFCell c7 = row.createCell(7);
	        c7.setCellValue(null != vo.getString("unmbers") ?vo.getString("unmbers") : "");
	       /* HSSFCell c8 = row.createCell(8);
	        c8.setCellValue(null != vo.getString("material_name") ? vo.getString("material_name") : "");*/
	        HSSFCell c8 = row.createCell(8);
	        Date adddate = vo.getDate("add_time");
	        if(AOSUtils.isNotEmpty(adddate)){
		        c8.setCellValue(String.valueOf(sdf.format(adddate)));
	        }
	        
		}
		FileSystemView fsv = FileSystemView.getFileSystemView();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		String newDateStr = sd.format(new Date());
		wb.write(new FileOutputStream(fsv.getHomeDirectory()+"\\"+"请购统计"+newDateStr+".xls"));

	}
	
	
	/**
	 * 导出材料出库统计
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws JRException
	 */
	public void fillReport(HttpModel httpModel) throws FileNotFoundException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		OutputStream out = httpModel.getResponse().getOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("table");  //创建table工作薄
		Dto inDto = httpModel.getInDto();
		List<T_orderqgtjVO> list = t_order_detailedMapper.qgtjlistVO(inDto);
		HSSFRow row1= sheet.createRow(1);   ////创建第二列 标题
		HSSFCell cell0 = row1.createCell((short)0);   //--->创建一个单元格  
		cell0.setCellValue("商品名称");
		HSSFCell cell1 = row1.createCell((short)1);   //--->创建一个单元格  
		cell1.setCellValue("分类");
		HSSFCell cell2 = row1.createCell((short)2);   //--->创建一个单元格  
		cell2.setCellValue("品牌");
		HSSFCell cell3 = row1.createCell((short)3);   //--->创建一个单元格  
		cell3.setCellValue("规格 ");
		HSSFCell cell4 = row1.createCell((short)4);   //--->创建一个单元格  
		cell4.setCellValue("价格 ");
		HSSFCell cell5 = row1.createCell((short)5);   //--->创建一个单元格  
		cell5.setCellValue("数量 ");
		HSSFCell cell6 = row1.createCell((short)6);   //--->创建一个单元格  
		cell6.setCellValue("请购人姓名 ");
		HSSFCell cell7 = row1.createCell((short)7);   //--->创建一个单元格  
		cell7.setCellValue("项目 ");
		HSSFCell cell8 = row1.createCell((short)8);   //--->创建一个单元格  
		cell8.setCellValue("供应商");
		HSSFCell cell9 = row1.createCell((short)9);   //--->创建一个单元格  
		cell9.setCellValue("下单时间");
		for(int i = 0;i < list.size();i ++) {
			T_orderqgtjVO vo = list.get(i);
			HSSFRow row = sheet.createRow(i+2);//创建表格行
		    HSSFCell c0 = row.createCell(0);//根据表格行创建单元格
		    c0.setCellValue(null != vo.getMaterial_name() ? vo.getMaterial_name() : "");
	        HSSFCell c1 = row.createCell(1);
	        c1.setCellValue(null != vo.getType_name() ? vo.getType_name() : "");
	        HSSFCell c2 = row.createCell(2);
	        c2.setCellValue(null != vo.getBrand() ? vo.getBrand() : "");
	        HSSFCell c3 = row.createCell(3);
	        c3.setCellValue(null != vo.getSpecifications_name() ? vo.getSpecifications_name() : "");
	        HSSFCell c4 = row.createCell(4);
	        c4.setCellValue(null != vo.getPrice() ? vo.getPrice() : "");
	        HSSFCell c5 = row.createCell(5);
	        c5.setCellValue(null != vo.getNumber() ? vo.getNumber() : "");
	        HSSFCell c6 = row.createCell(6);
	        c6.setCellValue(null != vo.getUsername() ? vo.getUsername() : "");
	        HSSFCell c7 = row.createCell(7);
	        c7.setCellValue(null != vo.getProject_name() ? vo.getProject_name() : "");
	        HSSFCell c8 = row.createCell(8);
	        c8.setCellValue(null != vo.getSuppliername() ? vo.getSuppliername() : "");
	        HSSFCell c9 = row.createCell(9);
	        Date adddate = vo.getAdd_date();
	        if(AOSUtils.isNotEmpty(adddate)){
		        c9.setCellValue(String.valueOf(sdf.format(adddate)));
	        }
	        
		}
		FileSystemView fsv = FileSystemView.getFileSystemView();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		String newDateStr = sd.format(new Date());
		wb.write(new FileOutputStream(fsv.getHomeDirectory()+"\\"+"请购统计"+newDateStr+".xls"));

	}
	
	/**
	 * 导出请购记录详情
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws JRException
	 */
	public void fillReportQgDetail(HttpModel httpModel) throws FileNotFoundException, IOException {
		Dto inDto = httpModel.getInDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		OutputStream out = httpModel.getResponse().getOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("table");  //创建table工作薄
	
		String order_id = inDto.getString("order_id");
		String project_name = inDto.getString("project_name");
		String xdsj = inDto.getString("xdsj");
		String zhanghao = inDto.getString("zhanghao");
		List<T_order_detailedVO> list = new ArrayList<T_order_detailedVO>();
		if(AOSUtils.isNotEmpty(order_id)){
			list = t_order_detailedMapper.qgorder_detailedByOrderId(order_id);
		}
		HSSFRow row2= sheet.createRow(1);
		HSSFCell cel0 = row2.createCell((short)0);   //--->创建一个单元格  
		cel0.setCellValue("项目:");
		HSSFCell cel1 = row2.createCell((short)1);   //--->创建一个单元格  
		cel1.setCellValue(null != project_name ? String.valueOf(project_name) : "");
		
		HSSFCell dcel = row2.createCell((short)3);   //--->创建一个单元格  
		dcel.setCellValue("下单时间:");
		HSSFCell dcel1 = row2.createCell((short)4);   //--->创建一个单元格  
		dcel1.setCellValue(null != xdsj ? String.valueOf(xdsj) : "");
		
		HSSFRow row3= sheet.createRow(2);
		HSSFCell zcel = row3.createCell((short)0);   //--->创建一个单元格  
		zcel.setCellValue("账号:");
		HSSFCell zcel1 = row3.createCell((short)1);   //--->创建一个单元格  
		zcel1.setCellValue(null != zhanghao ? String.valueOf(zhanghao) : "");
		
		HSSFRow row1= sheet.createRow(3);   ////创建第二列 标题
		HSSFCell cell0 = row1.createCell((short)0);   //--->创建一个单元格  
		cell0.setCellValue("商品名称");
		HSSFCell cell1 = row1.createCell((short)1);   //--->创建一个单元格  
		cell1.setCellValue("分类");
		HSSFCell cell2 = row1.createCell((short)2);   //--->创建一个单元格  
		cell2.setCellValue("品牌");
		HSSFCell cell3 = row1.createCell((short)3);   //--->创建一个单元格  
		cell3.setCellValue("规格 ");
		
		HSSFCell cell4 = row1.createCell((short)4);   //--->创建一个单元格  
		cell4.setCellValue("数量 ");
		HSSFCell cell5 = row1.createCell((short)5);   //--->创建一个单元格  
		cell5.setCellValue("商品备注");
		
		for(int i = 0;i < list.size();i ++) {
			T_order_detailedVO vo = list.get(i);
			HSSFRow row = sheet.createRow(i+4);//创建表格行
		    HSSFCell c0 = row.createCell(0);//根据表格行创建单元格
		    c0.setCellValue(null != vo.getMaterial_name() ? vo.getMaterial_name() : "");
	        HSSFCell c1 = row.createCell(1);
	        c1.setCellValue(null != vo.getType_name() ? vo.getType_name() : "");
	        HSSFCell c2 = row.createCell(2);
	        c2.setCellValue(null != vo.getBrand() ? vo.getBrand() : "");
	        HSSFCell c3 = row.createCell(3);
	        c3.setCellValue(null != vo.getSpecifications_name() ? vo.getSpecifications_name() : "");
	        HSSFCell c4 = row.createCell(4);
	        c4.setCellValue(null != vo.getNumber() ? vo.getNumber() : "");
	        HSSFCell c5 = row.createCell(5);
	        c5.setCellValue(null != vo.getNote() ? vo.getNote() : "");
		}
		FileSystemView fsv = FileSystemView.getFileSystemView();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		String newDateStr = sd.format(new Date());
		wb.write(new FileOutputStream(fsv.getHomeDirectory()+"\\"+"请购记录详情"+newDateStr+".xls"));
	}
	/**
	 * 导出请购统计
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws JRException
	 */
	public void fillReportGoods(HttpModel httpModel) throws FileNotFoundException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		OutputStream out = httpModel.getResponse().getOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("table");  //创建table工作薄
		Dto inDto = httpModel.getInDto();
		List<T_goodsVO> list = t_goodsMapper.likelist(inDto);
		HSSFRow row1= sheet.createRow(1);   ////创建第二列 标题
		HSSFCell cell0 = row1.createCell((short)0);   //--->创建一个单元格  
		cell0.setCellValue("商品名称");
		HSSFCell cell1 = row1.createCell((short)1);   //--->创建一个单元格  
		cell1.setCellValue("分类");
		HSSFCell cell2 = row1.createCell((short)2);   //--->创建一个单元格  
		cell2.setCellValue("单位");
		HSSFCell cell3 = row1.createCell((short)3);   //--->创建一个单元格  
		cell3.setCellValue("供应商 ");
		HSSFCell cell4 = row1.createCell((short)4);   //--->创建一个单元格  
		cell4.setCellValue("商品备注 ");
		HSSFCell cell5 = row1.createCell((short)5);   //--->创建一个单元格  
		cell5.setCellValue("创建时间");
		for(int i = 0;i < list.size();i ++) {
			T_goodsVO vo = list.get(i);
			HSSFRow row = sheet.createRow(i+2);//创建表格行
		    HSSFCell c0 = row.createCell(0);//根据表格行创建单元格
		    c0.setCellValue(null != vo.getMaterial_name() ? vo.getMaterial_name() : "");
	        HSSFCell c1 = row.createCell(1);
	        c1.setCellValue(null != vo.getType_name() ? vo.getType_name() : "");
	        HSSFCell c2 = row.createCell(2);
	        c2.setCellValue(null != vo.getUnit() ? vo.getUnit() : "");
	        HSSFCell c3 = row.createCell(3);
	        c3.setCellValue(null != vo.getSupplier_name() ? vo.getSupplier_name() : "");
	        HSSFCell c4 = row.createCell(4);
	        c4.setCellValue(null != vo.getNote() ? vo.getNote() : "");
	        
	        HSSFCell c5 = row.createCell(5);
	        Date createTime = vo.getCreateTime();
	        if(AOSUtils.isNotEmpty(createTime)){
		        c5.setCellValue(String.valueOf(sdf.format(createTime)));
	        }
		}
		FileSystemView fsv = FileSystemView.getFileSystemView();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		String newDateStr = sd.format(new Date());
		wb.write(new FileOutputStream(fsv.getHomeDirectory()+"\\"+"材料信息"+newDateStr+".xls"));
	}
	
	//出库材料
	public void OutboundMaterial(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		List<Dto> goodslist = sqlDao.list("com.api.hl.dao.TGutboundDao.pgPage", dto);
		String outString = AOSJson.toGridJson(goodslist, dto.getPageTotal());
		httpModel.setOutMsg(outString);
	}
	
	//出库材料
	public void Outboundtj(HttpModel httpModel){
		Dto dto = httpModel.getInDto();
		Dto goodslist = (Dto) sqlDao.selectOne("com.api.hl.dao.TOrderDetailedDao.qgtjOutboundcount", dto);
		String outString = AOSJson.toJson(goodslist);
		httpModel.setOutMsg(outString);
	}
}
