package anso.expensetracker.adapter.out.persistence;

import anso.expensetracker.domain.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transaction",
        schema = "public",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id", "fit_id", "bank_account_id"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @Column(name = "bank_account_id")
  UUID bankAccountId;

  @Column(name = "txn_date")
  LocalDate txnDate;

  @Column(name = "fit_id")
  String fitId;

  @Column(name = "type")
  String type;

  @Column(name = "amount")
  BigDecimal amount;

  @Column(name = "memo")
  String memo;

  @Column(name = "payee")
  String payee;

  public static TransactionEntity fromTransaction(Transaction transaction, UUID bankAccountId) {
    var txn = new TransactionEntity();
    txn.setTxnDate(transaction.date());
    txn.setType(transaction.type().getText());
    txn.setAmount(transaction.amount());
    txn.setPayee(transaction.payee());
    txn.setMemo(transaction.memo());
    txn.setFitId(transaction.fitId());
    txn.setBankAccountId(bankAccountId);
    return txn;
  }
}