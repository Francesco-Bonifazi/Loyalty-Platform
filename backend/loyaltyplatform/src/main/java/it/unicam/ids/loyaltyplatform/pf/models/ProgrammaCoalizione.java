package it.unicam.ids.loyaltyplatform.pf.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Coalizione")
@Getter
@Setter
public class ProgrammaCoalizione extends ProgrammaFedelta{

    private int idCollaboratore;

    public ProgrammaCoalizione(){
        setType(PFType.PFCOALIZIONE);
    }

    public ProgrammaCoalizione(int rapporto, int idCollaboratore){
        setType(PFType.PFCOALIZIONE);
        setRapporto(rapporto);
        setIdCollaboratore(idCollaboratore);
    }
}
