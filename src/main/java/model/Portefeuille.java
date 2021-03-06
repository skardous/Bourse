package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Portefeuille {
	/**
	 * ID du portefeuille
	 */
    @Id
    @GeneratedValue
    private int id;
    
    /**
     * La liste d'actions du portefeuille
     */
    @OneToMany(mappedBy="portefeuille") 
    private List<Action> actions;
    
    /**
     * La liste des spéculations du portefeuille
     */
    @OneToMany(mappedBy="portefeuille")
    private List<Speculation> speculations;
    
    public Portefeuille() {
    }

    public int getId() {
        return id;
    }
    
    /**
     * Retourne la valeur total du portefeuille
     * @throws NumberFormatException si le cours de la société est "n/a"
     * @return
     * 	La valeur total du portefeuille
     */
    public float getValeur() {
    	float value = 0;
    	for (Action a : actions) {
    		value += a.getNumber() * Float.parseFloat(a.getSociete().getValeur());
    	}
    	return value;
    }
    
    public List<Action> getActionsGroupedByCompany() {
    	Map<String, Action> group = new HashMap<String, Action>();  
    	  
    	for (Action a : actions) {  
    	    String company=a.getSociete().getCode();  
    	    if (group.containsKey(company)) {
    	        int number = group.get(company).getNumber();  
    	        double gains = group.get(company).getGainsNombre();
    	        number = number + a.getNumber();  
    	        gains = gains + a.getGainsNombre();
    	        Action tempact = new Action(number, a.getSociete(), this);
    	        tempact.setGains(String.valueOf(gains));
    	        tempact.setValeurAchat(Double.parseDouble(a.getSociete().getValeur()) - gains);
    	        group.put(company, tempact);  
    	    }else{  
    	    	group.put(company, a); 
    	    }    	  
    	}
    	
    	List<Action> list = new ArrayList<Action>();
    	for (Action ac : group.values()) {
    		list.add(ac);
    	}
    	
    	return list;
    }
    
    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

	public List<Speculation> getSpeculations() {
		return speculations;
	}

	public void setSpectulations(List<Speculation> speculations) {
		this.speculations = speculations;
	}


	public void setId(int id) {
		this.id = id;
	}
}
