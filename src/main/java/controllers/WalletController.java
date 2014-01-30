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

	@ManagedProperty(value = "#{sessionController.session.connectedUser.portefeuille}")
	private Portefeuille wallet;

	@ManagedProperty(value = "#{sessionController.session.connectedUser.compte}")
	private Compte compte;

	@EJB
	private WalletBean bean;

	private String orderCompanyCode;
	private int orderNumber;
	private Action selectedShare;
	


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

	public void orderSale() throws IOException {
		try {
			bean.orderSale(wallet, selectedShare, orderNumber, compte);
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Bourse/wallet/orderSell.xhtml");
		} catch (NegativeActionNumberException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous ne possédez pas un tel nombre d'actions !!"));
		} catch (NegativeSoldException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous n'avez pas assez d'argent (commission bancaire)"));
		}		
	} 
	
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
