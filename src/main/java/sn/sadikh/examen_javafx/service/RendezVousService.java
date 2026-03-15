package sn.sadikh.examen_javafx.service;

import sn.sadikh.examen_javafx.model.RendezVous;
import sn.sadikh.examen_javafx.model.Utilisateur;
import sn.sadikh.examen_javafx.repository.IRendezVousDAO;
import sn.sadikh.examen_javafx.utilitaire.SessionManager;

import java.util.List;

public class RendezVousService {
    private IRendezVousDAO dao ;

    public RendezVousService(IRendezVousDAO dao) {
        this.dao = dao;
    }

    public void ajouter(RendezVous rv) {
        dao.save(rv);
    }

    // READ (Tous)
    public List<RendezVous> listerTout() {
        return dao.getAll();
    }

    // UPDATE
    public void modifier(RendezVous rv) {
        dao.update(rv);
    }

    public List<RendezVous> listerToutParMedecin(Utilisateur user){
        return dao.getAllByMedcin(user);
    }
}
