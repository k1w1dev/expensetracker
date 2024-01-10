package anso.expensetracker.adapter.out.persistence;

import anso.expensetracker.domain.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "transaction", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @Column(name = "bank_account_id")
  String bankAccountId;

  @Column(name = "fit_id")
  String fitId;

  @Column(name = "type")
  String type;

  @Column(name = "amount")
  String amount;

  @Column(name = "memo")
  String memo;

  public static TransactionEntity fromTransaction(Transaction transaction) {
    var txn = new TransactionEntity();
    txn.setType(transaction.getType());
    txn.setAmount(transaction.getAmount());
    txn.setMemo("Some Memo");
    txn.setFitId("fitId");
    txn.setBankAccountId("bankAccountId");
    return txn;
  }
}