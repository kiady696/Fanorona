import java.util.HashMap;

/**
 * Le plateau de jeu composé de Cases
 * @author Kiady
 */
public class Plateau {
	
	//Atributs
	private Case[][] cases; // Tableau à deux dimensions nécessaire pour gérer la matrice de Case qu'est le Plateau
	private HashMap<Integer, String> associationLigneNumero; // Associe la désignation de la ligne au numéro de la ligne 
	
	//getters 
	public Case[][] getCases() {
		return cases;
	}

	//Contructeur
	/**
	 * Initialise un Plateau 
	 */
	public Plateau() {
		this.cases = new Case[5][9]; // initialise les Cases avec leurs dimensions
		this.assocNbLigne(); // associe la désignation d'une ligne avec son numéro
		this.resetPlateau(); // initialise le plateau et place les pions en début de partie 
	}
	
	
	//Méthodes
	
	/**
	 * Retourne la case dont on spécifie l'abcisse et l'ordonnée
	 * @param int x : l'abcisse de la Case
	 * @param int y : l'ordonnée de la Case
	 * @return Case : où l'on va avoir les détails de la Case demandé
	 */
	public Case getCase(int x, int y) throws Exception{
		// Si l'on demande des coordonnées en dehors du Plateau
		if(x < 0 || x > 9 || y < 0 || y > 5) {
			throw new Exception("Coordonées en dehors de notre Plateau");
		}
		
		return this.cases[x][y];
		
	}
	
	
	/**
	 * Réinitialise le Plateau
	 * 
	 */
	public void resetPlateau(){
		
		// initialise la première ligne (haut en bas)
		this.cases[0][0] = new Case(new Pion(false, true), 0, 0);
		this.cases[0][1] = new Case(new Pion(false, true), 0, 1);
		this.cases[0][2] = new Case(new Pion(false, true), 0, 2);
		this.cases[0][3] = new Case(new Pion(false, true), 0, 3);
		this.cases[0][4] = new Case(new Pion(false, true), 0, 4);
		this.cases[0][5] = new Case(new Pion(false, true), 0, 5);
		this.cases[0][6] = new Case(new Pion(false, true), 0, 6);
		this.cases[0][7] = new Case(new Pion(false, true), 0, 7);
		this.cases[0][8] = new Case(new Pion(false, true), 0, 8);
		
		// initialise la deuxième ligne (haut en bas)
		this.cases[1][0] = new Case(new Pion(false, true), 1, 0);
		this.cases[1][1] = new Case(new Pion(false, true), 1, 1);
		this.cases[1][2] = new Case(new Pion(false, true), 1, 2);
		this.cases[1][3] = new Case(new Pion(false, true), 1, 3);
		this.cases[1][4] = new Case(new Pion(false, true), 1, 4);
		this.cases[1][5] = new Case(new Pion(false, true), 1, 5);
		this.cases[1][6] = new Case(new Pion(false, true), 1, 6);
		this.cases[1][7] = new Case(new Pion(false, true), 1, 7);
		this.cases[1][8] = new Case(new Pion(false, true), 1, 8);
		
		// initialise la troisième ligne (haut en bas)
		this.cases[2][0] = new Case(new Pion(false, false), 2, 0); // alternance des Pions ronds et croix sur la troisième ligne
		this.cases[2][1] = new Case(new Pion(false, true), 2, 1);
		this.cases[2][2] = new Case(new Pion(false, false), 2, 2);
		this.cases[2][3] = new Case(new Pion(false, true), 2, 3);
		this.cases[2][4] = new Case(null, 2, 4); // La seule case vide du début de partie 
		this.cases[2][5] = new Case(new Pion(false, false), 2, 5);
		this.cases[2][6] = new Case(new Pion(false, true), 2, 6);
		this.cases[2][7] = new Case(new Pion(false, false), 2, 7);
		this.cases[2][8] = new Case(new Pion(false, true), 2, 8);
		
		// initialise la quatrième ligne (haut en bas)
		this.cases[3][0] = new Case(new Pion(false, false), 3, 0);
		this.cases[3][1] = new Case(new Pion(false, false), 3, 1);
		this.cases[3][2] = new Case(new Pion(false, false), 3, 2);
		this.cases[3][3] = new Case(new Pion(false, false), 3, 3);
		this.cases[3][4] = new Case(new Pion(false, false), 3, 4); 
		this.cases[3][5] = new Case(new Pion(false, false), 3, 5);
		this.cases[3][6] = new Case(new Pion(false, false), 3, 6);
		this.cases[3][7] = new Case(new Pion(false, false), 3, 7);
		this.cases[3][8] = new Case(new Pion(false, false), 3, 8);
		
		// initialise la quatrième ligne (haut en bas)
		this.cases[4][0] = new Case(new Pion(false, false), 4, 0);
		this.cases[4][1] = new Case(new Pion(false, false), 4, 1);
		this.cases[4][2] = new Case(new Pion(false, false), 4, 2);
		this.cases[4][3] = new Case(new Pion(false, false), 4, 3);
		this.cases[4][4] = new Case(new Pion(false, false), 4, 4); 
		this.cases[4][5] = new Case(new Pion(false, false), 4, 5);
		this.cases[4][6] = new Case(new Pion(false, false), 4, 6);
		this.cases[4][7] = new Case(new Pion(false, false), 4, 7);
		this.cases[4][8] = new Case(new Pion(false, false), 4, 8);
		
	}
	
