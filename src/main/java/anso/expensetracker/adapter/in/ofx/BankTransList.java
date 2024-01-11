package anso.expensetracker.adapter.in.ofx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AccessLevel;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter(AccessLevel.PACKAGE)
class BankTransList {

  List<StmtTrn> getStmtTrns() {
    return stmtTrns;
  }

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "STMTTRN")
  private List<StmtTrn> stmtTrns;
}