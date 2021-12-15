package de.jlingott.jlpasswordmanagmentsystem.logic.database;

import de.jlingott.jlpasswordmanagmentsystem.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Diese Klasse ist eine DAO-Klasse.
 * DAO seht fuer Data Access Object.
 * Fuert die Operationen auf die DbTabellen aus
 */
public class DaoUser extends ASqlKeyWords {

    /**
     * Prüft ob der nutzer in der Datenbank ist
     *
     * @param conn Die Connection zur Datenbank
     * @return boolean ob der beitrag gefunden wurde
     */
    public ResultSet selectUser(Connection conn, String strUsername, String strPassword) {

        ResultSet rs = null;

        try {
            String querySelect = "SELECT _ID,USERNAME,PASSWORD FROM USERS WHERE USERNAME = ? and PASSWORD = ?";

            PreparedStatement stmt = conn.prepareStatement(querySelect);
            stmt.setString(1, strUsername);
            stmt.setString(2, strPassword);

            stmt.executeQuery();
            rs = stmt.getResultSet();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rs;
    }

    /**
     * Prüft ob der nutzer in der Datenbank ist
     *
     * @param conn Die Connection zur Datenbank
     * @return boolean ob der beitrag gefunden wurde
     */
    public ResultSet selectUsername(Connection conn, String strUsername) {

        ResultSet rs = null;

        try {
            String querySelect = "SELECT _ID,USERNAME,PASSWORD FROM USERS WHERE USERNAME = ?";

            PreparedStatement stmt = conn.prepareStatement(querySelect);
            stmt.setString(1, strUsername);

            stmt.executeQuery();
            rs = stmt.getResultSet();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rs;
    }

    /**
     * Fügt den Datensatz der Datenbank hinzu
     *
     * @param conn Die verbindung zur Datenbank
     * @param user User Objekt welches in die Datenbank geschrieben wird
     */
    public void insertUser(Connection conn, User user) {

        String insertQuery = "INSERT INTO USERS (USERNAME,PASSWORD) VALUES(?,?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(insertQuery);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
