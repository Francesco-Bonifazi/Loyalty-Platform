package it.unicam.ids.loyaltyplatform;

import it.unicam.ids.loyaltyplatform.account.AccountRepository;
import it.unicam.ids.loyaltyplatform.account.models.Amministratore;
import it.unicam.ids.loyaltyplatform.account.models.Cassiere;
import it.unicam.ids.loyaltyplatform.account.models.Titolare;
import it.unicam.ids.loyaltyplatform.account.models.Utente;
import it.unicam.ids.loyaltyplatform.dashboard.DashboardService;
import it.unicam.ids.loyaltyplatform.fattura.FatturaService;
import it.unicam.ids.loyaltyplatform.fattura.models.Fattura;
import it.unicam.ids.loyaltyplatform.pf.models.PFType;
import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaPunti;
import it.unicam.ids.loyaltyplatform.premio.PremioService;
import it.unicam.ids.loyaltyplatform.premio.models.PremioPunti;
import it.unicam.ids.loyaltyplatform.puntovendita.PuntoVenditaRepository;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import it.unicam.ids.loyaltyplatform.tessera.TesseraRepository;
import it.unicam.ids.loyaltyplatform.tessera.models.Tessera;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class LoyaltyplatformApplication {

	public static void main(String[] args) {
		
		//Rimuovere prima di iniziare con un nuovo Loyalty Platform

		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(LoyaltyplatformApplication.class, args);
		AccountRepository accountRepository = configurableApplicationContext.getBean(AccountRepository.class);
		PuntoVenditaRepository puntoVenditaRepository = configurableApplicationContext.getBean(PuntoVenditaRepository.class);
		TesseraRepository tesseraRepository = configurableApplicationContext.getBean(TesseraRepository.class);
		DashboardService dashboardService = configurableApplicationContext.getBean(DashboardService.class);
		PasswordEncoder passwordEncoder = configurableApplicationContext.getBean(PasswordEncoder.class);
		FatturaService fatturaService = configurableApplicationContext.getBean(FatturaService.class);
		PremioService premioService = configurableApplicationContext.getBean(PremioService.class);

		Utente utente = new Utente("Francesco", "Bonifazi", "francesco.bonifazi@studenti.unicam.it", "3333635592", passwordEncoder.encode("1234"));
		Cassiere cassiere = new Cassiere("Vittorio", "Tidei", "vittorio.tidei@studenti.unicam.it", "3333635592", passwordEncoder.encode("1234"));
		Amministratore amministratore = new Amministratore("Tizio", "Caio", "tizio.caio@studenti.unicam.it", "3333635592", passwordEncoder.encode("1234"));
		Titolare titolare = new Titolare("Mario", "Rossi", "mario.rossi@studenti.unicam.it", "3333635592", passwordEncoder.encode("1234"));


		accountRepository.save(utente);
		accountRepository.save(cassiere);
		accountRepository.save(amministratore);
		accountRepository.save(titolare);

		puntoVenditaRepository.save(new PuntoVendita("Unicam", "unicam.it", "3333635592", "Camerino"));
		PuntoVendita pv = puntoVenditaRepository.findPuntoVenditaByNome("Unicam");
		titolare.setPuntoVendita(pv);
		cassiere.setPuntoVendita(pv);
		amministratore.setPuntoVendita(pv);
		accountRepository.save(titolare);
		accountRepository.save(cassiere);
		accountRepository.save(amministratore);


		pv.addProgrammaFedelta(PFType.PFPUNTI);
		puntoVenditaRepository.save(pv);
		tesseraRepository.save(new Tessera(utente, pv.getProgrammiFedelta().getLast()));

		Fattura fattura = new Fattura(100, new Date(),new Date(),pv);
		Fattura fattura1 = new Fattura(200, new Date(),new Date(),pv);
		Fattura fattura2 = new Fattura(300, new Date(),new Date(),pv);
		fatturaService.addFattura(fattura);
		fatturaService.addFattura(fattura1);
		fatturaService.addFattura(fattura2);

	}

}
