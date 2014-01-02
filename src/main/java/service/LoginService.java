package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import model.Client;
import model.Utilisateur;

@Stateless
public class LoginService extends DataAccessService<Utilisateur>{
	    
    public LoginService(){
        super(Utilisateur.class);
    }   
    
    public List<Utilisateur> tryLogin(String username, String password) {
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("pass", password);
    	parameters.put("name", username);
    	return this.findWithNamedQuery(Utilisateur.findForLogin, parameters);    	
    }
}