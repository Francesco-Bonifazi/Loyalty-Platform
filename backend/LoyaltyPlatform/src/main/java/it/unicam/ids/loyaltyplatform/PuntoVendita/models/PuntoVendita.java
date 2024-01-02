package it.unicam.ids.loyaltyplatform.PuntoVendita.models;
import jakarta.persistence.*;


@Entity
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

    public PuntoVendita() {

    }

    public PuntoVendita(String nome, String email, String telefono, String indirizzo) {
        this.nome = nome;
        this.email = email;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.logo = "null";
        this.linkExt = "null";
    }


    public int getId(){
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getIndirizzo() {
        return this.indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLinkExt() {
        return this.linkExt;
    }

    public void setLinkExt(String linkExt) {
        this.linkExt = linkExt;
    }
}
