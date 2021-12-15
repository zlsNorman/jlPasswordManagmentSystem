package de.jlingott.jlpasswordmanagmentsystem.logic.database;

import de.jlingott.jlpasswordmanagmentsystem.helper.PasswordHelper;
import de.jlingott.jlpasswordmanagmentsystem.model.Account;
import de.jlingott.jlpasswordmanagmentsystem.service.UserDataService;
import de.jlingott.jlpasswordmanagmentsystem.settings.AppTexts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse ist eine DAO-Klasse.
 * DAO seht fuer Data Access Object.
 * Fuert die Operationen auf die DbTabellen aus
 */
public class DaoAccounts extends ASqlKeyWords {

    /**
     * Holt alle Daten aus der Accounts Tabelle
     *
     * @param conn Die Connection zur Datenbank
     * @return List eine Liste mit Account Objekten
     */
    public List<Account> selectData(Connection conn) {

        List<Account> accountList = new ArrayList<>();

        //eingelogterUser
        int loggedInUserId = UserDataService.getInstance().getiUserId();

        try {
            String querySelect = "SELECT * FROM ACCOUNTS WHERE USERID = " + loggedInUserId;

            Statement stmt = conn.createStatement();
            stmt.executeQuery(querySelect);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Account account = new Account();
                int iId = Integer.parseInt(rs.getString(1));
                String strTitle = rs.getString(2);
                String strUsername = rs.getString(3);
                String strPassword = rs.getString(4);
                String strUrl = rs.getString(5);
                String strNote = rs.getString(6);

                account.setiAccountId(iId);
                account.setStrAccountTitle(strTitle);
                account.setStrAccountUsername(strUsername);
                account.setStrAccountPassword(strPassword);
                account.setStrAccountUrl(strUrl);
                account.setStrAccountNote(strNote);

                //Zensiertes Passwort setzen
                PasswordHelper passwordHelper = new PasswordHelper(strPassword);
                account.setStrCensoredPassword(passwordHelper.getCensoredPassword());

                accountList.add(account);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
    }

    /**
     * Fügt den Datensatz der Datenbank hinzu
     *
     * @param conn    Die verbindung zur Datenbank
     * @param account Account Object behinhaltet den zu speichernden Account
     */
    public void insertData(Connection conn, Account account) {

        try {
            System.out.println(AppTexts.CONNECTION_FOR_INPUT_WAS_SUCCESSFULL);

            //eingelogterUser
            int loggedInUserId = UserDataService.getInstance().getiUserId();

            //Objektdaten in variablen Speichern
            String strTitle = account.getStrAccountTitle();
            String strUsername = account.getStrAccountUsername();
            String strPassword = account.getStrAccountPassword();
            String strUrl = account.getStrAccountUrl();
            String strNote = account.getStrAccountNote();

            String queryInsert = "INSERT INTO ACCOUNTS (title,username,password,url,note,userid) VALUES(?,?,?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(queryInsert);
            stmt.setString(1, strTitle);
            stmt.setString(2, strUsername);
            stmt.setString(3, strPassword);
            stmt.setString(4, strUrl);
            stmt.setString(5, strNote);
            stmt.setInt(6, loggedInUserId);

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ändert den Datensatz der Datenbank
     *
     * @param conn    Die verbindung zur Datenbank
     * @param account Account Object behinhaltet den zu speichernden Account
     */
    public void updateData(Connection conn, Account account) {

        try {
            System.out.println(AppTexts.CONNECTION_FOR_UPDATE_WAS_SUCCESSFULL);

            //Objektdaten in variablen Speichern
            int iId = account.getiAccountId();
            String strTitle = account.getStrAccountTitle();
            String strUsername = account.getStrAccountUsername();
            String strPassword = account.getStrAccountPassword();
            String strUrl = account.getStrAccountUrl();
            String strNote = account.getStrAccountNote();

            String queryUpdate = "UPDATE ACCOUNTS SET TITLE = ?, USERNAME = ?, PASSWORD = ?, URL = ?, NOTE = ? WHERE _ID = ?";

            PreparedStatement stmt = conn.prepareStatement(queryUpdate);
            stmt.setString(1, strTitle);
            stmt.setString(2, strUsername);
            stmt.setString(3, strPassword);
            stmt.setString(4, strUrl);
            stmt.setString(5, strNote);
            stmt.setInt(6, iId);

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * löscht den Datensatz der Datenbank
     *
     * @param conn       Die verbindung zur Datenbank
     * @param iAccountId Id des ausgewählten accounts
     */
    public void deleteData(Connection conn, int iAccountId) {

        try {
            System.out.println("Erfolgreich mit der Datenbank für den Delte verbunden");

            //Objektdaten in variablen Speichern
            int iId = iAccountId;

            String queryUpdate = "DELETE FROM ACCOUNTS WHERE _ID = ?";

            PreparedStatement stmt = conn.prepareStatement(queryUpdate);
            stmt.setInt(1, iId);

            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
