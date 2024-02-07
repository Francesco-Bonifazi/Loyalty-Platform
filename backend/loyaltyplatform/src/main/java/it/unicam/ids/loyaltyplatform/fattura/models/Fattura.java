package it.unicam.ids.loyaltyplatform.fattura.models;

import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int fatturaId;

    @ManyToOne
    private PuntoVendita puntoVendita;

    private int saldo;

    private Date data_emissione;

    private Date data_scadenza;

    public Fattura(int saldo, Date data_emissione, Date data_scadenza, PuntoVendita puntoVendita){
        this.saldo = saldo;
        this.data_emissione = data_emissione;
        this.data_scadenza = data_scadenza;
        this.puntoVendita = puntoVendita;
    }

}
