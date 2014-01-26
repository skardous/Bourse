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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * EJB stockant la session
	 */
	@EJB
	private SessionBean session;
	
	@EJB
	private CSVRequests requester;

	private String username;
	private String password;

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
