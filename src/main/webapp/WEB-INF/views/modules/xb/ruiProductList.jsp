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
		<li class="active"><a href="${ctx}/xb/ruiProduct/">商品列表</a></li>
		<%--<shiro:hasPermission name="xb:ruiProduct:edit"><li><a href="${ctx}/xb/ruiProduct/form">商品添加</a></li--%>
		<%--></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="ruiProduct" action="${ctx}/xb/ruiProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>归属类目：</label>
				<form:select path="categoryId" class="input-medium">
					<form:option value="" label=""></form:option>
					<form:options items="${fns:getDictList('sys_office_grade')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>上架状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""></form:option>
					<form:options items="${fns:getDictList('online')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>审核状态：</label>
				<form:select path="auditState" class="input-medium">
					<form:option value="" label=""></form:option>
					<form:options items="${fns:getDictList('audit')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</li>
			<li class="clearfix"></li>
			<li><label>锁定状态：</label>
				<form:select path="isLock" class="input-medium">
					<form:option value="" label=""></form:option>
					<form:options items="${fns:getDictList('isLock')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>商品名：</label><form:input path="productName"  htmlEscape="false" maxlength="50"
											   class="input-medium"/></li>
			<li><label>所属用户：</label><form:input path="owner" placeholder="手机号/用户名" htmlEscape="false"
												maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">商品名称</th>
				<th style="text-align: center;">所属类目</th>
				<th style="text-align: center;">上架状态</th>
				<th style="text-align: center;">上下架备注</th>
				<th style="text-align: center;">上架时间</th>
				<th style="text-align: center;">下架时间</th>
				<th style="text-align: center;">审核状态</th>
				<th style="text-align: center;">审核备注</th>
				<th style="text-align: center;">所属用户名</th>
				<th style="text-align: center;">用户手机</th>
				<th style="text-align: center;">运费</th>
				<th style="text-align: center;">是否推荐</th>
				<th style="text-align: center;">是否锁定</th>
				<shiro:hasPermission name="xb:ruiProduct:edit"><th style="text-align: center;
">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ruiProduct">
			<tr>
				<td style="text-align: center;">${ruiProduct.productName}</td>
				<td style="text-align: center;">${ruiProduct.categoryName}</td>
				<td style="text-align: center;"><c:if test="${ruiProduct.state==1}">已上架</c:if>
					<c:if test="${ruiProduct.state==0}">未上架</c:if>
					<c:if test="${ruiProduct.state==10}">禁售</c:if></td>
				<td style="text-align: center;">${ruiProduct.stateRemark}</td>
				<td style="text-align: center;">${ruiProduct.onlineTime}</td>
				<td style="text-align: center;">${ruiProduct.offlineTime}</td>
				<td style="text-align: center;"><c:if test="${ruiProduct.auditState==1}">通过</c:if>
					<c:if test="${ruiProduct.auditState==0}">未通过</c:if>
					<c:if test="${ruiProduct.auditState==10}">审核中</c:if></td>
				<td style="text-align: center;">${ruiProduct.auditRemark}</td>
				<td style="text-align: center;">${ruiProduct.username}</td>
				<td style="text-align: center;">${ruiProduct.phone}</td>
				<td style="text-align: center;">${ruiProduct.freight}</td>
				<td style="text-align: center;">${ruiProduct.isCommend}</td>
				<td style="text-align: center;">${ruiProduct.isLock==1?'是':"否"}</td>
				<shiro:hasPermission name="xb:ruiProduct:edit"><td style="text-align: center;">
    				<a href="${ctx}/xb/ruiProduct/form?id=${ruiProduct.id}">审核与锁定</a>
					<%--<a href="${ctx}/xb/ruiProduct/delete?id=${ruiProduct.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>