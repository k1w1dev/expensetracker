package anso.expensetracker.domain;

public enum AccountType {
  CHECKING("CHECKING"),
  SAVINGS("SAVINGS"),
  UNKNOWN("UNKNOWN");

  public String getText() {
    return type;
  }

  private String type;

  AccountType(String type) {
    this.type = type;
  }

  public static AccountType fromString(String type) {
    return switch (type) {
      case "CHECKING" -> CHECKING;
      case "SAVINGS" -> SAVINGS;
      default -> UNKNOWN;
    };
  }
}