package it.unicam.ids.loyaltyplatform.pf;

import it.unicam.ids.loyaltyplatform.account.models.Account;
import it.unicam.ids.loyaltyplatform.pf.models.PFType;
import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgrammaFedeltaRepository extends JpaRepository<ProgrammaFedelta, Integer> {

    <T extends ProgrammaFedelta> T findProgrammaFedeltaById(int id);
    <T extends ProgrammaFedelta> T findProgrammaFedeltaByIdAndType(int id, PFType type);
    <T extends ProgrammaFedelta> List<T> findProgrammaFedeltasByPuntoVendita(PuntoVendita puntoVendita);

}
