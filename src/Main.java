import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new java.util.Scanner(System.in);

        try {
            System.out.print("Input the first number: ");
            double num1 = Double.parseDouble(scanner.nextLine());

            System.out.print("Input the second number: ");
            double num2 = Double.parseDouble(scanner.nextLine());

            System.out.print("Choose operation (+, -, *, /, sqrt): ");
            String operation = scanner.nextLine();

            double result = 0;
            boolean validOperation = true;

            switch (operation) {
                case "+":
                    result = calculator.add(num1, num2);
                    break;
                case "-":
                    result = calculator.subtract(num1, num2);
                    break;
                case "*":
                    result = calculator.multiply(num1, num2);
                    break;
                case "/":
                    result = calculator.divide(num1, num2);
                    break;
                default:
                    System.out.println("Invalid operation.");
                    validOperation = false;
            }

            if (validOperation) {
                System.out.println("Result: " + result);
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Custom error: " + e.getMessage());
        } finally {
            System.out.println("Calculation process completed.");
            scanner.close();
        }
    }
}
