package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import model.Bourse;
import model.Societe;

@ManagedBean
public class BourseController {
	
	private List<Societe> societes;
	
	public BourseController() {
		
	}
	

	public List<Societe> getSocietes() {
		societes = new ArrayList<Societe>();
		Societe s = new Societe();
		Bourse b = new Bourse();
		b.setNom("NASDAQ");
		s.setBourse(b);
		s.setCode("CODE");
		s.setNom("nom");
		return societes;
	}

	public void setSocietes(List<Societe> societes) {
		this.societes = societes;
	}
	
	
	

}
