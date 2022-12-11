/**
 * La classe de test du jeu Fanorona
 * @author Kiady
 *
 */
import java.util.Scanner;
public class Test {

	

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			System.out.println("Bienvenue dans Fanorona le jeu \n");
			
			// initialiser les Joueurs 
			Joueur j1 = new Joueur();
			Joueur j2 = new Joueur();
		
			// initialise le Jeu (avec le plateau et les Joueurs)
			Jeu jeu = new Jeu(j1, j2);		
			// ici, le plateau ainsi que les deux Joueurs sont déjà initialisés
			
			System.out.println("OK les joueurs , à vous maintenant de jouer !\\n ");

			// afficher le plateau avec les équipes et les joueurs ("Joueur 1, vous êtes l'équipe des pions Ronds")
			// "Joueur 2, vous êtes l'équipe des pions Croix" par exemple 
			System.out.println(jeu);
			
			// grande boucle du jeu : tant qu'il reste encore des Pions d'une même équipe sur le plateau
			while(jeu.getPlateau().getNbPions().get("ronds") != 0 || jeu.getPlateau().getNbPions().get("croix") != 0) {
				
			
				// demander au Joueur qui a la main de choisir un de ces Pions (vérification canChoose du Pion selon la case)
				String typePion = jeu.getTourActuel().isRoundSide() ? "Ronds" : "Croix"  ;
				System.out.println(jeu.getTourActuel().getPseudo()+ ", Vous avez la main, veuillez sélectionner un de vos Pions "+ typePion +" : [x,y] (x lettre de la ligne et y numéro de la colonne)");
				Scanner inp = new Scanner(System.in); // ouverture scanner mouvements
				String coordonnees = inp.nextLine();
				Input xyInput = new Input(coordonnees, "coordonnées");
				while(!xyInput.checkInputIsValid()) { // redemande à chaque fois que les coordonnées ne sont pas valides
					System.out.println("coordonnées invalides, veuillez entrer les coordonnées du Pion comprises sur le plateau et dans le format [A,1] par exemple.");
					coordonnees = inp.nextLine();
					xyInput = new Input(coordonnees,"coordonnées");
				}
				//coordonnées OK
				
				//Vérification choix
				Mouvement mouvActuel = new Mouvement(jeu.getTourActuel(), jeu.getPlateau()); // le mouvement que veut faire le Joueur qui a la main actuelle sur le plateau actuel
				while(!mouvActuel.canChoose(xyInput) || !xyInput.checkInputIsValid()) { // vérifie si le Joueur peut choisir ce Pion à ces coordonnées
					// le joueur ne peut pas sélectionner ce Pion
					System.out.println("Vous ne pouvez pas sélectionner cette Case, veuillez ressaisir les coordonnées d'un de vos Pions "+ typePion +", que vous voulez sélectionner comprises sur le plateau et dans le format [A,1] par exemple.");
					coordonnees = inp.nextLine();
					xyInput = new Input(coordonnees,"coordonnées");
				}
				// choix pion OK
				
				
				//* // si choix OK, demander où il veut déplacer son Pion (vérification canMove du Pion selon la case)
				// reboucle si mouvement non OK
				System.out.println(jeu.getTourActuel().getPseudo()+ ", Où voulez-vous déplacer votre Pion "+ typePion +" depuis "+xyInput.getInput()+"? : [x,y] (x lettre de la ligne et y numéro de la colonne)");
				inp = new Scanner(System.in); // ouverture scanner mouvements
				coordonnees = inp.nextLine();
				xyInput = new Input(coordonnees, "coordonnées");
				int count = 0; // un compteur pour éviter que le Joueur soit coincé à essayer de déplacer un Pion enclavé
				while(!xyInput.checkInputIsValid() || !mouvActuel.canMove(xyInput)) { // redemande à chaque fois que les coordonnées ne sont pas valides
					if(count > 3) {
						break;
					}
					System.out.println("coordonnées ou Case invalides, veuillez entrer les coordonnées du Pion comprises sur le plateau et dans le format [A,1] par exemple.");
					coordonnees = inp.nextLine();
					xyInput = new Input(coordonnees,"coordonnées");
					count++;
				}
				if(count > 3) { // CAS EXTRÊME D'ENCLAVEMENT, LE JOUEUR DOIT CHOISIR UN PION QUI PEUT SE DEPLACER MAINTENANT
					// re-choisir un Pion de départ
					System.out.println("Vous êtes peut-être enclavé. Veuillez re-choisir un Pion "+ typePion +" valide et qui peut se déplacer cette fois");
					inp = new Scanner(System.in); // ouverture scanner mouvements
					coordonnees = inp.nextLine();
					xyInput = new Input(coordonnees, "coordonnées");
					while(!xyInput.checkInputIsValid()) { // redemande à chaque fois que les coordonnées ne sont pas valides
						System.out.println("coordonnées invalides, veuillez entrer les coordonnées du Pion comprises sur le plateau et dans le format [A,1] par exemple.");
						coordonnees = inp.nextLine();
						xyInput = new Input(coordonnees,"coordonnées");
					}
					//coordonnées OK
					
					//Vérification choix
					while(!mouvActuel.canChoose(xyInput) || !xyInput.checkInputIsValid()) { // vérifie si le Joueur peut choisir ce Pion à ces coordonnées
						// le joueur ne peut pas sélectionner ce Pion
						System.out.println("Vous ne pouvez pas sélectionner cette Case, veuillez ressaisir les coordonnées d'un de vos Pions "+ typePion +", que vous voulez sélectionner comprises sur le plateau et dans le format [A,1] par exemple.");
						coordonnees = inp.nextLine();
						xyInput = new Input(coordonnees,"coordonnées");
					}
					// choix pion OK
					
					
					//* // si choix OK, demander où il veut déplacer son Pion (vérification canMove du Pion selon la case)
					// reboucle si mouvement non OK
					System.out.println(jeu.getTourActuel().getPseudo()+ ", Cette fois-ci, où voulez-vous déplacer votre Pion "+ typePion +" depuis "+xyInput.getInput()+"? : [x,y] (x lettre de la ligne et y numéro de la colonne)");
					inp = new Scanner(System.in); // ouverture scanner mouvements
					coordonnees = inp.nextLine();
					xyInput = new Input(coordonnees, "coordonnées");
					while(!xyInput.checkInputIsValid() || !mouvActuel.canMove(xyInput)) { // redemande à chaque fois que les coordonnées ne sont pas valides
						System.out.println("coordonnées ou Case invalides, veuillez entrer les coordonnées du Pion comprises sur le plateau et dans le format [A,1] par exemple.");
						coordonnees = inp.nextLine();
						xyInput = new Input(coordonnees,"coordonnées");
					}
				}
				count = 0; // on remet les pendules à l'heure
				
				
				
				// Déplacement OK
				mouvActuel.makeMove(); // fait le déplacement (le switch de case du pion, les captures percussions/aspirations sur des axes, la màj de la liste de pions aspirés) 
				
				// On réaffiche le plateau après le mouvv
				System.out.println(jeu);
				
				while(mouvActuel.getPionsCaptures() != null && mouvActuel.getPionsCaptures().size() > 0) { // si a fait une capture, peut se redéplacer
					System.out.println(jeu.getTourActuel().getPseudo()+ ", Vous avez fait une capture magnifique! Pour cela vous pouvez encore déplacer votre dernier Pion déplacé , Où voulez vous le déplacer?  : [x,y] (x lettre de la ligne et y numéro de la colonne)");
					inp = new Scanner(System.in); // ouverture scanner mouvements
					coordonnees = inp.nextLine();
					xyInput = new Input(coordonnees, "coordonnées");
					while(!xyInput.checkInputIsValid() || !mouvActuel.canMove(xyInput)) { // redemande à chaque fois que les coordonnées ne sont pas valides
						System.out.println("coordonnées invalides, veuillez entrer les coordonnées du Pion dans le format [A,1] par exemple.");
						coordonnees = inp.nextLine();
						xyInput = new Input(coordonnees,"coordonnées");
					}
					// Déplacement OK
//					mouvActuel.setCaseDepart(new Case(null,,));
					mouvActuel.makeMove(); // fait le déplacement (le switch de case du pion, les captures percussions/aspirations sur des axes, la màj de la liste de pions aspirés) 
					mouvActuel.setPionsCaptures(null); // on remet à null la liste de pion(s) capturé(s)
					
				}
				// On réaffiche le plateau après le mouvv
				System.out.println(jeu);
				// Fin 1 déplacement / capture multiple 
				// Passage de main et reboucle 
				jeu.setTourActuel(j2);
				
				
			}
			
			jeu.vainqueur(); // déclaration du vainqueur		
			
			// rejouer?
			
		}

	}


