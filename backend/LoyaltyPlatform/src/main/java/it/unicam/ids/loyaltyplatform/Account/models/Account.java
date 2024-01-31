package it.unicam.ids.loyaltyplatform.account.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Account_Type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Data
public abstract class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String telefono;
    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = Permessi.class, fetch = FetchType.EAGER)
    private List<Permessi> permessi = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private AccountType type;


    public void addPermesso(Permessi permesso){
        this.permessi.add(permesso);
    }
    public void removePermesso(Permessi permesso){
        this.permessi.remove(permesso);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> perms = new ArrayList<>();
        for(Permessi p : permessi){
            perms.add(new SimpleGrantedAuthority(p.name()));
        }
        return perms;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
