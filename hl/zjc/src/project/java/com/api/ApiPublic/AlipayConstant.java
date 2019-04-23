/**
 * 
 */
package com.api.ApiPublic;

/**
 * 支付接口常量
 * 
 * @author Administrator
 *
 */
public class AlipayConstant {
	public static final String METHOD = "alipay.trade.app.pay";
	public static final String FORMATE = "JSON";
	public static final String SIGNTYPE = "RSA2";
	public static final String VERSION = "1.0";
	// 支付宝重要参数  
   /* public final static String APP_ID = "2017032806444271";  
    public final static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDcPHfO7SKWz7ftZO2MHvK175shsZPfv7MaL1CVlcDg+ujPmbDTudVk14i2o0LRedg+NEH6B5aoP41aUIMhOhEryUxTWdQhc3tXK/rtojKL/kaRjsj/xZGGB+Y0q/a0Ip2c9UfowfTDiJmH8fuVeVSt/+Bnxp/JAU6TP5I4wNYxRXAzPRNAXv/2FR/cWot6y9Pao4QrUGooRj4soON0LrUM5ya82H66GCKIKvvuPLGhr0Zhpq+REi3En7Vt92Q+05VA4Q7tMSfAYzua2KONCvncSEW0VEjc00s5kfZ0EtLG9sd/lS5d2Sl0z0pLR3tcJpH3p3iwUezzGj3M4eF6y4IZAgMBAAECggEBALbPeqPJM2z7/zCPFp+5EL70pVDSlmykM4cIv1trcy9NFvM8IAuftgJ+5ny0CELZYSZBOoLQyb3fAvZ03mQ2TwqJjVGivvMFaNxzJVcby9louCsa2r10wdb/t5cBJ0dfdHVVJNuQJBwqx0aRFsR8ck38l8mMlhXDf/W6TyXM6/74man9GfbQQdYUVzJGIIboQcf5lFD2KnIfxkM71HJgTbutx9Z43vQgVndSMlMf1u1cHg5yQB/M8KWwptw1Bf+anjuCQUY9FBQxFdau+EDYXDRKZELUvXtCvyVSFi69C2+aVsFhTju0YpvCJUH5kWvJZTf++9M6XIQkvKpdRCwNYgECgYEA/RdB7yfy6kUy2WV6tfPQVyUfdxtIKCxKs3BhWW26q88p1xVcxV+zHsWD7858xnap4Ye4eSvxBnVpq7XyTGPquvDjQtBA/735+msq5QVixMBJpKW7Usoxfq+ZMsNlj+bIMucRllYd2PZXOZKdbiiPdrAtHZElCcmPzrfbmEXIFwsCgYEA3sSIX8kDnHAGzkOJTtcN0WnxPrUHExzDQfywq4oezXn+83lmY+iNn6iPXA+zrHYTMcCOEZEKL84mnnCChdaKJJlTWLj0jNJ/8j3PtPfq7pMUMsOjMyLk/Nb959PS74p4VrlhL6/GndIF6ia/8jiVhUlqc25uUbN5Q1cpTftn8esCgYA/Cj/+FfFHsRzNUZ2THLvUOQbxX/ZFK2Mf9uoIoHBSwzMkw8lr0D6Jd6E5ZjFmLcB60Jd+dxd+CFjd0GAd7vVUwxOYXM8T5HoTigYRLZpAb7ToMe/K21oguBRstIbjpRRQuYWD8MdWd2WajY5xFeFGKvPR3YlgOrh7QQiNkTQZqQKBgHh2EESzn2dF0PhS3voqIoe+YT0GiUfVguS47xQjwVpdgPdKC4s1IvhWHweqWf+FbgW2WVy4cKi/MnT05C/ZfqQh4UcV5PANmDB3Kk1yzrCqCf8VebfaQ7dRzsVA70ynNUG4RwyRYbbP7nDa+WrTHE+SDqVS7wbxJT678FMCAwh3AoGADtAsN6OAkHbN7CN/MGrAnw2NtsjBkTDucwvwWQlG6eDc/YcUvBXby/DHb0PVcy8/fEB5hHf3kIUCYSHZNbJTCL/nOpL2hwcktrB7QHhBv9i6mAxbXOVexcJuBj51xt/AWPOdnJ3ZJXkxkoMHVXK1af5gEW2uTXQe8DhKmcNCrT8=";  
    public final static String CHARSET = "utf-8";  
    public final static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhL2FqkOJ6DL3sfSO8a9ssEcn3p/enEMNW8HA8SaGceuX8gkgMuLTLKk1a4wPt8e5Bz80DXgSfGodiJBZ4E9mnEY+QuRguFi186hdvaxFpKrxUnZA2e9GWOjcgiFhIozw5aB3P734Nksz6bxmwZbdMBTk14XfKJJVRaMqJXR/zCY0+p5bIFX/2tUfCXDkj6e9c+wrRndv1nktqq/i84owlsSGvL1OiAv/SPTkQZk9LImv2CeoNLgNfJCxn7CWHzVqelfSBTjCO7iLs2BB4zOpsE6t2NNWlonOxHVKygsPeYm1kPNqiGxjMCY6ftUZ1ECdoKwiTcXF6D6HKEJpy5QndwIDAQAB";
	public static final String PRODUCTCODE = "QUICK_MSECURITY_PAY";  
	public static final String SELLER_ID = "2088521416084920";  
	public static final String EXPIRE_TIME = "15d";
	public final static String IOS_ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTtzycZQe6a0QLkecMR8+RChaPA2V7oUJiApSJcnt2iz9d8EjCwAGkzwROL9LEbooAdYoqspCV24//ssHTL7cJhxSdSt1vaCE2AlFhKvQX9Af3XShnoTYxWzlpiRIy/HI7Ta65q6dQKTQkf8vIdLhbVCx1p/TDJgLAa5EhnJnsiQIDAQAB";*/
	public final static String APP_ID = "2019011662994900";
	public final static String APP_PRIVATE_KEY ="MIIEugIBADANBgkqhkiG9w0BAQEFAASCBKQwggSgAgEAAoIBAQDD3esSEGriIQWtiMHxixGCvCBylQmY1Fe611E9hT6e17XV8Cb534LXtF/cAaibYtD1zVIEniJAvNmV/MkbATegnI+1TDaKyNfiTCWAPBp1t/Yew0LubdBpbSoFB1mK83Ltfvpa9kyJd1eEW8USHMUrRLDYJLfrF7DXAyjfSvzGbrBDk4lWPdkyi/Uge3F88xFMa1R465mDTAK1529+Kxt+0/yfLgb1ea2yctF6ywC1QywCdc4vJtw0MeUxiJATDMSmJRImat4rMFKs8iYvO8YK2eGnS2tFtY3/fF9ZBeGKKScjUVmKSPFOcvrOv7NYjLUGwweoVL5xf1NdKgqKBo5RAgMBAAECggEAGDCqPZrleHQXnW0MduZ0DS9q2nvpqYRgKWU6VVzFdRyzCm8UgTvHjxhp1mQmDLrRj+oBvSxCkrhALzP/KmSmGCH95ag5/D3926cH4qSJqAxGS1pWQ9gwKzUeQWy89FpI0WgjR2Sv4zJoDqK+3hPLjiJ3rNosFM7iOkvbmmhKe78skVvqk/uvtL5Ty1AsgqHeNM/CDOzaFPdbWUG0ry0iQVibR0dQwXW0VLZhA31n03V8GXQZLCe8PFzBfHh0YcgrlBFMsl0jN0h4yKXHNv2E/w/T5x2EWKXCDQOj5zgMmc0QEblJS6yAjrXccXsaBIFtpOit0MtYE8DSbMdZmL65IQKBgQDn727eyD3998POgRvDlvBfhhZGQvh6+j/00swSElvQqYyIQ6VbuwyIf8BQeUoYpq1aZcEzY0rtepQbS56arlrNk3Fz0uOkIAYHBp++/cx4tnkh5KI43ynndH39Rrzj0rIGu+XdjhFRFLltauGU7GfpwsqMvIi7/VzwaXmv1/W6vQKBgQDYMHN8cZa7xwVdyzYwPr9tDnIwiZwcAsVSwRrMJ+C0ta/O8eBXc6i9YWvrBz8kH5+C0+wf0TUMhBHem4aeWDdUn0soTMPXCJThiGI4vQTnmCm/mqNEjNACTdWNsP9t4CZqQ1IWDFqprPcgvGDSt2/PzzA/61FDkskP+MGsvI5lJQKBgFAhRpqtb814diu4K1Ftqmo2NyfbJ2HFZ6c71MlBknsKuMSzKkQA4ZmfGWA0J0dQ/n9GvHL2kQJu4RB1Fby3FCQGFqI/b+paLfrrbPFdWfEyibMN/IXNg/q1InoIoGXNezgcXpQm6xCqKk4FKR58doIqjDQHRu474IomxqXb5Me5An9rP54LHk6o2RLQdP9s77QHZiM/8QSA4N8DLskcfqv6+ic9gsDIoZgUW0AuWGn6gbWgMYHyRtcKXnp+9KjT5O+OC3g9hdeh1aAGQqJ3g03rlpEffJeCaljGy9YldKlC2PA1xy/xwC85/ZSbqNhfsKDHdwF4tEScQTV9tupdxyzdAoGADDQcC+j4e1yVHxSuFEUPws4vI+tfsPR1/p3Onm8JcIQ+SY0MFHQN3kle9hzSWC4j1XF7FOSxF4oYauGw3lSMGBMVEk5kE/ceAWGW19q6bCL9xT3ReCsEVKESlnAXjDNUKqg+Y0i8HNmlUNYXfnt0bAvE7QHX25I0fdAnQqu45Wo=";
	public final static String CHARSET = "utf-8";
	public final static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwbNfFvr8ZOpIVoJxEtmArjiMX693ihSEn2QYr6VngpYI6nhScCRMlEBh2hSvwyd9lDmfFSl1WB7hEmgGRNPXqr1lol3QNGi97MZoumKfuYcY9ETWNsF92evfa7xezs/rsiyqbVD3fjr0mOro7nbq+cFxvg2n37Lo5UDDIgbQ/t6wQzLGzLgfNcPbUsSvpDjA46frdY8fThUjmeLu/7ex6mmlDa3vU7YZkrjLqd1+9Ti+caQ8eNe2YOPuKOuROXPKhAlRKELKg1IuOQnuHD40P3WzdpuzmWRzj/dwWuv5YZfkuDF1jlL9ilh2dLk84iu0JM4SG37p7SHuSKCl5cje3QIDAQAB";
	public final static String PRODUCTCODE = "QUICK_MSECURITY_PAY";
	public final static String SELLER_ID = "2088231456445110";
	public final static String EXPIRE_TIME = "30m";
	public final static String IOS_ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTtzycZQe6a0QLkecMR8+RChaPA2V7oUJiApSJcnt2iz9d8EjCwAGkzwROL9LEbooAdYoqspCV24//ssHTL7cJhxSdSt1vaCE2AlFhKvQX9Af3XShnoTYxWzlpiRIy/HI7Ta65q6dQKTQkf8vIdLhbVCx1p/TDJgLAa5EhnJnsiQIDAQAB";

    /**
     * 未支付
     */
    public static Integer NO_PAY = 0;
    /**
     * 充值成功
     */
    public static Integer PAY_SUCCEESS = 1;
    /**
     * 充值失败
     */
    public static Integer PAY_LOSE = 3;
    
    /**
     * 支付方式code
     */
    public static String PAY_CODE = "alipay";
    
    /**
     * 支付方式name
     */
    public static String PAY_NAME = "支付宝支付";
    
    /**
     * 支付方式code
     */
    public static String MIXED_PAY_CODE = "mixed_payment";
    
    /**
     * 支付方式name
     */
    public static String MIXED_PAY_NAME = "混合支付-支付宝";
}
