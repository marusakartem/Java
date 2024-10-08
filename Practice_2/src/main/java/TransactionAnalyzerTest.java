import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance() {
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth("02-2023", transactions);
        int countMar = TransactionAnalyzer.countTransactionsByMonth("03-2023", transactions);

        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testFindTopTenExpenses() {
        List<Transaction> data = Arrays.asList(
                new Transaction("05-12-2023", -7850.0, "Сільпо"),
                new Transaction("07-12-2023", -1200.0, "Аптека"),
                new Transaction("10-12-2023", 80000.0, "Зарплата"),
                new Transaction("12-12-2023", 1500.0, "Авторські винагороди"),
                new Transaction("14-12-2023", -3200.0, "Комунальні послуги"),
                new Transaction("16-12-2023", 25000.0, "Фріланс"),
                new Transaction("18-12-2023", -600.0, "Кав'ярня"),
                new Transaction("20-12-2023", -7500.0, "Подарунки"),
                new Transaction("22-12-2023", -6000.0, "Бензин"),
                new Transaction("24-12-2023", -1950.0, "Ресторан"),
                new Transaction("26-12-2023", 3000.0, "Премія"),
                new Transaction("28-12-2023", -1900.0, "Кінотеатр"),
                new Transaction("30-12-2023", -3200.0, "Новорічні прикраси"),
                new Transaction("05-01-2024", -4500.0, "Сільпо"),
                new Transaction("07-01-2024", -2000.0, "Аптека"),
                new Transaction("10-01-2024", 85000.0, "Зарплата"),
                new Transaction("12-01-2024", 1600.0, "Авторські винагороди"),
                new Transaction("14-01-2024", -3300.0, "Комунальні послуги"),
                new Transaction("16-01-2024", 26000.0, "Фріланс"),
                new Transaction("18-01-2024", -750.0, "Кав'ярня"),
                new Transaction("20-01-2024", -8800.0, "Подарунки"),
                new Transaction("22-01-2024", -5500.0, "Бензин"),
                new Transaction("24-01-2024", -3000.0, "Ресторан"),
                new Transaction("26-01-2024", 3100.0, "Премія"),
                new Transaction("28-01-2024", -1850.0, "Кінотеатр"),
                new Transaction("30-01-2024", -9100.0, "Інші витрати")
        );
        List<Transaction> expectedTopExpenses = new ArrayList<>();
        for (Transaction t : data) {
            if(t.getAmount() < 0) {
                expectedTopExpenses.add(t);
            }
        }
        expectedTopExpenses.sort(Comparator.comparing(Transaction::getAmount));
        if(expectedTopExpenses.size() > 10) {
            expectedTopExpenses = expectedTopExpenses.subList(0, 10);
        }

        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.getTransactions(filePath);
        List<Transaction> topExpenses = TransactionAnalyzer.findTopTenExpenses(transactions);

        Assertions.assertEquals(expectedTopExpenses, topExpenses, "Список найбільших витрат неправильний");
    }
}
