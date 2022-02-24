package fr.eni.ihm;

import fr.eni.bll.IAuthenticationService;
import fr.eni.bll.IFilmService;
import fr.eni.bo.Film;
import fr.eni.bo.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicationControllerWeb {

    @Autowired
    @Qualifier("finalAuthService")
    IAuthenticationService authenticationService;

    @Autowired
    @Qualifier("final")
    IFilmService filmService;


    @GetMapping({"/accueil", "/"})
    public String index(Model model) {

        model.addAttribute("films", filmService.recupererTousLesFilms());
        return "listeFilms";
    }


    @GetMapping({"/connexion"})
    public String connexion(Model model) {

        Membre membre = new Membre();

        model.addAttribute("membre", membre);




        return "connexion";
    }

    @GetMapping({"/deconnexion"})
    public String deconnexion() {

       authenticationService.deconnexion(null);






        return "redirect:/";
    }

    @PostMapping({"/connexion"})
    public String connexionPost(@ModelAttribute("membre") Membre membre, Model model) {

        try{
            authenticationService.connexion(membre);

            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("monErreur", e.getMessage());
            return "erreur";
        }



    }

}
