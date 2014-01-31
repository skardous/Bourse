package controllers;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.activation.UnknownObjectException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import util.NegativeActionNumberException;
import util.NegativeSoldException;
import model.Action;
import model.Compte;
import model.Portefeuille;
import model.Speculation;
import ejb.WalletBean;

@ManagedBean
@ViewScoped
public class WalletController implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Le portefeuille du client en session.
	 */
	@ManagedProperty(value = "#{sessionController.session.connectedUser.portefeuille}")
	private Portefeuille wallet;

	/**
	 * Le compte du client en session.
	 */
	@ManagedProperty(value = "#{sessionController.session.connectedUser.compte}")
	private Compte compte;

	/**
	 * Le bean du portefeuille.
	 */
	@EJB
	private WalletBean bean;

	/**
	 * Le code de la société du titre acheté
	 */
	private String orderCompanyCode;
	
	/**
	 * Le nombre d'ordres d'achat ou de vente passés.
	 */
	private int orderNumber;
	private Action selectedShare;
	
	/**
	 * Traite un ordre d'achat de titre d'une société. Redirige vers
	 * le récapitulatif du portefeuille en cas de succès.
	 * @throws UnknownObjectException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void orderBuy() throws UnknownObjectException,
			MalformedURLException, IOException {
		try {
			bean.orderBuy(wallet, orderCompanyCode, orderNumber, compte);
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Bourse/wallet/walletGlobal.xhtml");
		} catch (NegativeSoldException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous n'avez pas assez d'argent"));
		}
	}

	/**
	 * Traite la vente d'actions de la part du client.
	 * @throws IOException
	 */
	public void orderSale() throws IOException {
		try {
			bean.orderSale(wallet, selectedShare, orderNumber, compte);
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Bourse/wallet/orderSell.xhtml");
		} catch (NegativeActionNumberException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous ne possédez pas un tel nombre d'actions."));
		} catch (NegativeSoldException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous n'avez pas assez d'argent (commission bancaire)"));
		}		
	} 
	
	/**
	 * Traite la vente à la speculation d'actions.
	 * @throws IOException
	 */
	public void orderSpeculativeSale() throws IOException {
		try {
			bean.orderSpeculativeSale(wallet, orderCompanyCode,  orderNumber, compte);
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Bourse/wallet/orderBuy.xhtml");
		} catch (NegativeSoldException e) { 
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous n'avez pas assez d'argent (commission bancaire)"));
		}		
	} 
	
	/**
	 * Retourne le montant total des obligations d'achat.
	 * @return
	 * 	Le montant total des obligations d'achat
	 */
	public double getObligationsTotal() {
		double total = 0;
		for (Speculation s : wallet.getSpeculations()) {
			total += s.getTotalValue() - s.getGains();
		}
		return total;
	}

	public boolean hasObligations() {
		return getObligationsTotal() > 0;
	}
	
	public String getOrderCompanyCode() {
		return orderCompanyCode;
	}

	public void setOrderCompanyCode(String orderCompanyCode) {
		this.orderCompanyCode = orderCompanyCode;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Action getSelectedShare() {
		return selectedShare;
	}

	public void setSelectedShare(Action selectedShare) {
		this.selectedShare = selectedShare;
	}

	public Portefeuille getWallet() {
		return wallet;
	}
	
	public void setWallet(Portefeuille wallet) {
		this.wallet = wallet;
	}
}
