package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Speculation {

	/**
	 * ID de la spéculation
	 */
	@Id
	@GeneratedValue
	private int id;
	
	/**
	 * La société cible de la spéculation
	 */
	@ManyToOne
	private Societe societe;

	/**
	 * Le portefeuille dans lequel se trouve la spéculation
	 */
	@ManyToOne
	private Portefeuille portefeuille;
	
	/**
	 * La valeur à la vente de la spéculation
	 */
	private double valeurVente;

	/**
	 * Le nombre de spéculations
	 */
	private int number;

	public Speculation() {
	}

	public Speculation(int number, double valeurVente, Societe societe, Portefeuille portefeuille) {
		super();
		this.valeurVente = valeurVente;
		this.number = number;
		this.societe = societe;
		this.portefeuille = portefeuille;
	}
	

	public double getTotalValue() {
		return Double.parseDouble(this.societe.getValeur()) * this.number;
	}
	

	public double getGains() {
		return (this.valeurVente * this.number) - (getTotalValue());
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

	public double getValeurVente() {
		return valeurVente;
	}

	public void setValeurVente(double valeurVente) {
		this.valeurVente = valeurVente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
