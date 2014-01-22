package ejb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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

	@EJB
	SEService service;
	
	@EJB
	CompanyService compService;
	
	public void updateSE(String se) throws MalformedURLException, IOException {
		System.out.println("updateSE :"+se);
		List<Societe> companyList = new ArrayList<Societe>();
		String line = "";
		String cvsSplitBy = "\",\"";

		InputStream input = new URL(
				"http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange="
						+ se + "&render=download").openStream();
		System.out.println("1");
		BufferedReader br = new BufferedReader(new InputStreamReader(input,
				"UTF-8"));
		System.out.println("2");
		int lineNbr = 0;
		Bourse b = service.getSEByName(se);
		System.out.println("3");
		if (b == null) {
			b = new Bourse(se);
		}
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			lineNbr++;
			if (lineNbr != 1) {
				String[] company = line.split(cvsSplitBy);
				companyList.add(new Societe(company[1],
						company[0].substring(1), company[2], b));
			}
		}
		br.close();
		b.setSocietes(companyList);
		service.update(b);
	}
	
	public void updateDatabase() throws MalformedURLException, IOException {			
		for (SE se : SE.values()) {
			this.updateSE(se.name());
		}
	}

}