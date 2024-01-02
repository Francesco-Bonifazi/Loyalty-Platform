package it.unicam.ids.loyaltyplatform.PuntoVendita;



import it.unicam.ids.loyaltyplatform.PF.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.PuntoVendita.models.PuntoVendita;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pv")
public class PuntoVenditaController {

    private PuntoVenditaService puntoVenditaService;

    public PuntoVenditaController(PuntoVenditaService puntoVenditaService){
        this.puntoVenditaService = puntoVenditaService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PuntoVendita>> getPVById(){
        return new ResponseEntity<>(puntoVenditaService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<PuntoVendita> getPVById(@PathVariable("id") int id){
        return new ResponseEntity<>(puntoVenditaService.findPVById(id), HttpStatus.OK);
    }



    @GetMapping("/programmiAttivi/{id}")
    public ResponseEntity<List<ProgrammaFedelta>> getProgrammiAttivi(@PathVariable("id") int id){
        return new ResponseEntity<>(puntoVenditaService.getProgrammiAttivi(id), HttpStatus.OK);
    }

}
