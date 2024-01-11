package anso.expensetracker.adapter.in.ofx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AccessLevel;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter(AccessLevel.PACKAGE)
class StmtTrn {

  public String getType() {
    return type;
  }

  public String getDate() {
    return date;
  }

  public String getAmount() {
    return amount;
  }

  public String getName() {
    return name;
  }

  @JacksonXmlProperty(localName = "TRNTYPE")
  private String type;
  @JacksonXmlProperty(localName = "DTPOSTED")
  private String date;
  @JacksonXmlProperty(localName = "TRNAMT")
  private String amount;
  @JacksonXmlProperty(localName = "NAME")
  private String name;

  public String getFitId() {
    return fitId;
  }

  public String getMemo() {
    return memo;
  }

  @JacksonXmlProperty(localName = "FITID")
  private String fitId;

  @JacksonXmlProperty(localName = "MEMO")
  private String memo;
}