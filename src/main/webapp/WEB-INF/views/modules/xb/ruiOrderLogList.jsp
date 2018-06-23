<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单操作日志管理</title>
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
		<li ><a href="${ctx}/xb/ruiOrder/">订单列表</a></li>
		<li class="active"><a href="${ctx}/xb/ruiOrderLog/">订单操作日志列表</a></li>
		<%--<shiro:hasPermission name="xb:ruiOrderLog:edit"><li><a href="${ctx}/xb/ruiOrderLog/form">单表添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="ruiOrderLog" action="${ctx}/xb/ruiOrderLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单号：</label><form:input path="orderNo" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li>
				<label>操作时间：</label><input id="createTime" name="createTime" type="text" maxlength="20"
										   class="input-mini Wdate"
										   value="<fmt:formatDate value="${ruiOrderLog.createTime}" pattern="yyyy-MM-dd"/>"
										   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;--
				<input id="endTime" name="endTime" type="text" maxlength="20" class="input-mini Wdate"
					   value="<fmt:formatDate value="${ruiOrderLog.endTime}" pattern="yyyy-MM-dd"/>"
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
				<%--<th style="text-align: center;">订单Id</th>--%>
				<th style="text-align: center;">订单号</th>
				<th style="text-align: center;">操作记录</th>
				<th style="text-align: center;">买家Id</th>
				<th style="text-align: center;">操作时间</th>

				<%--<shiro:hasPermission name="xb:ruiOrderLog:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ruiOrderLog">
			<tr>
				<%--<td style="text-align: center;">${ruiOrderLog.orderId}</td>--%>
				<td style="text-align: center;">${ruiOrderLog.orderNo}</td>
				<td style="text-align: center;">${ruiOrderLog.logMsg}</td>
				<td style="text-align: center;">${ruiOrderLog.logOperaterId}</td>
				<td style="text-align: center;"><fmt:formatDate value="${ruiOrderLog.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<%--<shiro:hasPermission name="xb:ruiOrderLog:edit"><td>--%>
    				<%--<a href="${ctx}/xb/ruiOrderLog/form?id=${ruiOrderLog.id}">修改</a>--%>
					<%--<a href="${ctx}/xb/ruiOrderLog/delete?id=${ruiOrderLog.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>--%>
				<%--</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>