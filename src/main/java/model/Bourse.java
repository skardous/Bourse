package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import java.util.List;


@Entity
@NamedQueries({
	  @NamedQuery(name=Bourse.findSEByName,
	              query="SELECT b " +
	                    "FROM Bourse b " +
	            		"WHERE b.nom = :name")
	})
public class Bourse {

	public final static String findSEByName = "Bourse.findsebyname";
	
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
