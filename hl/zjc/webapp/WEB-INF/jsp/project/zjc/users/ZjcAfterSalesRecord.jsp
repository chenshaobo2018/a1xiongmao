<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="用户列表" base="http" lib="ext">
	<aos:body>
		<!-- <div id="subtn" style="width:1660px;text-align:center;" >
			<button onclick="getUser_Add()">提交</button>
		</div> -->
	</aos:body>
</aos:html>

<aos:onready >
	<aos:viewport layout="border" >
	    	<aos:tabpanel id="tabpanel" region="center" bodyBorder="0 0 0 0" >
			<aos:tab title="会员配置"  id="_tab_org"  >
				<aos:formpanel  id="_tab_orgs" width="400" layout="anchor" labelWidth="260">
					<aos:textareafield name="user_id"  fieldLabel="输入会员ID" allowBlank="false" columnWidth="0.4"/>
					<aos:numberfield name="operating_content" id="operating_content" fieldLabel="券的数额" minValue="0" maxValue="2000000" value="0" columnWidth="0.49" />
					<aos:fieldset title="1）注册赠送" labelWidth="120"  collapsible="false" border="false">
					  <aos:radioboxgroup fieldLabel="注册赠送" id="giving" columns="3"  columnWidth="0.53">
						<aos:radiobox name="operation_type"  boxLabel="可用" inputValue="1" />
						<aos:radiobox name="operation_type"  boxLabel="可转" inputValue="2" />
						<aos:radiobox name="operation_type"  boxLabel="可转可用各一半" inputValue="3" />
					</aos:radioboxgroup>
					</aos:fieldset>
					<aos:fieldset title="2）购物赠送" labelWidth="120"  collapsible="false" border="false">
					<aos:radioboxgroup fieldLabel="购物赠送" id="shopping" columns="3"  columnWidth="0.53">
						<aos:radiobox name="operation_type"  boxLabel="可用" inputValue="4" />
						<aos:radiobox name="operation_type"  boxLabel="可转" inputValue="5" />
						<aos:radiobox name="operation_type"  boxLabel="可转可用各一半" inputValue="6" />
					</aos:radioboxgroup>
					</aos:fieldset>
					
					<aos:fieldset title="3）冻结与解封" labelWidth="120"  collapsible="false" border="false">
					<aos:radioboxgroup fieldLabel="冻结与解封" id="Sealing" columns="3"  columnWidth="0.53">
						<aos:radiobox name="operation_type"  boxLabel="批量封号" inputValue="7" />
						<aos:radiobox name="operation_type"  boxLabel="批量解封" inputValue="8" />
					</aos:radioboxgroup>
					</aos:fieldset>
					
					<aos:fieldset title="4）售后处理" labelWidth="120"  collapsible="false" border="false">
					   <aos:fieldset title="A、平台直接处理" labelWidth="120"  collapsible="false" border="false">
					<aos:radioboxgroup fieldLabel=" 会员" id="sales" columns="3"  columnWidth="0.53">
						<aos:radiobox name="operation_type"  boxLabel="可用" inputValue="9" />
						<aos:radiobox name="operation_type"  boxLabel="可转" inputValue="10" />
						<aos:radiobox name="operation_type"  boxLabel="可转可用各一半" inputValue="11" />
					</aos:radioboxgroup>
					<aos:radioboxgroup fieldLabel="商家" id="store" columns="3"  columnWidth="0.53">
						<aos:radiobox name="operation_type"  boxLabel="可转" inputValue="12" />
					</aos:radioboxgroup>
					</aos:fieldset>
					
					<aos:fieldset title="B、会员申请后处理" labelWidth="120"  collapsible="false" border="false">
						<aos:radioboxgroup fieldLabel="会员" id="apply" columns="3"  columnWidth="0.53">
							<aos:radiobox name="operation_type"  boxLabel="可用" inputValue="13" />
							<aos:radiobox name="operation_type"  boxLabel="可转" inputValue="14" />
							<aos:radiobox name="operation_type"  boxLabel="可转可用各一半" inputValue="15" />
						</aos:radioboxgroup>
					</aos:fieldset>
					
					<aos:fieldset title="C、取消订单（退券）" labelWidth="120"  collapsible="false" border="false">
						<aos:radioboxgroup fieldLabel="会员退券" id="tuiquan" columns="3"  columnWidth="0.53">
							<aos:radiobox name="operation_type"  boxLabel="退券" inputValue="18" />
						</aos:radioboxgroup>
					</aos:fieldset>
					</aos:fieldset>
					<aos:fieldset title="5）双创合伙人" labelWidth="120"  collapsible="false" border="false">
					<aos:radioboxgroup fieldLabel="双创合伙人" id="squser" columns="3"  columnWidth="0.53">
						<aos:radiobox name="operation_type"  boxLabel="双创资格" inputValue="16" />
						<aos:radiobox name="operation_type"  boxLabel="周期赠送" inputValue="17" />	
						<aos:radiobox name="operation_type"  boxLabel="取消双创资格" inputValue="19" />	
					</aos:radioboxgroup>
					</aos:fieldset>
					
					<%-- <aos:fieldset title="6）退券" labelWidth="120"  collapsible="false" border="false">
					<aos:radioboxgroup fieldLabel="会员退券" id="tuiquan" columns="3"  columnWidth="0.53">
						<aos:radiobox name="operation_type"  boxLabel="退券" inputValue="18" />
						</aos:radioboxgroup>
					</aos:fieldset> --%>
					
				</aos:formpanel>
				<aos:docked dock="bottom" ui="footer">
					<aos:dockeditem xtype="tbfill" />
					<aos:dockeditem onclick="f_tab_org4_d_submit" text="保存" icon="ok.png" />
				      </aos:docked>
			</aos:tab>
		</aos:tabpanel>
	</aos:viewport>
		<script type="text/javascript">
		 //新增特殊商品置参数保存
	    function f_tab_org4_d_submit() {
	    	var test=Ext.getCmp('giving').getValue();
			var test1=Ext.getCmp('shopping').getValue();
			var test2=Ext.getCmp('Sealing').getValue();
			var test3=Ext.getCmp('sales').getValue();
			var test4=Ext.getCmp('store').getValue();
			var test5=Ext.getCmp('apply').getValue();
			var test6=Ext.getCmp('squser').getValue();
			var test7=Ext.getCmp('tuiquan').getValue();
			var operating_content=Ext.getCmp('operating_content').getValue(); 
			var temp;
			for(var i in test){
				temp =test[i]; 
				} 
			for(var i in test1){
				temp =test1[i]; 
				} 
			for(var i in test2){
				temp =test2[i]; 
				} 
			for(var i in test3){
				temp =test3[i]; 
				} 
			for(var i in test4){
				temp =test4[i]; 
				} 
			for(var i in test5){
				temp =test5[i]; 
				} 
			for(var i in test6){
				temp =test6[i]; 
				} 
			for(var i in test7){
				temp =test7[i]; 
				} 
			if(temp==1){
				if(operating_content==0){
					 AOS.tip("返还易物券不能为0!"); 
				}else{
					AOS.ajax({
			            forms: _tab_orgs,
			            url: 'ZjcAfterSalesRecordService.payPoints',
			            ok: function (data) {
			          	     AOS.tip(data.appmsg); 
			          	   if(data.appcode == 1){
			            		AOS.reset(_tab_orgs);
			            	}
			            }
			        });
				}
			}else if(temp==2){
				if(operating_content==0){
				 AOS.tip("返还易物券不能为0!"); 
			}else{
				 AOS.ajax({
			            forms: _tab_orgs,
			            url: 'ZjcAfterSalesRecordService.MakeOverIntegral',
			            ok: function (data) {
			          	     AOS.tip(data.appmsg); 
			          	   if(data.appcode == 1){
			            		AOS.reset(_tab_orgs);
			            	}
			            }
			        });}
			}else if(temp==3){
				if(operating_content==0){
					 AOS.tip("返还易物券不能为0!"); 
				}else{
				 AOS.ajax({
			            forms: _tab_orgs,
			            url: 'ZjcAfterSalesRecordService.half',
			            ok: function (data) {
			          	     AOS.tip(data.appmsg); 
			          	   if(data.appcode == 1){
			            		AOS.reset(_tab_orgs);
			            	}
			            }
			        });}
			}else if(temp==4){
				if(operating_content==0){
				 AOS.tip("返还易物券不能为0!"); 
			}else{
				 AOS.ajax({
			            forms: _tab_orgs,
			            url: 'ZjcAfterSalesRecordService.payPoints',
			            ok: function (data) {
			          	     AOS.tip(data.appmsg); 
			          	   if(data.appcode == 1){
			            		AOS.reset(_tab_orgs);
			            	}
			            }
			        });}
			}else if(temp==5){
				if(operating_content==0){
					 AOS.tip("返还易物券不能为0!"); 
				}else{
				 AOS.ajax({
			            forms: _tab_orgs,
			            url: 'ZjcAfterSalesRecordService.MakeOverIntegral',
			            ok: function (data) {
			          	     AOS.tip(data.appmsg); 
			          	   if(data.appcode == 1){
			            		AOS.reset(_tab_orgs);
			            	}
			            }
			        });}
			}else if(temp==6){
				if(operating_content==0){
					 AOS.tip("返还易物券不能为0!"); 
				}else{
				 AOS.ajax({
			            forms: _tab_orgs,
			            url: 'ZjcAfterSalesRecordService.half',
			            ok: function (data) {
			          	     AOS.tip(data.appmsg); 
			          	   if(data.appcode == 1){
			            		AOS.reset(_tab_orgs);
			            	}
			            }
			        });}
			}else if(temp==7){
				
				AOS.ajax({
		            forms: _tab_orgs,
		            url: 'ZjcAfterSalesRecordService.titles',
		            ok: function (data) {
		          	     AOS.tip(data.appmsg); 
		          	   if(data.appcode == 1){
		            		AOS.reset(_tab_orgs);
		            	}
		            }
		        });
			}else if(temp==8){
				AOS.ajax({
		            forms: _tab_orgs,
		            url: 'ZjcAfterSalesRecordService.unlock',
		            ok: function (data) {
		          	     AOS.tip(data.appmsg); 
		          	   if(data.appcode == 1){
		            		AOS.reset(_tab_orgs);
		            	}
		            }
		        });
			}else if(temp==9){
				
				 AOS.ajax({
			            forms: _tab_orgs,
			            url: 'ZjcAfterSalesRecordService.payPoints',
			            ok: function (data) {
			          	     AOS.tip(data.appmsg); 
			          	   if(data.appcode == 1){
			            		AOS.reset(_tab_orgs);
			            	}
			            }
			        });
			}else if(temp==10){
				
				AOS.ajax({
		            forms: _tab_orgs,
		            url: 'ZjcAfterSalesRecordService.MakeOverIntegral',
		            ok: function (data) {
		          	     AOS.tip(data.appmsg);
		          	   if(data.appcode == 1){
		            		AOS.reset(_tab_orgs);
		            	}
		            }
		        });
			}else if(temp==11){
				if(operating_content==0){
					 AOS.tip("返还易物券不能为0!"); 
				}else{
				AOS.ajax({
		            forms: _tab_orgs,
		            url: 'ZjcAfterSalesRecordService.half',
		            ok: function (data) {
		          	     AOS.tip(data.appmsg); 
		          	   if(data.appcode == 1){
		            		AOS.reset(_tab_orgs);
		            	}
		            }
		        });}
			}else if(temp==12){
				if(operating_content==0){
					 AOS.tip("返还易物券不能为0!"); 
				}else{
				AOS.ajax({
		            forms: _tab_orgs,
		            url: 'ZjcAfterSalesRecordService.MakeOverIntegrals',
		            ok: function (data) {
		          	     AOS.tip(data.appmsg); 
		          	   if(data.appcode == 1){
		            		AOS.reset(_tab_orgs);
		            	}
		            }
		        });}
			}else if(temp==13){
				if(operating_content==0){
					 AOS.tip("返还易物券不能为0!"); 
				}else{
				AOS.ajax({
		            forms: _tab_orgs,
		            url: 'ZjcAfterSalesRecordService.payPoints',
		            ok: function (data) {
		          	     AOS.tip(data.appmsg);
		          	   if(data.appcode == 1){
		            		AOS.reset(_tab_orgs);
		            	}
		            }
		        });}
			}else if(temp==14){
				if(operating_content==0){
					 AOS.tip("返还易物券不能为0!"); 
				}else{
				AOS.ajax({
		            forms: _tab_orgs,
		            url: 'ZjcAfterSalesRecordService.MakeOverIntegral',
		            ok: function (data) {
		          	     AOS.tip(data.appmsg); 
		          	   if(data.appcode == 1){
		            		AOS.reset(_tab_orgs);
		            	}
		            }
		        });}
			} else if(temp==15){
				if(operating_content==0){
					 AOS.tip("返还易物券不能为0!"); 
				}else{
				AOS.ajax({
		            forms: _tab_orgs,
		            url: 'ZjcAfterSalesRecordService.half',
		            ok: function (data) {
		          	     AOS.tip(data.appmsg); 
		          	   if(data.appcode == 1){
		            		AOS.reset(_tab_orgs);
		            	}
		            }
		        });}
			} else if(temp==16){//开通双千			
				AOS.ajax({
		            forms: _tab_orgs,
		            url: 'ZjcAfterSalesRecordService.addAfterSales',
		            ok: function (data) {
		          	     AOS.tip(data.appmsg); 
		            	if(data.appcode == 1){
		            		AOS.reset(_tab_orgs);
		            	}
		            }
		        });
			} else if(temp==17){//双千赠送				
				if(operating_content==0){
					 AOS.tip("赠送易物券不能为0!"); 
				}else{
					 AOS.ajax({
				            forms: _tab_orgs,
				            url: 'ZjcAfterSalesRecordService.addOrUpdateAfterSales',
				            ok: function (data) {
				          	     AOS.tip(data.appmsg); 
				            	if(data.appcode == 1){
				            		AOS.reset(_tab_orgs);
				            	}
				            }
				        });
					 }
			}else if(temp == 18){//退券
				if(operating_content==0){
					AOS.tip("退还易物券不能为0!"); 
				}else{
					 AOS.ajax({
				            forms: _tab_orgs,
				            url: 'ZjcAfterSalesRecordService.halfd',
				            ok: function (data) {
				          	   AOS.tip(data.appmsg);
				            	if(data.appcode == 1){
				            		AOS.reset(_tab_orgs);
				            	}
				            }
				        });
				}
			}  else if(temp==19){//取消双创				
				AOS.ajax({
		            forms: _tab_orgs,
		            url: 'ZjcAfterSalesRecordService.cancleDouble',
		            ok: function (data) {
		            	console.log(data)
		          	   AOS.tip(data.appmsg); 
		            	if(data.appcode == 1){
		            		AOS.reset(_tab_orgs);
		            	}
		            }
		        });
			}
		 }
		
		</script>
</aos:onready>

