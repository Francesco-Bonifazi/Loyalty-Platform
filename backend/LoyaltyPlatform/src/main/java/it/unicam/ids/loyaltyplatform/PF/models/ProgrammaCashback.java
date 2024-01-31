package it.unicam.ids.loyaltyplatform.pf.models;

import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Cashback")
public class ProgrammaCashback extends ProgrammaFedelta{

    public ProgrammaCashback(){
        setType(PFType.PFCASHBACK);
    }

    public ProgrammaCashback(PuntoVendita puntoVendita){
        setType(PFType.PFCASHBACK);
        setPuntoVendita(puntoVendita);
    }
    public ProgrammaCashback(int rapporto, PuntoVendita puntoVendita){
        setType(PFType.PFCASHBACK);
        setPuntoVendita(puntoVendita);
        setRapporto(rapporto);
    }
}
