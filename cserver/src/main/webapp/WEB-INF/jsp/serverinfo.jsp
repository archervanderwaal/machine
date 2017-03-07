<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mayongbin01
  Date: 2017/1/20
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    if (!path.endsWith("/")) {
        path += "/";
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>服务器详情</title>
    <link href="http://cdn.staticfile.org/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.1/css/bootstrap-theme.min.css"/>
    <style type="text/css"></style>
    <script type="text/javascript"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.0.1/js/button.js"></script>
    <style type="text/css">
        #home {
            margin-left: 15px;
            margin-top: 30px;
        }

        #img1 {
            width: 30px;
            height: 30px;
        }
    </style>
</head>
<body style="background-color: white">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-center">
                机器可视化
            </h3>
            <div id="home">
                <a href="<%=path%>servers">
                    <img id="img1"
                         src="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=269607662,3070173031&fm=21&gp=0.jpg">
                </a>
            </div>
            <br><br>
            <a href="<%=path%>servers" style="margin-left: 0px"></a>
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <p>
                            服务器详情
                        </p>
                    </div>
                </div>
            </div>
            <br>
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    展示面板
                                </h3>
                            </div>
                            <div class="panel-body">
                                服务器地址：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${server.destIp}
                            </div>
                            <c:forEach items="${server.serverInfos}" var="serverInfo">
                                <div class="panel-footer">
                                        ${serverInfo.serviceName}：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${serverInfo.content}
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
