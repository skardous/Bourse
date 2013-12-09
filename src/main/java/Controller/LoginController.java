package Controller;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;

@Entity(name = "LoginController")
@ManagedBean
public class LoginController {
	private String username;
	private String password;
	
	public String login() {
		System.out.println("username : "+username);
		return "/index.xhtml";
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
