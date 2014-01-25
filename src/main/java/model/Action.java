package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import util.NegativeActionNumberException;

/**
 * Created with IntelliJ IDEA. User: thiergeo Date: 27/11/13 Time: 12:47 To
 * change this template use File | Settings | File Templates.
 */
// Pas sur de ça
// @Embeddable
@Entity
public class Action {

	@Id
	@ManyToOne
	private Societe societe;

	@ManyToOne
	private Portefeuille portefeuille;

	private int number;
	
	private double valeurAchat; 

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

}
