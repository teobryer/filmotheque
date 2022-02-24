package fr.eni.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Film")
@Getter
@Setter
public class Film
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFilm;

    private String titre;
    private int annee;
    private  int duree;
    private String synopsis;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn (name="id_realisateur")
    private Realisateur realisateur;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Acteur> acteurs;

    @OneToMany(cascade = {CascadeType.ALL})
    private  List<Avis> avisList;

    @ManyToOne
    private Genre genre;

    public Film(long idFilm, String titre, int annee, int duree, String synopsis, Realisateur realisateur, List<Acteur> acteurs, List<Avis> avisList, Genre genre) {
        this.idFilm = idFilm;
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
        this.realisateur = realisateur;
        this.acteurs = acteurs;
        this.avisList = avisList;
        this.genre = genre;

    }

    public Film() {
this.realisateur = new Realisateur();
    }

    @Override
    public String toString() {

        String acteursStr="\n";

        for (Personne a: acteurs) {
          acteursStr+= "     ☺ "+a + "\n";
        }

        String avisStr="\n";

        for (Avis unAvis: avisList) {

           avisStr+= "     "+"★".repeat(unAvis.getNote()) + " '" + unAvis.getAvis()  + "'\n"; ;


        }

        return
                "### " + titre +  " ###" + '\n' +
                " Année de sortie : " + annee +  '\n' +
                " Durée(mns) : " + duree +  '\n' +
                " Synopsis : " + synopsis + '\n' +
                " Réalisateur : " + realisateur + '\n' +
                " Acteurs : " + acteursStr + '\n' +
                " Avis :" + avisStr + '\n' +
                " Genre : " + genre + '\n'+
                 "######################" + '\n'
                ;
    }
}
