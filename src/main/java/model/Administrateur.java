package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrateur
 * 
 */
@Entity
public class Administrateur extends Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;

	public Administrateur() {
		super();
	}

}
