/**  
 * @文件名 ExportExcle.java
 * @描述 ExportExcle.java
 * @修改人 wugaoming
 * @修改时间 2017年5月22日
 * @修改内容 新增
 */
package com.zjc.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import aos.framework.core.utils.AOSUtils;

import com.store.goods.OrderExportVO;
import com.zjc.common.po.ExportExcelInfo;
import com.zjc.order.dao.po.ZjcTDOrderExportVO;



public class ExportExcle {
    private ExportExcle(){        
    }
    
    
    /**
     * 根据表做表单创建表单的标题行
     * @author lls
     * @param sheet 表单对象
     * @param strHeader 标题行字符串
     */
    private static void createHeader(Sheet sheet, String strHeader[]) {
    	Row row = sheet.createRow(0); // 创建该页的一行
    	Cell cell = null;
        for (int i = 0; i < strHeader.length; i++) {
            cell = row.createCell(i); // 创建该行的一列
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(strHeader[i]);
        }
    }
    
    /**
     * 根据导出配置参数导出EXCEL文件
     * @Title exportExcel
     * @author forest
     * @Description: 
     * @date 2015年12月22日
     * @param param 导出配置参数
     * @return Http响应输出Excel文件
     */ 
    @SuppressWarnings({ "rawtypes", "resource" })
    public static Boolean exportExcel(ExportExcelInfo param) {
        // 创建工作簿（Excel文件）
    	SXSSFWorkbook  workbook = new SXSSFWorkbook();
        OutputStream out=null;
        // 文件头信息
 		String fileName = param.getTitle()  + ".xls";
        try {
			out = param.getResp().getOutputStream();// 取得输出流   
			param.getResp().reset();// 清空输出流  
			fileName = AOSUtils.encodeChineseDownloadFileName(param.getReq().getHeader("USER-AGENT"),
	 				fileName);
	 		param.getResp().setHeader("Content-Disposition", "attachment; filename=" + fileName + ";");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        // 创建Excel工作簿的第一个Sheet页
        Sheet sheet = (Sheet) workbook.createSheet(param.getTitle());
            
        // 创建Sheet页的标题行
        createHeader(sheet, param.getStrHeader());
        // 外循环为行数、内循环为列数
        for (int objIndex = 0; objIndex < param.getObjList().size(); objIndex++) {
            try {
            	Map map = BeanToMapUtil.convertBean(param.getObjList().get(objIndex));
                Row row=sheet.createRow(objIndex + 1);
                for (int i = 0; i < param.getStrField().length; i++) {
                	Cell cell = row.createCell(i);
                    if((param.getStrHeader()[i]).toString().equals("商品数量") || (param.getStrHeader()[i]).toString().equals("券")
                    	|| (param.getStrHeader()[i]).toString().equals("易支付")|| (param.getStrHeader()[i]).toString().equals("库存")
                    	|| (param.getStrHeader()[i]).toString().equals("销量")){
                    	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    	cell.setCellValue(map.get(param.getStrField()[i]) == null ? 0
                        		: new BigDecimal(map.get(param.getStrField()[i]).toString()).intValue());
                    } else if(((param.getStrHeader()[i]).toString().equals("现金"))||(param.getStrHeader()[i]).toString().equals("在线支付")){
                        	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        	cell.setCellValue(map.get(param.getStrField()[i]) == null ? 0
                            		: Double.parseDouble(map.get(param.getStrField()[i]).toString()));
                   } else {
                    	cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(map.get(param.getStrField()[i]) == null ? ""
                        		: map.get(param.getStrField()[i]).toString());
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        // 保存Excel文件
        try {
        	//OutputStream out = param.getResp().getOutputStream();
            out.flush();
            workbook.write(out);
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    @SuppressWarnings({ "resource", "rawtypes" })
	public static ResponseEntity<byte[]> exportExcel2(String title, String[] hd, String[] fields, List<OrderExportVO> list, HttpServletResponse response){
    	File file = new File(title+".xls");
    	try {
    		SXSSFWorkbook  workbook = new SXSSFWorkbook();
        	// 创建Excel工作簿的第一个Sheet页
            Sheet sheet = (Sheet) workbook.createSheet(title);
            // 创建Sheet页的标题行
            createHeader(sheet, hd);
            // 外循环为行数、内循环为列数
            for (int objIndex = 0; objIndex < list.size(); objIndex++) {
                Map map = BeanToMapUtil.convertBean(list.get(objIndex));
                Row row=sheet.createRow(objIndex + 1);
                for (int i = 0; i < fields.length; i++) {
                    Cell cell = row.createCell(i);
                    if(i == 4){
                    	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(map.get(fields[i]) == null ? 0
                        :  Double.parseDouble(map.get(fields[i]).toString()));
                    } else if(i == 5 || i == 6){
                    	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(map.get(fields[i]) == null ? 0
                        :  new BigDecimal(map.get(fields[i]).toString()).intValue());
                    } else {
                    	cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(map.get(fields[i]) == null ? ""
                        : map.get(fields[i]).toString());
                    }
                }
            }
            
            FileOutputStream outputStream = new FileOutputStream(title+".xls");  
            workbook.write(outputStream);  
            outputStream.flush();  
            outputStream.close(); 
        	HttpHeaders headers = new HttpHeaders();  
        	String downFileName = URLEncoder.encode(title+".xls");
            headers.setContentDispositionFormData("attachment",downFileName);   
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			return null;
		}finally {
			file.delete();
		}
    }
	
	
	
	 @SuppressWarnings({ "resource", "rawtypes" })
		public static ResponseEntity<byte[]> exportExcel3(String title, String[] hd, String[] fields, List<OrderExportVO> list, HttpServletResponse response){
	    	File file = new File(title+".xls");
	    	try {
	    		SXSSFWorkbook  workbook = new SXSSFWorkbook();
	        	// 创建Excel工作簿的第一个Sheet页
	            Sheet sheet = (Sheet) workbook.createSheet(title);
	            // 创建Sheet页的标题行
	            createHeader(sheet, hd);
	            // 外循环为行数、内循环为列数
	            for (int objIndex = 0; objIndex < list.size(); objIndex++) {
	                Map map = BeanToMapUtil.convertBean(list.get(objIndex));
	                Row row=sheet.createRow(objIndex + 1);
	                for (int i = 0; i < fields.length; i++) {
	                    Cell cell = row.createCell(i);
	                    cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(map.get(fields[i]) == null ? ""
                        : map.get(fields[i]).toString());
	                    /*if(i == 4){
	                    	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
	                        cell.setCellValue(map.get(fields[i]) == null ? 0
	                        :  Double.parseDouble(map.get(fields[i]).toString()));
	                    } else if(i == 5 || i == 6){
	                    	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
	                        cell.setCellValue(map.get(fields[i]) == null ? 0
	                        :  new BigDecimal(map.get(fields[i]).toString()).intValue());
	                    } else {
	                    	cell.setCellType(Cell.CELL_TYPE_STRING);
	                        cell.setCellValue(map.get(fields[i]) == null ? ""
	                        : map.get(fields[i]).toString());
	                    }*/
	                }
	            }
	            
	            FileOutputStream outputStream = new FileOutputStream(title+".xls");  
	            workbook.write(outputStream);  
	            outputStream.flush();  
	            outputStream.close(); 
	        	HttpHeaders headers = new HttpHeaders();  
	        	String downFileName = URLEncoder.encode(title+".xls");
	            headers.setContentDispositionFormData("attachment",downFileName);   
	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
			} catch (Exception e) {
				return null;
			}finally {
				file.delete();
			}
	    }
		
		@SuppressWarnings({ "resource", "rawtypes" })
		public static ResponseEntity<byte[]> exportTDExcel(String title, String[] hd, String[] fields, List<ZjcTDOrderExportVO> list, HttpServletResponse response){
	    	File file = new File(title+".xls");
	    	try {
	    		SXSSFWorkbook  workbook = new SXSSFWorkbook();
	        	// 创建Excel工作簿的第一个Sheet页
	            Sheet sheet = (Sheet) workbook.createSheet(title);
	            // 创建Sheet页的标题行
	            createHeader(sheet, hd);
	            // 外循环为行数、内循环为列数
	            for (int objIndex = 0; objIndex < list.size(); objIndex++) {
	                Map map = BeanToMapUtil.convertBean(list.get(objIndex));
	                Row row=sheet.createRow(objIndex + 1);
	                for (int i = 0; i < fields.length; i++) {
	                    Cell cell = row.createCell(i);
	                    cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(map.get(fields[i]) == null ? ""
                        : map.get(fields[i]).toString());
	                }
	            }
	            
	            FileOutputStream outputStream = new FileOutputStream(title+".xls");  
	            workbook.write(outputStream);  
	            outputStream.flush();  
	            outputStream.close(); 
	        	HttpHeaders headers = new HttpHeaders();  
	        	String downFileName = URLEncoder.encode(title+".xls");
	            headers.setContentDispositionFormData("attachment",downFileName);   
	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
			} catch (Exception e) {
				return null;
			}finally {
				file.delete();
			}
	    }

}