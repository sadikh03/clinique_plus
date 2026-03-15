package sn.sadikh.examen_javafx.model;

import lombok.Data;
import sn.sadikh.examen_javafx.model.enums.StatutPaiement;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float montantTotal;
    private LocalDate dateFacture;
    private String modePaiement;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut;

    @OneToOne
    private Consultation consultation;
}
