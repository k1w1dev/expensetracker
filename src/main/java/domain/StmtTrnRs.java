package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StmtTrnRs {

  public StmtRs getStmtRs() {
    return stmtRs;
  }

  public void setStmtRs(StmtRs stmtRs) {
    this.stmtRs = stmtRs;
  }

  @JacksonXmlProperty(localName = "STMTRS")
  private StmtRs stmtRs;

}