package ejb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import model.Bourse;
import model.Societe;
import service.CompanyService;
import service.SEService;
import ejb.SEBean.SE;


@Stateful
public class CSVRequests {

	/**
	 * EJB réalisant les opérations CRUD d'une bourse.
	 */
	@EJB
	SEService service;

	/**
	 * EJB réalisant les opérations CRUD d'une société.
	 */
	@EJB
	CompanyService compService;

	/**
	 * Met à jour une bourse en base de données à partir de données collectées
	 * en temps réél.
	 * @param se
	 * 	La bourse mis à jour
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void updateSE(String se) throws MalformedURLException, IOException {
		System.out.println("updateSE :"+se);
		List<Societe> companyList = new ArrayList<Societe>();
		String line = "";
		String cvsSplitBy = "\",\"";
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

		InputStream input = new URL(
				"http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange="
						+ se + "&render=download").openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(input,
				"UTF-8"));
		int lineNbr = 0;
		Bourse b = service.getSEByName(se);
		if (b == null) {
			b = new Bourse(se);
		}
		System.out.println("startSEwhile");
		while ((line = br.readLine()) != null) {
			lineNbr++;
			if (lineNbr != 1) {
				String[] company = line.split(cvsSplitBy);
				companyList.add(new Societe(company[1],
						company[0].substring(1), company[2], b));
			}
		}
		System.out.println("endSEwhile");
		br.close();
		b.setSocietes(companyList);
		service.update(b);
		System.out.println("endUpdateSE");
	}

	/**
	 * Lance la mise à jour des bourses dans la base de données.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void updateDatabase() throws MalformedURLException, IOException{			
		for (SE se : SE.values()) {
			this.updateSE(se.name());
		}
	}

}
