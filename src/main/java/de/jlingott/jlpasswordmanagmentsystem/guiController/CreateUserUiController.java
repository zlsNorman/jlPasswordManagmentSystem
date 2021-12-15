package de.jlingott.jlpasswordmanagmentsystem.guiController;

import de.jlingott.jlpasswordmanagmentsystem.helper.PasswordHelper;
import de.jlingott.jlpasswordmanagmentsystem.helper.RequiredFieldsHelper;
import de.jlingott.jlpasswordmanagmentsystem.logic.database.DbManager;
import de.jlingott.jlpasswordmanagmentsystem.model.User;
import de.jlingott.jlpasswordmanagmentsystem.settings.AppTexts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Diese Klasse wird beim Nutzen der createUser.fxml genutzt
 */
public class CreateUserUiController implements Initializable {

    //region variablen

    private ArrayList<TextField> listRequiredFields;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtCheckPassword;

    @FXML
    private Label lblError;
    //endregion

    //region funktionen methoden
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRequiredFields();
    }

    @FXML
    /**
     * Wird nach dem klick auf Benutzer erstellen ausgelöst, und speichert unter bestimmten bedingungen den User
     * in die Datenbank
     */
    private void saveUser() {

        if (RequiredFieldsHelper.getInstance().checkRequiredFields(listRequiredFields) && checkPasswords()) {

            String strUsername = txtUsername.getText();

            //Prüft ob der Benutzername schon exisitiert
            if (!DbManager.getInstance().checkExistence(strUsername)) {

                DbManager.getInstance().insertUserIntoDb(getNewUser());

                //Fenster nach dem Speichern schließen
                Stage stage = (Stage) txtUsername.getScene().getWindow();
                stage.close();
            } else {

                lblError.setText("dieser Benutzername exisitert bereits");
            }
        }
    }

    /**
     * Fügt der ArrayList alle gewünschten Pflichtfelder für diese UI hinzu
     */
    private void addRequiredFields() {

        listRequiredFields = new ArrayList<>();

        listRequiredFields.add(txtUsername);
        listRequiredFields.add(txtPassword);
        listRequiredFields.add(txtCheckPassword);
    }

    /**
     * Prüft ob die Passwörter identisch sind.
     *
     * @return true falls identisch
     */
    private boolean checkPasswords() {

        boolean isEqual = false;

        String strPassword = txtPassword.getText();
        String strCheckPassword = txtCheckPassword.getText();

        if (strPassword.equals(strCheckPassword)) {

            isEqual = true;
        } else {

            lblError.setText(AppTexts.PASSWORT_NEEDS_TO_BE_EQUAl);
        }

        return isEqual;

    }

    /**
     * Erstellt ein neues User Objekt
     *
     * @return User Objekt
     */
    private User getNewUser() {
        PasswordHelper passwordHelper = new PasswordHelper(txtPassword.getText());
        User newUser = new User();

        newUser.setUsername(txtUsername.getText());
        newUser.setPassword(passwordHelper.getEncryptPassword());

        return newUser;
    }

    //endregion


}
