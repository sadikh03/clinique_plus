package sn.sadikh.examen_javafx.controller.utilisateur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sn.sadikh.examen_javafx.model.Utilisateur;
import sn.sadikh.examen_javafx.model.enums.Role;
import sn.sadikh.examen_javafx.repository.IUtilisateurDAO;
import sn.sadikh.examen_javafx.repository.implement.UtilisateurDAOImple;
import sn.sadikh.examen_javafx.service.UtilisateurService;
import sn.sadikh.examen_javafx.utilitaire.Navigation;
import sn.sadikh.examen_javafx.utilitaire.SecurityUtils;
import sn.sadikh.examen_javafx.utilitaire.SessionManager;

public class LoginController {
    @FXML private TextField txtLogin;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;

    private final IUtilisateurDAO dao = new UtilisateurDAOImple();
    private final UtilisateurService userService = new UtilisateurService(dao);

    @FXML
    private void handleLogin() {
        String login = txtLogin.getText();
        String password = txtPassword.getText();

        // 1. On cherche l'utilisateur par son login
        Utilisateur user = userService.rechercherParLogin(login);

        // 2. Vérification (Hachage inclus) [cite: 23, 24]
        if (user != null && user.getPassword().equals(SecurityUtils.hashPassword(password))) {
            // 3. Stocker dans la session
            SessionManager.ouvrirSession(user);

            // 4. Rediriger selon le rôle [cite: 19]
            redirigerVersAccueil();
        } else {
            lblError.setText("Identifiants incorrects !");
        }
    }

    private void redirigerVersAccueil() {
        try {
            Stage stage = (Stage) txtLogin.getScene().getWindow();
            Navigation.transition(stage, "utilisateur/dashboard.fxml", "Tableau de Bord - " + SessionManager.getUtilisateur().getRole());
        } catch (Exception e) {
            System.out.println("erreur de navigation");
        }
    }
}
