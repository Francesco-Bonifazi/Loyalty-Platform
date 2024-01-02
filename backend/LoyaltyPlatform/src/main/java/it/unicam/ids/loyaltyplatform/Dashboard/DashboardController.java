package it.unicam.ids.loyaltyplatform.Dashboard;

import it.unicam.ids.loyaltyplatform.Account.models.Account;
import it.unicam.ids.loyaltyplatform.Account.models.Utente;
import it.unicam.ids.loyaltyplatform.PF.models.PFType;
import it.unicam.ids.loyaltyplatform.PF.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.PuntoVendita.models.PuntoVendita;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;


    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }



    @PostMapping("/addPF")
    public ResponseEntity<ProgrammaFedelta> addPF(@RequestParam("type") PFType type, @RequestParam("account_id") int accountId) {
        ProgrammaFedelta newPF;
        try {
            newPF = dashboardService.creaProgrammaFedeltà(type, accountId);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(newPF, HttpStatus.CREATED);
    }

    @PostMapping("/addCliente")
    public ResponseEntity<Account> addCliente(@RequestBody Utente cliente, @RequestParam("account_id") int accountId) {
        Account newUtente;
        try {
            newUtente = dashboardService.registraCliente(cliente, accountId);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(newUtente, HttpStatus.CREATED);

    }

    @PutMapping("/edit")
    public ResponseEntity<ProgrammaFedelta> modPF(@RequestBody ProgrammaFedelta modPF, @RequestParam("account_id") int account_id){
        ProgrammaFedelta newPF;
        try {
            newPF = dashboardService.modificaProgrammaFedeltà(modPF, account_id);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newPF, HttpStatus.OK);

    }

    @PostMapping("/addPuntoVendita")
    public ResponseEntity<PuntoVendita> addPuntoVendita(@RequestParam("account_id") int accountId) {
        PuntoVendita newPV;
        try {
            newPV = dashboardService.registraPuntoVendita(new PuntoVendita(), accountId);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(newPV, HttpStatus.CREATED);

    }

}
