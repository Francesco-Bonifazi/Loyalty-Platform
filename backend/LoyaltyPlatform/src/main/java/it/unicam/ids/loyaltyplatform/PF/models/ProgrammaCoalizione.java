package it.unicam.ids.loyaltyplatform.PF.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Coalizione")
public class ProgrammaCoalizione extends ProgrammaFedelta {

    private int idCollaboratore;

    public ProgrammaCoalizione() {

    }

    public ProgrammaCoalizione(PFType type, short rapporto, int idCollaboratore, int pvId) {
        super(type, rapporto, pvId);
        this.idCollaboratore = idCollaboratore;
    }

    public ProgrammaCoalizione(PFType type, int pvId) {
        super(type, pvId);
    }


    public int getIdCollaboratore() {
        return idCollaboratore;
    }

    public void setIdCollaboratore(int idCollaboratore) {
        this.idCollaboratore = idCollaboratore;
    }
}
