<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/xb/ruiUser/">用户列表</a></li>
    <li class="active"><a href="${ctx}/xb/ruiUser/form?id=${ruiUser.id}">用户<shiro:hasPermission
            name="xb:ruiUser:edit">${not empty ruiUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="xb:ruiUser:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="ruiUser" action="${ctx}/xb/ruiUser/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">用户名：</label>
        <div class="controls">
            <form:input path="username" htmlEscape="false" maxlength="50" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
    <label class="control-label">密码：</label>
    <div class="controls">
    <form:input path="password" htmlEscape="false" maxlength="20" class="input-xlarge "/>
    </div>
    </div>
    <div class="control-group">
        <label class="control-label">性别：</label>
        <div class="controls">
            <form:select path="sex" class="input-medium">
                <form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">昵称：</label>
        <div class="controls">
            <form:input path="nickname" htmlEscape="false" maxlength="20" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">电话：</label>
        <div class="controls">
            <form:input path="phone" htmlEscape="false" maxlength="20" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">邮箱：</label>
        <div class="controls">
            <form:input path="email" htmlEscape="false" maxlength="100" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">是否激活：</label>
        <div class="controls">
            <form:select path="isActive" class="input-medium">
                <form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">是否锁定：</label>
        <div class="controls">
            <form:select path="isLock" class="input-medium">
                <form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <%--<div class="control-group">--%>
        <%--<label class="control-label">头像：</label>--%>
        <%--<div class="controls">--%>
            <%--<form:input path="avatar" htmlEscape="false" maxlength="600" class="input-xlarge "/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
        <%--<label class="control-label">创建时间：</label>--%>
        <%--<div class="controls">--%>
            <%--<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
                   <%--value="<fmt:formatDate value="${ruiUser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
                   <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
        <%--<label class="control-label">创建时IP：</label>--%>
        <%--<div class="controls">--%>
            <%--<form:input path="createIp" htmlEscape="false" maxlength="50" class="input-xlarge "/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
        <%--<label class="control-label">最后登录时间：</label>--%>
        <%--<div class="controls">--%>
            <%--<input name="lastLoginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
                   <%--value="<fmt:formatDate value="${ruiUser.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
                   <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
        <%--<label class="control-label">最后登录IP：</label>--%>
        <%--<div class="controls">--%>
            <%--<form:input path="lastLoginIp" htmlEscape="false" maxlength="50" class="input-xlarge "/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
        <%--<label class="control-label">更新时间：</label>--%>
        <%--<div class="controls">--%>
            <%--<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
                   <%--value="<fmt:formatDate value="${ruiUser.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
                   <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">pre1：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="pre1" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">pre2：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="pre2" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">pre3：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="pre3" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <div class="form-actions">
        <shiro:hasPermission name="xb:ruiUser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                           value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>