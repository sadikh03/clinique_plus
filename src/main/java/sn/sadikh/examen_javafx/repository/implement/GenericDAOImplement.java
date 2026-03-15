package sn.sadikh.examen_javafx.repository.implement;

import sn.sadikh.examen_javafx.config.FactoryJPA;
import sn.sadikh.examen_javafx.repository.GenericDAO;

import javax.persistence.EntityManager;
import java.util.List;

public class GenericDAOImplement<T> implements GenericDAO<T> {
    private final Class<T> type ;

    public GenericDAOImplement(Class<T> type) {
        this.type = type;
    }

    @Override
    public void save(T entite) {
        EntityManager em = FactoryJPA.getManager();
        em.getTransaction().begin();
        em.persist(entite);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(T entite) {
        EntityManager em = FactoryJPA.getManager();
        em.getTransaction().begin();
        em.merge(entite);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<T> getAll() {
        EntityManager em = FactoryJPA.getManager();
        return em.createQuery("from "+type.getSimpleName(),type)
                .getResultList();
    }

    @Override
    public void delete(T entite) {
        EntityManager em = FactoryJPA.getManager();
        em.getTransaction().begin();
        em.remove(em.merge(entite));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public T findById(int id) {
        EntityManager em = FactoryJPA.getManager();
        em.getTransaction().begin();
        T entite = em.find(type,id);
        em.getTransaction().commit();
        em.close();
        return entite;
    }
}
