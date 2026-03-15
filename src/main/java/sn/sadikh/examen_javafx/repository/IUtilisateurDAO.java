package sn.sadikh.examen_javafx.repository;

import sn.sadikh.examen_javafx.model.Utilisateur;
import sn.sadikh.examen_javafx.model.enums.Role;

import java.util.List;

public interface IUtilisateurDAO extends GenericDAO<Utilisateur>{
    Utilisateur rechercherParLogin(String login);
    List<Utilisateur> findByRole(Role role);
}
