package anso.expensetracker.adapter.in.ofx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter(AccessLevel.PACKAGE)
class BankAcctFrom {

  @JacksonXmlProperty(localName = "BANKID")
  private String bankId;

  @JacksonXmlProperty(localName = "ACCTID")
  private String accountId;

  @JacksonXmlProperty(localName = "ACCTTYPE")
  private String accountType;


}