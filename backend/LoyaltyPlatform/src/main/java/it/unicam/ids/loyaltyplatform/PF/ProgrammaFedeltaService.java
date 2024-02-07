package it.unicam.ids.loyaltyplatform.pf;

import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        T pf = programmaFedeltaRepository.findProgrammaFedeltaByIdAndType(programmaFedelta.getId(), programmaFedelta.getType());
        if(pf == null){
            throw new IllegalArgumentException("Couldn't find Programma Fedelta: "+programmaFedelta+"!");
        } else {
            return pf;
        }
    }


}
