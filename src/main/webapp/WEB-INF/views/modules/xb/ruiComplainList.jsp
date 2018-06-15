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
		<li class="active"><a href="${ctx}/xb/ruiComplain/">投诉列表</a></li>
		<%--<shiro:hasPermission name="xb:ruiComplain:edit"><li><a href="${ctx}/xb/ruiComplain/form">单表添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="ruiComplain" action="${ctx}/xb/ruiComplain/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>进展状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('complain')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>提交时间：</label><input id="createTime" name="createTime" type="text" maxlength="20"
										   class="input-mini Wdate"
										   value="<fmt:formatDate value="${ruiComplain.createTime}" pattern="yyyy-MM-dd"/>"
										   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;--
				<input id="endTime" name="endTime" type="text" maxlength="20" class="input-mini Wdate"
					   value="<fmt:formatDate value="${ruiComplain.endTime}" pattern="yyyy-MM-dd"/>"
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
				<th style="text-align: center;">订单号</th>
				<th style="text-align: center;">原告id</th>
				<th style="text-align: center;">原告电话</th>
				<th style="text-align: center;">被告id</th>
				<th style="text-align: center;">被告电话</th>
				<th style="text-align: center;">提交时间</th>
				<th style="text-align: center;">处理时间</th>
				<th style="text-align: center;">当前状态</th>
				<th style="text-align: center;">是否平台处理</th>
				<shiro:hasPermission name="xb:ruiComplain:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ruiComplain">
			<tr>
				<td style="text-align: center;">${ruiComplain.orderNo}</td>
				<td style="text-align: center;">${ruiComplain.accuserId}</td>
				<td style="text-align: center;">${ruiComplain.accuserPhone}</td>
				<td style="text-align: center;">${ruiComplain.accusedId}</td>
				<td style="text-align: center;">${ruiComplain.accusedPhone}</td>
				<td style="text-align: center;"><fmt:formatDate value="${ruiComplain.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
				<td style="text-align: center;"><fmt:formatDate value="${ruiComplain.finalHandleTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
				<td style="text-align: center;"><c:if test="${ruiComplain.state==10}">新投诉</c:if>
				<c:if test="${ruiComplain.state==20}">已转达被告人</c:if>
				<c:if test="${ruiComplain.state==30}">被告人申述</c:if>
				<c:if test="${ruiComplain.state==40}">平台仲裁</c:if>
				<c:if test="${ruiComplain.state==99}">已解决</c:if></td>
				<td style="text-align: center;">${ruiComplain.isPlatformHandle==1?'是':'否'}</td>
				<shiro:hasPermission name="xb:ruiComplain:edit"><td style="text-align: center;">
    				<a href="${ctx}/xb/ruiComplain/form?id=${ruiComplain.id}">处理</a>
					<%--<a href="${ctx}/xb/ruiComplain/delete?id=${ruiComplain.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>