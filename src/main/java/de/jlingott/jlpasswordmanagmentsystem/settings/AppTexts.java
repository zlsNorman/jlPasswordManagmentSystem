package de.jlingott.jlpasswordmanagmentsystem.settings;

/**
 * beinhaltet alle statischen Texte als Konstanten
 */
public class AppTexts {
    //region Stagenames
    public static final String APPLICATIONNAME = "Passwort Managment System";
    public static final String CREATE_ACCOUNT = "Account erstellen";
    public static final String EDIT_ACCOUNT = "Account ändern";
    public static final String DELETE_ACCOUNT = "Account löschen";
    public static final String CREATE_USER = "Benutzer erstellen";
    public static final String LOGIN = "Login";

    //endregion

    //region Textfield Errors
    public static final String USER_DOSNT_EXIST = "Dieser Benutzer existiert nicht,\n" +
            "stellen Sie sicher das ihre eingaben korrekt sind.";
    public static final String PASSWORT_NEEDS_TO_BE_EQUAl = "Passwörter müssen übereinstimmen";
    public static final String JUMP_TO_MAIN_VIEW_FAILED = "Verbindung mit der Main-View konnte nicht hergestellt werden";

    //endregion

    //region System out println
    public static final String WRONG_STYLESHEET_PATH = "Falscher Stylsheet-Path";
    public static final String ENCRYPT_PASSWORD_IS_EMPTY = "getEncryptPassword() is empty";
    public static final String CONNECTION_FOR_INPUT_WAS_SUCCESSFULL = "Erfolgreich mit der Datenbank für den INSERT verbunden";
    public static final String CONNECTION_FOR_UPDATE_WAS_SUCCESSFULL = "Erfolgreich mit der Datenbank für den UPDATE verbunden";

    //endregion

    //region exeptions

    public static final String NO_DATABASE_CONNECTION = "Keine Datenbankverbindung";
    public static final String CANNOT_LOAD_JDBC = "JDBC Treiber konnte nicht geladen werden";
    //endregion


    private AppTexts() {
    }

}
