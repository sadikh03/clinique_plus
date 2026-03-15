package sn.sadikh.examen_javafx.service;

import sn.sadikh.examen_javafx.model.Utilisateur;
import sn.sadikh.examen_javafx.model.enums.Role;
import sn.sadikh.examen_javafx.repository.IUtilisateurDAO;

import java.util.List;

public class UtilisateurService {
    private IUtilisateurDAO dao ;

    public UtilisateurService(IUtilisateurDAO dao) {
        this.dao = dao;
    }

    public void ajouter(Utilisateur u) {
        dao.save(u);
    }

    // READ (Tous)
    public List<Utilisateur> listerTout() {
        return dao.getAll();
    }

    // UPDATE
    public void modifier(Utilisateur u) {
        dao.update(u);
    }

    // DELETE
    public void supprimer(int id) {
        dao.delete(dao.findById(id));
    }

    public Utilisateur rechercherParLogin(String login){
        return dao.rechercherParLogin(login);
    }

    public List<Utilisateur> findByRole(Role role){
        return dao.findByRole(role);
    }
}
