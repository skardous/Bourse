package service;

import javax.ejb.Stateless;

import model.Confiance;

@Stateless
public class ConfianceService extends DataAccessService<Confiance>{
    
    public ConfianceService(){
        super(Confiance.class);
    }   
}
