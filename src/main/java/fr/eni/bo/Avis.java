package fr.eni.bo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Avis")
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAvis;
    private  int note;
    private String avis;
    @ManyToOne()
    @JoinColumn (name="id_membre")
    private Membre membre; // AJOUTER UNE RELATIONNN !!!!

    public Avis(int note, String avis) {
        this.note = note;
        this.avis = avis;
    }
    public Avis(int note, String avis, Membre membre) {
        this.note = note;
        this.avis = avis;
        this.membre = membre;
    }
    public Avis(){

    }

}
