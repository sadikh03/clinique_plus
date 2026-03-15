package sn.sadikh.examen_javafx.controller.consultation;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.sadikh.examen_javafx.model.Consultation;
import sn.sadikh.examen_javafx.model.RendezVous;
import sn.sadikh.examen_javafx.repository.IConsultationDAO;
import sn.sadikh.examen_javafx.repository.IRendezVousDAO;
import sn.sadikh.examen_javafx.repository.implement.ConsultationDAOImple;
import sn.sadikh.examen_javafx.repository.implement.RendezVousDAOImple;
import sn.sadikh.examen_javafx.service.ConsultationService;
import sn.sadikh.examen_javafx.service.RendezVousService;
import sn.sadikh.examen_javafx.utilitaire.Alerte;
import sn.sadikh.examen_javafx.validator.ConsultationValidator;

public class consultationController {
    private Consultation consultationSelected ;
    private final IConsultationDAO dao = new ConsultationDAOImple();
    private final ConsultationService service = new ConsultationService(dao);
    private final IRendezVousDAO rdvDao = new RendezVousDAOImple();
    private final RendezVousService rdvService = new RendezVousService(rdvDao);

    @FXML private TextArea txtDianostic , txtPrescription , txtObservation ;
    @FXML private ComboBox<RendezVous> cbRDV ;
    @FXML private ObservableList<RendezVous> rdvs = FXCollections.observableArrayList();
    @FXML private TableView<Consultation> table ;
    @FXML private TableColumn<Consultation , String> colId , colDiagnostic , colPrescription , colObservation , colMedecin , colPatient , colDate ;
    @FXML private ObservableList<Consultation> consultations = FXCollections.observableArrayList();
    @FXML
    private void enregistrer(){
        String erreurs = ConsultationValidator.valider(txtDianostic.getText(),txtPrescription.getText(),cbRDV.getValue());

        // 2. Vérifier le résultat
        if (!erreurs.isEmpty()) {
            // Tu affiches directement la chaîne retournée !
            Alerte.afficherAlerte("Erreurs de saisie", erreurs, Alert.AlertType.WARNING);
            return;
        }

        Consultation c = creeConsultation();

        service.ajouter(creeConsultation());
        chargerDonnees();
        effacerChamps();
    }
    @FXML
    private void modifier(){
        if(consultationSelected != null){
            String erreurs = ConsultationValidator.valider(txtDianostic.getText(),txtPrescription.getText(),cbRDV.getValue());

            // 2. Vérifier le résultat
            if (!erreurs.isEmpty()) {
                // Tu affiches directement la chaîne retournée !
                Alerte.afficherAlerte("Erreurs de saisie", erreurs, Alert.AlertType.WARNING);
                return;
            }
            consultationSelected.setRendezVous(cbRDV.getValue());
            consultationSelected.setPrescription(txtPrescription.getText());
            consultationSelected.setObservations(txtObservation.getText());
            consultationSelected.setPrescription(txtPrescription.getText());

            service.modifier(consultationSelected);
            chargerDonnees();
            effacerChamps();
        }
    }
    @FXML
    private void annuler(){
        effacerChamps();
    }

    @FXML
    public void initialize() {
        colDate.setCellValueFactory(cell ->
                new SimpleStringProperty(String.valueOf(cell.getValue().getRendezVous().getDateHeure())));
        colMedecin.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getRendezVous().getMedecin().getUsername()));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDiagnostic.setCellValueFactory(new PropertyValueFactory<>("diagnostic"));
        colPrescription.setCellValueFactory(new PropertyValueFactory<>("prescription"));
        colObservation.setCellValueFactory(new PropertyValueFactory<>("observations"));
        colPatient.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getRendezVous().getPatient().getPrenom() + " " + cell.getValue().getRendezVous().getPatient().getNom()));

        table.setItems(consultations);
        chargerDonnees();

        // Gestion du clic pour modifier
        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                consultationSelected = table.getSelectionModel().getSelectedItem();
                if (consultationSelected != null) {
                    txtDianostic.setText(consultationSelected.getDiagnostic());
                    txtPrescription.setText(consultationSelected.getPrescription());
                    txtObservation.setText(consultationSelected.getObservations());
                    cbRDV.setValue(consultationSelected.getRendezVous());
                }
            }
        });
    }
    private void chargerDonnees() {
        consultations.setAll(service.listerTout());
        rdvs.setAll(rdvService.listerTout());
        cbRDV.setItems(rdvs);
    }

    private void effacerChamps(){
        txtDianostic.clear();
        txtObservation.clear();
        txtPrescription.clear();
        consultationSelected = null ;
        cbRDV.getSelectionModel().clearSelection();
    }

    private Consultation creeConsultation(){
        Consultation c = new Consultation();
        c.setDiagnostic(txtDianostic.getText());
        c.setPrescription(txtPrescription.getText());
        c.setObservations(txtObservation.getText());
        c.setRendezVous(cbRDV.getValue());

        return  c;
    }
}
