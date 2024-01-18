document.getElementById('registrazioneForm').addEventListener('submit', function (event) {
    // Array di ID dei campi obbligatori
    var requiredFields = ['email', 'password', 'tipoUtente'];

    for (var i = 0; i < requiredFields.length; i++) {
        var fieldId = requiredFields[i];
        var fieldValue = document.getElementById(fieldId).value.trim();

        if (fieldValue === '') {
            alert('Compila tutti i campi obbligatori.');
            event.preventDefault();
            return;
        }
    }

    var tipoUtente = document.getElementById('tipoUtente').value;
    if (tipoUtente === 'paziente') {
        var pazienteFields = ['nome', 'cognome', 'dataNascita', 'indirizzo', 'codiceFiscale'];

        for (var j = 0; j < pazienteFields.length; j++) {
            var pazienteFieldId = pazienteFields[j];
            var pazienteFieldValue = document.getElementById(pazienteFieldId).value.trim();

            if (pazienteFieldValue === '') {
                alert('Compila tutti i campi obbligatori.');
                event.preventDefault();
                return;
            }
        }
    }

    if (tipoUtente === 'operatore') {
        var codiceOperatoreValue = document.getElementById('codiceOperatore').value.trim();

        if (codiceOperatoreValue === '') {
            alert('Compila tutti i campi obbligatori.');
            event.preventDefault();
            return;
        }
    }
});

function showHideFields() {
    var tipoUtente = document.getElementById('tipoUtente').value;
    var pazienteField = document.getElementById('pazienteField');
    var codiceOperatoreField = document.getElementById('codiceOperatoreField');

    if (tipoUtente === 'paziente') {
        pazienteField.style.display = 'block';
        codiceOperatoreField.style.display = 'none';
    } else if (tipoUtente === 'operatore') {
        pazienteField.style.display = 'none';
        codiceOperatoreField.style.display = 'block';
    } else {
        pazienteField.style.display = 'none';
        codiceOperatoreField.style.display = 'none';
    }
}
