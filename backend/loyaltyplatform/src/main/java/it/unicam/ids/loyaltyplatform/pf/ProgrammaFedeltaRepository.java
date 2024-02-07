package it.unicam.ids.loyaltyplatform.pf;

import it.unicam.ids.loyaltyplatform.pf.models.PFType;
import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProgrammaFedeltaRepository extends JpaRepository<ProgrammaFedelta, Integer> {

    <T extends ProgrammaFedelta> T findProgrammaFedeltaById(int id);
    <T extends ProgrammaFedelta> T findProgrammaFedeltaByIdAndType(int id, PFType type);

}