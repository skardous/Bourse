package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Historique;
import model.Societe;
import ejb.SEBean;
import ejb.SEBean.SE;

@ManagedBean
@ViewScoped
public class StockExController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private SEBean bean;

	/**
	 * Le StockExchange sélectionné.
	 */
	private String selectedSE;

	/**
	 * La liste de tous les StockExchange.
	 */
	private Map<String, String> listSE = new HashMap<String, String>();

	/**
	 * La liste des sociétés à partir d'un StockExchange.
	 */
	private List<Societe> companyList = new ArrayList<Societe>();
	
	private List<Societe> filteredCompanies;  

	/**
	 * La société sélectionnée.
	 */
	private Societe selectedCompany = new Societe();

	/**
	 * L'historique sélectionné.
	 */
	private List<Historique> selectedHistorique = new ArrayList<Historique>();

	public StockExController() {
		for (SE se : SE.values()) {
			listSE.put(se.name(), se.name());
		}
	}

	/**
	 * Met à jour la liste des sociétés selon le StockExchange sélectionné.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void selectSE() throws MalformedURLException, IOException {
		companyList.clear();
		companyList = bean.getCompaniesBySE(selectedSE);
		filteredCompanies = bean.getCompaniesBySE(selectedSE);
		//companyList = requester.getCompaniesBySE(selectedSE);
	}

	/**
	 * Met à jour l'historique de la société sélectionnée.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void updateSelectedHistorique() throws MalformedURLException,
	IOException {
		selectedHistorique.clear();
		String line = "";
		String cvsSplitBy = ",";

		if (selectedCompany.getCode() != null) {
			final String authUser = "kardosim";
			final String authPassword = "cemen123";

			System.setProperty("http.proxyHost", "charon.olympe");
			System.setProperty("http.proxyPort", "3128");
			System.setProperty("http.proxyUser", authUser);
			System.setProperty("http.proxyPassword", authPassword);

			Authenticator.setDefault(
			  new Authenticator() {
			    public PasswordAuthentication getPasswordAuthentication() {
			      return new PasswordAuthentication(authUser, authPassword.toCharArray());
			    }
			  }
			);
			Calendar c = new GregorianCalendar();
			InputStream input = new URL(
					"http://ichart.finance.yahoo.com/table.csv?s="
							+ selectedCompany.getCode() + "&d="
							+ c.get(Calendar.MONTH) + "&e="
							+ c.get(Calendar.DAY_OF_MONTH) + "&f="
							+ c.get(Calendar.YEAR)
							+ "&g=d&a=7&b=19&c=2004&ignore=.csv").openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(input,
					"UTF-8"));
			int lineNbr = 0;
			while ((line = br.readLine()) != null) {
				lineNbr++;
				if (lineNbr != 1) {
					String[] history = line.split(cvsSplitBy);
					selectedHistorique.add(new Historique(history[0],
							history[1], history[2], history[3], history[4],
							history[5], history[6]));
				}
			}
			br.close();
		}
	}

	public String getSelectedSE() {
		return selectedSE;
	}

	public void setSelectedSE(String selectedSE) {
		this.selectedSE = selectedSE;
	}

	public Map<String, String> getListSE() {
		return listSE;
	}

	public void setListSE(Map<String, String> listSE) {
		this.listSE = listSE;
	}

	public List<Societe> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Societe> companyList) {
		this.companyList = companyList;
	}

	public Societe getSelectedCompany() {
		return selectedCompany;
	}

	public void setSelectedCompany(Societe selectedCompany) {
		this.selectedCompany = selectedCompany;
		try {
			this.updateSelectedHistorique();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Historique> getSelectedHistorique() {
		return selectedHistorique;
	}

	public void setSelectedHistorique(List<Historique> selectedHistorique) {
		this.selectedHistorique = selectedHistorique;
	}

	public List<Societe> getFilteredCompanies() {
		return filteredCompanies;
	}

	public void setFilteredCompanies(List<Societe> filteredCompanies) {
		this.filteredCompanies = filteredCompanies;
	}

}
