<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <base href="<%=basePath%>">
    <title>秒杀详情页</title>

</head>
<body>
<%--秒杀产品begin--%>
<div class="container">
    <div class="panel panel-default text-center">
        <div class="panel-heading">
            <h1>${seckillDetail.name}</h1>
        </div>
        <div class="panel-body">
            <h2 class="text-danger">
                <span class="glyphicon glyphicon-time"></span>
                <span class="glyphicon" id="seckillBox"></span>
            </h2>
            <button class="btn btn-primary disabled" id="killBeginBtn">开启秒杀</button>
        </div>
    </div>
</div>
<%--秒杀产品end--%>

<%--登陆弹出层begin--%>
<div class="modal fade" id="killPhoneModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title" style="text-align: center">
                    <h3 class="glphyicon glphyicon-phone">需先填写秒杀电话:</h3>
                </div>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-8 col-md-push-2">
                        <input type="text" name="killPhone" id="userPhone" placeholder="填写手机号^o^" class="form-control">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <span class="label label-warning" id="phoneTipMsg">手机格式不正确</span>
                <button type="button" id="loginBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-phone"></span>
                    submit
                </button>
            </div>
        </div>
    </div>
</div>
<%--登陆弹出层end--%>
</body>
<%@include file="common/tail.jsp"%>
<script type="application/javascript" src="js/jquery-cookie1.4.1.js"></script>
<script type="application/javascript" src="js/mine/seckill.js"></script>

<script>
   $(function () {
       seckill.checkUserCookie({
           seckillId:${seckillDetail.seckillId},
           startTime:"${seckillDetail.startTime}",
           endTime:"${seckillDetail.endTime}"
       });
   })
    
</script>
</html>
