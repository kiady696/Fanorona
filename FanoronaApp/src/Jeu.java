import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Le Jeu qui gérera tous les péripéties d'un jeu de Fanorona : les mouvements, les tours des joueurs et la déclaration du vainqueur
 */
public class Jeu {
	
	//Attributs
	private Plateau plateau; // le plateau de jeu 
	private Joueur tourActuel; // le Joueur qui a la main en ce moment 
	private Joueur[] joueurs; // les joueurs en train de jouer
	private List<Mouvement> mouvsDuTour; // les mouvements pendant un tour d'un Joueur
	
	
	//getters & setters
	public Plateau getPlateau() {
		return this.plateau;
	}
	public Joueur getTourActuel() {
		return this.tourActuel;
	}
	public void setTourActuel(Joueur j) {
		this.tourActuel = j;
	}
	
	
	//Constructeur
	/**
	 * Crée un nouveau Jeu où le Plateau est initialisé et les Joueurs instanciés
	 */
	public Jeu(Joueur j1, Joueur j2) {
		// assigne les joueurs
		this.joueurs = new Joueur[2]; // il y a seulement deux Joueurs qui peuvent jouer 
		this.joueurs[0] = j1;
		this.joueurs[1] = j2;
		
		// initialise le plateau
		this.plateau = new Plateau(); 
		
		// demander au Joueur 1 d'entrer son pseudo
		System.out.println("Pour commencer à jouer, veuillez entrer votre pseudo :");
		Scanner in = new Scanner(System.in); // initialise un Scanner pour le pseudo du Joueur 1
		String pseudo = in.nextLine(); // demande et récupère un pseudo à la console
		Input pseudoInput = new Input(pseudo,"pseudo"); // crée un input 
		while(!pseudoInput.checkInputIsValid()) { // redemande à chaque fois que le pseudo n'est pas valide
			System.out.println("Pseudo invalide, veuillez entrer un pseudo simple :");
			pseudo = in.nextLine();
			pseudoInput = new Input(pseudo,"pseudo");
		}
		// pseudo OK, demander au Joueur 1 de choisir son équipe
		System.out.println("Bien reçu "+pseudoInput.getInput()+", voulez-vous faire partie de l\'équipe des Pions ronds ou celui des Pions croix? : [r/c]");
		String choixEquipe = in.nextLine(); // demande et récupère le choix d'équipe du Joueur 1 
		Input choixEquipeInput = new Input(choixEquipe, "choix équipe");
		while(!choixEquipeInput.checkInputIsValid()) { // tant que le joueur n'entre pas 'r' ou 'c'
			System.out.println("Oups, mauvaise entrée, veuiller saisir \'r\' pour Pions ronds ou \'c\' pour Pions croix : ");
			choixEquipe = in.nextLine();
			choixEquipeInput = new Input(choixEquipe, "choix équipe");
		}
		
		// demander au Joueur 1 si il veut prendre la main pour le premier tour 
		System.out.println(pseudoInput.getInput()+", Voulez-vous prendre la main pour le premier tour? [o/n]");
		String choixPrendreMain = in.nextLine();
		Input choixPrendreMainInput = new Input(choixPrendreMain, "prise de main");
		while(!choixPrendreMainInput.checkInputIsValid()) {
			System.out.println("Oups, mauvaise entrée, veuiller saisir en lettre \\'o\\' pour prendre la main ou \\'n\\' pour la laisser : ");
			choixPrendreMain = in.nextLine();
			choixPrendreMainInput = new Input(choixPrendreMain, "prise de main");
		}
		
		// Si tout passe jusqu'ici, on renseigne le Joueur j1
		this.joueurs[0].setPseudo(pseudoInput.getInput()); // comme ça le pseudo a été vérifié 
		if(choixEquipeInput.getInput().compareTo("r") == 0 || choixEquipeInput.getInput().compareTo("R") == 0) {
			// this.joueurs[0] a choisi l'équipe des Pions ronds
			this.joueurs[0].setRoundSide(true);
			// this.joueurs[1] sera automatiquement assigné à l'équipe des Pions croix 
			this.joueurs[1].setRoundSide(false);
		}else { 
			// this.joueurs[0] n'a pas choisi l'équipe des Pions ronds
			this.joueurs[0].setRoundSide(false);
			this.joueurs[1].setRoundSide(true); // this.joueurs[1] va être l'équipe des Pions ronds
		}
		if(choixPrendreMainInput.getInput().compareTo("o") == 0 || choixPrendreMainInput.getInput().compareTo("O") == 0) {
			// le Joueur this.joueurs[0] a décidé de prendre la main
			this.joueurs[0].setHasTakenFirstTurn(true);
			this.tourActuel = joueurs[0]; // c'est le Joueur 1 qui a la main actuelle (la première main)
			this.joueurs[1].setHasTakenFirstTurn(false);
		}else { // le Joueur this.joueurs[0] a laissé la main
			this.joueurs[0].setHasTakenFirstTurn(false);
			this.joueurs[1].setHasTakenFirstTurn(true); // le Joueur j2 prend la main en premier
			this.tourActuel = joueurs[1]; // c'est le Joueur 2 qui a la main actuelle (la première main)

		}
		
		// demander au Joueur 2 d'entrer son pseudo etc idem que pour this.joueurs[0]
		System.out.println("Enfin, pour le deuxième joueur, veuiller entrer votre pseudo :");
		pseudo = in.nextLine();
		pseudoInput = new Input(pseudo, "pseudo");
		while(!pseudoInput.checkInputIsValid()) {
			System.out.println("Pseudo invalide, veuillez entrer un pseudo simple :");
			pseudo = in.nextLine();
			pseudoInput = new Input(pseudo,"pseudo");
		}
		// on set le pseudo du joueur j2
		this.joueurs[1].setPseudo(pseudoInput.getInput());
		
//		in.close(); // ferme l'IO du Scanner
		
		if(this.mouvsDuTour != null) this.mouvsDuTour.clear(); // purge la liste des mouvements , on rappelera cette fonction à chaque passage de main
	}
	
	
	//Methodes
	
