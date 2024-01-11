package anso.expensetracker.domain;


public enum TxnType {

  CREDIT("CREDIT"),
  DEBIT("DEBIT"),
  UNKNOWN("UNKNOWN");

  public String getText() {
    return type;
  }

  private String type;

  TxnType(String type) {
    this.type = type;
  }

  public static TxnType fromString(String type) {
    return switch (type) {
      case "CREDIT" -> CREDIT;
      case "DEBIT" -> DEBIT;
      default -> UNKNOWN;
    };
  }
}