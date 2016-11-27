<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>风险管理系统</title>
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
        <div class="col-md-12">
            <div class="" id="js-panel-intro">
                <div class="item" style="font-size: 16px;font-weight: bold;display: flex;justify-content: space-between">
                    <div>
                        <span>RA：</span>
                        <span class="content" data-item="name">-</span>
                    </div>
                    <div class="content" data-item="createTime" style="font-size: 12px;font-weight: lighter">-</div>
                </div>
                <div class="item" style="color: #9b9b9b;display: flex;margin: 10px 0">
                    <div>描述：</div>
                    <div class="content" data-item="description">-</div>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="">
                <div class="" style="font-size: 16px">
                    风险列表
                    <span class="risk-add" id="js-btn-add" title="添加风险条目">+</span>
                </div>
                <div style="position: relative; top: 26px; display: flex; margin-left: auto; ">
                    <button type="button" class="btn btn-info">分析</button>
                </div>
                <div class="">
                    <table class="table table-hover table-bordered " id="js-table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>风险类型</th>
                            <th>风险描述</th>
                            <th>可能性</th>
                            <th>影响程度</th>
                            <th>跟踪者</th>
                            <th>创建时间</th>
                            <th>成为问题</th>
                            <th>操作</th>
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
        cursor: default;
        word-break: keep-all;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .risk-add {
        color: #926dea;
        font-size: 20px;
        font-weight: bold;
        cursor: pointer;
        transition: 0.3s;
    }
    .risk-add:hover {
        color: #6e44cc;
    }
</style>

</body>
</html>

