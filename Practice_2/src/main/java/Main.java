import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.getTransactions(filePath);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);
        System.out.println();

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(monthYear, transactions);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);
        System.out.println();

        List<Transaction> topExpenses = TransactionAnalyzer.findTopTenExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);
        System.out.println();

        List<Transaction> topExpensesByMonth = TransactionAnalyzer.findTopTenExpensesByMonth(monthYear,transactions);
        List<Transaction> lowestExpensesByMonth = TransactionAnalyzer.findLowTenExpensesByMonth(monthYear, transactions);
        TransactionReportGenerator.printExpensesByMonthReport(monthYear, topExpensesByMonth, lowestExpensesByMonth);
        System.out.println();

        TransactionReportGenerator.printGeneralExpansesReport(transactions);
    }
}
