/**
 * 
 */
package com.zjc.prize.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSCons;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;
import aos.system.common.utils.SystemCons;

import com.zjc.prize.dao.ZjcLotteryDrawDao;
import com.zjc.prize.dao.ZjcPrizeDao;
import com.zjc.prize.dao.ZjcWinPrizeDao;
import com.zjc.prize.dao.po.ZjcLotteryDrawPO;
import com.zjc.prize.dao.po.ZjcPrizePO;
import com.zjc.prize.dao.po.ZjcWinPrizePO;

/**
 * 奖品管理后台
 * 
 * @author Administrator
 *
 */
@Service(value="zjcPrizeHTService")
public class ZjcPrizeHTService {
	
	@Autowired
	private ZjcLotteryDrawDao zjcLotteryDrawDao;
	
	@Autowired
	private ZjcWinPrizeDao zjcWinPrizeDao;
	
	@Autowired
	private ZjcPrizeDao zjcPrizeDao;

	/**
	 * 初始化抽奖次数查询
	 * 
	 * @param httpModel
	 */
	public void initLotteryDraw(HttpModel httpModel){
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/prize/zjcLotteryDraw.jsp");
	}
	
	/**
	 * 初始化奖品管理
	 * 
	 * @param httpModel
	 */
	public void initPrize(HttpModel httpModel){
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/prize/zjcPrize.jsp");
	}
	
	/**
	 * 初始化中奖纪录
	 * 
	 * @param httpModel
	 */
	public void initWinPrize(HttpModel httpModel){
		httpModel.setAttribute("juid", httpModel.getInDto().getString("juid"));
		httpModel.setViewPath("project/zjc/prize/zjcWinPrize.jsp");
	}
	
	/**
	 * 抽奖次数查询列表
	 * 
	 * @param httpModel
	 */
	public void listLotteryDraw(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcLotteryDrawPO> lotteryDrawList = zjcLotteryDrawDao.listPage(qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(lotteryDrawList, qDto.getPageTotal()));
	}
	
	/**
	 * 奖品管理列表查询
	 * 
	 * @param httpModel
	 */
	public void listPrize(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcPrizePO> prizeList = zjcPrizeDao.listPage(qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(prizeList, qDto.getPageTotal()));
	}
	
	/**
	 * 中奖记录查询
	 * 
	 * @param httpModel
	 */
	public void listWinPrize(HttpModel httpModel){
		Dto qDto = httpModel.getInDto();
		List<ZjcWinPrizePO> winPrizeList = zjcWinPrizeDao.listPage(qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(winPrizeList, qDto.getPageTotal()));
	}
	
	
	/**
	 * 新增奖品
	 * @param httpModel
	 */
	public void savePrize(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		if (zjcPrizeDao.rows(Dtos.newDto("prize_name", inDto.getString("prize_name"))) > 0) {
			outDto.setAppCode(AOSCons.ERROR);
			outDto.setAppMsg(AOSUtils.merge("操作失败，该奖品[{0}]已经存在。", inDto.getString("prize_name")));
		}else {
			ZjcPrizePO zjcPrizePO = new ZjcPrizePO();
			zjcPrizePO.copyProperties(inDto);
			zjcPrizeDao.insert(zjcPrizePO);
			outDto.setAppMsg("奖品新增成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 修改奖品
	 * @param httpModel
	 */
	public void updatePrize(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcPrizePO zjcOldPrizePO = zjcPrizeDao.selectByKey(inDto.getInteger("prize_id"));
		if (!StringUtils.equals(zjcOldPrizePO.getPrize_name(), inDto.getString("prize_name"))) {
			if (zjcPrizeDao.rows(Dtos.newDto("prize_name", inDto.getString("prize_name"))) > 0) {
				outDto.setAppCode(AOSCons.ERROR);
				outDto.setAppMsg(AOSUtils.merge("操作失败，奖品[{0}]已经存在。", inDto.getString("prize_name")));
			}
		}
		if (StringUtils.equals(outDto.getAppCode(), SystemCons.SUCCESS)) {
			ZjcPrizePO zjcPrizePO = new ZjcPrizePO();
			zjcPrizePO.copyProperties(inDto);
			zjcPrizeDao.updateByKey(zjcPrizePO);
			outDto.setAppMsg("奖品修改成功");
		}
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
	
	/**
	 * 删除奖品
	 * 
	 * @param httpModel
	 */
	public void deletePrize(HttpModel httpModel){
		String[] selectionIds = httpModel.getInDto().getRows();
		int rows = 0;
		for (String prize_id : selectionIds) {
			zjcPrizeDao.deleteByKey(Integer.valueOf(prize_id));
			rows++;
		}
		httpModel.setOutMsg(AOSUtils.merge("操作成功，成功删除[{0}]条商品。", rows));
	}
	
	
	/**
	 * 修改抽奖次数
	 * 
	 * @param httpModel
	 */
	public void updateLotteryDraw(HttpModel httpModel){
		Dto outDto = Dtos.newOutDto();
		Dto inDto = httpModel.getInDto();
		ZjcLotteryDrawPO zjcLotteryDrawPO = new ZjcLotteryDrawPO();
		zjcLotteryDrawPO.copyProperties(inDto);
		zjcLotteryDrawDao.updateByKey(zjcLotteryDrawPO);
		outDto.setAppMsg("抽奖次数修改成功");
		httpModel.setOutMsg(AOSJson.toJson(outDto));
	}
}
