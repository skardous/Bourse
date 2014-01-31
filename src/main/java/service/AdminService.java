package service;

import javax.ejb.Stateless;

import model.Administrateur;

@Stateless
public class AdminService extends DataAccessService<Administrateur> {
	public AdminService(){
		super(Administrateur.class);
	}   
}
