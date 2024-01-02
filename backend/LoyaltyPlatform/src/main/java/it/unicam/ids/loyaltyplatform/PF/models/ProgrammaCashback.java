package it.unicam.ids.loyaltyplatform.PF.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Cashback")
public class ProgrammaCashback extends ProgrammaFedelta {

    public ProgrammaCashback() {

    }

    public ProgrammaCashback(PFType type, int rapporto, int pvId) {
        super(type, rapporto, pvId);
    }

    public ProgrammaCashback(PFType type, int pvId) {
        super(type, pvId);
    }
}
