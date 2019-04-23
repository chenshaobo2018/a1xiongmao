package com.api.comment;

import java.util.Date;

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
import com.api.common.util.ParameterUtil;
import com.zjc.comment.dao.ZjcCommentDao;
import com.zjc.comment.dao.po.ZjcCommentPO;
import com.zjc.goods.dao.ZjcGoodsDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.order.dao.ZjcRechargeDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * 需要Tiken的app接口- 易物券订单
 * 
 * @author wgm
 */
@Service(value="commentService")
public class CommentService {
	@Autowired
	private ZjcRechargeDao zjcRechargeDao;
	
	
	@Autowired
	private SqlDao sqlDao;
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	
	@Autowired
	private ZjcCommentDao zjcCommentDao;
	
	@Autowired
	private ZjcGoodsDao zjcGoodsDao;
	

	/**
	 * app接口-添加评论
	 * 
	 * @param httpModel
	 * @return json
	 */
	public String add_comment(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
		if(AOSUtils.isEmpty(dto.getString("order_id"))||AOSUtils.isEmpty(dto.getString("goods_id"))
				||AOSUtils.isEmpty(dto.getString("logistics_rank"))||AOSUtils.isEmpty(dto.getString("quality_rank"))){//参数不能为空
			msgVO.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Condition_Is_Null.getName());
		} else {
			ZjcUsersInfoPO userInfo = zjcUsersInfoDao.selectByKey(dto.getBigInteger("user_id"));
			ZjcCommentPO commentPO = zjcCommentDao.selectOne(dto);
			if(AOSUtils.isEmpty(userInfo)){//用户不存在
				msgVO.setCode(Apiconstant.Username_Not_Exist.getIndex());
				msgVO.setMsg(Apiconstant.Username_Not_Exist.getName());
			} else if(!AOSUtils.isEmpty(commentPO)){
				msgVO.setCode(Apiconstant.HAS_COMMENT.getIndex());
				msgVO.setMsg(Apiconstant.HAS_COMMENT.getName());
			}else {//用户存在
				ZjcCommentPO zjcCommentPO = new ZjcCommentPO();
				zjcCommentPO.copyProperties(dto);
				zjcCommentPO.setUsername(userInfo.getReal_name());
				zjcCommentPO.setContent(ParameterUtil.filterEmoji(dto.getString("content")));
				zjcCommentPO.setComment_id(idService.nextValue(SystemCons.SEQ.SEQ_SYSTEM).intValue());
				zjcCommentPO.setAdd_time(new Date());
				zjcCommentPO.setIp_address(ParameterUtil.getIpAddr(httpModel.getRequest()));
				int successNum = zjcCommentDao.insert(zjcCommentPO);
				if(successNum==0){//保存失败
					msgVO.setCode(Apiconstant.Save_fails.getIndex());
					msgVO.setMsg(Apiconstant.Save_fails.getName());
				} else {//保存成功
					//提交完评价修改订单状态
					sqlDao.update("com.zjc.order.dao.ZjcOrderDao.order_has_comment", dto);
					//增加商品评论数
					ZjcGoodsPO goodsPO = zjcGoodsDao.selectByKey(dto.getInteger("goods_id"));
					goodsPO.setComment_count(goodsPO.getComment_count()+1);//评论数加1
					zjcGoodsDao.updateByKey(goodsPO);
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
				}
			}
		}
		return AOSJson.toJson(msgVO);
	}


	/**
	 * app接口-追加评论
	 * 
	 * @param httpModel
	 * @return
	 */
	public String again_content(HttpModel httpModel) {
		MessageVO msgVO = new MessageVO();
		//获取查询条件
		Dto dto = httpModel.getInDto();
		dto.put("user_id", httpModel.getRequest().getAttribute("user_id"));
		String again_content = dto.getString("again_content");
		if(AOSUtils.isEmpty(dto.getString("order_id"))||AOSUtils.isEmpty(dto.getString("goods_id"))){//参数不能为空
			msgVO.setCode(Apiconstant.Condition_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Condition_Is_Null.getName());
		} else if(AOSUtils.isEmpty(again_content)){//追加评论内容不能为空
			msgVO.setCode(Apiconstant.Comment_Is_Null.getIndex());
			msgVO.setMsg(Apiconstant.Comment_Is_Null.getName());
		} else {//查询条件不为空
			//通过user_id,order_id,goods_id查询评论
			dto.put("again_content", null);
			ZjcCommentPO zjcCommentPO = zjcCommentDao.selectOne(dto);
			if(AOSUtils.isEmpty(zjcCommentPO)){//您尚未评论该商品
				msgVO.setCode(Apiconstant.Comment_Not_Exist.getIndex());
				msgVO.setMsg(Apiconstant.Comment_Not_Exist.getName());
			} else if(!AOSUtils.isEmpty(zjcCommentPO.getAgain_content())) {//您已经追加评论过了
				msgVO.setCode(Apiconstant.Comment_Was_Again_Evl.getIndex());
				msgVO.setMsg(Apiconstant.Comment_Was_Again_Evl.getName());
			} else {//成功
				zjcCommentPO.setAgain_content(ParameterUtil.filterEmoji(again_content));
				zjcCommentPO.setAgain_time(new Date());
				zjcCommentPO.setIp_address(ParameterUtil.getIpAddr(httpModel.getRequest()));
				int successNum = zjcCommentDao.updateByKey(zjcCommentPO);
				if(successNum==0){//追加评论失败
					msgVO.setCode(Apiconstant.Save_fails.getIndex());
					msgVO.setMsg(Apiconstant.Save_fails.getName());
				} else {//追加评论成功
					//增加商品评论数
					ZjcGoodsPO goodsPO = zjcGoodsDao.selectByKey(dto.getInteger("goods_id"));
					goodsPO.setComment_count(goodsPO.getComment_count()+1);//评论数加1
					zjcGoodsDao.updateByKey(goodsPO);
					msgVO.setCode(Apiconstant.Do_Success.getIndex());
					msgVO.setMsg(Apiconstant.Do_Success.getName());
				}
			}
			
		}
		return AOSJson.toJson(msgVO);
	}
}
