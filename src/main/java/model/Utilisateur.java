package model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Utilisateurs
 * 
 */
@Entity
@Inheritance
@NamedQueries({
	  @NamedQuery(name=Utilisateur.findForLogin,
	              query="SELECT u " +
	                    "FROM Utilisateur u " +
	                    "WHERE u.username = :name AND " +
	                    "      u.password = :pass")
	})
public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID de l'utilisateur
	 */
	@Id
	@GeneratedValue
	protected Integer id;

	/**
	 * Login de l'utilisateur
	 */
	protected String username;
	/**
	 * Mot de passe de l'utilisateur
	 */
	protected String password;
	
	public final static String findForLogin = "Utilisateur.findforlogin";

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (getId() != null ? getId().hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		} else if (!(obj instanceof Utilisateur)) {
			return false;
		} else if (((Client) obj).getId().equals(this.getId())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAdmin() {
		return (this instanceof Administrateur);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", username=" + username
				+ ", password=" + password + "]";
	}
	

}
