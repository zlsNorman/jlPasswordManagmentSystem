module de.jlingott.jlpasswordmanagmentsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires Encrypter;


    opens de.jlingott.jlpasswordmanagmentsystem to javafx.fxml;
    exports de.jlingott.jlpasswordmanagmentsystem;
    exports de.jlingott.jlpasswordmanagmentsystem.model;
    opens de.jlingott.jlpasswordmanagmentsystem.model to javafx.fxml;
    exports de.jlingott.jlpasswordmanagmentsystem.guiController;
    opens de.jlingott.jlpasswordmanagmentsystem.guiController to javafx.fxml;
    exports de.jlingott.jlpasswordmanagmentsystem.service;
    opens de.jlingott.jlpasswordmanagmentsystem.service to javafx.fxml;

}