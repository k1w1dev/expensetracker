package anso.expensetracker.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, UUID> {

  BankAccountEntity findByBankIdAndAccountId(String bankId, String bankAccountId);
}