document.getElementById('registrazioneForm').addEventListener('submit', function (event) {
    // Array di ID dei campi obbligatori
    var requiredFields = ['email', 'password', 'tipoUtente'];

    // Effettua il controllo per ciascun campo
    for (var i = 0; i < requiredFields.length; i++) {
        var fieldId = requiredFields[i];
        var fieldValue = document.getElementById(fieldId).value.trim();

        // Se un campo è vuoto, mostra un avviso e impedisce l'invio del form
        if (fieldValue === '') {
            alert('Compila tutti i campi obbligatori.');
            event.preventDefault();
            return;
        }
    }

    // Se il tipoUtente è "paziente", controlla i campi aggiuntivi per i pazienti
    var tipoUtente = document.getElementById('tipoUtente').value;
    if (tipoUtente === 'paziente') {
        var pazienteFields = ['nome', 'cognome', 'dataNascita', 'indirizzo', 'codiceFiscale'];

        for (var j = 0; j < pazienteFields.length; j++) {
            var pazienteFieldId = pazienteFields[j];
            var pazienteFieldValue = document.getElementById(pazienteFieldId).value.trim();

            // Se un campo paziente è vuoto, mostra un avviso e impedisce l'invio del form
            if (pazienteFieldValue === '') {
                alert('Compila tutti i campi obbligatori.');
                event.preventDefault();
                return;
            }
        }
    }

    // Se il tipoUtente è "operatore", controlla il campo aggiuntivo per gli operatori
    if (tipoUtente === 'operatore') {
        var codiceOperatoreValue = document.getElementById('codiceOperatore').value.trim();

        // Se il campo codiceOperatore è vuoto, mostra un avviso e impedisce l'invio del form
        if (codiceOperatoreValue === '') {
            alert('Compila tutti i campi obbligatori.');
            event.preventDefault();
            return;
        }
    }
});

// Funzione per mostrare/nascondere i campi del paziente e dell'operatore in base al tipoUtente
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
