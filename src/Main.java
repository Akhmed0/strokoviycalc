import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String expression = scanner.nextLine();

        try {
            String result = calculateExpression(expression);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calculateExpression(String expression) {
        String[] parts = expression.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Некорректное выражение! Проверьте правильность ввода.");
        }

        String operand1 = parts[0];
        String operator = parts[1];
        String operand2 = parts[2];

        if (!operand1.matches("\".*\"") || !operand2.matches("\".*\"")) {
            throw new IllegalArgumentException("Первый и второй операнды должны быть строками, заключенными в двойные кавычки!");
        }

        operand1 = operand1.substring(1, operand1.length() - 1); // Удаляем кавычки из операндов-строк
        operand2 = operand2.substring(1, operand2.length() - 1);

        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1.replace(operand2, "");
            case "*":
                int repeatCount = Integer.parseInt(operand2);
                return repeatString(operand1, repeatCount);
            case "/":
                int divisor = Integer.parseInt(operand2);
                int divideLength = operand1.length() / divisor;
                return operand1.substring(0, divideLength);
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция! Доступны операции: +, -, *, /");
        }
    }

    public static String repeatString(String str, int repeatCount) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < repeatCount; i++) {
            result.append(str);
        }
        return result.toString();
    }
}