package it.unicam.ids.loyaltyplatform.pf.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.unicam.ids.loyaltyplatform.premio.models.Premio;
import it.unicam.ids.loyaltyplatform.tessera.models.Tessera;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Programma_Type", discriminatorType = DiscriminatorType.STRING)
@Data
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProgrammaPunti.class, name = "PFPUNTI"),
        @JsonSubTypes.Type(value = ProgrammaLivelli.class, name = "PFLIVELLI"),
        @JsonSubTypes.Type(value = ProgrammaCoalizione.class, name = "PFCOALIZIONE"),
        @JsonSubTypes.Type(value = ProgrammaCashback.class, name = "PFCASHBACK"),
        @JsonSubTypes.Type(value = ProgrammaVip.class, name = "PFVIP")
})
public abstract class ProgrammaFedelta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;
    private int rapporto;

    @Enumerated(value = EnumType.STRING)
    @Column(updatable = false)
    private PFType type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "programmaFedelta")
    @JsonIgnore
    private List<Tessera> tesserati = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "programmaFedelta")
    private List<Premio> premi = new ArrayList<>();

}
