<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="javascript:;">风险管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${sessionScope.get('id') != null}">
                        <li style="padding: 6px 0 6px 0"><img src="${pageContext.request.contextPath}/assets/img/user.jpeg" alt=""
                        style="border-radius: 50%;width: 40px;height: 40px"></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="<%=request.getContextPath()%>/info">${sessionScope.get('name')} <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="javascript:;" id="js-btn-logout">登出</a></li>
                            </ul>
                        </li>
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