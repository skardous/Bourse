package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thiergeo
 * Date: 27/11/13
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */

@Entity
@NamedQueries({
	  @NamedQuery(name=Bourse.findAllSE,
	              query="SELECT b " +
	                    "FROM Bourse b ")
	})
public class Bourse {

	public final static String findAllSE = "Bourse.findallse";
	
    @Id
    @GeneratedValue
    private int id;

    private String nom;
    @OneToMany(mappedBy="bourse")
    private List<Societe> societes;
    
    public Bourse() {}

    public Bourse(String nom) {
		this.nom = nom;
	}

	public List<Societe> getSocietes() {
        return societes;
    }

    public void setSocietes(List<Societe> societes) {
        this.societes = societes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }
}
