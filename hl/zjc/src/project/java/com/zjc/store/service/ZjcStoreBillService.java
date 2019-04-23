/**
 * 
 */
package com.zjc.store.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;

import com.api.message.SysMessageService;
import com.taobao.api.ApiException;
import com.zjc.common.po.ExportExcelInfo;
import com.zjc.common.util.ExportExcle;
import com.zjc.order.dao.po.ZjcOrderPO;
import com.zjc.store.dao.ZjcStoreDao;
import com.zjc.store.dao.ZjcTransferDao;
import com.zjc.store.dao.po.ZjcBillOrder;
import com.zjc.store.dao.po.ZjcBillRecord;
import com.zjc.store.dao.po.ZjcBillVO;
import com.zjc.store.dao.po.ZjcTransferPO;

/**
 * @author Administrator
 *
 */
@Service("zjcStoreBillService")
public class ZjcStoreBillService {
	//private static Logger logger = LoggerFactory.getLogger(ZjcStoreService.class);
	
	@Autowired
	private IdService idService;
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcTransferDao zjcTransferDao;
	@Autowired
	private ZjcStoreDao zjcStoreDao;
	@Autowired
	private SysMessageService sysMessageService;
	/**
	 * 初始化委托收款列表页面
	 * 
	 * @param httpModel
	 */
	public void initStoreBillPage(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/store/storeBillList.jsp");
	}
	
	/**
	 * 初始化商户管理店铺信息页面
	 * 
	 * @param httpModel
	 */
	public void initStoreBillAduit(HttpModel httpModel){
		httpModel.setAttribute("vercode_uuid", idService.uuid());
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/store/storeBillAduit.jsp");
	}
	
	public void listZjcStoreBill(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		List<Dto> billList = sqlDao.list("com.zjc.store.dao.ZjcStoreDao.billListPage", inDto);
		for(Dto dto :billList){
			Dto temp = sqlDao.selectDto("com.zjc.store.dao.ZjcStoreDao.getJe", Dtos.newDto("store_id", dto.getString("store_id")));
			dto.put("sum_cash", (int)(temp.getInteger("sum_cash") * 0.94));
		}
		httpModel.setOutMsg(AOSJson.toGridJson(billList, inDto.getPageTotal()));
	}
	
	public void listZjcStoreBillAduit(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		List<Dto> billList = sqlDao.list("com.zjc.store.dao.ZjcStoreDao.billAduitListPage", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(billList, inDto.getPageTotal()));
	}
	
	public void query_transfer_record(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		List<Dto> billList = sqlDao.list("com.zjc.store.dao.ZjcTransferDao.query_transfer_record", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(billList, inDto.getPageTotal()));
	}
	
