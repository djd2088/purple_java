<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/xb/ruiUserLog/">用户操作日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ruiUserLog" action="${ctx}/xb/ruiUserLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名：</label><form:input path="username" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>电&nbsp;&nbsp;&nbsp;话：</label><form:input path="phone" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li>
				<label>操作时间：</label><input id="operateTime" name="operateTime" type="text" maxlength="20"
										   class="input-mini Wdate"
										   value="<fmt:formatDate value="${ruiUserLog.operateTime}" pattern="yyyy-MM-dd"/>"
										   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;--
				<input id="endTime" name="endTime" type="text" maxlength="20" class="input-mini Wdate"
					   value="<fmt:formatDate value="${ruiUserLog.endTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">用户名</th>
				<th style="text-align: center;">电话</th>
				<th style="text-align: center;">操作描述</th>
				<th style="text-align: center;">入参</th>
				<th style="text-align: center;">结果</th>
				<th style="text-align: center;">操作时间</th>
				<shiro:hasPermission name="xb:ruiUserLog:edit">
					<th style="text-align: center;">操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ruiUserLog">
			<tr>
				<td style="text-align: center;">${ruiUserLog.username}</td>
				<td style="text-align: center;">${ruiUserLog.phone}</td>
				<td style="text-align: center;">${ruiUserLog.description}</td>
				<td style="text-align: center;">${ruiUserLog.params}</td>
				<td style="text-align: center;">${ruiUserLog.result}</td>
				<td style="text-align: center;"><fmt:formatDate value="${ruiUserLog.operateTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<shiro:hasPermission name="xb:ruiUserLog:edit"><td style="text-align: center;">
					<a href="${ctx}/xb/ruiUserLog/delete?id=${ruiUserLog.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>