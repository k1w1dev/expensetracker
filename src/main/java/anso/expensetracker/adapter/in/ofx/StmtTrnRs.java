package anso.expensetracker.adapter.in.ofx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AccessLevel;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter(AccessLevel.PACKAGE)
class StmtTrnRs {

  StmtRs getStmtRs() {
    return stmtRs;
  }

  @JacksonXmlProperty(localName = "STMTRS")
  private StmtRs stmtRs;

}