	/**
	 * Affiche le Plateau
	 * @return String res : la représentation du Plateau actuel 
	 */
	public String toString() {
		//initialisation du String de résultat
		String res = ""; 
		// première ligne des numéros de colonnes
		res += "  1   2   3   4   5   6   7   8   9 \n";
		// on parcours le tableau de cases en ligne (index i) et en colonnes (index y)
		for(int i = 0; i < this.cases.length; i++) {
			res += this.associationLigneNumero.get(i) + " "; // première Lettre de la ligne : la désignation de la ligne 
			for (int j = 0; j < this.cases[i].length; j++) {
				res += this.cases[i][j]; // On affiche la ligne selon les Pion.toString()
				if(j < 8) {
					res += " - "; // inter-Case sur une ligne
				}
			} // fin parcours d'une ligne 
			res += "\n"; // à la ligne 
			if (i < 4 && i%2 == 0) res += "  | \\ | / | \\ | / | \\ | / | \\ | / | \n"; // inter-Case entre deux lignes A-B et C-D
			if (i < 4 && i%2 != 0) res += "  | / | \\ | / | \\ | / | \\ | / | \\ | \n"; // inter-Case entre deux lignes B-C et D-E
			
		}
		
		return res; // retourne le résultat :))
		
	}
	
	/**
	 * Associe les lettres de lignes par des nombres 
	 * @param int nb : le numéro de la ligne 
	 * @return HashMap<String>, int : correspondance entre la lettre correspondante et le numéro de ligne 
	 */
	public void assocNbLigne(){
		HashMap<Integer,String> assoc = new HashMap<Integer,String>();
		assoc.put(0, "A");
		assoc.put(1, "B");
		assoc.put(2, "C");
		assoc.put(3, "D");
		assoc.put(4, "E");
		this.associationLigneNumero = assoc;
	}
	
	/**
	 * Retourne le nombre de Pions ronds et croix restants sur le plateau
	 * @return HashMap<Pion, Integer> : Pion le type de pion et Integer son nombre
	 */
	public HashMap<String, Integer> getNbPions(){
		HashMap<String, Integer> res =  new HashMap<String, Integer>();
		int compteurRounds = 0;
		int compteurCroix = 0;
		for(int i = 0; i < this.getCases().length; i++) {
			for(int j = 0; j < this.getCases()[i].length; j++) {
				if(this.getCases()[i][j].getPion() != null && this.getCases()[i][j].getPion().getIsRound()) {
					compteurRounds += 1;
				}else if (this.getCases()[i][j].getPion() != null && !this.getCases()[i][j].getPion().getIsRound()) {
					compteurCroix += 1;
				}
			}
		}
		res.put("ronds", compteurRounds);
		res.put("croix", compteurCroix);
		return res;
	}
	

}
