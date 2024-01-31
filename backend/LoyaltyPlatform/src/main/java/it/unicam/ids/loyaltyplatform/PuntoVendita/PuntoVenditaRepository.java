package it.unicam.ids.loyaltyplatform.puntovendita;

import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoVenditaRepository extends JpaRepository<PuntoVendita, Integer> {

    PuntoVendita findPuntoVenditaById(int id);
    PuntoVendita findPuntoVenditaByNome(String nome);
    void deletePuntoVenditaById(int id);
}
