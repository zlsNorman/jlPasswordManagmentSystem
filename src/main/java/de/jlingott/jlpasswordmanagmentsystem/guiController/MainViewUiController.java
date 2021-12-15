package de.jlingott.jlpasswordmanagmentsystem.guiController;

import de.jlingott.jlpasswordmanagmentsystem.Login;
import de.jlingott.jlpasswordmanagmentsystem.helper.StylesheetHelper;
import de.jlingott.jlpasswordmanagmentsystem.logic.uiElements.SetupTableView;
import de.jlingott.jlpasswordmanagmentsystem.model.Account;
import de.jlingott.jlpasswordmanagmentsystem.service.TableViewService;
import de.jlingott.jlpasswordmanagmentsystem.service.UserDataService;
import de.jlingott.jlpasswordmanagmentsystem.settings.AppTexts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Diese Klasse steuert alle Ui elemetne die in der main-view definiert sind
 * <p>
 * die FXML elemente sind public da diese auch in anderen Klassen verwendet werden
 */

public class MainViewUiController implements Initializable {

    //region Konstanten
    public static final int CREATE_SCENE_WIDTH = 300;
    public static final int CREATE_SCENE_HEIGHT = 400;

    public static final String CREATE_FXML = "createAccount.fxml";

    //endregion

    //region variablen

    //region FXML

    @FXML
    public TableView<Account> accountTable;

    @FXML
    public TableColumn<Account, Integer> colId;
    @FXML
    public TableColumn<Account, String> colTitle;
    @FXML
    public TableColumn<Account, String> colUsername;
    @FXML
    public TableColumn<Account, String> colPassword;
    @FXML
    public TableColumn<Account, String> colUrl;
    @FXML
    public TableColumn<Account, String> colNote;
    @FXML
    public TableColumn<Account, Button> colBtnEdit;
    @FXML
    public TableColumn<Account, Button> colBtnDelete;

    @FXML
    Button btnLogout;

    //endregion


    private Stage stageCreate;
    private Stage stageDelete;

    //endregion

    //region funktionen Mehtoden

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        stageCreate = new Stage();
        stageDelete = new Stage();

        initializeTableView();
    }

    /**
     * Ruft die {@link SetupTableView} klasse auf und befüllt die Tabelle
     */
    private void initializeTableView() {

        printLoggedInUser();

        TableViewService.getInstance().updateObservableList();

        SetupTableView tableViewSetup = new SetupTableView();
        tableViewSetup.setupTable(this);
    }

    @FXML
    /**
     * Öffnet eine neue Stage zum Erstellen eines Accounts
     */
    private void createAccount() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource(CREATE_FXML));
        Scene scene = new Scene(fxmlLoader.load(), CREATE_SCENE_WIDTH, CREATE_SCENE_HEIGHT);

        StylesheetHelper.getInstance().setStylesheet(scene);

        stageCreate.setTitle(AppTexts.CREATE_ACCOUNT);
        stageCreate.setScene(scene);
        stageCreate.show();
    }


    /**
     * Schreibt den Eingelogten User in die Console
     */
    private void printLoggedInUser() {

        System.out.println(UserDataService.getInstance().toString());
    }

    //region logout
    @FXML
    private void handleLogout() {
        try {
            jumpToLoginView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void jumpToLoginView() throws IOException {

        Stage stage = (Stage) btnLogout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource(Login.LOGIN_FXML));
        Scene scene = new Scene(fxmlLoader.load(), Login.LOGIN_SCENE_WIDTH, Login.LOGIN_SCENE_HEIGHT);

        stage.setTitle(AppTexts.APPLICATIONNAME);
        stage.setResizable(false);

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

    //endregion

}