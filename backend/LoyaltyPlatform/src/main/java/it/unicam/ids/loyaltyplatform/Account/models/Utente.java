package it.unicam.ids.loyaltyplatform.Account.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Utente")
public class Utente extends Account{

    public Utente() {

    }
    public Utente(String nome, String cognome, String email, String telefono) {
        super(nome, cognome, email, telefono, Ruolo.UTENTE);
    }

}
