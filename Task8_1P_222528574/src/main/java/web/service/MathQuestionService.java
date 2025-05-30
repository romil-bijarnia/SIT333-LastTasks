package web.service;

public class MathQuestionService {

    /**
     * Calculate Q1 result (addition).
     * @param number1 First addend as string
     * @param number2 Second addend as string
     * @return Sum of the two numbers, or null if inputs are invalid
     */
    public static Double q1Addition(String number1, String number2) {
        // Check for empty or null inputs
        if (number1 == null || number2 == null || number1.isEmpty() || number2.isEmpty()) {
            return null;
        }
        try {
            double result = Double.valueOf(number1) + Double.valueOf(number2);
            return result;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Calculate Q2 result (subtraction).
     * @param number1 Minuend as string
     * @param number2 Subtrahend as string
     * @return Difference of the two numbers, or null if inputs are invalid
     */
    public static Double q2Subtraction(String number1, String number2) {
        if (number1 == null || number2 == null || number1.isEmpty() || number2.isEmpty()) {
            return null;
        }
        try {
            double result = Double.valueOf(number1) - Double.valueOf(number2);
            return result;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Calculate Q3 result (multiplication).
     * @param number1 First factor as string
     * @param number2 Second factor as string
     * @return Product of the two numbers
     * @throws NumberFormatException if either input is empty or non-numeric
     */
    public static Double q3Multiplication(String number1, String number2) {
        // Validate inputs (non-empty and numeric)
        if (number1 == null || number2 == null || number1.isEmpty() || number2.isEmpty()) {
            throw new NumberFormatException("Invalid input for multiplication");
        }
        // Perform multiplication (throws NumberFormatException if inputs are not numeric)
        double result = Double.valueOf(number1) * Double.valueOf(number2);
        return result;
    }
}
