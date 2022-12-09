import java.util.HashMap;

/**
 * Le plateau de jeu composé de Cases
 * @author Kiady
 */
public class Plateau {
	
	//Atributs
	private Case[][] cases; // Tableau à deux dimensions nécessaire pour gérer la matrice de Case qu'est le Plateau
	private HashMap<Integer, String> associationLigneNumero; // Associe la désignation de la ligne au numéro de la ligne 
	private Axe[] axes; // Les axes qui composent le Plateau
	
	//getters 
	public Case[][] getCases() {
		return cases;
	}
	public Axe[] getAxes() {
		return this.axes;
	}
	
	public void setCase(Pion pionAMettre, int x, int y) {
		this.cases[x][y] = new Case(pionAMettre, x, y);
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
		if(x < 0 || x > 5 || y < 0 || y > 9) {
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
		
		// On set les axes aussi au début ici
		this.axes = new Axe[24]; // il y a 24 axes sur un plateau de fanorona
		// Première Axe
		Case[] casesLigne0 = new Case[9];
		casesLigne0[0] = this.cases[0][0];
		casesLigne0[1] = this.cases[0][1];
		casesLigne0[2] = this.cases[0][2];
		casesLigne0[3] = this.cases[0][3];
		casesLigne0[4] = this.cases[0][4];
		casesLigne0[5] = this.cases[0][5];
		casesLigne0[6] = this.cases[0][6];
		casesLigne0[7] = this.cases[0][7];
		casesLigne0[8] = this.cases[0][8];
		this.axes[0] = new Axe(casesLigne0);
		//  2è Axe
		Case[] casesLigne1 = new Case[9];
		casesLigne1[0] = this.cases[4][0];
		casesLigne1[1] = this.cases[4][1];
		casesLigne1[2] = this.cases[4][2];
		casesLigne1[3] = this.cases[4][3];
		casesLigne1[4] = this.cases[4][4];
		casesLigne1[5] = this.cases[4][5];
		casesLigne1[6] = this.cases[4][6];
		casesLigne1[7] = this.cases[4][7];
		casesLigne1[8] = this.cases[4][8];
		this.axes[1] = new Axe(casesLigne1);
		//  3è Axe
		Case[] casesLigne2 = new Case[5];
		casesLigne2[0] = this.cases[0][0];
		casesLigne2[1] = this.cases[1][0];
		casesLigne2[2] = this.cases[2][0];
		casesLigne2[3] = this.cases[3][0];
		casesLigne2[4] = this.cases[4][0];
		this.axes[2] = new Axe(casesLigne2);
		//  4è Axe
		Case[] casesLigne3 = new Case[5];
		casesLigne3[0] = this.cases[0][8];
		casesLigne3[1] = this.cases[1][8];
		casesLigne3[2] = this.cases[2][8];
		casesLigne3[3] = this.cases[3][8];
		casesLigne3[4] = this.cases[4][8];
		this.axes[3] = new Axe(casesLigne3);
		
		//  5è Axe
		Case[] casesLigne4 = new Case[9];
		casesLigne4[0] = this.cases[1][0];
		casesLigne4[1] = this.cases[1][1];
		casesLigne4[2] = this.cases[1][2];
		casesLigne4[3] = this.cases[1][3];
		casesLigne4[4] = this.cases[1][4];
		casesLigne4[5] = this.cases[1][5];
		casesLigne4[6] = this.cases[1][6];
		casesLigne4[7] = this.cases[1][7];
		casesLigne4[8] = this.cases[1][8];
		this.axes[4] = new Axe(casesLigne4);
		//  6è Axe
		Case[] casesLigne5 = new Case[9];
		casesLigne5[0] = this.cases[2][0];
		casesLigne5[1] = this.cases[2][1];
		casesLigne5[2] = this.cases[2][2];
		casesLigne5[3] = this.cases[2][3];
		casesLigne5[4] = this.cases[2][4];
		casesLigne5[5] = this.cases[2][5];
		casesLigne5[6] = this.cases[2][6];
		casesLigne5[7] = this.cases[2][7];
		casesLigne5[8] = this.cases[2][8];
		this.axes[5] = new Axe(casesLigne5);
		//  7è Axe
		Case[] casesLigne6 = new Case[9];
		casesLigne6[0] = this.cases[3][0];
		casesLigne6[1] = this.cases[3][1];
		casesLigne6[2] = this.cases[3][2];
		casesLigne6[3] = this.cases[3][3];
		casesLigne6[4] = this.cases[3][4];
		casesLigne6[5] = this.cases[3][5];
		casesLigne6[6] = this.cases[3][6];
		casesLigne6[7] = this.cases[3][7];
		casesLigne6[8] = this.cases[3][8];
		this.axes[6] = new Axe(casesLigne6);
		
		//  8è Axe
		Case[] casesLigne8 = new Case[5];
		casesLigne8[0] = this.cases[0][1];
		casesLigne8[1] = this.cases[1][1];
		casesLigne8[2] = this.cases[2][1];
		casesLigne8[3] = this.cases[3][1];
		casesLigne8[4] = this.cases[4][1];
		this.axes[7] = new Axe(casesLigne8);
		//  9è Axe
		Case[] casesLigne9 = new Case[5];
		casesLigne9[0] = this.cases[0][2];
		casesLigne9[1] = this.cases[1][2];
		casesLigne9[2] = this.cases[2][2];
		casesLigne9[3] = this.cases[3][2];
		casesLigne9[4] = this.cases[4][2];
		this.axes[8] = new Axe(casesLigne9);
		//  10è Axe
		Case[] casesLigne10 = new Case[5];
		casesLigne10[0] = this.cases[0][3];
		casesLigne10[1] = this.cases[1][3];
		casesLigne10[2] = this.cases[2][3];
		casesLigne10[3] = this.cases[3][3];
		casesLigne10[4] = this.cases[4][3];
		this.axes[9] = new Axe(casesLigne10);
		//  11è Axe
		Case[] casesLigne11 = new Case[5];
		casesLigne11[0] = this.cases[0][4];
		casesLigne11[1] = this.cases[1][4];
		casesLigne11[2] = this.cases[2][4];
		casesLigne11[3] = this.cases[3][4];
		casesLigne11[4] = this.cases[4][4];
		this.axes[10] = new Axe(casesLigne11);
		//  12è Axe
		Case[] casesLigne12 = new Case[5];
		casesLigne12[0] = this.cases[0][5];
		casesLigne12[1] = this.cases[1][5];
		casesLigne12[2] = this.cases[2][5];
		casesLigne12[3] = this.cases[3][5];
		casesLigne12[4] = this.cases[4][5];
		this.axes[11] = new Axe(casesLigne12);
		//  13è Axe
		Case[] casesLigne13 = new Case[5];
		casesLigne13[0] = this.cases[0][6];
		casesLigne13[1] = this.cases[1][6];
		casesLigne13[2] = this.cases[2][6];
		casesLigne13[3] = this.cases[3][6];
		casesLigne13[4] = this.cases[4][6];
		this.axes[12] = new Axe(casesLigne13);
		//  14è Axe
		Case[] casesLigne14 = new Case[5];
		casesLigne14[0] = this.cases[0][7];
		casesLigne14[1] = this.cases[1][7];
		casesLigne14[2] = this.cases[2][7];
		casesLigne14[3] = this.cases[3][7];
		casesLigne14[4] = this.cases[4][7];
		this.axes[13] = new Axe(casesLigne14);
		
		
		//  15è Axe
		Case[] casesLigne15 = new Case[3];
		casesLigne15[0] = this.cases[2][0];
		casesLigne15[1] = this.cases[3][1];
		casesLigne15[2] = this.cases[4][2];
		this.axes[14] = new Axe(casesLigne15);
		//  16è Axe
		Case[] casesLigne16 = new Case[5];
		casesLigne16[0] = this.cases[0][0];
		casesLigne16[1] = this.cases[1][1];
		casesLigne16[2] = this.cases[2][2];
		casesLigne16[3] = this.cases[3][3];
		casesLigne16[4] = this.cases[4][4];
		this.axes[15] = new Axe(casesLigne16);
		//  17è Axe
		Case[] casesLigne17 = new Case[5];
		casesLigne17[0] = this.cases[0][2];
		casesLigne17[1] = this.cases[1][3];
		casesLigne17[2] = this.cases[2][4];
		casesLigne17[3] = this.cases[3][5];
		casesLigne17[4] = this.cases[4][6];
		this.axes[16] = new Axe(casesLigne17);
		//  18è Axe
		Case[] casesLigne18 = new Case[5];
		casesLigne18[0] = this.cases[0][4];
		casesLigne18[1] = this.cases[1][5];
		casesLigne18[2] = this.cases[2][6];
		casesLigne18[3] = this.cases[3][7];
		casesLigne18[4] = this.cases[4][8];
		this.axes[17] = new Axe(casesLigne18);
		//  19è Axe
		Case[] casesLigne19 = new Case[3];
		casesLigne19[0] = this.cases[0][6];
		casesLigne19[1] = this.cases[1][7];
		casesLigne19[2] = this.cases[2][8];
		this.axes[18] = new Axe(casesLigne19);
		
		//  20è Axe
		Case[] casesLigne20 = new Case[5];
		casesLigne20[0] = this.cases[4][0];
		casesLigne20[1] = this.cases[3][1];
		casesLigne20[2] = this.cases[2][2];
		casesLigne20[3] = this.cases[1][3];
		casesLigne20[4] = this.cases[0][4];
		this.axes[19] = new Axe(casesLigne20);
		//  21è Axe
		Case[] casesLigne21 = new Case[5];
		casesLigne21[0] = this.cases[4][2];
		casesLigne21[1] = this.cases[3][3];
		casesLigne21[2] = this.cases[2][4];
		casesLigne21[3] = this.cases[1][5];
		casesLigne21[4] = this.cases[0][6];
		this.axes[20] = new Axe(casesLigne21);
		//  22è Axe
		Case[] casesLigne22 = new Case[5];
		casesLigne22[0] = this.cases[4][4];
		casesLigne22[1] = this.cases[3][5];
		casesLigne22[2] = this.cases[2][6];
		casesLigne22[3] = this.cases[1][7];
		casesLigne22[4] = this.cases[0][8];
		this.axes[21] = new Axe(casesLigne22);
		//  23è Axe
		Case[] casesLigne23 = new Case[3];
		casesLigne23[0] = this.cases[4][6];
		casesLigne23[1] = this.cases[3][7];
		casesLigne23[2] = this.cases[2][8];
		this.axes[22] = new Axe(casesLigne23);
		//  24è Axe
		Case[] casesLigne24 = new Case[3];
		casesLigne24[0] = this.cases[2][0];
		casesLigne24[1] = this.cases[1][1];
		casesLigne24[2] = this.cases[0][2];
		this.axes[23] = new Axe(casesLigne24);
		
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
