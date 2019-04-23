package aos.framework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aos.framework.core.utils.AOSUtils;

/**
 * <b>API接口拦截器</b>
 * <p>
 * 拦截/api/*后台接口请求。无实现，可自行扩展。
 * 
 * @author xiongchun
 */
@SuppressWarnings("notokenapi")
public class HttpApiNoTokenFilter implements Filter {

	private static Logger log = LoggerFactory.getLogger(HttpApiNoTokenFilter.class);
	
	/**
	 * 初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * 过滤
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		response.setContentType("application/json; charset=UTF-8");
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Headers",  "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Authorization");
	    //允许请求资源的方式
		httpResponse.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
		httpResponse.setHeader("X-Powered-By", "3.2.1");
		httpResponse.setHeader("Content-Type", "application/json;charset=utf-8");
		String servletPath = httpRequest.getServletPath();
		//判断是否是微信商城所需接口
		if(StringUtils.equalsIgnoreCase(servletPath, "/notokenapi/wx/v1/toWxRegisterPage.jhtml")
				//||StringUtils.equalsIgnoreCase(servletPath, "/notokenapi/wx/v1/wxBindRegister.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/notokenapi/wx/v1/wxSendGoods.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/notokenapi/wx/v1/initLottery.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/notokenapi/wx/v1/goodsDetails.jhtml")
				//||StringUtils.equalsIgnoreCase(servletPath, "/notokenapi/wx/v1/wxBind.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/notokenapi/wx/v1/toWxBindPage.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/notokenapi/wx/v1/initPayPwd.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/notokenapi/wx/v1/initGoBuy.jhtml")){//微信商城过滤
			boolean pass = true;
			log.info("-----------------------------------------------------------接口调用逻辑开始------------------------：" + servletPath);
			if(StringUtils.equalsIgnoreCase(servletPath, "/notokenapi/wx/v1/toWxRegisterPage.jhtml")){
				if(AOSUtils.isEmpty(httpRequest.getSession().getAttribute("user_id"))){
					httpRequest.getSession().setAttribute("type", "register");//标识注册或者绑定
					pass = false;
				}
			} else if(StringUtils.equalsIgnoreCase(servletPath, "/notokenapi/wx/v1/toWxBindPage.jhtml")){
				if(AOSUtils.isEmpty(httpRequest.getSession().getAttribute("user_id"))){
					httpRequest.getSession().setAttribute("type", "bind");//标识注册或者绑定
					pass = false;
				}
			} else if(servletPath.contains("goodsDetails.jhtml")){
				if(AOSUtils.isEmpty(httpRequest.getSession().getAttribute("openid"))){
					httpRequest.getSession().setAttribute("type", "special");//特殊标识商品详情
					httpRequest.getSession().setAttribute("goods_id", httpRequest.getParameter("goods_id"));
					pass = false;
				}
			} else {
				if(AOSUtils.isEmpty(httpRequest.getSession().getAttribute("openid"))||AOSUtils.isEmpty(httpRequest.getSession().getAttribute("user_id"))){
					httpRequest.getSession().setAttribute("type", "normal");//标识正常请求接口
					httpRequest.getSession().setAttribute("methodStr", servletPath);//传入请求地址
					pass = false;
				}
			}
			
			if(pass){
				chain.doFilter(httpRequest, httpResponse);
				return;	
			} else {
				String urla = "https://zjc1518.com/aosuite/notokenapi/wx/v1/getWxCode.jhtml";
				//String urla = "http://wexin.web.zjc1518.cn/aosuite/notokenapi/wx/v1/getWxCode.jhtml";
				log.info("-----------------------------------------------------------请求被重定向到登录页。【URL：{}】", urla);
				httpResponse.sendRedirect(urla);
				return ;
			}
		} 
		chain.doFilter(httpRequest, httpResponse);
	}
	
	/**
	 * 释放资源
	 */
	@Override
	public void destroy() {
	}

}
