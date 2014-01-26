package ejb;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.activation.UnknownObjectException;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import model.Action;
import model.Compte;
import model.Portefeuille;
import model.Societe;
import model.Speculation;
import service.CompanyService;
import service.ShareService;
import service.SpeculationService;
import service.WalletService;
import util.NegativeActionNumberException;
import util.NegativeSoldException;

/**
 * @author Simon KARDOUS
 * 
 *         Bean de gestion du portefeuille : appelle les services réalisant la
 *         persistance
 */
@Stateful
public class WalletBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static double COMMISSION = 0.5/100;

	/**
	 * EJB d'accès à la persistance
	 */
	@EJB
	private WalletService service;

	@EJB
	private CompanyService compService;

	@EJB
	private AccountBean accService;

	@EJB
	private ShareService shareService;
	
	@EJB
	private SpeculationService specService;

	/**
	 * Méthode appelant les différents services pour réaliser un achat
	 * d'actions.
	 * 
	 * @param p
	 *            Portefeuille du client
	 * @param code
	 * @param number
	 * @param cpt
	 * @throws UnknownObjectException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws NegativeSoldException
	 *             Le solde du compte courant n'est pas suffisant pour réaliser
	 *             l'achat
	 */
	public void orderBuy(Portefeuille p, String code, int number, Compte cpt)
			throws UnknownObjectException, MalformedURLException, IOException,
			NegativeSoldException {

		Societe c = compService.findCompanyByCode(code);

		accService.debiter(cpt, Double.parseDouble(c.getValeur()) * number);
		accService.debiter(cpt, (COMMISSION * Double.parseDouble(c.getValeur())) * number);

		boolean alreadyOwner = false;
		for (Action a : p.getActions()) {
			if (a.getSociete().getCode().equals(code)) { //Si nous possédons déjà des actions de cette société
				alreadyOwner = true;
				a.setNumber(a.getNumber() + number);
				shareService.update(a);
			}
		}
		
		if (!alreadyOwner) {
			Action a = new Action(number, c, p);
			p.getActions().add(a);
		}
		
		for (Speculation spec : p.getSpectulations()) {
			if (spec.getSociete().getCode().equals(code)) { //Si nous avons spéculé à la baisse sur cette action
				spec.setNumber(spec.getNumber() - number);
				if (spec.getNumber() == 0) {
					p.getSpectulations().remove(spec);
					specService.delete(spec.getId());
					break;
				} else {
					if (spec.getNumber() < 0) {
						p.getSpectulations().remove(spec);
						specService.delete(spec.getId());
						number = number + spec.getNumber();
					} else {
						specService.update(spec);
						break;
					}
				}
				
			}
		}

		service.update(p);
	}

	/**
	 * @param p
	 * @param share
	 * @param number
	 * @param cpt	Compte qui sera débité
	 * @throws NegativeActionNumberException
	 * @throws NegativeSoldException 
	 */
	public void orderSale(Portefeuille p, Action share, int number, Compte cpt)
			throws NegativeActionNumberException, NegativeSoldException {
		Action s = p.getActions().get(p.getActions().indexOf(share));
		s.sell(number);
		if (s.getNumber() == 0) {
			p.getActions().remove(s);
			shareService.delete(s.getSociete().getCode());
		} else {
			shareService.update(s);
		}
		service.update(p);
		accService.crediter(cpt, Double.parseDouble(s.getSociete().getValeur())
				* number);
		accService.debiter(cpt, (COMMISSION * Double.parseDouble(s.getSociete().getValeur())) * number);
	}

	public void orderSpeculativeSale(Portefeuille p,
			String code, int number, Compte cpt) throws NumberFormatException, NegativeSoldException {
		Societe c = compService.findCompanyByCode(code);
		
		accService.debiter(cpt, (COMMISSION * Double.parseDouble(c.getValeur())) * number);
		Speculation s = new Speculation(number,Double.parseDouble(c.getValeur()), c, p);
		p.getSpectulations().add(s);		

		service.update(p);	
		
	}

	

}
