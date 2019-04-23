/**
 * 
 */
package com.api.activity.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.wxactivity.share.dao.WxShareActivityDao;
import com.wxactivity.share.dao.po.WxShareActivityPO;
import com.wxactivity.share.dao.po.ZjcSharePrizePO;
import com.wxactivity.share.service.SharePrizeService;
import com.wxactivity.share.util.SharePrizeUtil;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.prize.dao.po.ZjcWinPrizePO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.id.IdService;
import aos.system.common.utils.SystemCons;

/**
 * @author Administrator
 *
 */
@Service(value="AppActivityShareService")
public class AppActivityShareService {
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	@Autowired
	private WxShareActivityDao WxShareActivityDao;
	@Autowired
	private IdService idService;
	@Autowired
	private SharePrizeService sharePrizeService;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private ApiLogService apiLogService;
	
	/**
	 * 查询用户商品分享数据
	 * @param httpModel
	 * @return
	 */
	public MessageVO shareGoods(HttpModel httpModel) {
		MessageVO MessageVO = new MessageVO();
		Dto qDto = httpModel.getInDto();
		if(AOSUtils.isEmpty(qDto.getString("user_id"))){
			MessageVO.setCode(Apiconstant.parameter_is_not_null.getIndex());
			MessageVO.setMsg(Apiconstant.parameter_is_not_null.getName());
			return MessageVO;
		}
		List<WxShareActivityPO> sharelist = sqlDao.list("com.wxactivity.share.dao.WxShareActivityDao.appSharelist", qDto);
		WxShareActivityPO shareintegral = (WxShareActivityPO) sqlDao.selectOne("com.wxactivity.share.dao.WxShareActivityDao.appShareintegral", qDto);
		if(AOSUtils.isNotEmpty(shareintegral) && AOSUtils.isNotEmpty(sharelist)){
			//设置分享商品数量
			shareintegral.setMarket_price(String.valueOf(sharelist.size()));
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
			MessageVO.setData(shareintegral);
		}else {
			MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
			MessageVO.setMsg(Apiconstant.NO_DATA.getName());
			MessageVO.setData(new WxShareActivityPO());
		}
		return MessageVO;
	}
	
	/**
	 * 商品分享进度
	 * @param httpModel
	 * @return
	 */
	public String appGoodsShare(HttpModel httpModel) {
		MessageVO MessageVO=new MessageVO();
		Dto qDto=httpModel.getInDto();
			//设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			//设置start开始条数
			int page = qDto.getInteger("page");
			qDto.put("start", (page-1)*ConstantUtil.pageSize);
			if(AOSUtils.isEmpty(qDto.getString("user_id"))){
				MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
				return AOSJson.toJson(MessageVO);
			}
			ZjcUsersInfoPO user = zjcUsersInfoDao.selectByKey(qDto.getBigInteger("user_id"));
			if(AOSUtils.isNotEmpty(user)){
				qDto.put("openid", user.getOpenid());
			}
			List<WxShareActivityPO> shareGoods = sqlDao.list("com.wxactivity.share.dao.WxShareActivityDao.appshareGoodsPage", qDto);
			PageVO pageVO=new PageVO();
			if(shareGoods.size()==0){
				if("undefined".equals(qDto.getString("goods_id"))){
					qDto.put("goods_id", null);
				}
				ZjcGoodsPO zjcGoodsPO = zjcGoodsDao.selectByKey(qDto.getInteger("goods_id"));
				if(AOSUtils.isEmpty(zjcGoodsPO)){
					MessageVO.setCode(Apiconstant.Goods_Is_Null.getIndex());
					MessageVO.setMsg(Apiconstant.Goods_Is_Null.getName());
					return AOSJson.toJson(MessageVO);
				}
				List<WxShareActivityPO> funList = new ArrayList<WxShareActivityPO>();
				WxShareActivityPO activityPO  = new WxShareActivityPO();
				activityPO.setGoods_id(zjcGoodsPO.getGoods_id().toString());
				activityPO.setMarket_price(zjcGoodsPO.getMarket_price().toString());
				activityPO.setShare_integral("0");
				funList.add(activityPO);
				pageVO.setList(funList);
				pageVO.setNowPage(page);
				MessageVO.setData(pageVO);
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}else{
				//生成返回分页参数实体
				pageVO.setList(shareGoods);
				pageVO.setNowPage(page);
				MessageVO.setData(pageVO);
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}
			return AOSJson.toJson(MessageVO);
	}
	
