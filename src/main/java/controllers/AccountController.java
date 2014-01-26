package controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Client;
import model.Compte;
import model.Utilisateur;
import util.NegativeSoldException;
import ejb.AccountBean;
import ejb.SessionBean;

@ManagedBean
@ViewScoped
public class AccountController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{sessionController.session}")
	private SessionBean session;
	
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
		try {
			bean.debiter(compte, debitValue);
		} catch (NegativeSoldException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("vous n'avez pas assez d'argent"));
		}
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/Bourse/account/account.xhtml");
	}

	public void clore() throws IOException {
		System.out.println("cloture de compte...");
		bean.clore(compte);
		System.out.println("logout...");
		session.logout();
		System.out.println("redirection...");
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/Bourse/index.xhtml");
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

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}

}
