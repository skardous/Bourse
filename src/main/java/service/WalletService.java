package service;

import javax.ejb.Stateless;

import model.Portefeuille;

@Stateless
public class WalletService extends DataAccessService<Portefeuille>{
    
    public WalletService(){
        super(Portefeuille.class);
    }

}
