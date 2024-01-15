<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WellNexa - Home</title>
    <%@include file="/WEB-INF/jsp/common.jsp"%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
<%@include file="WEB-INF/jsp/Bar.jsp"%>

<section>
    <h1>Benvenuto su WellNexa</h1>
    <p>Una soluzione avanzata per la gestione sicura ed efficiente delle informazioni sanitarie.</p>
    <img src="${pageContext.request.contextPath}/img/img1.jpg">
</section>

<%@include file="WEB-INF/jsp/Footer.jsp" %>
</body>
</html>
