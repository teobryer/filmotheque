package fr.eni.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGenre;
    private String nomGenre;

    public Genre(String nomGenre) {
        this.nomGenre = nomGenre;
    }

    @Override
    public String toString() {
        return  nomGenre ;
    }
}
