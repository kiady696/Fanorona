
/*
 * @description Un Pion qui se place sur le Plateau et qu'utilisera le Joueur
 * @author Kiady
 */
public class Pion {
	
	//Atributs
	private boolean isKilled; //pour vérifier si le Pion a été capturé
	private boolean isRound; //pour vérifier si le Pion appartient à l'équipe des Pions ronds , si false, alors appartient aux Pions croix
	
	
	//getters & setters
	public boolean isKilled() {
		return isKilled;
	}
	public void setIsKilled(boolean isKilled) {
		this.isKilled = isKilled;
	}
	public boolean getIsRound() {
		return isRound;
	}
	/*
	 * initialise le Pion comme étant un Pion rond ou croix
	 * @param boolean isRound : false if we want to set the Pion as a Pion croix | true to set a Pion rond 
	 * @return void
	*/
	public void setIsRound(boolean isRound) {
		this.isRound = isRound;
	}
	
	
	//Constructeurs
	/*
	 * Create a Pion 
	 * @param boolean isKilled : false if to set a Pion for the first time
	 * @param boolean isRound : false si on crée un Pion croix | true si on crée un Pion rond
	 */
	public Pion(boolean isKilled, boolean isRound) {
		super();
		this.isKilled = isKilled;
		this.isRound = isRound;
	}
	
	
	//Méthodes
	
	/*
	 * Retourne le type de pion 
	 * @return true si le Pion est de l'équipe des Pion rond | false si c'est un Pion croix
	 */
	public boolean isRound() {
		if(this.getIsRound()) {
			return true;
		}
		return false;
	}
	
	
	
	/*
	 * Check si le Pion peut effectuer un mouvement 
	 * @param Mouvement mouv : inclus le Plateau, la Case depart, la Case arrivée ainsi que le Joueur qui veut faire le Mouvement 
	 * @return boolean : true si le mouvement peut être fait | false sinon
	 */
	//public boolean canMove(Mouvement mouv) {
		
		// on le permet de faire tous les mouvements pour l'instant
		//return true;
	//}


}
