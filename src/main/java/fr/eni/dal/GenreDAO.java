package fr.eni.dal;

import fr.eni.bo.Genre;
import fr.eni.bo.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDAO extends JpaRepository<Genre, Long> {
}
