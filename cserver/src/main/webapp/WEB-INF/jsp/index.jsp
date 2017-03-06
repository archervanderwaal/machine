<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    if (!path.endsWith("/")) {
        path += "/";
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Insert title here</title>
    <link href="http://cdn.staticfile.org/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.1/css/bootstrap-theme.min.css"/>
    <style type="text/css"></style>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.0.1/js/button.js"></script>
    <c:set var="count" scope="session" value="1"/>
</head>
<body style="background-color: white">
<h3 align="center">机器列表</h3>
<br>
<div class="container">
    <div class="row clearfix">

        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <a href="<%=path%>server/add">
                        <button type="button" class="btn btn-default">新增服务器</button>
                    </a>
                    <button style="margin-left: 720px;background-color: white"><a
                            href="<%=path%>server?destType=0">测试
                    </a></button>
                    <button style=" background-color: white; margin-left: 40px"><a
                            href="<%=path%>server?destType=1">线上</a></button>
                    <button style=" background-color: white; margin-left: 40px"><a
                            href="<%=path%>servers">全部</a></button>
                    <button style="margin-left: 40px; margin-right: 0px;background-color: white">
                        <a href="<%=path%>servers/update">刷新</a>
                    </button>
                </div>
            </div>
        </div>
        <br>
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr class="success">
                    <th>
                        Host Address
                    </th>
                    <th>
                        Mysql
                    </th>
                    <th>
                        Nginx
                    </th>
                    <th>
                        Redis
                    </th>
                    <th>
                        Solr
                    </th>
                    <th>
                        Tomcat
                    </th>
                    <th>
                        类型
                    </th>
                    <th style="align-items: center">
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="server" items="${servers }">
                    <tr class="info">
                        <td>${server.destIp}</td>
                        <c:choose>
                            <c:when test="${server.serverInfos[0].serviceName != 'mysql'}">
                                <td></td>
                            </c:when>
                            <c:when test="${server.serverInfos[0].serviceName == 'mysql'}">
                                <td>${server.serverInfos[0].content}</td>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${server.serverInfos[1].serviceName != 'negix' &&
                            server.serverInfos[0].serviceName != 'negix'}">
                                <td></td>
                            </c:when>
                            <c:when test="${server.serverInfos[1].serviceName == 'negix'}">
                                <td>${server.serverInfos[1].content}</td>
                            </c:when>
                            <c:when test="${server.serverInfos[0].serviceName == 'negix'}">
                                <td>${server.serverInfos[0].content}</td>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${server.serverInfos[0].serviceName != 'redis' &&
                            server.serverInfos[1].serviceName != 'redis' && server.serverInfos[2].serviceName != 'redis'}">
                                <td></td>
                            </c:when>
                            <c:when test="${server.serverInfos[0].serviceName == 'redis'}">
                                <td>${server.serverInfos[0].content}</td>
                            </c:when>
                            <c:when test="${server.serverInfos[1].serviceName == 'redis'}">
                                <td>${server.serverInfos[1].content}</td>
                            </c:when>
                            <c:when test="${server.serverInfos[2].serviceName == 'redis'}">
                                <td>${server.serverInfos[2].content}</td>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${server.serverInfos[3].serviceName != 'solr' &&
                            server.serverInfos[0].serviceName != 'solr' &&
                            server.serverInfos[1].serviceName != 'solr' &&
                            server.serverInfos[2].serviceName != 'solr'}">
                                <td></td>
                            </c:when>
                            <c:when test="${server.serverInfos[0].serviceName == 'solr'}">
                                <td>${server.serverInfos[0].content}</td>
                            </c:when>
                            <c:when test="${server.serverInfos[1].serviceName == 'solr'}">
                                <td>${server.serverInfos[1].content}</td>
                            </c:when>
                            <c:when test="${server.serverInfos[2].serviceName == 'solr'}">
                                <td>${server.serverInfos[2].content}</td>
                            </c:when>
                            <c:when test="${server.serverInfos[3].serviceName == 'solr'}">
                                <td>${server.serverInfos[3].content}</td>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${server.serverInfos[4].serviceName != 'tomcat' &&
                            server.serverInfos[0].serviceName != 'tomcat' &&
                            server.serverInfos[1].serviceName != 'tomcat' &&
                            server.serverInfos[2].serviceName != 'tomcat' &&
                            server.serverInfos[3].serviceName != 'tomcat'}">
                                <td></td>
                            </c:when>
                            <c:when test="${server.serverInfos[0].serviceName == 'tomcat'}">
                                <td>${server.serverInfos[0].content}</td>
                            </c:when>
                            <c:when test="${server.serverInfos[1].serviceName == 'tomcat'}">
                                <td>${server.serverInfos[1].content}</td>
                            </c:when>
                            <c:when test="${server.serverInfos[2].serviceName == 'tomcat'}">
                                <td>${server.serverInfos[2].content}</td>
                            </c:when>
                            <c:when test="${server.serverInfos[3].serviceName == 'tomcat'}">
                                <td>${server.serverInfos[3].content}</td>
                            </c:when>
                            <c:when test="${server.serverInfos[4].serviceName == 'tomcat'}">
                                <td>${server.serverInfos[4].content}</td>
                            </c:when>
                        </c:choose>
                        <td>
                            <c:choose>
                                <c:when test="${server.destType eq '0'}">
                                    测试
                                </c:when>
                                <c:when test="${server.destType eq '1'}">
                                    线上
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <a
                                    href="javascript:if(confirm('确实要删除该内容吗?'))
                                    location='<%=path%>server/delete/${server.id}'">删除
                            </a>&nbsp;
                            <a href="<%=path%>server/editor/${server.id}">编辑</a>
                        </td>
                    </tr
                    ${count = count + 1}
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<c:if test="${FailServers != null}">
    <h5 align="center">已断开的服务器列表</h5>
    <br>
    <div class="container">
        <div class="row clearfix">
            <br>
            <div class="col-md-12 column">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr class="success">
                        <th>
                            Host Address
                        </th>
                        <th>
                            Mysql
                        </th>
                        <th>
                            Nginx
                        </th>
                        <th>
                            Redis
                        </th>
                        <th>
                            Solr
                        </th>
                        <th>
                            Tomcat
                        </th>
                        <th>
                            类型
                        </th>
                        <th style="align-items: center">
                            操作
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="server" items="${FailServers}">
                        <tr class="info">
                            <td>${server.destIp}</td>
                            <c:choose>
                                <c:when test="${server.serverInfos[0].serviceName != 'mysql'}">
                                    <td></td>
                                </c:when>
                                <c:when test="${server.serverInfos[0].serviceName == 'mysql'}">
                                    <td>${server.serverInfos[0].content}</td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${server.serverInfos[1].serviceName != 'negix' &&
                            server.serverInfos[0].serviceName != 'negix'}">
                                    <td></td>
                                </c:when>
                                <c:when test="${server.serverInfos[1].serviceName == 'negix'}">
                                    <td>${server.serverInfos[1].content}</td>
                                </c:when>
                                <c:when test="${server.serverInfos[0].serviceName == 'negix'}">
                                    <td>${server.serverInfos[0].content}</td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${server.serverInfos[0].serviceName != 'redis' &&
                            server.serverInfos[1].serviceName != 'redis' && server.serverInfos[2].serviceName != 'redis'}">
                                    <td></td>
                                </c:when>
                                <c:when test="${server.serverInfos[0].serviceName == 'redis'}">
                                    <td>${server.serverInfos[0].content}</td>
                                </c:when>
                                <c:when test="${server.serverInfos[1].serviceName == 'redis'}">
                                    <td>${server.serverInfos[1].content}</td>
                                </c:when>
                                <c:when test="${server.serverInfos[2].serviceName == 'redis'}">
                                    <td>${server.serverInfos[2].content}</td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${server.serverInfos[3].serviceName != 'solr' &&
                            server.serverInfos[0].serviceName != 'solr' &&
                            server.serverInfos[1].serviceName != 'solr' &&
                            server.serverInfos[2].serviceName != 'solr'}">
                                    <td></td>
                                </c:when>
                                <c:when test="${server.serverInfos[0].serviceName == 'solr'}">
                                    <td>${server.serverInfos[0].content}</td>
                                </c:when>
                                <c:when test="${server.serverInfos[1].serviceName == 'solr'}">
                                    <td>${server.serverInfos[1].content}</td>
                                </c:when>
                                <c:when test="${server.serverInfos[2].serviceName == 'solr'}">
                                    <td>${server.serverInfos[2].content}</td>
                                </c:when>
                                <c:when test="${server.serverInfos[3].serviceName == 'solr'}">
                                    <td>${server.serverInfos[3].content}</td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${server.serverInfos[4].serviceName != 'tomcat' &&
                            server.serverInfos[0].serviceName != 'tomcat' &&
                            server.serverInfos[1].serviceName != 'tomcat' &&
                            server.serverInfos[2].serviceName != 'tomcat' &&
                            server.serverInfos[3].serviceName != 'tomcat'}">
                                    <td></td>
                                </c:when>
                                <c:when test="${server.serverInfos[0].serviceName == 'tomcat'}">
                                    <td>${server.serverInfos[0].content}</td>
                                </c:when>
                                <c:when test="${server.serverInfos[1].serviceName == 'tomcat'}">
                                    <td>${server.serverInfos[1].content}</td>
                                </c:when>
                                <c:when test="${server.serverInfos[2].serviceName == 'tomcat'}">
                                    <td>${server.serverInfos[2].content}</td>
                                </c:when>
                                <c:when test="${server.serverInfos[3].serviceName == 'tomcat'}">
                                    <td>${server.serverInfos[3].content}</td>
                                </c:when>
                                <c:when test="${server.serverInfos[4].serviceName == 'tomcat'}">
                                    <td>${server.serverInfos[4].content}</td>
                                </c:when>
                            </c:choose>
                            <td>
                                <c:choose>
                                    <c:when test="${server.destType eq '0'}">
                                        测试
                                    </c:when>
                                    <c:when test="${server.destType eq '1'}">
                                        线上
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <a
                                        href="javascript:if(confirm('确实要删除该内容吗?'))
                                    location='<%=path%>server/delete/${server.id}'">删除
                                </a>&nbsp;
                                <a href="<%=path%>server/editor/${server.id}">编辑</a>
                            </td>
                        </tr
                        ${count = count + 1}
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>