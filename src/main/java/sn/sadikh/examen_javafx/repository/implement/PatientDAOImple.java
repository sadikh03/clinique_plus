package sn.sadikh.examen_javafx.repository.implement;

import sn.sadikh.examen_javafx.model.Patient;
import sn.sadikh.examen_javafx.repository.IPatientDAO;

public class PatientDAOImple extends GenericDAOImplement<Patient> implements IPatientDAO {
    public PatientDAOImple() {
        super(Patient.class);
    }
}
