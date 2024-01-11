package anso.expensetracker.adapter.in.ofx;

import anso.expensetracker.domain.AccountType;
import anso.expensetracker.domain.BankAccount;
import anso.expensetracker.domain.Transaction;
import anso.expensetracker.domain.TxnType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AccessLevel;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter(AccessLevel.PACKAGE)
public class OFX {

  @JacksonXmlProperty(localName = "BANKMSGSRSV1")
  private BankMsgsSrv1 bankMsgsRsv1;


  public BankAccount getBankAccount() {
    return fromBankAcctFrom(this.bankMsgsRsv1.getStmtTrnRs().getStmtRs().getBankAcctFrom());
  }

  BankAccount fromBankAcctFrom(BankAcctFrom bankAcctFrom) {
    return new BankAccount(bankAcctFrom.getBankId(), bankAcctFrom.getAccountId(), AccountType.fromString(bankAcctFrom.getAccountType()));
  }

  public List<Transaction> getTransactions() {
    return this.bankMsgsRsv1.getStmtTrnRs().getStmtRs().getBankTransList().getStmtTrns().stream().map(this::fromStatementTransaction).toList();
  }

  Transaction fromStatementTransaction(StmtTrn stmtTrn) {
    final TxnType txnType = TxnType.fromString(stmtTrn.getType());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    formatter = formatter.withLocale(Locale.getDefault());
    final LocalDate date = LocalDate.parse(stmtTrn.getDate(), formatter);

    final BigDecimal amount = new BigDecimal(stmtTrn.getAmount().replace("-", ""));

    final String payee = stmtTrn.getName();

    return new Transaction(txnType, date, stmtTrn.getFitId(), amount, payee, stmtTrn.getMemo());
  }

}