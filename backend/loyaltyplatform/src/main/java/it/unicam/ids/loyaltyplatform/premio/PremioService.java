package it.unicam.ids.loyaltyplatform.premio;

import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.premio.models.Premio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PremioService {

    private final PremioRepository premioRepository;

    public Premio findPremio(int id){
        Premio premio = premioRepository.findPremioById(id);
        if(premio == null){
            throw new IllegalArgumentException("Couldn't find premio with ID: "+id+"!");
        } else {
            return premio;
        }
    }

    public Premio findPremio(ProgrammaFedelta programmaFedelta){
        Premio premio = premioRepository.findPremioByProgrammaFedelta(programmaFedelta);
        if(premio == null){
            throw new IllegalArgumentException("Couldn't find premio with PF: "+programmaFedelta+"!");
        } else {
            return premio;
        }
    }

    public Premio findPremio(int id, ProgrammaFedelta programmaFedelta){
        Premio premio = premioRepository.findPremioByIdAndProgrammaFedelta(id, programmaFedelta);
        if(premio == null){
            throw new IllegalArgumentException("Couldn't find premio with ID:"+id+" and PF: "+programmaFedelta+"!");
        } else {
            return premio;
        }
    }

    public List<Premio> findPremi(ProgrammaFedelta programmaFedelta){
        List<Premio> premi = premioRepository.findAllByProgrammaFedelta(programmaFedelta);
        if(premi.isEmpty()){
            throw new IllegalArgumentException("Couldn't find any premi for PF: "+programmaFedelta+"!");
        } else {
            return premi;
        }
    }

    public Premio addPremio(Premio premio){
        Premio p = premioRepository.findPremioByIdAndProgrammaFedelta(premio.getId(), premio.getProgrammaFedelta());
        if(p != null){
            throw new IllegalArgumentException("Premio already exists!");
        } else {
            return premioRepository.save(premio);
        }
    }

    public void deletePremio(int id){
        Premio premio = premioRepository.findPremioById(id);
        if(premio == null){
            throw new IllegalArgumentException("Couldn't find premio with ID: "+id+"!");
        } else {
            premioRepository.deletePremioById(id);
        }
    }

    public void deletePremio(Premio premio){
        Premio p = premioRepository.findPremioByIdAndProgrammaFedelta(premio.getId(), premio.getProgrammaFedelta());
        if(p == null){
            throw new IllegalArgumentException("Couldn't find premio with ID: "+premio.getId()+"!");
        } else {
            premioRepository.deletePremioById(premio.getId());
        }
    }

}
