package it.unicam.ids.loyaltyplatform.PF.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Punti")
public class ProgrammaPunti extends ProgrammaFedelta {

    private int threshold;
    private int maxPunti;

    public ProgrammaPunti() {

    }
    public ProgrammaPunti(PFType type, int rapporto, int threshold, int maxPunti, int pvId) {
        super(type, rapporto, pvId);
        this.threshold = threshold;
        this.maxPunti = maxPunti;
    }

    public ProgrammaPunti(PFType type, int pvId) {
        super(type, pvId);
    }


    public void setThreshold(int threshold){
        this.threshold = threshold;
    }

    public int getThreshold(){
        return threshold;
    }

    public void setMaxPunti(int maxPunti){
        this.maxPunti = maxPunti;
    }

    public int getMaxPunti(){
        return maxPunti;
    }
}
