package larocca.WellNexaDApp.Model.Paziente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PazienteConstructor {
    public static Paziente constructPaziente(ResultSet set) throws SQLException {
        Paziente paziente = new Paziente();
        paziente.setEmail(set.getString("email"));
        paziente.setPasswordhash(set.getString("passwordHash"));
        paziente.setNome(set.getString("nome"));
        paziente.setCognome(set.getString("cognome"));
        paziente.setDataNascita(set.getDate("dataNascita"));
        paziente.setIndirizzo(set.getString("indirizzo"));
        paziente.setCodiceFiscale(set.getString("codiceFiscale"));
        return paziente;
    }
}
