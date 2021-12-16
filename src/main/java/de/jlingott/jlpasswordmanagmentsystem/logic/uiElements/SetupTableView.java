package de.jlingott.jlpasswordmanagmentsystem.logic.uiElements;

import de.jlingott.jlpasswordmanagmentsystem.guiController.MainViewUiController;
import de.jlingott.jlpasswordmanagmentsystem.model.Account;
import de.jlingott.jlpasswordmanagmentsystem.service.TableViewService;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Diese Klasse füngiert zum bearbeiten der TableView des main-view.fxml
 */
public class SetupTableView {

    /**
     * diese Funktion bereitet die Tabelle im initialize vom UiController vor.
     * Sie füllt die Tabelle mit allen Inhalten
     *
     * @param mainViewElements
     */
    public void setupTable(MainViewUiController mainViewElements) {

        mainViewElements.colId.setCellValueFactory(new PropertyValueFactory<>("iAccountId"));
        mainViewElements.colTitle.setCellValueFactory(new PropertyValueFactory<>("strAccountTitle"));
        mainViewElements.colUsername.setCellValueFactory(new PropertyValueFactory<>("strAccountUsername"));
        mainViewElements.colPassword.setCellValueFactory(new PropertyValueFactory<>("strPasswordCensored"));
        mainViewElements.colUrl.setCellValueFactory(new PropertyValueFactory<>("strAccountUrl"));
        mainViewElements.colNote.setCellValueFactory(new PropertyValueFactory<>("strAccountNote"));
        mainViewElements.colBtnEdit.setCellValueFactory(new PropertyValueFactory<>("btnEdit"));
        mainViewElements.colBtnDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        mainViewElements.accountTable.setItems(getAllData());
    }

    /**
     * in dieser Funktion wird eine ObservableList von der Datenbank befüllt und für die Tabelle vorbereitet
     * <p>
     * Die ObservableList wird in der Klasse {@link TableViewService} gespeichert
     *
     * @return ObservableList :: Liste mit Account einträgen für die TableView
     */
    public ObservableList<Account> getAllData() {

        return TableViewService.getInstance().getAccountsObsList();
    }
}
