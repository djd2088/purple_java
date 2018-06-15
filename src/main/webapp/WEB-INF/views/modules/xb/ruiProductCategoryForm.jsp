<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});

        function queryCategory(th){
            $("#parentId").empty();
            $.ajax({
                url:'${ctx}/xb/ruiProductCategory/getParentByLevel',
                data:{
                    level:$(th).val()
                },
                type:'get',
                cache:false,
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
		<li><a href="${ctx}/xb/ruiProductCategory/">类目列表</a></li>
		<li class="active"><a href="${ctx}/xb/ruiProductCategory/form?id=${ruiProductCategory.id}">类目<shiro:hasPermission name="xb:ruiProductCategory:edit">${not empty ruiProductCategory.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="xb:ruiProductCategory:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ruiProductCategory" action="${ctx}/xb/ruiProductCategory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">类目名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">create_time：</label>--%>
			<%--<div class="controls">--%>
				<%--<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
					<%--value="<fmt:formatDate value="${ruiProductCategory.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">update_time：</label>--%>
			<%--<div class="controls">--%>
				<%--<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
					<%--value="<fmt:formatDate value="${ruiProductCategory.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">类目等级：</label>
			<div class="controls">
				<form:select path="categoryLevel" class="input-medium" onchange="queryCategory(this)">
					<form:options items="${fns:getDictList('sys_office_grade')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">父类目：</label>
			<div class="controls">
				<form:select path="parentId" class="input-medium">
					<form:option value="0" label=""></form:option>
				</form:select>
			</div>
		</div>
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
			<shiro:hasPermission name="xb:ruiProductCategory:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>