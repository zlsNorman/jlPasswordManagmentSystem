package de.jlingott.jlpasswordmanagmentsystem.service;

import de.jlingott.jlpasswordmanagmentsystem.logic.database.DbManager;
import de.jlingott.jlpasswordmanagmentsystem.model.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Diese Klasse dient zur Kommunikation zwischen den verschiedenen Stages um die Obs List aufzurufen
 * <p>
 * die Variable selected Account wird beim click auf dem Button {@link de.jlingott.jlpasswordmanagmentsystem.logic.uiElements.SetupTableButtons}
 * editAccount() und deleteAccount() gesetztz
 */
public class TableViewService {

    private static TableViewService accountTable;
    private Account selectedAccount;
    private ObservableList<Account> accountsObsList;


    //region Konstruktor

    /**
     * Holt sich die Liste der Accounts aus der Datenbank und fügt diese in eine ObservableArrayList hinzu
     */
    private TableViewService() {

        this.accountsObsList = FXCollections.observableArrayList(DbManager.getInstance().selectAccountsFromDb());
    }
    //endregion

    public synchronized static TableViewService getInstance() {
        if (accountTable == null) {
            accountTable = new TableViewService();
        }
        return accountTable;
    }

    /**
     * Leert die bestehende ObsvavleList und fügt erneut die Datensätze von der Tabelle accounts hinzu
     * (Nötig damit die TableView nach dem ändern eines datensatzes aktuellsiert wird)
     */
    public synchronized void updateObservableList() {

        this.accountsObsList.clear();
        this.accountsObsList.addAll(DbManager.getInstance().selectAccountsFromDb());
    }

    //region getter setter
    public ObservableList<Account> getAccountsObsList() {

        return accountsObsList;
    }

    public void setAccountsObsList(ObservableList<Account> accountsObsList) {
        this.accountsObsList = accountsObsList;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    //endregion
}
