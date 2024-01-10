package anso;

import anso.expensetracker.ExpenseTrackerApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {
  public static void main(String[] args) {
    SpringApplication.from(ExpenseTrackerApplication::main)
    .with(PostgresContainerConfiguration.class)
    .run(args);
  }
}