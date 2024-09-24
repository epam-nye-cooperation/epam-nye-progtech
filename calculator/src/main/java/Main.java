import java.util.Scanner;

import operator.AddOperator;
import printer.ConsolePrinter;

public final class Main {

    private static final int ADD = 1;
    private static final int SUBTRACT = 2;
    private static final int MULTIPLY = 3;
    private static final int DIVIDE = 4;

    private Main() {

    }

    public static void main(final String[] args) {
        AddOperator addOperator = new AddOperator();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        Scanner scanner = new Scanner(System.in);
        consolePrinter.print("Enter number one: ");
        int numberOne = scanner.nextInt();
        consolePrinter.print("Enter number two: ");
        int numberTwo = scanner.nextInt();
        consolePrinter.print("Enter on operator: ");
        int operator = scanner.nextInt();
        switch (operator) {
            case ADD -> consolePrinter.print("Result " + addOperator.calculate(numberOne, numberTwo));
            case SUBTRACT -> consolePrinter.print("Result " + (numberOne - numberTwo));
            case MULTIPLY -> consolePrinter.print("Result " + (numberOne * numberTwo));
            case DIVIDE -> consolePrinter.print("Result " + (numberOne / numberTwo));
            default -> consolePrinter.print("Invalid operator");
        }
    }
}
