
/*
 * Une case qui constitue le Plateau de jeu 
 * @author Kiady
 */
public class Case {
	
	//Atributs
	private Pion pion; // le Pion qui se situe sur la case, null si il n'y a aucun Pion sur this Case
	private int x; // la position abcisse de la Case sur le plateau (origine des axes en haut à gauche)
	private int y; // la position ordonnée de la Case sur le plateau (origine des axes en haut à gauche)
	
	
	//getters & setters
	/*
	 * Retourne le Pion sur this Case
	 * @return this.pion si il existe sur la Case | null si il n'y a pas de Pion sur la Case
	 */
	public Pion getPion() {
		return pion;
	}
	
	/*
	 * Met un Pion sur this Case
	 * @param Pion pion : le Pion à mettre sur la case | null si on veut spécifier une Case vide
	 */
	public void setPion(Pion pion){
		this.pion = pion;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	

	//Constructeur
	/*
	 * Constructs a Case with its Pion and coordinates
	 * @param Pion pion : null if its an empty Case
	 * @param int x : abcisse coordinate of the Case
	 * @param int y : ordonate coordinate of the Case
	 */
	public Case(Pion pion, int x, int y){
		super();
		this.setPion(pion); // pion peut être null si Case vide
		this.x = x;
		this.y = y;
	}
	
	
	//Méthodes
	/*
	 * Affichage d'une Case selon le Pion qui s'y trouve
	 * @return String caseRepresentation : soit "o" si Pion rond | "x" si Pion croix | "+" si Case vide
	 */
	public String toString() {
		// initialisation du résultat 
		String res = "";
		if(this.pion == null) { // si le Pion est null (Case vide)
			res = "+";
		}else if(!this.getPion().isRound()) { // si le pion est croix
			res = "x";
		}else if(this.getPion().isRound()){ // si le pion est rond
			res = "o";
		}
		
		return res;
	}

}
