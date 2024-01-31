package it.unicam.ids.loyaltyplatform.pf;

import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgrammaFedeltaService {
    private final ProgrammaFedeltaRepository programmaFedeltaRepository;

    public <T extends ProgrammaFedelta> T findPF(int pfId){
        T pf = programmaFedeltaRepository.findProgrammaFedeltaById(pfId);
        if(pf == null){
            throw new IllegalArgumentException("Couldn't find Programma Fedelta with ID: "+pfId+"!");
        } else {
            return programmaFedeltaRepository.findProgrammaFedeltaById(pfId);
        }
    }

    public <T extends ProgrammaFedelta> T findPF(T programmaFedelta){
        T pf = programmaFedeltaRepository.findProgrammaFedeltaById(programmaFedelta.getId());
        if(pf == null){
            throw new IllegalArgumentException("Couldn't find Programma Fedelta: "+programmaFedelta+"!");
        } else {
            return programmaFedeltaRepository.findProgrammaFedeltaById(pf.getId());
        }
    }

    public  List<? extends ProgrammaFedelta> findAllByPuntoVendita(PuntoVendita puntoVendita){
        List<? extends ProgrammaFedelta> programmi = programmaFedeltaRepository.findProgrammaFedeltasByPuntoVendita(puntoVendita);
        if(programmi.isEmpty()){
            throw new IllegalArgumentException("Couldn't find any Programma Fedeltà in Punto Vendita with ID: "+puntoVendita.getId()+"!");
        } else {
            return programmi;
        }
    }

}
