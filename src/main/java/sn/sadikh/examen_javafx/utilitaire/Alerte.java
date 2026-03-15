package sn.sadikh.examen_javafx.utilitaire;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerte {
    public static void afficherAlerte(String titre, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null); // Pour faire plus simple
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean confirmerSuppression(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titre);
        alert.setHeaderText("Action irréversible");
        alert.setContentText(message);

        // On attend la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();

        // Retourne true si l'utilisateur a cliqué sur OK
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
