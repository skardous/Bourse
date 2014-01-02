package service;


import javax.ejb.Stateless;

import model.Client;


@Stateless
public class ClientService extends DataAccessService<Client>{
    
    public ClientService(){
        super(Client.class);
    }   
}
