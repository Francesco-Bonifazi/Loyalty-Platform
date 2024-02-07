package it.unicam.ids.loyaltyplatform.account.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Cassiere")
@Getter
@Setter
public class Cassiere extends Account{

    @ManyToOne
    @JsonIgnore
    private PuntoVendita puntoVendita;

    public Cassiere() {
        setType(AccountType.CASSIERE);
    }
    public Cassiere(String nome, String cognome, String email, String telefono, String password) {
        setNome(nome);
        setCognome(cognome);
        setEmail(email);
        setTelefono(telefono);
        setPassword(password);
        setType(AccountType.CASSIERE);
    }

}
