/**
 * 
 */
package com.api.ApiPublic;

/**
 * @author Administrator
 *
 */
public enum Apiconstant {
	 /** 公用信息提示START */
	 Do_Success("操作成功",1),
	 System_busy("系统繁忙",-1),
	 Validation_fails("验证失败",10000),
	 Save_fails("数据保存失败，请稍后再试",10001),
	 NO_DATA("暂无数据",10002),
	 Do_Fails("操作失败，请稍后再试",10003),
	 Token_Is_Unused("连接已失效，请重新登录",10004),
	 Token_Is_Null("验证失效",10005),
	 Token_Not_Exist("连接失败，请重新登录",10006),
	 Token_Time_Is_Null("连接异常",10007),
	 Condition_Is_Null("查询条件不能为空",10008),
	 Data_Is_Wrong("数据不正确 ",10009),
	 Page_Is_Null("当前页码不能为空",9999),
	 Log_Save_Fails("日志生成失败",8888),
	 Account_Was_Locked("账号已被冻结",7777),
	 Wx_Login_False("微信登录失败，请重新登录",6666),
	 Store_Not_Used("店铺未通过审核或被异常停用，请联系客服",55555),
	 Mobile_Not_User("该手机号尚未开通账号，请先注册会员号。",44444),
	 /** 公用信息信息提示END */
	 
	 /** 登陆信息提示START */
	Username_Not_Exist("账号不存在",10010),
	Username_Is_Lock("账号异常已被锁定",10011),
	ACCOUNT_EXCEPTION("账号异常，请联系客服。",10258),
	Password_Wrong("密码输入错误",10012),
	Login_Status_Is_Used("登陆失败：验证码重复使用",10013),
	 /** 登陆信息提示END */
	
	 /** 检查用户是否购买企业宣传START */
	 Has_Buy_Cp("用户已购买企业宣传",10014),
	 No_Buy_Cp("用户未购买企业宣传",10015),
	 /** 检查用户是否购买企业宣传END */
	 
	 No_Seller("用户不是商家",10016),
	CAN_NOT_TRANSFER("用户不能自己给自己转账",10017),
	/** 注册信息提示START */
	Username_Has_Existed("账号已存在",10018),
	ShareUsername_Not_Exist("分享人不存在",10019),
	Phone_Is_Null("手机号码不能为空",10017),
	Phone_Is_Wrong("手机号码格式错误",10018),
	Password_Is_Null("密码不能为空",10019),
	open_id_Is_Null("open_id不能为空",11019),
	query_Is_Null("查询失败",10020),
	ShareNum_IS_Overflow("分享ID所属合格会员已达上限",10021),
	/** 注册信息提示END */
	 
	/** 修改用户信息提示START */
	Update_userInfo_Lose("修改用户信息失败",10022),
	/** 修改用户信息提示END */
	
	/** 商品管理提示START */
	Store_Not_Exist("店铺不存在",10023),
	Store_Was_Existed("该店铺已存在",10024),
	User_Has_A_Store("你已经开过店铺，不能开通多个店铺",10025),
	Store_Was_Collected("该店铺已被收藏",10026),
	Store_Has_Closed("店铺已被关闭",10027),
	Store_No_Goods("该店铺暂无商品",10028),
	Srore_No_Login("用户未登录",10029),
	Srore_Param_Error("参数错误",10030),
	Order_NO_Cancle("支付状态或订单状态不允许取消",10031),
	Order_NO_Exist("订单不存在",10032),
	Order_UnDel("订单状态不允许删除",10033),
	Goods_Is_Null("该商品不存在",10034),
	GOODS_HAS_EXIST("该商品已存存在",10035),
	Goods_Was_Collected("该商品已被收藏",10036),
	Order_NO_Confirm("该订单不能收货确认",10037),
	Lng_Or_Lat_Is_Null("经纬度不能为空",10038),
	Office_Phone_Is_Null("办公电话不能为空",10039),
	Contacts_Name_Is_Null("联系人不能为空",10040),
	Contacts_Phone_Is_Wrong("联系电话格式错误",10041),
	Bussiness_Or_Foods_Not_All_Null("营业执照和身份证照必须不能为空",10042),
	Cat_Id_Is_Wrong("请选择商品分类",10043),
	Contacts_Phone_Is_Null("联系电话不能为空",10044),
	Store_Name_Is_Null("店铺名字不能为空",10045),
	Office_Phone_Is_Wrong("办公电话格式错误",10046),
	Seller_Info_Save_Fails("商家信息新增失败",10047),
	Seller_Account_Save_Fails("商家账户信息新增失败",10048),
	/** 商品管理提示END */
	
