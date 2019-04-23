package com.zjc.article.dao.po;

import java.util.Date;

import aos.framework.core.typewrap.PO;

/**
 * <b>zjc_article[zjc_article]数据持久化对象</b>
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改。
 * </p>
 * 
 * @author AHei
 * @date 2017-07-07 11:48:02
 */
public class ZjcArticlePO extends PO {

	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	private Integer article_id;
	
	/**
	 * 类别ID
	 */
	private Integer cat_id;
	
	/**
	 * 文章标题
	 */
	private String title;
	
	/**
	 * 文章内容
	 */
	private String content;
	
	/**
	 * 文章作者
	 */
	private String author;
	
	/**
	 * 作者邮箱
	 */
	private String author_email;
	
	/**
	 * 关键字
	 */
	private String keywords;
	
	/**
	 * 文章类型
	 */
	private Integer article_type;
	
	/**
	 * 是否开启
	 */
	private Integer is_open;
	
	/**
	 * 添加时间
	 */
	private Date add_time;
	
	/**
	 * 附件地址
	 */
	private String file_url;
	
	/**
	 * open_type
	 */
	private Integer open_type;
	
	/**
	 * 链接地址
	 */
	private String link;
	
	/**
	 * 文章摘要
	 */
	private String description;
	
	/**
	 * 浏览量
	 */
	private Integer click;
	
	/**
	 * 文章发布时间
	 */
	private Date publish_time;
	
	/**
	 * 文章缩略图
	 */
	private String thumb;
	
	private String cat_name;
	/**
	 * 表id
	 * 
	 * @return article_id
	 */
	public Integer getArticle_id() {
		return article_id;
	}
	
	/**
	 * 类别ID
	 * 
	 * @return cat_id
	 */
	public Integer getCat_id() {
		return cat_id;
	}
	
	/**
	 * 文章标题
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 文章内容
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 文章作者
	 * 
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * 作者邮箱
	 * 
	 * @return author_email
	 */
	public String getAuthor_email() {
		return author_email;
	}
	
	/**
	 * 关键字
	 * 
	 * @return keywords
	 */
	public String getKeywords() {
		return keywords;
	}
	
	/**
	 * 文章类型
	 * 
	 * @return article_type
	 */
	public Integer getArticle_type() {
		return article_type;
	}
	
	/**
	 * 是否开启
	 * 
	 * @return is_open
	 */
	public Integer getIs_open() {
		return is_open;
	}
	
	/**
	 * 添加时间
	 * 
	 * @return add_time
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * 附件地址
	 * 
	 * @return file_url
	 */
	public String getFile_url() {
		return file_url;
	}
	
	/**
	 * open_type
	 * 
	 * @return open_type
	 */
	public Integer getOpen_type() {
		return open_type;
	}
	
	/**
	 * 链接地址
	 * 
	 * @return link
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * 文章摘要
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 浏览量
	 * 
	 * @return click
	 */
	public Integer getClick() {
		return click;
	}
	
	/**
	 * 文章发布时间
	 * 
	 * @return publish_time
	 */
	public Date getPublish_time() {
		return publish_time;
	}
	
	/**
	 * 文章缩略图
	 * 
	 * @return thumb
	 */
	public String getThumb() {
		return thumb;
	}
	

	/**
	 * 表id
	 * 
	 * @param article_id
	 */
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	
	/**
	 * 类别ID
	 * 
	 * @param cat_id
	 */
	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}
	
	/**
	 * 文章标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 文章内容
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 文章作者
	 * 
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * 作者邮箱
	 * 
	 * @param author_email
	 */
	public void setAuthor_email(String author_email) {
		this.author_email = author_email;
	}
	
	/**
	 * 关键字
	 * 
	 * @param keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * 文章类型
	 * 
	 * @param article_type
	 */
	public void setArticle_type(Integer article_type) {
		this.article_type = article_type;
	}
	
	/**
	 * 是否开启
	 * 
	 * @param is_open
	 */
	public void setIs_open(Integer is_open) {
		this.is_open = is_open;
	}
	
	/**
	 * 添加时间
	 * 
	 * @param add_time
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * 附件地址
	 * 
	 * @param file_url
	 */
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	
	/**
	 * open_type
	 * 
	 * @param open_type
	 */
	public void setOpen_type(Integer open_type) {
		this.open_type = open_type;
	}
	
	/**
	 * 链接地址
	 * 
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * 文章摘要
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 浏览量
	 * 
	 * @param click
	 */
	public void setClick(Integer click) {
		this.click = click;
	}
	
	/**
	 * 文章发布时间
	 * 
	 * @param publish_time
	 */
	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}
	
	/**
	 * 文章缩略图
	 * 
	 * @param thumb
	 */
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	

}