package de.jlingott.jlpasswordmanagmentsystem.model;

/**
 * Modelklasse für Benutzer
 */
public class User {
    public static final String DEFAULT_STRING_VALUE = ">noValueSetYet<";
    //region konstante

    //endregion

    //region variable
    private String username;
    private String password;
    //endregion

    public User() {
        username = DEFAULT_STRING_VALUE;
        password = DEFAULT_STRING_VALUE;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
