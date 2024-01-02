package it.unicam.ids.loyaltyplatform.Account;

import it.unicam.ids.loyaltyplatform.Account.models.Account;
import it.unicam.ids.loyaltyplatform.Account.models.Ruolo;
import it.unicam.ids.loyaltyplatform.Account.models.Titolare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account){
        return accountRepository.save(account);
    }

    public List<Account> findAllAccounts(){
        return accountRepository.findAll();
    }

    public Account findAccountById(int id){
        return accountRepository.findAccountById(id);
    }

    public Titolare findTitolareById(int id){
        Titolare tempTitolare = accountRepository.findTitolareById(id);
        if(tempTitolare == null){
            throw new IllegalArgumentException("Account is not TITOLARE");
        } else {
            return tempTitolare;
        }
    }

    public Account updateAccount(Account account){ return accountRepository.save(account); }

    public void deleteAccount(int id){
        accountRepository.deleteAccountById(id);
    }

}
