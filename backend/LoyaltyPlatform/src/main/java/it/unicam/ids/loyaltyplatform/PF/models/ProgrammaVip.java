package it.unicam.ids.loyaltyplatform.pf.models;

import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "VIP")
@Getter
@Setter
public class ProgrammaVip extends ProgrammaFedelta {

    private int costo;

    public ProgrammaVip() {
        setType(PFType.PFVIP);
    }
    public ProgrammaVip(PuntoVendita puntoVendita) {
        setPuntoVendita(puntoVendita);
        setType(PFType.PFVIP);
    }
    public ProgrammaVip(int rapporto, int costo, PuntoVendita puntoVendita) {
        setPuntoVendita(puntoVendita);
        setType(PFType.PFVIP);
        setRapporto(rapporto);
        setCosto(costo);
    }

}
