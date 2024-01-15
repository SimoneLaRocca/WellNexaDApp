<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifica Cartella Clinica</title>
    <%@include file="common.jsp" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/aggiorna_paziente.css">
</head>
<body>

<%@include file="Bar.jsp" %>
<div class="container">

    <section>
        <h2>Nuova Visita</h2>
        <form action="${pageContext.request.contextPath}/OperatoreServlet/aggiungi_visita" method="post">

            <label for="codiceVisita">Codice visita:</label>
            <input type="text" id="codiceVisita" name="codiceVisita">

            <label for="dataEsame">Data:</label>
            <input type="date" id="dataEsame" name="dataEsame">

            <label for="esame">Esame:</label>
            <input type="text" id="esame" name="esame">

            <label for="nota">Nota:</label>
            <textarea rows="10" id="nota" name="nota"></textarea>

            <input type="hidden" name="codice_fiscale" value="${requestScope.paziente.codiceFiscale}">

            <button type="submit">Salva</button>
        </form>
    </section>

    <section class="info">
        <h2>Dati del Paziente</h2>
        <form action="processa_modifiche_servlet" method="post">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome"
                   value="${not empty requestScope.paziente.nome ? requestScope.paziente.nome : ''}">

            <label for="cognome">Cognome:</label>
            <input type="text" id="cognome" name="cognome" value="${requestScope.paziente.cognome}">

            <label for="dataNascita">Data di Nascita:</label>
            <input type="date" id="dataNascita" name="dataNascita" value="${requestScope.paziente.dataNascita}">

            <label for="indirizzo">Indirizzo:</label>
            <input type="text" id="indirizzo" name="indirizzo" value="${requestScope.paziente.indirizzo}">

            <button type="submit">Salva Modifiche</button>
        </form>
    </section>

    <section class="table">
        <h2>Visite Mediche</h2>
        <table>
            <thead>
            <tr>
                <th>Data</th>
                <th>Codice della Visita</th>
                <th>Tipo di Esame</th>
            </tr>
            </thead>
            <tbody>
            <!-- Utilizza JSTL per iterare sulle visite mediche del paziente e popolare la tabella
            <c:forEach var="visita" items="${requestScope.cartellaClinica.visite}">
                <tr>
                    <td>${visita.data}</td>
                    <td>${visita.codiceVisita}</td>
                    <td>${visita.tipoEsame}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/OperatoreServlet?data=${visita.data}&tipoEsame=${visita.tipoEsame}">
                            Leggi Note
                        </a>
                    </td>
                </tr>
            </c:forEach>
            -->
            </tbody>
        </table>
    </section>

</div>
<%@include file="Footer.jsp" %>

</body>
</html>

