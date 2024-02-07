package it.unicam.ids.loyaltyplatform.account;

import it.unicam.ids.loyaltyplatform.account.models.Account;
import it.unicam.ids.loyaltyplatform.account.models.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    <T extends Account> T findAccountById(int id);
    <T extends Account> T findAccountByEmail(String email);
    <T extends Account> T findAccountByIdAndType(int id, AccountType type);

    <T extends Account> List<T> findAccountsByType(AccountType type);
    void deleteAccountById(int id);
    void deleteAccountByEmail(String email);

}
