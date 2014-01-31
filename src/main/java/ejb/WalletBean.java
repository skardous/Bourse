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
 * Bean de gestion du portefeuille : appelle les services réalisant la
 * persistance
 */
@Stateful
public class WalletBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Le montant de la commission.
	 */
	private static double COMMISSION = 0.5 / 100;

	/**
	 * EJB d'accès à la persistance d'un portefeuille.
	 */
	@EJB
	private WalletService service;

	/**
	 * EJB d'accès à la persistance d'une société.
	 */
	@EJB
	private CompanyService compService;

	/**
	 * EJB d'accès à la persistance d'un compte.
	 */
	@EJB
	private AccountBean accService;

	/**
	 * EJB d'accès à la persistance d'une action.
	 */
	@EJB
	private ShareService shareService;

	/**
	 * EJB d'accès à la persistance d'une spéculation.
	 */
	@EJB
	private SpeculationService specService;

	/**
	 * Méthode appelant les différents services pour réaliser un achat
	 * d'actions.
	 * 
	 * @param p
	 *            Portefeuille du client
	 * @param code
	 * 	Le code de la société
	 * @param number
	 * 	Le nombre d'actions à acheter
	 * @param cpt
	 * 	Le compte responsable de l'achat
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
		accService.debiter(cpt,
				(COMMISSION * Double.parseDouble(c.getValeur())) * number);

		Action a = new Action(number, c, p);
		a.setValeurAchat(Double.parseDouble(c.getValeur()));
		p.getActions().add(a);

		for (Speculation spec : p.getSpeculations()) {
			if (spec.getSociete().getCode().equals(code)) {
				spec.setNumber(spec.getNumber() - number);
				if (spec.getNumber() == 0) {
					p.getSpeculations().remove(spec);
					specService.delete(spec.getId());
					break;
				} else {
					if (spec.getNumber() < 0) {
						p.getSpeculations().remove(spec);
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
	 * Passe un ordre de vente en appelant les différents services responsables.
	 * @param p
	 * 	Le portefeuille du client
	 * @param share
	 * 	L'action à vendre
	 * @param number
	 * 	Le nombre d'actions à vendre
	 * @param cpt
	 *            Compte qui sera débité
	 * @throws NegativeActionNumberException
	 * 	Le nombre d'actions à vendre est négatif
	 * @throws NegativeSoldException
	 * 	Le solde du compte est négatif
	 */
	public void orderSale(Portefeuille p, Action share, int number, Compte cpt)
			throws NegativeActionNumberException, NegativeSoldException {
		Action s = p.getActions().get(p.getActions().indexOf(share));
		s.sell(number);
		if (s.getNumber() == 0) {
			p.getActions().remove(s);
			shareService.delete(s.getId());
		} else {
			shareService.update(s);
		}
		service.update(p);
		accService.crediter(cpt, Double.parseDouble(s.getSociete().getValeur())
				* number);
		accService.debiter(cpt,
				(COMMISSION * Double.parseDouble(s.getSociete().getValeur()))
				* number);
	}

	/**
	 * Passe un ordre de vente à la spéculation.
	 * @param p
	 * 	Le portefeuille du client
	 * @param code
	 * 	Le code de la société
	 * @param number
	 * 	Le nombre d'actions à vendre
	 * @param cpt
	 * 	Le compte responsable de la vente
	 * @throws NumberFormatException
	 * @throws NegativeSoldException
	 * 	Le solde du compte est négatif
	 */
	public void orderSpeculativeSale(Portefeuille p, String code, int number,
			Compte cpt) throws NumberFormatException, NegativeSoldException {
		Societe c = compService.findCompanyByCode(code);
		accService.debiter(cpt,
				(COMMISSION * Double.parseDouble(c.getValeur())) * number);
		Speculation s = new Speculation(number, Double.parseDouble(c
				.getValeur()), c, p);
		p.getSpeculations().add(s);
		service.update(p);
	}

}
