package anso.expensetracker.adapter.out.persistence;

import anso.expensetracker.domain.BankAccount;
import anso.expensetracker.domain.Transaction;
import anso.expensetracker.port.out.PersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

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
  public void saveTransactions(List<Transaction> transactions, UUID bankId) {
    transactions.forEach(transaction -> {
      var entity = TransactionEntity.fromTransaction(transaction, bankId);
      transactionRepository.save(entity);
    });
  }

  @Override
  public UUID saveBankAccount(BankAccount bankAccount) {
      var entity = BankAccountEntity.fromBankAccount(bankAccount);
      var savedEntity =  bankAccountRepository.save(entity);
      return savedEntity.getId();
  }
}