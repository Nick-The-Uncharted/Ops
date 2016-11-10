<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="javascript:;">风险管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="<%=request.getContextPath()%>/">项目</a></li>
                <li><a href="<%=request.getContextPath()%>/tips">权限说明</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${sessionScope.get('id') != null}">
                        <li><a href="<%=request.getContextPath()%>/info">${sessionScope.get('name')}（${sessionScope.get('username')}）</a></li>
                        <li><a href="javascript:;" id="js-btn-logout">登出</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="<%=request.getContextPath()%>/login">登录</a></li>
                        <li><a href="<%=request.getContextPath()%>/register">注册</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
<input id="prefixUrl" value="<%=request.getContextPath()%>" hidden>