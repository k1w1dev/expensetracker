package anso.expensetracker.port.out;

import anso.expensetracker.domain.BankAccount;
import anso.expensetracker.domain.Transaction;

import java.util.List;
import java.util.UUID;

public interface PersistencePort {

  int saveTransactions(List<Transaction> transactions, UUID bankId);

  UUID saveBankAccount(BankAccount bankAccount);
}