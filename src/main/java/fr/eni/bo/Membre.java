package fr.eni.bo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Membre {
    private int idMembre;
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

    @Override
    public String toString() {
        return "Membre{" +
                "username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
