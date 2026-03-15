package sn.sadikh.examen_javafx.validator;

public class FactureValidator {
    public static String valider(String montantStr, String modePaiement) {
        StringBuilder erreurs = new StringBuilder();


        if (montantStr == null || montantStr.trim().isEmpty()) {
            erreurs.append("- Le montant est obligatoire.\n");
        } else {
            try {
                double m = Double.parseDouble(montantStr.trim());
                if (m <= 0) erreurs.append("- Le montant doit être supérieur à 0.\n");
            } catch (NumberFormatException e) {
                erreurs.append("- Le format du montant est invalide (chiffres uniquement).\n");
            }
        }

        // Vérification du mode de paiement
        if (modePaiement == null || modePaiement.isEmpty()) {
            erreurs.append("- Veuillez sélectionner un mode de paiement.\n");
        }

        return erreurs.toString();
    }
}
