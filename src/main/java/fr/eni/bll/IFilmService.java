package fr.eni.bll;

import fr.eni.bo.Avis;
import fr.eni.bo.Film;
import fr.eni.bo.Genre;

import java.util.List;

public interface IFilmService {

    void ajouterUnFilm(Film nouveauFilm) throws Exception;
    void supprimerUnFilm(Film ancienFilm) throws Exception;
    List<Film> recupererTousLesFilms();
    List<Film> recupererFilmParGenre(Genre genre);
    List<Film> chercherParmiLesFilms(String keyword);
    void modifierUnFilm(Film filmModifie);
    Film recupererFilmParId(long idFilm) throws Exception;
    void ajouterUnAvis(Film film, Avis avis) throws Exception;
}
