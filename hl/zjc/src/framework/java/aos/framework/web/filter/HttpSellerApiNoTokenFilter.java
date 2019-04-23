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

/**
 * <b>API接口拦截器</b>
 * <p>
 * 拦截/api/*后台接口请求。无实现，可自行扩展。
 * 
 * @author xiongchun
 */
@SuppressWarnings("sellernotokenapi")
public class HttpSellerApiNoTokenFilter implements Filter {

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
		chain.doFilter(httpRequest, httpResponse);
	}
	
	/**
	 * 释放资源
	 */
	@Override
	public void destroy() {
	}

}
