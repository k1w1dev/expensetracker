package anso.expensetracker.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(TxnType type, LocalDate date, String fitId, BigDecimal amount, String payee, String memo) {
}