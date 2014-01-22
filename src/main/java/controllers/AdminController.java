package controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ejb.AdminBean;
import ejb.ClientBean;
import model.Administrateur;
import model.Client;
import model.Compte;
import model.Portefeuille;
import service.ClientService;
import service.ConfianceService;

@ManagedBean
@ViewScoped
public class AdminController  {

	@EJB
	private AdminBean adminBean;

	private Administrateur admin = new Administrateur();
	
	


	public void createAdmin() throws IOException {
		adminBean.createAdmin(admin);
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("/Bourse/login/login.xhtml");
	}


	public Administrateur getAdmin() {
		return admin;
	}


	public void setAdmin(Administrateur admin) {
		this.admin = admin;
	}


}