	/** 短信发送START  */
	Sms_Is("消息发送太频繁，请稍后再试",10049),
	/** 短信发送END  */
	
	/** 投诉管理提示START */
	 To_User_Id_Null("被投诉人编号为空",10050),
	 Info_Is_Null("投诉信息不能为空",10051),
	/** 投诉管理提示END */
	
	/** 修改密码START */
	Pass_word_err("旧密码错误",10052),
	/** 修改密码END */
	
	/** 收货地址提示信息START */
	Address_Not_Exist("收货地址不存在",10053),
	Consignee_Is_Null("收货人不能为空",10054),
	ProviceCityDistrict_Is_Wrong("省市区选择不完整",10055),
	Address_Is_Null("详细地址不能为空",10056),
	Address_Was_Passed("最多只能添加10个收货地址",10057),
	/** 收货地址提示信息END */
	 
	 /** 修改支付密码START */
	Code_Not_Exist("验证码错误，请重新输入",10058),
	Code_Is_Null("验证码为空",10059),
	Code_Was_Used("验证码已使用",10060),
	Code_Is_Unused("验证码已失效,请重新获取",10061),
	Pay_Psd_Is_Null("请输入支付密码",10062),
	Pay_Psd_Not_Equal("支付密码输入不一致",10063),
	PayPsd_Equal_LoginPsd("请不要设置为登录密码",10064),
	PayPsd_Equal_NowPayPsd("请不要设置为当前的支付密码",10065),
	Code_Type_Is_Null("短信验证码类型为空",10066),
	Pay_Psd_Error("支付密码错误",10067),
	Pay_Psd_Corrent("支付密码正确",10068),
	/** 修改支付密码END */
	
	/***签到*****/
	Un_Sign_Able("企业用户不能签到",10069),
	User_Was_Signed("您今天已经签到过了",10070),
	Beyond_The_Ceiling("你的输入超出您的余额",10071),
	Below_Minimum ("低于300券不能转出",10072),
	Sum_points_today("您今天转账已经超出限制!",10073),
	/** 账户提示信息 START */
	Account_Not_Exist("账户不存在",10074),
	NON_PERSONAL_ACCOUNT("非本人账号",10075),
	Account_Is_Lock("账户已被冻结",10076),
	KZ_Money_Not_Enough("账户可转余额不足",10077),
	/** 账户提示信息 END */
	
	AUTH_INFORMATION_FAILED("认证信息失败，请使用本人的账号信息认证！",10078),
	ID_NAME_UN_MACTH("请输入店主本人真实姓名",10079),
	ID_NAME_MOBILE_UN_MACTH("电话号码和真实姓名不匹配",10080),
	/** 评论提示信息 START */
	Comment_Is_Null("评论内容不能为空",10081),
	Comment_Not_Exist("您尚未评论该商品",10082),
	Comment_Was_Again_Evl("您已经追加评论过了",10083),
	HAS_COMMENT("您已经评论过了",10084),
	/** 评论提示信息 END */
	
