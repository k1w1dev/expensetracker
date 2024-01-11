package anso.expensetracker.adapter.in.ofx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AccessLevel;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter(AccessLevel.PACKAGE)
class StmtRs {

  public BankTransList getBankTransList() {
    return bankTransList;
  }

  BankAcctFrom getBankAcctFrom() {
    return bankAcctFrom;
  }

  @JacksonXmlProperty(localName = "BANKTRANLIST")
  private BankTransList bankTransList;

  @JacksonXmlProperty(localName = "BANKACCTFROM")
  private BankAcctFrom bankAcctFrom;
}