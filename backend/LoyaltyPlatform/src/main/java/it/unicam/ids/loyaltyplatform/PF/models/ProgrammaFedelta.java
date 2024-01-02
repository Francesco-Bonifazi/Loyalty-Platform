package it.unicam.ids.loyaltyplatform.PF.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Programma_Type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true)   // field on which we differentiate objects
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProgrammaPunti.class, name = "PFPUNTI"),
        @JsonSubTypes.Type(value = ProgrammaLivelli.class, name = "PFLIVELLI"),
        @JsonSubTypes.Type(value = ProgrammaVip.class, name = "PFVIP"),
        @JsonSubTypes.Type(value = ProgrammaCashback.class, name = "PFCASHBACK"),
        @JsonSubTypes.Type(value = ProgrammaCoalizione.class, name = "PFCOALIZIONE")
})
public abstract class ProgrammaFedelta{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;
    @Enumerated(value = EnumType.STRING)
    private PFType type;
    private int rapporto;

    private int pvId;


    public ProgrammaFedelta() {

    }

    public ProgrammaFedelta(PFType type, int pvId){
        this.pvId = pvId;
        this.type = type;
    }

    public ProgrammaFedelta(PFType type, int rapporto, int pvId) {
        this.type = type;
        this.rapporto = rapporto;
        this.pvId = pvId;
    }

    public PFType getType() {
        return this.type;
    }


    public int getId() {
        return this.id;
    }


    public void setRapporto(short rapporto) {
        this.rapporto = rapporto;
    }


    public int getRapporto() {
        return this.rapporto;
    }

    public int getPvId() {
        return this.pvId;
    }

    public void setPvId(int pvId) {
        this.pvId = pvId;
    }
}
