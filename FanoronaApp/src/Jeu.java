import java.util.HashMap;
import java.util.List;

/*
 * Le Jeu qui gérera tous les péripéties d'un jeu de Fanorona : les mouvements, les tours des joueurs, la déclaration du vainqueur
 */
public class Jeu {
	
	//Attributs
	private Plateau plateau; // le plateau de jeu 
	private Joueur tourActuel; // le Joueur qui a la main en ce moment 
	private Joueur[] joueurs; // les joueurs en train de jouer
	private List<Mouvement> mouvsDuTour; // les mouvements pendant un tour d'un Joueur
	
	
	//getters & setters
	
	
	//Constructeur
	/*
	 * Crée un nouveau Jeu où le Plateau est initialisé et les Joueurs instanciés
	 * @return void 
	 */
	public Jeu(Joueur j1, Joueur j2) {
		// assigne les joueurs
		this.joueurs[0] = j1;
		this.joueurs[1] = j2;
		
		// initialise ou ré-initialise le plateau
		this.plateau = new Plateau(); 
		
		// TODO Mettre le truc trop long au début du Main ici
		
		this.mouvsDuTour.clear(); // purge la liste des mouvements , on rappelera cette fonction à chaque passage de main
	}
	
	
	//Methodes
	
	/*
	 * Déduit qui a gagné le jeu en fonction du nombre de Pion de chaque Joueur sur le plateau
	 * @return String : le pseudo du Joueur qui a gagné
	 */
	public String vainqueur() {
		String res = "";
		// on compte le nombre de Pions de même type encore sur le Plateau
		HashMap<Pion, Integer> resNb = this.plateau.getNbPions();
		// on vérifie le perdant qui n'a plus de Pion sur le Plateau 
		if(resNb.get(new Pion(false, true)) == 0) { // nb des pions ronds
			// cela veut dire que le Joueur qui a pris les Pions Croix est le gagnant
			// on récupère ce Joueur
			if(!this.joueurs[0].isRoundSide()) {
				res += "Félicitations "+this.joueurs[0].getPseudo()+" ! Vous avez gagné au Fanorona!"; 
			}else if (!this.joueurs[1].isRoundSide()) {
				res += "Félicitations "+this.joueurs[0].getPseudo()+" ! Vous avez gagné au Fanorona!"; 
			}
		}else if(resNb.get(new Pion(false, false)) == 0) { //nb des pions croix
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
