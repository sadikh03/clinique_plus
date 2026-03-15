package sn.sadikh.examen_javafx.service;

import sn.sadikh.examen_javafx.model.Patient;
import sn.sadikh.examen_javafx.repository.IPatientDAO;

import java.util.List;

public class PatientService {
    private IPatientDAO dao ;

    public PatientService(IPatientDAO dao) {
        this.dao = dao;
    }

    public void ajouter(Patient p) {
        dao.save(p);
    }

    // READ (Tous)
    public List<Patient> listerTout() {
        return dao.getAll();
    }

    // UPDATE
    public void modifier(Patient p) {
        dao.update(p);
    }

    // DELETE
    public void supprimer(int id) {
        dao.delete(dao.findById(id));
    }
}
