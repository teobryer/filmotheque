package fr.eni.bo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Catalogue {
    private  int idCatalogue;
    private List<Film> listeFilms;
}
