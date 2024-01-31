package it.unicam.ids.loyaltyplatform.tessera;

import it.unicam.ids.loyaltyplatform.account.models.Account;
import it.unicam.ids.loyaltyplatform.account.models.Utente;
import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.tessera.models.Tessera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TesseraRepository extends JpaRepository<Tessera, Integer> {
    Tessera findTesseraByTesseraId(int id);
    Tessera findTesseraByUtenteAndProgrammaFedelta(Utente utente, ProgrammaFedelta programmaFedelta);
    List<Tessera> findTesserasByProgrammaFedelta(ProgrammaFedelta programmaFedelta);
}
