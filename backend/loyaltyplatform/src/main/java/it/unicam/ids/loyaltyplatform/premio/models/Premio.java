package it.unicam.ids.loyaltyplatform.premio.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Premio_Type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PremioPunti.class, name = "PREMIO_PUNTI"),
        @JsonSubTypes.Type(value = PremioLivello.class, name = "PREMIO_LIVELLI"),
})
public abstract class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;

    private String nome;

    private int prezzo;


    @ManyToOne
    private ProgrammaFedelta programmaFedelta;


}
