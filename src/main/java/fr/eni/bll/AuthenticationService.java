package fr.eni.bll;

import fr.eni.bo.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService implements IAuthenticationService{
    @Autowired
    @Qualifier("listeMembres")
    List<Membre> mesMembres;


    @Autowired
    @Qualifier("userActif")
    Membre membreConnected;


    @Override
    public Membre connexion(Membre membre) throws Exception {
        try{
Membre recup =   mesMembres.stream().filter(membre1 -> membre1.getPassword().equals(membre.getPassword()) && membre.getUsername().equals(membre1.getUsername())).findFirst().get();

            membreConnected.copy(recup); }
        catch (Exception e){
            throw new Exception("Aucun utilisateur correspondant.");
        }


    return membreConnected;

    }

    @Override
    public void deconnexion(Membre membre) {
      membreConnected.copy(new Membre());

    }

    @Override
    public Membre membreConnected() {
        return membreConnected;
    }
}
