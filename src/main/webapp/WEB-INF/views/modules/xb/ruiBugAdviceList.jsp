<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>意见管理</title>
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
		<li class="active"><a href="${ctx}/xb/ruiBugAdvice/">意见列表</a></li>
		<%--<shiro:hasPermission name="xb:ruiBugAdvice:edit"><li><a href="${ctx}/xb/ruiBugAdvice/form">意见添加</a></li--%>
		<%--></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="ruiBugAdvice" action="${ctx}/xb/ruiBugAdvice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('advice')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>已处理：</label>
				<form:select path="isDeal" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>提交时间：</label><input id="createTime" name="createTime" type="text" maxlength="20"
										   class="input-mini Wdate"
										   value="<fmt:formatDate value="${ruiBugAdvice.createTime}" pattern="yyyy-MM-dd"/>"
										   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;--
				<input id="endTime" name="endTime" type="text" maxlength="20" class="input-mini Wdate"
					   value="<fmt:formatDate value="${ruiBugAdvice.endTime}" pattern="yyyy-MM-dd"/>"
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
                <%--<th style="text-align: center;">用户名</th>--%>
                <th style="text-align: center;">电话</th>
                <th style="text-align: center;">意见内容</th>
                <th style="text-align: center;">是否处理</th>
				<th style="text-align: center;">提交时间</th>
				<th style="text-align: center;">处理时间</th>
				<shiro:hasPermission name="xb:ruiBugAdvice:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ruiBugAdvice">
			<tr>
                <%--<td style="text-align: center;">${ruiBugAdvice.username}</td>--%>
                <td style="text-align: center;">${ruiBugAdvice.phone}</td>
                <td style="text-align: center;">${ruiBugAdvice.content}</td>
                <td style="text-align: center;">${ruiBugAdvice.isDeal==1?'是':'否'}</td>
				<td style="text-align: center;"><fmt:formatDate value="${ruiBugAdvice.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<td style="text-align: center;"><fmt:formatDate value="${ruiBugAdvice.dealTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<shiro:hasPermission name="xb:ruiBugAdvice:edit"><td style="text-align: center;">
					<a href="${ctx}/xb/ruiBugAdvice/deal?id=${ruiBugAdvice.id}" onclick="return confirmx('确认已处理该意见吗？', this.href)">处理</a>

    				<%--<a href="${ctx}/xb/ruiBugAdvice/form?id=${ruiBugAdvice.id}">修改</a>--%>
					<%--<a href="${ctx}/xb/ruiBugAdvice/delete?id=${ruiBugAdvice.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>