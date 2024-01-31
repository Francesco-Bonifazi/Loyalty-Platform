package it.unicam.ids.loyaltyplatform.pf.models;

import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Punti")
@Getter
@Setter
public class ProgrammaPunti extends ProgrammaFedelta {

    private int threshold;
    private int maxPunti;

    public ProgrammaPunti() {
        this.setType(PFType.PFPUNTI);
    }
    public ProgrammaPunti(PuntoVendita puntoVendita) {
        setPuntoVendita(puntoVendita);
        setType(PFType.PFPUNTI);
    }
    public ProgrammaPunti(int rapporto, int threshold, int maxPunti, PuntoVendita puntoVendita) {
        setPuntoVendita(puntoVendita);
        setType(PFType.PFPUNTI);
        setRapporto(rapporto);
        setThreshold(threshold);
        setMaxPunti(maxPunti);
    }

}
