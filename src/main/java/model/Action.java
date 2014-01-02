package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: thiergeo
 * Date: 27/11/13
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */
// Pas sur de ça
//@Embeddable
@Entity
public class Action {
	@Id
    @GeneratedValue
    private int id;
    private float value;
    
    @ManyToOne
    private Societe societe;

    @ManyToOne
    private Portefeuille portefeuille;
    
    public Action() {
    	
    }
    
    public Action(float value, Societe societe,
			Portefeuille portefeuille) {
		super();
		this.value = value;
		this.societe = societe;
		this.portefeuille = portefeuille;
	}

	public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
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
    
}
