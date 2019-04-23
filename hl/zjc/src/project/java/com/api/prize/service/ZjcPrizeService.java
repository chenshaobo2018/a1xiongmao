/**
 * 
 */
package com.api.prize.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSUtils;
import aos.system.common.id.IdService;

import com.api.ApiPublic.ApiLogService;
import com.zjc.prize.dao.ZjcLotteryDrawDao;
import com.zjc.prize.dao.ZjcPrizeDao;
import com.zjc.prize.dao.ZjcWinPrizeDao;
import com.zjc.prize.dao.po.ZjcLotteryDrawPO;
import com.zjc.prize.dao.po.ZjcPrizePO;
import com.zjc.prize.dao.po.ZjcWinPrizePO;
import com.zjc.users.dao.ZjcUsersAccountInfoDao;
import com.zjc.users.dao.po.ZjcUsersAccountInfoPO;

/**
 * 抽奖service
 * 
 * @author Administrator
 *
 */
@Service(value="zjcPrizeService")
public class ZjcPrizeService {
	
	@Autowired
	private ZjcPrizeDao zjcPrizeDao;
	
	@Autowired
	private ZjcLotteryDrawDao zjcLotteryDrawDao;
	
	@Autowired
	private ZjcWinPrizeDao zjcWinPrizeDao;
	
	@Autowired
	private ZjcUsersAccountInfoDao zjcUsersAccountInfoDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ApiLogService apiLogService;
	
	/**
	 * 获取剩余抽奖次数
	 * @param inDto
	 */
	public Integer getLotteryNumberByOpenID(String user_id){
		ZjcLotteryDrawPO zjcLotteryDrawPO = new ZjcLotteryDrawPO();
		if(user_id!= null){
			zjcLotteryDrawPO = zjcLotteryDrawDao.selectOne(Dtos.newDto("user_id", user_id));
		}
		return zjcLotteryDrawPO != null ? zjcLotteryDrawPO.getLottery_number() : 0 ;
	}
	
	
	/**
	 * 保存获奖信息
	 * 
	 * @param winMap
	 */
	public void saveWinPrize(Map<String,Object> winMap){
		ZjcWinPrizePO zjcWinPrizePO = new ZjcWinPrizePO();
		AOSUtils.copyProperties(winMap,zjcWinPrizePO);
		zjcWinPrizePO.setWin_time(new Date());
		zjcWinPrizeDao.insert(zjcWinPrizePO);
		
		if(zjcWinPrizePO.getPrize_name().contains("易物券")){//奖品是赠送易物券
			String prizeNum = zjcWinPrizePO.getPrize_name().substring(0, zjcWinPrizePO.getPrize_name().lastIndexOf("易"));
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
		ZjcLotteryDrawPO zjcLotteryDrawPO = zjcLotteryDrawDao.selectOne(Dtos.newDto("user_id", winMap.get("user_id")));
		//减少抽奖次数
		zjcLotteryDrawPO.setLottery_number(zjcLotteryDrawPO.getLottery_number() - 1);
		zjcLotteryDrawDao.updateByKey(zjcLotteryDrawPO);
	}
	
	/**
	 * 获取奖品列表
	 * 
	 * @return
	 */
	public List<ZjcPrizePO> getZjcPrizeList(){
		Dto dto = Dtos.newDto();
		List<ZjcPrizePO> prizeList = zjcPrizeDao.list(dto);
		return prizeList;
	}

	/**
	 * 获奖列表
	 * 
	 * @return
	 */
	public List<ZjcWinPrizePO> getWinPrizeList(){
		Dto dto = Dtos.newDto();
		List<ZjcWinPrizePO> winPrizeList = zjcWinPrizeDao.list(dto);
		return winPrizeList;
	}
	
	
	/**
	 * MinImg
	 * 
	 * @param agrs
	 */
	public static void main(String[] agrs) {
        int i = 0;
        int[] result=new int[6];
        List<ZjcPrizePO> prizes = new ArrayList<ZjcPrizePO>();
        
        ZjcPrizePO p1 = new ZjcPrizePO();
        p1.setPrize_name("iPhone X");
        p1.setPrize_weight(0);//奖品的权重设置成1
        prizes.add(p1);

        ZjcPrizePO p2 = new ZjcPrizePO();
        p2.setPrize_name("再来一次");
        p2.setPrize_weight(20);//奖品的权重设置成2
        prizes.add(p2);

        ZjcPrizePO p3 = new ZjcPrizePO();
        p3.setPrize_name("500易物卷");
        p3.setPrize_weight(5);//奖品的权重设置成3
        prizes.add(p3);

        ZjcPrizePO p4 = new ZjcPrizePO();
        p4.setPrize_name("38易物卷");
        p4.setPrize_weight(15);//奖品的权重设置成4
        prizes.add(p4);
        
        ZjcPrizePO p5 = new ZjcPrizePO();
        p5.setPrize_name("18易物卷");
        p5.setPrize_weight(25);//奖品的权重设置成4
        prizes.add(p5);
        
        ZjcPrizePO p6 = new ZjcPrizePO();
        p6.setPrize_name("春节奇葩祝福");
        p6.setPrize_weight(35);//奖品的权重设置成4
        prizes.add(p6);
        
        System.out.println("抽奖开始");
        for (i = 0; i < 100000; i++)// 打印100个测试概率的准确性
        {
           // int selected=PrizeUtil.getPrizeIndex(prizes);
           // System.out.println("第"+i+"次抽中的奖品为："+prizes.get(selected).getPrize_name());
            //result[selected]++;
            System.out.println("--------------------------------");
        }
        System.out.println("抽奖结束");
        System.out.println("每种奖品抽到的数量为：");
        System.out.println("一等奖："+result[0]);
        System.out.println("二等奖："+result[1]);
        System.out.println("三等奖："+result[2]);
        System.out.println("四等奖："+result[3]);
        System.out.println("五等奖："+result[4]);
        System.out.println("六等奖："+result[5]);
        
    }
}
