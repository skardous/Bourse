package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import util.NegativeActionNumberException;

@Entity
public class Action {

	/**
	 * ID d'une action
	 */
	@Id
	@GeneratedValue
	private int id;
	
	/**
	 * La société qui détient l'action
	 */
	@ManyToOne
	private Societe societe;

	/**
	 * Le portefeuille dans lequel se trouve l'action
	 */
	@ManyToOne
	private Portefeuille portefeuille;

	/**
	 * Le nombre d'actions
	 */
	private int number;
	
	/**
	 * La valeur d'achat
	 */
	private double valeurAchat; 

	/**
	 * Les gains potentiels en vendant l'action
	 */
	private String gains; 

	public Action() {

	}

	public Action(int number, Societe societe, Portefeuille portefeuille) {
		super();
		this.number = number;
		this.societe = societe;
		this.portefeuille = portefeuille;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public Portefeuille getPortefeuille() {
		return portefeuille;
	}

	public void setPortefeuille(Portefeuille portefeuille) {
		this.portefeuille = portefeuille;
	}

	public float getTotalValue() {
		return Float.parseFloat(this.societe.getValeur()) * this.number;
	}

	public void sell(int number) throws NegativeActionNumberException {
		if ((this.number - number) < 0) {
			throw new NegativeActionNumberException();
		}
		this.number = this.number - number;
	}

	@Override
	public String toString() {
		return "Action [societe=" + societe + ", portefeuille=" + portefeuille
				+ ", number=" + number + "]";
	}

	public double getValeurAchat() {
		return valeurAchat;
	}

	public void setValeurAchat(double valeurAchat) {
		this.valeurAchat = valeurAchat;
	}
	
	public String getGains() {
		gains = String.valueOf(Double.parseDouble(societe.getValeur()) - valeurAchat);
		return gains;
	}
	
	public double getGainsNombre() {
		return Double.parseDouble(societe.getValeur()) - valeurAchat;
	}

	public void setGains(String gains) {
		this.gains = gains;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