	/**
	 * 操作申请列表
	 * @param httpModel
	 */
	public void query_order_record(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		List<Dto> billList = sqlDao.list("com.zjc.store.dao.ZjcStoreDao.query_order_record", inDto);
		httpModel.setOutMsg(AOSJson.toGridJson(billList, inDto.getPageTotal()));
	}
	
	
	/**
	 * 申请转账操作
	 * @param httpModel
	 */
	@Transactional(rollbackFor=Exception.class)
	public void apply_for_transfer(HttpModel httpModel){
		Dto outDto =  Dtos.newDto();
		try {
			Dto inDto = httpModel.getInDto();
			String[] ids = inDto.getString("ids").split(",");
			inDto.put("ids", ids);
			String[] cashs = inDto.getString("cashs").split(",");
			inDto.put("cashs", cashs);
			//sqlDao.update("com.zjc.store.dao.ZjcStoreDao.apply_for_transfer", inDto);
			sqlDao.update("com.zjc.store.dao.ZjcStoreDao.via_for_transfer", inDto);
			inDto.put("approval_id", httpModel.getUserModel().getId());
			//修改审批人
			sqlDao.update("com.zjc.store.dao.ZjcTransferDao.via_for_transfer", inDto);
			for (int i = 0; i < ids.length; i++) {
				ZjcTransferPO add = new ZjcTransferPO();
				add.setCash(BigDecimal.valueOf(Double.valueOf(cashs[i])));
				add.setOperator_id(BigInteger.valueOf(Long.valueOf(httpModel.getUserModel().getId())));
				add.setOrder_id(Integer.valueOf(ids[i]));
				add.setAdd_time(new Date());
				sqlDao.insert("com.zjc.store.dao.ZjcTransferDao.insert1", add);
			}
			outDto.setAppMsg("操作成功！");
		} catch (Exception e) {
			outDto.setAppMsg("操作失败！");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void via_for_transfer(HttpModel httpModel){
		Dto outDto =  Dtos.newDto();
		try {
			Dto inDto = httpModel.getInDto();		
			String[] ids = inDto.getString("ids").split(",");
			inDto.put("ids", ids);
			inDto.put("approval_id", httpModel.getUserModel().getId());
			sqlDao.update("com.zjc.store.dao.ZjcStoreDao.via_for_transfer", inDto);
			sqlDao.update("com.zjc.store.dao.ZjcTransferDao.via_for_transfer", inDto);
			outDto.setAppMsg("操作成功！");
		} catch (Exception e) {
			outDto.setAppMsg("操作失败！");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	
	public void confirm_transfer(HttpModel httpModel) throws ApiException{
		Dto outDto =  Dtos.newDto();
		Dto inDto = httpModel.getInDto();		
		List<ZjcOrderPO> list = sqlDao.list("com.zjc.store.dao.ZjcStoreDao.selectOrders", inDto);
		inDto.put("orders", list);
		int i = sqlDao.update("com.zjc.store.dao.ZjcStoreDao.confirm_transfer", inDto);
		int j = sqlDao.update("com.zjc.store.dao.ZjcTransferDao.confirm_transfer", inDto);	
		if(i >= 1 && j>= 1){
			outDto.setAppMsg("操作成功！");
			//发送短信
			if(inDto.getInteger("send_msg") == 1){
				inDto.put("store_name", list.get(0).getStore_name());
				inDto.put("sms_type", "notice");
				inDto.put("number", inDto.getString("sj_cash"));
				sysMessageService.transferMsg(httpModel);
			}
		}else {
			outDto.setAppMsg("操作失败！");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 导出汇总订单
	 */
	public void exportBill(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		HttpServletRequest req = httpModel.getRequest();
		HttpServletResponse resp = httpModel.getResponse();
        String title = "订单信息汇总";
        String[] strHeader =
            {"商家店铺名称","商家店铺ID","冻结金额（元）", "待转账金额（元）", "已转账金额（元）",  "合计金额（元）", "转账金额"};
        String[] strField =
            {"store_name", "store_id","frozen_money","pending_transfer", "transferred", "sum_money", "cost"};
        //根据条件查询待导出订单数据
        List<ZjcBillVO> list = zjcStoreDao.exceportBillList(inDto);
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
        	httpModel.setOutMsg("订单信息导出成功");
        } else {
        	httpModel.setOutMsg("订单信息导出失败");
        }
	}
	
	/**
	 * 导出申请订单
	 */
	public void exportBillOrder(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		HttpServletRequest req = httpModel.getRequest();
		HttpServletResponse resp = httpModel.getResponse();
        String title = "申请订单信息";
        String[] strHeader =
            {"订单号","订单完成时间","支付方式", "货币金额（元）"};
        String[] strField =
            {"order_sn", "confirm_time","pay_name","cash"};
        //根据条件查询待导出订单数据
        List<ZjcBillOrder> list = zjcStoreDao.exportBillOrder(inDto);
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
        	httpModel.setOutMsg("订单信息导出成功");
        } else {
        	httpModel.setOutMsg("订单信息导出失败");
        }
	}
	
	/**
	 * 导出转账记录
	 */
	public void exportBillRecord(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		HttpServletRequest req = httpModel.getRequest();
		HttpServletResponse resp = httpModel.getResponse();
        String title = "转账记录";
        String[] strHeader =
            {"店铺名称","订单下单时间","订单完成时间", "转账完成时间", "转账金额", "操作员", "审批员"};
        String[] strField =
            {"store_name", "add_time","confirm_time","transit_time", "cash", "operator_id", "approval_id"};
        //根据条件查询待导出订单数据
        List<ZjcBillRecord> list = zjcTransferDao.query_transfer_record(inDto);
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
        	httpModel.setOutMsg("订单信息导出成功");
        } else {
        	httpModel.setOutMsg("订单信息导出失败");
        }
	}
	public void getDepute(HttpModel httpModel){
		Dto inDto = httpModel.getInDto();
		Dto outDto = (Dto) sqlDao.selectOne("com.zjc.store.dao.ZjcStoreDeputeDao.selectByStoreId", inDto);
		Dto temp = sqlDao.selectDto("com.zjc.store.dao.ZjcStoreDao.getJe", inDto);
		outDto.putAll(temp);
		outDto.put("sj_cash", (int)(temp.getInteger("sum_cash") * 0.94));
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
}

