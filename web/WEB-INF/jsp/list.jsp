<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <base href="<%=basePath%>">
    <title>秒杀列表页</title>
</head>
<body style="text-align: center">
<div class="container">
    <div class="panel panel-default">
        <div class="panel panel-heading text-center">
            <h2>秒杀列表</h2>
        </div>
        <div class="panel panel-body">
            <table class="table table-hover">
                <tr>
                    <th>名称</th>
                    <th>库存</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>创建时间</th>
                    <th>详情页</th>
                </tr>
                <c:forEach var="sk" items="${seckills}">
                    <tr>
                        <td>${sk.name}</td>
                        <td>${sk.number}</td>
                        <td>
                            <fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <td>
                            <fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <a href="/seckill/${sk.seckillId}/detail" class="btn btn-info" target="_blank">详情</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
<%@include file="common/tail.jsp" %>
</html>
