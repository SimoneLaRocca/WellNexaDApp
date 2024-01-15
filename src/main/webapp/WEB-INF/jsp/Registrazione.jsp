<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WellNexa - Registrazione</title>
    <%@include file="common.jsp"%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registrazione.css">
    <%--<script defer src="${pageContext.request.contextPath}/js/hidden.js"></script>--%>
    <script defer src="${pageContext.request.contextPath}/js/error_msg.js"></script>
    <script defer src="${pageContext.request.contextPath}/js/input_validation.js"></script>
</head>
<body>

<%@include file="Bar.jsp"%>

<c:if test="${not empty messaggioErrore}">
    <div id="messaggioErrore" style="color: red;">${messaggioErrore}</div>
</c:if>

<section>
    <h1>Registrazione</h1>
    <form id="registrazioneForm" action="${pageContext.request.contextPath}/AccountServlet/registrazione" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="tipoUtente">Tipo Utente:</label>
        <select class="UserType" id="tipoUtente" name="tipoUtente" onchange="showHideFields()">
            <option value="None">---</option>
            <option value="paziente">Paziente</option>
            <option value="operatore">Operatore Sanitario</option>
        </select>

        <div class="paziente" id="pazienteField" style="display:none;">
            <div id="nomeField">
                <label for="nomeField">Nome:</label>
                <input class="hidden" type="text" id="nome" name="nome">
            </div>

            <div id="cognomeField">
                <label for="cognomeField">Cognome:</label>
                <input class="hidden" type="text" id="cognome" name="cognome">
            </div>

            <div id="dataNascitaField">
                <label for="dataNascitaField">Data di Nascita:</label>
                <input type="date" id="dataNascita" name="dataNascita">
            </div>

            <div id="indirizzoField">
                <label for="indirizzoField">Indirizzo:</label>
                <input class="hidden" type="text" id="indirizzo" name="indirizzo">
            </div>

            <div id="codiceFiscaleField">
                <label for="codiceFiscale">Codice Fiscale:</label>
                <input class="hidden" type="text" id="codiceFiscale" name="codiceFiscale">
            </div>
        </div>

        <div  id="codiceOperatoreField" style="display:none;">
            <label for="codiceOperatore">Codice Operatore:</label>
            <input class="hidden" type="text" id="codiceOperatore" name="codiceOperatore">
        </div>

        <button type="submit">Registrati</button>
    </form>
</section>

<%@include file="Footer.jsp" %>


</body>
</html>
