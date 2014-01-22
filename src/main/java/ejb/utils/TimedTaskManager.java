package ejb.utils;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import model.Confiance;
import service.ConfianceService;
import ejb.ClientBean.ConfList;

@Singleton
@Startup
public class TimedTaskManager {

	@EJB
	CSVRequests requests;
	
	@EJB
	ConfianceService confianceService;
	

	@Schedule(second = "0", minute = "*/1", hour = "*")
	public void updateDB() throws MalformedURLException, IOException {
		System.out.println("updatedb");
		requests.updateDatabase();
	}
	
	@PostConstruct
	public void initDB() {
		for (ConfList confiance : ConfList.values()) {
			Confiance conf = new Confiance(confiance.ordinal() + 1, confiance.name());
			if (confianceService.find(confiance.ordinal() + 1) == null) {
				confianceService.create(conf);
			}
		}
//		Confiance c = new Confiance(1, "normal");
//		Confiance p = new Confiance(2, "privilegié"); 
//		if (confianceService.find(1) == null) {
//			confianceService.create(c);
//		}
//		if (confianceService.find(2) == null) {
//			confianceService.create(p);
//		} 
		System.out.println("initDB");
	} 

}