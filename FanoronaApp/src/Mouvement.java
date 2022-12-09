import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
	private Axe lastAxe; // la dernière axe empruntée
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
	 * @param joueurMoving : le joueur qui veut déplacer un de ces Pions 
	 */
	public Mouvement(Joueur joueurMoving, Plateau plateau) {
		super();
		this.joueurMoving = joueurMoving;
		this.plateauActuel = plateau;
		this.pionsCaptures = new ArrayList<Pion>();
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
	 * Fonction qui retourne l'Axe concerné à partir de deux Cases (x,y) & (x',y')
	 * @param Case caseDepart
	 * @param Case caseArrivée
	 * @return Axe l'axe concerné | null si on n'a pas d'axe qui correspond
	 */
	public Axe getAxe(Case caseDepart, Case caseArrivee) {
		Plateau p = new Plateau();
		Axe[] axesActuel = p.getAxes();
		int count = 0;
		//parcours les Axes (tableau de Cases)
		for(Axe axe : axesActuel) {
			count = 0;
			for(Case uneCaseDeChaqueAxe : axe.getCasesOfAnAxe()) {
				Case caseDUnAxe = uneCaseDeChaqueAxe;
				// des qu'il y a plus d'une case qui correspond soit au case de départ soit au case d'arrivee
				if((caseDUnAxe.getX() == caseDepart.getX() && caseDUnAxe.getY() == caseDepart.getY()) || (caseDUnAxe.getX() == caseArrivee.getX() && caseDUnAxe.getY() == caseArrivee.getY())) {
					count++;
				}
			}
			if(count == 2) {
				return axe;
			}
		}	
		return null;
	}
	
	/**
	 * Fonction qui vérifie la proximité d'un Pion adverse aux niveaux de la Case de Départ et de la Case d'arrivée, dans l'Axe du Mouvement
	 * @return HashMap<String,Axe> : ex: ( "Aspiration", Axe axeDit )
	 */
	public HashMap<String, Axe> proxi(){
		// vérifie si il y a des Pions adverses près de la position de départ 
		
		// vérifie si il y a des Pions adverses près de la position d'arrivée
		return null;
	}
	
	/**
	 * Fonction qui effectue le mouvement ainsi que la capture 
	 * @return void : il fait le mouvement du PionMoved, élimine les Pions capturés et les ajoute dans this.pionsCaptures
	 */
	public void makeMove() {
		
	try {
		// Check les captures
		//parcours l'Axe de mouvement
		for(Case caseDUnAxe : this.lastAxe.getCasesOfAnAxe()) {
			if(this.plateauActuel.getCase(caseDUnAxe.getX(), caseDUnAxe.getY()).getPion() != null) {
				if(this.joueurMoving.isRoundSide() && !this.plateauActuel.getCase(caseDUnAxe.getX(), caseDUnAxe.getY()).getPion().getIsRound()) {
					this.pionsCaptures.add(this.plateauActuel.getCase(caseDUnAxe.getX(), caseDUnAxe.getY()).getPion()); // ajoute le pion capturé au tableau de pion
					this.plateauActuel.setCase(null, caseDUnAxe.getX(), caseDUnAxe.getY()); // capture les pions adverses
				}else if(!this.joueurMoving.isRoundSide() && this.plateauActuel.getCase(caseDUnAxe.getX(), caseDUnAxe.getY()).getPion().getIsRound()) {
					this.pionsCaptures.add(this.plateauActuel.getCase(caseDUnAxe.getX(), caseDUnAxe.getY()).getPion()); // ajoute le pion capturé au tableau de pion
					this.plateauActuel.setCase(null, caseDUnAxe.getX(), caseDUnAxe.getY()); // capture les pions adverses
				}
			}
		}
		
		
		// set le Pion à l'endroit souhaité
		this.plateauActuel.setCase(this.pionMoved, this.caseArrivee.getX(), this.caseArrivee.getY());
		// retire le pionMoved de la case de départ
		this.plateauActuel.setCase(null, this.caseDepart.getX(), this.caseDepart.getY());
		// maj case de depart 
		this.caseDepart = new Case(null, this.caseArrivee.getX(), this.caseArrivee.getY());
	}catch(Exception e) {
		System.out.println("Il y a eu peut-être un petit problème durant la capture");
	}

	}
	
	/**
	 * Fonction qui vérifie si les coordonnées où le Pion de départ va aller sont valides
	 * @param Input : l'input qu'a spécifié le joueur
	 * @return boolean canMove
	 */
	public boolean canMove(Input xyInput) {
		String input = xyInput.getInput();
		char xStr = input.charAt(0);
		char yStr = input.charAt(2);
		int xVal = this.assocCharLigneToNbLigne(xStr);
		int yVal = Integer.parseInt(String.valueOf(yStr)) - 1; // -1 pour l'index des tableaux
		try {
			
			// vérifier si ce n'est pas la même position de départ
			if(this.caseDepart.getX() == xVal && this.caseDepart.getY() == yVal) {
				return false;
			}
			// vérifier si il n'y a pas d'écart de deux Case dans les coordonnées (saut de case)
			else if( Math.abs(xVal - this.caseDepart.getX()) > 2 || Math.abs(yVal - this.caseDepart.getY()) > 2 ) {
				return false;
			}
			// vérifier si ce n'est pas une Case déjà occupée
			else if(this.plateauActuel.getCase(xVal, yVal).getPion() != null) {
				return false;
			}
			// vérifier si le déplacement est bien sur une axe définie
			this.lastAxe = this.getAxe(caseDepart, new Case(null, xVal, yVal));//set la dernière axe concernée comme emprunté
			if(this.lastAxe == null) {
				return false;
			}
			
		}catch(Exception e) {
			System.out.println("Oups, là où vous voulez aller ne se trouve peut-être pas la voie");
		}
		// tout s'est bien passé 
		this.caseArrivee = new Case(null, xVal, yVal);
		return true;
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
			this.caseDepart = this.plateauActuel.getCase(xVal, yVal); // peut jeter une Exception si coordonnées hors du plateau ou Case vide
			
			//check if Pion adverse on this Case || Case vide
			boolean sideJoueurRound = this.getJoueurMoving().isRoundSide();
			boolean caseIsRound = this.caseDepart.getPion().getIsRound();
			if(!this.getJoueurMoving().isRoundSide() && this.caseDepart.getPion().getIsRound() 
					|| this.getJoueurMoving().isRoundSide() && !this.caseDepart.getPion().getIsRound() 
					|| this.caseDepart.getPion() == null) {
				return false;
			}else { // choix OK
				// set le Pion choisi
				this.setPionMoved(this.caseDepart.getPion());
				return true;
			}
		}catch(Exception e) {
			System.out.println("Oups, le Pion que vous avez décidé de sélectionner n'est pas un Pion de votre équipe ou il s\'agit d\'une Case vide.");
		}
		return false;
		
	}
	

}
