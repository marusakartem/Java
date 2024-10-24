import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printExpensesByMonthReport(String monthYear, List<Transaction> topExpenses, List<Transaction> lowestExpenses) {
        System.out.println("10 найбільших витрат за " + monthYear + ": ");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }

        System.out.println("10 найменших витрат за " + monthYear + ": ");
        for (Transaction expense : lowestExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printGeneralExpansesReport(List<Transaction> transactions) {
        List<String> months = new ArrayList<>();
        double amountByOneSymbol = 1000.0;
        String symbol = "*";

        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                String monthYear = LocalDate.parse(t.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")).format(DateTimeFormatter.ofPattern("MM-yyyy"));
                if (!months.contains(monthYear)) {
                    months.add(monthYear);
                }
            }
        }

        for (String month : months) {
            System.out.println("Витрати за " + month + ":");

            List<String> categories = new ArrayList<>();
            for (Transaction t : transactions) {
                if (t.getAmount() < 0) {
                    String transactionMonth = LocalDate.parse(t.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")).format(DateTimeFormatter.ofPattern("MM-yyyy"));
                    if (transactionMonth.equals(month) && !categories.contains(t.getDescription())) {
                        categories.add(t.getDescription());
                    }
                }
            }

            for (String category : categories) {
                double totalAmount = 0;
                for (Transaction t : transactions) {
                    if (t.getAmount() < 0) {
                        String transactionMonth = LocalDate.parse(t.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")).format(DateTimeFormatter.ofPattern("MM-yyyy"));
                        if (transactionMonth.equals(month) && t.getDescription().equals(category)) {
                            totalAmount += t.getAmount();
                        }
                    }
                }

                int countSymbols = (int) Math.abs(totalAmount / amountByOneSymbol);
                System.out.print(category + ": ");
                for (int i = 0; i < countSymbols; i++) {
                    System.out.print(symbol);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
