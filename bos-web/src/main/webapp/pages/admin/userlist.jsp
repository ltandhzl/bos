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
	// 工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查看',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '新增',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',
		text : '修改',
		iconCls : 'icon-edit',
		handler :function () {
			$("#editUserWindow").window("open");
			var rows = $('#grid').datagrid('getSelections');
			console.log(rows[0])
			$("#editUserForm").form("load",rows[0]);
		}
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}];
	//定义冻结列
	var frozenColumns = [ [ {
		field : 'id',
		checkbox : true,
		rowspan : 2
	}, {
		field : 'username',
		title : '名称',
		width : 80,
		rowspan : 2
	} ] ];


	// 定义标题栏
	var columns = [ [ {
		field : 'gender',
		title : '性别',
		width : 60,
		rowspan : 2,
		align : 'center'
	}, {
		field : 'birthdayString',
		title : '生日',
		width : 120,
		rowspan : 2,
		align : 'center'
	}, {
		field : 'roleNames',
		title : '担任角色',
		width: 80,
		rowspan : 2,
		align:'center'
	}, {
		field : 'telephone',
		title : '电话',
		width : 80,
		align : 'center',
		rowspan : 2
	},{
		field : 'station',
		title : '单位',
		width : 80,
		align : 'center',
		rowspan : 2
	}, {
		field : 'salary',
		title : '工资',
		width : 80,
		align : 'center',
		rowspan : 2
	}  ] ];
	$(function(){
		$("#editUserWindow").window("close");
		// 初始化 datagrid,
		// 创建grid
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/userAction_list",
			idField : 'id', 
			frozenColumns : frozenColumns,
			columns : columns,
			onClickRow : onClickRow,
			onDblClickRow : doDblClickRow
		});
		$('#editUserWindow').window({
			title: '修改用户',
			width: 800,
			modal: true,
			shadow: true,
			closed: true,
			height: 500,
			resizable:false
		});
		
		$("body").css({visibility:"visible"});
		
	});
	// 双击
	function doDblClickRow(rowIndex, rowData) {
		var items = $('#grid').datagrid('selectRow',rowIndex);
		doView();
	}
	// 单击
	function onClickRow(rowIndex){

	}
	
	function doAdd() {
		location.href="${pageContext.request.contextPath}/page_admin_userinfo";
	}

	function doView() {
		alert("编辑用户");
		var item = $('#grid').datagrid('getSelected');
		console.info(item);
		//window.location.href = "edit.html";
	}

	function doDelete() {
		alert("删除用户");
		var ids = [];
		var items = $('#grid').datagrid('getSelections');
		for(var i=0; i<items.length; i++){
		    ids.push(items[i].id);	    
		}
			

		$('#grid').datagrid('reload');
		$('#grid').datagrid('uncheckAll');
	}
	
</script>		
</head>
<body class="easyui-layout" style="visibility:hidden;">
    <div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="用户添加修改" id="editUserWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >修改</a>
				<script>
					$(function () {
						$("#edit").click(function () {
							var flag=$("#editUserForm").form("validate");
							if (flag){
								$("#editUserForm").submit();
							}
						})
					})
				</script>
			</div>
		</div>

		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editUserForm" action="${pageContext.request.contextPath}/userAction_edit" method="post">
				<input type="hidden" name="id">
				<table class="table-edit"  width="95%" align="center">
					<tr class="title"><td colspan="4">基本信息</td></tr>
					<tr><td>用户名:</td><td><input type="text" name="username" id="username" class="easyui-validatebox" required="true" /></td>
						<td>口令:</td><td><input type="password" name="password" id="password" class="easyui-validatebox" required="true" validType="minLength[5]" /></td></tr>
					<tr class="title"><td colspan="4">其他信息</td></tr>
					<tr><td>工资:</td><td><input type="text" name="salary" id="salary" class="easyui-numberbox" /></td>
						<td>生日:</td><td><input type="text" name="birthdayString" id="birthday" class="easyui-datebox" /></td></tr>
					<tr><td>性别:</td><td>
						<select name="gender" id="gender" class="easyui-combobox" style="width: 150px;">
							<option value="">请选择</option>
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</td>
						<td>单位:</td><td>
							<select name="station" id="station" class="easyui-combobox" style="width: 150px;">
								<option value="">请选择</option>
								<option value="总公司">总公司</option>
								<option value="分公司">分公司</option>
								<option value="厅点">厅点</option>
								<option value="基地运转中心">基地运转中心</option>
								<option value="营业所">营业所</option>
							</select>
						</td></tr>
					<tr>
						<td>联系电话</td>
						<td colspan="3">
							<input type="text" data-options="validType:'telephone'" name="telephone" id="telephone" class="easyui-validatebox" required="true" />
							<script>
								$(function () {
									var reg=/^1[3|4|5|7|8][0-9]{9}$/;
									$.extend($.fn.validatebox.defaults.rules, {
										telephone: {
											validator: function(value,param){
												return reg.test(value);
											},
											message: '手机号输入有误！'
										}
									});
								})

							</script>
						</td>
					</tr>
					<tr><td>备注:</td><td colspan="3"><textarea style="width:80%"></textarea></td></tr>
					<tr>
						<td>选择角色</td>
						<td id="selectRole">
							<script>
								$.post(
										"${pageContext.request.contextPath}/userAction_ajaxList",
										function (data) {
											for (var i=0;i<data.length;i++){
												$("#selectRole").append("<input type='radio' id='"+data[i].id+"' class='checkboxInput' name='roleIds' value='"+data[i].id+"'/><label for='"+data[i].id+"'>"+data[i].name+"</label>");
											}
										}
								)
							</script>
						</td>
					</tr>

				</table>
			</form>
		</div>
	</div>

</body>
</html>