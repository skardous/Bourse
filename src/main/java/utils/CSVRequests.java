package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import service.SEService;
import model.Bourse;
import model.Societe;

public class CSVRequests {

	public static List<Societe> getCompaniesBySE(String se) throws MalformedURLException, IOException {
		List<Societe> companyList = new ArrayList<Societe>();
		String line = "";
		String cvsSplitBy = "\",\"";

		InputStream input = new URL(
				"http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange="
						+ se + "&render=download").openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(input,
				"UTF-8"));
		int lineNbr = 0;
		
		while ((line = br.readLine()) != null) {
			lineNbr++;
			if (lineNbr != 1) {
				String[] company = line.split(cvsSplitBy);
				companyList.add(new Societe(company[1],
						company[0].substring(1), company[2]));
			}
		}
		br.close();
		return companyList;
	}
	
	public static void updateDatabase() throws MalformedURLException, IOException {
		SEService service = new SEService();
		Bourse b = service.getSEByName("NYSE");
		if (b == null) {
			b = new Bourse("NYSE");
		}
		b.setSocietes(getCompaniesBySE("NYSE"));
		service.update(b);
	}

}
