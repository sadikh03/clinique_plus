package sn.sadikh.examen_javafx.service;

import sn.sadikh.examen_javafx.model.Facture;
import sn.sadikh.examen_javafx.repository.IFactureDAO;

import java.util.List;

public class FactureService {
    private IFactureDAO dao ;

    public FactureService(IFactureDAO dao) {
        this.dao = dao;
    }

    public void ajouter(Facture f) {
        dao.save(f);
    }

    // READ (Tous)
    public List<Facture> listerTout() {
        return dao.getAll();
    }

    // UPDATE
    public void modifier(Facture f) {
        dao.update(f);
    }
}
