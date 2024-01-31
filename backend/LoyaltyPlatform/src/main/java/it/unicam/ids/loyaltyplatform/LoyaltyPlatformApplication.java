package it.unicam.ids.loyaltyplatform;

import it.unicam.ids.loyaltyplatform.account.AccountRepository;
import it.unicam.ids.loyaltyplatform.account.models.Amministratore;
import it.unicam.ids.loyaltyplatform.account.models.Cassiere;
import it.unicam.ids.loyaltyplatform.account.models.Titolare;
import it.unicam.ids.loyaltyplatform.account.models.Utente;
import it.unicam.ids.loyaltyplatform.puntovendita.PuntoVenditaRepository;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LoyaltyplatformApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(LoyaltyplatformApplication.class, args);
		AccountRepository accountRepository = configurableApplicationContext.getBean(AccountRepository.class);
		PuntoVenditaRepository puntoVenditaRepository = configurableApplicationContext.getBean(PuntoVenditaRepository.class);
		PasswordEncoder passwordEncoder = configurableApplicationContext.getBean(PasswordEncoder.class);

		Utente utente = new Utente("Francesco", "Bonifazi", "francesco.bonifazi@studenti.unicam.it", "3333635592", passwordEncoder.encode("1234"));
		Cassiere cassiere = new Cassiere("Vittorio", "Tidei", "vittorio.tidei@studenti.unicam.it", "3333635592", passwordEncoder.encode("1234"));
		Amministratore amministratore = new Amministratore("Tizio", "Caio", "tizio.caio@studenti.unicam.it", "3333635592", passwordEncoder.encode("1234"));
		Titolare titolare = new Titolare("Mario", "Rossi", "mario.rossi@studenti.unicam.it", "3333635592", passwordEncoder.encode("1234"));

		accountRepository.save(utente);
		accountRepository.save(cassiere);
		accountRepository.save(amministratore);
		accountRepository.save(titolare);

		PuntoVendita puntoVendita = new PuntoVendita("Unicam", "unicam@unicam.it", "3333635592", "Camerino");
		puntoVenditaRepository.save(puntoVendita);

	}

}
