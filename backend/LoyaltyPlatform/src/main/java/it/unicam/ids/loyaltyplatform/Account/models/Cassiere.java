package it.unicam.ids.loyaltyplatform.Account.models;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue(value = "Cassiere")
public class Cassiere extends Account{

    public Cassiere() {

    }
    public Cassiere(String nome, String cognome, String email, String telefono) {
        super(nome, cognome, email, telefono, Ruolo.CASSIERE);
    }

}
