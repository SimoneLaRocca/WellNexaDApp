
function nascondiMessaggio() {
    var messaggioErrore = document.getElementById("messaggioErrore");
    if (messaggioErrore) {
        messaggioErrore.style.display = "none";
    }
}

setTimeout(nascondiMessaggio, 30000);