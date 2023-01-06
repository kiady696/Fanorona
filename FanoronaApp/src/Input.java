import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe qui gère les inputs des joueurs
 */
public class Input {
	
	//Attributs
	private String input;
	private String typeInput;
	private Pattern patternPseudo; 
	private Pattern patternChoixEquipe; 
	private Pattern patternChoixPrendreMain; 
	private Pattern patternCoordonnees;
	private Pattern ouiNon;
	private Matcher matcher;
	
	//getter 
	public String getInput() {
		return this.input;
	}

	
	//Constructeur 
	public Input(String input, String typeInput) {
		this.input = input;
		this.typeInput = typeInput;
		this.patternPseudo = Pattern.compile("[a-zA-Z0-9\s+]+", Pattern.CASE_INSENSITIVE);
		this.patternChoixEquipe = Pattern.compile("[rc]", Pattern.CASE_INSENSITIVE);
		this.patternChoixPrendreMain = Pattern.compile("[on]", Pattern.CASE_INSENSITIVE);
		this.patternCoordonnees = Pattern.compile("^([ABCDE]),([123456789])$", Pattern.CASE_INSENSITIVE);
		this.ouiNon = Pattern.compile("[on]", Pattern.CASE_INSENSITIVE);
	}
	
	/**
	 * Gestion des inputs du Joueur, lève une Exception qui correspond à l'input invalide
	 * @return boolean : true si input valide | false autrement 
	 *
	 */
	public boolean checkInputIsValid(){
		if(this.typeInput.compareTo("pseudo") == 0) { // si cst un input de pseudo
			this.matcher = this.patternPseudo.matcher(this.input);
			if(matcher.matches()) return true; 
		}
		if(this.typeInput.compareTo("choix équipe") == 0) { // si cst un input de choix d'équipe
			this.matcher = this.patternChoixEquipe.matcher(this.input);
			if(matcher.matches()) return true; 
		}
		if(this.typeInput.compareTo("prise de main") == 0) { // si cst un input de prise de tour 
			this.matcher = this.patternChoixPrendreMain.matcher(this.input);
			if(matcher.matches()) return true; 
		}
		if(this.typeInput.compareTo("coordonnées") == 0) { // si cst un input de coordonnées
			this.matcher = this.patternCoordonnees.matcher(this.input);
			if(matcher.matches()) return true; 
		}
		return false;
	}

}
