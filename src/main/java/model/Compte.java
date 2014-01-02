package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private float solde;
    
    public Compte() {
    	this.solde = 0;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public void crediter(float value) {
        this.setSolde(this.solde + value);
    }

    public void debiter(float value) {
        // TODO Précondition pour vérifier > 0
        this.setSolde(this.solde - value);
    }
}
