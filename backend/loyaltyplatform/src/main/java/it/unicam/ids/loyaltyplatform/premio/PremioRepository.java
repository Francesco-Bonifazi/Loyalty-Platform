package it.unicam.ids.loyaltyplatform.premio;

import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.premio.models.Premio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Integer> {

    Premio findPremioById(int id);

    Premio findPremioByProgrammaFedelta(ProgrammaFedelta programmaFedelta);

    Premio findPremioByIdAndProgrammaFedelta(int id, ProgrammaFedelta programmaFedelta);

    List<Premio> findAllByProgrammaFedelta(ProgrammaFedelta programmaFedelta);

    void deletePremioById(int id);

}
