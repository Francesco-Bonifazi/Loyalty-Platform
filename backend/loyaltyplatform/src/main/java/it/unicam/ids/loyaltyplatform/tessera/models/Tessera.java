package it.unicam.ids.loyaltyplatform.tessera.models;

import it.unicam.ids.loyaltyplatform.account.models.Utente;
import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int tesseraId;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "programmafedelta_id")
    private ProgrammaFedelta programmaFedelta;

    private int saldo;

    public Tessera(Utente utente, ProgrammaFedelta programmaFedelta){
        setUtente(utente);
        setProgrammaFedelta(programmaFedelta);
    }

}
