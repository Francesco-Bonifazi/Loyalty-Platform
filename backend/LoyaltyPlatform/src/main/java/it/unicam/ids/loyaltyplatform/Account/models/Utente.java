package it.unicam.ids.loyaltyplatform.account.models;

import it.unicam.ids.loyaltyplatform.tessera.models.Tessera;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "Utente")
public class Utente extends Account{

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Tessera> tessere;

    public Utente(){
        addPermesso(Permessi.UTENTE);
        setType(AccountType.UTENTE);
    }

    public Utente(String nome, String cognome, String email, String telefono, String password){
        setNome(nome);
        setCognome(cognome);
        setEmail(email);
        setTelefono(telefono);
        setPassword(password);
        setType(AccountType.UTENTE);
        addPermesso(Permessi.UTENTE);
    }

    public void removeTessera(Tessera tessera){
        if(!this.tessere.contains(tessera)){
            throw new IllegalArgumentException("Tessera not found!");
        } else {
            this.tessere.remove(tessera);
        }
    }
    public Tessera addTessera(Tessera tessera){
        for (Tessera t : tessere){
            if(t.equals(tessera)){
                throw new IllegalArgumentException("Tessera already exists!");
            }
        }
        tessere.add(tessera);
        return tessera;
    }

}
