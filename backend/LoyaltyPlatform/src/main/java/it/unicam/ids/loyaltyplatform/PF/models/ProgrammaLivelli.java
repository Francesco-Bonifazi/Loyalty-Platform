package it.unicam.ids.loyaltyplatform.pf.models;

import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
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

    public ProgrammaLivelli(PuntoVendita puntoVendita){
        setPuntoVendita(puntoVendita);
        setType(PFType.PFLIVELLI);
    }

    public ProgrammaLivelli(int rapporto, int maxExp, int threshold, PuntoVendita puntoVendita){
        setPuntoVendita(puntoVendita);
        setType(PFType.PFLIVELLI);
        setRapporto(rapporto);
        setMaxExp(maxExp);
        setThreshold(threshold);
    }

}
