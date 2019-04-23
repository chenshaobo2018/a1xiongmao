/**
 * 
 */
package com.zjc.report.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterContext;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporterContext;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.export.XlsExporterConfiguration;
import net.sf.jasperreports.export.XlsReportConfiguration;
import net.sf.jasperreports.export.XlsxExporterConfiguration;
import net.sf.jasperreports.export.XlsxReportConfiguration;

import org.springframework.stereotype.Service;

import aos.framework.core.exception.AOSException;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.zjc.report.util.AOSPrint;
import com.zjc.report.util.AOSReport;

/**
 * @author Administrator
 *
 */
@Service(value="aosReportService")
public class AOSReportService {

	private static final String DEFAULT_FILE_NAME = "报表数据";

	// 用于支持同一个会话上缓存多个报表的参数名称，由URL传递过来。&aos_report_=会话中存储AOSPrint对象的Key
	private static final String AOSPRINT_PARAM = "aos_report_";

	/**
	 * 获取AOSPrint对象
	 * 
	 * @param inDto
	 * @param session
	 * @return
	 */
	private AOSPrint getAosPrintFromSession(HttpModel httpModel) {
		String Key = AOSReport.DEFAULT_AOSPRINT_KEY;
		if (AOSUtils.isNotEmpty(httpModel.getInDto().getString(AOSPRINT_PARAM))) {
			Key = httpModel.getInDto().getString(AOSPRINT_PARAM);
		}
		AOSPrint aosPrint = (AOSPrint) httpModel.getRequest().getSession().getAttribute(Key);
		if (aosPrint == null) {
			throw new AOSException(9, Key);
		}
		return aosPrint;
	}

	/**
	 * 以PDF格式输出打印对象
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	public void pdf(HttpModel httpModel) throws IOException {
		httpModel.getResponse().setContentType("application/pdf");
		AOSPrint aosPrint = getAosPrintFromSession(httpModel);
		String fileName = AOSUtils.isEmpty(aosPrint.getFileName()) ? DEFAULT_FILE_NAME : aosPrint.getFileName();
		httpModel.getResponse().setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName + ".pdf", "utf-8"));
		OutputStream outputStream = httpModel.getResponse().getOutputStream();
		JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(aosPrint.getJasperPrint()));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		try {
			exporter.exportReport();
		} catch (Exception e) {
			throw new AOSException(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e2) {
				}
			}
		}
	}
	
	/**
	 * 以DOCX格式输出打印对象
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	public void docx(HttpModel httpModel) throws IOException {
		httpModel.getResponse().setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		AOSPrint aosPrint = getAosPrintFromSession(httpModel);
		String fileName = AOSUtils.isEmpty(aosPrint.getFileName()) ? DEFAULT_FILE_NAME : aosPrint.getFileName();
		httpModel.getResponse().setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName + ".docx", "utf-8"));
		OutputStream outputStream = httpModel.getResponse().getOutputStream();
		JRDocxExporter exporter = new JRDocxExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(aosPrint.getJasperPrint()));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		try {
			exporter.exportReport();
		} catch (Exception e) {
			throw new AOSException(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e2) {
				}
			}
		}
	}
	
	/**
	 * 以PPTX格式输出打印对象
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	public void pptx(HttpModel httpModel) throws IOException {
		httpModel.getResponse().setContentType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
		AOSPrint aosPrint = getAosPrintFromSession(httpModel);
		String fileName = AOSUtils.isEmpty(aosPrint.getFileName()) ? DEFAULT_FILE_NAME : aosPrint.getFileName();
		httpModel.getResponse().setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName + ".pptx", "utf-8"));
		OutputStream outputStream = httpModel.getResponse().getOutputStream();
		JRPptxExporter exporter = new JRPptxExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(aosPrint.getJasperPrint()));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		try {
			exporter.exportReport();
		} catch (Exception e) {
			throw new AOSException(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	/**
	 * 以HTML格式输出打印对象
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	public void html(HttpModel httpModel) throws IOException {
		httpModel.getResponse().setContentType("text/html");
		JasperPrint jasperPrint = getAosPrintFromSession(httpModel).getJasperPrint();
		// Servlet环境下使用不需要关闭。response会帮你关闭，别自找麻烦！
		// 仅限PrintWriter哦!!
		PrintWriter out = httpModel.getResponse().getWriter();
		try {
			HtmlExporter exporter = new HtmlExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			SimpleHtmlExporterOutput output = new SimpleHtmlExporterOutput(out);
			exporter.setExporterOutput(output);
			exporter.exportReport();
		} catch (JRException e) {
			throw new AOSException("报表输出为HTML格式时发生异常。", e);
		}
	}

	/**
	 * 以XLS格式输出打印对象
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	public void xls(HttpModel httpModel) throws IOException {
		httpModel.getResponse().setContentType("application/xls");
		AOSPrint aosPrint = getAosPrintFromSession(httpModel);
		String fileName = AOSUtils.isEmpty(aosPrint.getFileName()) ? DEFAULT_FILE_NAME : aosPrint.getFileName();
		httpModel.getResponse().setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName + ".xls", "utf-8"));
		JRXlsAbstractExporter<XlsReportConfiguration, XlsExporterConfiguration, JRXlsExporterContext> exporter = new JRXlsExporter(
				DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(aosPrint.getJasperPrint()));
		SimpleXlsReportConfiguration configuration = aosPrint.getXlsConfiguration();
		if (configuration == null) {
			configuration = new SimpleXlsReportConfiguration();
			configuration.setOnePagePerSheet(false);
			configuration.setWhitePageBackground(false);
		}
		exporter.setConfiguration(configuration);
		OutputStream outputStream = httpModel.getResponse().getOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		try {
			exporter.exportReport();
			outputStream.flush();
		} catch (Exception e) {
			throw new AOSException(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException ex) {
				}
			}
		}
	}

	/**
	 * 以XLSX格式输出打印对象
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	public void xlsx(HttpModel httpModel) throws IOException {
		httpModel.getResponse().setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		AOSPrint aosPrint = getAosPrintFromSession(httpModel);
		String fileName = AOSUtils.isEmpty(aosPrint.getFileName()) ? DEFAULT_FILE_NAME : aosPrint.getFileName();
		httpModel.getResponse().setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
		JRXlsAbstractExporter<XlsxReportConfiguration, XlsxExporterConfiguration, JRXlsxExporterContext> exporter = new JRXlsxExporter(
				DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(aosPrint.getJasperPrint()));
		SimpleXlsxReportConfiguration configuration = aosPrint.getXlsxConfiguration();
		if (configuration == null) {
			configuration = new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(false);
			configuration.setWhitePageBackground(false);
		}
		exporter.setConfiguration(configuration);
		OutputStream outputStream = httpModel.getResponse().getOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		try {
			exporter.exportReport();
			outputStream.flush();
		} catch (Exception e) {
			throw new AOSException(e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException ex) {
				}
			}
		}
	}
	
}
