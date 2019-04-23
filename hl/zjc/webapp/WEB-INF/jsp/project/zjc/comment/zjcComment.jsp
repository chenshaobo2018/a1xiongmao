<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp"%>

<aos:html title="用户评价" base="http" lib="ext">
	<aos:body>
	</aos:body>
</aos:html>

<aos:onready>
	<aos:viewport layout="fit">
		<aos:gridpanel id="g_comment" url="zjcCommentService.listComment" onrender="g_comment_query" >
			<aos:menu>
				<aos:menuitem text="删除" onclick="g_comment_del" icon="del.png" />
				<aos:menuitem xtype="menuseparator" />
				<aos:menuitem text="刷新" onclick="#g_comment_store.reload();" icon="refresh.png" />
			</aos:menu>
			<aos:docked forceBoder="0 0 1 0" >
				<aos:dockeditem onclick="g_comment_del" text="删除" icon="del.png" />
				<aos:dockeditem xtype="tbseparator"/>
				<aos:triggerfield emptyText="真实姓名" id="username" onenterkey="g_comment_query"
					trigger1Cls="x-form-search-trigger" onTrigger1Click="g_comment_query" width="180" />
				<aos:triggerfield emptyText="评论内容" id="content" onenterkey="g_comment_query"
				trigger1Cls="x-form-search-trigger" onTrigger1Click="g_comment_query" width="180" />
				<aos:triggerfield emptyText="商品ID" id="goods_id" onenterkey="g_comment_query"
				trigger1Cls="x-form-search-trigger" onTrigger1Click="g_comment_query" width="180" />
				<aos:triggerfield emptyText="商品名称" id="goods_name" onenterkey="g_comment_query"
				trigger1Cls="x-form-search-trigger" onTrigger1Click="g_comment_query" width="180" />
				<aos:triggerfield emptyText="店铺名称" id="store_name" onenterkey="g_comment_query"
				trigger1Cls="x-form-search-trigger" onTrigger1Click="g_comment_query" width="180" />
			</aos:docked>
			<aos:selmodel type="checkbox" mode="multi" />
			<aos:column header="评论id" dataIndex="comment_id" width="50"  hidden="true"/>
			<aos:column header="真实姓名" dataIndex="username" celltip="true" width="80" />
			<aos:column header="商品id" dataIndex="goods_id"  celltip="true" width="50" />
			<aos:column header="商品名称" dataIndex="goods_name"  celltip="true" width="150" />
			<aos:column header="店铺名称" dataIndex="store_name"  celltip="true" width="100" />
			<aos:column header="评论内容" dataIndex="content" celltip="true" width="400" />
			<aos:column header="追加评论" dataIndex="again_content" celltip="true" width="400" />
			<aos:column header="商品ID" dataIndex="goods_id" width="50" />
			<aos:column header="是否显示" dataIndex="is_show" width="50" celltip="true"  rendererField="is" />
			<aos:column header="评论时间" dataIndex="add_time" celltip="true" minWidth="100"  />
			<aos:column header="ip地址" dataIndex="ip_address" celltip="true"  />
		</aos:gridpanel>
	</aos:viewport>
	<script type="text/javascript">

            //查询参数列表
            function g_comment_query() {
                var params = {
               		username : username.getValue(),
               		content : content.getValue(),
               		goods_id : goods_id.getValue(),
               		goods_name : goods_name.getValue(),
               		store_name : store_name.getValue()
                };
                g_comment_store.getProxy().extraParams = params;
                g_comment_store.loadPage(1);
            }

            //删除参数
            function g_comment_del() {
            	var rows = AOS.rows(g_comment);
                if (rows === 0) {
                    AOS.tip('删除前请先选中数据。');
                    return;
                }
                var msg = AOS.merge('确认要删除选中的[{0}]条数据吗？', rows);
                AOS.confirm(msg, function (btn) {
                    if (btn === 'cancel') {
                        AOS.tip('删除操作被取消。');
                        return;
                    }
                    AOS.ajax({
                        url: 'zjcCommentService.deleteComment',
                        params: {
                        	aos_rows:  AOS.selection(g_comment, 'comment_id')
                        },
                        ok: function (data) {
                            AOS.tip(data.appmsg);
                            g_comment_store.reload();
                        }
                    });
                });

            }

        </script>
</aos:onready>
