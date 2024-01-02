package it.unicam.ids.loyaltyplatform.PuntoVendita;

import it.unicam.ids.loyaltyplatform.PuntoVendita.models.PuntoVendita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuntoVenditaRepository extends JpaRepository<PuntoVendita, Integer> {

    void deletePuntoVenditaById(int id);
    PuntoVendita findPuntoVenditaById(int id);

}
