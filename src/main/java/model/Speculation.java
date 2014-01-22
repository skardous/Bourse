package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA. User: thiergeo Date: 27/11/13 Time: 12:47 To
 * change this template use File | Settings | File Templates.
 */
// Pas sur de ça
// @Embeddable
@Entity
public class Speculation {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Societe societe;

	@ManyToOne
	private Portefeuille portefeuille;
	
	private double valeurVente;

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

}
