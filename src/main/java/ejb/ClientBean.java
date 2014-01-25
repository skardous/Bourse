package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import model.Client;
import model.Compte;
import model.Confiance;
import model.Portefeuille;
import service.ClientService;
import service.ConfianceService;
import util.AlreadyTakenLoginException;

@Stateful
public class ClientBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ClientService clientService;

	@EJB
	private ConfianceService confianceService;

	public void createClient(Client client) throws AlreadyTakenLoginException {
		if (clientService.getClientsByLogin(client.getUsername()).isEmpty()) {
			Compte c = new Compte();
			Portefeuille p = new Portefeuille();
			client.setCompte(c);
			client.setPortefeuille(p);
			Confiance conf = confianceService.find(1);
			client.setConfiance(conf);
			clientService.create(client);
		} else {
			throw new AlreadyTakenLoginException();
		}
	}
	
	public void updateclient(Client selectedClient) {
		clientService.update(selectedClient);		
	}

	@SuppressWarnings("unchecked")
	public List<Client> getClientsList() {
		List<Client> clientsList = new ArrayList<Client>();
		clientsList = clientService.findWithNamedQuery(Client.ALL);
		return clientsList;
	}
	
	public enum ConfList {
		normal, privilégié;
	}

	

}
