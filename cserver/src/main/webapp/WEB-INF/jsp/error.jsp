<%--
  Created by IntelliJ IDEA.
  User: mayongbin01
  Date: 2017/1/19
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    if (!path.endsWith("/")) {
        path += "/";
    }
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error Page!</title>
    <style>
        ::-moz-selection {
            background: #b3d4fc;
            text-shadow: none;
        }

        ::selection {
            background: #b3d4fc;
            text-shadow: none;
        }

        html {
            padding: 30px 10px;
            font-size: 20px;
            line-height: 1.4;
            color: #737373;
            background: #f0f0f0;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            -webkit-text-size-adjust: 100%;
            -ms-text-size-adjust: 100%;
        }

        body {
            max-width: 550px;
            _width: 550px;
            padding: 30px 20px 50px;
            border: 1px solid #b3b3b3;
            border-radius: 4px;
            margin: 0 auto;
            box-shadow: 0 1px 10px #a7a7a7, inset 0 1px 0 #fff;
            background: #fcfcfc;
        }

        h1 {
            margin: 0 10px;
            font-size: 50px;
            text-align: center;
        }

        h1 span {
            color: #bbb;
        }

        h3 {
            margin: 1.5em 0 0.5em;
        }

        p {
            margin: 1em 0;
        }

        ul {
            padding: 0 0 0 40px;
            margin: 1em 0;
        }

        .container {
            max-width: 500px;
            _width: 500px;
            margin: 0 auto;
        }

    </style>
</head>
<body>
<h1>error message! <span>:(</span></h1>
<p>${errorMessage}</p>
<ul>
    <li>please check out. try again later</li>
</ul>
</body>
</html>
