package ejb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.context.FacesContext;

import org.omg.CORBA.UnknownUserException;

import service.ClientService;
import service.ConfianceService;
import service.LoginService;
import model.Client;
import model.Compte;
import model.Confiance;
import model.Portefeuille;
import model.Utilisateur;

@Stateful
public class ClientBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ClientService clientService;

	@EJB
	private ConfianceService confianceService;

	public void createClient(Client client) {
		Compte c = new Compte();
		Portefeuille p = new Portefeuille();
		client.setCompte(c);
		client.setPortefeuille(p);
		Confiance conf = confianceService.find(1);
		client.setConfiance(conf);
		clientService.create(client);
	}
	
	public void updateclient(Client selectedClient) {
		clientService.update(selectedClient);		
	}

	public List<Client> getClientsList() {
		List<Client> clientsList = new ArrayList<Client>();
		clientsList = clientService.findWithNamedQuery(Client.ALL);
		return clientsList;
	}
	
	public enum ConfList {
		normal, privilégié;
	}

	

}
