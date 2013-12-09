package Controller;


import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;

@Entity(name = "LoginController")
@ManagedBean
public class LoginController {
	private String username;
	private String password;
	
	public void login() {
		System.out.println("username : "+username);
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("list/societeList.xhtml");
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
