package fr.eni.ihm;

import fr.eni.bll.IAuthenticationService;
import fr.eni.bll.IFilmService;
import fr.eni.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class FilmController implements IFilmController {

    @Autowired
    @Qualifier("final")
    IFilmService filmService;


    @Autowired
    IAuthenticationService authenticationService;

    Scanner scanner = new Scanner(System.in);
    Film monFilm = new Film();

    Avis monAvis = new Avis();

    @Override
    public void ajouterFilm() {

        System.out.println("### Ajout d'un nouveau film ###");


        if (authenticationService.membreConnected() != null) {
            monFilm.setActeurs(new ArrayList<Acteur>());
            monFilm.setAvisList(new ArrayList<Avis>());


            Ajout_Etape1();


            try {
                filmService.ajouterUnFilm(monFilm);
            } catch (Exception e) {

            }

        }


        System.out.println("Vous devez être connecté pour ajouter un nouveau film");


        System.out.println("###############################");
    }


    private void Ajout_Etape1() {
        System.out.println("Étape 1 ~ Veuillez entrer le titre du film : ");

        String titre = scanner.nextLine();

        if (titre != null && !titre.equals("")) {
            monFilm.setTitre(titre);
            Ajout_Etape2();
        } else {
            System.out.println("Erreur ! Veuillez recommencer.");
            Ajout_Etape1();
        }
    }

    private void Ajout_Etape2() {
        System.out.println("Étape 2 ~ Veuillez entrer l'année de sortie du film : ");

        int annee = scanner.nextInt();

        if (annee > 1600 && annee <= LocalDateTime.now().getYear()) {
            monFilm.setAnnee(annee);
            Ajout_Etape3();
        } else {
            System.out.println("Erreur ! Veuillez recommencer.");
            Ajout_Etape2();
        }
    }

    private void Ajout_Etape3() {
        System.out.println("Étape 3 ~ Veuillez entrer la durée en minutes du film : ");

        int duree = scanner.nextInt();
        if (duree > 0) {
            monFilm.setDuree(duree);
            scanner.nextLine();
            Ajout_Etape4();
        } else {
            System.out.println("Erreur ! Veuillez recommencer.");
            Ajout_Etape3();
        }

    }

    private void Ajout_Etape4() {
        System.out.println("Étape 4 ~ Veuillez entrer le synopsis du film : ");


        String synopsis = scanner.nextLine();


        if (synopsis.length() <= 250 && synopsis.length() >= 20) {
            monFilm.setSynopsis(synopsis);
            Ajout_Etape5();
        } else {
            System.out.println("Erreur ! Veuillez recommencer.");
            Ajout_Etape4();
        }

    }

    private void Ajout_Etape5() {
        System.out.println("Étape 5 ~ Veuillez indiquer le réalisateur du film : ");
        System.out.println("   Son nom ? ");
        String nomRealisateur = scanner.nextLine();
        System.out.println("   Son prenom ? ");
        String prenomRealisateur = scanner.nextLine();

        if (nomRealisateur != null && !nomRealisateur.equals("") & prenomRealisateur != null && !prenomRealisateur.equals("")) {
            monFilm.setRealisateur(new Realisateur(nomRealisateur, prenomRealisateur));
            Ajout_Etape6();
        } else {
            System.out.println("Erreur ! Veuillez recommencer.");
            Ajout_Etape5();
        }
    }


    private void Ajout_Etape6() {
        System.out.println("Étape 6 ~ Veuillez indiquer les acteurs du film :");

        System.out.println("O (Si vous vous voulez en indiquer)/ N (Sinon)");
        String choixActeurs = scanner.nextLine();

        if (choixActeurs.equalsIgnoreCase("o") || choixActeurs.equalsIgnoreCase("oui")) {
            Ajout_Acteur();
        }
    }

    private void Ajout_Acteur() {
        System.out.println("   Son nom ? ");
        String nomActeur = scanner.nextLine();
        System.out.println("   Son prenom ? ");
        String prenomActeur = scanner.nextLine();

        if (nomActeur != null && !nomActeur.equals("") && prenomActeur != null && !prenomActeur.equals("")) {
            monFilm.getActeurs().add(new Acteur(nomActeur, prenomActeur));
            System.out.println("Voulez vous ajouter un autre acteur ?");
            System.out.println("O (Si vous vous voulez en indiquer)/ N (Sinon)");
            String choixActeurs = scanner.nextLine();

            if (choixActeurs.equalsIgnoreCase("o") || choixActeurs.equalsIgnoreCase("oui")) {
                Ajout_Acteur();
            } else {
                Ajout_EtapeFin();
            }
        } else {
            System.out.println("Erreur ! Veuillez recommencer.");
            Ajout_Acteur();
        }
    }

    private void Ajout_EtapeFin() {
        System.out.println("Zebi");
    }


    @Override
    public void supprimerFilm() {

    }

    @Override
    public void modifierFilm() {

    }

    @Override
    public void afficherFilms() {

        System.out.println("### AFFICHAGE DE TOUS LES FILMS ###");
        for (Film f : filmService.recupererTousLesFilms()) {

            System.out.println(f.toString());

        }

        System.out.println("##############################");

    }

    @Override
    public void detailsFilm() {
        System.out.println("### CONSULATION D'UN FILM ###");

        System.out.println("Liste de films disponibles : ");

        for (Film f : filmService.recupererTousLesFilms()) {

            System.out.println(f.getIdFilm() + " - " + f.getTitre());

        }

        demandeConsultationFilm();


        System.out.println("##############################");
    }

    @Override
    public void ajouterAvisFilm() {
        System.out.println("### AJOUT D'UN AVIS SUR UN FILM ###");

        if (authenticationService.membreConnected() != null) {

            System.out.println("Liste de films disponibles : ");

            for (Film f : filmService.recupererTousLesFilms()) {

                System.out.println(f.getIdFilm() + " - " + f.getTitre());

            }

            demandeAvisFilm();

        } else {
            System.out.println("Vous devez être connecté pour donner un avis sur un film");
        }


        System.out.println("##############################");
    }


    private void demandeConsultationFilm() {
        System.out.println("Quel film voulez-vous consulter ? : ");

        String id = scanner.nextLine();


        try {
            int idInt = Integer.parseInt(id);
            Film film = filmService.recupererFilmParId(idInt);

            System.out.println(film.toString());
        } catch (Exception e) {
            System.out.println("Erreur ! " + e.getMessage() + " Veuillez recommencer.");
            demandeConsultationFilm();
        }

    }

    private void demandeAvisFilm() {
        System.out.println("Quel film voulez-vous noter ? : ");

        String id = scanner.nextLine();


        try {
            int idInt = Integer.parseInt(id);
            Film film = filmService.recupererFilmParId(idInt);
            Etape_Avis(film);

        } catch (Exception e) {
            System.out.println("Erreur ! " + e.getMessage() + " Veuillez recommencer.");
            demandeAvisFilm();
        }

    }

    private void Etape_Avis(Film f) {

        System.out.println("   Votre note sur 5  ? ");
        String note = scanner.nextLine();

        try {
            int noteInt = Integer.parseInt(note);
            if (noteInt > 5 || noteInt < 0) {
                throw new Exception("La note doit être comprise entre 0 et 5");
            } else {
                monAvis.setNote(noteInt);
            }

            System.out.println("   Votre avis ? ");
            String avis = scanner.nextLine();

            monAvis.setAvis(avis);


            filmService.ajouterUnAvis(f, monAvis);

        } catch (Exception e) {
            System.out.println("Erreur ! " + e.getMessage() + " Veuillez recommencer.");
            Etape_Avis(f);
        }

    }
}
