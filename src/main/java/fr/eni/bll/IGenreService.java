package fr.eni.bll;

import fr.eni.bo.Film;
import fr.eni.bo.Genre;

import java.util.List;

public interface IGenreService {
    void ajouterGenre(Genre newGenre);
    void modifierGenre(Genre editedGenre);
    void supprimerGenre(Genre ancienGenre);
    List<Genre> recupererTousLesGenres();
}
