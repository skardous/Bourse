package ejb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import model.Compte;
import service.AccountService;

@Stateful
public class AccountBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * EJB d'accès à la persistance
	 */
	@EJB
	private AccountService service;

	public void crediter(Compte c, int montant) {
		c.crediter(montant);
		service.update(c);
	}
	
	public void debiter(Compte c, int montant) {
		c.debiter(montant);
		service.update(c);
	}
	

}
