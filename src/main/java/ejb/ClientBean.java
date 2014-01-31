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

	/**
	 * Service permettant de réaliser des opérations CRUD
	 * sur un client.
	 */
	@EJB
	private ClientService clientService;

	/**
	 * Service permettant de réaliser des opérations CRUD
	 * sur la confiance.
	 */
	@EJB
	private ConfianceService confianceService;

	/**
	 * Créé un nouveau client si le login n'a pas déjà été pris.
	 * @param client
	 * 	Le client à ajouter à l'application.
	 * @throws AlreadyTakenLoginException
	 * 	Le login a déjà été pris.
	 */
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
	
	/**
	 * Met à jour le client spécifié.
	 * @param selectedClient
	 * 	Le client à mettre à jour.
	 */
	public void updateclient(Client selectedClient) {
		clientService.update(selectedClient);		
	}

	/**
	 * Retourne une liste de tous les clients de l'application.
	 * @return
	 * 	Une liste de clients
	 */
	@SuppressWarnings("unchecked")
	public List<Client> getClientsList() {
		List<Client> clientsList = new ArrayList<Client>();
		clientsList = clientService.findWithNamedQuery(Client.ALL);
		return clientsList;
	}
	
	/**
	 * L'ensemble des niveaux de confiance possibles.
	 */
	public enum ConfList {
		normal, privilegie;
	}

}
