package larocca.WellNexaDApp.Model.Operatore;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OperatoreConstructor {
    public static Operatore constructOperatore(ResultSet set) throws SQLException {
        Operatore operatore = new Operatore();
        operatore.setEmail(set.getString("email"));
        operatore.setPasswordhash(set.getString("passwordHash"));
        operatore.setCodiceOperatore(set.getString("codice"));
        return operatore;
    }
}
