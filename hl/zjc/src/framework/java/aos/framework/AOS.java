package aos.framework;

import com.api.unionpay.common.CertUtil;

import aos.framework.core.server.AOSServer;

/**
 * 以内置Jetty模式启动Web应用
 * 
 * <p>
 * 提示:请以debug as java application的方式启动
 *
 * @author xiongchun
 */
public class AOS {
 
	/**
	 * 启动内置服务器
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		AOSServer aosServer = new AOSServer();
		aosServer.setWebContext("/aosuite");
		aosServer.setPort(10010);//端口号
		aosServer.start();
		CertUtil.contextInitialized();
	}
}
