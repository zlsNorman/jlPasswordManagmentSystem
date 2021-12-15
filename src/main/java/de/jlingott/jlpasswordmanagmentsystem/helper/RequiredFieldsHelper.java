package de.jlingott.jlpasswordmanagmentsystem.helper;

import javafx.scene.control.TextField;

import java.util.List;

/**
 * Diese Klasse hilft beim erstellen der Pflichtfelder
 */
public class RequiredFieldsHelper {

    public static RequiredFieldsHelper instance;

    public static RequiredFieldsHelper getInstance() {

        if (instance == null) {
            instance = new RequiredFieldsHelper();
        }

        return instance;
    }


    /**
     * wird angestoßen nachden auf speichern gedrückt wurde
     * Prüft ob alle pflichtfelder ausgefüllt sind falls nicht wird das GUI rot gefärbt und ein errortext kommt
     *
     * @return boolean : true falls ein textfield empty ist
     */
    public boolean checkRequiredFields(List<TextField> listRequiredFields) {

        boolean isFilled = true;
        for (TextField txtField : listRequiredFields) {

            if (txtField.getText().isEmpty() || txtField.getText().isBlank()) {

                txtField.getStyleClass().add("required");
                txtField.promptTextProperty().set("Pflichtfeld");

                isFilled = false;

            } else {

                txtField.getStyleClass().remove("required");
            }
        }
        return isFilled;
    }

}
