package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity(name = "Historique")
public class Historique {	
	
	@Id
	@GeneratedValue
	private int id;
	
	private Date date;
	
	private float ouverture;
	
	private float plus_bas;
	
	private float plus_haut;
	
	private int volume;
	
	private float adj_fermeture;
	
	public Historique() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getOuverture() {
		return ouverture;
	}

	public void setOuverture(float ouverture) {
		this.ouverture = ouverture;
	}

	public float getPlus_bas() {
		return plus_bas;
	}

	public void setPlus_bas(float plus_bas) {
		this.plus_bas = plus_bas;
	}

	public float getPlus_haut() {
		return plus_haut;
	}

	public void setPlus_haut(float plus_haut) {
		this.plus_haut = plus_haut;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public float getAdj_fermeture() {
		return adj_fermeture;
	}

	public void setAdj_fermeture(float adj_fermeture) {
		this.adj_fermeture = adj_fermeture;
	}

	

}