	/**
	 *  查询商品助力好友
	 * @param httpModel
	 * @return
	 */
	public String appShareFriends(HttpModel httpModel) {
		//获取查询参数
		Dto qDto=httpModel.getInDto();
		MessageVO MessageVO = new MessageVO();
			
			if(AOSUtils.isEmpty(qDto.getString("user_id"))){
				MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
				return AOSJson.toJson(MessageVO);
			}
			ZjcUsersInfoPO user = zjcUsersInfoDao.selectByKey(qDto.getBigInteger("user_id"));
			if(AOSUtils.isNotEmpty(user)){
				qDto.put("user_id", null);
				qDto.put("openid", user.getOpenid());
			}
			List<WxShareActivityPO> shareFriends = sqlDao.list("com.wxactivity.share.dao.WxShareActivityDao.list2", qDto);
			MessageVO.setData(shareFriends);
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
			return AOSJson.toJson(MessageVO);
	  }
	
	/**
	   * 添加分享助力信息
	   * @param httpModel
	   * @return
	   */
	  public String addShareActivity(HttpModel httpModel) {
			//获取查询参数
			Dto qDto=httpModel.getInDto();
			MessageVO MessageVO = new MessageVO();
			if(AOSUtils.isEmpty(qDto.getString("user_id"))){
				MessageVO.setCode(Apiconstant.open_id_Is_Null.getIndex());
				MessageVO.setMsg(Apiconstant.open_id_Is_Null.getName());
				return AOSJson.toJson(MessageVO);
			}
			if(AOSUtils.isEmpty(qDto.getString("goods_id"))){
				MessageVO.setCode(Apiconstant.goods_id_not_null.getIndex());
				MessageVO.setMsg(Apiconstant.goods_id_not_null.getName());
				return AOSJson.toJson(MessageVO);
			}
			Map<String,Object> qMap = new HashMap<String,Object>();
			qMap.put("user_id", qDto.getString("user_id"));
			qMap.put("goods_id", qDto.getString("goods_id"));
			qMap.put("share_integral", "0");
			WxShareActivityPO selectPo = WxShareActivityDao.selectOne(Dtos.newDto(qMap));
			if(AOSUtils.isEmpty(selectPo)){
				WxShareActivityPO WxShareActivityPO=new WxShareActivityPO();
				WxShareActivityPO.copyProperties(qDto);
				WxShareActivityPO.setAddtime(new Date());
				WxShareActivityPO.setShare_integral("0");
				WxShareActivityPO.setShare_id(idService.nextValue(SystemCons.SEQ.SEQ_USER).toString());
				int row=WxShareActivityDao.insert(WxShareActivityPO);
				if(row==1){
					MessageVO.setCode(Apiconstant.Do_Success.getIndex());
					MessageVO.setMsg(Apiconstant.Do_Success.getName());
				}else {
					MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
					MessageVO.setMsg(Apiconstant.Do_Fails.getName());
				}
			}else{
				MessageVO.setCode(Apiconstant.Do_Fails.getIndex());
				MessageVO.setMsg("该商品已经分享过");
			}
			return AOSJson.toJson(MessageVO);
	  }
	  
	  
	  /**
		 *  查询分享人
		 * @param httpModel
		 * @return
		 */
		public String shareUser(HttpModel httpModel) {
			MessageVO MessageVO = new MessageVO();
			Dto qDto = httpModel.getInDto();
			if(AOSUtils.isEmpty(qDto.getString("user_id"))){
				MessageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				MessageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
				return AOSJson.toJson(MessageVO);
			}
			// 设置limit每页条数
			qDto.put("limit", ConstantUtil.pageSize);
			// 设置start开始条数
			if(AOSUtils.isEmpty(qDto.getInteger("page")) || qDto.getInteger("page")<1){//当前页数不能为空
				MessageVO.setCode(Apiconstant.Page_Is_Null.getIndex());
				MessageVO.setMsg(Apiconstant.Page_Is_Null.getName());
			} 
			int page = qDto.getInteger("page");
			qDto.put("start", (page - 1) * ConstantUtil.pageSize);
			List<WxShareActivityPO> shareFriends =sqlDao.list("com.wxactivity.share.dao.WxShareActivityDao.shareUserPage", qDto);
			int sum=0;
			for (int i = 0; i < shareFriends.size(); i++) {
				sum+=Integer.parseInt(shareFriends.get(i).getShare_integral());
			}
				PageVO pageVO = new PageVO();
				if (shareFriends.size() == 0) {
					MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
					MessageVO.setMsg(Apiconstant.NO_DATA.getName());
				} else {
					// 生成返回分页参数实体
					pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), 1);
					pageVO.setList(shareFriends);
					pageVO.setNowPage(page);
					pageVO.setSum(sum);
					MessageVO.setCode(Apiconstant.Do_Success.getIndex());
					MessageVO.setMsg(Apiconstant.Do_Success.getName());
					MessageVO.setData(pageVO);
				}
				return AOSJson.toJson(MessageVO);
		  }
		
		
		/**
		 * 抽奖方法
		 * @param points 抽一次扣除易物券数量
		 * @param request
		 * @param response
		 */
		public MessageVO getWinSharePrize(HttpServletRequest request, HttpServletResponse response){
			MessageVO messageVO =new MessageVO();
			if(AOSUtils.isEmpty(request.getParameter("user_id"))){
				messageVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				messageVO.setMsg(Apiconstant.Srore_Param_Error.getName());
				return messageVO;
			}
			String user_id = request.getParameter("user_id").toString();
			//抽奖次数改为易物券抽奖
			MessageVO msgVo = consumeVoucher(user_id, 68);
			ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoDao.selectOne(Dtos.newDto("user_id", user_id));
			if(msgVo.getCode() == 1){
				//抽奖前必须执行这个
				sharePrizeService.selectShareGame();
				List<ZjcSharePrizePO> prizeList = sharePrizeService.getZjcSharePrizeList();
				if(prizeList != null && prizeList.size() > 0){
					//本次抽奖结果
					ZjcSharePrizePO prize = SharePrizeUtil.getPrizeIndex(prizeList);
					
					Map<String,Object> winMap = new HashMap<String, Object>();
					winMap.put("user_id",zjcUsersInfoPO.getUser_id());
					winMap.put("phone_num", zjcUsersInfoPO.getMobile());
					winMap.put("prize_id", prize.getShare_prize_id());
					winMap.put("prize_name", prize.getShare_prize_name());
					//winMap.put("pay_points",ZjcUsersAccountInfoPO.getPay_points());
					sharePrizeService.saveWinSharePrize(winMap);
					ZjcUsersAccountInfoPO ZjcUsersAccountInfoPO = zjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id", user_id));
					prize.setPay_points(ZjcUsersAccountInfoPO.getPay_points());
					messageVO.setData(prize);
					messageVO.setCode(Apiconstant.lottery_Number_SUCCESS.getIndex());
					messageVO.setMsg(Apiconstant.lottery_Number_SUCCESS.getName());
				}
			}else{
				messageVO.setCode(Apiconstant.Pay_points_not_enough.getIndex());
				messageVO.setMsg(Apiconstant.Pay_points_not_enough.getName());
				return messageVO;
			}
			return messageVO;
		}
		
		/**
		 * 扣除易物券
		 */
		/**
		 * @param user_id ID号
		 * @param vouchers 消耗券数
		 */
		public MessageVO consumeVoucher(String user_id,Integer vouchers){
			MessageVO msgVO = new MessageVO();
			//根据商品ID查询商品详情信息
			if( AOSUtils.isEmpty(user_id) || AOSUtils.isEmpty(vouchers) ){
				msgVO.setCode(Apiconstant.Srore_Param_Error.getIndex());
				msgVO.setMsg(Apiconstant.Srore_Param_Error.getName());
				return msgVO;
			}
			ZjcUsersAccountInfoPO zjcUsersAccountInfoPO = zjcUsersAccountInfoDao.selectByKey(new BigInteger(user_id));
			if(AOSUtils.isNotEmpty(zjcUsersAccountInfoPO)){
				if(zjcUsersAccountInfoPO.getPay_points() > vouchers){
					Integer newPoints = zjcUsersAccountInfoPO.getPay_points() - vouchers;
					zjcUsersAccountInfoPO.setPay_points(newPoints);
					int row = zjcUsersAccountInfoDao.updateByKey(zjcUsersAccountInfoPO);
					if(row == 1){
						//抽奖消费易物券
						Map<String,Object> zsmap = new HashMap<String,Object>();
						zsmap.put("logType", "活动消费");
						zsmap.put("user_id", user_id);
						zsmap.put("used_points", vouchers);
						zsmap.put("now_pay_points", zjcUsersAccountInfoPO.getPay_points());
						zsmap.put("show_type", 1);//展示到APP上
						apiLogService.saveLog(zsmap);
						msgVO.setCode(Apiconstant.Do_Success.getIndex());
						msgVO.setMsg(Apiconstant.Do_Success.getName());
					}else{
						msgVO.setCode(Apiconstant.deduction_points_fail.getIndex());
						msgVO.setMsg(Apiconstant.deduction_points_fail.getName());
					}
				}else{
					msgVO.setCode(Apiconstant.Pay_points_not_enough.getIndex());
					msgVO.setMsg(Apiconstant.Pay_points_not_enough.getName());
				}
			}else{
				msgVO.setCode(Apiconstant.NO_DATA.getIndex());
				msgVO.setMsg(Apiconstant.NO_DATA.getName());
			}
			return msgVO;
		}
		
		
		/**
		 * 获取奖品
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
		public MessageVO getWinPrizeList(HttpServletRequest request, HttpServletResponse response){
			   MessageVO messageVO =new MessageVO();
			   HttpModel httpModel = new HttpModel(request,response);
			   Dto qDto = httpModel.getInDto();
				//设置limit每页条数
				qDto.put("limit", ConstantUtil.pageSize);
				//设置start开始条数
				String a=request.getParameter("page");
				int page=Integer.parseInt((String)a);
				qDto.put("start", (page-1)*ConstantUtil.pageSize);
				List<ZjcWinPrizePO> List = sqlDao.list("com.zjc.prize.dao.ZjcWinPrizeDao.listPage", qDto);
				PageVO pageVO=new PageVO();
				if(List.size()==0){
					messageVO.setCode(Apiconstant.NO_DATA.getIndex());
					messageVO.setMsg(Apiconstant.NO_DATA.getName());
					return messageVO;
				}else{
					List<ZjcWinPrizePO> winPrizeList=new ArrayList<ZjcWinPrizePO>();
					ZjcWinPrizePO ZjcWinPrizePO=new ZjcWinPrizePO();
					for (int i = 0; i < List.size(); i++) {
						String phone=List.get(i).getPhone_num();
						ZjcWinPrizePO=List.get(i);
						StringBuffer buffer = new StringBuffer(phone);
						buffer.replace(4, 7, "****");
						ZjcWinPrizePO.setPhone_num(buffer.toString());
						winPrizeList.add(ZjcWinPrizePO);
					}
					pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), 1);
					pageVO.setList(winPrizeList);
					pageVO.setNowPage(page);
					messageVO.setCode(Apiconstant.Do_Success.getIndex());
					messageVO.setMsg(Apiconstant.Do_Success.getName());
					messageVO.setData(pageVO);
				}
				return messageVO;
		}
	  
}
