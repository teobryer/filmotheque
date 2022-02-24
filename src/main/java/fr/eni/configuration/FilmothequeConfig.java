package fr.eni.configuration;

import fr.eni.bo.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class FilmothequeConfig {


    Genre comedieDrama = new Genre("Comédie dramatique");
    Genre dramaFantas = new Genre("Drama-fantastique");


    @Bean(name = "catalogue")
    public Catalogue monCatalogueHanks() {
        Catalogue newCat = new Catalogue();
        newCat.setIdCatalogue(1);
        List<Film> maListeDeFilms = new ArrayList() {{
            add(new Film(1, "Forest Gump", 1994, 142, "Le film débute par une scène où une plume d'oiseau, volant dans les airs, atterrit aux pieds de Forrest Gump, un homme simple d'esprit. Assis sur un banc dans la ville de Savannah en Géorgie en 1981, Forrest attend l'autobus. Au fil des différents interlocuteurs qui viennent s’asseoir à tour de rôle à côté de lui, Forrest va leur raconter la fabuleuse et trépidante histoire de sa vie.", new Realisateur("Zemeckis", "Robert"), new ArrayList<>(){{ add(new Acteur("Hanks", "Tom"));}}, new ArrayList() {{ add(new Avis(5, "Très bon film"));}}, comedieDrama));
            add(new Film(2, "La ligne Verte", 1999, 188, "En 1996, Paul Edgecomb, un ancien gardien-chef d'un pénitencier dans les années 1930, entreprend d'écrire ses mémoires. Il revient sur l'affaire de John Coffey — ce grand Noir au regard absent, condamné à mort pour le viol et le meurtre de deux fillettes — qui défraya la chronique de 1935.", new Realisateur("Darabont", "Frank"), new ArrayList<>(){{ add(new Acteur("Hanks", "Tom"));}}, new ArrayList() {{ add(new Avis(2, "Un peu long"));}}, dramaFantas));

        }};


        newCat.setListeFilms(maListeDeFilms);

        return newCat;

    }

    @Bean(name = "genres")
    public List<Genre> mesGenres() {
        List<Genre> maListeGenres = Arrays.asList(dramaFantas, comedieDrama);


        return maListeGenres;

    }


    @Bean(name = "listeMembres")
    public List<Membre> utilisateurs() {


        Membre admin = new Membre(1, "admin", "admin@admin.adm", "admin" , true);
        Membre lambda = new Membre(2, "lambda", "lambda@lambda.lmbd", "lambda" , false);

        List<Membre> maListeMembres = new ArrayList() {{
         add(admin);
         add(lambda);
        }};




        return maListeMembres;

    }

    @SessionScope
    @Bean(name = "userActif")
    public Membre userActif() {


        return new Membre();

    }


}
