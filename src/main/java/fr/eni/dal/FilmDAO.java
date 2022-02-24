package fr.eni.dal;

import fr.eni.bo.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmDAO extends JpaRepository<Film, Long> {

    List<Film> findByGenreNomGenre(String nomGenre);

    @Query("select f from Film f INNER JOIN f.acteurs a  where f.titre LIKE ?1 OR a.nom LIKE ?1 OR  f.realisateur.nom LIKE ?1")
    List<Film> findKeyWord(String keywords);
}