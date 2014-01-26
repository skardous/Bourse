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
    @Id
    @GeneratedValue
    private int id;
    
    @OneToMany(mappedBy="portefeuille") 
    private List<Action> actions;
    
    @OneToMany(mappedBy="portefeuille")
    private List<Speculation> spectulations;
    
    public Portefeuille() {
    }

    public int getId() {
        return id;
    }
    
    /**
     * @throws NumberFormatException si le cours de la société est "n/a"
     * @return
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
    	        double gains = Double.parseDouble(group.get(company).getGains());
    	        number = number + a.getNumber();  
    	        gains = gains + Double.parseDouble(a.getGains());
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

	public List<Speculation> getSpectulations() {
		return spectulations;
	}

	public void setSpectulations(List<Speculation> spectulations) {
		this.spectulations = spectulations;
	}


	public void setId(int id) {
		this.id = id;
	}
}
