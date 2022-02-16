package fr.eni.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Film
{
    private  int idFilm;
    private String titre;
    private int annee;
    private  int duree;
    private String synopnis;
    private Personne realisateur;
    private List<Personne> acteurs;
    private  List<Avis> avisList;
    private Genre genre;

    public Film(int idFilm, String titre, int annee, int duree, String synopnis, Personne realisateur, List<Personne> acteurs, List<Avis> avisList, Genre genre) {
        this.idFilm = idFilm;
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopnis = synopnis;
        this.realisateur = realisateur;
        this.acteurs = acteurs;
        this.avisList = avisList;
        this.genre = genre;

    }

    public Film() {

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
                " Synopsis : " + synopnis + '\n' +
                " Réalisateur : " + realisateur + '\n' +
                " Acteurs : " + acteursStr + '\n' +
                " Avis :" + avisStr + '\n' +
                " Genre : " + genre + '\n'+
                 "######################" + '\n'
                ;
    }
}
