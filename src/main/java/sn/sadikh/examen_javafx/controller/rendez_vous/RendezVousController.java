package sn.sadikh.examen_javafx.controller.rendez_vous;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.sadikh.examen_javafx.model.Patient;
import sn.sadikh.examen_javafx.model.RendezVous;
import sn.sadikh.examen_javafx.model.Utilisateur;
import sn.sadikh.examen_javafx.model.enums.Role;
import sn.sadikh.examen_javafx.model.enums.StatutRDV;
import sn.sadikh.examen_javafx.repository.IPatientDAO;
import sn.sadikh.examen_javafx.repository.IRendezVousDAO;
import sn.sadikh.examen_javafx.repository.IUtilisateurDAO;
import sn.sadikh.examen_javafx.repository.implement.PatientDAOImple;
import sn.sadikh.examen_javafx.repository.implement.RendezVousDAOImple;
import sn.sadikh.examen_javafx.repository.implement.UtilisateurDAOImple;
import sn.sadikh.examen_javafx.service.PatientService;
import sn.sadikh.examen_javafx.service.RendezVousService;
import sn.sadikh.examen_javafx.service.UtilisateurService;
import sn.sadikh.examen_javafx.utilitaire.Alerte;
import sn.sadikh.examen_javafx.utilitaire.SessionManager;
import sn.sadikh.examen_javafx.validator.RendezVousValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class RendezVousController {
    private RendezVous rdvSelected ;
    private final IRendezVousDAO dao = new RendezVousDAOImple();
    private final RendezVousService service = new RendezVousService(dao);
    private final IUtilisateurDAO userDao = new UtilisateurDAOImple();
    private final UtilisateurService userService = new UtilisateurService(userDao);
    private final IPatientDAO patientDao = new PatientDAOImple();
    private final PatientService patientService = new PatientService(patientDao);

    @FXML private ComboBox<Patient> cbPatient;
    @FXML private ComboBox<Utilisateur> cbMedecin;
    @FXML private ComboBox<String>cbHeure ;
    @FXML private ComboBox<StatutRDV> cbStatut ;
    @FXML private DatePicker dtRDV ;
    @FXML ObservableList<RendezVous> rdvs = FXCollections.observableArrayList() ;
    @FXML ObservableList<Patient> patients = FXCollections.observableArrayList() ;
    @FXML ObservableList<Utilisateur> medecins = FXCollections.observableArrayList() ;
    @FXML TableView<RendezVous> table ;
    @FXML TableColumn<RendezVous , String> colDate , colPatient , colMedecin , colId , colStatut ;


    @FXML
    private void modifier(){
        if(rdvSelected != null){

            String erreurs = RendezVousValidator.valider(LocalDateTime.of(dtRDV.getValue() , LocalTime.parse(cbHeure.getValue())),cbPatient.getValue() , cbMedecin.getValue());

            // 2. Vérifier le résultat
            if (!erreurs.isEmpty()) {
                // Tu affiches directement la chaîne retournée !
                Alerte.afficherAlerte("Erreurs de saisie", erreurs, Alert.AlertType.WARNING);
                return;
            }

            rdvSelected.setStatut(cbStatut.getValue());
            rdvSelected.setMedecin(cbMedecin.getValue());
            rdvSelected.setPatient(cbPatient.getValue());
            rdvSelected.setDateHeure(LocalDateTime.of(dtRDV.getValue() , LocalTime.parse(cbHeure.getValue())));

            service.modifier(rdvSelected);
            Alerte.afficherAlerte("Succes", "Modification effectuer avec succes", Alert.AlertType.INFORMATION);
            chargerDonnees();
        }
    }

    @FXML
    private void annuler(){
        effacerChamps();
    }

    @FXML
    private void enregistrer(){

        String erreurs = RendezVousValidator.valider(LocalDateTime.of(dtRDV.getValue() , LocalTime.parse(cbHeure.getValue())),cbPatient.getValue() , cbMedecin.getValue());

        // 2. Vérifier le résultat
        if (!erreurs.isEmpty()) {
            // Tu affiches directement la chaîne retournée !
            Alerte.afficherAlerte("Erreurs de saisie", erreurs, Alert.AlertType.WARNING);
            return;
        }

        RendezVous rdv = creeRDV();
        Alerte.afficherAlerte("Succes", "Ajout effectuer avec succes", Alert.AlertType.INFORMATION);

        service.ajouter(rdv);
        chargerDonnees();
    }

    private void remplirCreneauxHoraires() {
        ObservableList<String> heures = FXCollections.observableArrayList();

        for (int h = 8; h <= 18; h++) {
            String heureH = String.format("%02d", h);
            heures.add(heureH + ":00");
            heures.add(heureH + ":30");
        }

        cbHeure.setItems(heures);
    }

    @FXML
    public void initialize() {
        // Configuration des colonnes (Comme tu l'as fait pour Produit)
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateHeure"));
        colStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colMedecin.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getMedecin().getUsername()));
        colPatient.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getPatient().getPrenom() + " " + cell.getValue().getPatient().getNom()));

        table.setItems(rdvs);
        chargerDonnees();

        // Gestion du clic pour modifier
        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                rdvSelected = table.getSelectionModel().getSelectedItem();
                if (rdvSelected != null) {
                    dtRDV.setValue(LocalDate.from(rdvSelected.getDateHeure()));
                    cbHeure.setValue(String.valueOf(LocalTime.from(rdvSelected.getDateHeure())));
                    cbMedecin.setValue(rdvSelected.getMedecin());
                    cbPatient.setValue(rdvSelected.getPatient());
                    cbStatut.setValue(rdvSelected.getStatut());
                }
            }
        });
    }
    private void chargerDonnees() {
        Utilisateur currentUser = SessionManager.getUtilisateur();
        rdvs.setAll(currentUser.getRole() != Role.MEDECIN ? service.listerTout() : service.listerToutParMedecin(currentUser));
        patients.setAll(patientService.listerTout());
        cbPatient.setItems(patients);
        medecins.setAll(userService.findByRole(Role.ADMIN));
        cbMedecin.setItems(medecins);
        cbStatut.setItems(FXCollections.observableArrayList(StatutRDV.values()));
        remplirCreneauxHoraires();
    }

    private RendezVous creeRDV(){
        RendezVous rdv = new RendezVous();
        rdv.setMedecin(cbMedecin.getValue());
        rdv.setPatient(cbPatient.getValue());
        rdv.setDateHeure(LocalDateTime.of(dtRDV.getValue() , LocalTime.parse(cbHeure.getValue())));
        rdv.setStatut(cbStatut.getValue());

        return rdv ;
    }

    private void effacerChamps(){
        cbStatut.getSelectionModel().clearSelection();
        cbPatient.getSelectionModel().clearSelection();
        cbMedecin.getSelectionModel().clearSelection();
        cbHeure.getSelectionModel().clearSelection();

        rdvSelected = null ;
    }
}
