package fr.eni.ihm;

import fr.eni.bll.IAuthenticationService;
import fr.eni.bll.IFilmService;
import fr.eni.bll.IGenreService;
import fr.eni.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("film")
public class FilmControllerWeb {


    @Autowired
    @Qualifier("final")
    IFilmService filmService;


    @Autowired
    @Qualifier("finalGenreService")
    IGenreService genreService;


    @GetMapping("/ajouter")
    public String ajouterFilm(Model model) {

        List<Genre> listeGenres = genreService.recupererTousLesGenres();
        if(!listeGenres.isEmpty()) {

            Film film = new Film();

            film.setAnnee(2000);
            film.setDuree(1);
            film.setActeurs(new ArrayList<>() {{
                add(new Acteur());
            }});
            model.addAttribute("filmForm", film);
            model.addAttribute("allGenres", listeGenres);
            return "ajoutFilm";

        }

        else{
            model.addAttribute("monErreur", "Veuillez d'abord créer au moins un genre pour créer un film.");
            return "erreur";
        }
    }

    @PostMapping("/ajouter")
    public String submissionResult(@ModelAttribute("filmForm") @Valid Film filmForm, BindingResult br, Model model) {


        if (br.hasErrors()) {
            System.out.println("Valid a échoué");
            model.addAttribute("filmForm", filmForm);
            model.addAttribute("valid", true);
            return "ajoutFilm";
        }

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
