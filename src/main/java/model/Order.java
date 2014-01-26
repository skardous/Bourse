package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import util.NegativeActionNumberException;

@Entity
public class Order {

	@Id
	@ManyToOne
	private Societe societe;

	@ManyToOne
	private Portefeuille portefeuille;

	private int number;
	
	private double valeurAchat; 
		
	private String date;

	public Order() {

	}

	public Order(int number, Societe societe, Portefeuille portefeuille) {
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

	
	
	public double getValeurAchat() {
		return valeurAchat;
	}

	public void setValeurAchat(double valeurAchat) {
		this.valeurAchat = valeurAchat;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
