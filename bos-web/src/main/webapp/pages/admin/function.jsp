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
<script type="text/javascript">
	$(function(){
		$("#editFunctionWindow").window("close");
		$("#grid").datagrid({
			toolbar : [
				{
					id : 'add',
					text : '添加权限',
					iconCls : 'icon-add',
					handler : function(){
						location.href='${pageContext.request.contextPath}/page_admin_function_add';
					}
				},
				{
					id : 'edit',
					text : '修改权限',
					iconCls : 'icon-edit',
					handler : function(){
						var rows = $("#grid").datagrid("getSelections");
						$("input[name=id]").val(rows[0].id);
						if(rows.length!==1){
							$.messager.alert("修改权限","请选择一条需要修改的权限!","warning");
						}else{
							$("#editFunctionWindow").window("open");
							$.post(
									"${pageContext.request.contextPath}/functionAction_findParentNameById",
									{id:rows[0].id},
									function (data) {
										$(".parentNode .combo-text").val(data.parentFunction.name);
									}
							);
							$(".parentNode .combo-value").val(rows[0].pId);
							$("#editFunctionForm").form("load",rows[0]);

						}
					}
				}
			],
			url:'${pageContext.request.contextPath}/functionAction_list',
			pagination : true,
			fit:true,
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
			  },  
			  {
				  field : 'generatemenu',
				  title : '是否生成菜单',
				  width : 200,
				formatter : function(data,row, index){
					if(data=="1"){
						return "是";
					}else{
						return "否";
				     }
				}
			  },  
			  {
				  field : 'zindex',
				  title : '优先级',
				  width : 200
			  },  
			  {
				  field : 'page',
				  title : '路径',
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

<div class="easyui-window" title="权限修改" id="editFunctionWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
	<div style="height:31px;overflow:hidden;" split="false" border="false" >
		<div class="datagrid-toolbar">
			<a id="editFunction" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >修改</a>
			<script>
				$(function () {
					$("#editFunction").click(function () {
							var flag=$("#editFunctionForm").form("validate");
							if (flag){
								$("#editFunctionForm").submit();
							}
					})
				})
			</script>
		</div>
	</div>

	<div style="overflow:auto;padding:5px;" border="false">
		<form id="editFunctionForm" method="post" action="${pageContext.request.contextPath}/functionAction_edit">
			<input type="hidden" name="id">
			<table class="table-edit" width="80%" align="center">
				<tr class="title">
					<td colspan="2">功能权限信息</td>
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
					<td>访问路径</td>
					<td><input type="text" name="page"  /></td>
				</tr>
				<tr>
					<td>是否生成菜单</td>
					<td>
						<select name="generateMenu" class="easyui-combobox">
							<option value="0">不生成</option>
							<option value="1">生成</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>优先级</td>
					<td>
						<input type="text" name="zindex" class="easyui-numberbox"  />
					</td>
				</tr>
				<tr class="parentNode">
					<td>父功能点</td>
					<td >
						<%--							<input name="parentFunction.id"--%>
						<%--								   class="easyui-combobox"--%>
						<%--								   data-options="valueField:'id',textField:'name',url:'${pageContext.request.contextPath}/functionAction_ajaxList'"/>--%>
						<%--                               这里的parentFunction.id指的是保存到后台的这条数据的pid--%>
						<input class="easyui-combotree" name="parentFunction.id"
							   data-options="url:'${pageContext.request.contextPath}/functionAction_ajaxList'"
							   style="width:170px;">
						<%--	                                树形结构必须包含怕pid--%>

					</td>
				</tr>
				<tr>
					<td>描述</td>
					<td>
						<textarea name="description" rows="4" cols="60"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

</body>
</html>