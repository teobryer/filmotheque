package fr.eni.ihm;

import fr.eni.bll.IAuthenticationService;
import fr.eni.bll.IFilmService;
import fr.eni.bo.Avis;
import fr.eni.bo.Film;
import fr.eni.bo.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("film")
public class FilmControllerWeb {


    @Autowired
    IFilmService filmService;



    @GetMapping("/ajouter")
    public String ajouterFilm(Model model) {
        Film film = new Film();
        film.setAnnee(2000);
        film.setDuree(1);
        film.setActeurs(new ArrayList<>(){{add(new Personne());}});
        model.addAttribute("filmForm", film);
        return "ajoutFilm";
    }

    @PostMapping("/ajouter")
    public String submissionResult(@ModelAttribute("filmForm") Film filmForm, Model model) {


        try {
            filmService.ajouterUnFilm(filmForm);
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";

        }


        return "redirect:/film/liste";
    }

    @GetMapping("/supprimer/{idFilm}")
    public String supprimerFilm(@PathVariable("idFilm") String idFilm) {
        return "film";
    }

    @GetMapping("/modifier/{idFilm}")
    public String modifierFilm(@PathVariable("idFilm") String idFilm) {
        return "film";
    }

    @GetMapping("/liste")
    public String afficherFilms(Model model) {
        model.addAttribute("films", filmService.recupererTousLesFilms());
        return "listeFilms";
    }

    @GetMapping("/{idFilm}")
    public String detailsFilm(@PathVariable("idFilm") String idFilm, Model model) {
        try {
            model.addAttribute("film", filmService.recupererFilmParId(Integer.parseInt(idFilm)));
            return "film";
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";
        }


    }

    @GetMapping("/nouvelAvis/{idFilm}")
    public String ajouterAvisFilm(@PathVariable("idFilm") String idFilm, Model model) {
        Avis nouvelAvis = new Avis();
        model.addAttribute("avis",nouvelAvis);
        try {
            model.addAttribute("film", filmService.recupererFilmParId(Integer.parseInt(idFilm)));
            return "nouvelAvis";
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";
        }

    }

    @PostMapping("/nouvelAvis/{idFilm}")
    public String ajouterAvisPost(@ModelAttribute("avis") Avis avis, @ModelAttribute("film") Film  film, Model model) {



        try {
            Film monFilm = filmService.recupererFilmParId((film.getIdFilm()));
            filmService.ajouterUnAvis(monFilm, avis);
        } catch (Exception e) {

            model.addAttribute("monErreur", e.getMessage());
            return "erreur";
        }




        return "redirect:/film/"+film.getIdFilm();
    }
}
