package controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Administrateur;
import ejb.AdminBean;

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
