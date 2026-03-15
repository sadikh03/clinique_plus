package sn.sadikh.examen_javafx.utilitaire;

import javafx.scene.control.Alert;

public class Alerte {
    public static void afficherAlerte(String titre, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null); // Pour faire plus simple
        alert.setContentText(message);
        alert.showAndWait();
    }
}
