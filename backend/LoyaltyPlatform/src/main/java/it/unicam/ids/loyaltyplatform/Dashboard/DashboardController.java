package it.unicam.ids.loyaltyplatform.dashboard;

import it.unicam.ids.loyaltyplatform.account.models.Account;
import it.unicam.ids.loyaltyplatform.account.models.Utente;
import it.unicam.ids.loyaltyplatform.pf.models.PFType;
import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import it.unicam.ids.loyaltyplatform.tessera.models.Tessera;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/healthcheck")
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>("Works!", HttpStatus.OK);
    }

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
            return new ResponseEntity<>(dashboardService.cercaPuntoVendita(nome), HttpStatus.OK);
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
    public ResponseEntity<?> rimuoviIscrizione(@RequestParam("account_id") int account_id, @RequestBody Tessera tessera){
        try{
            dashboardService.rimuoviIscrizione(account_id, tessera);
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

    @GetMapping("/viewinfo")
    public <T extends Account> ResponseEntity<?> visualizzaInfo(@RequestBody T account){
        try{
            return new ResponseEntity<>(dashboardService.visualizzaInfoCliente(account), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/viewinfo", params = {"account_id"})
    public <T extends Account> ResponseEntity<?> visualizzaInfo(@RequestParam("account_id") int account_id){
        try{
            return new ResponseEntity<>(dashboardService.visualizzaInfoCliente(account_id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/viewinfo", params = {"email"})
    public <T extends Account> ResponseEntity<?> visualizzaInfo(@RequestParam("email") String email){
        try{
            return new ResponseEntity<>(dashboardService.visualizzaInfoCliente(email), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
