package it.unicam.ids.loyaltyplatform.PF.models;

import it.unicam.ids.loyaltyplatform.PF.models.PFType;
import it.unicam.ids.loyaltyplatform.PF.models.ProgrammaFedelta;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Livelli")
public class ProgrammaLivelli extends ProgrammaFedelta {

    private int maxExp;
    private int threshold;

    public ProgrammaLivelli() {

    }

    public ProgrammaLivelli(PFType type, int rapporto, int maxExp, int threshold, int pvId) {
        super(type, rapporto, pvId);
        this.maxExp = maxExp;
        this.threshold = threshold;
    }

    public ProgrammaLivelli(PFType type, int pvId) {
        super(type, pvId);
    }


    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
