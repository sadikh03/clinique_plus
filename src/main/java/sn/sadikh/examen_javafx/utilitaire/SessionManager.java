package sn.sadikh.examen_javafx.utilitaire;

import sn.sadikh.examen_javafx.model.Utilisateur;

public class SessionManager {
    // Utilisation du pattern Singleton ou static pour la session
    private static Utilisateur instance;

    public static void ouvrirSession(Utilisateur utilisateur) {
        instance = utilisateur;
    }

    public static void fermerSession() {
        instance = null;
    }

    public static Utilisateur getUtilisateur() {
        return instance;
    }

    public static boolean hasRole(String roleName) {
        return instance != null && instance.getRole().name().equalsIgnoreCase(roleName);
    }
}
