<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 13.06.2017
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@include file="library.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="${contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title><tiles:insertAttribute name="title"/></title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <security:csrfMetaTags/>
        <script src="${contextPath}/resources/js/myscript.js"></script>
</head>

<body>

        <tiles:insertAttribute name="header" />

        <tiles:insertAttribute name="menu" />

        <tiles:insertAttribute name="body" />

        <tiles:insertAttribute name="footer" />


</body>
</html>
