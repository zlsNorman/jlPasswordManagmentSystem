package de.jlingott.jlpasswordmanagmentsystem.guiController;

import de.jlingott.jlpasswordmanagmentsystem.helper.RequiredFieldsHelper;
import de.jlingott.jlpasswordmanagmentsystem.logic.database.DbManager;
import de.jlingott.jlpasswordmanagmentsystem.model.Account;
import de.jlingott.jlpasswordmanagmentsystem.service.TableViewService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Dieser UiController ist zuständig für alle Aktivitäten die im createAccount.fxml vorsich gehen
 */
public class EditAccountUiController implements Initializable {

    //region FXML Elements

    @FXML
    private Button btnEditAccount;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUrl;

    @FXML
    private TextArea txtAreaNote;

    @FXML
    private Button btnShow;

    //endregion

    //region methoden funktionen

    private ArrayList<TextField> listRequiredFields;

    @Override
    /**
     * wird beim Öffnen der Gui gestartet
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        hidePassword();

        initializeTextfields();
        addRequiredFields();
    }

    @FXML
    /**
     * Prüft ob das Passwort zur zeit angezeigt wird und zensiert diese wenn nicht
     */
    private void togglePasswordView() {

        boolean isHidden = txtPassword.getStyleClass().contains("hidePassword");

        if (isHidden) {

            showPassword();
        } else {

            hidePassword();
        }
    }

    @FXML
    /**
     * wird beim Drücken der Änder-Schaltfläche Ausgelöst und Ändert den Datensatz in der Datenbank
     */
    public void editAccount() {

        if (RequiredFieldsHelper.getInstance().checkRequiredFields(listRequiredFields)) {
            Account account = getAccount();

            try {

                //Daten in die Datenbank eintragen
                DbManager.getInstance().updateAccountIntoDb(account);

                //ObservableList aktualisieren
                TableViewService.getInstance().updateObservableList();
            } catch (Exception e) {

                e.printStackTrace();
            }

            //Fenster nach dem Speichern schließen
            Stage stage = (Stage) btnEditAccount.getScene().getWindow();
            stage.close();
        }
    }

    //region funktionen

    /**
     * Erstellt ein neues Account Objekt
     *
     * @return Account
     */
    private Account getAccount() {
        Account account = new Account();

        account.setiAccountId(Integer.parseInt(txtId.getText()));
        account.setStrAccountTitle(txtTitle.getText());
        account.setStrAccountUsername(txtUsername.getText());
        account.setStrAccountPassword(txtPassword.getText());
        account.setStrAccountUrl(txtUrl.getText());
        account.setStrAccountNote(txtAreaNote.getText());
        return account;
    }

    /**
     * Setzt die Werte für die Textfelder
     */
    private void initializeTextfields() {
        Account selectedAccount = TableViewService.getInstance().getSelectedAccount();

        txtId.setText(String.valueOf(selectedAccount.getiAccountId()));
        txtTitle.setText(selectedAccount.getStrAccountTitle());
        txtUsername.setText(selectedAccount.getStrAccountUsername());
        txtPassword.setText(selectedAccount.getStrAccountPassword());
        txtUrl.setText(selectedAccount.getStrAccountUrl());
        txtAreaNote.setText(selectedAccount.getStrAccountNote());
    }

    /**
     * Fügt dem Passwort-Textfeld eine Styleklasse hinzu um das Passwort zu verstecken
     */
    private void hidePassword() {
        txtPassword.setDisable(true);
        txtPassword.getStyleClass().add("hidePassword");

        btnShow.getStyleClass().remove("hidePasswordIcon");
        btnShow.getStyleClass().add("showPasswordIcon");

    }

    /**
     * Nimmt dem Passwort-Textfeld eine Styleklasse um das Passwort wieder anzuzeigen
     */
    private void showPassword() {
        txtPassword.setDisable(false);
        txtPassword.getStyleClass().remove("hidePassword");

        btnShow.getStyleClass().remove("showPasswordIcon");
        btnShow.getStyleClass().add("hidePasswordIcon");
    }

    /**
     * Fügt der ArrayList alle gewünschten Pflichtfelder für diese UI hinzu
     */
    private void addRequiredFields() {

        listRequiredFields = new ArrayList<>();

        listRequiredFields.add(txtTitle);
        listRequiredFields.add(txtUsername);
        listRequiredFields.add(txtPassword);
    }

    @FXML
    private void copyToClipboard(){

        String strPassword = txtPassword.getText();

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(strPassword);
        clipboard.setContent(content);
    }
    //endregion
}
