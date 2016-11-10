<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>权限说明 - 软件项目风险管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/select2/css/select2.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/normalize.css">
</head>
<body>
<%@include file="common/header.jsp"%>

<div class="container tips-container">
    <div class="panel panel-default">
        <div class="panel-heading">系统角色和权限的相关说明</div>
        <div class="panel-body">
            <p>项目：用户可以创建项目，每个用户都可以是一个项目的创建者和参与者。参与者的权限是创建者的子集，参与者可以进入项目，对项目的风险进行增删改操作。创建者还可以编辑项目信息和修改项目。</p>
            <p>风险：项目中的成员可以创建风险，风险相关的角色有提交者和跟踪者。所有项目成员都可以查看该项目中的风险及其跟踪，但只有提交者和跟踪者编辑风险，提交者可以删除风险。</p>
            <p>跟踪：所有项目成员都可以查看该项目中的风险及其跟踪，但只有提交者和跟踪者可以对风险进行增改操作。</p>
        </div>
    </div>

</div>

<%@include file="common/toaster.jsp"%>
<%@include file="common/project_modal.jsp"%>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

<style>
    .tips-container {
        margin-top: 60px;
    }
</style>

</body>
</html>
