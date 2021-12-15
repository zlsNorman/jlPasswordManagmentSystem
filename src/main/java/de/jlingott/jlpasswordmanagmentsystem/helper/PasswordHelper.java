package de.jlingott.jlpasswordmanagmentsystem.helper;


import de.jlingott.jlpasswordmanagmentsystem.settings.AppTexts;
import de.rhistel.Encrypter;

/**
 * Diese Klasse hilft dabei Funktionen für die Passwortintegration durchzuführen
 */
public class PasswordHelper {

    //region Konstanten
    public static final String EMPTY_STRING = "";
    public static final String CENSORED_PASSWORD_CHAR = "*";
    //endregion

    //region Variablen
    private final String password;

    //endregion

    //region Konstruktor
    public PasswordHelper(String strAccountPassword) {

        password = strAccountPassword;
    }
    //endregion

    //region methoden funktionen

    /**
     * Holt sich mithilfe der {@link Encrypter} Klasse das verschlüsselte Passwort und gibt dieses zurück
     *
     * @return verschlüsselte Passwort
     */
    public String getEncryptPassword() {

        String strEncryptPassword = EMPTY_STRING;
        try {
            strEncryptPassword = Encrypter.getInstance().encryptToSha256HashHexString(password);
        } catch (Exception e) {
            System.out.println(AppTexts.ENCRYPT_PASSWORD_IS_EMPTY);
        }
        return strEncryptPassword;
    }


    /**
     * Password in * umtauschen und in der Tabelle anzeigen
     */
    public String getCensoredPassword() {

        int passwordLength = this.password.length();
        String strCensoredPassword = EMPTY_STRING;

        for (int i = 0; i < passwordLength; i++) {
            strCensoredPassword += CENSORED_PASSWORD_CHAR;
        }
        return strCensoredPassword;
    }
    //endregion
}
