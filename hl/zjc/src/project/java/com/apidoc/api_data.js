define({ "api": [
  {
    "type": "get",
    "url": "api/app/v1/again_content.jhtml",
    "title": "追加评论",
    "name": "____",
    "group": "Comment",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>订单ID（必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>商品ID（必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "again_content",
            "description": "<p>追加评论内容（必填）</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/comment/controller/CommentController.java",
    "groupTitle": "Comment",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/again_content.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/add_comment.jhtml",
    "title": "添加评论",
    "name": "____",
    "group": "Comment",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>订单ID（必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>商品ID（必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>评论内容</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "img",
            "description": "<p>晒单图片（多个评论图片以英文逗号隔开）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "logistics_rank",
            "description": "<p>物流评价等级（必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "quality_rank",
            "description": "<p>产品质量等级（必填）</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/comment/controller/CommentController.java",
    "groupTitle": "Comment",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/add_comment.jhtml"
      }
    ]
  },
  {
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>用户编码</p>"
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "group": "F__hl_zjc_src_project_java_com_api_order_controller_OrderController_java",
    "groupTitle": "F__hl_zjc_src_project_java_com_api_order_controller_OrderController_java",
    "name": ""
  },
  {
    "type": "get",
    "url": "api/app/v1/getFinaceToolList.jhtml",
    "title": "官方网址",
    "name": "____",
    "group": "Find",
    "version": "0.0.0",
    "filename": "api/find/controller/FindController.java",
    "groupTitle": "Find",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getFinaceToolList.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getServiceList.jhtml",
    "title": "客服列表",
    "name": "____",
    "group": "Find",
    "version": "0.0.0",
    "filename": "api/find/controller/FindController.java",
    "groupTitle": "Find",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getServiceList.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/articleDetail.jhtml",
    "title": "文章详情",
    "name": "____",
    "group": "Find",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "article_id",
            "description": "<p>文章ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/find/controller/FindController.java",
    "groupTitle": "Find",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/articleDetail.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getSystemMsg.jhtml",
    "title": "平台通知列表",
    "name": "______",
    "group": "Find",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/find/controller/FindController.java",
    "groupTitle": "Find",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getSystemMsg.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getAlertMsg.jhtml",
    "title": "获取弹出公告",
    "name": "______",
    "group": "Find",
    "version": "0.0.0",
    "filename": "api/find/controller/FindController.java",
    "groupTitle": "Find",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getAlertMsg.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/systemMsgDetail.jhtml",
    "title": "系统消息详情",
    "name": "______",
    "group": "Find",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "message_id",
            "description": "<p>信息ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/find/controller/FindController.java",
    "groupTitle": "Find",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/systemMsgDetail.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getNewsList.jhtml",
    "title": "获取新闻列表",
    "name": "______",
    "group": "Find",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/find/controller/FindController.java",
    "groupTitle": "Find",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getNewsList.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/checkCPIsBuy.jhtml",
    "title": "判断用户是否购买企业宣传",
    "name": "____________",
    "group": "Find",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cp_id",
            "description": "<p>企业宣传ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/store/controller/StoreTokenController.java",
    "groupTitle": "Find",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/checkCPIsBuy.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/get_goods_category_tree.jhtml",
    "title": "商品分类",
    "name": "____",
    "group": "Goods",
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "Goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/get_goods_category_tree.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/collectGoods.jhtml",
    "title": "收藏商品",
    "name": "____",
    "group": "Goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>商品id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsTokenController.java",
    "groupTitle": "Goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/collectGoods.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getGoodsDetailByGoodsId.jhtml",
    "title": "通过商品ID查询商品详情",
    "name": "____ID______",
    "group": "Goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>店铺id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token(非必填，用来判断用户是否收藏了该商品)</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "Goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getGoodsDetailByGoodsId.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getNewGoods.jhtml",
    "title": "查找新商品",
    "name": "_____",
    "group": "Goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>查询类型（全部：all,时间：time,类型：price）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "desc",
            "description": "<p>是否降序查询（true,false）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "Goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getNewGoods.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/cancleCollectGoods.jhtml",
    "title": "取消收藏商品",
    "name": "______",
    "group": "Goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>商品id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsTokenController.java",
    "groupTitle": "Goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/cancleCollectGoods.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getStoreGoods.jhtml",
    "title": "获取店铺的商品",
    "name": "_______",
    "group": "Goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "store_id",
            "description": "<p>店铺id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "Goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getStoreGoods.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/cancleCollectGoods.jhtml",
    "title": "查看我的收藏商品",
    "name": "________",
    "group": "Goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsTokenController.java",
    "groupTitle": "Goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/cancleCollectGoods.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getGoodsCategory.jhtml",
    "title": "商品分类查询",
    "name": "______",
    "group": "Goods_____________",
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "Goods_____________",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getGoodsCategory.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getExchangRang.jhtml",
    "title": "获取转账范围",
    "name": "______",
    "group": "Index",
    "version": "0.0.0",
    "filename": "api/accountManager/controller/UserAccountInfoController.java",
    "groupTitle": "Index",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getExchangRang.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getExchangRang.jhtml",
    "title": "获取转账范围",
    "name": "______",
    "group": "Index",
    "version": "0.0.0",
    "filename": "api/index/controller/IndexController.java",
    "groupTitle": "Index",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getExchangRang.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getPoundage.jhtml",
    "title": "根据积分计算手续费",
    "name": "_________",
    "group": "Index",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "points",
            "description": "<p>积分数量</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/index/controller/IndexController.java",
    "groupTitle": "Index",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getPoundage.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/queryPinTuanInvitation.jhtml",
    "title": "查询邀请拼团",
    "name": "queryPinTuanInvitation______",
    "group": "Invitation",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>商品id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/pintuan/controller/PinTuanController.java",
    "groupTitle": "Invitation",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/queryPinTuanInvitation.jhtml"
      }
    ]
  },
  {
    "type": "post",
    "url": "api/app/v1/transferToSLB.jhtml",
    "title": "积分转slb",
    "name": "__",
    "group": "Login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobileArea",
            "description": "<p>区号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "integral",
            "description": "<p>积分</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/slb/controller/SlbController.java",
    "groupTitle": "Login",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/transferToSLB.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/login.jhtml",
    "title": "登陆",
    "name": "__",
    "group": "Login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "design",
            "description": "<p>校验包：LoginRandom+mobile+password+&quot;zjc_1815&quot;；先将passwordMD5加密，然后整体MD5加密</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "LoginRandom",
            "description": "<p>登陆随机码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/login/controller/LoginController.java",
    "groupTitle": "Login",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/login.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/SLlogin.jhtml",
    "title": "搜了登录接口",
    "name": "__",
    "group": "Login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "sign",
            "description": "<p>密匙</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/login/controller/LoginController.java",
    "groupTitle": "Login",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/SLlogin.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/loginParamater.jhtml",
    "title": "获取登陆参数",
    "name": "______",
    "group": "Login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/login/controller/LoginController.java",
    "groupTitle": "Login",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/loginParamater.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/mobileLogin.jhtml",
    "title": "手机号不存在则生成 存在则登录",
    "name": "_______________",
    "group": "Login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "wxJson",
            "description": "<p>微信的json</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>短信登录码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>短信类型</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/login/controller/LoginNotPasswordController.java",
    "groupTitle": "Login",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/mobileLogin.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/wxOpenIdLogin.jhtml",
    "title": "登陆",
    "name": "__open_id__",
    "group": "Login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "open_id",
            "description": "<p>微信的union_id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/login/controller/LoginNotPasswordController.java",
    "groupTitle": "Login",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/wxOpenIdLogin.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/loginClientid.jhtml",
    "title": "往redis里面存Clientid",
    "name": "_redis___Clientid",
    "group": "Login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "clientid",
            "description": "<p>clientid</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/login/controller/LoginController.java",
    "groupTitle": "Login",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/loginClientid.jhtml"
      }
    ]
  },
  {
    "type": "post",
    "url": "api/app/v1/ps_account.jhtml 提交商品页面",
    "title": "线下支付",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pay_code",
            "description": "<p>支付随机码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "sellerId",
            "description": "<p>商户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "point",
            "description": "<p>支付金额</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "design",
            "description": "<p>校验码(支付随机码+用户电话+用户支付密码+zjc_1815){用户支付密码加密方式见注册}</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "name": "PostApiAppV1Ps_accountJhtml",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/ps_account.jhtml 提交商品页面"
      }
    ]
  },
  {
    "type": "get",
    "url": "/app/v1/orderDetail",
    "title": "订单快照",
    "name": "____",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>订单id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest//app/v1/orderDetail"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/confirm_order.jhtml",
    "title": "确认收货",
    "name": "____",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>订单id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/confirm_order.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "/app/v1/order_info",
    "title": "订单详情",
    "name": "____",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>订单id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest//app/v1/order_info"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/order_pay.jhtml",
    "title": "计算中心确认结算",
    "name": "____",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>tokrn</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "orderId",
            "description": "<p>订单id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "random",
            "description": "<p>随机数</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "sign",
            "description": "<p>校验包（random+mobile+password的MD5+&quot;zjc_1815&quot;），然后整体MD5</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/order_pay.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/mixed_pay.jhtml",
    "title": "订单混合支付扣除易物券",
    "name": "____",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>订单id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/mixed_pay.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "/app/v1/order_del",
    "title": "删除订单",
    "name": "____",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>订单id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_status",
            "description": "<p>订单状态</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest//app/v1/order_del"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/mixDrops_pay.jhtml",
    "title": "订单混合支付扣除滴",
    "name": "____",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>订单id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/mixDrops_pay.jhtml"
      }
    ]
  },
  {
    "type": "post",
    "url": "api/app/v1/orderSub.jhtml",
    "title": "提交商品页面",
    "name": "______",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "address_id",
            "description": "<p>地址id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "total_amount",
            "description": "<p>订单总额</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "store_id",
            "description": "<p>店铺id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "Goods",
            "description": "<p>商品数据</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pay_type",
            "description": "<p>支付方式</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_note",
            "description": "<p>备注信息</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "voucher_id",
            "description": "<p>代金券id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/orderSub.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/saveExchange.jhtml",
    "title": "提交易物交易",
    "name": "______",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "real_name",
            "description": "<p>对方姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>对方手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "points",
            "description": "<p>积分数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "sign",
            "description": "<p>校验包</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "random",
            "description": "<p>随机码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/ExChangeController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/saveExchange.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "/app/v1/my_order_list",
    "title": "我的订单列表",
    "name": "______",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_status",
            "description": "<p>订单状态 0-待支付,1-待发货,2-待收货,3-待评价,4-完成;允许值: 0, 1, 2, 3, 4</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "is_shop_group",
            "description": "<p>是否是拼团订单(只在拼单里面传,其他地方不传改参数)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest//app/v1/my_order_list"
      }
    ]
  },
  {
    "type": "get",
    "url": "/app/v1/settle_center",
    "title": "倍增订单提交",
    "name": "______",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "center_id",
            "description": "<p>结算中心Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "center_name",
            "description": "<p>结算中心真实姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "points",
            "description": "<p>结算金额</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "random",
            "description": "<p>随机码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "sign",
            "description": "<p>校验包 {加密方式见登录}</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest//app/v1/settle_center"
      }
    ]
  },
  {
    "type": "get",
    "url": "/app/v1/transferOrderDetail",
    "title": "订单快照",
    "name": "______",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "transfer_type",
            "description": "<p>0未转账1转账</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "add_time",
            "description": "<p>下单时间</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest//app/v1/transferOrderDetail"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/delRechargeOrder.jhtml",
    "title": "删除易物券订单",
    "name": "_______",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>订单ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/ReChargeController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/delRechargeOrder.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/myRechargeOrder.jhtml",
    "title": "我的易物券订单",
    "name": "_______",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pay_status",
            "description": "<p>充值状态0:待支付 1:充值成功 2:交易关闭</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/ReChargeController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/myRechargeOrder.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/recharge_order_list.jhtml",
    "title": "积分充值订单列表",
    "name": "________",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>当前第几页</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>支付状态 0未支付，1已支付</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/recharge_order_list.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "/app/v1/updateTransferOrder",
    "title": "用户订单退单催单",
    "name": "________",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>1为催单2为退单</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>order_id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "reminder",
            "description": "<p>催单次数</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "reminderTime",
            "description": "<p>催单时间</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "single_back",
            "description": "<p>退单</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "single_back_time",
            "description": "<p>退单时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest//app/v1/updateTransferOrder"
      }
    ]
  },
  {
    "type": "get",
    "url": "/app/v1/updateOperateOrder",
    "title": "用户订单退单催单",
    "name": "________",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>1为催单2为退单</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>订单id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>商品id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest//app/v1/updateOperateOrder"
      }
    ]
  },
  {
    "type": "post",
    "url": "api/app/v1/getMyVouchers.jhtml",
    "title": "获取我的代金券列表",
    "name": "_________",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "is_use",
            "description": "<p>是否是代金券商品 ：0未使用；1已使用（不传查询全部代金券）</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getMyVouchers.jhtml"
      }
    ]
  },
  {
    "type": "post",
    "url": "api/app/v1/getVouchers.jhtml",
    "title": "获取可用代金券列表",
    "name": "_________",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "is_voucher",
            "description": "<p>商品代金券类型 0：不支持代金券；1:代金券商品；</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getVouchers.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/check_buy_points.jhtml",
    "title": "检查购买积分所需现金",
    "name": "__________",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "buy_points",
            "description": "<p>输入的积分购买数</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/check_buy_points.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/check_user_points.jhtml",
    "title": "今天还能购买多少积分",
    "name": "__________",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/check_user_points.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "/app/v1/settle_center",
    "title": "根据输入的金额，计算结算明细",
    "name": "______________",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "center_id",
            "description": "<p>结算中心Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "center_name",
            "description": "<p>结算中心真实姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "points",
            "description": "<p>结算金额</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/OrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest//app/v1/settle_center"
      }
    ]
  },
  {
    "type": "post",
    "url": "api/activity/v1/orderSub.jhtml",
    "title": "提交商品页面",
    "name": "orderSub______",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>user_id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "address_id",
            "description": "<p>地址id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "total_amount",
            "description": "<p>订单总额</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "store_id",
            "description": "<p>店铺id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "Goods",
            "description": "<p>商品数据</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pay_type",
            "description": "<p>支付方式</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_note",
            "description": "<p>备注信息</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/activity/controller/AppActivityOrderController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/activity/v1/orderSub.jhtml"
      }
    ]
  },
  {
    "type": "post",
    "url": "api/app/v1/pinOrderSub.jhtml",
    "title": "提交商品页面",
    "name": "orderSub______",
    "group": "Order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "address_id",
            "description": "<p>地址id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "total_amount",
            "description": "<p>订单总额</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "store_id",
            "description": "<p>店铺id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "Goods",
            "description": "<p>商品数据</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pay_type",
            "description": "<p>支付方式</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_note",
            "description": "<p>备注信息</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "voucher_id",
            "description": "<p>代金券id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pin_order_id",
            "description": "<p>如果从参与拼单中去下单 则需要传参与人的订单id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "is_shop_group",
            "description": "<p>是否是拼团订单</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/pintuan/controller/OrderPinTuanController.java",
    "groupTitle": "Order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/pinOrderSub.jhtml"
      }
    ]
  },
  {
    "type": "post",
    "url": "api/app/v1/queryOrderPersonnel.jhtml",
    "title": "查询当前订单那些人参与了",
    "name": "queryOrderPersonnel____________",
    "group": "Personnel",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pin_order_id",
            "description": "<p>订单id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "is_head",
            "description": "<p>是否是团长（团长为1）</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/pintuan/controller/OrderPinTuanController.java",
    "groupTitle": "Personnel",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/queryOrderPersonnel.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/activity/v1/appGoodsShare.jhtml",
    "title": "商品分享进度",
    "name": "appGoodsShare______",
    "group": "Share",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>user_id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>商品id(查该人员全部分享商品不传,查单个传)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>从第一页开始</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/activity/controller/AppActivityShareController.java",
    "groupTitle": "Share",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/activity/v1/appGoodsShare.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/activity/v1/appShareFriends.jhtml",
    "title": "查询商品助力好友",
    "name": "appShareFriends________",
    "group": "Share",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>user_id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>goods_id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/activity/controller/AppActivityShareController.java",
    "groupTitle": "Share",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/activity/v1/appShareFriends.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/activity/v1/getAppWinPrizeList.jhtml",
    "title": "查询抽中的奖品",
    "name": "getAppWinPrizeList___________",
    "group": "Share",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>user_id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>page</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/activity/controller/AppActivityShareController.java",
    "groupTitle": "Share",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/activity/v1/getAppWinPrizeList.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/activity/v1/shareActivityGoodsUser.jhtml",
    "title": "商品分享助力数据新增",
    "name": "shareActivityGoodsUser__________",
    "group": "Share",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>分享人user_id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>商品ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/activity/controller/AppActivityShareController.java",
    "groupTitle": "Share",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/activity/v1/shareActivityGoodsUser.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/activity/v1/shareHomePowers.jhtml",
    "title": "查询助力累计数据",
    "name": "shareHomePowers________",
    "group": "Share",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>user_id必须传的值</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/activity/controller/AppActivityShareController.java",
    "groupTitle": "Share",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/activity/v1/shareHomePowers.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/activity/v1/appWinSharePrize.jhtml",
    "title": "抽奖接口",
    "name": "shareUser_____",
    "group": "Share",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>user_id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/activity/controller/AppActivityShareController.java",
    "groupTitle": "Share",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/activity/v1/appWinSharePrize.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/activity/v1/shareUser.jhtml",
    "title": "查询分享人",
    "name": "shareUser______",
    "group": "Share",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>分享人user_id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/activity/controller/AppActivityShareController.java",
    "groupTitle": "Share",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/activity/v1/shareUser.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getMonthSign.jhtml",
    "title": "本月的签到数据",
    "name": "__",
    "group": "Sign",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": ""
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/sign/controller/SignController.java",
    "groupTitle": "Sign",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getMonthSign.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/signOut.jhtml",
    "title": "本月的签到数据",
    "name": "__",
    "group": "Sign",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "outPoint",
            "description": "<p>提取的数量</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/sign/controller/SignController.java",
    "groupTitle": "Sign",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/signOut.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/daySign.jhtml",
    "title": "当天签到",
    "name": "__",
    "group": "Sign",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": ""
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/sign/controller/SignController.java",
    "groupTitle": "Sign",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/daySign.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getSignList.jhtml",
    "title": "签到历史",
    "name": "__",
    "group": "Sign",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/sign/controller/SignController.java",
    "groupTitle": "Sign",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getSignList.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getSignSetting.jhtml",
    "title": "签到设置",
    "name": "____",
    "group": "Sign",
    "version": "0.0.0",
    "filename": "api/sign/controller/SignNoTokenController.java",
    "groupTitle": "Sign",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getSignSetting.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/checkShop.jhtml",
    "title": "检查商户",
    "name": "____",
    "group": "Store",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>商家电话</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/store/controller/StoreTokenController.java",
    "groupTitle": "Store",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/checkShop.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/collectStore.jhtml",
    "title": "通过店铺ID收藏店铺",
    "name": "____ID____",
    "group": "Store",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "store_id",
            "description": "<p>店铺ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/store/controller/StoreTokenController.java",
    "groupTitle": "Store",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/collectStore.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/cancleCollectStore.jhtml",
    "title": "通过店铺ID取消收藏店铺",
    "name": "____ID______",
    "group": "Store",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "store_id",
            "description": "<p>店铺ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/store/controller/StoreTokenController.java",
    "groupTitle": "Store",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/cancleCollectStore.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/companyPublicDetail.jhtml",
    "title": "企业宣传详情",
    "name": "______",
    "group": "Store",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cp_id",
            "description": "<p>宣传ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/store/controller/StoreController.java",
    "groupTitle": "Store",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/companyPublicDetail.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/companyPublicList.jhtml",
    "title": "企业宣传列表",
    "name": "______",
    "group": "Store",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>当前是第几页</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "condition",
            "description": "<p>筛选条件 (all,free,unFree)</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/store/controller/StoreController.java",
    "groupTitle": "Store",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/companyPublicList.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getStores.jhtml",
    "title": "获取店铺列表",
    "name": "______",
    "group": "Store",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "store_name",
            "description": "<p>店铺名字</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "lng",
            "description": "<p>经度</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "lat",
            "description": "<p>纬度</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/store/controller/StoreController.java",
    "groupTitle": "Store",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getStores.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/applyStore.jhtml",
    "title": "商家申请开店",
    "name": "______",
    "group": "Store",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "store_name",
            "description": "<p>企业名字</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "province_id",
            "description": "<p>省ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "city_id",
            "description": "<p>市ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "area_id",
            "description": "<p>区ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "twon_id",
            "description": "<p>街道ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "address_detail",
            "description": "<p>详细地址</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "lng",
            "description": "<p>经度</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "lat",
            "description": "<p>纬度</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "office_phone",
            "description": "<p>办公电话</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "contacts_name",
            "description": "<p>联系人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "contacts_phone",
            "description": "<p>联系电话</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "business_licence_number_elc",
            "description": "<p>营业执照</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "food_hygiene_img",
            "description": "<p>卫生许可证(身份证)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cat_id",
            "description": "<p>商品分类ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "special_premit_img",
            "description": "<p>其他证件</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "store_logo",
            "description": "<p>店铺logo</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/store/controller/StoreTokenController.java",
    "groupTitle": "Store",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/applyStore.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/cancleCollectStore.jhtml",
    "title": "查询我的收藏店铺",
    "name": "________",
    "group": "Store",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/store/controller/StoreTokenController.java",
    "groupTitle": "Store",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/cancleCollectStore.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getStoreDetail.jhtml",
    "title": "判断是否有开通店铺",
    "name": "_________",
    "group": "Store",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/store/controller/StoreTokenController.java",
    "groupTitle": "Store",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getStoreDetail.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getStoreInfoByStoreId.jhtml",
    "title": "通过store_id获取店铺信息",
    "name": "__store_id______",
    "group": "Store",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "store_id",
            "description": "<p>店铺ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token(非必填，用来判断用户是否收藏了该店铺)</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/store/controller/StoreController.java",
    "groupTitle": "Store",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getStoreInfoByStoreId.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/companyPublicPay.jhtml",
    "title": "支付企业宣传",
    "name": "______",
    "group": "UserAccount",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cp_id",
            "description": "<p>企业宣传ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "sign",
            "description": "<p>校验包</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "random",
            "description": "<p>随机码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/accountManager/controller/UserAccountInfoController.java",
    "groupTitle": "UserAccount",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/companyPublicPay.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/changeKzToKy.jhtml",
    "title": "易物券转换（可转到可用）",
    "name": "____________",
    "group": "UserAccount",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "points_num",
            "description": "<p>易物券数量</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/accountManager/controller/UserAccountInfoController.java",
    "groupTitle": "UserAccount",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/changeKzToKy.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/register.jhtml",
    "title": "注册",
    "name": "__",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "recommended_code",
            "description": "<p>分享人ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/register/controller/RegisterController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/register.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/findPassword.jhtml",
    "title": "找回密码",
    "name": "____",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>验证短信</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>电话</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "new_password",
            "description": "<p>新密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>验证码类型</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoNoTokenController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/findPassword.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/myComplaints.jhtml",
    "title": "我的投诉",
    "name": "____",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "complaints_type",
            "description": "<p>投诉类型（0表示被投诉，1表示投诉）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/complaints/controller/ComplaintsController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/myComplaints.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/myApprentice.jhtml",
    "title": "我的下级",
    "name": "____",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>类型  0全部  1合格 2不合格</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/myApprentice.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/authen.jhtml",
    "title": "实名认证",
    "name": "____",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "real_name",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id_card",
            "description": "<p>身份证号</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/authen.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/userCommission.jhtml",
    "title": "用户提成",
    "name": "____",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cash",
            "description": "<p>提成现金</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/userCommission.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/updateClientID.jhtml",
    "title": "更新设备号",
    "name": "_____",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "clientid",
            "description": "<p>会员登录时的设备号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "src_client",
            "description": "<p>登陆设备的类型：android 1 ,IOS 2,weixin 3</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/updateClientID.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getUserInfo.jhtml",
    "title": "获取用户信息",
    "name": "______",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getUserInfo.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/addComplaints.jhtml",
    "title": "新增投诉记录",
    "name": "______",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "to_user_id",
            "description": "<p>被投诉人编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "info",
            "description": "<p>投诉理由</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>投诉人手机号码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/complaints/controller/ComplaintsController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/addComplaints.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/updatePayPsd.jhtml",
    "title": "修改支付密码",
    "name": "______",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pay_password",
            "description": "<p>支付密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>验证码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>类型</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/updatePayPsd.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getMyUserLog.jhtml",
    "title": "获取操作记录",
    "name": "______",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "is_qualified_member",
            "description": "<p>是否是合格会员</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getMyUserLog.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/delAddress.jhtml",
    "title": "删除收货地址",
    "name": "______",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "address_id",
            "description": "<p>收货地址ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userAddress/controller/UserAddressController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/delAddress.jhtml"
      }
    ]
  },
  {
    "type": "update",
    "url": "api/app/v1/updateUserPassWord.jhtml",
    "title": "修改用户密码",
    "name": "______",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>新密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "new_password",
            "description": "<p>新密码2</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/updateUserPassWord.jhtml"
      }
    ]
  },
  {
    "type": "update",
    "url": "api/app/v1/updateUserInfo.jhtml",
    "title": "修改用户信息",
    "name": "______",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "nickname",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "head_pic",
            "description": "<p>头像</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/updateUserInfo.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/checkVersion.jhtml",
    "title": "获取最新版本",
    "name": "______",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "terminalType",
            "description": "<p>安卓 0, 苹果1</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>0表示用户端；1表示商家端</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/index/controller/IndexController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/checkVersion.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getMyShareInfo.jhtml",
    "title": "获取我的分享信息",
    "name": "________",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "is_qualified_member",
            "description": "<p>是否是合格会员(0全部;1合格;2不合格)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getMyShareInfo.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/updateDefaultAddress.jhtml",
    "title": "修改默认收货地址",
    "name": "________",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "address_id",
            "description": "<p>收货地址ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userAddress/controller/UserAddressController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/updateDefaultAddress.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getUserDefaultAddress.jhtml",
    "title": "查询默认收货地址",
    "name": "________",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userAddress/controller/UserAddressController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getUserDefaultAddress.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/saveAddress.jhtml",
    "title": "新增/编辑收货地址",
    "name": "_________",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "address_id",
            "description": "<p>地址ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "consignee",
            "description": "<p>收货人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>联系电话</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "province",
            "description": "<p>省id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "city",
            "description": "<p>市id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "district",
            "description": "<p>区id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "town",
            "description": "<p>街道id（选填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>详细地址</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userAddress/controller/UserAddressController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/saveAddress.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/check_user_pay_password.jhtml",
    "title": "查找检验用户支付密码",
    "name": "__________",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "design",
            "description": "<p>校验码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "random",
            "description": "<p>随机数</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/check_user_pay_password.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getUserAddress.jhtml",
    "title": "获取我的收获地址列表",
    "name": "__________",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userAddress/controller/UserAddressController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getUserAddress.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getAddr.jhtml",
    "title": "级联查询市区街道名字列表",
    "name": "____________",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "parent_id",
            "description": "<p>地区父ID，查询省信息的话，parent_id传0</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/index/controller/IndexController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getAddr.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getLancher.jhtml",
    "title": "获取会员app启动图形",
    "name": "____app____",
    "group": "User",
    "version": "0.0.0",
    "filename": "api/index/controller/IndexController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getLancher.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/getUserCommission.jhtml",
    "title": "查询用户提成金额和总金额",
    "name": "getUserCommission____________",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/userInfo/controller/UserInfoController.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/getUserCommission.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/addCart.jhtml",
    "title": "添加购物车",
    "name": "________",
    "group": "cat",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "optional": false,
            "description": ""
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/CartController.java",
    "groupTitle": "cat",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/addCart.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/cartList.jhtml",
    "title": "获取购物车列表",
    "name": "________",
    "group": "cat",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>用户id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/CartController.java",
    "groupTitle": "cat",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/cartList.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/delCart.jhtml",
    "title": "根据id删除购物车里的商品",
    "name": "__id_________",
    "group": "cat",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>用户token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>购物车id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/order/controller/CartController.java",
    "groupTitle": "cat",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/delCart.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getHomePage.jhtml",
    "title": "获取App首页列表",
    "name": "__App____",
    "group": "goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页（必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cat_id",
            "description": "<p>分类ID（非必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cat_id2",
            "description": "<p>二级分类ID（非必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "keywords",
            "description": "<p>关键字（非必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "is_recommend",
            "description": "<p>是否为优选商品1：是；0：否（非必填，优选商品查询请传1）</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/goods/controller/ZjcGoodsHomePageController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getHomePage.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/queryZjcActivitiy",
    "title": "查询活动时间",
    "name": "______",
    "group": "goods",
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/queryZjcActivitiy"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getArticle.jhtml",
    "title": "获取新闻资讯",
    "name": "______",
    "group": "goods",
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getArticle.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getMessage.jhtml",
    "title": "获取系统公告",
    "name": "______",
    "group": "goods",
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getMessage.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getAd.jhtml",
    "title": "获取首页图片",
    "name": "______",
    "group": "goods",
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getAd.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/GoodsLists.jhtml",
    "title": "获取商品列表",
    "name": "______",
    "group": "goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页（必填）</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "is_hot",
            "description": "<p>热卖</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "Special_offer",
            "description": "<p>特价</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "market_price",
            "description": "<p>易物卷</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "shop_price",
            "description": "<p>现金</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "is_mixed",
            "description": "<p>混合支付</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "is_vip",
            "description": "<p>是否是vip商品。1：是，0：否</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>非必填</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/GoodsLists.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/newDate",
    "title": "查询系统时间",
    "name": "______",
    "group": "goods",
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/newDate"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/queryLimitGoods",
    "title": "查询限时购商品",
    "name": "_______",
    "group": "goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>当前页码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "start_time",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "end_time",
            "description": "<p>结束时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/queryLimitGoods"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/GoodsComments",
    "title": "获取商品评论表",
    "name": "_______",
    "group": "goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>商品id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>当前页码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/goods/controller/GoodsController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/GoodsComments"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/activity/v1/getActivityAppGoods.jhtml",
    "title": "获取商品列表",
    "name": "getActivityAppGoods______",
    "group": "goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页（必填）</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/activity/controller/AppActivityGoodsController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/activity/v1/getActivityAppGoods.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getPinTuanWxGoods.jhtml",
    "title": "易物卷拼团商品",
    "name": "getPinTuanWxGoods_______",
    "group": "goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cat_id",
            "description": "<p>一级分类</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/pintuan/controller/PinTuanController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getPinTuanWxGoods.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/queryPinTuan.jhtml",
    "title": "查询现金拼团商品",
    "name": "queryPinTuan________",
    "group": "goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "page",
            "description": "<p>第几页</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cat_id",
            "description": "<p>一级分类</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/pintuan/controller/PinTuanController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/queryPinTuan.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/activity/v1/shareIntegralDetailed.jhtml",
    "title": "查询商品首页数据",
    "name": "shareIntegralDetailed________",
    "group": "goods",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>goods_id (从商品详情跳转到首页是必需传)</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/activity/controller/AppActivityGoodsController.java",
    "groupTitle": "goods",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/activity/v1/shareIntegralDetailed.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/add_cat.jhtml",
    "title": "添加购物车",
    "name": "add_cat_____",
    "group": "hl",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>商品id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "num",
            "description": "<p>数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>用户id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/hl/controller/LoginContriller.java",
    "groupTitle": "hl",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/add_cat.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/add_detail.jhtml",
    "title": "查询类型数据",
    "name": "add_detail______",
    "group": "hl",
    "version": "0.0.0",
    "filename": "api/hl/controller/LoginContriller.java",
    "groupTitle": "hl",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/add_detail.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/add_order.jhtml",
    "title": "添加购物车",
    "name": "add_order_____",
    "group": "hl",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "project_name",
            "description": "<p>项目名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_name",
            "description": "<p>用户名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "note",
            "description": "<p>备注</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/hl/controller/LoginContriller.java",
    "groupTitle": "hl",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/add_order.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/con_order.jhtml",
    "title": "获取购物车中的数据数据",
    "name": "con_order___________",
    "group": "hl",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>用户id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/hl/controller/LoginContriller.java",
    "groupTitle": "hl",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/con_order.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/delete_cat.jhtml",
    "title": "删除购物车",
    "name": "delete_cat_____",
    "group": "hl",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "goods_id",
            "description": "<p>商品id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "num",
            "description": "<p>数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type_id",
            "description": "<p>材料id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/hl/controller/LoginContriller.java",
    "groupTitle": "hl",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/delete_cat.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getGoodsVOByMaterial.jhtml",
    "title": "根据材料名称查询产品集合",
    "name": "getGoodsVOByMaterial____________",
    "group": "hl",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>商品id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/hl/controller/LoginContriller.java",
    "groupTitle": "hl",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getGoodsVOByMaterial.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getGoodsVOByMaterial_name.jhtml",
    "title": "根据材料名称查询产品集合",
    "name": "getGoodsVOByMaterial_name____________",
    "group": "hl",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>商品id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/hl/controller/LoginContriller.java",
    "groupTitle": "hl",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getGoodsVOByMaterial_name.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/hlIndex.jhtml",
    "title": "获取商品列表",
    "name": "hlIndex______",
    "group": "hl",
    "version": "0.0.0",
    "filename": "api/hl/controller/LoginContriller.java",
    "groupTitle": "hl",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/hlIndex.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/query_detail.jhtml",
    "title": "添加产品数据",
    "name": "hlIndex______",
    "group": "hl",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type_name",
            "description": "<p>材料类型id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "material_name",
            "description": "<p>材料名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "unit",
            "description": "<p>单位</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "brand",
            "description": "<p>品牌</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "specifications_name",
            "description": "<p>规格名称多规格以,隔开</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "feedbackcontent",
            "description": "<p>备注</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/hl/controller/LoginContriller.java",
    "groupTitle": "hl",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/query_detail.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/order_detailed.jhtml",
    "title": "订单详情",
    "name": "order_detailed____",
    "group": "hl",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>订单id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/hl/controller/LoginContriller.java",
    "groupTitle": "hl",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/order_detailed.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/order_list.jhtml",
    "title": "查询订单列表",
    "name": "order_list______",
    "group": "hl",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>用户id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/hl/controller/LoginContriller.java",
    "groupTitle": "hl",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/order_list.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/storeLogin.jhtml",
    "title": "登录",
    "name": "storeLogin______",
    "group": "hl",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>电话</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/hl/controller/LoginContriller.java",
    "groupTitle": "hl",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/storeLogin.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/getIcon.jhtml",
    "title": "获取图片列表",
    "name": "______",
    "group": "icon",
    "version": "0.0.0",
    "filename": "api/icon/controller/IconController.java",
    "groupTitle": "icon",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/getIcon.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/orderComplaint.jhtml",
    "title": "订单投诉",
    "name": "____",
    "group": "order",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>订单id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_sn",
            "description": "<p>订单编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "store_id",
            "description": "<p>店铺id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "store_name",
            "description": "<p>店铺名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "info",
            "description": "<p>投诉内容</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>投诉人手机号码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "img",
            "description": "<p>图片</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/complaints/controller/ComplaintsController.java",
    "groupTitle": "order",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/orderComplaint.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/SLConvert.jhtml",
    "title": "搜了券豆兑换",
    "name": "______",
    "group": "pay",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>user_id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "voucher",
            "description": "<p>多少券</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/alipay/controller/YtpayController.java",
    "groupTitle": "pay",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/SLConvert.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/queryBank.jhtml",
    "title": "银行卡信息查询",
    "name": "_______",
    "group": "pay",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>用户id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/alipay/controller/YtpayController.java",
    "groupTitle": "pay",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/queryBank.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/updataBank.jhtml",
    "title": "银行卡信息修改",
    "name": "_______",
    "group": "pay",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>电话</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bank_card",
            "description": "<p>银行卡号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id_card",
            "description": "<p>身份证号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pay_default",
            "description": "<p>是否默认绑定</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/alipay/controller/YtpayController.java",
    "groupTitle": "pay",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/updataBank.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "api/app/v1/bankSave.jhtml",
    "title": "银行卡信息添加绑定",
    "name": "_________",
    "group": "pay",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "user_id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>电话</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bank_card",
            "description": "<p>银行卡号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id_card",
            "description": "<p>身份证号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pay_default",
            "description": "<p>是否默认绑定</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/alipay/controller/YtpayController.java",
    "groupTitle": "pay",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/bankSave.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/payBankSubmit.jhtml",
    "title": "cfca支付",
    "name": "cfca__",
    "group": "pay",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>order_id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/cfca/controller/CfcaController.java",
    "groupTitle": "pay",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/payBankSubmit.jhtml"
      }
    ]
  },
  {
    "type": "get",
    "url": "notokenapi/app/v1/payBankSubmitMax.jhtml",
    "title": "cfca支付",
    "name": "cfca__",
    "group": "pay",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "order_id",
            "description": "<p>order_id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/cfca/service/CfcaService.java",
    "groupTitle": "pay",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/payBankSubmitMax.jhtml"
      }
    ]
  },
  {
    "type": "add",
    "url": "api/app/v1/sendSMS.jhtml",
    "title": "短信发送",
    "name": "____",
    "group": "sms",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>用户手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "sms_type",
            "description": "<p>短信类型 (支付验证码 &quot;pay_password&quot; 找回密码 &quot;find_password&quot;  注册或者登录&quot;lg_or_reg&quot;)</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/message/controller/SysMessageController.java",
    "groupTitle": "sms",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/api/app/v1/sendSMS.jhtml"
      }
    ]
  },
  {
    "type": "post",
    "url": "notokenapi/app/v1/picUpload.jhtml",
    "title": "文件上传",
    "name": "______",
    "group": "upload",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "File",
            "optional": false,
            "field": "fileUpload",
            "description": ""
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "api/picUpload/controller/PicUploadController.java",
    "groupTitle": "upload",
    "sampleRequest": [
      {
        "url": "http://54.206.77.169:8080/aosuitetest/notokenapi/app/v1/picUpload.jhtml"
      }
    ]
  }
] });
