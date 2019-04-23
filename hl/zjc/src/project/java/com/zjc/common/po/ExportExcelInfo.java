/**  
 * @文件名 ExportExcelInfo.java
 * @版权 Copyright 2009-2015 版权所有：大庆金桥信息技术工程有限公司成都分公司
 * @描述 ExportExcelInfo.java
 * @修改人 forest
 * @修改时间 2015年12月22日
 * @修改内容 新增
 */
package com.zjc.common.po;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Excel导出用参数类
 * 
 * @author wugaoming
 * @version V1.0,2017年5月22日
 * @param <T>
 * @see [相关类/方法]
 * @since V1.0
 * 
 */
public class ExportExcelInfo {

    @SuppressWarnings("rawtypes")
    private List objList;
    private String title;    
    private String[] strHeader;
    private String[] strField;
    private HttpServletRequest req;
    private HttpServletResponse resp;

    /**
     * @return List 输出数据List的get方法
     * 
     */
    @SuppressWarnings("rawtypes")
    public List getObjList() {
        return objList;
    }
    /**
     * @return String sheet名称的get方法
     * 
     */
    public String getTitle() {
        return title;
    }
    /**
     * @return String[] Excel模板列头数据数组的get方法
     * 
     */
    public String[] getStrHeader() {
        return strHeader;
    }
    /**
     * @return String[] Excel列对应字段名称数组的get方法
     * 
     */
    public String[] getStrField() {
        return strField;
    }
    /**
     * @return HttpServletRequest Http请求对象的get方法
     * 
     */
    public HttpServletRequest getReq() {
        return req;
    }
    /**
     * @return HttpServletResponse http响应对象的get方法
     * 
     */
    public HttpServletResponse getResp() {
        return resp;
    }
    /**
     * @param objList 输出数据List的set方法
     */
    @SuppressWarnings("rawtypes")
    public void setObjList(List objList) {
        this.objList = objList;
    }
    /**
     * @param title sheet名称的set方法
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @param strHeader Excel模板列头数据数组的set方法
     */
    public void setStrHeader(String[] strHeader) {
        this.strHeader = strHeader;
    }
    /**
     * @param strField Excel列对应字段名称数组的set方法
     */
    public void setStrField(String[] strField) {
        this.strField = strField;
    }
    /**
     * @param req Http请求对象的set方法
     */
    public void setReq(HttpServletRequest req) {
        this.req = req;
    }
    /**
     * @param resp Http响应对象的set方法
     */
    public void setResp(HttpServletResponse resp) {
        this.resp = resp;
    }
    
}
