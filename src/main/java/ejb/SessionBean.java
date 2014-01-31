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

	private static final long serialVersionUID = 1L;

	/**
	 * Le modèle Utilisateur correspondant à l'utilisateur connecté.
	 */
	private Utilisateur connectedUser;

	/**
	 * EJB d'accès à la persistance
	 */
	@EJB
	private LoginService service;

	/**
	 * Lance le processus de connexion d'un utilisateur à partir
	 * de son login et de son mot de passe.
	 * @param username
	 * 	Le login de l'utilisateur
	 * @param password
	 * 	Le mot de passe de l'utilisateur
	 * @throws UnknownUserException
	 * 	L'utilisateur n'a pas été trouvé.
	 * @throws IOException
	 * @throws AccountClosedException
	 * 	Le compte en question est fermé.
	 */
	public void login(String username, String password)
			throws UnknownUserException, IOException, AccountClosedException {
		List<Utilisateur> list = service.tryLogin(username, password);
		if (list.isEmpty()) {
			throw new UnknownUserException();
		} else {
			if (list.get(0) instanceof Client){
				System.out.println("isclient");
				if (!((Client)list.get(0)).getCompte().isOpen()) {
					throw new AccountClosedException();
				} else {
					connectedUser = list.get(0);
				}				
			} else {
				connectedUser = list.get(0);
			}
		}
	}

	/**
	 * Déconnecte l'utilisateur courant.
	 */
	public void logout() {
		connectedUser = null;		
	}

	public Utilisateur getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(Utilisateur connectedUser) {
		this.connectedUser = connectedUser;
	}

}
