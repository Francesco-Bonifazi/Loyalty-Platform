package it.unicam.ids.loyaltyplatform.pf.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Cashback")
public class ProgrammaCashback extends ProgrammaFedelta{

    public ProgrammaCashback(){
        setType(PFType.PFCASHBACK);
    }

    public ProgrammaCashback(int rapporto){
        setType(PFType.PFCASHBACK);
        setRapporto(rapporto);
    }
}
