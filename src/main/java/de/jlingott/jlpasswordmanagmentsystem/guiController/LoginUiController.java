package de.jlingott.jlpasswordmanagmentsystem.guiController;

import de.jlingott.jlpasswordmanagmentsystem.Login;
import de.jlingott.jlpasswordmanagmentsystem.helper.PasswordHelper;
import de.jlingott.jlpasswordmanagmentsystem.helper.StylesheetHelper;
import de.jlingott.jlpasswordmanagmentsystem.logic.database.DbManager;
import de.jlingott.jlpasswordmanagmentsystem.settings.AppTexts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Diese Klasse steuert alle Ui Elemente die in der login.fxml definiert sind
 */
public class LoginUiController implements Initializable {

    //region Konstanten
    public static final int MAIN_VIEW_SCENE_WIDTH = 800;
    public static final int MAIN_VIEW_SCENE_HEIGHT = 600;
    public static final int CREATE_USER_SCENE_WIDTH = 300;
    public static final int CREATE_USER_SCENE_HEIGHT = 300;
    public static final String MAIN_VIEW_FXML = "main-view.fxml";
    public static final String CREATE_USER_FXML = "createUser.fxml";

    //endregion

    //region Variablen
    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label txtError;

    private Stage createUserStage;
    //endregion

    //region methoden funktionen

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createUserStage = new Stage();
    }

    //region handleLogin
    @FXML
    /**
     * wird nach dem Klick auf den Login aufgerufen und ruft alle weiteren funktionen zum einloggen auf
     *
     */
    public void handleLogin() {

        String strUsername = txtUsername.getText();
        String strEncryptPassword = setEncryptPassword();

        if (checkLogin(strUsername, strEncryptPassword)) {
            try {
                goToMainView();
            } catch (IOException e) {

                //Setzt den ErrorText für den User
                txtError.setText(AppTexts.JUMP_TO_MAIN_VIEW_FAILED);
            }
        } else {

            txtError.setText(AppTexts.USER_DOSNT_EXIST);
        }

    }

    /**
     * Überprüft ob die Eingaben mit der Datenbank stimmen
     *
     * @param strUsername        Der eingegebene Benutzername
     * @param strEncryptPassword Das eingegebene Passwort
     * @return Boolean
     */
    private boolean checkLogin(String strUsername, String strEncryptPassword) {

        return DbManager.getInstance().isDatabaseOnline() && DbManager.getInstance().handleUserVerification(strUsername, strEncryptPassword);
    }

    /**
     * holt sich anhand des {@link PasswordHelper} das Verschlüsselte Passwort
     *
     * @return String : Das verschlüsselte Passwort
     */
    private String setEncryptPassword() {

        String strPassword = txtPassword.getText();
        PasswordHelper passwordHelper = new PasswordHelper(strPassword);
        String strEncryptPassword = passwordHelper.getEncryptPassword();

        return strEncryptPassword;
    }


    /**
     * Nach dem Login wird der User zur Main-view.fxml weitergeleitet
     */
    private void goToMainView() throws IOException {

        Stage stage = (Stage) btnLogin.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource(MAIN_VIEW_FXML));
        Scene scene = new Scene(fxmlLoader.load(), MAIN_VIEW_SCENE_WIDTH, MAIN_VIEW_SCENE_HEIGHT);

        stage.setTitle(AppTexts.APPLICATIONNAME);
        stage.setResizable(true);

        StylesheetHelper.getInstance().setStylesheet(scene);

        stage.setScene(scene);

        setWindowInTheMiddle(stage);
    }

    /**
     * Diese Funktion setzt das Fenster in die Mitte des Bildschirms
     * <p>
     * diese Funktion Könnte man mit Transform: translate(-50%) im Webdesign vergleichen
     *
     * @param stage :: Aktuelle Stage vom Login
     */
    private void setWindowInTheMiddle(Stage stage) {

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }
    //endregion
    //region createUser

    @FXML
    /**
     * Ruft eine neue Stage auf um User zu erstellen
     */
    public void CreateUserStage() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource(CREATE_USER_FXML));
        Scene scene = new Scene(fxmlLoader.load(), CREATE_USER_SCENE_WIDTH, CREATE_USER_SCENE_HEIGHT);

        createUserStage.setTitle(AppTexts.CREATE_USER);
        createUserStage.setResizable(true);

        StylesheetHelper.getInstance().setStylesheet(scene);

        createUserStage.setResizable(false);
        createUserStage.setScene(scene);
        createUserStage.show();
    }
    //endregion
    //endregion
}
