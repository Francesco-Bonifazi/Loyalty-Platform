package it.unicam.ids.loyaltyplatform.dashboard;

import it.unicam.ids.loyaltyplatform.account.AccountService;
import it.unicam.ids.loyaltyplatform.account.models.*;
import it.unicam.ids.loyaltyplatform.fattura.FatturaService;
import it.unicam.ids.loyaltyplatform.pf.ProgrammaFedeltaService;
import it.unicam.ids.loyaltyplatform.pf.models.*;
import it.unicam.ids.loyaltyplatform.premio.PremioService;
import it.unicam.ids.loyaltyplatform.premio.models.Premio;
import it.unicam.ids.loyaltyplatform.puntovendita.PuntoVenditaService;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import it.unicam.ids.loyaltyplatform.tessera.TesseraService;
import it.unicam.ids.loyaltyplatform.tessera.models.Tessera;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DashboardService {
    private final PuntoVenditaService pvService;
    private final ProgrammaFedeltaService pfService;
    private final AccountService accountService;
    private final TesseraService tesseraService;
    private final FatturaService fatturaService;
    private final PremioService premioService;

    public ProgrammaFedelta creaProgrammaFedelta(int account_id, PFType pfType){

        Titolare t = accountService.findAccount(account_id, AccountType.TITOLARE);
        PuntoVendita puntoVendita = t.getPuntoVendita();
        if(puntoVendita == null){
            throw new IllegalArgumentException("Couldn't find Punto Vendita in Titolare's account!");
        } else {
            puntoVendita.addProgrammaFedelta(pfType);
            pvService.updatePuntoVendita(t.getPuntoVendita());
            return t.getPuntoVendita().getProgrammiFedelta().getLast();
        }

    }

    public <T extends ProgrammaFedelta> T modificaProgrammaFedelta(T programmaFedelta, int account_id){
        Titolare t = accountService.findAccount(account_id, AccountType.TITOLARE);
        PuntoVendita puntoVendita = t.getPuntoVendita();
        T modPF = puntoVendita.updateProgrammaFedelta(programmaFedelta);
        pvService.updatePuntoVendita(puntoVendita);
        return modPF;
    }

    public Utente registraCliente(Utente cliente){
        return accountService.addAccount(cliente);
    }

    public PuntoVendita registraPuntoVendita(int account_id, PuntoVendita puntoVendita){
        Titolare t = accountService.findAccount(account_id, AccountType.TITOLARE);
        if(t.getPuntoVendita() == null){
            t.setPuntoVendita(puntoVendita);
            accountService.updateAccount(t);
            return t.getPuntoVendita();
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
        Tessera t = new Tessera(utente, programmaFedelta);

        return tesseraService.addTessera(t);
    }

    public void rimuoviIscrizionePF(int utente_id, int pf_id){
        Utente utente = accountService.findAccount(utente_id);
        ProgrammaFedelta programmaFedelta = pfService.findPF(pf_id);
        tesseraService.removeTessera(utente, programmaFedelta);
    }

    public List<Utente> visualizzaClienti(int pf_id){
        return tesseraService.findIscritti(pfService.findPF(pf_id));
    }

    public void rimuoviUtente(int utente_id){
        accountService.deleteAccount(utente_id);
    }

    public <T extends Account> T updateAccount(T account){
        return accountService.updateAccount(account);
    }

    public Account visualizzaInfoCliente(int account_id){
        return accountService.findAccount(account_id);
    }

    public List<Account> visualizzaDipendenti(int pv_id){
        return accountService.findDipendentiByPvId(pv_id);
    }

    public InputStreamResource visualizzaFattura(int fattura_id){
        return new InputStreamResource(fatturaService.getFatturaPDF(fattura_id));
    }

    public InputStreamResource visualizzaFatture(int pv_id){
        return new InputStreamResource(fatturaService.getFatturaPDF(pvService.findPuntoVendita(pv_id)));
    }

    public List<Premio> visualizzaPremi(int programma_id){
        ProgrammaFedelta programmaFedelta = pfService.findPF(programma_id);
        return premioService.findPremi(programmaFedelta);
    }

}
