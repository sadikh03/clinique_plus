package sn.sadikh.examen_javafx.repository;

import sn.sadikh.examen_javafx.model.RendezVous;
import sn.sadikh.examen_javafx.model.Utilisateur;

import java.util.List;

public interface IRendezVousDAO  extends  GenericDAO<RendezVous>{
    List<RendezVous> getAllByMedcin(Utilisateur med);
}
