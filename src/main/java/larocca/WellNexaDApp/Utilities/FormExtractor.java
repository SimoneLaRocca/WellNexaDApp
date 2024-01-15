package larocca.WellNexaDApp.Utilities;

import larocca.WellNexaDApp.Model.Operatore.Operatore;
import larocca.WellNexaDApp.Model.Paziente.Paziente;

import java.sql.Date;
import java.util.Map;

public class FormExtractor {

    public static Object extractLogin(Map<String, String[]> parameters) {
        Paziente paziente = new Paziente();
        paziente.setEmail(parameters.get("email")[0]);
        paziente.setPasswordhash(parameters.get("password")[0]);
        return paziente;
    }

    public static Paziente extractRegistrationPaziente(Map<String, String[]> parameters) {
        Paziente paziente = new Paziente();
        paziente.setEmail(parameters.get("email")[0]);
        paziente.setNome(parameters.get("nome")[0]);
        paziente.setCognome(parameters.get("cognome")[0]);
        paziente.setPasswordhash(parameters.get("password")[0]);
        paziente.setDataNascita(Date.valueOf(parameters.get("dataNascita")[0]));
        paziente.setIndirizzo(parameters.get("indirizzo")[0]);
        paziente.setCodiceFiscale(parameters.get("codiceFiscale")[0]);
        return paziente;
    }

    public static Operatore extractRegistrationOperatore(Map<String, String[]> parameters) {
        Operatore operatore = new Operatore();
        operatore.setEmail(parameters.get("email")[0]);
        operatore.setCodiceOperatore(parameters.get("codiceOperatore")[0]);
        operatore.setPasswordhash(parameters.get("password")[0]);
        return operatore;
    }
}
