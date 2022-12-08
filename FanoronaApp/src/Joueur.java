
/**
 * Le Joueur qui prendra un côté (soit l'équipe aux Pions ronds soit celui aux Pions croix)
 * @author Kiady
 */
public class Joueur {

	//Attributs
	private String pseudo;
	private boolean isRoundSide; // spécifie si le joueur a pris les Pions ronds
	private boolean hasTakenFirstTurn; // true si le Joueur a décidé de prendre la main en premier 
	private boolean isHumanSide; // Vérifie si le Joueur est humain, true par défaut pour le moment, on implémentra cela plus tard ;)
	
	
	//Getters & Setters
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public boolean isRoundSide() {
		return isRoundSide;
	}
	public void setRoundSide(boolean isRoundSide) {
		this.isRoundSide = isRoundSide;
	} 
	public boolean isHasTakenFirstTurn() {
		return hasTakenFirstTurn;
	}
	public void setHasTakenFirstTurn(boolean hasTakenFirstTurn) {
		this.hasTakenFirstTurn = hasTakenFirstTurn;
	}
	
	//Constructeur
	
	/**
	 * Constructeur simple au début de l'initialisation de la partie (sans renseignement)
	 */
	public Joueur() {
	}
	
	
	/**
	 * Constructeur qui initialise un Joueur après obtention de ses renseignements 
	 * @return void
	 */
	public Joueur(String pseudo, boolean isRoundSide, boolean isFirst) {
		this.setPseudo(pseudo);
		this.setRoundSide(isRoundSide);
		this.setHasTakenFirstTurn(isFirst);
		this.isHumanSide = true;
	}
	
	
	//Methodes
	
	
	
	
	
}
