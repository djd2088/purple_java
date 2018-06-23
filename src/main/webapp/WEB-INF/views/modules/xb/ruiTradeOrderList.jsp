<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易管理</title>
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
		<li class="active"><a href="${ctx}/xb/ruiTradeOrder/">交易列表</a></li>
		<%--<shiro:hasPermission name="xb:ruiTradeOrder:edit"><li><a href="${ctx}/xb/ruiTradeOrder/form">单表添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="ruiTradeOrder" action="${ctx}/xb/ruiTradeOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单号：</label><form:input path="orderNo" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>交易号：</label><form:input path="tradeNo" htmlEscape="false" maxlength="50"
											   class="input-medium"/></li>
			<li><label>支付号：</label><form:input path="payNo" htmlEscape="false" maxlength="50"
											   class="input-medium"/></li>
			<li class="clearfix"></li>
			<li><label>交易状态：</label>
				<form:select path="tradeState" class="input-medium">
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
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">订单号</th>
				<th style="text-align: center;">交易号</th>
				<th style="text-align: center;">支付号</th>
				<th style="text-align: center;">支付方式</th>
				<th style="text-align: center;">总金额</th>
				<th style="text-align: center;">当前状态</th>
				<th style="text-align: center;">支付时间</th>
				<th style="text-align: center;">操作时间</th>
				<%--<shiro:hasPermission name="xb:ruiTradeOrder:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ruiTradeOrder">
			<tr>
				<td style="text-align: center;">${ruiTradeOrder.orderNo}</td>
				<td style="text-align: center;">${ruiTradeOrder.tradeNo}</td>
				<td style="text-align: center;">${ruiTradeOrder.payNo}</td>
				<td style="text-align: center;">${ruiTradeOrder.payMethod == 'ALI_PAY'?'支付宝':'微信'}</td>
				<td style="text-align: center;">${ruiTradeOrder.toltalFee}</td>
				<td style="text-align: center;"><c:if test="${ruiTradeOrder.tradeState==10}">未支付</c:if>
					<c:if test="${ruiTradeOrder.tradeState==20}">已支付</c:if>
					<c:if test="${ruiTradeOrder.tradeState==30}">已发货</c:if>
					<c:if test="${ruiTradeOrder.tradeState==40}">已完成</c:if>
					<c:if test="${ruiTradeOrder.tradeState==50}">退款中</c:if>
					<c:if test="${ruiTradeOrder.tradeState==60}">退货中</c:if></td>
				<%--<td style="text-align: center;"><fmt:formatDate value="${ruiTradeOrder.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>--%>
				<td style="text-align: center;"><fmt:formatDate value="${ruiTradeOrder.payTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<td style="text-align: center;"><fmt:formatDate value="${ruiTradeOrder.updateTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>

				<%--<shiro:hasPermission name="xb:ruiTradeOrder:edit"><td>--%>
    				<%--<a href="${ctx}/xb/ruiTradeOrder/form?id=${ruiTradeOrder.id}">修改</a>--%>
					<%--<a href="${ctx}/xb/ruiTradeOrder/delete?id=${ruiTradeOrder.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>--%>
				<%--</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>