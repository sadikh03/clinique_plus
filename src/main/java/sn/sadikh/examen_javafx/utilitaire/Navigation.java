package sn.sadikh.examen_javafx.utilitaire;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Navigation {
    public static void transition(Stage stage, String fxmlPath, String title) throws IOException {
        Parent root = FXMLLoader.load(Navigation.class.getResource("/sn/sadikh/examen_javafx/view/" + fxmlPath));
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
