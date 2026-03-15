package sn.sadikh.examen_javafx.controller.facture;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.sadikh.examen_javafx.model.Consultation;
import sn.sadikh.examen_javafx.model.Facture;
import sn.sadikh.examen_javafx.model.enums.StatutPaiement;
import sn.sadikh.examen_javafx.repository.IConsultationDAO;
import sn.sadikh.examen_javafx.repository.IFactureDAO;
import sn.sadikh.examen_javafx.repository.implement.ConsultationDAOImple;
import sn.sadikh.examen_javafx.repository.implement.FactureDAOImple;
import sn.sadikh.examen_javafx.service.ConsultationService;
import sn.sadikh.examen_javafx.service.FactureService;
import sn.sadikh.examen_javafx.utilitaire.Alerte;
import sn.sadikh.examen_javafx.utilitaire.FacturePDFGenerator;
import sn.sadikh.examen_javafx.validator.FactureValidator;

import java.time.LocalDate;

public class FactureController {
    private Facture factureSelected ;

    private final IFactureDAO dao = new FactureDAOImple();
    private final FactureService service = new FactureService(dao);
    private final IConsultationDAO consultationDAO = new ConsultationDAOImple();
    private final ConsultationService consultationService = new ConsultationService(consultationDAO);

    @FXML private TextField txtMontant ;
    @FXML private ComboBox<StatutPaiement> cbStatut ;
    @FXML private ComboBox<String> cbModePaiement ;
    @FXML private DatePicker dtDate ;
    @FXML private ComboBox<Consultation> cbConsultation ;
    @FXML private TableView<Facture> table ;
    @FXML private ObservableList<Facture> factures = FXCollections.observableArrayList();
    @FXML private ObservableList<Consultation> consultations = FXCollections.observableArrayList();
    @FXML private TableColumn<Facture , String> colId , colMontant , colModePaiement , colDate ,colConsultation , colStatut ;
    @FXML
    private void enregistrer(){
        String erreurs = FactureValidator.valider(txtMontant.getText(), cbModePaiement.getValue());

        if (!erreurs.isEmpty()) {
            Alerte.afficherAlerte("Erreurs de saisie", erreurs, Alert.AlertType.WARNING);
            return;
        }

        Facture f = creeFacture() ;

        service.ajouter(f);
        Alerte.afficherAlerte("Succès", "Facture enregistrée !", Alert.AlertType.INFORMATION);
        FacturePDFGenerator.genererFacturePDF(f);
        Alerte.afficherAlerte("Succes", "Ajout effectuer avec succes", Alert.AlertType.INFORMATION);
        chargerDonnees();
        effacerChamps();
    }
    @FXML
    private void modifier(){
        if(factureSelected != null){
            String erreurs = FactureValidator.valider(txtMontant.getText(), cbModePaiement.getValue());

            if (!erreurs.isEmpty()) {
                Alerte.afficherAlerte("Erreurs de saisie", erreurs, Alert.AlertType.WARNING);
                return;
            }
            factureSelected.setDateFacture(dtDate.getValue());
            factureSelected.setStatut(cbStatut.getValue());
            factureSelected.setMontantTotal(Float.parseFloat(txtMontant.getText()));
            factureSelected.setModePaiement(cbModePaiement.getValue());
            factureSelected.setConsultation(cbConsultation.getValue());

            service.modifier(factureSelected);
            Alerte.afficherAlerte("Succes", "Modification effectuer avec succes", Alert.AlertType.INFORMATION);
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
        colConsultation.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getConsultation().getDiagnostic()));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMontant.setCellValueFactory(new PropertyValueFactory<>("montantTotal"));
        colModePaiement.setCellValueFactory(new PropertyValueFactory<>("modePaiement"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateFacture"));
        colStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));

        table.setItems(factures);
        chargerDonnees();

        // Gestion du clic pour modifier
        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                factureSelected = table.getSelectionModel().getSelectedItem();
                if (factureSelected != null) {
                    txtMontant.setText(String.valueOf(factureSelected.getMontantTotal()));
                    cbConsultation.setValue(factureSelected.getConsultation());
                    cbStatut.setValue(factureSelected.getStatut());
                    cbModePaiement.setValue(factureSelected.getModePaiement());
                    dtDate.setValue(factureSelected.getDateFacture());
                }
            }
        });
    }
    private void chargerDonnees() {
        factures.setAll(service.listerTout());
        consultations.setAll(consultationService.listerTout());
        cbConsultation.setItems(consultations);
        cbStatut.setItems(FXCollections.observableArrayList(StatutPaiement.values()));
    }

    private void effacerChamps(){
        txtMontant.clear();
        dtDate.setValue(LocalDate.now());
        cbConsultation.getSelectionModel().clearSelection();
        cbModePaiement.getSelectionModel().clearSelection();
        cbStatut.getSelectionModel().clearSelection();

        factureSelected = null ;
    }

    private Facture creeFacture(){
        Facture f = new Facture();
        f.setDateFacture(dtDate.getValue());
        f.setStatut(cbStatut.getValue());
        f.setMontantTotal(Float.parseFloat(txtMontant.getText()));
        f.setModePaiement(cbModePaiement.getValue());
        f.setConsultation(cbConsultation.getValue());

        return  f;
    }
}
