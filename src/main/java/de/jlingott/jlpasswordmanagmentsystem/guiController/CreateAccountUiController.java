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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Dieser UiController ist zuständig für alle Aktivitäten die im createAccount.fxml vorsich gehen
 */
public class CreateAccountUiController implements Initializable {

    //region variablen
    //region FXML elements

    @FXML
    private Button btnSaveAccount;

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
    //endregion

    private ArrayList<TextField> listRequiredFields;
    //endregion

    //region funktionen methoden
    @Override
    /**
     * wird beim Öffnen der Gui gestartet
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addRequiredFields();
    }

    @FXML
    /**
     * wird beim Drücken der Speicher-Schaltfläche Ausgelöst und Speichert den Datensatz in der Datenbank
     * falls alle Pflichtfelder ausgefüllt sind
     */
    public void saveAccount() {

        //prüfen ob alle Pflichtfelder ausgefüllt sind
        if (RequiredFieldsHelper.getInstance().checkRequiredFields(listRequiredFields)) {
            Account account = getAccount();

            try {
                //Daten in die Datenbank eintragen
                DbManager.getInstance().insertAccountIntoDb(account);

                //ObservableList aktualisieren
                TableViewService.getInstance().updateObservableList();

            } catch (Exception e) {
                e.printStackTrace();
            }

            //Fenster nach dem Speichern schließen
            Stage stage = (Stage) btnSaveAccount.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Erstellt ein neues Account Objekt
     *
     * @return Account Objekt
     */
    private Account getAccount() {
        Account account = new Account();

        account.setStrAccountTitle(txtTitle.getText());
        account.setStrAccountUsername(txtUsername.getText());
        account.setStrAccountPassword(txtPassword.getText());
        account.setStrAccountUrl(txtUrl.getText());
        account.setStrAccountNote(txtAreaNote.getText());
        return account;
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

    //endregion
}
