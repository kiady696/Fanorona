import java.util.ArrayList;

/**
 * La classe qui gère les mouvements et les Captures des Pions sur le Plateau de jeu 
 */
public class Mouvement {
	
	//Attributs
	private Joueur joueurMoving; // le joueur qui veut déplacer un de ses pions 
	private Plateau plateauActuel; // le plateau au moment du mouvement (la référence de son état actuel)
	private Pion pionMoved; // le Pion que le Joueur a décidé de déplacer
	private Case caseDepart; // la case de départ du Pion qui est à déplacer
	private Case caseArrivee; // la case d'arrivée où le Joueur decide de mettre son Pion
	private ArrayList<Pion> pionsCaptures; // une liste dynamique de Pions ayant été capturés pendant le mouvement
	
	
	//getters & setters
	public Joueur getJoueurMoving() {
		return joueurMoving;
	}
	public void setJoueurMoving(Joueur joueurMoving) {
		this.joueurMoving = joueurMoving;
	}
	public Pion getPionMoved() {
		return pionMoved;
	}
	public void setPionMoved(Pion pionMoved) {
		this.pionMoved = pionMoved;
	}
	public Case getCaseDepart() {
		return caseDepart;
	}
	public void setCaseDepart(Case caseDepart) {
		this.caseDepart = caseDepart;
	}
	public Case getCaseArrivee() {
		return caseArrivee;
	}
	public void setCaseArrivee(Case caseArrivee) {
		this.caseArrivee = caseArrivee;
	}
	public ArrayList<Pion> getPionsCaptures() {
		return pionsCaptures;
	}
	public void setPionsCaptures(ArrayList<Pion> pionsCaptures) {
		this.pionsCaptures = pionsCaptures;
	}
	
	
	//Constructeur
	/*** 
	 * initialise un Mouvement avec le Joueur qui a le tour actuel 
	 * @param Joueur joueurMoving : le joueur qui veut déplacer un de ces Pions 
	 */
	public Mouvement(Joueur joueurMoving, Plateau plateau) {
		super();
		this.joueurMoving = joueurMoving;
		this.plateauActuel = plateau;
	}
	
	
	
	//Méthodes
	/**
	 * Associe la lettre de ligne avec le numéro de la ligne
	 * @param charX la lettre de désignation de la ligne
	 * @return int le numero de ligne : -1 si jamais la lettre de ligne ne correspond à rien
	 */
	public int assocCharLigneToNbLigne(char charX) {
		if(charX == 'A') {
			return 0;
		}else if(charX == 'B' ) {
			return 1;
		}else if(charX == 'C' ) {
			return 2;
		}else if(charX == 'D' ) {
			return 3;
		}else if(charX == 'E' ) {
			return 4;
		}
		return -1;
	}
	
	/**
	 * Fonction qui vérifie si le Joueur joueurMoving peut choisir un Pion qu'il a spécifié à un endroit
	 * @param Input xyInputs : les coordonnées demandées par le Joueur
	 * @return boolean canChoose
	 */
	public boolean canChoose(Input xyInput){
		// décortique l'input
		try {
			
			String input = xyInput.getInput();
			char xStr = input.charAt(0);
			char yStr = input.charAt(2);
			int xVal = this.assocCharLigneToNbLigne(xStr);
			int yVal = Integer.parseInt(String.valueOf(yStr)) - 1; // -1 pour l'index des tableaux
			
			//check if coordonnées en dehors du plateau 
			this.caseDepart = this.plateauActuel.getCase(xVal, yVal); // peut jeter une Exception si coordonnées hors du plateau
			
			//check if Pion adverse on this Case || Case vide
			boolean sideJoueurRound = this.getJoueurMoving().isRoundSide();
			boolean caseIsRound = this.caseDepart.getPion().getIsRound();
			if(!this.getJoueurMoving().isRoundSide() && this.caseDepart.getPion().getIsRound() 
					|| this.getJoueurMoving().isRoundSide() && !this.caseDepart.getPion().getIsRound() 
					|| this.caseDepart.getPion() == null) {
				return false;
			}else {
				// set le Pion choisi
				this.setPionMoved(this.caseDepart.getPion());
				return true;
			}
		}catch(Exception e) {
			System.out.println("Oups, le Pion que vous avez décidé de sélectionner n'est pas un Pion valide");
		}finally {
			if(this.caseDepart == null) {
				return false;
			}
		}
		return false;
		
	}
	

}
