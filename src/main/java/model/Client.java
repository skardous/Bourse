package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = Client.ALL, query = "SELECT c FROM Client c "),
		@NamedQuery(name = Client.TOTAL, query = "SELECT COUNT(c) FROM Client c"),
		@NamedQuery(name= Client.findClientByLogin, query="SELECT c " +
				"FROM Client c " +
      			"WHERE c.username = :login")})
public class Client extends Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String findClientByLogin = "Client.findClientByLogin";

	/**
	 * Le prénom du client
	 */
	private String name;
	/**
	 * Le nom du client
	 */
	private String lastName;
	/**
	 * Le portefeuille du client
	 */
	@OneToOne(cascade=CascadeType.ALL)
	private Portefeuille portefeuille;
	/**
	 * Le compte du client
	 */
	@OneToOne(cascade=CascadeType.ALL)
	private Compte compte;
	
	private Confiance confiance;

	public final static String ALL = "Client.populateClients";
	public final static String TOTAL = "Client.countClientsTotal";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Portefeuille getPortefeuille() {
		return portefeuille;
	}

	public void setPortefeuille(Portefeuille portefeuille) {
		this.portefeuille = portefeuille;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", lastName=" + lastName
				+ ", portefeuille=" + portefeuille + ", compte=" + compte
				+ ", id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}

	public Confiance getConfiance() {
		return confiance;
	}

	public void setConfiance(Confiance confiance) {
		this.confiance = confiance;
	}

}
