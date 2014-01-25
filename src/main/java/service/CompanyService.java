package service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import model.Portefeuille;
import model.Societe;


@Stateless
public class CompanyService extends DataAccessService<Portefeuille>{
    
    public CompanyService(){
        super(Portefeuille.class);
    }
    
    @SuppressWarnings("unchecked")
	public Societe findCompanyByCode(String code) {
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("code", code);
    	List<Societe> list = this.findWithNamedQuery(Societe.findByCode, parameters);
    	if (list.isEmpty()) {
    		return null;
    	}
    	return list.get(0);    	  
    }

}
