function showHideFields() {
    var tipoUtente = document.getElementById("tipoUtente").value;
    var codiceOperatoreField = document.getElementById("codiceOperatoreField");
    var pazienteField = document.getElementById("pazienteField")

    if (tipoUtente === "paziente") {
        pazienteField.style.display = "block";
        codiceOperatoreField.style.display = "none";
    } else if (tipoUtente === "operatore") {
        pazienteField.style.display = "none";
        codiceOperatoreField.style.display = "block";
    } else if (tipoUtente === "None") {
        pazienteField.style.display = "none";
        codiceOperatoreField.style.display = "none";
    } else {
        pazienteField.style.display = "none";
        codiceOperatoreField.style.display = "none";
    }
}
