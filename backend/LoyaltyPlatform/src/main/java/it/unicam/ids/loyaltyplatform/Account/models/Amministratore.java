package it.unicam.ids.loyaltyplatform.account.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Amministratore")
public class Amministratore extends Account{

    public Amministratore(){
        setType(AccountType.AMMINISTRATORE);
        addPermesso(Permessi.AMMINISTRATORE);
    }
    public Amministratore(String nome, String cognome, String email, String telefono, String password) {
        setNome(nome);
        setCognome(cognome);
        setEmail(email);
        setTelefono(telefono);
        setPassword(password);
        addPermesso(Permessi.AMMINISTRATORE);
        setType(AccountType.AMMINISTRATORE);
    }
}
