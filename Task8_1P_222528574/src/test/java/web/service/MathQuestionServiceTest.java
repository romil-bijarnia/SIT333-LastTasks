package web.service;

import org.junit.Assert;
import org.junit.Test;

public class MathQuestionServiceTest {

    @Test
    public void testQ1AdditionValid() {
        // 3 + 4 should equal 7.0
        Assert.assertEquals(7.0, MathQuestionService.q1Addition("3", "4"), 0.001);
    }

    @Test
    public void testQ2SubtractionValid() {
        // 5 - 4 should equal 1.0
        Assert.assertEquals(1.0, MathQuestionService.q2Subtraction("5", "4"), 0.001);
    }

    @Test
    public void testQ3MultiplicationValid() {
        // 4 * 5 should equal 20.0
        Assert.assertEquals(20.0, MathQuestionService.q3Multiplication("4", "5"), 0.001);
    }

    @Test
    public void testQ3MultiplicationWithNegative() {
        // -3 * 5 should equal -15.0
        Assert.assertEquals(-15.0, MathQuestionService.q3Multiplication("-3", "5"), 0.001);
    }

    @Test(expected = NumberFormatException.class)
    public void testQ3MultiplicationInvalidInput() {
        // Invalid input should throw NumberFormatException
        MathQuestionService.q3Multiplication("abc", "2");
    }
}
