package it.unicam.ids.loyaltyplatform.account;

import it.unicam.ids.loyaltyplatform.account.models.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public <T extends Account> T addAccount(T account){
        if(this.accountRepository.findAccountByEmail(account.getEmail()) == null){
            if(account.getPassword() == null){
                account.setPassword(passwordEncoder.encode("default"));
            } else {
                account.setPassword(passwordEncoder.encode(account.getPassword()));
            }
            return accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Account with Email: "+account.getEmail()+" already exists!");
        }
    }

    public <T extends Account> T updateAccount(T account){
        T a = this.accountRepository.findAccountByEmail(account.getEmail());
        if(a == null){
            throw new IllegalArgumentException("Couldn't find Account with Email: "+account.getEmail()+" in the database!");
        } else {
            account.setId(a.getId());
            account.setPassword(a.getPassword());
            account.setType(a.getType());
            return accountRepository.save(account);
        }
    }

    public <T extends Account> T findAccount(int id){
        T account = accountRepository.findAccountById(id);
        if(account == null){
            throw new IllegalArgumentException("Couldn't find Account with ID: "+id+" in the database!");
        } else {
            return account;
        }
    }

    public <T extends Account> T findAccount(String email){
        T account = accountRepository.findAccountByEmail(email);
        if(account == null){
            throw new IllegalArgumentException("Couldn't find Account with Email: "+email+" in the database!");
        } else {
            return account;
        }
    }

    public <T extends Account> T findAccount(int id, AccountType type){
        T account = accountRepository.findAccountByIdAndType(id, type);
        if(account == null){
            throw new IllegalArgumentException("Couldn't find Account with ID:"+id+" and Type: "+type+" in the database!");
        } else {
            return account;
        }
    }

    public <T extends Account> T findAccount(T account){
        T a = accountRepository.findAccountByEmail(account.getEmail());
        if(a == null){
            throw new IllegalArgumentException("Couldn't find Account with Email: "+account.getEmail()+" in the database!");
        } else if(a != account) {
            throw new IllegalArgumentException("Couldn't find the Equal Account in the database!");
        } else {
            return a;
        }
    }

    @Transactional
    public <T extends Account> void deleteAccount(T account){
        T a = accountRepository.findAccountById(account.getId());
        if(a == null){
            throw new IllegalArgumentException("Couldn't find Account: "+account+" in the database!");
        } else if(a != account) {
            throw new IllegalArgumentException("Couldn't find the Equal Account in the database!");
        } else{
            accountRepository.delete(account);
        }
    }

    @Transactional
    public void deleteAccount(int id){
        if(this.accountRepository.findAccountById(id) == null){
            throw new IllegalArgumentException("Couldn't find Account with ID: "+id+" in the database!");
        } else {
            accountRepository.deleteAccountById(id);
        }
    }

    @Transactional
    public void deleteAccount(String email){
        if(this.accountRepository.findAccountByEmail(email) == null){
            throw new IllegalArgumentException("Couldn't find Account with Email: "+email+" in the database!");
        } else {
            accountRepository.deleteAccountByEmail(email);
        }
    }

    public List<Account> findDipendentiByPvId(int pv_id){
        List<Account> dipendenti = new ArrayList<>();
        List<Cassiere> cassieri= new ArrayList<>(accountRepository.findAccountsByType(AccountType.CASSIERE));
        List<Amministratore> admins= new ArrayList<>(accountRepository.findAccountsByType(AccountType.AMMINISTRATORE));

        for(Cassiere c : cassieri){
            if(c.getPuntoVendita().getId() == pv_id){
                dipendenti.add(c);
            }
        }
        for(Amministratore a : admins){
            if(a.getPuntoVendita().getId() == pv_id){
                dipendenti.add(a);
            }
        }
        return dipendenti;
    }

}
