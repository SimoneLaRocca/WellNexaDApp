package larocca.WellNexaDApp.Model.Operatore;

import larocca.WellNexaDApp.Utilities.ConPool;

import java.sql.*;
import java.util.List;

public class OperatoreDAO {

    public void doSave(Operatore operatore) {
        if (operatore == null) return;
        String sql = "INSERT INTO operatore(email, passwordhash, codice) VALUES (?,?,?)";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, operatore.getEmail());
            ps.setString(2, operatore.getPasswordhash());
            ps.setString(3, operatore.getCodiceOperatore());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Operatore> doRetrieveAll() {

        return null;
    }

    public Operatore doRetrieveByEmail(String email) {
        String sql = "SELECT * FROM operatore WHERE email = ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet set = ps.executeQuery();

            if (set.next())
                return OperatoreConstructor.constructOperatore(set);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean checkRegistration(Operatore o) {
        String sql = "SELECT * FROM operatore WHERE email = ? OR codice = ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, o.getEmail());
            ps.setString(2, o.getCodiceOperatore());
            ResultSet set = ps.executeQuery();

            if (set.next())
                return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkLogin(String email, String passwordHash) {
        String sql = "SELECT * FROM operatore WHERE email = ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet set = ps.executeQuery();

            if (set.next()) {
                if (passwordHash.equals(set.getString("passwordHash")))
                    return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
