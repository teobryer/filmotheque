package fr.eni.bo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Membre")
public class Membre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMembre;
    private String username;
    private String mail;
    private String password;
    private boolean isAdmin;

    public Membre(int idMembre, String username, String mail, String password, boolean isAdmin) {
        this.idMembre = idMembre;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Membre(String username, String password){
        this.username = username;
        this.password = password;
    }


    public void copy(Membre membre){
        this.idMembre = membre.idMembre;
        this.username = membre.username;
        this.mail = membre.mail;
        this.password = membre.password;
        this.isAdmin = membre.isAdmin;
    }

    public Membre() {

    }

    @Override
    public String toString() {
        return "Membre{" +
                "username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
