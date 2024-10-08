import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {
    public static List<Transaction> getTransactions(String filepath) {
        List<String> lines = readTransactions(filepath);
        return processTransactions(lines);
    }

    public static List<String> readTransactions(String filepath) {
        try {
            URL url = new URL(filepath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                List<String> lines = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                return lines;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static List<Transaction> processTransactions(List<String> lines) {
        List<Transaction> transactions = new ArrayList<>();

        for (String line : lines) {
            String[] values = line.split(",");
            Transaction transaction = new Transaction(values[0], Double.parseDouble(values[1]), values[2]);
            transactions.add(transaction);
        }
        return transactions;
    }
}
