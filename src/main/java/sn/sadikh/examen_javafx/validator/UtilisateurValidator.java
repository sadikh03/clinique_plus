package sn.sadikh.examen_javafx.validator;

import sn.sadikh.examen_javafx.model.Utilisateur;
import sn.sadikh.examen_javafx.model.enums.Role;

public class UtilisateurValidator {

    public static String valider(String username , String login , String pass , Role role) {
        StringBuilder erreurs = new StringBuilder();

        if (username == null || username.trim().isEmpty()) {
            erreurs.append("- Le nom d'utilisateur est obligatoire.\n");
        }

        // 2. Validation du Login (Identifiant de connexion)
        if (login == null || login.trim().isEmpty()) {
            erreurs.append("- Le login de connexion est obligatoire.\n");
        } else if (login.trim().length() < 4) {
            erreurs.append("- Le login doit contenir au moins 4 caractères.\n");
        }

        // 3. Validation du Password
        if (pass == null || pass.isEmpty()) {
            erreurs.append("- Le mot de passe est obligatoire.\n");
        } else if (pass.length() < 6) {
            erreurs.append("- Le mot de passe doit contenir au moins 6 caractères pour la sécurité.\n");
        }

        // 4. Validation du Rôle (Admin, Medecin, etc.)
        if (role == null) {
            erreurs.append("- Veuillez attribuer un rôle à cet utilisateur.\n");
        }

        return erreurs.toString();
    }
}
