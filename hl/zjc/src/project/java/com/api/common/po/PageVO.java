package com.api.common.po;



/**
 * <b>分页实体类</b>
 * 
 * @author wgm
 * @date 2017-06-27 15:04:49
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class PageVO {

	
	/**
	 * 当前页
	 */
	private int nowPage;
	
	/**
	 * 分页数据
	 */
	private Object list;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 是否有下一页
	 */
	private boolean hasNextPage;
	
	/**
	 * 总记录数
	 */
	private int totalSize;
	private int sum;
	private int notsum;
	/**
	 * 连续购买了多少天
	 */
	private int continueTimes;
	
	/**
	 * 连续购买了多少天
	 * 
	 * @return
	 */
	public int getContinueTimes() {
		return continueTimes;
	}

	/**
	 * 连续购买了多少天
	 * 
	 * @param continueTimes
	 */
	public void setContinueTimes(int continueTimes) {
		this.continueTimes = continueTimes;
	}

	public int getNotsum() {
		return notsum;
	}

	public void setNotsum(int notsum) {
		this.notsum = notsum;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
}