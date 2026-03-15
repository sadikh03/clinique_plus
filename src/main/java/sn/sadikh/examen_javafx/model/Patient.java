package sn.sadikh.examen_javafx.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String sexe;
    private String telephone;
    private String adresse;
    private String groupeSanguin;
    private String antecedentsMedicaux;

    @Override
    public String toString() {
        return this.prenom + " " + this.nom.toUpperCase();
    }
}
