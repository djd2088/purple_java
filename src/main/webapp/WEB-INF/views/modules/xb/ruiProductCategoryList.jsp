<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>类目管理</title>
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

        function queryCategory(th){
            $("#parentId").empty();
		    $.ajax({
				url:'${ctx}/xb/ruiProductCategory/getParentByLevel',
				data:{
				  level:$(th).val()
				},
                type:'get',
                cache:true,
                dataType:'json',
                success:function(data) {
                    $.each(data.items, function(index,item){
                        $("#parentId").append("<option value='"+item.id+"'>"+item.name+"</option>")
                    });
                },
                error : function() {
                    alert("异常！");
                }
            });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/xb/ruiProductCategory/">类目列表</a></li>
		<shiro:hasPermission name="xb:ruiProductCategory:edit"><li><a href="${ctx}/xb/ruiProductCategory/form">类目添加
		</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ruiProductCategory" action="${ctx}/xb/ruiProductCategory/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类目级别：</label>
				<form:select path="categoryLevel" class="input-medium" onchange="queryCategory(this)">
					<form:option value="" label=""></form:option>
					<form:options items="${fns:getDictList('sys_office_grade')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>父级类目：</label>
				<form:select path="parentId" class="input-medium">
					<form:option value="" label=""></form:option>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
                <th style="text-align: center;">类目名称</th>
                <th style="text-align: center;">父类目</th>
                <th style="text-align: center;">类目等级</th>
				<shiro:hasPermission name="xb:ruiProductCategory:edit"><th style="text-align: center">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ruiProductCategory">
			<tr>
                <td style="text-align: center;">${ruiProductCategory.name}</td>
                <td style="text-align: center;">${ruiProductCategory.parentName}</td>
                <td style="text-align: center;">${ruiProductCategory.categoryLevel}</td>
                <shiro:hasPermission name="xb:ruiProductCategory:edit"><td style="text-align: center">
					<c:if test="${ruiProductCategory.categoryLevel == 2}">
						<c:if test="${ruiProductCategory.isCommend == 1}">
                            <a href="${ctx}/xb/ruiProductCategory/commend?id=${ruiProductCategory.id}&isCommend=0"
                               onclick="return confirmx('确认要取消推荐吗？', this.href)">取消推荐</a>
						</c:if>
						<c:if test="${ruiProductCategory.isCommend == 0}">
                            <a href="${ctx}/xb/ruiProductCategory/commend?id=${ruiProductCategory.id}&isCommend=1"
                               onclick="return confirmx('确认设置热门推荐吗？', this.href)">热门推荐</a>

						</c:if>
					</c:if>
    				<a href="${ctx}/xb/ruiProductCategory/form?id=${ruiProductCategory.id}">修改</a>
					<a href="${ctx}/xb/ruiProductCategory/delete?id=${ruiProductCategory.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>