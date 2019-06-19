<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<!-- 导入ztree类库 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
	type="text/css" />
<script
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"
	type="text/javascript"></script>	
<script type="text/javascript">

	function selectAllChecked(data,arr){
		for (var i=0;i<data.length;i++){
			arr.push(data[i].id);
			if (data[i].children.length>0){
				selectAllChecked(data[i].children,arr);
			}
		}
	}

	$(function(){
		$("#editRoleWindow").window("close");
		// 数据表格属性
		$("#grid").datagrid({
			toolbar : [
				{
					id : 'add',
					text : '添加角色',
					iconCls : 'icon-add',
					handler : function(){
						location.href='${pageContext.request.contextPath}/page_admin_role_add.action';
					}
				},
				{
					id : 'edit',
					text : '修改角色',
					iconCls : 'icon-edit',
					handler : function(){
						var rows = $("#grid").datagrid("getSelections");

						if (rows.length!==1){
							$.messager.alert("修改角色失败","请选择一条角色记录修改！","warning");
						} else{
							$("#editRoleWindow").window("open");
							// 授权树初始化
							var setting = {
								data : {
									key : {
										title : "t"
									},
									simpleData : {
										enable : true
									}
								},
								check : {
									enable: true
								}
							};
							$.ajax({
								url : '${pageContext.request.contextPath}/functionAction_ajaxList',
								type : 'POST',
								dataType : 'json',
								success : function(data) {
									 $.fn.zTree.init($("#functionTree"),setting,data);
									$("#editRoleForm").form("load",rows[0]);
									//var arr=[];
									<%--$.post(--%>
									<%--		"${pageContext.request.contextPath}/roleAction_getDistinctList",--%>
									<%--		{id:rows[0].id},--%>
									<%--		function (data) {--%>
									<%--			//selectAllChecked(data.functions,arr);--%>
									<%--			--%>
									<%--		}--%>
									<%--);--%>

								},
								error : function(msg) {
									alert('树加载异常!');
								}
							});
						}
					}
				}
			],
			url : '${pageContext.request.contextPath}/roleAction_list',
			columns : [[
				{
					field : 'id',
					title : '编号',
					width : 200,
					checkbox:true
				},
				{
					field : 'name',
					title : '名称',
					width : 200
				}, 
				{
					field : 'description',
					title : '描述',
					width : 200
				} 
			]]
		});
	});
</script>	
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table id="grid"></table>
	</div>

	<div class="easyui-window" title="角色修改" id="editRoleWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="editRole" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >修改</a>
			</div>
		</div>

		<div style="overflow:auto;padding:5px;" border="false">
			<form id="editRoleForm" method="post" action="${pageContext.request.contextPath}/roleAction_edit">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">角色信息</td>
					</tr>
					<tr>
						<td width="200">关键字</td>
						<td>
							<input type="text" name="code" class="easyui-validatebox" data-options="required:true" />
						</td>
					</tr>
					<tr>
						<td>名称</td>
						<td><input type="text" name="name" class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>描述</td>
						<td>
							<textarea name="description" rows="4" cols="60"></textarea>
						</td>
					</tr>
					<tr>
						<td>授权</td>
						<td>
							<ul id="functionTree" class="ztree"></ul>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>