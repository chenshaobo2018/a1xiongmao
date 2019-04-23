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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zjc.users.dao.po.ZjcUsersInfoPO;

import aos.framework.core.utils.AOSUtils;

/**
 * <b>后台管理系统动态资源请求拦截器</b>
 * <p>
 *    拦截http路径的请求，进行登录身份验证和登录有效期展期 (维持心跳)
 * 
 * @author liwenjun 
 */
public class HttpWebFilter implements Filter {
	private static Logger log = LoggerFactory.getLogger(HttpBackendFilter.class);

	/**
	 * 初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
		String servletPath = httpRequest.getServletPath();
		if(StringUtils.equalsIgnoreCase(servletPath, "/web/app/v1/webSendMessage.jhtml")
				|| StringUtils.equalsIgnoreCase(servletPath, "/web/storeLogin.jhtml")
					|| StringUtils.equalsIgnoreCase(servletPath, "/web/initRegister.jhtml")
						|| StringUtils.equalsIgnoreCase(servletPath, "/web/storeRegister.jhtml")
						|| StringUtils.equalsIgnoreCase(servletPath, "/web/getVerificationCode.jhtml")
						|| StringUtils.equalsIgnoreCase(servletPath, "/web/validate_code.jhtml")
						|| StringUtils.equalsIgnoreCase(servletPath, "/web/confirm_reset.jhtml")){
		}
		boolean pass = true;
		/*log.info("-----------------------------------------------------------接口调用逻辑开始------------------------：" + servletPath);
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
				log.info("-----------------------------------------------------------商品详情参数goods_id:------------------------：" + httpRequest.getParameter("goods_id"));
				httpRequest.getSession().setAttribute("goods_id", httpRequest.getParameter("goods_id"));
				pass = false;
			}
		} else {
			if(AOSUtils.isEmpty(httpRequest.getSession().getAttribute("openid"))||AOSUtils.isEmpty(httpRequest.getSession().getAttribute("user_id"))){
				httpRequest.getSession().setAttribute("type", "normal");//标识正常请求接口
				httpRequest.getSession().setAttribute("methodStr", servletPath);//传入请求地址
				pass = false;
			}
		}*/
		
		ZjcUsersInfoPO userinfo = (ZjcUsersInfoPO) httpRequest.getSession().getAttribute("user_id");
		if(AOSUtils.isEmpty(userinfo)){
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