	/**支付限制*/
	Members_close("会员此功能被关闭！",10085),
	/**结算中心*/
	Settlement_center("此类别产品您需要开通结算中心才能购买！",10086),
	/**此类商品已购买！*/
	Goods_purchased("此类商品已购买！",10087),
	/**收货人地址错误*/
	Shipping_address_wrong("收货人地址错误",10088),
	/**商品数据错误*/
	Commodity_data_errors("商品数据错误",10089),
	/**商品已下架*/
	Commodities_have_shelves("商品已下架",10090),
	/**库存不足*/
	Insufficient_inventory("库存不足",10091),
	/**只能后买一个*/
	Can_only_buy("每次只能买一个",10092),
	/**不支持你选的支付方式*/
	Does_not_support("不支持你选的支付方式",10093),
	/**金额错误*/
	Amount_error("金额错误",10094),
	/**账户余额不足*/
	Account_balance_insufficient("账户余额不足",10095),
	/**一天只能下50个订单*/
	fifty("一天只能下50个订单",10096),
	/**订单生成失败*/
	Order_generate_failure("订单生成失败!请重新操作",10097),
	/**积分不能小于1*/
	Points_Was_Wrong("积分不能小于1",10098),
	/**请先配置系统参数*/
	Parameter_Is_Null("请先配置系统参数",10099),
	/**real_name不能为空*/
	REAL_NAME_Is_Null("姓名不能为空",10100),
	/**验证码输入错误*/
	/**订单生成成功*/
	Order_generate_success("订单生成成功!",10101),
	/**订单取消失败*/
	cancel_order_failure("订单取消失败",10102),
	/**订单取消成功*/
	cancel_order_success("订单取消成功",10103),
	/**账户余额不足*/
	Points_Dedution_Fail("券扣除失败",10104),
	ILLEGAL_PARAMETER("非法参数",10296),
	/** 易物券转账start */
	 /**不能给本身转账*/
	ACCOUNT_NOT_MYSELF("不能给自身转账",10105),
	POINTS_MORE_THAN_MAXVALUE("积分转账超过最大额度",10106),
	POINTS_LOW_MINVALUE("积分转账低于最小额度",10107),
	ROUDOM_IS_NULL("随机码不能为空",10108),
	SIGN_IS_NULL("校验包不能为空",10109),
	IN_OR_OUT_FAIL("转出账户转出失败或者转入账户转入失败",10110),
	SAVE_EXCHANGE_FAIL("生成转账订单失败",10111),
	POINTS_LOW_OwnSelf_Money("积分超过自身积分总额",10112),
	POINTS_TOO_MIN("转账金额低于单笔转账金额的最小值",10113),
	OUT_OF_MAX("今日转账金额已超出每日转账上限",10114),
	/** 易物券转账end */
	/** 购买企业宣传START */
	CP_ID_IS_NULL("企业宣传ID不能为空",10115),
	CP_NOT_EXIST("该企业宣传不存在",10116),
	CP_IS_FREE("该企业宣传为免费宣传，无需支付",10117),
	CP_WAS_BUY("该企业宣传已支付",10118),
	POINTS_NOT_ENOUGH("您的可用积分不足，请置换后进行购买",10119),
	SAVE_CP_ORDER_FAIL("生成企业宣传订单失败",10120),
	/** 购买企业宣传end */
	Code_Was_Wrong("验证码输入错误",10121),
	User_Was_Authentication("你已经实名认证,请勿重复认证!",10122),
	ID_CARD_WAS_WRONG("该身份证号码格式错误或者不合法",10123),
	ID_CARD_WAS_EXIST("该身份证号码已存在",10124),
	ID_CARD_HAS_USED("该身份证号码已经申请企业号!",10125),
	GOODS_NOT_AUDIT("该商品未审核或者审核不通过",10126),
	/**结算中心不存在！*/
	Settlement_Center_Not_Exist("结算中心不存在！",10127),
	/**结算中心真实姓名不一致！*/
	Settlement_Center_Name_Not_Equal("结算中心真实姓名不一致！",10128),
	/**返分记录生成失败！*/
	Create_Back_Points_Fails("返分记录生成失败！",10129),
	Wx_No_Bind("该微信没有绑定账号",10130),
	Phone_Is_Not_User("请输入当前用户的手机号",10131),
	User_Has_Not_Audit_Store("你已经开过店铺，请等待系统审核",10132),
	Settle_Owner_Is_Lock("结算中心拥有者账户已被冻结，不能结算",10133),
	Out_Account_Is_Lock("转出账户已被冻结，不能进行转账",10134),
	In_Account_Is_Lock("转入账户已被冻结，不能进行转账",10135),
	User_Handle_Is_Lock("平台维护中，暂停使用该功能，请等候通知",10136),
	User_Account_Is_Lock("您的账户已被冻结，不能进行该操作",10137),
	User_Day_Line_Is_Enough("您当日线下消费额度已达最大额度，请明天再来",10138),
	User_Day_Settle_Is_Enough("您当日倍增结算额度已达最大额度，请明天再来",10140),
	User_Day_Shop_Online_Is_Enough("您当日商城购物额度已达最大额度，请明天再来",10141),
	User_One_Settle_Is_Low("您单笔倍增结算额度低于最小额度，请增加数额",10142),
	User_NOT_MYSELF("不能给自身做倍增结算",10143),
	Order_Not_Cancle("该订单已付券，不能取消，请继续完成订单",10144),
	/**商家app提示*/
	Order_cannot_ship("该订单不能发货",10145),
	Order_was_ship("该订单已发货",10146),
	Store_no_goods("该店铺没有该商品，不能修改库存",10147),
	Order_is_fail("订单支付失败，请稍后再试",10148),
	Sellername_Not_Exist("商家不存在",10149),
	Store_Is_Audit_Or_Close("店铺未通过审核或异常被关闭，请联系客服",10150),
	Store_Account_Is_Lock("商家账号被冻结，请联系客服",10151),
	Pay_Code_Is_Wrong("支付失败，支付码错误，请重新扫码支付",10152),
	Pay_Code_Is_Used("支付失败，支付码已使用，请重新扫码支付",10153),
	Store_Is_Lock_Or_Close("商家账号被冻结或店铺已关闭，请联系客服",10154),
	Goods_Is_Xpt("很抱歉！此款商品为指定购买商品，您的账户不能购买。",10155),
	User_Is_Xpt("很抱歉！您不能转给小平台用户。",10156),
	Efficacy_packet_error("验证密文错误！",10157),
	Store_No_User_Account("您还未开通商家会员号，请先开通会员号再填写委托申请",10158),
	First_Goods_Is_Xpt("很抱歉！特殊用户第一次只能购买指定商品。",10159),
	First_Exchange_Is_Xpt("很抱歉！特殊用户未购买指定商品不能转账。",10160),
	Regular_Members_Not_Transfer("很抱歉！普通用户不能转账。",10161),
	lottery_Number_NO("抽奖失败，抽奖次数不足！",-1),
	lottery_Number_SUCCESS("抽奖成功！",1),
	Voucher_Num_Was_Too_long("很抱歉！该商品仅支持小于商品总价额度的代金券。",10162),
	Goods_Not_Buy("很抱歉！您不能购买该商品。",10163),
	OPEN_ID_FAILS("获取openid失败，请稍后再试",100164),
	Wx_Is_Bind("该微信已绑定账号，不能使用",10165),
	Bank_Is_Bind("绑定失败，该银行卡已绑定",10166),
	Store_Not_Opened("该账号尚未开通店铺",10167),
	checkcode("验证码为空",10168),
	phonecode("手机验证码为空",10169),
	sms_type("短信类型为空",10170),
	checkcode_error("验证码错误",10168),
	phonecode_error("手机验证码错误",10168),
	goods_id_not_null("商品id不能为空",10169),
	count_is_have("账号已经存在",10170),
	Have_to_buy("该商品您已经购买过了,不能重复购买",10171),
	Have_one_goods("该商品只能买一个",10172),
	Pay_points_not_enough("券不够，不能抽奖",10173),
	deduction_points_fail("扣券失败吗，请稍后再试",10174),
	parameter_is_not_null("参数不能为空",10175),
	mobile_openid_id_notMate("手机号和微信号不匹配",10176),
	Can_not_buy("该商品还不能购买",10177),
	IS_NOT_SHARE_HANDLE("助力失败，请勿重复助力或助力自己",10178),
	WX_USERINFO_IS_NULL("微信用户信息获取失败",10179),
	WX_OPENID_IS_NULL("微信openid获取失败",10180),
	goods_one("每次只能提交一种商品",10181),
	NO_ACTIVITY("暂无活动",10182),
	Order_Has_Payed("暂无活动",10183),
	goods_num_enough("你已超出该商品每单的购买数量，请重新下单",10184),
	goods_times_enough("你已超出该商品每日每人的限购次数，请明日再来",10185),
	order_cannot_create("支付方式错误，不能下单",10186),
	wx_cannot_buy("微信端不能购买该商品，请下载app购买",10187),
	openid_different("手机绑定的不是该微信号",10189),
	openid_bind_success("微信号与手机号绑定成功",10190),
	openid_bind_false("微信号与手机号绑定失败",10191),
	pay_psd_is_null("请先设置支付密码",10192),
	Goods_Cannot_Buy("连续六天在“中军创1号店”购买任意一款商品，才能购买本商品",10193),
	Order_Cannot_Reminder("很抱歉，该订单已催单成功，请24小时候再试",10194),
	Order_Cannot_Repeat_Reminder("很抱歉，该订单最多催单3次，请耐心等待商家发货",10195),
	Order_Cannot_Chargeback("很抱歉，该订单已发起退单，请耐心等待商家处理",10196),
	PinTuan_Order_Cannot_Chargeback("很抱歉，拼团订单不能退单",10197),
	Vip_Order_Cannot_Chargeback("很抱歉，vip商品订单不能退单",10198),
	Prompt_Order_Cannot_Chargeback("很抱歉，限时抢购订单不能退单",10199),
	Order_Cannot_Reminder_If_Chargeback("很抱歉，该订单已发起退单，不能再次催单",10200),
	LiaoDou_Too_Many("了豆兑换失败，最多兑换10000了豆",10201),
	LiaoDou_Only_One_Times("了豆兑换失败，能且只能兑换一次了豆",10202),
	LiaoDou_Change_Is_Fail("了豆兑换失败，请确认是否创建了豆账户",10203),
	LiaoDou_Too_Manys("易物券转账失败，最多转账100000券",10204),
	LiaoDou_Too_Manyb("易物券转slb币失败，最多转账100000券",10205),
	 ;
	
	 private String name ;
     private int index ;
     
     private Apiconstant( String name , int index ){
        this.name = name ;
        this.index = index ;
     }
     
     public String getName() {
        return name;
     }
     public void setName(String name) {
        this.name = name;
     }
     public int getIndex() {
        return index;
     }
     public void setIndex(int index) {
        this.index = index;
     }
}
