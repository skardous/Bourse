package controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ejb.AccountBean;
import service.AccountService;
import model.Compte;

@ManagedBean
@ViewScoped
public class AccountController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{sessionController.session.connectedUser.compte}")
	private Compte compte;

	@EJB
	private AccountBean bean;

	private Integer creditValue;
	private Integer debitValue;

	public void crediter() throws IOException {
		bean.crediter(compte, creditValue);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/Bourse/account/account.xhtml");
	}
	
	public void debiter() throws IOException {
		bean.debiter(compte, debitValue);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/Bourse/account/account.xhtml");
	}

	public Integer getCreditValue() {
		return creditValue;
	}

	public void setCreditValue(Integer creditValue) {
		this.creditValue = creditValue;
	}

	public Integer getDebitValue() {
		return debitValue;
	}

	public void setDebitValue(Integer debitValue) {
		this.debitValue = debitValue;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	

}
