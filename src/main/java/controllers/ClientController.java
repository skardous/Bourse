package controllers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Client;
import model.Compte;
import model.Portefeuille;
import service.ClientService;

@ManagedBean
@SessionScoped
public class ClientController implements Serializable {

	@EJB
	private ClientService das;
	private List<Client> clientsList;

	private Client client = new Client();

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ClientController() {
		System.out.println("appelCONST CTRL");
	}

	public void createclient() {
		Compte c = new Compte();
		Portefeuille p = new Portefeuille();
		client.setCompte(c);
		client.setPortefeuille(p);
		System.out.println(client.toString());
		das.create(client);
	}

	public List<Client> getClientsList() {
		if (clientsList == null) {
			clientsList = das.findWithNamedQuery(Client.ALL);			
		}
		System.out.println("clientlist");
		return clientsList;
	}

	public void setClientsList(List<Client> clientsList) {
		this.clientsList = clientsList;
	}
}
