package service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import model.Client;


@Stateless
public class ClientService extends DataAccessService<Client>{
    
    public ClientService(){
        super(Client.class);
    }   
    
    @SuppressWarnings("unchecked")
	public List<Client> getClientsByLogin(String login) {
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("login", login);
    	List<Client> list = this.findWithNamedQuery(Client.findClientByLogin, parameters);
    	
    	return list;    	    
    }
}
