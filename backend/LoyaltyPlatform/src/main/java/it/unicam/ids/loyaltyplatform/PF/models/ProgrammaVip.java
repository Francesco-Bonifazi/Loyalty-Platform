package it.unicam.ids.loyaltyplatform.pf.models;

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

    public ProgrammaVip(int rapporto, int costo) {
        setType(PFType.PFVIP);
        setRapporto(rapporto);
        setCosto(costo);
    }

}
