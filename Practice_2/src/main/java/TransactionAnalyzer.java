import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


public abstract class TransactionAnalyzer {
    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public static int countTransactionsByMonth(String monthYear, List<Transaction> transactions) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopTenExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static List<Transaction> findTopTenExpensesByMonth(String monthYear, List<Transaction> transactions) {
        List<Transaction> expenses = new ArrayList<>();

        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                String transactionMonthYear = LocalDate.parse(t.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")).format(DateTimeFormatter.ofPattern("MM-yyyy"));
                if (transactionMonthYear.equals(monthYear)) {
                    expenses.add(t);
                }
            }
        }

        for (int i = 0; i < expenses.size() - 1; i++) {
            for (int j = i + 1; j < expenses.size(); j++) {
                if (expenses.get(i).getAmount() > expenses.get(j).getAmount()) {
                    Transaction temp = expenses.get(i);
                    expenses.set(i, expenses.get(j));
                    expenses.set(j, temp);
                }
            }
        }

        return expenses.size() > 10 ? expenses.subList(0, 10) : expenses;
    }

    public static List<Transaction> findLowTenExpensesByMonth(String monthYear, List<Transaction> transactions) {
        List<Transaction> expenses = new ArrayList<>();

        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                String transactionMonthYear = LocalDate.parse(t.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")).format(DateTimeFormatter.ofPattern("MM-yyyy"));
                if (transactionMonthYear.equals(monthYear)) {
                    expenses.add(t);
                }
            }
        }

        for (int i = 0; i < expenses.size() - 1; i++) {
            for (int j = i + 1; j < expenses.size(); j++) {
                if (expenses.get(i).getAmount() < expenses.get(j).getAmount()) {
                    Transaction temp = expenses.get(i);
                    expenses.set(i, expenses.get(j));
                    expenses.set(j, temp);
                }
            }
        }

        return expenses.size() > 10 ? expenses.subList(0, 10) : expenses;
    }
}
