<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/xb/ruiUser/">用户列表</a></li>
    <shiro:hasPermission name="xb:ruiUser:edit">
        <li><a href="${ctx}/xb/ruiUser/form">用户添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="ruiUser" action="${ctx}/xb/ruiUser/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>用户名：</label><form:input path="username" htmlEscape="false" maxlength="50" class="input-medium"/></li>
        <li><label>电&nbsp;&nbsp;&nbsp;话：</label><form:input path="phone" htmlEscape="false" maxlength="50" class="input-medium"/></li>
        <li><label>已锁定：</label>
            <form:select path="isLock" class="input-medium">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li class="clearfix"></li>
        <li>
            <label>注册时间：</label><input id="createTime" name="createTime" type="text" maxlength="20"
                                             class="input-mini Wdate"
                                             value="<fmt:formatDate value="${ruiUser.createTime}" pattern="yyyy-MM-dd"/>"
                                             onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;--
            <input id="endTime" name="endTime" type="text" maxlength="20" class="input-mini Wdate"
                   value="<fmt:formatDate value="${ruiUser.endTime}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">是否可用:</label>--%>
    <%--<div class="controls">--%>
    <%--<form:select path="useable">--%>
    <%--<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
    <%--</form:select>--%>
    <%--</div>--%>
    <%--</div>--%>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th style="text-align: center;">Id</th>
        <th style="text-align: center;">用户名</th>
        <th style="text-align: center;">性别</th>
        <th style="text-align: center;">昵称</th>
        <th style="text-align: center;">电话</th>
        <th style="text-align: center;">邮件</th>
        <%--<th style="text-align: center;">是否激活</th>--%>
        <th style="text-align: center;">是否锁定</th>
        <th style="text-align: center;">注册时间</th>
        <%--<th>注册IP</th>--%>
        <th style="text-align: center;">最后登录</th>
        <%--<th>最后登录IP</th>--%>
        <shiro:hasPermission name="xb:ruiUser:edit">
            <th style="text-align: center;">操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="ruiUser">
        <tr>
            <td style="text-align: center;">${ruiUser.id}</td>
            <td style="text-align: center;">${ruiUser.username}</td>
            <%--<td style="text-align: center;"><c:if test="${ruiUser.sex==1}">男</c:if>--%>
                <%--<c:if test="${ruiUser.sex==2}">女</c:if>--%>
                <%--<c:if test="${ruiUser.sex==0}">保密</c:if>--%>
            <%--</td>--%>
            <td style="text-align: center;">${ruiUser.sex==1?'男':'女'}</td>
            <td style="text-align: center;">${ruiUser.nickname}</td>
            <td style="text-align: center;">${ruiUser.phone}</td>
            <td style="text-align: center;">${ruiUser.email}</td>
                <%--<td style="text-align: center;">${ruiUser.isActive}</td>--%>
            <td style="text-align: center;">${ruiUser.isLock==1?'是':'否'}</td>
            <td style="text-align: center;"><fmt:formatDate value="${ruiUser.createTime}"
                                                            pattern="yyyy-MM-dd hh:mm:ss"/></td>
                <%--<td>${ruiUser.createIp}</td>--%>
            <td style="text-align: center;"><fmt:formatDate value="${ruiUser.lastLoginTime}"
                                                            pattern="yyyy-MM-dd hh:mm:ss"/></td>
                <%--<td>${ruiUser.lastLoginIp}</td>--%>
            <shiro:hasPermission name="xb:ruiUser:edit">
                <td style="text-align: center;">
                    <a href="${ctx}/xb/ruiUser/form?id=${ruiUser.id}">修改</a>
                        <%--<a href="${ctx}/xb/ruiUser/delete?id=${ruiUser.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>--%>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>