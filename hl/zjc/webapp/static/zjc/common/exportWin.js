Ext.define("Ext.exportBtn", {
	extend : 'Ext.Button',
	xtype : 'exportBtn',
	text : "导出",
	//iconCls: "folder6.png",
	url : '',
	queryItems : [],
	params : {start : 0,limit : 10000},
	initComponent : function(){
    	var me = this;
    	url = me.url;
    	queryItems = me.queryItems;
    	params = me.params;
        me.callParent(arguments);
    },
	handler : function () {
		if (Ext.isIE8) {
			var params = "start=0&limit=10000&";
			for(var i = 0; i< this.queryItems.length; i++) {
				var item = this.queryItems[i];
				if (Ext.getCmp(item).getValue() != undefined && Ext.getCmp(item).getValue() != "") {
					params += item + "=" + Ext.getCmp(item).getValue() + "&";
				}
			}
			params = params.substring(0,params.length-1);
			window.location = this.url+"?"+params;
		} else {
			console.log("url:"+url);
			for(var i = 0; i< this.queryItems.length; i++) {
				var item = this.queryItems[i];
				if(Ext.getCmp(item)){
					this.params[item] = Ext.getCmp(item).getValue();
				}
			}
			this.setParams(this.params);
		}
	}
});