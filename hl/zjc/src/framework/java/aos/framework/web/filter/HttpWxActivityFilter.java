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
 * <b>wx分享活动接口拦截器</b>
 * <p>
 * 
 * 
 * @author wgm
 */
@SuppressWarnings("wxact")
public class HttpWxActivityFilter implements Filter {

	private static Logger log = LoggerFactory.getLogger(HttpWxActivityFilter.class);
	
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
		String servletPath = httpRequest.getServletPath();
		//判断是否是微信分享活动所需接口
		if(StringUtils.equalsIgnoreCase(servletPath, "/wxact/activity/v1/initActivityShareHelp.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/wxact/activity/v1/initShareProduction.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/wxact/activity/v1/initLuckDraw.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/wxact/activity/v1/getActivityGoodsDetails.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/wxact/activity/v1/initShareDetail.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/wxact/activity/v1/init.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/wxact/activity/v1/initRedPacketIndex.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/wxact/activity/v1/initShareRedPacket.jhtml")
				||StringUtils.equalsIgnoreCase(servletPath, "/wxact/activity/v1/initRedPacketRegister.jhtml")){//是微信分享活动过滤
			boolean pass = true;
			log.info("-----------------------------------------------------------微信信分享活动接口调用逻辑开始------------------------：" + servletPath);
			if(servletPath.contains("getActivityGoodsDetails.jhtml")){
				log.info("======----------------分享GOODID：-------------------=====:"+ httpRequest.getParameter("goods_id"));
				httpRequest.getSession().setAttribute("goods_id", httpRequest.getParameter("goods_id"));
				if(AOSUtils.isEmpty(httpRequest.getSession().getAttribute("openid"))){
					httpRequest.getSession().setAttribute("type", "special");//特殊标识商品详情
					pass = false;
				}
			}else if(servletPath.contains("initShareDetail.jhtml")){
				httpRequest.getSession().setAttribute("goods_id", httpRequest.getParameter("goods_id"));
				httpRequest.getSession().setAttribute("share_id", httpRequest.getParameter("shareId"));
				if(AOSUtils.isEmpty(httpRequest.getSession().getAttribute("openid"))){
					httpRequest.getSession().setAttribute("type", "sharepage");//跳转分享页面
					pass = false;
				}
			}else if(servletPath.contains("initShareRedPacket.jhtml")){
				httpRequest.getSession().setAttribute("shareid", httpRequest.getParameter("shareId"));
				if(AOSUtils.isEmpty(httpRequest.getSession().getAttribute("openid"))){
					httpRequest.getSession().setAttribute("type", "redsharepage");//跳转分享页面
					pass = false;
				}
			} else {
				if(AOSUtils.isEmpty(httpRequest.getSession().getAttribute("openid"))){
					httpRequest.getSession().setAttribute("type", "normal");//标识正常请求接口
					httpRequest.getSession().setAttribute("methodStr", servletPath);//传入请求地址
					pass = false;
				}
			}
			
			if(pass){
				chain.doFilter(httpRequest, httpResponse);
				return;	
			} else {
				//String urla = "http://wexin.web.zjc1518.cn/aosuite/wxact/getWxShareCode.jhtml";
				String urla = "https://zjc1518.com/aosuite/wxact/getWxShareCode.jhtml";
				log.info("-----------------------------------------------------------微信分享活动请求被重定向到。【URL：{}】", urla);
				//httpResponse.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxConstant.TEST_WEB_ID + "&redirect_uri="+urla+"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
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
