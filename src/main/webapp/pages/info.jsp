<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的信息 — 软件项目风险管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
</head>
<body>
<%@include file="common/header.jsp"%>

<div class="container info-container">
    <div class="panel panel-default">
        <div class="panel-heading">我的信息</div>
        <div class="panel-body project-panel-body">
            <div class="row info-item">
                <label class="col-md-4">用户名</label>
                <label class="col-md-4 label-value" id="js-label-username">-</label>
            </div>
            <div class="row info-item">
                <label class="col-md-4">姓名</label>
                <label class="col-md-4 label-value" id="js-label-name">-</label>
            </div>
            <div class="row info-item">
                <label class="col-md-4">角色</label>
                <label class="col-md-4 label-value" id="js-label-role">-</label>
            </div>

            <div class="cut-line"></div>

            <h5>修改密码</h5>
            <div class="form-group">
                <label for="inputOldPassword" class="sr-only">旧密码</label>
                <input type="email" id="inputOldPassword" class="form-control" placeholder="旧密码">
            </div>
            <div class="form-group">
                <label for="inputPassword" class="sr-only">新密码</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="新密码">
            </div>
            <div class="form-group">
                <label for="inputPassword2" class="sr-only">再次输入新密码</label>
                <input type="password" id="inputPassword2" class="form-control" placeholder="再次输入新密码">
            </div>
            <button class="btn btn-primary pull-right" id="js-btn-submit">提交</button>
        </div>
    </div>
</div>

<%@include file="common/toaster.jsp"%>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>

<style>
    .info-container {
        margin-top: 60px;
        width: 400px;
    }
    .label-value {
        font-weight: normal;
    }
    .info-item {
        margin-bottom: 15px;
    }
    .cut-line {
        height: 1px;
        width: 100%;
        background: #ddd;
        margin-bottom: 15px;
    }
</style>

<script>
$(document).ready(function () {

    $.ajax({
        type: 'GET',
        url: $('#prefixUrl').val() + '/api/user/getMyInfo',
        success: function(ret) {
            if (ret.code == 0) {
                if (ret.data) {
                    $('#js-label-username').text(ret.data.username);
                    $('#js-label-name').text(ret.data.name);
                    if (ret.data.role == 0) {
                        $('#js-label-role').text('普通用户');
                    } else {
                        $('#js-label-role').text('系统管理员');
                    }
                }
            } else {
                toaster(ret.msg || '系统繁忙', 'error');
                if (ret.code == 401) {
                    setTimeout(function () {
                        window.location.href = $('#prefixUrl').val() + '/login';
                    }, 500);
                }
            }
        },
        error: function() {
            toaster('系统繁忙', "error");
        }
    });

    $('#js-btn-submit').on('click', function () {
        var data = {
            oldPassword: $('#inputOldPassword').val(),
            password: $('#inputPassword').val(),
            password2: $('#inputPassword2').val()
        };
        $.ajax({
            type: 'POST',
            url: $('#prefixUrl').val() + '/api/user/password',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(ret) {
                if (ret.code == 0) {
                    toaster('修改成功', 'success');
                    setTimeout(function () {
                        window.location.reload();
                    }, 500);
                } else {
                    toaster(ret.msg || '系统繁忙', 'error');
                }
            },
            error: function() {
                toaster('系统繁忙', "error");
            }
        });
    });
});
</script>

</body>
</html>