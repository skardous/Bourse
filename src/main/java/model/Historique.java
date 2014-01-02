package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Historique")
public class Historique {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(targetEntity = model.Societe.class)
	private Societe societe;

	private String date;

	private String ouverture;

	private String plus_bas;

	private String plus_haut;

	private String fermeture;

	private String volume;

	private String adj_fermeture;

	public Historique() {

	}

	public Historique(String date, String ouverture, String plus_bas,
			String plus_haut, String fermeture, String volume,
			String adj_fermeture) {
		super();
		this.date = date;
		this.ouverture = ouverture;
		this.plus_bas = plus_bas;
		this.plus_haut = plus_haut;
		this.fermeture = fermeture;
		this.volume = volume;
		this.adj_fermeture = adj_fermeture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOuverture() {
		return ouverture;
	}

	public void setOuverture(String ouverture) {
		this.ouverture = ouverture;
	}

	public String getPlus_bas() {
		return plus_bas;
	}

	public void setPlus_bas(String plus_bas) {
		this.plus_bas = plus_bas;
	}

	public String getPlus_haut() {
		return plus_haut;
	}

	public void setPlus_haut(String plus_haut) {
		this.plus_haut = plus_haut;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getAdj_fermeture() {
		return adj_fermeture;
	}

	public void setAdj_fermeture(String adj_fermeture) {
		this.adj_fermeture = adj_fermeture;
	}

	public String getFermeture() {
		return fermeture;
	}

	public void setFermeture(String fermeture) {
		this.fermeture = fermeture;
	}

}
