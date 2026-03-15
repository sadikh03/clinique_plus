package sn.sadikh.examen_javafx.repository.implement;

import sn.sadikh.examen_javafx.config.FactoryJPA;
import sn.sadikh.examen_javafx.model.RendezVous;
import sn.sadikh.examen_javafx.model.Utilisateur;
import sn.sadikh.examen_javafx.repository.IRendezVousDAO;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAOImple extends GenericDAOImplement<RendezVous> implements IRendezVousDAO {
    public RendezVousDAOImple() {
        super(RendezVous.class);
    }

    @Override
    public List<RendezVous> getAllByMedcin(Utilisateur med) {
        EntityManager em = FactoryJPA.getManager();
        em.getTransaction().begin();
        try {
            return em.createQuery(
                    "SELECT r FROM RendezVous r WHERE r.medecin = :medecin ORDER BY r.dateRDV DESC", RendezVous.class)
                    .setParameter("medecin", med)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }finally {
            em.close();
        }
    }
}
