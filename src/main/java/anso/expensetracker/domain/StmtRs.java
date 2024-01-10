package anso.expensetracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StmtRs {

  public BankTransList getBankTransList() {
    return bankTransList;
  }

  public void setBankTransList(BankTransList bankTransList) {
    this.bankTransList = bankTransList;
  }

  @JacksonXmlProperty(localName = "BANKTRANLIST")
  private BankTransList bankTransList;

  public BankAcctFrom getBankAcctFrom() {
    return bankAcctFrom;
  }

  public void setBankAcctFrom(BankAcctFrom bankAcctFrom) {
    this.bankAcctFrom = bankAcctFrom;
  }

  @JacksonXmlProperty(localName = "BANKACCTFROM")
  private BankAcctFrom bankAcctFrom;
}