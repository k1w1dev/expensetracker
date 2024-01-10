package anso.expensetracker.adapter.out.persistence;

import anso.expensetracker.domain.Transaction;
import anso.expensetracker.port.out.PersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class PostgresPersistence implements PersistencePort {

  @Autowired
  private TransactionRepository transactionRepository;

  @Override
  public void saveTransactions(List<Transaction> transactions) {
    transactions.forEach(transaction -> {
      var entity = TransactionEntity.fromTransaction(transaction);
      transactionRepository.save(entity);
    });
  }
}