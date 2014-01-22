package service;

import javax.ejb.Stateless;

import model.Action;

@Stateless
public class ShareService extends DataAccessService<Action>{
	    
    public ShareService(){
        super(Action.class);
    }   
    
    
}