package web.service;

/**
 * Provides methods to perform math operations for the quiz questions.
 */
public class MathQuestionService {

    public static double q1Addition(String number1, String number2) {
        // Parse inputs as doubles and return their sum
        double a = Double.parseDouble(number1);
        double b = Double.parseDouble(number2);
        return a + b;
    }

    public static double q2Subtraction(String number1, String number2) {
        // Parse inputs and return number1 minus number2
        double a = Double.parseDouble(number1);
        double b = Double.parseDouble(number2);
        return a - b;
    }

    public static double q3Multiplication(String number1, String number2) {
        // Parse inputs and return their product
        double a = Double.parseDouble(number1);
        double b = Double.parseDouble(number2);
        return a * b;
    }
}
