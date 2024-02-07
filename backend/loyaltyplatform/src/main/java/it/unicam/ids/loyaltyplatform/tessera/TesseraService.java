package it.unicam.ids.loyaltyplatform.tessera;

import it.unicam.ids.loyaltyplatform.account.models.Utente;
import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.tessera.models.Tessera;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TesseraService {

    private final TesseraRepository tesseraRepository;

    public Tessera findTessera(int tessera_id){
        Tessera tessera = tesseraRepository.findTesseraByTesseraId(tessera_id);
        if(tessera == null){
            throw new IllegalArgumentException("Couldn't find Tessera with ID: "+tessera_id);
        } else {
            return tessera;
        }
    }

    public List<Tessera> findTessera(ProgrammaFedelta programmaFedelta){
        List<Tessera> tessere = tesseraRepository.findTesserasByProgrammaFedelta(programmaFedelta);
        if(tessere.isEmpty()){
            throw new IllegalArgumentException("Couldn't find any Tessere for Programma Fedeltà: "+programmaFedelta+"!");
        } else {
            return tessere;
        }
    }

    public Tessera findTessera(Utente utente, ProgrammaFedelta programmaFedelta){
        Tessera tessera = tesseraRepository.findTesseraByUtenteAndProgrammaFedelta(utente, programmaFedelta);
        if(tessera == null){
            throw new IllegalArgumentException("Couldn't find Tessera with Utente: "+utente+" and Programma Fedeltà: "+programmaFedelta+"!");
        } else {
            return tessera;
        }
    }

    public List<Utente> findIscritti(ProgrammaFedelta programmaFedelta){
        List<Tessera> tessere = findTessera(programmaFedelta);
        if(tessere.isEmpty()){
            throw new IllegalArgumentException("Couldn't find any Tessere for Programma Fedeltà: "+programmaFedelta+"!");
        } else {
            return tesseraRepository.findUtentiByProgrammaFedelta(programmaFedelta);
        }
    }

    public Tessera addTessera(Tessera tessera){
        if(tesseraRepository.findTesseraByUtenteAndProgrammaFedelta(tessera.getUtente(),tessera.getProgrammaFedelta()) == null){
            return tesseraRepository.save(tessera);
        } else {
            throw new IllegalArgumentException("Tessera already exists!");
        }
    }

    public void removeTessera(Tessera tessera){
        Tessera t = tesseraRepository.findTesseraByUtenteAndProgrammaFedelta(tessera.getUtente(),tessera.getProgrammaFedelta());
        if(t != null){
            tesseraRepository.deleteTesseraByTesseraId(t.getTesseraId());
        } else {
            throw new IllegalArgumentException("Tessera with Utente: "+tessera.getUtente()+" and PF: "+tessera.getProgrammaFedelta()+" doesn't exist!");
        }
    }

    @Transactional
    public void removeTessera(Utente utente, ProgrammaFedelta programmaFedelta){
        Tessera t = tesseraRepository.findTesseraByUtenteAndProgrammaFedelta(utente,programmaFedelta);
        if(t != null){
            tesseraRepository.deleteTesseraByTesseraId(t.getTesseraId());
        } else {
            throw new IllegalArgumentException("Tessera with Utente: "+utente+" and PF: "+programmaFedelta+" doesn't exist!");
        }
    }

}
