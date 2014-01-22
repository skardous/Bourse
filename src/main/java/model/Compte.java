package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import util.NegativeSoldException;

/**
 * Created with IntelliJ IDEA.
 * User: thiergeo
 * Date: 27/11/13
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
@Entity(name = "Compte")
public class Compte {
	@Id
    @GeneratedValue
    private int id;
    private double solde;
    
    public Compte() {
    	this.solde = 0;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void crediter(double value) {
        this.setSolde(this.solde + value);
    }

    public void debiter(double value) throws NegativeSoldException {
        if (this.solde - value < 0) {
        	throw new NegativeSoldException();
        }
        this.setSolde(this.solde - value);
    }
}
