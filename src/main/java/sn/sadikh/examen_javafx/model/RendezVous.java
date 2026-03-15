package sn.sadikh.examen_javafx.model;

import lombok.Data;
import sn.sadikh.examen_javafx.model.enums.StatutRDV;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime dateHeure;

    @Enumerated(EnumType.STRING)
    private StatutRDV statut;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Utilisateur medecin;

    @Override
    public String toString() {
        return "RDV : Medecin : "
                + this.medecin.getUsername()
                + " Patient : "
                + this.patient.getPrenom()
                + " "
                +this.patient.getNom()
                + " Du "
                + this.dateHeure;
    }
}

