package it.unicam.ids.loyaltyplatform.Account.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Titolare")
public class Titolare extends Account{

    private int idPuntoVendita;

    public Titolare() {
    }

    public Titolare(String nome, String cognome, String email, String telefono) {
        super(nome, cognome, email, telefono, Ruolo.TITOLARE);
        this.idPuntoVendita = 0;
    }
    public Titolare(String nome, String cognome, String email, String telefono, int idPuntoVendita) {
        super(nome, cognome, email, telefono, Ruolo.TITOLARE);
        this.idPuntoVendita = idPuntoVendita;
    }

    public int getIdPuntoVendita() {
        return this.idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }
}
