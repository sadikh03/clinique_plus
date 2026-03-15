package sn.sadikh.examen_javafx.validator;

import sn.sadikh.examen_javafx.model.Consultation;
import sn.sadikh.examen_javafx.model.RendezVous;

public class ConsultationValidator {

    public static String valider(String diagnostic , String prescription, RendezVous rdv) {
        StringBuilder erreurs = new StringBuilder();

        if (diagnostic == null || diagnostic.trim().isEmpty()) {
            erreurs.append("- Le diagnostic est obligatoire.\n");
        }

        // Validation de la Prescription
        if (prescription == null || prescription.trim().isEmpty()) {
            erreurs.append("- La prescription ne peut pas être vide.\n");
        }

        // Sécurité : Une consultation doit être liée à un Rendez-vous existant
        if (rdv == null) {
            erreurs.append("- Erreur système : Aucun rendez-vous lié à cette consultation.\n");
        }

        return erreurs.toString();
    }
}
