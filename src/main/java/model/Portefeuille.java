package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created with IntelliJ IDEA.
 * User: thiergeo
 * Date: 27/11/13
 * Time: 12:44
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Portefeuille {
    @Id
    @GeneratedValue
    private int id;
    
    //@Embedded // Pas sur
    @OneToMany(mappedBy="portefeuille")
    private List<Action> actions;
    
    public Portefeuille() {
    }

    public int getId() {
        return id;
    }
    
    public float getValeur() {
    	float value = 0;
    	for (Action a : actions) {
    		value += a.getValue();
    	}
    	return value;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
