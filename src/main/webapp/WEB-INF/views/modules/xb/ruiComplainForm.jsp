<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>单表管理</title>
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
    <li><a href="${ctx}/xb/ruiComplain/">投诉列表</a></li>
    <li class="active"><a href="${ctx}/xb/ruiComplain/form?id=${ruiComplain.id}">投诉<shiro:hasPermission
            name="xb:ruiComplain:edit">${not empty ruiComplain.id?'处理':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="xb:ruiComplain:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="ruiComplain" action="${ctx}/xb/ruiComplain/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">orderNo：</label>
        <div class="controls">
            <form:input path="orderNo" htmlEscape="false" class="input-xlarge "/>
        </div>
    </div>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">原告id：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="accuserId" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">accuser_name：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="accuserName" htmlEscape="false" maxlength="50" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">被告人id：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="accusedId" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">accused_name：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="accusedName" htmlEscape="false" maxlength="50" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">complain_id：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="complainId" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">complain_pic1：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="complainPic1" htmlEscape="false" maxlength="500" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">complain_pic2：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="complainPic2" htmlEscape="false" maxlength="500" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">complain_pic3：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="complainPic3" htmlEscape="false" maxlength="500" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">create_time：</label>--%>
    <%--<div class="controls">--%>
    <%--<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
    <%--value="<fmt:formatDate value="${ruiComplain.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
    <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">处理时间：</label>--%>
    <%--<div class="controls">--%>
    <%--<input name="handleTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
    <%--value="<fmt:formatDate value="${ruiComplain.handleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
    <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">处理人id：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="handlerId" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>

    <%--<div class="control-group">--%>
    <%--<label class="control-label">final_handle_time：</label>--%>
    <%--<div class="controls">--%>
    <%--<input name="finalHandleTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
    <%--value="<fmt:formatDate value="${ruiComplain.finalHandleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
    <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">final_handler_id：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="finalHandlerId" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">投诉状态(10-新投诉/20-投诉通过转给被投诉人/30-被投诉人已申诉/40-提交仲裁/99-已关闭)：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="state" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">是否平台处理：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="isPlatformHandle" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <div class="control-group">
        <label class="control-label">原告电话：</label>
        <div class="controls">
            <form:input path="accuserPhone" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">被告电话：</label>
        <div class="controls">
            <form:input path="accusedPhone" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">处理结果:</label>
        <div class="controls">
            <form:select path="state" class="input-medium">
                <form:options items="${fns:getDictList('complain')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>

    <div class="form-actions">
        <shiro:hasPermission name="xb:ruiComplain:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                               value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>