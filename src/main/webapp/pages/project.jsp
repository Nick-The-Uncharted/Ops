<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>软件项目风险管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/select2/css/select2.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/datatables/css/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/normalize.css">
</head>
<body>
<%@include file="common/header.jsp"%>

<input id="js-pid" hidden value="${id}">
<div class="container project-container" >
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default intro-panel" id="js-panel-intro">
                <div class="panel-heading">项目概述</div>
                <div class="panel-body project-panel-body">
                    <div class="item">
                        <p class="title">项目名称：</p>
                        <p class="content" data-item="name">-</p>
                    </div>
                    <div class="item">
                        <p class="title">项目简介：</p>
                        <p class="content" data-item="description">-</p>
                    </div>
                    <div class="item">
                        <p class="title">创建者：</p>
                        <p class="content" data-item="creator">-</p>
                    </div>
                    <div class="item">
                        <p class="title">参与者：</p>
                        <p class="content" data-item="users">-</p>
                    </div>
                    <div class="item">
                        <p class="title">创建时间：</p>
                        <p class="content" data-item="createTime">-</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">风险一览</div>
                <div class="panel-body project-panel-body">
                    <button class="btn btn-primary btn-sm" id="js-btn-add">创建条目</button>
                    <div class="row">
                        <div class="col-md-2">
                            <select class="form-control" id="js-select-risk-flag" style="margin-top: 15px; margin-bottom: 15px;">
                                <option value="0">所有</option>
                                <option value="1">我提交的</option>
                                <option value="2">我跟踪的</option>
                            </select>
                        </div>
                    </div>
                    <table class="table table-hover table-bordered " id="js-table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>风险类型</th>
                            <th>风险描述</th>
                            <th>可能性</th>
                            <th>影响程度</th>
                            <th>提交者</th>
                            <th>创建时间</th>
                        </tr>
                        </thead>
                        <tbody id="js-table-body"></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="common/toaster.jsp"%>
<%@include file="common/project_modal.jsp"%>
<%@include file="common/risk_modal.jsp"%>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/select2/js/select2.full.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/select2/js/i18n/zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/datatables/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/project.js"></script>

<style>
    .project-container {
        margin-top: 60px;
    }
    .intro-panel .item {
        margin-bottom: 25px;
    }
    .intro-panel .item .title {
        font-size: 15px;
    }
    .intro-panel .item .content {
        font-size: 14px;
    }
    table {
        font-size: 12px;
    }
    
    table tbody td {
        max-width: 200px;
        cursor: pointer;
        word-break: keep-all;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
</style>

</body>
</html>

