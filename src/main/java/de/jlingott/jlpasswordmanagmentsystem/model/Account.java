package de.jlingott.jlpasswordmanagmentsystem.model;

import de.jlingott.jlpasswordmanagmentsystem.logic.uiElements.SetupTableButtons;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 * Diese Klasse dient als Modelklasse der Account sie beinhaltet alle Attribute der Accounts
 */
public class Account {
    //region 1 konstante
    public static final String DEFAULT_STRING_VALUE = ">noValueSetYet<";
    public static final int DEFAULT_INT_VALUE = 1;
    public static final String PASSWORD_CENSORED = "*********";
    //endregion
    public Button btnEdit;
    public Button btnDelete;
    //region 2. variablen
    private int iAccountId;
    private String strAccountTitle;
    private String strAccountUsername;
    private String strAccountPassword;
    private String strAccountNote;
    private String strAccountUrl;
    private String strPasswordCensored;
    private SetupTableButtons tableButtons;
    //endregion

    //region 3. konstruktor
    public Account() {
        this.iAccountId = DEFAULT_INT_VALUE;
        this.strAccountTitle = DEFAULT_STRING_VALUE;
        this.strAccountUsername = DEFAULT_STRING_VALUE;
        this.strAccountPassword = DEFAULT_STRING_VALUE;
        this.strAccountNote = DEFAULT_STRING_VALUE;
        this.strAccountUrl = DEFAULT_STRING_VALUE;
        this.strPasswordCensored = PASSWORD_CENSORED;

        setUpButtons();
    }


    //endregion

    //region4. getter setter


    public int getiAccountId() {
        return iAccountId;
    }

    public void setiAccountId(int iAccountId) {
        this.iAccountId = iAccountId;
    }

    /**
     * wird für diePropertyValueFactory gebraucht ansonsten bekommt diese kein Value
     * WARNING: Can not retrieve property 'iAccountId' in PropertyValueFactory: javafx.scene.control.cell.PropertyValueFactory@366360de with provided class type: class de.jlingott.jlpasswordmanagmentsystem.model.Account
     */
    public SimpleStringProperty iAccountIdProperty() {
        return new SimpleStringProperty(String.valueOf(this.iAccountId));
    }

    public String getStrAccountTitle() {
        return strAccountTitle;
    }

    public void setStrAccountTitle(String strAccountTitle) {
        this.strAccountTitle = strAccountTitle;
    }

    public String getStrAccountUsername() {
        return strAccountUsername;
    }

    public void setStrAccountUsername(String strAccountUsername) {
        this.strAccountUsername = strAccountUsername;
    }

    public String getStrAccountPassword() {
        return strAccountPassword;
    }

    public void setStrAccountPassword(String strAccountPassword) {
        this.strAccountPassword = strAccountPassword;
    }

    public String getStrAccountNote() {
        return strAccountNote;
    }

    public void setStrAccountNote(String strAccountNote) {
        this.strAccountNote = strAccountNote;
    }

    public String getStrAccountUrl() {
        return strAccountUrl;
    }

    public void setStrAccountUrl(String strAccountUrl) {
        this.strAccountUrl = strAccountUrl;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(Button btnEdit) {
        this.btnEdit = btnEdit;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    public String getStrPasswordCensored() {
        return strPasswordCensored;
    }

    public void setStrCensoredPassword(String strPasswordCensored) {
        this.strPasswordCensored = strPasswordCensored;
    }

    //endregion

    //region 5. Funktionen
    private void setUpButtons() {
        tableButtons = new SetupTableButtons(this);
    }


    //endregion
}
