package de.jlingott.jlpasswordmanagmentsystem.logic.database;

import de.jlingott.jlpasswordmanagmentsystem.model.Account;
import de.jlingott.jlpasswordmanagmentsystem.model.User;
import de.jlingott.jlpasswordmanagmentsystem.service.UserDataService;
import de.jlingott.jlpasswordmanagmentsystem.settings.AppTexts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Diese Klasse ruft die Datenbankfunktionen der Dao Klasse auf
 * (Singleton damit die Verbindung nur ein einziges mal besteht)
 */
public class DbManager {

    //region variablen
    private static DbManager instance;
    private final DaoAccounts daoAccounts;
    private final DaoUser daoUser;

    //endregion

    //region konstruktor

    /**
     * Holt sich das Dao Objekt damit wir die Sql funktionen aufrufen können
     */
    private DbManager() {
        daoAccounts = new DaoAccounts();
        daoUser = new DaoUser();
    }
    //endregion

    //region methoden funktionen

    /**
     * holt eine einmalige Instanz der Klasse
     */
    public static synchronized DbManager getInstance() {

        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }


    //region Database Connection

    /**
     * Gibt eine generiert Datenbankverbindung mit Lese(r) als auch Schreibrechten(w)
     * zurueckt oder null sollte etwas schiefgelaufen sein.
     *
     * @return rwDbConnection : {@link Connection} : Verbindung zur Datenbank mit rw - Rechten
     */
    private Connection getDbConnection() throws Exception {
        Connection dbConnection = null;

        try {
            //1: Registeren des JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //2. Offenen einer Verbindung
            dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost/password_managment_system", "root", "");

        } catch (SQLNonTransientConnectionException sqlNoConnectionEx) {
            throw new Exception(AppTexts.NO_DATABASE_CONNECTION);
        } catch (ClassNotFoundException classNotFoundEx) {
            throw new Exception(AppTexts.CANNOT_LOAD_JDBC);
        }

        return dbConnection;
    }

    /**
     * Checkt ob die Datenbank online ist oder nicht
     *
     * @return isOnline : boolean : true : Dbist Online : false nicht
     */
    public boolean isDatabaseOnline() {
        boolean isOnline = true;
        try {
            this.getDbConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            isOnline = false;
        }
        return isOnline;
    }
    //endregion

    //region DaoAccounts

    /**
     * Holt sich alle Datensätze die in der Datenbank vorhanden sind und speicehrt diese in eine Liste
     *
     * @return List mit allen Datensätzen die in der Datenbank vorhanden sind
     */
    public List<Account> selectAccountsFromDb() {

        List<Account> accountList = new ArrayList<>();

        try {
            if (this.isDatabaseOnline()) {

                accountList = this.daoAccounts.selectData(this.getDbConnection());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountList;
    }


    /**
     * Stoßt die funktion InsertData von {@link DaoAccounts} an und füngt somit den Datensatz der Tabelle hinzu
     *
     * @param account Das Obejkt welches gespeichert werden soll
     */
    public void insertAccountIntoDb(Account account) {

        try {
            if (isDatabaseOnline()) {

                this.daoAccounts.insertData(this.getDbConnection(), account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //endregion

    /**
     * Stoßt die funktion UpdateData von {@link DaoAccounts} an und füngt somit den Datensatz der Tabelle hinzu
     *
     * @param account Das Obejkt welches gespeichert werden soll
     */
    public void updateAccountIntoDb(Account account) {

        try {
            if (isDatabaseOnline()) {

                this.daoAccounts.updateData(getDbConnection(), account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stoßt die funktion DeleteData von {@link DaoAccounts} an und füngt somit den Datensatz der Tabelle hinzu
     */
    public void deleteAccountIntoDb(int iAccountId) {

        try {
            if (isDatabaseOnline()) {

                this.daoAccounts.deleteData(getDbConnection(), iAccountId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //region DaoUser

    /**
     * Schaut ob der User im Login Verifiziert für die anmeldung ist,
     * falls er Verifiziert ist wird der eingeloggte User an den LoggedInUserService weitergegeben
     *
     * @param strUsername
     * @param strPassword
     * @return Boolean ob der User Verifiziert ist
     */
    public Boolean handleUserVerification(String strUsername, String strPassword) {

        Boolean isVerified = false;

        try {

            ResultSet selectedUser = daoUser.selectUser(getDbConnection(), strUsername, strPassword);
            isVerified = selectedUser.first();

            if (isVerified) {
                setLoggedInUser(selectedUser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isVerified;

    }

    /**
     * übergibt den eingelogten User in die Service-Klasse
     *
     * @param selectedUser : ResultSet vom Userselect {@link DaoUser#selectUser(Connection, String, String)}
     */
    private void setLoggedInUser(ResultSet selectedUser) {

        UserDataService user = UserDataService.getInstance();

        try {
            user.setiUserId(selectedUser.getInt(1));
            user.setStrUsername(selectedUser.getString(2));
            user.setStrPassword(selectedUser.getString(3));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Stoßt den Insert in die Datenbank an um einen neuen User anzulegen
     */
    public void insertUserIntoDb(User user) {

        try {
            daoUser.insertUser(getDbConnection(), user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Schaut ob der User schon exisitert,
     *
     * @param strUsername
     * @return Boolean ob der User Verifiziert ist
     */
    public Boolean checkExistence(String strUsername) {

        Boolean alreadyExist = false;

        try {

            ResultSet selectedUser = daoUser.selectUsername(getDbConnection(), strUsername);
            alreadyExist = selectedUser.first();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alreadyExist;

    }
    //endregion
    //endregion
}
