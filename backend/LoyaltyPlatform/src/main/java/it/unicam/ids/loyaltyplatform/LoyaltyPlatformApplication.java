package it.unicam.ids.loyaltyplatform;

import ch.qos.logback.core.net.SyslogOutputStream;
import it.unicam.ids.loyaltyplatform.Account.*;
import it.unicam.ids.loyaltyplatform.Account.models.*;
import it.unicam.ids.loyaltyplatform.PF.ProgrammaFedeltaRepository;
import it.unicam.ids.loyaltyplatform.PF.ProgrammaFedeltaService;
import it.unicam.ids.loyaltyplatform.PF.models.*;
import it.unicam.ids.loyaltyplatform.PuntoVendita.PuntoVenditaRepository;
import it.unicam.ids.loyaltyplatform.PuntoVendita.models.PuntoVendita;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class LoyaltyPlatformApplication {




	public static void main(String[] args) {

		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(LoyaltyPlatformApplication.class, args);
		AccountRepository accountRepository = configurableApplicationContext.getBean(AccountRepository.class);
		PuntoVenditaRepository pvRepository = configurableApplicationContext.getBean(PuntoVenditaRepository.class);
		ProgrammaFedeltaRepository pfRepository = configurableApplicationContext.getBean(ProgrammaFedeltaRepository.class);

		Account utente = new Utente("Francesco", "Bonifazi", "francesco_bonifazi@studenti.unicam.it", "3333635592");
		Account cassiere = new Cassiere("Vittorio", "Tidei", "vittorio_tidei@studenti.unicam.it", "3209557894");
		accountRepository.save(utente);
		accountRepository.save(cassiere);

		PuntoVendita pv1 = new PuntoVendita("Marisol", "a@a.it", "3333333392","Marche");
		PuntoVendita pv2 = new PuntoVendita("B&B Bonifazi", "a@a.it", "3333333392","Marche");
		PuntoVendita pv3 = new PuntoVendita("RedBurger", "a@a.it", "3333333392","Marche");
		pvRepository.save(pv1);
		pvRepository.save(pv2);
		pvRepository.save(pv3);

		ProgrammaFedelta pf1 = new ProgrammaPunti(PFType.PFPUNTI, 1, 1, 1, pv1.getId());
		ProgrammaFedelta pf2 = new ProgrammaLivelli(PFType.PFLIVELLI,  1,  1,  1,pv1.getId());
		ProgrammaFedelta pf3 = new ProgrammaCashback(PFType.PFCASHBACK,  1,pv2.getId());
		ProgrammaFedelta pf4 = new ProgrammaVip(PFType.PFVIP,  1,  10,pv2.getId());
		pfRepository.save(pf1);
		pfRepository.save(pf2);
		pfRepository.save(pf3);
		pfRepository.save(pf4);

		Account titolare = new Titolare("Francesco","Bonifazi","a@A.it","3333635592");
		accountRepository.save(titolare);



	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
