package fr.eni.bll;

import fr.eni.bo.Avis;
import fr.eni.bo.Catalogue;
import fr.eni.bo.Film;
import fr.eni.bo.Genre;
import fr.eni.dal.FilmDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("final")
public class FilmServiceFinal implements IFilmService{

    @Autowired
    FilmDAO filmDAO;


    @Autowired
    IAuthenticationService authenticationService;


    @Override
    public void ajouterUnFilm(Film nouveauFilm) throws Exception {


        if(authenticationService.membreConnected().isAdmin()){


        boolean error = false;
        String erreurs =  "Erreur dans les champs :";
       if(nouveauFilm.getTitre() == null || nouveauFilm.getTitre().equals("")){
           erreurs+=" Titre,";
           error = true;
       }

        if(nouveauFilm.getAnnee() < 1600 ){
            erreurs+=" Année,";
            error = true;
        }

        if(nouveauFilm.getDuree() < 0 ){
            erreurs+=" Durée,";
            error = true;
        }

        if(nouveauFilm.getSynopsis().length() < 25 ){
            erreurs+=" Synopsis,";
            error = true;
        }

        if(nouveauFilm.getRealisateur() == null || nouveauFilm.getRealisateur().getPrenom().equals("") || nouveauFilm.getRealisateur().getNom().equals("") || nouveauFilm.getRealisateur().getPrenom() == null || nouveauFilm.getRealisateur().getNom()== null   ){
            erreurs+=" Réalisateur,";
            error = true;
        }

        if(error){
            erreurs= erreurs.substring(0, erreurs.length() - 1);
            erreurs+=".";
            throw new Exception(erreurs);
        }
        else{
            nouveauFilm.setIdFilm(0);
            filmDAO.save(nouveauFilm);

            System.out.println("Nouveau Film ajouté avec succès. Vous retrouverez désormais " + nouveauFilm.getTitre() + " parmi votre catalogue ! ");
        }

        }

        else{
            throw new Exception("Vous n'êtes pas autorisé à ajouter un film.");
        }


    }

    @Override
    public void supprimerUnFilm(Film ancienFilm) throws Exception {


        if(authenticationService.membreConnected().isAdmin()) {

        filmDAO.delete(ancienFilm);
        }

        else{
            throw new Exception("Vous n'êtes pas autorisé à supprimer un film.");
        }
        System.out.println("Le film "+ ancienFilm.getTitre()+ " a été supprimé avec succès.");
    }

    @Override
    public List<Film> recupererTousLesFilms() {

        return filmDAO.findAll();
    }

    @Override
    public List<Film> recupererFilmParGenre(Genre genre) {
        return filmDAO.findByGenreNomGenre(genre.getNomGenre());
    }

    @Override
    public List<Film> chercherParmiLesFilms(String keyword) {
         return filmDAO.findByGenreNomGenre("%"+keyword+"%");
    }

    @Override
    public void modifierUnFilm(Film filmModifie) {
        filmDAO.save(filmModifie);
        System.out.println("Le film "+ filmModifie.getTitre()+" a été modifié avec succès. Vous retrouverez désormais les nouvelles informations sur ce dernier." );
    }

    @Override
    public Film recupererFilmParId(long idFilm) throws Exception {
        try{
            return filmDAO.findById(idFilm).get();
        }
        catch (Exception e){
            throw new Exception("Aucun film ne correspond.");
        }

    }

    @Override
    public void ajouterUnAvis(Film film, Avis avis) throws Exception {

        boolean error = false;
        String erreurs =  "Erreur dans les champs :";
        if(avis.getNote() > 5 || avis.getNote() < 0 ){
            erreurs+=" Note,";
            error = true;
        }

        if(avis.getAvis().length() <= 0 ){
            erreurs+=" Avis,";
            error = true;
        }


        if(!error){
            film.getAvisList().add(avis);
            filmDAO.save(film);
            System.out.println("Ajout d'un nouvel avis");
        }

        else{
            erreurs= erreurs.substring(0, erreurs.length() - 1);
            erreurs+=".";
            throw new Exception(erreurs);
        }

    }
}
