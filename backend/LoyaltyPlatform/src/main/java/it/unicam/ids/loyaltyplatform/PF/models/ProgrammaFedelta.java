package it.unicam.ids.loyaltyplatform.pf.models;

import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Programma_Type", discriminatorType = DiscriminatorType.STRING)
@Data
public abstract class ProgrammaFedelta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;
    private int rapporto;

    @ManyToOne
    private PuntoVendita puntoVendita;

    @Enumerated(value = EnumType.STRING)
    @Column(updatable = false)
    private PFType type;
}
