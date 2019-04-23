<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="系统配置" base="http" lib="ext">
<aos:body>
</aos:body>
</aos:html>
<aos:onready>
	<aos:viewport layout="border">
		<aos:tabpanel id="tabpanel" region="center" bodyBorder="0 0 0 0">
			<aos:tab title="会员配置" id="_tab_org">
				<aos:docked forceBoder="0 0 1 0">
					<aos:dockeditem onclick="#w_zjcUser_us.show();" text="新增会员配置"
						icon="add.png" />
				</aos:docked>
				<aos:formpanel id="_tab_orgs" width="400" layout="column"
					labelWidth="200" onrender="getUser_Add">
					<aos:textfield name="menber_id" fieldLabel="注册跳段"
						allowBlank="false" maxLength="50" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="give_barter" fieldLabel="注册赠送券"
						allowBlank="false" maxLength="50" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="start_time" fieldLabel="注册赠送开始日期"
						editable="false" width="700" readOnly="true" padding="15 0 15 0" />
					<aos:textfield name="end_time" fieldLabel="注册赠送截止日期"
						editable="false" width="700" readOnly="true" padding="15 0 15 0" />
					<%-- <aos:textareafield name="menber_spending"  fieldLabel="限制会员支出" allowBlank="false"  readOnly="true"  width="700" padding="15 0 15 0"/>
					<aos:textfield name="membership_amount_limit"  fieldLabel="直推会员总数量限制" allowBlank="false" maxLength="80" readOnly="true"  width="700" padding="15 0 15 0"/>
					<aos:textfield name="barter_ coupo_rate"  fieldLabel="充值易物券兑换比例" allowBlank="false" maxLength="50" readOnly="true"  width="700" padding="15 0 15 0"/>
					<aos:textfield name="top_up_barter_volume"  fieldLabel="充值易物券返还比例" allowBlank="false" maxLength="50" readOnly="true"  width="700" padding="15 0 15 0"/>
					<aos:textfield name="top_up_barter_volume_minimum"  fieldLabel="充值易物券返还最小值" allowBlank="false" maxLength="50" readOnly="true"  width="700" padding="15 0 15 0"/>
					<aos:textfield name="offline_top_up_barter_volume"  fieldLabel="线下充值易物券返还比例" allowBlank="false" maxLength="50" readOnly="true"  width="700" padding="15 0 15 0"/> --%>
					<%-- <aos:textfield name="sum_settlement"  fieldLabel="当日结算最大值" allowBlank="false" maxLength="11"  readOnly="true"  width="700" padding="15 0 15 0"/> --%>
					<aos:textfield name="day_online_shop_max" fieldLabel="当日商城购物最大值"
						allowBlank="false" maxLength="11" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="day_line_shop_max" fieldLabel="当日线下购物最大值"
						allowBlank="false" readOnly="true" width="700" padding="15 0 15 0" />
					<aos:textfield name="sum_points_today" fieldLabel="当日转出最大值"
						allowBlank="false" maxLength="11" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="is_lock" fieldLabel="是否禁用会员功能" readOnly="true"
						allowBlank="false" width="700" padding="15 0 15 0" />
					<aos:textfield name="member_comm_percentage_one" fieldLabel="一级会员提成比例" readOnly="true"
						allowBlank="false" width="700" padding="15 0 15 0" />
					<aos:textfield name="member_comm_percentage_two" fieldLabel="二级会员提成比例" readOnly="true"
						allowBlank="false" width="700" padding="15 0 15 0" />
					<aos:textfield name="qualified_menber" fieldLabel="合格会员条件" readOnly="true"
						allowBlank="false" width="700" padding="15 0 15 0" />
				</aos:formpanel>
			</aos:tab>

			<aos:tab title="倍增配置" id="_tab_param">
				<aos:docked forceBoder="0 0 1 0">
					<aos:dockeditem onclick="#_tab_param_d.show();" text="新增倍增配置"
						icon="add.png" />
				</aos:docked>
				<aos:formpanel id="_tab_params" width="400" layout="column"
					labelWidth="200" onrender="getMemberParameter">
					<aos:textfield name="default_multiplier" fieldLabel="默认倍增倍数"
						allowBlank="false" maxLength="80" readOnly="true" width="700"
						padding="15 0 15 0" />
					<%-- <aos:textfield name="timed_multiplication"  fieldLabel="限时倍增倍数" allowBlank="false" maxLength="50" readOnly="true" width="700" padding="15 0 15 0"/>
					<aos:datefield name="start_time" fieldLabel="限时倍增倍数开始时间" value="2015-03-10" format="Y-m-d h:m:s" editable="false"  readOnly="true" width="700" padding="15 0 15 0"/>
					<aos:datefield name="end_time" fieldLabel="限时倍增倍数截止时间" format="Y-m-d h:m:s" editable="false" maxValue="${maxValue}" disabledDaysText="测试" minValue="${minValue}" readOnly="true" width="700" padding="15 0 15 0"/> --%>
					<aos:textfield name="settlement_center_commission"
						fieldLabel="结算中心提成比例" allowBlank="false" maxLength="50"
						readOnly="true" width="700" padding="15 0 15 0" />
					<aos:textfield name="points_other_min" fieldLabel="单笔倍增最小值"
						allowBlank="false" maxLength="50" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="sum_settlement" fieldLabel="当日倍增最大值"
						allowBlank="false" maxLength="50" readOnly="true" width="700"
						padding="15 0 15 0" />
					<%-- <aos:textfield name="special_merchants_multiplication_id"  fieldLabel="特殊商家结算中心" allowBlank="false" maxLength="80" readOnly="true" width="700" padding="15 0 15 0"/> --%>
					<%-- <aos:textfield name="special_merchants_multiplication"  fieldLabel="特殊商家倍增倍数" allowBlank="false" maxLength="50" readOnly="true" width="700" padding="15 0 15 0"/> --%>
					<aos:textfield name="offline_shopping_commission_ratio"
						fieldLabel="终端机结算比例" allowBlank="false" maxLength="80"
						readOnly="true" width="700" padding="15 0 15 0" />
					<aos:textfield name="offline_shopping_multiplication_rate"
						fieldLabel="线下购物赠送比例" allowBlank="false" maxLength="80"
						readOnly="true" width="700" padding="15 0 15 0" />
				</aos:formpanel>
			</aos:tab>

			<aos:tab title="其他配置" id="_tab_org3">
				<aos:docked forceBoder="0 0 1 0">
					<aos:dockeditem onclick="#_tab_org3_d.show();" text="新增其他配置"
						icon="add.png" />
				</aos:docked>
				<aos:formpanel id="g_org3" region="center" width="400"
					layout="column" labelWidth="200" onrender="getZjcMemberOther">
					<aos:textfield name="consumption_coupons" fieldLabel="可用券比例"
						allowBlank="false" maxLength="80" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="convertible_barter" fieldLabel="可转券比例"
						allowBlank="false" maxLength="80" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="merchants_quantity_limit" fieldLabel="商品数量限制"
						allowBlank="false" maxLength="80" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="barter_voucher_transfer_fees"
						fieldLabel="转账手续费" allowBlank="false" maxLength="80"
						readOnly="true" width="700" padding="15 0 15 0" />
					<aos:textfield name="rebate_days_delay" fieldLabel="普通商家赠送延迟天数"
						allowBlank="false" maxLength="80" width="700" readOnly="true"
						padding="15 0 15 0" />
							<aos:textfield name="order_receiving_days_overtime"
						fieldLabel="订单超时收货延迟天数" allowBlank="false" maxLength="80"
						readOnly="true" width="700" padding="15 0 15 0" />
					<aos:textfield name="special_rebate_delay_days"
						fieldLabel="特殊商家赠送延迟天数" allowBlank="false" maxLength="80"
						readOnly="true" width="700" padding="15 0 15 0" />
					<aos:textfield name="signstep" fieldLabel="每日签到赠送劵"
						allowBlank="false" maxLength="80" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="transfer_minimum" fieldLabel="单笔转出最小值"
						allowBlank="false" maxLength="80" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="maximum_transfer" fieldLabel="单笔转出最大值"
						allowBlank="false" maxLength="80" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="shop_drop_commission_rate"
						fieldLabel="商家一滴酒提成比例" allowBlank="false" maxLength="80"
						readOnly="true" width="700" padding="15 0 15 0" />
					<aos:textfield name="shop_drop_commission_limit_days" fieldLabel="商家一滴酒提成开始日期"
						allowBlank="false" maxLength="80" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="user_drop_commission_rate" fieldLabel="会员一滴酒提成比例"
						allowBlank="false" maxLength="80" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="user_drop_commission_limit_days" fieldLabel="会议一滴酒提成开始日期"
						allowBlank="false" maxLength="80" readOnly="true" width="700"
						padding="15 0 15 0" />
					<aos:textfield name="return_time" fieldLabel="自然消费赠送时间点"
						allowBlank="false" maxLength="80" readOnly="true" width="700"
						padding="15 0 15 0" />
					<%-- <aos:textfield name="maximum_fee"  fieldLabel="手续费最大值" allowBlank="false" maxLength="80" readOnly="true" width="700" padding="15 0 15 0"/> --%>
					<%-- <aos:textfield name="invalid_number"  fieldLabel="易物担保未确认失效天数" allowBlank="false" maxLength="80" readOnly="true"/> --%>
					<%-- <aos:textfield name="barter_voucher_transfer_fees"  fieldLabel="易物券转账手续费" allowBlank="false" maxLength="80" readOnly="true"/> --%>
					<%-- <aos:textfield name="rebate_days_delay"  fieldLabel="普通商品返利延迟天数" allowBlank="false" maxLength="80" readOnly="true"/>
					<aos:textfield name="special_rebate_delay_days"  fieldLabel="特殊商品返利延迟天数" allowBlank="false" maxLength="80" readOnly="true"/> --%>
					<%-- <aos:textfield name="order_receiving_days_overtime"  fieldLabel="订单超时收货天数" allowBlank="false" maxLength="80" readOnly="true" width="700" padding="15 0 15 0"/> --%>
					<%-- <aos:textfield name="minoutpoint"  fieldLabel="转出签到赠送易物劵下限" allowBlank="false" maxLength="80" readOnly="true" width="700" padding="15 0 15 0"/> --%>
				</aos:formpanel>
			</aos:tab>
		</aos:tabpanel>

		<aos:window id="w_zjcUser_us" title="新增会员配置"
			onshow="getNewMemberParameter()">
			<aos:formpanel id="f_ad_position" width="800" layout="anchor"
				labelWidth="200">
				<aos:textfield name="menber_id" id="menber_id" fieldLabel="注册跳段"
					allowBlank="false" maxLength="50" emptyText="跳段ID只能设0-9999之间的整数" />
				<aos:textfield name="give_barter" id="give_barter"
					fieldLabel="注册赠送券" allowBlank="false" maxLength="50"
					emptyText="注册赠送劵只能是0-9999之间的整数" />
				<aos:datetimefield name="start_time" fieldLabel="注册赠送开始日期"
					editable="false" format="Y-m-d H:i:s" maxValue="${maxValue}"
					disabledDaysText="测试" columnWidth="0.33" />
				<aos:datetimefield name="end_time" fieldLabel="注册赠送截止日期"
					editable="false" format="Y-m-d h:i:s" disabledDaysText="测试"
					minValue="${minValue}" columnWidth="0.33" />
				<%-- <aos:textfield name="barter_coupo_rate" id="barter_coupo_rate"  fieldLabel="充值易物券兑换比例" allowBlank="false" maxLength="50" />
					<aos:textfield name="top_up_barter_volume"  fieldLabel="充值易物券返还比例" allowBlank="false" maxLength="50" />
					<aos:textfield name="offline_top_up_barter_volume"  fieldLabel="线下充值易物券返还比例" allowBlank="false" maxLength="50" />
					<aos:textfield name="top_up_barter_volume_minimum"  fieldLabel="充值易物券返还最小值" allowBlank="false" maxLength="50" />
					<aos:textareafield name="menber_spending" id="menber_spending"  fieldLabel="限制会员支出" allowBlank="false" maxLength="950"/>
					<aos:textfield name="membership_amount_limit"  fieldLabel="直推会员总数量限制" allowBlank="false" maxLength="80" /> --%>
				<aos:textfield name="day_online_shop_max" id="day_online_shop_max"
					fieldLabel="当日商城购物最大值" allowBlank="false" maxLength="11" />
				<aos:textfield name="day_line_shop_max" id="day_line_shop_max"
					fieldLabel="当日线下购物最大值" allowBlank="false" />
				<aos:textfield name="sum_points_today" id="sum_points_today"
					fieldLabel="当日转出最大值" allowBlank="false" maxLength="11" />
				<aos:radioboxgroup fieldLabel="是否禁用会员功能" columns="3"
					columnWidth="0.53">
					<aos:radiobox name="is_lock" boxLabel="是" inputValue="1" />
					<aos:radiobox name="is_lock" boxLabel="否" inputValue="0" />
				</aos:radioboxgroup>
				<aos:textfield name="member_comm_percentage_one" fieldLabel="一级会员提成比例" 
					allowBlank="false" id="member_comm_percentage_one" maxLength="11" />
				<aos:textfield name="member_comm_percentage_two" fieldLabel="二级会员提成比例" 
					allowBlank="false" id="member_comm_percentage_two" maxLength="11" />
				<aos:textfield name="qualified_menber" fieldLabel="合格会员条件" 
					allowBlank="false" id="qualified_menber" maxLength="11" />
				<%-- <aos:combobox name="is_lock" id="is_lock" fieldLabel="是否禁用会员功能" dicField="is_lock" allowBlank="false"/> --%>
				<%-- <aos:textfield name="member_comm_percentage_one" id="member_comm_percentage_one" fieldLabel="会员提成比例设置一(合格会员)" allowBlank="false" maxLength="50" />
					<aos:textfield name="member_comm_percentage_ones" id="member_comm_percentage_ones"   fieldLabel="会员提成比例设置一(提出比例)" allowBlank="false" maxLength="50" />
					<aos:textfield name="member_comm_percentage_two" id="member_comm_percentage_two" fieldLabel="会员提成比例设置二(合格会员)" allowBlank="false" maxLength="50" />
					<aos:textfield name="member_comm_percentage_twos"  id="member_comm_percentage_twos" fieldLabel="会员提成比例设置二(提出比例)" allowBlank="false" maxLength="50" />
					<aos:textfield name="member_comm_percentage_three" id="member_comm_percentage_three" fieldLabel="会员提成比例设置三(合格会员)" allowBlank="false" maxLength="50" />
					<aos:textfield name="member_comm_percentage_threes" id="member_comm_percentage_threes"  fieldLabel="会员提成比例设置三(提出比例)" allowBlank="false" maxLength="50" /> --%>
				<%-- <aos:radioboxgroup fieldLabel="会员返佣模式" columns="3"  columnWidth="0.53">
						<aos:radiobox name="member_commission_model"  boxLabel="直接返佣" inputValue="0" />
						<aos:radiobox name="member_commission_model"  boxLabel="隔代返佣" inputValue="1" />
						<aos:radiobox name="member_commission_model"  boxLabel="双重返佣" inputValue="2" />
					</aos:radioboxgroup> --%>
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="f_ad_position_submit" text="保存"
					icon="ok.png" />
				<aos:dockeditem onclick="#w_zjcUser_us.hide();" text="关闭"
					icon="close.png" />
			</aos:docked>
		</aos:window>

		<aos:window title="新增倍增配置" id="_tab_param_d"
			onshow="getNewMemberMultiplication()">
			<aos:formpanel id="f_tab_param_d" width="800" layout="anchor"
				labelWidth="200">
				<aos:textfield name="default_multiplier" fieldLabel="默认倍增倍数"
					allowBlank="false" maxLength="80" emptyText="如1-1.25（整数或小数）" />
				<%-- <aos:textfield name="timed_multiplication"  fieldLabel="限时倍增倍数" allowBlank="false" maxLength="50" emptyText="如1-5（整数或小数）"/>
					<aos:datetimefield name="start_time" id="start_time" fieldLabel="限时倍增开始时间" value="2015-03-10" format="Y-m-d h:m:s" editable="false" columnWidth="0.33" />
					<aos:datetimefield name="end_time" id="end_time" fieldLabel="限时倍增截止时间" format="Y-m-d h:m:s" editable="false" maxValue="${maxValue}" disabledDaysText="测试" minValue="${minValue}"
							columnWidth="0.33" /> --%>
				<aos:textfield name="settlement_center_commission"
					fieldLabel="结算中心提成比例" allowBlank="false" maxLength="50" emptyText="如0-0.2（百分比）" />
				<aos:textfield name="points_other_min" fieldLabel="单笔倍增最小值"
					allowBlank="false" maxLength="50" emptyText="如0-10000（整数）" />
				<aos:textfield name="sum_settlement" fieldLabel="当日倍增最大值"
					allowBlank="false" maxLength="50" emptyText="如0-10000（整数）" />
				<%-- <aos:textfield name="special_merchants_multiplication_id"  fieldLabel="特殊商家结算中心" allowBlank="false" maxLength="80" emptyText="输入会员ID"/> --%>
				<%-- <aos:textfield name="special_merchants_multiplication"  fieldLabel="特殊商家倍增倍数" allowBlank="false" maxLength="50" emptyText="如1-5（整数或小数）"/> --%>
				<aos:textfield name="offline_shopping_commission_ratio"
					fieldLabel="终端机结算比例" allowBlank="false" maxLength="80"
					emptyText="如0-0.3（小数）" />
				<aos:textfield name="offline_shopping_multiplication_rate"
					fieldLabel="线下购物赠送比例" allowBlank="false" maxLength="80"
					emptyText="如0-1（可以小数）" />
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="f_tab_param_d_submit" text="保存"
					icon="ok.png" />
				<aos:dockeditem onclick="#_tab_param_d.hide();" text="关闭"
					icon="close.png" />
			</aos:docked>
		</aos:window>

		<aos:window title="新增其他配置" id="_tab_org3_d"
			onshow="getNewMemberOther()">
			<aos:formpanel id="f_tab_org3_d" width="800" layout="anchor"
				labelWidth="200">
				<aos:textfield name="consumption_coupons" fieldLabel="可用券比例"
					emptyText="如0-1（百分比）" allowBlank="false" maxLength="50" />
				<aos:textfield name="convertible_barter" fieldLabel="可转券比例"
					emptyText="如0-1（百分比）" allowBlank="false" maxLength="50" />
				<aos:textfield name="merchants_quantity_limit" fieldLabel="商品数量限制"
					emptyText="如0-100" allowBlank="false" maxLength="80" />
				<aos:textfield name="barter_voucher_transfer_fees"
					fieldLabel="转账手续费" emptyText="如0-0.1（百分比）" allowBlank="false"
					maxLength="80" />
				<aos:textfield name="rebate_days_delay" fieldLabel="普通商家赠送延迟天数"
					emptyText="如0-10天（从会员确认收货算起）" allowBlank="false" maxLength="80" />
				<aos:textfield name="order_receiving_days_overtime"
					fieldLabel="订单超时收货延迟天数" emptyText="如0-10天（从系统确认收货算起）"
					allowBlank="false" maxLength="80" />
				<aos:textfield name="special_rebate_delay_days"
					fieldLabel="特殊商家赠送延迟天数" emptyText="从会员确认下单+后台确认商家收款算起"
					allowBlank="false" maxLength="80" />
				<aos:textfield name="signstep" id="signstep" fieldLabel="每日签到赠送劵"
					allowBlank="false" emptyText="如0-10间的整数" />
				<aos:textfield name="transfer_minimum" fieldLabel="单笔转出最小值"
					allowBlank="false" maxLength="80" emptyText="如0-50000（整数）" />
				<aos:textfield name="maximum_transfer" fieldLabel="单笔转出最大值"
					allowBlank="false" maxLength="80" emptyText="如0-50000（整数）" />
				<aos:textfield name="shop_drop_commission_rate"
					fieldLabel="商家一滴酒提成比例" allowBlank="false" maxLength="80"
					emptyText="请输入大于或等于0的数字"/>
				<aos:textfield name="shop_drop_commission_limit_days" fieldLabel="商家一滴酒提成开始日期"
					allowBlank="false" maxLength="80" emptyText="请输入大于或等于0的数字"/>
				<aos:textfield name="user_drop_commission_rate" fieldLabel="会员一滴酒提成比例"
					allowBlank="false" maxLength="80" emptyText="请输入大于或等于0的数字"/>
				<aos:textfield name="user_drop_commission_limit_days" fieldLabel="会议一滴酒提成开始日期"
					allowBlank="false" maxLength="80" emptyText="请输入大于或等于0的数字"/>
				<aos:datetimefield name="return_time" fieldLabel="自然消费赠送时间点"
					format="Y-m-d H:i:s" editable="false" columnWidth="0.33" />
				<%-- <aos:textfield name="maximum_fee"  fieldLabel="手续费最大值" allowBlank="false" emptyText="如0-10天（从系统确认收货算起）" maxLength="80" /> --%>
				<%--<aos:textfield name="minoutpoint"  fieldLabel="转出签到赠送易物劵下限" allowBlank="false" maxLength="80"/> --%>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem onclick="f_tab_org3_d_submit" text="保存"
						icon="ok.png" />
					<aos:dockeditem onclick="#_tab_org3_d.hide();" text="关闭"
						icon="close.png" />
				</aos:docked>
			</aos:formpanel>
		</aos:window>

		<aos:window title="新增特殊商品配置" id="_tab_org4_d"
			onshow="getNewMemberSpecial()">
			<aos:formpanel id="f_tab_org4_d" width="800" layout="anchor"
				labelWidth="200">
				<aos:radioboxgroup fieldLabel="设置前提条件" columns="3"
					columnWidth="0.53">
					<aos:radiobox name="premise_condition" boxLabel="商品ID "
						inputValue="0" />
					<aos:radiobox name="premise_condition" boxLabel="商品分类"
						inputValue="1" />
					<aos:radiobox name="premise_condition" boxLabel="特殊设置"
						inputValue="2" />
				</aos:radioboxgroup>
				<aos:textfield name="limited_product_id" fieldLabel="受限商品ID"
					allowBlank="false" maxLength="50" />
				<aos:textareafield name="special_commodity" fieldLabel="特殊商品ID列表"
					allowBlank="false" maxLength="950" />
				<aos:datefield name="time_condition" fieldLabel="时间条件"
					value="2015-03-10" format="Y-m-d 00:00:00" editable="false"
					columnWidth="0.33" />
				<aos:textfield name="limit_for_purchasing" fieldLabel="限购数量限制"
					allowBlank="false" maxLength="80" />
				<aos:textfield name="membership_level_limit" fieldLabel="会员等级限制"
					allowBlank="false" maxLength="80" />
			</aos:formpanel>
			<aos:docked dock="bottom" ui="footer">
				<aos:dockeditem xtype="tbfill" />
				<aos:dockeditem onclick="f_tab_org4_d_submit" text="保存"
					icon="ok.png" />
				<aos:dockeditem onclick="#_tab_org4_d.hide();" text="关闭"
					icon="close.png" />
			</aos:docked>
		</aos:window>

	</aos:viewport>
	<script type="text/javascript">
	//新增会员配置参数保存
    function f_ad_position_submit() {
		var menber_id = Ext.getCmp('menber_id').getValue();
		var give_barter = Ext.getCmp('give_barter').getValue();
    	/* var  barter_coupo_rate = Ext.getCmp('barter_coupo_rate').getValue();
    	var menber_spending = Ext.getCmp('menber_spending').getValue(); */
    	if(menber_id % 1 != 0 || menber_id > 9999 || menber_id < 0){
    		AOS.tip("跳段ID只能设0-9999之间的整数！"); 
			return;
    	}else if(give_barter % 1 != 0 || give_barter > 9999 || give_barter < 0){
    		AOS.tip("注册赠送的易物劵只能是0-9999之间的整数！"); 
			return;
    	}else {
    		AOS.ajax({
                forms: f_ad_position,
                url: 'ZjcMemberParameter.saveAdposition',
                ok: function (data) {
                	 w_zjcUser_us.hide();
              	     AOS.tip(data.appmsg); 
              	     getUser_Add();
                }
            });
    	}
    }
	 
    //新增倍增配置参数保存
    function f_tab_param_d_submit() {
		AOS.ajax({
           forms: f_tab_param_d,
           url: 'ZjcMemberMultiplication.savezjcMemberMultiplication',
           ok: function (data) {
           	 _tab_param_d.hide();
       	     AOS.tip(data.appmsg); 
       	     getMemberParameter();
           }
       });
    }
	 
    //新增结算置参数保存
    function f_tab_account_d_submit() {
        AOS.ajax({
            forms: f_tab_account_d,
            url: 'ZjcMemberSettlement.saveZjcMemberSettlemen',
            ok: function (data) {
            	 _tab_account_d.hide();
          	     AOS.tip(data.appmsg); 
          	     getZjcMemberSettlemen();
            }
        });
    }
    
    
    //新增钱包置参数保存
    function f_tab_org2_d_submit() {
        AOS.ajax({
            forms: f_tab_org2_d,
            url: 'ZjcMemberWalletService.saveZjcMemberWallet',
            ok: function (data) {
            	_tab_org2_d.hide();
          	     AOS.tip(data.appmsg); 
          	   getZjcMemberWallet();
            }
        });
    }
    
    //新增其他置参数保存
    function f_tab_org3_d_submit() {
    	//var i = Ext.getCmp("small_barter_coupon_limits").value;
    	/* if(i != "" && i != 0 && i != 1){
    		AOS.tip("只能填0或1或者不填，1表示不需要验证，0或不填表示需要验证！"); 
    	}else{ */
    		AOS.ajax({
                forms: f_tab_org3_d,
                url: 'ZjcMemberOtherService.saveZjcMemberOther',
                ok: function (data) {
                	_tab_org3_d.hide();
              	     AOS.tip(data.appmsg); 
              	   getZjcMemberOther();
                }
            });
    	//}       
    }
    
    
    //新增特殊商品置参数保存
    function f_tab_org4_d_submit() {
        AOS.ajax({
            forms: f_tab_org4_d,
            url: 'ZjcMemberSpecialService.saveZjcMemberSpecial',
            ok: function (data) {
            	_tab_org4_d.hide();
          	     AOS.tip(data.appmsg); 
          	     getZjcMemberSpecial();
            }
        });
    }
    
     //查询会员配置数据
    function getUser_Add() {
    	AOS.ajax({
        url: 'ZjcMemberParameter.getMemberParameter',
        ok: function (data) {
        	console.log(data[0])
        	_tab_orgs.form.setValues(data[0]);
        	if(data[0].is_lock==1){
        		_tab_orgs.down('[name=is_lock]').setValue('是');
        	} else if(data[0].is_lock==0) {
        		_tab_orgs.down('[name=is_lock]').setValue('否');
        	}
        }
    }); 
    } 
     
    //查询倍增配置数据
    function getMemberParameter() {
    	 AOS.ajax({
        url: 'ZjcMemberMultiplication.getMemberMultiplication',
        ok: function (data) {
        	_tab_params.form.setValues(data[0]);
        }
    });  
    } 
    //查询结算 配置数据
    function getZjcMemberSettlemen() {
    	 AOS.ajax({
        url: 'ZjcMemberSettlement.getZjcMemberSettlemen',
        ok: function (data) {
        	g_account.form.setValues(data[0]);
        }
    });  
    } 
    
    //查询钱包 配置数据
    function getZjcMemberWallet() {
    	 AOS.ajax({
        url: 'ZjcMemberWalletService.getZjcMemberWallet',
        ok: function (data) {
        	g_orgs.form.setValues(data[0]);
        }
    });  
    } 
    
    //查询其他 配置数据
    function getZjcMemberOther() {
    	 AOS.ajax({
        url: 'ZjcMemberOtherService.getZjcMemberOther',
        ok: function (data) {
        	g_org3.form.setValues(data[0]);
        }
    });  
    } 
	 
    //查询特殊商品 配置数据
    function getZjcMemberSpecial() {
    	 AOS.ajax({
        url: 'ZjcMemberSpecialService.getZjcMemberSpecial',
        ok: function (data) {
        	g_org4.form.setValues(data[0]);
        }
    });  
    } 
    
  //回填
    function getNewMemberParameter() {
    	 AOS.ajax({
        url: 'ZjcMemberParameter.getNewMemberParameter',
        ok: function (data) {
        	f_ad_position.form.setValues(data);
        }
    });  
    } 
  //回填
    function getNewMemberMultiplication() {
    	 AOS.ajax({
        url: 'ZjcMemberMultiplication.getNewMemberMultiplication',
        ok: function (data) {
        	f_tab_param_d.form.setValues(data);
        }
    });  
    } 
  //回填
    function getNewMemberSettlement() {
    	 AOS.ajax({
        url: 'ZjcMemberSettlement.getNewZjcMemberSettlemen',
        ok: function (data) {
        	f_tab_account_d.form.setValues(data);
        }
    });  
    } 
  //回填
    function getNewMemberWallet() {
    	 AOS.ajax({
        url: 'ZjcMemberWalletService.getNewZjcMemberWallet',
        ok: function (data) {
        	f_tab_org2_d.form.setValues(data);
        }
    });  
    } 
  //回填
    function getNewMemberOther() {
    	 AOS.ajax({
        url: 'ZjcMemberOtherService.getNewZjcMemberOther',
        ok: function (data) {
        	f_tab_org3_d.form.setValues(data);
        }
    });  
    } 
  //回填
    function getNewMemberSpecial() {
    	 AOS.ajax({
        url: 'ZjcMemberSpecialService.getNewZjcMemberSpecial',
        ok: function (data) {
        	f_tab_org4_d.form.setValues(data);
        }
    });  
    } 
	</script>
</aos:onready>
