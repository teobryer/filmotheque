package fr.eni.bo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Personne {
    private int idPersonne;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return prenom +" " + nom;
    }
}
