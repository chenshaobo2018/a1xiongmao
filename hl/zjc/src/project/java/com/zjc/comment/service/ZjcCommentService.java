/**
 * 
 */
package com.zjc.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.zjc.comment.dao.ZjcCommentDao;

/**
 * 用户评论
 * 
 * @author Administrator
 *
 */
@Service(value="zjcCommentService")
public class ZjcCommentService {
	
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcCommentDao zjcCommentDao;
	/**
	 * 初始化页面
	 * @param httpModel
	 */
	public void initComment(HttpModel httpModel){
		httpModel.setViewPath("project/zjc/comment/zjcComment.jsp");
	}
	
	/**
	 * 查询用户评论列表
	 * 
	 * @param httpModel
	 * @return
	 */
	public void listComment(HttpModel httpModel) {
		Dto qDto = httpModel.getInDto();
		List<Dto> commentDtos = sqlDao.list("com.zjc.comment.dao.ZjcCommentDao.listCommentPage", qDto);
		httpModel.setOutMsg(AOSJson.toGridJson(commentDtos, qDto.getPageTotal()));
	}
	
	
	/**
	 * 删除用户评论
	 * @param httpModel
	 */
	public void deleteComment(HttpModel httpModel){
		String[] selectionIds = httpModel.getInDto().getRows();
		int rows = 0;
		for (String comment_id : selectionIds) {
			zjcCommentDao.deleteByKey(Integer.valueOf(comment_id));
			rows++;
		}
		httpModel.setOutMsg(AOSUtils.merge("操作成功，成功删除[{0}]条用户评论。", rows));
	}
}
