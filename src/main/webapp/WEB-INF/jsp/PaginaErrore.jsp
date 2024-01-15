<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WellNexa - Errore</title>
    <%@include file="common.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/errore.css">
</head>
<body>
    <%@include file="Bar.jsp"%>
    <div class="container">
        <h2>Errore</h2>
        <p>Si e' verificato un errore durante l'elaborazione della richiesta.</p>
        <p>Torna alla <a href="${pageContext.request.contextPath}/index.jsp">pagina principale</a>.</p>
    </div>
    <%@include file="Footer.jsp" %>
</body>
</html>

