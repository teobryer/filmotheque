package fr.eni.bo;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Realisateur")
public class Realisateur extends Personne{

    public Realisateur(String nom, String prenom) {
        super(nom, prenom);
    }

    public Realisateur() {
    }
}
