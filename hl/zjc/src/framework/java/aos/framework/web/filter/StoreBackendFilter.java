/**
 * 
 */
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

import com.zjc.store.dao.po.ZjcSellerInfoPO;

import aos.framework.core.utils.AOSUtils;

/**
 * 商家管理后台拦截
 * 
 * @author zc
 *
 */
public class StoreBackendFilter implements Filter{
	
	private static Logger log = LoggerFactory.getLogger(StoreBackendFilter.class);
	
	/** 
	 * 初始化
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
		String servletPath = httpRequest.getServletPath();
		if(StringUtils.equalsIgnoreCase(servletPath, "/store/initStoreLogin.jhtml")
				|| StringUtils.equalsIgnoreCase(servletPath, "/store/storeLogin.jhtml")
					|| StringUtils.equalsIgnoreCase(servletPath, "/store/initRegister.jhtml")
						|| StringUtils.equalsIgnoreCase(servletPath, "/store/storeRegister.jhtml")
						|| StringUtils.equalsIgnoreCase(servletPath, "/store/getVerificationCode.jhtml")
						|| StringUtils.equalsIgnoreCase(servletPath, "/store/validate_code.jhtml")
						|| StringUtils.equalsIgnoreCase(servletPath, "/store/confirm_reset.jhtml")){
			chain.doFilter(httpRequest, httpResponse);
			return;			
		}
		ZjcSellerInfoPO sellerInfo = (ZjcSellerInfoPO) httpRequest.getSession().getAttribute("sellerInfo");
		boolean pass = true;
		if(AOSUtils.isEmpty(sellerInfo)){
			log.warn("用户未登录。");
			pass = false;
		}else{
			if(StringUtils.equalsIgnoreCase(servletPath, "/store")){
				log.warn("请求被重定向到登录页。【URL：{}】", uri);
				httpResponse.getWriter().write("<script type=\"text/javascript\">parent.location.href='"
						+ httpRequest.getContextPath() + "/store/homepage.jhtml'</script>");
				httpResponse.getWriter().flush();
				httpResponse.getWriter().close();

			}
		}
		if (!pass) {
			log.warn("请求被重定向到登录页。【URL：{}】", uri);
			httpResponse.getWriter().write("<script type=\"text/javascript\">parent.location.href='"
					+ httpRequest.getContextPath() + "/store/initStoreLogin.jhtml'</script>");
			httpResponse.getWriter().flush();
			httpResponse.getWriter().close();
			return;
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
