package fr.eni.bo;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Acteur")
public class Acteur extends Personne{
    public Acteur(String nom, String prenom) {
        super(nom, prenom);
    }

    public Acteur() {
    }
}
