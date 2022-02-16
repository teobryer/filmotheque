package fr.eni.bll;

import fr.eni.bo.Genre;

public interface IGenreService {
    void ajouterGenre(Genre newGenre);
    void modifierGenre(Genre editedGenre);
    void supprimerGenre(Genre ancienGenre);
}
