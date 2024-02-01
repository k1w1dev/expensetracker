package anso.expensetracker.adapter.out.persistence;

import anso.expensetracker.domain.BankAccount;
import anso.expensetracker.domain.Transaction;
import anso.expensetracker.port.out.PersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Component
class PostgresPersistence implements PersistencePort {

  @Autowired
  private final TransactionRepository transactionRepository;

  @Autowired
  private final BankAccountRepository bankAccountRepository;

  PostgresPersistence(TransactionRepository transactionRepository,
                      BankAccountRepository bankAccountRepository) {
    this.transactionRepository = transactionRepository;
    this.bankAccountRepository = bankAccountRepository;
  }

  @Override
  public int saveTransactions(List<Transaction> transactions, UUID bankId) {
    AtomicInteger saved = new AtomicInteger();
    transactions.forEach(transaction -> {
      var entity = TransactionEntity.fromTransaction(transaction, bankId);
      var existingEntity = transactionRepository.findByFitId(entity.fitId);
      if (existingEntity.size() == 0) {
        transactionRepository.save(entity);
        saved.getAndIncrement();
      }
    });

    return saved.get();
  }

  @Override
  public UUID saveBankAccount(BankAccount bankAccount) {
      var entity = BankAccountEntity.fromBankAccount(bankAccount);
      var existingEntity = bankAccountRepository.findByBankIdAndAccountId(entity.bankId, entity.getAccountId());
      if (null == existingEntity) {
        var savedEntity =  bankAccountRepository.save(entity);
        return savedEntity.getId();
      }

      return existingEntity.getId();
  }
}