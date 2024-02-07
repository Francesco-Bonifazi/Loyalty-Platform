package it.unicam.ids.loyaltyplatform.account.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.ids.loyaltyplatform.tessera.models.Tessera;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "Utente")
@Getter
public class Utente extends Account{

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "utente", orphanRemoval = true)
    @JsonIgnore
    private List<Tessera> tessere = new ArrayList<>();



    public Utente(){
        setType(AccountType.UTENTE);
    }

    public Utente(String nome, String cognome, String email, String telefono){
        setNome(nome);
        setCognome(cognome);
        setEmail(email);
        setTelefono(telefono);
        setType(AccountType.UTENTE);
    }

    public Utente(String nome, String cognome, String email, String telefono, String password){
        setNome(nome);
        setCognome(cognome);
        setEmail(email);
        setTelefono(telefono);
        setPassword(password);
        setType(AccountType.UTENTE);
    }

}
