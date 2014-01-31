package controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Compte;
import util.NegativeSoldException;
import ejb.AccountBean;
import ejb.SessionBean;

@ManagedBean
@ViewScoped
public class AccountController implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * La session correspondant à l'utilisateur courant.
	 */
	@ManagedProperty(value = "#{sessionController.session}")
	private SessionBean session;

	/**
	 * Accès au compte de l'utilisateur en session.
	 */
	@ManagedProperty(value = "#{sessionController.session.connectedUser.compte}")
	private Compte compte;

	/**
	 * Le bean correspondant à un compte.
	 */
	@EJB
	private AccountBean bean;

	/**
	 * La valeur du crédit.
	 */
	private Integer creditValue;
	
	/**
	 * La valeur du débit.
	 */
	private Integer debitValue;

	/**
	 * Crédite le compte de l'utilisateur en session d'un montant creditValue.
	 * Redirige vers la page du compte de l'utilisateur courant.
	 * @throws IOException
	 */
	public void crediter() throws IOException {
		bean.crediter(compte, creditValue);
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("/Bourse/account/account.xhtml");
	}

	/**
	 * Débite le compte de l'utilisateur en session d'un montant debitValue.
	 * Affiche un message d'erreur si le compte n'a pas assez d'argent.
	 * Redirige vers la page du compte de l'utilisateur courant.
	 * @throws IOException
	 */
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

	/**
	 * Ferme le compte de l'utilisateur en session, puis le déconnecte.
	 * Redirige vers la page d'accueil.
	 * @throws IOException
	 */
	public void clore() throws IOException {
		bean.clore(compte);
		session.logout();
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
