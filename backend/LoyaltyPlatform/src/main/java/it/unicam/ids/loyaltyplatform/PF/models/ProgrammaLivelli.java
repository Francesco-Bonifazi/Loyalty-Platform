package it.unicam.ids.loyaltyplatform.pf.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Livelli")
@Getter
@Setter
public class ProgrammaLivelli extends ProgrammaFedelta{

    private int maxExp;
    private int threshold;

    public ProgrammaLivelli(){
        setType(PFType.PFLIVELLI);
    }

    public ProgrammaLivelli(int rapporto, int maxExp, int threshold){
        setType(PFType.PFLIVELLI);
        setRapporto(rapporto);
        setMaxExp(maxExp);
        setThreshold(threshold);
    }

}
