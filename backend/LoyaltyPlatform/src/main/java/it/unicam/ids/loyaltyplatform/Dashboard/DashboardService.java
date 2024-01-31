package it.unicam.ids.loyaltyplatform.dashboard;

import it.unicam.ids.loyaltyplatform.account.AccountService;
import it.unicam.ids.loyaltyplatform.account.models.*;
import it.unicam.ids.loyaltyplatform.pf.ProgrammaFedeltaService;
import it.unicam.ids.loyaltyplatform.pf.models.PFType;
import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.puntovendita.PuntoVenditaService;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import it.unicam.ids.loyaltyplatform.tessera.TesseraService;
import it.unicam.ids.loyaltyplatform.tessera.models.Tessera;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final PuntoVenditaService pvService;
    private final ProgrammaFedeltaService pfService;
    private final AccountService accountService;
    private final TesseraService tesseraService;

    public ProgrammaFedelta creaProgrammaFedelta(int account_id, PFType pfType){

        Titolare t = accountService.findAccount(account_id, AccountType.TITOLARE);
        PuntoVendita puntoVendita = t.getPuntoVendita();
        if(puntoVendita == null){
            throw new IllegalArgumentException("Couldn't find Punto Vendita in Titolare's account!");
        } else {
            return puntoVendita.addProgrammaFedelta(pfType, puntoVendita);
        }

    }

    public <T extends ProgrammaFedelta> T modificaProgrammaFedelta(T programmaFedelta, int account_id){
        Titolare t = accountService.findAccount(account_id, AccountType.TITOLARE);
        PuntoVendita puntoVendita = t.getPuntoVendita();

        return puntoVendita.updateProgrammaFedelta(programmaFedelta);
    }

    public Utente registraCliente(Utente cliente){
        return accountService.addAccount(cliente);
    }

    public PuntoVendita registraPuntoVendita(int account_id, PuntoVendita puntoVendita){
        Titolare t = accountService.findAccount(account_id, AccountType.TITOLARE);
        if(t.getPuntoVendita() == null){
            puntoVendita.setTitolare(t);
            PuntoVendita newPV = pvService.addPuntoVendita(puntoVendita);
            t.setPuntoVendita(newPV);
            return newPV;
        } else {
            throw new IllegalArgumentException("Account already has a Punto Vendita!");
        }
    }

    public PuntoVendita cercaPuntoVendita(String nome){
        return pvService.findPuntoVendita(nome);
    }

    public Tessera iscrizionePF(int utente_id, int pf_id){
        Utente utente = accountService.findAccount(utente_id);
        ProgrammaFedelta programmaFedelta = pfService.findPF(pf_id);
        return utente.addTessera(new Tessera(utente, programmaFedelta));
    }

    public void rimuoviIscrizione(int utente_id, Tessera tessera){
        accountService.rimuoviTesseraUtente(utente_id, tessera);
    }

    public <T extends ProgrammaFedelta> List<Utente> visualizzaClienti(int pf_id){
        T pf = pfService.findPF(pf_id);
        List<Tessera> tessere = tesseraService.findTessera(pf);
        List<Utente> utenti = new ArrayList<>();

        for (Tessera t : tessere){
            utenti.add(t.getUtente());
        }
        return utenti;

    }

    public void rimuoviUtente(int utente_id){
        accountService.deleteAccount(utente_id);
    }

    public <T extends Account> T updateAccount(T account){
        return accountService.updateAccount(account);
    }

    public <T extends Account> T visualizzaInfoCliente(int account_id){
        return accountService.findAccount(account_id);
    }

    public <T extends Account> T visualizzaInfoCliente(T account){
        return accountService.findAccount(account);
    }

    public <T extends Account> T visualizzaInfoCliente(String email){
        return accountService.findAccount(email);
    }

}
