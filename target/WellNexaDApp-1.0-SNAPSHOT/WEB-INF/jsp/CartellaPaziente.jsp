<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 30/11/2023
  Time: 02:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WellNexa - Cartella clinica</title>
    <%@include file="common.jsp"%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cartella_clinica.css">
</head>
<body>
    <%@include file="Bar.jsp"%>
    <section>
    <h2>Dati Paziente</h2>
    <ul>
        <li><strong>Nome:</strong> Mario</li>
        <li><strong>Cognome:</strong> Rossi</li>
        <li><strong>Data di Nascita:</strong> 01/01/1980</li>
        <li><strong>Codice Fiscale:</strong> RSSMRA80M01H501Z</li>
        <li><strong>Indirizzo:</strong> Via Roma, 123</li>
        <li><strong>Email:</strong> mario.rossi@email.com</li>
    </ul>
</section>

    <section>
    <h2>Storico Visite</h2>
    <table>
        <thead>
        <tr>
            <th>Data</th>
            <th>Motivo</th>
            <th>Medico</th>
            <th>Esami Prescritti</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>02/03/2023</td>
            <td>Controllo generale</td>
            <td>Dr. Bianchi</td>
            <td>Emocromo, Visita oculistica</td>
        </tr>
        <tr>
            <td>15/05/2023</td>
            <td>Dolore addominale</td>
            <td>Dr. Verdi</td>
            <td>Ecografia addominale</td>
        </tr>
        <!-- Aggiungi altre righe secondo necessità -->
        </tbody>
    </table>
</section>
    <%@include file="Footer.jsp" %>
</body>

</html>
