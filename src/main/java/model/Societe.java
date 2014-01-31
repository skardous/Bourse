package model;

import java.util.List;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA. User: thiergeo Date: 27/11/13 Time: 13:29 To
 * change this template use File | Settings | File Templates.
 */

@Entity
@NamedQueries({ @NamedQuery(name = Societe.findByCode, query = "SELECT s "
		+ "FROM Societe s " + "WHERE s.code = :code") })
public class Societe {

	public final static String findByCode = "Societe.findbycode";

	/**
	 * Le code de la société
	 */
	@Id
	private String code;

	/**
	 * Le nom de la société
	 */
	private String nom;

	/**
	 * La valeur de la société
	 */
	private String valeur;

	/**
	 * L'ensemble des historiques de la société
	 */
	@OneToMany(targetEntity = model.Historique.class)
	private List<Historique> historique;

	/**
	 * L'ensemble des actions de la société
	 */
	@OneToMany(mappedBy = "societe")
	private List<Action> actions;
	
	/**
	 * L'ensemble des spéculations de la société
	 */
	@OneToMany(mappedBy = "societe")
	private List<Speculation> speculations;

	/**
	 * La bourse dans laquelle se trouve la société
	 */
	@ManyToOne
	private Bourse bourse;

	public Societe() {
	}

	public Societe(String nom, String code, String valeur, Bourse b) {
		this.bourse = b;
		this.nom = nom;
		this.code = code;
		this.valeur = valeur;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Bourse getBourse() {
		return bourse;
	}

	public void setBourse(Bourse bourse) {
		this.bourse = bourse;
	}

	public String getValeur() {
		return valeur;
	}

	public double getValeurNombre() {
		if (valeur.equals("n/a")) {
			return 0;
		}
		return Double.parseDouble(valeur);
	}
	
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	@Override
	public String toString() {
		return "Societe [nom=" + nom + ", code=" + code + ", valeur=" + valeur
				+ ", historique=" + historique + ", bourse=" + bourse + "]";
	}

	public List<Historique> getHistorique() {
		return historique;
	}

	public void setHistorique(List<Historique> historique) {
		this.historique = historique;
	}

}
