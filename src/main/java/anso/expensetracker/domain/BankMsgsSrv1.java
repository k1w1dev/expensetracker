package anso.expensetracker.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class BankMsgsSrv1 {

  public StmtTrnRs getStmtTrnRs() {
    return stmtTrnRs;
  }

  public void setStmtTrnRs(StmtTrnRs stmtTrnRs) {
    this.stmtTrnRs = stmtTrnRs;
  }

  @JacksonXmlProperty(localName = "STMTTRNRS")
  private StmtTrnRs stmtTrnRs;
}