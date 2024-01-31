package it.unicam.ids.loyaltyplatform.account.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Cassiere")
public class Cassiere extends Account{

    public Cassiere() {
        addPermesso(Permessi.CASSIERE);
        setType(AccountType.CASSIERE);
    }
    public Cassiere(String nome, String cognome, String email, String telefono, String password) {
        setNome(nome);
        setCognome(cognome);
        setEmail(email);
        setTelefono(telefono);
        setPassword(password);
        addPermesso(Permessi.CASSIERE);
        setType(AccountType.CASSIERE);
    }

}
