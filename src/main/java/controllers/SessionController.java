package controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.omg.CORBA.UnknownUserException;

import utils.CSVRequests;
import ejb.SessionBean;

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

	private String username;
	private String password;

	public void login() throws IOException {
		try {
			session.login(username, password);
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Bourse/index.xhtml");
			CSVRequests.updateDatabase();
		} catch (UnknownUserException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("utilisateur inconnu"));
		}
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
