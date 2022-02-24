package fr.eni.bll;

import fr.eni.bo.Membre;
import fr.eni.dal.MembreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("finalAuthService")
public class AuthenticationServiceFinal implements IAuthenticationService{
    @Autowired
    MembreDAO membreDAO;


    @Autowired
    @Qualifier("userActif")
    Membre membreConnected;


    @Override
    public Membre connexion(Membre membre) throws Exception {
        try{


Membre recup =   membreDAO.findByUsernameAndPassword(membre.getUsername(), membre.getPassword()).stream().findFirst().get();
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
