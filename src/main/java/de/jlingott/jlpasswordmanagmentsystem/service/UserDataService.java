package de.jlingott.jlpasswordmanagmentsystem.service;

/**
 * Diese Klasse speichert den Eingelogten User.
 * Es gibt immer nur einen Eingelogten User deshalb nutz ich dieses Model als Singleton
 */
public class UserDataService {
    public static final int DEFAULT_INT_VALUE = 0;
    public static final String DEFAULT_STRING_VALUE = ">noValueSetYet<";

    //region konstanten
    //endregion

    //region variable

    private static UserDataService loggedInUser;
    private String strUsername;
    private String strPassword;
    private int iUserId;
    //endregion

    //region konstruktor
    public UserDataService() {

        this.strUsername = DEFAULT_STRING_VALUE;
        this.strPassword = DEFAULT_STRING_VALUE;
        this.iUserId = DEFAULT_INT_VALUE;
    }
    //endregion

    //region funktionen methoden

    public static synchronized UserDataService getInstance() {

        if (loggedInUser == null) {
            loggedInUser = new UserDataService();
        }

        return loggedInUser;
    }

    @Override
    public String toString() {
        return "LoggedInUserService{" +
                "strUsername='" + strUsername + '\'' +
                ", strPassword='" + strPassword + '\'' +
                ", iUserId=" + iUserId +
                '}';
    }

    //endregion
    //region getter setter

    public String getStrUsername() {
        return strUsername;
    }

    public void setStrUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public int getiUserId() {
        return iUserId;
    }

    public void setiUserId(int iUserId) {
        this.iUserId = iUserId;
    }

    //endregion
}
