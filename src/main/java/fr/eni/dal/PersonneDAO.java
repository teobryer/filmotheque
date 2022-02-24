package fr.eni.dal;

import fr.eni.bo.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonneDAO<T extends Personne> extends JpaRepository< T, Long> {

    @Query("select u from #{#entityName} u ")
    List<T> findAllPerso();
}