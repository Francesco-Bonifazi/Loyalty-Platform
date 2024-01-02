package it.unicam.ids.loyaltyplatform.PF.models;

import it.unicam.ids.loyaltyplatform.PF.models.PFType;
import it.unicam.ids.loyaltyplatform.PF.models.ProgrammaFedelta;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "VIP")
public class ProgrammaVip extends ProgrammaFedelta {

    private int costo;

    public ProgrammaVip() {

    }

    public ProgrammaVip(PFType type, int rapporto, int costo, int pvId) {
        super(type, rapporto, pvId);
        this.costo = costo;
    }

    public ProgrammaVip(PFType type, int pvId) {
        super(type, pvId);
    }


    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
}
