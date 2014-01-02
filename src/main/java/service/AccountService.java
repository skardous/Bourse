package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import model.Compte;
import model.Utilisateur;

@Stateless
public class AccountService extends DataAccessService<Compte>{
	    
    public AccountService(){
        super(Compte.class);
    }   
    
    
}