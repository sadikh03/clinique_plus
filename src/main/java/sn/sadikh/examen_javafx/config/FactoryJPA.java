package sn.sadikh.examen_javafx.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryJPA {
    private static EntityManagerFactory emf;
    private FactoryJPA(){}
    public static EntityManagerFactory getEmf(){
        if(emf==null) emf = Persistence.createEntityManagerFactory("mall");
        return emf;
    }
    public static EntityManager getManager(){
        return getEmf().createEntityManager();
    }
}
