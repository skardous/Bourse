package ejb;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import model.Client;
import model.Utilisateur;

import org.omg.CORBA.UnknownUserException;

import service.LoginService;
import util.AccountClosedException;

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
			throws UnknownUserException, IOException, AccountClosedException {
		List<Utilisateur> list = service.tryLogin(username, password);
		if (list.isEmpty()) {
			throw new UnknownUserException();
		} else {
			System.out.println("plop lp o");
			if (list.get(0) instanceof Client){
				System.out.println("isclient");
				if (!((Client)list.get(0)).getCompte().isOpen()) {
					System.out.println("compte fermé");
					throw new AccountClosedException();
				} else {
					connectedUser = list.get(0);
				}				
			} else {
				connectedUser = list.get(0);
			}
		}
	}

	public void logout() throws IOException {
		connectedUser = null;		
	}

	public Utilisateur getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(Utilisateur connectedUser) {
		this.connectedUser = connectedUser;
	}

}
