package it.unicam.ids.loyaltyplatform.PF;

import it.unicam.ids.loyaltyplatform.PF.models.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammaFedeltaService {

    private final ProgrammaFedeltaRepository programmaFedeltaRepository;

    @Autowired
    public ProgrammaFedeltaService(ProgrammaFedeltaRepository programmaFedeltaRepository) {
        this.programmaFedeltaRepository = programmaFedeltaRepository;
    }

    public ProgrammaFedelta addPF(ProgrammaFedelta pf){
        return programmaFedeltaRepository.save(pf);
    }

    public List<ProgrammaFedelta> findAll(){
        return programmaFedeltaRepository.findAll();
    }

    public ProgrammaFedelta updatePF(ProgrammaFedelta pf){
        return programmaFedeltaRepository.save(pf);
    }

    public List<ProgrammaFedelta> findActivebyPvId(int pvId){
        return programmaFedeltaRepository.findProgrammaFedeltaByPvId(pvId);
    }

}
