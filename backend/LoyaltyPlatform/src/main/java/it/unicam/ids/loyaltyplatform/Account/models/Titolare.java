package it.unicam.ids.loyaltyplatform.account.models;

import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Titolare")
@Getter
@Setter
public class Titolare extends Account{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "puntovendita_id")
    private PuntoVendita puntoVendita;

    public Titolare(){
        setType(AccountType.TITOLARE);
        addPermesso(Permessi.TITOLARE);
    }

    public Titolare(String nome, String cognome, String email, String telefono, String password) {
        setNome(nome);
        setCognome(cognome);
        setEmail(email);
        setTelefono(telefono);
        setPassword(password);
        addPermesso(Permessi.TITOLARE);
        setType(AccountType.TITOLARE);
        setPuntoVendita(null);
    }

}
