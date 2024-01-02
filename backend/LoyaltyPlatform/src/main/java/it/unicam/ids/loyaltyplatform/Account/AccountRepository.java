package it.unicam.ids.loyaltyplatform.Account;

import it.unicam.ids.loyaltyplatform.Account.models.Account;
import it.unicam.ids.loyaltyplatform.Account.models.Ruolo;
import it.unicam.ids.loyaltyplatform.Account.models.Titolare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    void deleteAccountById(int id);
    Account findAccountById(int id);

    //Account findAccountByIdAndRuolo_Titolare(int id);

    @Query("select t from Account t where t.id = ?1 and t.ruolo = 'TITOLARE'")
    Titolare findTitolareById(int id);



}
