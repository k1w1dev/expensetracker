package anso.expensetracker.domain;

public record BankAccount(String bankId, String accountId, AccountType type) {
}