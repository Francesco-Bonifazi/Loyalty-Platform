package it.unicam.ids.loyaltyplatform.account.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "Titolare")
@Getter
@Setter
public class Titolare extends Account{

    @ManyToOne
    @JsonIgnore
    private PuntoVendita puntoVendita;

    @Transient
    private final List<Permessi> defaultAdminPerms = new ArrayList<>(List.of(Permessi.USER_REMOVE_AUTH,Permessi.TESSERE_VIEW, Permessi.UNSUBSCRIBE));

    @Transient
    private final List<Permessi> defaultTitolarePerms = new ArrayList<>(List.of(Permessi.PF_AUTH,Permessi.PV_AUTH, Permessi.DIPENDENTI_VIEW));

    public Titolare(){
        setType(AccountType.TITOLARE);
        addPermesso(defaultAdminPerms);
        addPermesso(defaultTitolarePerms);
    }

    public Titolare(String nome, String cognome, String email, String telefono, String password) {
        setNome(nome);
        setCognome(cognome);
        setEmail(email);
        setTelefono(telefono);
        setPassword(password);
        addPermesso(defaultAdminPerms);
        addPermesso(defaultTitolarePerms);
        setType(AccountType.TITOLARE);
        setPuntoVendita(null);
    }



}
