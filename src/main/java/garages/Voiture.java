package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	@Override
	public String toString() {
		return "Voiture{" + "immatriculation='" + immatriculation + '}';
	}

	/**
	 * Fait rentrer la voiture au garage
	 * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		// Et si la voiture est déjà dans un garage ?

		for (Stationnement stationne : myStationnements){
			if (stationne.estEnCours()){
				throw new java.lang.Exception("La voiture est déjà dans un garage");
			}
		}

		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	/**
	 * Fait sortir la voiture du garage
	 * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		//throw new UnsupportedOperationException("Pas encore implémenté");
		// TODO: Implémenter cette méthode
		// Trouver le dernier stationnement de la voiture
		// Terminer ce stationnement
		boolean bool = false;
		for (Stationnement stationne : myStationnements){
			if (stationne.estEnCours()){
				stationne.terminer();
				bool = true;
			}
		}
		if (bool == false){
			throw new java.lang.Exception("La voiture n'est pas en cours de stationnement");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Voiture voiture = (Voiture) o;
		return Objects.equals(immatriculation, voiture.immatriculation) && Objects.equals(myStationnements, voiture.myStationnements);
	}

	@Override
	public int hashCode() {
		return Objects.hash(immatriculation, myStationnements);
	}


	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		// TODO: Implémenter cette méthode
		//throw new UnsupportedOperationException("Pas encore implémenté");

		Set<Garage> lesGarages = new HashSet<Garage>();

		for (Stationnement stationne : myStationnements){
			lesGarages.add(stationne.getGarage());
		}
		return lesGarages;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		// TODO: Implémenter cette méthode
		//throw new UnsupportedOperationException("Pas encore implémenté");
		// Vrai si le dernier stationnement est en cours
		boolean bool = false;
		for (Stationnement stationne : myStationnements) {
			if (stationne.estEnCours()) {
				bool = true;
			}
		}
		return bool;
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des
	 * dates d'entrée / sortie dans ce garage
	 * <br>
	 * Exemple :
	 * 
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		// TODO: Implémenter cette méthode
		//throw new UnsupportedOperationException("Pas encore implémenté");
		Set<Garage> lesGarages = this.garagesVisites();
		for (Garage g : lesGarages){
			out.println(g + ":");
			for (Stationnement stat : myStationnements){
				if (g == stat.getGarage()){
					out.println("    " + stat);
				}
			}
		}
	}

}
