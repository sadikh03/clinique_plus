package sn.sadikh.examen_javafx.validator;

import sn.sadikh.examen_javafx.model.Patient;
import sn.sadikh.examen_javafx.model.RendezVous;
import sn.sadikh.examen_javafx.model.Utilisateur;

import java.time.LocalDateTime;

public class RendezVousValidator {

    public static String valider(LocalDateTime dateHeure , Patient p , Utilisateur u) {
        StringBuilder erreurs = new StringBuilder();

        if (dateHeure == null) {
            erreurs.append("- La date et l'heure sont obligatoires.\n");
        } else if (dateHeure.isBefore(java.time.LocalDateTime.now())) {
            // Optionnel mais pro : vérifier que ce n'est pas dans le passé
            erreurs.append("- La date du rendez-vous ne peut pas être dans le passé.\n");
        }

        // Validation des associations (Foreign Keys)
        if (p == null) {
            erreurs.append("- Vous devez sélectionner un patient.\n");
        }

        if (u == null) {
            erreurs.append("- Vous devez affecter un médecin.\n");
        }

        return erreurs.toString();
    }
}