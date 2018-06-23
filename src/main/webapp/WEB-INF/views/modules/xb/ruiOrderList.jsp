<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		<li class="active"><a href="${ctx}/xb/ruiOrder/">订单列表</a></li>
		<%--<shiro:hasPermission name="xb:ruiOrder:edit"><li><a href="${ctx}/xb/ruiOrder/form">单表添加</a></li></shiro:hasPermission>--%>
		<li><a href="${ctx}/xb/ruiOrderLog/">订单操作日志</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ruiOrder" action="${ctx}/xb/ruiOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<li><label>订单号：</label><form:input path="orderNo" htmlEscape="false" maxlength="50" class="input-medium"/></li>
		<li><label>订单状态：</label>
			<form:select path="state" class="input-medium">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('order_state')}" itemLabel="label" itemValue="value"
							  htmlEscape="false"/>
			</form:select>
		</li>
		<li>
			<label>生成时间：</label><input id="createTime" name="createTime" type="text" maxlength="20"
									   class="input-mini Wdate"
									   value="<fmt:formatDate value="${ruiOrder.createTime}" pattern="yyyy-MM-dd"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;--
			<input id="endTime" name="endTime" type="text" maxlength="20" class="input-mini Wdate"
				   value="<fmt:formatDate value="${ruiOrder.endTime}" pattern="yyyy-MM-dd"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		<li class="clearfix"></li>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">订单号</th>
				<th style="text-align: center;">买家id</th>
				<th style="text-align: center;">买家名称</th>
				<th style="text-align: center;">卖家id</th>
				<th style="text-align: center;">卖家名称</th>
				<th style="text-align: center;">商品金额</th>
				<th style="text-align: center;">订单金额</th>
				<th style="text-align: center;">订单状态</th>
				<th style="text-align: center;">运费</th>
				<th style="text-align: center;">生成时间</th>
				<th style="text-align: center;">支付时间</th>
				<th style="text-align: center;">完成时间</th>
				<%--<shiro:hasPermission name="xb:ruiOrder:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ruiOrder">
			<tr>
				<td style="text-align: center;">${ruiOrder.orderNo}</td>
				<td style="text-align: center;">${ruiOrder.buyerId}</td>
				<td style="text-align: center;">${ruiOrder.buyerName}</td>
				<td style="text-align: center;">${ruiOrder.sellerId}</td>
				<td style="text-align: center;">${ruiOrder.sellerName}</td>
				<td style="text-align: center;">${ruiOrder.goodsAmount}</td>
				<td style="text-align: center;">${ruiOrder.orderAmount}</td>
				<td style="text-align: center;"><c:if test="${ruiOrder.state==10}">未支付</c:if>
					<c:if test="${ruiOrder.state==20}">已支付</c:if>
					<c:if test="${ruiOrder.state==30}">已发货</c:if>
					<c:if test="${ruiOrder.state==40}">已完成</c:if>
					<c:if test="${ruiOrder.state==50}">退款中</c:if>
					<c:if test="${ruiOrder.state==60}">退货中</c:if></td>
				<td style="text-align: center;">${ruiOrder.freight}</td>
				<td style="text-align: center;"><fmt:formatDate value="${ruiOrder.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<td style="text-align: center;"><fmt:formatDate value="${ruiOrder.payTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<td style="text-align: center;"><fmt:formatDate value="${ruiOrder.finishTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<%--<shiro:hasPermission name="xb:ruiOrder:edit"><td>--%>
    				<%--<a href="${ctx}/xb/ruiOrder/form?id=${ruiOrder.id}">修改</a>--%>
					<%--<a href="${ctx}/xb/ruiOrder/delete?id=${ruiOrder.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>--%>
				<%--</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>