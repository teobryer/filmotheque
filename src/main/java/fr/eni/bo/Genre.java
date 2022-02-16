package fr.eni.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genre {
    private int idGenre;
    private String nomGenre;

    public Genre(String nomGenre) {
        this.nomGenre = nomGenre;
    }

    @Override
    public String toString() {
        return  nomGenre ;
    }
}
