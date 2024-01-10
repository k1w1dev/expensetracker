package anso.expensetracker.port.out;

import anso.expensetracker.domain.Transaction;

import java.util.List;

public interface PersistencePort {

  void saveTransactions(List<Transaction> transactions);
}