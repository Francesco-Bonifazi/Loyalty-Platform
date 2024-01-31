package it.unicam.ids.loyaltyplatform.tessera;

import it.unicam.ids.loyaltyplatform.account.models.Utente;
import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.tessera.models.Tessera;
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

}
