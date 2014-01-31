package service;

import javax.ejb.Stateless;

import model.Speculation;

@Stateless
public class SpeculationService extends DataAccessService<Speculation>{
	    
    public SpeculationService(){
        super(Speculation.class);
    }   
    
}