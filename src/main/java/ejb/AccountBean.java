package ejb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import model.Compte;
import service.AccountService;
import util.NegativeSoldException;

@Stateful
public class AccountBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * EJB d'accès à la persistance du compte.
	 */
	@EJB
	private AccountService service;
		

	/**
	 * Crédite le compte d'un montant spécifié.
	 * @param c
	 * 	Le compte à créditer
	 * @param montant
	 * 	Le montant à créditer
	 */
	public void crediter(Compte c, double montant) {
		c.crediter(montant);
		service.update(c);
	}
	
	/**
	 * Débite le compte du montant spécifié si il contient
	 * assez d'argent. Lance une exception si ce n'est pas
	 * le cas.
	 * @param c
	 * 	Le compte à débiter
	 * @param d
	 * 	Le montant à débiter
	 * @throws NegativeSoldException
	 * 	Le solde du compte passe en négatif après la transaction.
	 */
	public void debiter(Compte c, double d) throws NegativeSoldException {		
		c.debiter(d);
		service.update(c);
	}

	/**
	 * Ferme le compte spécifié définitivement.
	 * @param compte
	 * 	Le compte à fermer.
	 */
	public void clore(Compte compte) {
		compte.close();
		service.update(compte);
	}
	

}
