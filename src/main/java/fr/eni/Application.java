package fr.eni;

import fr.eni.bll.IFilmService;
import fr.eni.ihm.IAuthenticationController;
import fr.eni.ihm.IFilmController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {



	@Autowired
	IFilmController filmController;

	@Autowired
	IAuthenticationController authenticationController;

	public static void main(String[] args) {
		ConfigurableApplicationContext  ctx = SpringApplication.run(Application.class, args);

		ctx.close();
	}

	@Override
	public void run(String... args) throws Exception {


		while(!choisirMenu().equals("8"));

		System.out.println("BYE ! ");

	}


	public String choisirMenu() {
		System.out.println("Que voulez-vous faire ?\n" +
				"1: Lister les films\n" +
				"2: Consulter un film\n" +
				"3: Se connecter\n" +
				"4: Se déconnecter\n" +
				"5: Ajouter un film \n" +
				"6: Mon profil\n" +
				"7: Noter un film\n" +
				"8: Quitter");

		Scanner scanner = new Scanner(System.in);
		String choix = scanner.nextLine();

		switch (choix) {

			case "1":
				filmController.afficherFilms();
				break;

			case "2":
				filmController.detailsFilm();
				break;

			case "3":
				authenticationController.connexion();
				break;
			case "4":
				authenticationController.deconnexion();
				break;

			case "5":
				filmController.ajouterFilm();
				break;

			case "6":
				authenticationController.monProfil();
				break;
			case "7":
				filmController.ajouterAvisFilm();
				break;

			case "8":
				return  choix;

			default:
				System.out.println("Choix incorrect");
				break;
		}

	return choix;

	}


}
