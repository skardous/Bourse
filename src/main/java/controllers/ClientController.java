package controllers;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Client;
import model.Confiance;
import util.AlreadyTakenLoginException;
import ejb.ClientBean;
import ejb.ClientBean.ConfList;

@ManagedBean
@ViewScoped
public class ClientController implements Serializable  {

	private static final long serialVersionUID = 1L;

	/**
	 * Le bean du client.
	 */
	@EJB
	private ClientBean clientBean;

	/**
	 * La liste de tous les clients de l'application.
	 */
	private List<Client> clientsList;

	/**
	 * Un nouveau client qui peut être ajouté.
	 */
	private Client client = new Client();

	/**
	 * Le client selectionné.
	 */
	private Client selectedClient;

	/**
	 * Le niveau de confiance selectionné.
	 */
	private String selectedConfiance;

	/**
	 * L'ensemble des niveaux de confiances existants.
	 */
	private Map<String, String> listConfiance = new HashMap<String, String>();

	/**
	 * Initialisation de la liste des niveaux de confiance.
	 */
	public ClientController() {
		for (ConfList se : ConfList.values()) {
			listConfiance.put(se.name(), se.name());
		}
	}

	/**
	 * Modifie la confiance de l'utilisateur sélectionné par la confiance sélectionnée.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void selectConfiance() throws MalformedURLException, IOException {
		int id = ConfList.valueOf(selectedClient.getConfiance().getIntitule()).ordinal() + 1;
		String intitule = selectedClient.getConfiance().getIntitule();
		Confiance c = new Confiance(id, intitule);
		System.out.println("confiance selectionnée : " +c);
		selectedClient.setConfiance(c);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Créé un nouveau client et redirige vers la page de login. 
	 * @throws IOException
	 */
	public void createclient() throws IOException {
		try {
			clientBean.createClient(client);
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Bourse/login/login.xhtml");
		} catch (AlreadyTakenLoginException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Login déjà utilisé"));
		}

	}

	/**
	 * Met à jour le client sélectionné.
	 * @throws IOException
	 */
	public void updateclient() throws IOException {
		clientBean.updateclient(selectedClient);
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("/Bourse/list/clientList.xhtml");
	}

	public List<Client> getClientsList() {
		clientsList = clientBean.getClientsList();
		return clientsList;
	}

	public void setClientsList(List<Client> clientsList) {
		this.clientsList = clientsList;
	}

	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

	public String getSelectedConfiance() {
		return selectedConfiance;
	}

	public void setSelectedConfiance(String selectedConfiance) {
		this.selectedConfiance = selectedConfiance;
	}

	public Map<String, String> getListConfiance() {
		return listConfiance;
	}

	public void setListConfiance(Map<String, String> listConfiance) {
		this.listConfiance = listConfiance;
	}
}
