package it.unicam.ids.loyaltyplatform.account;

import it.unicam.ids.loyaltyplatform.account.models.Account;
import it.unicam.ids.loyaltyplatform.account.models.AccountType;
import it.unicam.ids.loyaltyplatform.account.models.Utente;
import it.unicam.ids.loyaltyplatform.tessera.models.Tessera;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public <T extends Account> T addAccount(T account){
        if(this.accountRepository.findAccountByEmail(account.getEmail()) == null){
            return accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Account with Email: "+account.getEmail()+" already exists!");
        }
    }

    public <T extends Account> T updateAccount(T account){
        if(this.accountRepository.findAccountByEmail(account.getEmail()) == null){
            throw new IllegalArgumentException("Couldn't find Account with Email: "+account.getEmail()+" in the database!");
        } else {
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

    public void deleteAccount(int id){
        if(this.accountRepository.findAccountById(id) == null){
            throw new IllegalArgumentException("Couldn't find Account with ID: "+id+" in the database!");
        } else {
            accountRepository.deleteAccountById(id);
        }
    }

    public void deleteAccount(String email){
        if(this.accountRepository.findAccountByEmail(email) == null){
            throw new IllegalArgumentException("Couldn't find Account with Email: "+email+" in the database!");
        } else {
            accountRepository.deleteAccountByEmail(email);
        }
    }

    public void rimuoviTesseraUtente(int utente_id, Tessera tessera){
        Utente utente = this.accountRepository.findAccountById(utente_id);
        if(this.accountRepository.findAccountById(utente_id) == null){
            throw new IllegalArgumentException("Couldn't find Account with ID: "+utente_id+" in the database!");
        } else {
            utente.removeTessera(tessera);
            this.accountRepository.save(utente);
        }
    }

    public void rimuoviTesseraUtente(Utente utente, Tessera tessera){
        if(this.accountRepository.findAccountByEmail(utente.getEmail()) == null){
            throw new IllegalArgumentException("Couldn't find Account: "+utente+" in the database!");
        } else {
            utente.removeTessera(tessera);
            this.accountRepository.save(utente);
        }
    }

}
