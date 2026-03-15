package sn.sadikh.examen_javafx.utilitaire;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import sn.sadikh.examen_javafx.model.Facture;

import java.io.FileOutputStream;
import java.io.IOException;

public class FacturePDFGenerator {

    public static void genererFacturePDF(Facture facture) {
        Document document = new Document();
        try {
            // Le fichier sera créé sur le bureau de l'utilisateur
            String path = System.getProperty("user.home") + "/Desktop/L3GL/Java/TP/Examen_JAVAFX/factures/Facture_" + facture.getId() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(path));

            document.open();

            // --- Titre ---
            Font fontTitre = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Paragraph titre = new Paragraph("FACTURE CLINIQUE", fontTitre);
            titre.setAlignment(Element.ALIGN_CENTER);
            document.add(titre);
            document.add(new Paragraph(" ")); // Espace

            // --- Infos Facture ---
            document.add(new Paragraph("Numéro de Facture : " + facture.getId()));
            document.add(new Paragraph("Date : " + facture.getDateFacture()));
            document.add(new Paragraph("Mode de paiement : " + facture.getModePaiement()));
            document.add(new Paragraph("------------------------------------------------------------------"));

            // --- Infos Patient & Médecin ---
            // On récupère les infos via la consultation liée à la facture
            String nomPatient = facture.getConsultation().getRendezVous().getPatient().getNom();
            String prenomPatient = facture.getConsultation().getRendezVous().getPatient().getPrenom();
            String nomMedecin = facture.getConsultation().getRendezVous().getMedecin().getUsername();

            document.add(new Paragraph("Patient : " +prenomPatient+" "+ nomPatient));
            document.add(new Paragraph("Médecin consultant : Dr. " + nomMedecin));
            document.add(new Paragraph(" "));

            // --- Montant ---
            Font fontMontant = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Paragraph montant = new Paragraph("MONTANT TOTAL : " + facture.getMontantTotal() + " FCFA", fontMontant);
            montant.setAlignment(Element.ALIGN_RIGHT);
            document.add(montant);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Statut : " + facture.getStatut()));

            document.close();
            System.out.println("Facture générée avec succès sur le bureau !");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}