package fr.eni.bll;

import fr.eni.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


public class FilmServiceBasique implements IFilmService{

    @Autowired
    Catalogue monCatalogue;



    @Override
    public void ajouterUnFilm(Film nouveauFilm) {

        nouveauFilm.setIdFilm(monCatalogue.getListeFilms().size()+1);
        monCatalogue.getListeFilms().add(nouveauFilm);

        System.out.println("Nouveau Film ajouté avec succès. Vous retrouverez désormais " + nouveauFilm.getTitre() + " parmi votre catalogue ! ");
    }

    @Override
    public void supprimerUnFilm(Film ancienFilm) {
        monCatalogue.getListeFilms().remove(ancienFilm);
        System.out.println("Le film "+ ancienFilm.getTitre()+ " a été supprimé avec succès.");
    }

    @Override
    public List<Film> recupererTousLesFilms() {

        return monCatalogue.getListeFilms();
    }

    @Override
    public List<Film> recupererFilmParGenre(Genre genre) {
        return monCatalogue.getListeFilms().stream().filter(film -> film.getGenre() == genre).collect(Collectors.toList());
    }

    @Override
    public List<Film> chercherParmiLesFilms(String keyword) {
        return monCatalogue.getListeFilms().stream().filter(film -> film.getTitre().contains(keyword)).collect(Collectors.toList());
    }

    @Override
    public void modifierUnFilm(Film filmModifie) {
        System.out.println("Le film "+ filmModifie.getTitre()+" a été modifié avec succès. Vous retrouverez désormais les nouvelles informations sur ce dernier." );
    }

    @Override
    public Film recupererFilmParId(int idFilm) throws Exception {
        try{
            return monCatalogue.getListeFilms().stream().filter(film -> film.getIdFilm()== idFilm).findFirst().get();
        }
        catch (Exception e){
            throw new Exception("Aucun film ne correspond.");
        }

    }

    @Override
    public void ajouterUnAvis(Film film, Avis avis) {
        film.getAvisList().add(avis);
    }
}
