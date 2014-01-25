package service;

import javax.ejb.Stateless;

import model.Compte;

@Stateless
public class AccountService extends DataAccessService<Compte>{
	    
    public AccountService(){
        super(Compte.class);
    }   
    
    
}