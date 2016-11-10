<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>软件项目风险管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/select2/css/select2.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/normalize.css">
</head>
<body>
<%@include file="common/header.jsp"%>

<input id="js-pid" hidden value="${pid}">
<input id="js-rid" hidden value="${rid}">
<div class="container project-container" >
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default intro-panel" id="js-panel-intro">
                <div class="panel-heading">风险概述</div>
                <div class="panel-body project-panel-body">
                    <div class="item">
                        <p class="title">风险内容：</p>
                        <p class="content" data-item="content" style="max-height: 200px; overflow: auto">-</p>
                    </div>
                    <div class="item">
                        <p class="title">可能性：</p>
                        <p class="content" data-item="possibility">-</p>
                    </div>
                    <div class="item">
                        <p class="title">影响程度：</p>
                        <p class="content" data-item="impact">-</p>
                    </div>
                    <div class="item">
                        <p class="title">触发器/阈值：</p>
                        <p class="content" data-item="trigger">-</p>
                    </div>
                    <div class="item">
                        <p class="title">提交者：</p>
                        <p class="content" data-item="committer">-</p>
                    </div>
                    <div class="item">
                        <p class="title">跟踪者：</p>
                        <p class="content" data-item="followers">-</p>
                    </div>
                    <div class="item">
                        <p class="title">创建时间：</p>
                        <p class="content" data-item="createTime">-</p>
                    </div>
                    <div class="item">
                        <p class="title">更新时间：</p>
                        <p class="content" data-item="updateTime">-</p>
                    </div>
                    <div class="pull-right" id="js-btn-committer" hidden>
                        <button class="btn btn-primary btn-sm" id="js-btn-edit">编辑</button>
                        <button class="btn btn-danger btn-sm" id="js-btn-delete">删除</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">风险跟踪</div>
                <div class="panel-body project-panel-body">
                    <button class="btn btn-primary btn-sm" id="js-btn-add" hidden>创建跟踪</button>
                    <div class="panel-states" id="js-panel-states"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="common/toaster.jsp"%>
<%@include file="common/risk_modal.jsp"%>
<%@include file="common/risk_state_modal.jsp"%>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/select2/js/select2.full.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/select2/js/i18n/zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/risk.js"></script>

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
    .panel-states {
        margin-top: 20px;
    }
    .panel-state {
        margin-top: 5px;
        margin-bottom: 5px;
    }
    .panel-state .info {
        font-size: 13px;
        color: #9c9c9c;
        display: inline-block;
    }
    .panel-state .title {
        font-size: 16px;
        color: #337ab7;
        font-weight: bold;
        margin-top: 6px;
    }
    .panel-state .content {

    }
    .panel-arrow {
        font-size: 20px;
        text-align: center;
        color: #7d7d7d;
    }
</style>

</body>
</html>

