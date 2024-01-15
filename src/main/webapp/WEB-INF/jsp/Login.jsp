
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WellNexa - Login</title>
    <%@include file="common.jsp"%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<%@include file="Bar.jsp"%>

<c:if test="${not empty messaggioErrore}">
    <div id="messaggioErrore" style="color: red;">${messaggioErrore}</div>
</c:if>

<section class="login-section">
    <h1>Login</h1>
    <form action="${pageContext.request.contextPath}/AccountServlet/login" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <div class="radio-buttons">
            <label>
                <input class="paziente_radio" type="radio" name="tipoUtente" value="paziente">
                Paziente
            </label>
            <label>
                <input class="operatore_radio" type="radio" name="tipoUtente" value="operatore">
                Operatore
            </label>
        </div>

        <button type="submit">Accedi</button>
    </form>
</section>

<%@include file="Footer.jsp" %>
</body>
</html>
