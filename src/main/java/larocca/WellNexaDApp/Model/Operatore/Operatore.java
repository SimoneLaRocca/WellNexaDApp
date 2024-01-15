package larocca.WellNexaDApp.Model.Operatore;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public class Operatore {

    private String email;
    private String passwordhash;
    private String codiceOperatore;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.passwordhash = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCodiceOperatore() {
        return codiceOperatore;
    }

    public void setCodiceOperatore(String codiceeOperatore) {
        this.codiceOperatore = codiceeOperatore;
    }


}
