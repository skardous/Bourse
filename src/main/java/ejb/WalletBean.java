package ejb;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.activation.UnknownObjectException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import org.omg.CORBA.UnknownUserException;

import model.Action;
import model.Portefeuille;
import model.Societe;
import service.WalletService;
import utils.CSVRequests;

@Stateful
public class WalletBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * EJB d'accès à la persistance
	 */
	@EJB
	private WalletService service;

	public void orderBuy(Portefeuille p, String code, String se, int number) throws UnknownObjectException, MalformedURLException, IOException {
		CSVRequests req = new CSVRequests();
		List<Societe> list = req.getCompaniesBySE(se);
		if (list.isEmpty()) {
			throw new UnknownObjectException("Nom de bourse incorrect ou données irrécupérables");
		}
		Societe societe = null;
		for (Societe s : list) {
			if (s.getCode().equals(code)) {
				societe = s;
			}
		}
		if (societe == null) {
			throw new UnknownObjectException("code incorrect");
		}
		
		Action a = new Action(Float.parseFloat(societe.getValeur()), societe, p);
		for (int i = 0; i < number; i++) {
			p.getActions().add(a);			
		}
		service.update(p);
	}
	
	public void orderSell(Portefeuille p, String code, int number) {
//		c.debiter(montant);
//		service.update(c);
	}
	

}
