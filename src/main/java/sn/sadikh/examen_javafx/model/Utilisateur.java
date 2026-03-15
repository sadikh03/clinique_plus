package sn.sadikh.examen_javafx.model;

import lombok.Data;
import sn.sadikh.examen_javafx.model.enums.Role;

import javax.persistence.*;

@Data
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String login;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String toString() {
        return this.username;
    }
}

