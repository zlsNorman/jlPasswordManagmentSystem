package de.jlingott.jlpasswordmanagmentsystem.helper;

import de.jlingott.jlpasswordmanagmentsystem.Login;
import de.jlingott.jlpasswordmanagmentsystem.settings.AppTexts;
import javafx.scene.Scene;

import java.net.URL;

/**
 * setzt das Stylesheet für die Stages
 * da es immer das selbe Stylesheet ist habe ich mich entschieden diese einer eigenen Klasse zu widmen,
 * um redundanzen zu verhindern.
 */
public class StylesheetHelper {
    private static final String PATH_CSS = "stylesheet.css";

    private static StylesheetHelper instance;

    public static StylesheetHelper getInstance() {

        if (instance == null) {
            instance = new StylesheetHelper();
        }
        return instance;
    }

    /**
     * Setzt den Stylesheet für die Scene
     *
     * @param scene
     */
    public void setStylesheet(Scene scene) {

        URL urlStylesheet = Login.class.getResource(PATH_CSS);
        if (urlStylesheet == null) {
            System.out.println(AppTexts.WRONG_STYLESHEET_PATH);
        } else {
            String strStylesheet = urlStylesheet.toExternalForm();
            scene.getStylesheets().add(strStylesheet);
        }

    }


}
