<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WellNexa - Registrazione Completata</title>
    <%@include file="common.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registrazione_completata.css">
</head>
<body>
    <%@include file="Bar.jsp"%>
    <div class="container">
        <h2>Registrazione Completata</h2>
        <p>Grazie per esserti registrato! Il tuo account e' stato creato con successo.</p>
        <p>Accedi alla tua area riservata per iniziare a utilizzare i nostri servizi.</p>
        <a class="btn" href="${pageContext.request.contextPath}/AccountServlet/login_page">Accedi</a>
    </div>
    <%@include file="Footer.jsp" %>
</body>
</html>
