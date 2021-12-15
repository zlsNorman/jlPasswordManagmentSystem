package de.jlingott.jlpasswordmanagmentsystem.guiController;

import de.jlingott.jlpasswordmanagmentsystem.logic.database.DbManager;
import de.jlingott.jlpasswordmanagmentsystem.service.TableViewService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Dieser UiController ist zuständig für alle Aktivitäten die im createAccount.fxml vorsich gehen
 */
public class DeleteAccountUiController {

    //region FXML elements
    @FXML
    Stage stage;
    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;

    //endregion

    //region methoden funktionen

    @FXML
    /**
     * Holt sich die Stage von einem FXML element und schließt diese
     */
    public void closeStage() {

        stage = (Stage) btnYes.getScene().getWindow();
        stage.close();
    }

    @FXML
    /**
     * wird beim Drücken der Ja-Schaltfläche Ausgelöst und Löscht den Datensatz in der Datenbank
     */
    public void deleteAccount() {

        int iAccountId = TableViewService.getInstance().getSelectedAccount().getiAccountId();

        try {

            //Daten in die Datenbank eintragen
            DbManager.getInstance().deleteAccountIntoDb(iAccountId);

            //ObservableList aktualisieren
            TableViewService.getInstance().updateObservableList();
        } catch (Exception e) {

            e.printStackTrace();
        }

        //Fenster nach dem Speichern schließen
        stage = (Stage) btnYes.getScene().getWindow();
        stage.close();

    }
    //endregion
}
