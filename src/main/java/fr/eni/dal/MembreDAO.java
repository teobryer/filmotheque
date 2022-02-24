package fr.eni.dal;

import fr.eni.bo.Membre;
import fr.eni.bo.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MembreDAO extends JpaRepository<Membre, Long> {

    List<Membre> findByUsernameAndPassword(String username, String password);
}