package ejb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import model.Administrateur;
import service.AdminService;

@Stateful
public class AdminBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Service permettant de réaliser des opérations CRUD
	 * pour l'administrateur.
	 */
	@EJB
	private AdminService adminService;

	/**
	 * Créé un nouvel administrateur dans l'application.
	 * @param admin
	 * 	L'administrateur à ajouter
	 */
	public void createAdmin(Administrateur admin) {
		adminService.create(admin);		
	}

}
