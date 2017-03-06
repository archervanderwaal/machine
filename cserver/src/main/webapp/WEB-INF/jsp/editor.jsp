<%--
  Created by IntelliJ IDEA.
  User: mayongbin01
  Date: 2017/3/6
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    if (!path.endsWith("/")) {
        path += "/";
    }
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="http://cdn.staticfile.org/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.1/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <style type="text/css"></style>
    <script type="text/javascript"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.0.1/js/button.js"></script>
    <style type="text/css">
        #form1{
            width: 800px;
        }
    </style>
</head>
<body style="background-color: white">
<br><br><br>
<h3 align="center">修改信息</h3>
<br><br><br>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column" align="center">
            <form class="form-horizontal" id="form1" role="form" action="<%=path%>server/edit" method="post">
                <div class="form-group">
                    <label for="hostAddress" class="col-sm-2 control-label">远程服务器地址</label>
                    <div class="col-sm-10">
                        <input type="text" name="destIp" class="form-control" id="hostAddress"
                               value="${server.destIp}" readonly/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="loginName" class="col-sm-2 control-label">登录名</label>
                    <div class="col-sm-10">
                        <input type="text" name="destLoginname" class="form-control" id="loginName"
                               value="${server.destLoginname}" readonly />
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-10">
                        <input type="password" name="destPassword" class="form-control" id="password"
                               value="${server.destPassword}" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="port" class="col-sm-2 control-label">端口</label>
                    <div class="col-sm-10">
                        <input type="text" name="destPort" class="form-control" id="port" value="22"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="type" class="col-sm-2 control-label">类型</label>
                    <div class="col-sm-10">
                        <select id="type" name="destType" class="selectpicker show-tick form-control">
                            <option data-subtext="option subtext" value="1">线上</option>
                            <option data-subtext="option subtext" value="0">测试</option>
                        </select>
                    </div>
                </div>
                <br>
                <div class="form-group" align="center">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" style="width: 100%"
                                onclick="return isTrue()">确认提交
                        </button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function isTrue() {
        if (document.getElementById("hostAddress").value == "" ||
            document.getElementById("loginName").value == "" ||
            document.getElementById("password").value == "" ||
            document.getElementById("port").value == "") {
            alert("请填写完整!");
            return false;
        } else {
            return true;
        }
    }
</script>
</body>
</html>

