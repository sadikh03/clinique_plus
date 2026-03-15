package sn.sadikh.examen_javafx.controller.utilisateur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.sadikh.examen_javafx.model.Patient;
import sn.sadikh.examen_javafx.model.Utilisateur;
import sn.sadikh.examen_javafx.model.enums.Role;
import sn.sadikh.examen_javafx.repository.IUtilisateurDAO;
import sn.sadikh.examen_javafx.repository.implement.UtilisateurDAOImple;
import sn.sadikh.examen_javafx.service.UtilisateurService;
import sn.sadikh.examen_javafx.utilitaire.Alerte;
import sn.sadikh.examen_javafx.utilitaire.SecurityUtils;
import sn.sadikh.examen_javafx.validator.PatientValidator;
import sn.sadikh.examen_javafx.validator.UtilisateurValidator;

public class UtilisateurController {
    private Utilisateur utilisateurSelected;

    private final IUtilisateurDAO dao = new UtilisateurDAOImple();
    private final UtilisateurService service = new UtilisateurService(dao);

    @FXML private TextField txtUsername , txtLogin ;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<Role> cbRole;
    @FXML private TableView<Utilisateur> table ;
    @FXML private ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList() ;
    @FXML private TableColumn<Utilisateur,String> colId , colUsername , colLogin , colPassword , colRole ;

    @FXML
    public void initialize() {
        // Configuration des colonnes (Comme tu l'as fait pour Produit)
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        table.setItems(utilisateurs);
        chargerDonnees();

        // Gestion du clic pour modifier
        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                utilisateurSelected = table.getSelectionModel().getSelectedItem();
                if (utilisateurSelected != null) {
                    txtLogin.setText(utilisateurSelected.getLogin());
                    txtUsername.setText(utilisateurSelected.getUsername());
                    cbRole.setValue(utilisateurSelected.getRole());
                }
            }
        });
    }

    private void chargerDonnees() {
        utilisateurs.setAll(service.listerTout());
        cbRole.setItems(FXCollections.observableArrayList(Role.values()));
    }

    private Utilisateur creeUtilisateur(){
        Utilisateur u = new Utilisateur();
        u.setPassword(SecurityUtils.hashPassword(txtPassword.getText()));
        u.setLogin(txtLogin.getText());
        u.setUsername(txtUsername.getText());
        u.setRole(cbRole.getValue());

        return u ;
    }

    @FXML
    private void enregistrer() {

        String erreurs = UtilisateurValidator.valider(txtUsername.getText(),txtLogin.getText(),txtPassword.getText(),cbRole.getValue());

        // 2. Vérifier le résultat
        if (!erreurs.isEmpty()) {
            // Tu affiches directement la chaîne retournée !
            Alerte.afficherAlerte("Erreurs de saisie", erreurs, Alert.AlertType.WARNING);
            return;
        }

        service.ajouter(creeUtilisateur());
        Alerte.afficherAlerte("Succes", "Ajout effectuer avec succes", Alert.AlertType.INFORMATION);
        chargerDonnees();
        effacerChamps();
    }

    private void effacerChamps(){
        // 1. Vider les champs texte
        txtUsername.clear();
        txtPassword.clear();
        txtLogin.clear();
        cbRole.getSelectionModel().clearSelection();

        utilisateurSelected = null ;
    }

    @FXML
    private void supprimer(){
        if(utilisateurSelected != null){
            boolean confirme = Alerte.confirmerSuppression(
                    "Confirmation de suppression",
                    "Voulez-vous vraiment supprimer l'utilisateur " + utilisateurSelected.getUsername() + " ?"
            );

            if (confirme) {
                service.supprimer(utilisateurSelected.getId());
                effacerChamps();
                chargerDonnees();
                Alerte.afficherAlerte("Succès", "Patient supprimé avec succès", Alert.AlertType.INFORMATION);
            }
        }
    }

    @FXML
    private void modifier(){
        if(utilisateurSelected != null){

            String erreurs = UtilisateurValidator.valider(txtUsername.getText(),txtLogin.getText(),txtPassword.getText(),cbRole.getValue());

            // 2. Vérifier le résultat
            if (!erreurs.isEmpty()) {
                // Tu affiches directement la chaîne retournée !
                Alerte.afficherAlerte("Erreurs de saisie", erreurs, Alert.AlertType.WARNING);
                return;
            }

            utilisateurSelected.setUsername(txtUsername.getText());
            utilisateurSelected.setLogin(txtLogin.getText());
            utilisateurSelected.setPassword(SecurityUtils.hashPassword(txtPassword.getText()));
            utilisateurSelected.setRole(cbRole.getValue());

            service.modifier(utilisateurSelected);
            Alerte.afficherAlerte("Succes", "Ajout effectuer avec succes", Alert.AlertType.INFORMATION);
            chargerDonnees();
            effacerChamps();
        }
    }

    @FXML
    private void annuler(){
        effacerChamps();
    }
}
