package it.unicam.ids.loyaltyplatform.Account.models;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Account_Type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    @Enumerated(value = EnumType.STRING)
    private Ruolo ruolo;

    public Account() {

    }

    public Account(String nome, String cognome, String email, String telefono, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
        this.ruolo = ruolo;
    }


    public int getId() {
        return this.id;
    }


    public String getNome() {
        return this.nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCognome() {
        return this.cognome;
    }


    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getTelefono() {
        return this.telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public Ruolo getRuolo() {
        return this.ruolo;
    }
}
