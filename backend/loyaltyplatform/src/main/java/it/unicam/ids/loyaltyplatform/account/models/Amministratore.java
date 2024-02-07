package it.unicam.ids.loyaltyplatform.account.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "Amministratore")
@Getter
@Setter
public class Amministratore extends Account{

    @Transient
    private final List<Permessi> defaultAdminPerms = new ArrayList<>(List.of(Permessi.USER_REMOVE_AUTH,Permessi.TESSERE_VIEW, Permessi.UNSUBSCRIBE));

    @ManyToOne
    @JsonIgnore
    private PuntoVendita puntoVendita;

    public Amministratore(){
        setType(AccountType.AMMINISTRATORE);
        addPermesso(defaultAdminPerms);
    }
    public Amministratore(String nome, String cognome, String email, String telefono, String password) {
        setNome(nome);
        setCognome(cognome);
        setEmail(email);
        setTelefono(telefono);
        setPassword(password);
        addPermesso(defaultAdminPerms);
        setType(AccountType.AMMINISTRATORE);
    }
}
