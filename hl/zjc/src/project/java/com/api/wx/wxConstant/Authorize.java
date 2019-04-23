/**
 * 
 */
package com.api.wx.wxConstant;

/**
 * @author Administrator
 *
 */

/*public class OAuth_Token  
{  
    public OAuth_Token()  
    {  
        //  
        //TODO: 在此处添加构造函数逻辑  
        //  
    }  
    //access_token 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同  
    //expires_in access_token接口调用凭证超时时间，单位（秒）  
    //refresh_token 用户刷新access_token  
    //openid 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID  
    //scope 用户授权的作用域，使用逗号（,）分隔  
    public String access_token;  
    public String expires_in;  
    public String refresh_token;  
    public String openid;  
    public String scope;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}  
    
    
}  
*/

public class Authorize {
	
	private String errcode;  
    private String errmsg;  
    private String access_token;  
    private String expires_in;  
    private String refresh_token;  
    private String openid;  
    private String scope;
    private String unionid;
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
    
}
