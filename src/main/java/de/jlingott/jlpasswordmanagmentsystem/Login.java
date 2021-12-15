package de.jlingott.jlpasswordmanagmentsystem;

import de.jlingott.jlpasswordmanagmentsystem.settings.AppTexts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Startet den Login
 */
public class Login extends Application {

    //TODO Stages einer eigenen Klasse widmen, damit hätte man zentralen zugriff auf jedes Stage element. Zudem wäre die übersicht besser da man eine zentrale Klasse für alle Stages hat.
    //TODO Stages schließen falls man die hauptumgebung verlassen hat

    public static final int LOGIN_SCENE_WIDTH = 350;
    public static final int LOGIN_SCENE_HEIGHT = 200;
    public static final String LOGIN_FXML = "login.fxml";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource(LOGIN_FXML));
        Scene scene = new Scene(fxmlLoader.load(), LOGIN_SCENE_WIDTH, LOGIN_SCENE_HEIGHT);
        stage.setTitle(AppTexts.APPLICATIONNAME);
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }
}