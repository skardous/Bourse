package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import model.Bourse;

@Stateless
public class SEService extends DataAccessService<Bourse>{
	    
    public SEService() {
        super(Bourse.class);
    }   
    
    public Bourse getSEByName(String name) {
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("name", name);
    	System.out.println(Bourse.findAllSE);
    	this.findWithNamedQuery(Bourse.findAllSE, parameters);
    	List<Bourse> list = new ArrayList<Bourse>();
    	System.out.println(list);
    	return list.get(0);    	    
    }
}