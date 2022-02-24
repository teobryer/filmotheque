package fr.eni.bo;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Realisateur")
@Getter
@Setter
public class Realisateur extends Personne{

    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;

    public Realisateur(String nom, String prenom) {
        super(nom, prenom);
    }

    public Realisateur() {
    }
}
