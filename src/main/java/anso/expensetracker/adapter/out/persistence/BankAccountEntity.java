package anso.expensetracker.adapter.out.persistence;

import anso.expensetracker.domain.BankAccount;
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
@Table(name = "bank_account", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @Column(name = "bank_id")
  String bankId;

  @Column(name = "account_id")
  String accountId;

  @Column(name = "account_type")
  String accountType;

  static BankAccountEntity fromBankAccount(BankAccount bankAccount) {
    var entity = new BankAccountEntity();
    entity.setAccountId(bankAccount.accountId());
    entity.setBankId(bankAccount.bankId());
    entity.setAccountType(bankAccount.type().getText());
    return entity;
  }

}