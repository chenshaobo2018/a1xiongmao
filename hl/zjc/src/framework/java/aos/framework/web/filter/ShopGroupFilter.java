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

import aos.framework.core.utils.AOSUtils;

/**
 * @author Administrator
 *
 */
public class ShopGroupFilter  implements Filter {
	private static Logger log = LoggerFactory.getLogger(ShopGroupFilter.class);

	/**
	 * 初始化
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String servletPath = httpRequest.getServletPath();
		if(StringUtils.equalsIgnoreCase(servletPath, "/shopGroup/activity/v1/initSpellBuy.jhtml")
		|| StringUtils.equalsIgnoreCase(servletPath, "/shopGroup/activity/v1/initSpellShare.jhtml")
		|| StringUtils.equalsIgnoreCase(servletPath, "/shopGroup/activity/v1/initSpellGoBuy.jhtml")
		|| StringUtils.equalsIgnoreCase(servletPath, "/shopGroup/activity/v1/initSetAddress.jhtml")
		|| StringUtils.equalsIgnoreCase(servletPath, "/shopGroup/activity/v1/initGoBuy.jhtml")
		){
			boolean pass = true;
			log.info("-----------------------------------------------------------0元购接口调用逻辑开始------------------------：" + servletPath);
			if(servletPath.contains("/shopGroup/activity/v1/initSpellGoBuy.jhtml")){
				log.info("======----------------0元购goodsID：-------------------=====:"+ httpRequest.getParameter("goods_id"));
				if(AOSUtils.isEmpty(httpRequest.getSession().getAttribute("openid"))){
					httpRequest.getSession().setAttribute("type", "spellGoBuy");//跳转0元购下单界面
					httpRequest.getSession().setAttribute("goods_id", httpRequest.getParameter("goods_id"));
					httpRequest.getSession().setAttribute("pin_order_id", httpRequest.getParameter("pin_order_id"));
					httpRequest.getSession().setAttribute("methodStr", servletPath);//传入请求地址
					pass = false;
				}
			}else if(servletPath.contains("/shopGroup/activity/v1/initGoBuy.jhtml")){
				log.info("======----------------0元购goodsID：-------------------=====:"+ httpRequest.getParameter("goods_id"));
				if(AOSUtils.isEmpty(httpRequest.getSession().getAttribute("openid"))){
					httpRequest.getSession().setAttribute("type", "normalGoBuy");//跳转正常下单界面
					httpRequest.getSession().setAttribute("goods_id", httpRequest.getParameter("goods_id"));
					httpRequest.getSession().setAttribute("methodStr", servletPath);//传入请求地址
					pass = false;
				}
			}
			if(pass){
				chain.doFilter(httpRequest, httpResponse);
				return;
			} else {
//				String urla = "http://wexin.web.zjc1518.cn/aosuite/shopGroup/getShopGroupCode.jhtml";
				String urla = "https://zjc1518.com/aosuite/shopGroup/getShopGroupCode.jhtml";
				log.info("-----------------------------------------------------------0元购活动请求被重定向到。【URL：{}】", urla);
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
