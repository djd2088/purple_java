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
		<li class="active"><a href="${ctx}/xb/ruiProductPicture/">单表列表</a></li>
		<shiro:hasPermission name="xb:ruiProductPicture:edit"><li><a href="${ctx}/xb/ruiProductPicture/form">单表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ruiProductPicture" action="${ctx}/xb/ruiProductPicture/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>图片名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>图片名称</th>
				<shiro:hasPermission name="xb:ruiProductPicture:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ruiProductPicture">
			<tr>
				<td><a href="${ctx}/xb/ruiProductPicture/form?id=${ruiProductPicture.id}">
					${ruiProductPicture.name}
				</a></td>
				<shiro:hasPermission name="xb:ruiProductPicture:edit"><td>
    				<a href="${ctx}/xb/ruiProductPicture/form?id=${ruiProductPicture.id}">修改</a>
					<a href="${ctx}/xb/ruiProductPicture/delete?id=${ruiProductPicture.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>