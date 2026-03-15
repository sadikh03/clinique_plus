package sn.sadikh.examen_javafx.controller.utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sn.sadikh.examen_javafx.utilitaire.SessionManager;

import java.io.IOException;
import java.util.Objects;

public class DashboardController {

    @FXML private StackPane contentArea;
    @FXML private Button btnUtilisateurs, btnPatients, btnRendezVous, btnConsultations;

    @FXML
    public void initialize() {
        // Vérification de la session
        if (SessionManager.getUtilisateur() != null) {
            String role = SessionManager.getUtilisateur().getRole().name();
            // Si l'utilisateur n'est pas ADMIN, on cache le bouton paramètres
            if (!role.equals("ADMIN")) {
                btnUtilisateurs.setVisible(false);
                btnUtilisateurs.setManaged(false);
            }
        }
    }

    private void loadView(String fxmlFile) {
        try {
            // Assure-toi que le chemin commence bien par /sn/sadikh/examen_javafx/view/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sn/sadikh/examen_javafx/view/" + fxmlFile));
            Node node = loader.load();

            contentArea.getChildren().clear();
            contentArea.getChildren().add(node);
        } catch (IOException e) {
            System.err.println("Erreur de chargement du fichier FXML : " + fxmlFile);
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Fichier FXML introuvable : " + fxmlFile);
        }
    }

    @FXML private void afficherPatients() { loadView("patient/gestion_patient.fxml"); }
    @FXML private void afficherRendezVous() { loadView("rendez_vous/gestion_rendez_vous.fxml"); }
    @FXML private void afficherConsultations() { loadView("consultation/gestion_consultation.fxml"); }
    @FXML private void afficherFactures() { loadView("facture/gestion_facture.fxml"); }
    @FXML private void afficherUtilisateurs() { loadView("utilisateur/gestion_utilisateur.fxml"); }

    @FXML
    private void deconnexion() {
        try {
            // 1. Vider la session
            SessionManager.fermerSession();

            // 2. Charger la page de login
            Parent loginRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sn/sadikh/examen_javafx/view/utilisateur/login.fxml")));
            Scene loginScene = new Scene(loginRoot);

            // 3. Récupérer la fenêtre actuelle (Stage) et changer la scène
            Stage stage = (Stage) contentArea.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Connexion - Clinique Plus");
            stage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}