	/***
	 *	Affiche l'état actuel du plateau 
	 *	@return String infos : les informations des Joueurs et du Plateau
	 */
	public String toString() {
		String infos = "";
		// ajoute les infos du Joueur 1
		String typePionJ1 = this.joueurs[0].isRoundSide() ? "Ronds" : "Croix";
		Pion pionJ1 = this.joueurs[0].isRoundSide() ? new Pion(false, true) : new Pion(false, false);
		Integer nbPionJ1 = this.joueurs[0].isRoundSide() ? this.plateau.getNbPions().get("ronds") : this.plateau.getNbPions().get("croix");
		infos += this.joueurs[0].getPseudo() +", vous avez "+ nbPionJ1 +" Pions "+ typePionJ1 + " sur le plateau \n";
		// ajoute les infos du Joueur 2
		String typePionJ2 = this.joueurs[1].isRoundSide() ? "Ronds" : "Croix";
		Pion pionJ2 = this.joueurs[1].isRoundSide() ? new Pion(false, true) : new Pion(false, false);
		Integer nbPionJ2 = this.joueurs[1].isRoundSide() ? this.plateau.getNbPions().get("ronds") : this.plateau.getNbPions().get("croix");
		infos += this.joueurs[1].getPseudo() +", vous avez "+ nbPionJ2 +" Pions "+ typePionJ2 + " sur le plateau \n";
		// ajoute la représentation du plateau actuellement 
		infos += " \n";
		infos +=this.plateau;
		// on peut ajouter le chrono ici ou jsp pour le futur
		
		return infos;
		
	}
	
	/**
	 * Déduit qui a gagné le jeu en fonction du nombre de Pion de chaque Joueur sur le plateau
	 * @return String : le pseudo du Joueur qui a gagné
	 */
	public String vainqueur() {
		String res = "";
		// on compte le nombre de Pions de même type encore sur le Plateau
		HashMap<String, Integer> resNb = this.plateau.getNbPions();
		// on vérifie le perdant qui n'a plus de Pion sur le Plateau 
		if(resNb.get("ronds") == 0) { // nb des pions ronds
			// cela veut dire que le Joueur qui a pris les Pions Croix est le gagnant
			// on récupère ce Joueur
			if(!this.joueurs[0].isRoundSide()) {
				res += "Félicitations "+this.joueurs[0].getPseudo()+" ! Vous avez gagné au Fanorona!"; 
			}else if (!this.joueurs[1].isRoundSide()) {
				res += "Félicitations "+this.joueurs[0].getPseudo()+" ! Vous avez gagné au Fanorona!"; 
			}
		}else if(resNb.get("croix") == 0) { //nb des pions croix
			// cela veut dire que le Joueur qui a pris les Pions Ronds est le gagnant
			// on récupère ce Joueur
			if(this.joueurs[0].isRoundSide()) {
				res += "Félicitations "+this.joueurs[0].getPseudo()+" ! Vous avez gagné au Fanorona!"; 
			}else if (this.joueurs[1].isRoundSide()) {
				res += "Félicitations "+this.joueurs[0].getPseudo()+" ! Vous avez gagné au Fanorona!"; 
			}
		}
		
		return res;
		
	}
	
	
	


	

}
