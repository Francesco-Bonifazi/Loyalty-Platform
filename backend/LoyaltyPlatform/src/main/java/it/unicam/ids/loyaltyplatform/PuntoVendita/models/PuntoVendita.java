package it.unicam.ids.loyaltyplatform.puntovendita.models;

import it.unicam.ids.loyaltyplatform.account.models.Account;
import it.unicam.ids.loyaltyplatform.account.models.Titolare;
import it.unicam.ids.loyaltyplatform.pf.models.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PuntoVendita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;

    private String nome;
    private String email;
    private String telefono;
    private String indirizzo;
    private String logo;
    private String linkExt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "puntoVendita", cascade = CascadeType.ALL)
    private List<ProgrammaFedelta> programmiFedelta;

    @OneToOne
    private Titolare titolare;

    public PuntoVendita(String nome, String email, String telefono, String indirizzo) {
        this.nome = nome;
        this.email = email;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.logo = null;
        this.linkExt = null;
    }

    public <T extends ProgrammaFedelta> T addProgrammaFedelta(T programmaFedelta){
        if(programmiFedelta.size() == 5){
            throw new IllegalArgumentException("Punto Vendita has reached the max amount of Programmi!");
        }
        for(ProgrammaFedelta pf : programmiFedelta){
            if(pf.equals(programmaFedelta)){
                throw new IllegalArgumentException("Programma fedeltà already exists!");
            }
        }
        programmiFedelta.add(programmaFedelta);
        return programmaFedelta;
    }

    public ProgrammaFedelta addProgrammaFedelta(PFType type, PuntoVendita puntoVendita){
        if(programmiFedelta.size() == 5){
            throw new IllegalArgumentException("Punto Vendita has reached the max amount of Programmi!");
        }
        for(ProgrammaFedelta pf : programmiFedelta){
            if(pf.getType().equals(type)){
                throw new IllegalArgumentException("Programma fedeltà already exists!");
            }
        }
        ProgrammaFedelta newPF;
        switch (type) {
            case PFPUNTI -> {
                newPF = new ProgrammaPunti(puntoVendita);
            }
            case PFLIVELLI -> {
                newPF = new ProgrammaLivelli(puntoVendita);
            }
            case PFCASHBACK -> {
                newPF = new ProgrammaCashback(puntoVendita);
            }
            case PFVIP -> {
                newPF = new ProgrammaVip(puntoVendita);
            }
            case PFCOALIZIONE -> {
                newPF = new ProgrammaCoalizione(puntoVendita);
            }
            default -> throw new IllegalArgumentException("Invalid Type");
        }
        programmiFedelta.add(newPF);
        return newPF;

    }

    public <T extends ProgrammaFedelta> void removeProgrammaFedelta(T programmaFedelta){
        if(programmiFedelta.isEmpty()){
            throw new IllegalArgumentException("Punto Vendita doesn't have any Programma Fedeltà!");
        }
        if(!programmiFedelta.contains(programmaFedelta)){
            throw new IllegalArgumentException("Programma Fedeltà not found!");
        } else {
            programmiFedelta.remove(programmaFedelta);
        }
    }

    public void removeProgrammaFedelta(int id){
        if(programmiFedelta.isEmpty()){
            throw new IllegalArgumentException("Punto Vendita doesn't have any Programma Fedeltà!");
        }
        for (ProgrammaFedelta pf : programmiFedelta){
            if(pf.getId() == id){
                programmiFedelta.remove(pf);
            }
        }
    }

    public <T extends ProgrammaFedelta> T updateProgrammaFedelta(T programmaFedelta){
        if(programmiFedelta.isEmpty()){
            throw new IllegalArgumentException("Punto Vendita doesn't have any Programma Fedeltà!");
        }
        for (ProgrammaFedelta pf : programmiFedelta){
            if(pf.getId() == programmaFedelta.getId()){
                programmiFedelta.set(programmiFedelta.indexOf(pf), programmaFedelta);
                return programmaFedelta;
            }
        }
        throw new IllegalArgumentException("Couldn't find Programma Fedeltà with ID: "+programmaFedelta.getId()+"!");
    }

}
