package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import model.Utilisateur;

@Stateless
public class LoginService extends DataAccessService<Utilisateur>{
	    
    public LoginService(){
        super(Utilisateur.class);
    }   
    
    /**
     * Retourne une liste d'utilisateurs correspondant aux login et mot de passe
     * renseignés.
     * @param username
     * 	Le login d'un utilisateur
     * @param password
     * 	Le mot de passe d'un utilisateur
     * @return
     * 	Une liste d'utilisateurs
     */
    @SuppressWarnings("unchecked")
	public List<Utilisateur> tryLogin(String username, String password) {
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("pass", password);
    	parameters.put("name", username);
    	return this.findWithNamedQuery(Utilisateur.findForLogin, parameters);    	
    }
}