package ejb.utils;

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

	/**
	 * EJB gérant la mise à jour des StockExchange en base de données.
	 * */
	@EJB
	CSVRequests requests;

	/**
	 * EJB gérant les opérations CRUD d'une confiance.
	 * */
	@EJB
	ConfianceService confianceService;

	/**
	 * Programme la mise à jour des StockExchange dans la base de données
	 * toutes les minutes.
	 * */
	@Schedule(second = "0", minute = "*/1", hour = "*")
	public void updateDB() {
		System.out.println("updatedb");
		try {
			requests.updateDatabase();
		} catch (Exception e) {			
			System.err
					.println("Impossible de mettre à jour les bourses : temps de réponse trop long");
		}
		System.out.println("endUpdateDB");
	}

	/**
	 * Initialise la base en créant les niveaux de confiance.
	 */
	@PostConstruct
	public void initDB() {
		for (ConfList confiance : ConfList.values()) {
			Confiance conf = new Confiance(confiance.ordinal() + 1,
					confiance.name());
			if (confianceService.find(confiance.ordinal() + 1) == null) {
				confianceService.create(conf); 
			}
		}
		System.out.println("initDB"); 
	}

}