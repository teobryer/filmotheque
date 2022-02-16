package fr.eni.bll;

import fr.eni.bo.Membre;

public interface IAuthenticationService {
    Membre connexion(Membre membre) throws Exception;
    void deconnexion(Membre membre);
    Membre membreConnected();
}
