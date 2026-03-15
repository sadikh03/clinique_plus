package sn.sadikh.examen_javafx.repository.implement;

import sn.sadikh.examen_javafx.config.FactoryJPA;
import sn.sadikh.examen_javafx.model.Utilisateur;
import sn.sadikh.examen_javafx.model.enums.Role;
import sn.sadikh.examen_javafx.repository.IUtilisateurDAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class UtilisateurDAOImple extends GenericDAOImplement<Utilisateur> implements IUtilisateurDAO {
    public UtilisateurDAOImple() {
        super(Utilisateur.class);
    }
    @Override
    public Utilisateur rechercherParLogin(String login){
        EntityManager em = FactoryJPA.getManager();
        try {
            // "from Utilisateur u where u.login = :login"
            return em.createQuery("from " + Utilisateur.class.getSimpleName() + " u where u.login = :loginParam", Utilisateur.class)
                    .setParameter("loginParam", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Si aucun utilisateur n'est trouvé, on retourne null
            return null;
        } finally {
            em.close();
        }
    }
    @Override
    public List<Utilisateur> findByRole(Role role) {
        EntityManager em = FactoryJPA.getManager();
        try {
            return em.createQuery("SELECT u FROM Utilisateur u WHERE u.role = :role", Utilisateur.class)
                    .setParameter("role", role)
                    .getResultList();
        } catch (NoResultException e) {
            // Si aucun utilisateur n'est trouvé, on retourne null
            return null;
        } finally {
            em.close();
        }
    }
}
