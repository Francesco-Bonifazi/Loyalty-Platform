package it.unicam.ids.loyaltyplatform.Dashboard;

import it.unicam.ids.loyaltyplatform.Account.AccountService;
import it.unicam.ids.loyaltyplatform.Account.models.Account;
import it.unicam.ids.loyaltyplatform.Account.models.Ruolo;
import it.unicam.ids.loyaltyplatform.Account.models.Titolare;
import it.unicam.ids.loyaltyplatform.Account.models.Utente;
import it.unicam.ids.loyaltyplatform.PF.ProgrammaFedeltaService;
import it.unicam.ids.loyaltyplatform.PF.models.*;
import it.unicam.ids.loyaltyplatform.PuntoVendita.PuntoVenditaService;
import it.unicam.ids.loyaltyplatform.PuntoVendita.models.PuntoVendita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final PuntoVenditaService pvService;
    private final ProgrammaFedeltaService pfService;

    private final AccountService accountService;

    @Autowired
    public DashboardService(PuntoVenditaService pvService, ProgrammaFedeltaService pfService, AccountService accountService) {
        this.pvService = pvService;
        this.pfService = pfService;
        this.accountService = accountService;
    }

    public ProgrammaFedelta creaProgrammaFedeltà(PFType type, int idTitolare){

        Titolare t = accountService.findTitolareById(idTitolare);

        ProgrammaFedelta newPF;

        if(!checkProgrammaDisponibile(t.getIdPuntoVendita(),type)){
            throw new IllegalArgumentException("Programma Fedeltà non disponibile");
        }

        switch (type) {
            case PFPUNTI -> {
                newPF = new ProgrammaPunti(type, t.getIdPuntoVendita());
            }
            case PFLIVELLI -> {
                newPF = new ProgrammaLivelli(type, t.getIdPuntoVendita());
            }
            case PFCASHBACK -> {
                newPF = new ProgrammaCashback(type, t.getIdPuntoVendita());
            }
            case PFVIP -> {
                newPF = new ProgrammaVip(type, t.getIdPuntoVendita());
            }
            case PFCOALIZIONE -> {
                newPF = new ProgrammaCoalizione(type, t.getIdPuntoVendita());
            }
            default -> throw new IllegalArgumentException("Invalid Type");
        }

        return pfService.addPF(newPF);

    }

    private Boolean checkProgrammaDisponibile(int pvId, PFType type){

        List<ProgrammaFedelta> attivi = pvService.getProgrammiAttivi(pvId);

        if(attivi.size() == 5){
            return false;
        }

        for (ProgrammaFedelta pf: attivi) {
            if(pf.getType() == type){
                return false;
            }
        }
        return true;
    }

    public ProgrammaFedelta modificaProgrammaFedeltà(ProgrammaFedelta modPF, int idTitolare){

        List<ProgrammaFedelta> attivi = pvService.getProgrammiAttivi(accountService.findTitolareById(idTitolare).getIdPuntoVendita());

        if(attivi.size() == 0){
            throw new IllegalArgumentException("Programmi fedeltà attivi non trovati");
        }

        for (ProgrammaFedelta pf: attivi) {

            if(pf.getType() == modPF.getType()){
                return pfService.updatePF(modPF);
            }
        }
        throw new IllegalArgumentException("Nessun programma da modificare");

    }

    public Account registraCliente(Utente cliente, int idCassiere){


        if(accountService.findAccountById(idCassiere).getRuolo() != Ruolo.CASSIERE){
            throw new IllegalArgumentException("ID Cassiere non valido");
        }

        return accountService.addAccount(cliente);
    }

    public PuntoVendita registraPuntoVendita(PuntoVendita pv, int idTitolare){

        Titolare titolare = accountService.findTitolareById(idTitolare);
        PuntoVendita newPV;

        if(titolare.getRuolo() != Ruolo.TITOLARE){
            throw new IllegalArgumentException("ID Titolare non valido");
        }

        if(titolare.getIdPuntoVendita() != 0){
            throw new IllegalArgumentException("Titolare ha già un punto vendita");
        }
        newPV = pvService.addPuntoVendita(pv);
        titolare.setIdPuntoVendita(newPV.getId());
        accountService.updateAccount(titolare);
        return newPV;

    }

}
