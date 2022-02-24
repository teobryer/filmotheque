package fr.eni.bo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Personne")
@Getter
@Setter
public abstract  class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPersonne;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne() {

    }

    @Override
    public String toString() {
        return prenom +" " + nom;
    }
}
