<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WellNexa - Ricerca Pazienti</title>
    <%@include file="common.jsp"%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ricerca_paziente.css">
</head>
<body>
<%@include file="Bar.jsp"%>

<section>
    <form id="searchForm" action="${pageContext.request.contextPath}/OperatoreServlet/search" method="post">
        <label for="codiceFiscale">Codice Fiscale:</label>
        <input type="text" id="codiceFiscale" name="codiceFiscale">

        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome">

        <label for="cognome">Cognome:</label>
        <input type="text" id="cognome" name="cognome">

        <label for="indirizzo">Indirizzo:</label>
        <input type="text" id="indirizzo" name="indirizzo">

        <label for="dataNascita">Data di Nascita:</label>
        <input type="date" id="dataNascita" name="dataNascita">

        <button type="submit">Cerca</button>
    </form>

    <div id="patientList">
        <table>
            <thead>
            <tr>
                <th>Codice Fiscale</th>
                <th>Nome</th>
                <th>Cognome</th>
                <th>Indirizzo</th>
                <th>Data di Nascita</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="paziente" items="${requestScope.pazienti}">
                <tr>
                    <td>${paziente.codiceFiscale}</td>
                    <td>${paziente.nome}</td>
                    <td>${paziente.cognome}</td>
                    <td>${paziente.indirizzo}</td>
                    <td>${paziente.dataNascita}</td>
                    <td class="png">
                        <!-- Aggiungi un'icona/link per la visualizzazione dei dettagli -->
                        <a href="${pageContext.request.contextPath}/OperatoreServlet?codiceFiscale=${paziente.codiceFiscale}">
                            <img src="${pageContext.request.contextPath}/img/img2.png">
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<%@include file="Footer.jsp" %>

</body>
</html>

