package it.unicam.ids.loyaltyplatform.PuntoVendita;

import it.unicam.ids.loyaltyplatform.Account.models.Account;
import it.unicam.ids.loyaltyplatform.PF.ProgrammaFedeltaService;
import it.unicam.ids.loyaltyplatform.PF.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.PuntoVendita.models.PuntoVendita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PuntoVenditaService {

    private final PuntoVenditaRepository puntoVenditaRepository;
    private final ProgrammaFedeltaService programmaFedeltaService;

    @Autowired
    public PuntoVenditaService(PuntoVenditaRepository puntoVenditaRepository, ProgrammaFedeltaService programmaFedeltaService){
        this.puntoVenditaRepository = puntoVenditaRepository;
        this.programmaFedeltaService = programmaFedeltaService;
    }

    public PuntoVendita findPVById(int id){
        return puntoVenditaRepository.findPuntoVenditaById(id);
    }
    public List<PuntoVendita> findAll(){
        return puntoVenditaRepository.findAll();
    }
   public List<ProgrammaFedelta> getProgrammiAttivi(int pvId){
       return programmaFedeltaService.findActivebyPvId(pvId);
    }

    public PuntoVendita addPuntoVendita(PuntoVendita pv){
        return puntoVenditaRepository.save(pv);
    }

}
