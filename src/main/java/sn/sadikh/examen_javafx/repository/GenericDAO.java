package sn.sadikh.examen_javafx.repository;

import java.util.List;

public interface GenericDAO<T> {
    void save(T entite);
    void update(T entite);
    List<T> getAll();
    void delete(T entite);
    T findById(int id);
}
