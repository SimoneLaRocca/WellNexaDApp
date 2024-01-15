package larocca.WellNexaDApp.Model.Paziente;

import larocca.WellNexaDApp.Utilities.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class PazienteDAO {

    public void doSave(Paziente paziente) {
        if (paziente == null) return;
        String sql = "INSERT INTO paziente(email, passwordhash, nome, " +
                "cognome, dataNascita, indirizzo, codiceFiscale) VALUES (?,?,?,?,?,?,?)";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, paziente.getEmail());
            ps.setString(2, paziente.getPasswordhash());
            ps.setString(3, paziente.getNome());
            ps.setString(4, paziente.getCognome());
            ps.setDate(5, paziente.getDataNascita());
            ps.setString(6, paziente.getIndirizzo());
            ps.setString(7, paziente.getCodiceFiscale());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Paziente> doRetrieveAll() {
        String sql = "SELECT * FROM paziente";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            List<Paziente> list = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Paziente paziente = new Paziente();
                paziente.setEmail(rs.getString("email"));
                paziente.setNome(rs.getString("nome"));
                paziente.setCognome(rs.getString("cognome"));
                paziente.setDataNascita(rs.getDate("dataNascita"));
                paziente.setIndirizzo(rs.getString("indirizzo"));
                paziente.setCodiceFiscale(rs.getString("codiceFiscale"));
                list.add(paziente);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Paziente doRetrieveByCodiceFiscale(String codiceFiscale) {
        String sql = "SELECT * FROM paziente WHERE codiceFiscale = ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, codiceFiscale);
            ResultSet set = ps.executeQuery();

            if (set.next())
                return PazienteConstructor.constructPaziente(set);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Paziente doRetrieveByEmail(String email) {
        String sql = "SELECT * FROM paziente WHERE email = ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet set = ps.executeQuery();

            if (set.next())
                return PazienteConstructor.constructPaziente(set);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Paziente> search(Map<String, String> parameters, Map<String, String[]> parameterMap) {

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String paramName = entry.getKey();
            String[] paramValues = entry.getValue();

            // Considera solo il primo valore (ignorando gli eventuali valori multipli per lo stesso parametro)
            if (paramValues != null && paramValues.length > 0) {
                parameters.put(paramName, paramValues[0]);
            }
        }

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM paziente WHERE 1=1");

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String paramName = entry.getKey();
            String paramValue = entry.getValue();

            // Aggiungi condizione solo se il valore del parametro non è vuoto
            if (paramValue != null && !paramValue.isEmpty()) {
                queryBuilder.append(" AND ").append(paramName).append(" = '").append(paramValue).append("'");
            }
        }

        String finalQuery = queryBuilder.toString();

        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(String.valueOf(finalQuery), Statement.RETURN_GENERATED_KEYS)) {

            List<Paziente> list = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Paziente paziente1 = new Paziente();
                paziente1.setEmail(rs.getString("email"));
                paziente1.setNome(rs.getString("nome"));
                paziente1.setCognome(rs.getString("cognome"));
                paziente1.setDataNascita(rs.getDate("dataNascita"));
                paziente1.setIndirizzo(rs.getString("indirizzo"));
                paziente1.setCodiceFiscale(rs.getString("codiceFiscale"));
                list.add(paziente1);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkRegistration(Paziente p) {
        String sql = "SELECT * FROM paziente WHERE email = ? OR codiceFiscale = ?";
        try (Connection connection = ConPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, p.getEmail());
            ps.setString(2, p.getCodiceFiscale());
            ResultSet set = ps.executeQuery();

            if (set.next())
                return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkLogin(String email, String passwordHash) {
        String sql = "SELECT * FROM paziente WHERE email = ?";
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
