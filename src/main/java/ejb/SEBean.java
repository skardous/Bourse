package ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import model.Bourse;
import model.Societe;
import service.SEService;

@Stateful
public class SEBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * EJB d'accès à la persistance pour une bourse.
	 */
	@EJB
	private SEService service;

	/**
	 * Retourne une bourse à partir de son nom.
	 * @param name
	 * 	Le nom de la bourse à chercher
	 * @return
	 * 	Un modèle de type Bourse
	 */
	public Bourse getSEByName(String name) {
		return service.getSEByName(name);
	}
	
	/**
	 * Retourne une liste de sociétés à partir d'un
	 * nom de bourse spécifié.
	 * @param se
	 * 	Le nom de la bourse
	 * @return
	 * 	La liste de sociétés
	 */
	public List<Societe> getCompaniesBySE(String se) {
		return service.getCompaniesBySE(se);
	}
	
	/**
	 * L'ensemble des noms de bourse possibles. 
	 */
	public enum SE {
		NASDAQ, NYSE, AMEX;
	}

}
