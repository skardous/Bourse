package ejb;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.context.FacesContext;

import org.omg.CORBA.UnknownUserException;

import service.LoginService;
import model.Utilisateur;

@Stateful
public class SessionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Utilisateur connectedUser;

	/**
	 * EJB d'accès à la persistance
	 */
	@EJB
	private LoginService service;

	public void login(String username, String password)
			throws UnknownUserException, IOException {
		List<Utilisateur> list = service.tryLogin(username, password);
		if (list.isEmpty()) {
			throw new UnknownUserException();
		} else {
			connectedUser = list.get(0);
		}
	}

	public void logout() throws IOException {
		connectedUser = null;
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("/Bourse/index.xhtml");
	}

	public Utilisateur getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(Utilisateur connectedUser) {
		this.connectedUser = connectedUser;
	}

}
