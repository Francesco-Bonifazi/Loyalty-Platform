package it.unicam.ids.loyaltyplatform.pf.models;

import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
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

    public ProgrammaCoalizione(PuntoVendita puntoVendita){
        setType(PFType.PFCOALIZIONE);
        setPuntoVendita(puntoVendita);
    }

    public ProgrammaCoalizione(int rapporto, int idCollaboratore, PuntoVendita puntoVendita){
        setType(PFType.PFCOALIZIONE);
        setPuntoVendita(puntoVendita);
        setRapporto(rapporto);
        setIdCollaboratore(idCollaboratore);
    }
}
