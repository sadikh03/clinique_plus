package sn.sadikh.examen_javafx.service;

import sn.sadikh.examen_javafx.model.Consultation;
import sn.sadikh.examen_javafx.repository.IConsultationDAO;

import java.util.List;

public class ConsultationService {
    private IConsultationDAO dao ;

    public ConsultationService(IConsultationDAO dao) {
        this.dao = dao;
    }

    public void ajouter(Consultation c) {
        dao.save(c);
    }

    // READ (Tous)
    public List<Consultation> listerTout() {
        return dao.getAll();
    }

    // UPDATE
    public void modifier(Consultation c) {
        dao.update(c);
    }

}
