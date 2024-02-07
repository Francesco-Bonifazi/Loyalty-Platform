package it.unicam.ids.loyaltyplatform.dashboard;

import it.unicam.ids.loyaltyplatform.account.models.Account;
import it.unicam.ids.loyaltyplatform.account.models.Utente;
import it.unicam.ids.loyaltyplatform.pf.models.PFType;
import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @PostMapping("/newpuntovendita")
    public ResponseEntity<?> registraPuntoVendita(@RequestParam("account_id") int account_id, @RequestBody PuntoVendita puntoVendita){
        try{
            return new ResponseEntity<>(dashboardService.registraPuntoVendita(account_id, puntoVendita), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/viewpuntovendita")
    public ResponseEntity<?> cercaPuntoVendita(@RequestParam("nome") String nome){
        try{
            return new ResponseEntity<>(dashboardService.cercaPuntoVendita(nome), HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/newprogram")
    public ResponseEntity<?> creaProgramma(@RequestParam("account_id") int account_id, @RequestParam("PFType") PFType pfType){
        try{
            return new ResponseEntity<>(dashboardService.creaProgrammaFedelta(account_id, pfType), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editprogram")
    public <T extends ProgrammaFedelta> ResponseEntity<?> modificaProgramma(@RequestParam("account_id") int account_id, @RequestBody T programmaFedelta){
        try{
            return new ResponseEntity<>(dashboardService.modificaProgrammaFedelta(programmaFedelta, account_id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/newtessera")
    public ResponseEntity<?> iscrizionePF(@RequestParam("account_id") int account_id, @RequestParam("pf_id") int pf_id){
        try{
            return new ResponseEntity<>(dashboardService.iscrizionePF(account_id, pf_id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removetessera")
    public ResponseEntity<?> rimuoviIscrizione(@RequestParam("account_id") int account_id, @RequestParam int pf_id){
        try{
            dashboardService.rimuoviIscrizionePF(account_id, pf_id);
            return new ResponseEntity<>("Removed!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/viewiscritti")
    public ResponseEntity<?> visualizzaClienti(@RequestParam("pf_id") int pf_id){
        try{
            return new ResponseEntity<>(dashboardService.visualizzaClienti(pf_id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/newutente")
    public ResponseEntity<?> registraUtente(@RequestBody Utente utente){
        try{
            return new ResponseEntity<>(dashboardService.registraCliente(utente), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeutente")
    public ResponseEntity<?> rimuoviUtente(@RequestParam("account_id") int account_id){
        try{
            dashboardService.rimuoviUtente(account_id);
            return new ResponseEntity<>("Removed!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateaccount")
    public <T extends Account> ResponseEntity<?> updateAccount(@RequestBody T account){
        try{
            return new ResponseEntity<>(dashboardService.updateAccount(account), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/viewinfo")
    public <T extends Account> ResponseEntity<?> visualizzaInfo(@RequestParam("account_id") int account_id){
        try{
            return new ResponseEntity<>(dashboardService.visualizzaInfoCliente(account_id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/viewdipendenti")
    public ResponseEntity<?> visualizzaDipendenti(@RequestParam("pv_id") int pv_id){
        try{
            return new ResponseEntity<>(dashboardService.visualizzaDipendenti(pv_id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/viewfattura/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> visualizzaFattura(@PathVariable int id){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=fattura.pdf");
        try {
            return new ResponseEntity<>(dashboardService.visualizzaFattura(id), headers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/viewfatture/{pv_id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> visualizzaFatture(@PathVariable int pv_id){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=fattura.pdf");
        try {
            return new ResponseEntity<>(dashboardService.visualizzaFatture(pv_id), headers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/viewcatalogo/{programma_id}")
    public ResponseEntity<?> visualizzaCatalogo(@PathVariable int programma_id){
        try{
            return new ResponseEntity<>(dashboardService.visualizzaPremi(programma_id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
