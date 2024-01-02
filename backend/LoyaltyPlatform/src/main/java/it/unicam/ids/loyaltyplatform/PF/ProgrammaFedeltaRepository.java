package it.unicam.ids.loyaltyplatform.PF;

import it.unicam.ids.loyaltyplatform.PF.models.ProgrammaFedelta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgrammaFedeltaRepository extends JpaRepository<ProgrammaFedelta, Integer> {

    ProgrammaFedelta findProgrammaFedeltaById(int id);

    List<ProgrammaFedelta> findProgrammaFedeltaByPvId(int id);

}
