package sn.sadikh.examen_javafx.repository.implement;

import sn.sadikh.examen_javafx.model.Consultation;
import sn.sadikh.examen_javafx.repository.IConsultationDAO;

public class ConsultationDAOImple extends GenericDAOImplement<Consultation> implements IConsultationDAO {
    public ConsultationDAOImple() {
        super(Consultation.class);
    }
}
