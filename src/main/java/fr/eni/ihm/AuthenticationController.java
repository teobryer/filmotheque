package fr.eni.ihm;

import fr.eni.bll.IAuthenticationService;
import fr.eni.bll.IFilmService;
import fr.eni.bo.Film;
import fr.eni.bo.Membre;
import fr.eni.bo.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class AuthenticationController implements IAuthenticationController{




    @Autowired
    @Qualifier("finalAuthService")
    IAuthenticationService authenticationService;


    Membre monMembre;
    Scanner scanner = new Scanner(System.in);
    @Override
    public void connexion() {

        System.out.println("### CONNEXION ###");


        System.out.println("   Votre identifiant ? ");
        String username = scanner.nextLine();
        System.out.println("   Votre mot de passe  ? ");
        String password = scanner.nextLine();

        monMembre= new Membre(username, password);

        try{
            monMembre=  authenticationService.connexion(monMembre);
            System.out.println("Vous êtes bien connecté ! Voici vos informations : " + monMembre.toString());

        }

        catch (Exception e){
            System.out.println("Erreur ! " +e.getMessage()+" Veuillez recommencer.");
            connexion();
        }


        System.out.println("##############################");

    }

    @Override
    public void deconnexion() {

        System.out.println("### DECONNEXION ###");

        authenticationService.deconnexion(monMembre);
        System.out.println("##############################");

    }

    @Override
    public void monProfil() {
        System.out.println("### MON PROFIL ###");



        if( authenticationService.membreConnected()!= null){
            System.out.println("Voici vos informations : " +  authenticationService.membreConnected().toString());

            System.out.println("##############################");
        }
        else{
            System.out.println("Vous n'êtes pas connecté ! ");

            System.out.println("##############################");

            System.out.println("Voulez-vous vous connecter ? O (Si vous vous voulez en indiquer)/ N (Sinon)");
            String choix = scanner.nextLine();

            if( choix.equalsIgnoreCase("o")|| choix.equalsIgnoreCase("oui")){
                connexion();
            }
        }


    }
}
