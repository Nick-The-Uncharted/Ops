<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>风险管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/select2/css/select2.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/normalize.css">
</head>
<body>
<%@include file="common/header.jsp"%>

<div class="container project-container">
    <div class="heading">
        我的项目
        <span class="project-add" id="js-btn-add" title="创建项目">+</span>
    </div>
    <div class="project-panel-body">
        <div class="content" id="js-create">无</div>
        <div class="content" id="js-in" style="display: none">无</div>

    </div>

</div>

<%@include file="common/toaster.jsp"%>
<%@include file="common/project_modal.jsp"%>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/select2/js/select2.full.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/select2/js/i18n/zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/index.js"></script>

<style>
    .project-add {
        color: #926dea;
        transition: 0.3s;
        cursor: pointer;
        margin-left: 10px;
        font-size: 20px;
    }
    .project-panel-body {
        padding: 20px 0;
    }
    .project-add:hover {
        color: #6e44cc;
    }
    .heading {
        font-size: 18px;
        font-weight: bold;
        margin-top: 10px;
    }
    .project-container {
        margin-top: 60px;
    }
    .project-panel-body .title {
        margin-top: 30px;
    }
    .project-panel-body .content {
        min-height: 100px;
    }
    .project-panel-body .content .thumbnail {
        height: 120px;
        cursor: pointer;
        text-align: center;
        line-height: 120px;
        font-size: 18px;
        background: url('${pageContext.request.contextPath}/assets/img/project.jpg');
        opacity: 1;
        color: #926dea;
        -webkit-transition: 0.5s;
        transition: 0.5s;
    }
    .project-panel-body .content .thumbnail:hover {
        background-color: #fff;
        opacity: 0.8;
    }
</style>

</body>
</html>
