package web.service;

import org.junit.Assert;
import org.junit.Test;

public class MathQuestionServiceTest {

    @Test
    public void testAddition() {
        // 5 + 7 = 12
        Assert.assertEquals(12.0, MathQuestionService.q1Addition("5", "7"), 0.0001);
    }

    @Test
    public void testSubtraction() {
        // 10 - 4 = 6
        Assert.assertEquals(6.0, MathQuestionService.q2Subtraction("10", "4"), 0.0001);
    }

    @Test
    public void testMultiplication() {
        // 3 * 4 = 12
        Assert.assertEquals(12.0, MathQuestionService.q3Multiplication("3", "4"), 0.0001);
    }

    @Test(expected = NumberFormatException.class)
    public void testAdditionInvalidInput() {
        // Non-numeric input should throw NumberFormatException
        MathQuestionService.q1Addition("five", "2");
    }
}
