package it.unicam.ids.loyaltyplatform.fattura;

import it.unicam.ids.loyaltyplatform.fattura.models.Fattura;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Integer> {
    Fattura findFatturaByFatturaId(int id);
    Fattura findFatturaByFatturaIdAndPuntoVendita(int fatturaId, PuntoVendita puntoVendita);

    List<Fattura> findAllByPuntoVendita(PuntoVendita puntoVendita);

    void deleteFatturaByFatturaId(int id);
}
