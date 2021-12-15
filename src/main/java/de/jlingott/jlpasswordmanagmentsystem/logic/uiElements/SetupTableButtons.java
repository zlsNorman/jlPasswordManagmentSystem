package de.jlingott.jlpasswordmanagmentsystem.logic.uiElements;

import de.jlingott.jlpasswordmanagmentsystem.Login;
import de.jlingott.jlpasswordmanagmentsystem.helper.StylesheetHelper;
import de.jlingott.jlpasswordmanagmentsystem.model.Account;
import de.jlingott.jlpasswordmanagmentsystem.service.TableViewService;
import de.jlingott.jlpasswordmanagmentsystem.settings.AppTexts;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Hier werden die Buttons der Tabelle konfguriert
 * Die Buttons werden in der {@link de.jlingott.jlpasswordmanagmentsystem.model.Account} Klasse erstellt
 */
public class SetupTableButtons {

    //region 0. Konstanten
    public static final int EDIT_SCENE_WIDTH = 300;
    public static final int EDIT_SCENE_HEIGHT = 500;
    public static final int DELETE_SCENE_WIDTH = 300;
    public static final int DELETE_SCENE_HEIGHT = 100;


    public static final String EDIT_FXML = "editAccount.fxml";
    public static final String DELETE_FXML = "deleteAccount.fxml";
    //endregion

    //region 1. Variablen
    private final Stage stageEdit;
    private final Stage stageDelete;
    private final Account account;
    //endregion

    //region.Konstruktor

    /**
     * Weißt den Buttons ihre Fnktionen zu
     *
     * @param account der einzelne Account
     */
    public SetupTableButtons(Account account) {

        stageEdit = new Stage();
        stageDelete = new Stage();

        this.account = account;
        account.btnEdit = new Button();
        account.btnDelete = new Button();

        account.btnEdit.setId("btnEdit");
        account.btnDelete.setId("btnDelete");

        account.getBtnEdit().setOnAction(this::editAccount);
        account.getBtnDelete().setOnAction(this::deleteAccount);
    }
    //endregion

    /**
     * Öffnet eine neue Stage zum Editieren des Accounts
     */
    private void editAccount(ActionEvent event) {

        TableViewService.getInstance().setSelectedAccount(account);

        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource(EDIT_FXML));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), EDIT_SCENE_WIDTH, EDIT_SCENE_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StylesheetHelper.getInstance().setStylesheet(scene);

        stageEdit.setTitle(AppTexts.EDIT_ACCOUNT);
        stageEdit.setScene(scene);
        stageEdit.show();
    }

    /**
     * Öffnet eine neue Stage zum Löschen des Accounts
     */
    private void deleteAccount(ActionEvent event) {

        TableViewService.getInstance().setSelectedAccount(account);

        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource(DELETE_FXML));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), DELETE_SCENE_WIDTH, DELETE_SCENE_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        stageDelete.setTitle(AppTexts.DELETE_ACCOUNT);
        stageDelete.setScene(scene);
        stageDelete.show();
    }
}
