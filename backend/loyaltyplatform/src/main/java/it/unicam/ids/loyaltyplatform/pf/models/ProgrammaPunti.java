package it.unicam.ids.loyaltyplatform.pf.models;

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
    public ProgrammaPunti(int rapporto, int threshold, int maxPunti) {
        setType(PFType.PFPUNTI);
        setRapporto(rapporto);
        setThreshold(threshold);
        setMaxPunti(maxPunti);
    }

}
