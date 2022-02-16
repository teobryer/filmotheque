package fr.eni.bo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Avis {
    private int idAvis;
    private  int note;
    private String avis;
    private Membre membre;

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
