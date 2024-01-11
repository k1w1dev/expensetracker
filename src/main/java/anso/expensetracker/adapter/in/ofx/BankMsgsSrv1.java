package anso.expensetracker.adapter.in.ofx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AccessLevel;
import lombok.Setter;


@Setter(AccessLevel.PACKAGE)
@JsonIgnoreProperties(ignoreUnknown = true)
class BankMsgsSrv1 {

  StmtTrnRs getStmtTrnRs() {
    return stmtTrnRs;
  }

  @JacksonXmlProperty(localName = "STMTTRNRS")
  private StmtTrnRs stmtTrnRs;
}