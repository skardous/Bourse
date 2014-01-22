package service;


import javax.ejb.Stateless;

import model.Administrateur;
import model.Client;


@Stateless
public class AdminService extends DataAccessService<Administrateur>{
    
    public AdminService(){
        super(Administrateur.class);
    }   
}
