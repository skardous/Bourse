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
	 * EJB d'accès à la persistance
	 */
	@EJB
	private SEService service;

	public Bourse getSEByName(String name) {
		return service.getSEByName(name);
	}
	
	public List<Societe> getCompaniesBySE(String se) {
		return service.getCompaniesBySE(se);
	}
	
	public enum SE {
		NASDAQ, NYSE, AMEX;
	}

}
