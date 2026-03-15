package sn.sadikh.examen_javafx.repository.implement;

import sn.sadikh.examen_javafx.model.Facture;
import sn.sadikh.examen_javafx.repository.IFactureDAO;

public class FactureDAOImple extends GenericDAOImplement<Facture> implements IFactureDAO {
    public FactureDAOImple() {
        super(Facture.class);
    }
}
