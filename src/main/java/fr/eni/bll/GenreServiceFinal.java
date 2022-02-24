package fr.eni.bll;

import fr.eni.bo.Genre;
import fr.eni.dal.GenreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("finalGenreService")
public class GenreServiceFinal implements IGenreService{

    @Autowired
    GenreDAO genreDAO;

    @Override
    public void ajouterGenre(Genre newGenre) {
        newGenre.setIdGenre(0);
        genreDAO.save(newGenre);
    }

    @Override
    public void modifierGenre(Genre editedGenre) {
        genreDAO.save(editedGenre);
    }

    @Override
    public void supprimerGenre(Genre ancienGenre) {
        genreDAO.delete(ancienGenre);
    }

    @Override
    public List<Genre> recupererTousLesGenres() {
        return genreDAO.findAll();
    }
}
