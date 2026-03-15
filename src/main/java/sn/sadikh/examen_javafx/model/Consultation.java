package sn.sadikh.examen_javafx.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String diagnostic;
    @Column(columnDefinition = "TEXT")
    private String observations;
    @Column(columnDefinition = "TEXT")
    private String prescription;

    @OneToOne
    private RendezVous rendezVous;

}