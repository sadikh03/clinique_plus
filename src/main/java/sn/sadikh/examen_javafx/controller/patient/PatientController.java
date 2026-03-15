package sn.sadikh.examen_javafx.controller.patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.sadikh.examen_javafx.model.Patient;
import sn.sadikh.examen_javafx.repository.IPatientDAO;
import sn.sadikh.examen_javafx.repository.implement.PatientDAOImple;
import sn.sadikh.examen_javafx.service.PatientService;
import sn.sadikh.examen_javafx.utilitaire.Alerte;
import sn.sadikh.examen_javafx.validator.PatientValidator;


public class PatientController {
    @FXML private TextField txtNom, txtPrenom, txtTel , txtAdresse, txtAnt ;
    @FXML private DatePicker dtNaissance ;
    @FXML private ComboBox<String> cbGroupeSanguin , cbSexe;
    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient, String> colNom, colPrenom, colTel, colAdresse, colGroupe , colId , colNaissance , colSexe;

    private ObservableList<Patient> patientList = FXCollections.observableArrayList() ;
    private final IPatientDAO dao = new PatientDAOImple();
    private final PatientService service = new PatientService(dao);
    private Patient patientSelected = null;

    @FXML
    public void initialize() {
        // Configuration des colonnes (Comme tu l'as fait pour Produit)
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colGroupe.setCellValueFactory(new PropertyValueFactory<>("groupeSanguin"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        colSexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));

        tablePatients.setItems(patientList);
        chargerDonnees();

        // Gestion du clic pour modifier
        tablePatients.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                patientSelected = tablePatients.getSelectionModel().getSelectedItem();
                if (patientSelected != null) {
                    txtNom.setText(patientSelected.getNom());
                    txtPrenom.setText(patientSelected.getPrenom());
                    txtAdresse.setText(patientSelected.getAdresse());
                    txtAnt.setText(patientSelected.getAntecedentsMedicaux());
                    txtTel.setText(patientSelected.getTelephone());
                    cbGroupeSanguin.setValue(patientSelected.getGroupeSanguin());
                    cbSexe.setValue(patientSelected.getSexe());
                    dtNaissance.setValue(patientSelected.getDateNaissance());
                }
            }
        });
    }

    private void chargerDonnees() {
        patientList.setAll(service.listerTout());
    }

    private Patient creerPatient(){
        Patient p = new Patient();
        p.setNom(txtNom.getText());
        p.setPrenom(txtPrenom.getText());
        p.setAdresse(txtAdresse.getText());
        p.setTelephone(txtTel.getText());
        p.setSexe((String) cbSexe.getValue());
        p.setDateNaissance(dtNaissance.getValue());
        p.setGroupeSanguin((String) cbGroupeSanguin.getValue());
        p.setAntecedentsMedicaux(txtAnt.getText());

        return p ;
    }

    @FXML
    private void enregistrer() {

        String erreurs = PatientValidator.valider(txtNom.getText(),txtPrenom.getText(),txtTel.getText(),cbSexe.getValue());

        // 2. Vérifier le résultat
        if (!erreurs.isEmpty()) {
            // Tu affiches directement la chaîne retournée !
            Alerte.afficherAlerte("Erreurs de saisie", erreurs, Alert.AlertType.WARNING);
            return;
        }

        Patient p = creerPatient();

        service.ajouter(p);
        chargerDonnees();
        effacerChamps();
    }

    private void effacerChamps(){
        // 1. Vider les champs texte
        txtNom.clear();
        txtPrenom.clear();
        txtTel.clear();
        txtAnt.clear();
        txtAdresse.clear();
        dtNaissance.setValue(null);
        cbSexe.getSelectionModel().clearSelection();
        cbGroupeSanguin.getSelectionModel().clearSelection();

        patientSelected = null ;
    }

    @FXML
    private void supprimer(){
        if(patientSelected != null){
            service.supprimer(patientSelected.getId());
            effacerChamps();
            chargerDonnees();
        }
    }

    @FXML
    private void modifier(){
        if(patientSelected != null){

            patientSelected.setNom(txtNom.getText());
            patientSelected.setPrenom(txtPrenom.getText());
            patientSelected.setAdresse(txtAdresse.getText());
            patientSelected.setTelephone(txtTel.getText());
            patientSelected.setSexe((String) cbSexe.getValue());
            patientSelected.setDateNaissance(dtNaissance.getValue());
            patientSelected.setGroupeSanguin((String) cbGroupeSanguin.getValue());
            patientSelected.setAntecedentsMedicaux(txtAnt.getText());

            service.modifier(patientSelected);
            chargerDonnees();
            effacerChamps();
        }
    }

    @FXML
    private void annuler(){
        effacerChamps();
    }
}
