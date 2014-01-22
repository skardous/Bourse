package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Confiance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String intitule;

	public Confiance(int i, String string) {
		this.id = i;
		this.intitule = string;
	}
	
	public Confiance() {}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	@Override
	public String toString() {
		return "Confiance [id=" + id + ", intitule=" + intitule + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
