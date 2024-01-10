package anso.expensetracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAcctFrom {

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  @JacksonXmlProperty(localName = "BANKID")
  private String bankId;

  @JacksonXmlProperty(localName = "ACCTID")
  private String accountId;

  @JacksonXmlProperty(localName = "ACCTTYPE")
  private String accountType;


}