package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import model.Bourse;
import model.Societe;

@Stateless
public class SEService extends DataAccessService<Bourse>{
	    
    public SEService() {
        super(Bourse.class);
    }   
    
    @SuppressWarnings("unchecked")
	public Bourse getSEByName(String name) {
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("name", name);
    	List<Bourse> list = this.findWithNamedQuery(Bourse.findSEByName, parameters);
    	if (list.isEmpty()) {
    		return null;
    	}
    	return list.get(0);    	    
    }

	public List<Societe> getCompaniesBySE(String se) {
		Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("name", se);
		Bourse b = (Bourse) this.findWithNamedQuery(Bourse.findSEByName, parameters).get(0);
		return b.getSocietes();
	}
}