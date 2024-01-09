package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JacksonXmlProperty(localName = "TRNTYPE")
  private String type;
  @JacksonXmlProperty(localName = "DTPOSTED")
  private String date;
  @JacksonXmlProperty(localName = "TRNAMT")
  private String amount;
  @JacksonXmlProperty(localName = "NAME")
  private String name;
}