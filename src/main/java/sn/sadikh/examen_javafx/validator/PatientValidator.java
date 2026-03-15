package sn.sadikh.examen_javafx.validator;

import sn.sadikh.examen_javafx.model.Patient;

public class PatientValidator {

    public static String valider(String nom , String prenom , String tel ,String sexe) {
        StringBuilder erreurs = new StringBuilder();

        if (nom == null || nom.trim().isEmpty()) {
            erreurs.append("- Le nom est obligatoire.\n");
        }

        // Validation du Prénom
        if (prenom == null || prenom.trim().isEmpty()) {
            erreurs.append("- Le prénom est obligatoire.\n");
        }

        // Validation du Téléphone (on laisse passer le format, juste présence)
        if (tel == null || tel.trim().isEmpty()) {
            erreurs.append("- Le numéro de téléphone est obligatoire.\n");
        }

        // Validation du Sexe
        if (sexe == null || sexe.isEmpty()) {
            erreurs.append("- Le sexe doit être renseigné.\n");
        }

        return erreurs.toString();
    }
}
