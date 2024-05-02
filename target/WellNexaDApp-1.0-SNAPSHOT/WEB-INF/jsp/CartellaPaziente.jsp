<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WellNexa - Cartella clinica</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cartella_clinica.css">
    <script defer src="https://cdn.jsdelivr.net/npm/web3@1.5.3/dist/web3.min.js"></script>
    <script defer src="${pageContext.request.contextPath}/js/bindSoul.js"></script>
</head>
<body>
<%@include file="Bar.jsp" %>

<div class="sm">
    <button id="bindSoulButton">Ottieni il token</button>
</div>

<section>
    <h2>Dati Paziente</h2>
    <ul>
        <li><strong>Nome:</strong> ${sessionScope.user.nome}</li>
        <li><strong>Cognome:</strong> ${sessionScope.user.cognome}</li>
        <li><strong>Data di Nascita:</strong> ${sessionScope.user.dataNascita}</li>
        <li><strong>Codice Fiscale:</strong>
            <div id="_codiceFiscale">${sessionScope.user.codiceFiscale}</div>
        </li>
        <li><strong>Indirizzo wallet:</strong> ${sessionScope.user.indirizzo}</li>
        <li><strong>Email:</strong> ${sessionScope.user.email}</li>
    </ul>
</section>

<section>
    <h2>Storico Visite</h2>
    <c:if test="${not empty sessionScope.lista_visite}">
        <table id="tabella_visite">
            <tr>
                <th>Codice</th>
                <th>Esame</th>
                <th>Note</th>
            </tr>
            <c:forEach var="examination" items="${sessionScope.lista_visite}">
                <tr>
                    <td><c:out value="${examination.code}"/></td>
                    <td><c:out value="${examination.name}"/></td>
                    <td><c:out value="${examination.note}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty sessionScope.lista_visite}">
        <p>Nessun esame disponibile al momento.</p>
    </c:if>

</section>

<%@include file="Footer.jsp" %>
</body>

</html>
