package controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.omg.CORBA.UnknownUserException;

import util.AccountClosedException;
import ejb.SessionBean;
import ejb.utils.CSVRequests;

@ManagedBean
@SessionScoped
public class SessionController implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * EJB stockant la session
	 */
	@EJB
	private SessionBean session;

	/**
	 * EJB pour mettre à jour la base de données en temps réél.
	 */
	@EJB
	private CSVRequests requester;

	/**
	 * Le login de l'utilisateur
	 */
	private String username;
	
	/**
	 * Le mot de passe de l'utilisateur
	 */
	private String password;

	/**
	 * Ouvre une nouvelle session à partir des identifiants renseignés.
	 * @throws IOException
	 */
	public void login() throws IOException {
		try {
			session.login(username, password);
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Bourse/index.xhtml");
		} catch (UnknownUserException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Utilisateur inconnu"));
		} catch (AccountClosedException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ce compte a été fermé"));
		}
	}

	/**
	 * Ferme la session en cours.
	 * @throws IOException
	 */
	public void logout() throws IOException {
		session.logout();
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("/Bourse/index.xhtml");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}

}
