package ejb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import model.Administrateur;
import service.AdminService;

@Stateful
public class AdminBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private AdminService adminService;


	public void createAdmin(Administrateur admin) {
		adminService.create(admin);		
	}

}
