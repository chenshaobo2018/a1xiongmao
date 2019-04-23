/**
 * 
 */
package com.wxactivity.share.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSUtils;

import com.api.ApiPublic.ApiLogService;
import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.wxactivity.share.dao.ShareGameDao;
import com.wxactivity.share.dao.ZjcSharePrizeDao;
import com.wxactivity.share.dao.po.ShareGamePO;
import com.wxactivity.share.dao.po.ZjcSharePrizePO;
import com.zjc.prize.dao.ZjcWinPrizeDao;
import com.zjc.prize.dao.po.ZjcWinPrizePO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;

/**
 * @author Administrator
 *
 */
@Service(value="sharePrizeService")
public class SharePrizeService {

	@Autowired
	private ZjcSharePrizeDao zjcSharePrizeDao;
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	@Autowired
	private ZjcWinPrizeDao zjcWinPrizeDao;
	@Autowired
	private ApiLogService apiLogService;
	@Autowired
	private SqlDao SqlDao;
	@Autowired
	private ShareGameDao shareGameDao;
	
	/**
	 * 获取活动奖品列表
	 * 
	 * @return
	 */
	public List<ZjcSharePrizePO> getZjcSharePrizeList(){
		Dto dto = Dtos.newDto();
		List<ZjcSharePrizePO> prizeList = zjcSharePrizeDao.list(dto);
		return prizeList;
	}
	
	/**
	 * 抽奖之前查询是否还有游戏礼包
	 */
	public void selectShareGame(){
		int rows = shareGameDao.rows(Dtos.newDto("type", "0"));
		if(rows == 0){
			//将游戏奖品的抽奖权重改为0
			ZjcSharePrizePO zjcSharePrizePO = zjcSharePrizeDao.selectByKey(5);
			zjcSharePrizePO.setShare_prize_weight(0);
			zjcSharePrizeDao.updateByKey(zjcSharePrizePO);
		}
	}
	
	/**
	 * 扣除易物券
	 */
	/**
	 * @param openid openid
	 * @param user_id ID号
	 * @param vouchers 消耗券数
	 */
	public MessageVO consumeVoucher(String openid,String user_id,Integer vouchers){
		MessageVO msgVO = new MessageVO();
		//根据商品ID查询商品详情信息
		if(AOSUtils.isEmpty(openid) || AOSUtils.isEmpty(user_id) || AOSUtils.isEmpty(vouchers) ){
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
	 * 中奖记录表
	 * @param winprize
	 */
	public void saveWinSharePrize(Map<String, Object> winMap){
		ZjcWinPrizePO zjcWinPrizePO = new ZjcWinPrizePO();
		AOSUtils.copyProperties(winMap,zjcWinPrizePO);
		zjcWinPrizePO.setWin_time(new Date());
		zjcWinPrizeDao.insert(zjcWinPrizePO);
		
		if(zjcWinPrizePO.getPrize_name().contains("券")){//奖品是赠送易物券
			String prizeNum = zjcWinPrizePO.getPrize_name().substring(0, zjcWinPrizePO.getPrize_name().lastIndexOf("券"));
			if(AOSUtils.isNotEmpty(prizeNum)){//如果没有中易物券
				ZjcUsersAccountInfoPO account = zjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id", winMap.get("user_id")));
				if(AOSUtils.isNotEmpty(account)){//如果账户信息不为空
					account.setPay_points(account.getPay_points()+Integer.parseInt(prizeNum));//增加可转券
					zjcUsersAccountInfoDao.updateByKey(account);
					
					Map<String,Object> zsmap = new HashMap<String,Object>();
					zsmap.put("logType", "商城赠送");
					zsmap.put("type", "抽奖赠送");
					zsmap.put("user_id", winMap.get("user_id"));
					zsmap.put("due_tc_points", prizeNum);
					zsmap.put("now_pay_points", account.getPay_points());
					zsmap.put("show_type", 1);//展示到APP上
					apiLogService.saveLog(zsmap);
				}
			}
		}
		if(zjcWinPrizePO.getPrize_name().contains("游戏礼包")){
			List<ShareGamePO> ShareGameList = SqlDao.list("com.wxactivity.share.dao.ShareGameDao.selectByMaxGameId", null);
			ShareGamePO shareGamePO = ShareGameList.get(0);
			String prize_name = zjcWinPrizePO.getPrize_name();
			zjcWinPrizePO.setPrize_name(prize_name + ":" + shareGamePO.getGame_account());
			zjcWinPrizeDao.updateByKey(zjcWinPrizePO);
			shareGamePO.setType("1");//表示已经使用
			shareGameDao.updateByKey(shareGamePO);
		}
		if(zjcWinPrizePO.getPrize_name().contains("再来一次")){
			String prizeNum = "68";
			if(AOSUtils.isNotEmpty(prizeNum)){//如果没有中易物券
				ZjcUsersAccountInfoPO account = zjcUsersAccountInfoDao.selectOne(Dtos.newDto("user_id", winMap.get("user_id")));
				if(AOSUtils.isNotEmpty(account)){//如果账户信息不为空
					account.setPay_points(account.getPay_points()+Integer.parseInt(prizeNum));//增加可转券
					zjcUsersAccountInfoDao.updateByKey(account);
					
					Map<String,Object> zsmap = new HashMap<String,Object>();
					zsmap.put("logType", "商城赠送");
					zsmap.put("type", "抽奖赠送");
					zsmap.put("user_id", winMap.get("user_id"));
					zsmap.put("due_tc_points", prizeNum);
					zsmap.put("now_pay_points", account.getPay_points());
					zsmap.put("show_type", 1);//展示到APP上
					apiLogService.saveLog(zsmap);
				}
			}
		}
	}
}
